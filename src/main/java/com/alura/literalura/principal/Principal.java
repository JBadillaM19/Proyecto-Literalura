package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import org.hibernate.boot.jaxb.SourceType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private List<Libro> libros;
    private List<Autor> autor;

    public Principal(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("\n---------------------------------------");
            System.out.println("Escoja una de las siguientes opciones: ");
            var menu = """
                    \n1 - Buscar libro por titulo
                    2 - Ver libros registrados
                    3 - Ver autores registrados
                    4 - Filtrar autores vivos en un determinado año
                    5 - Filtrar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);

            try{
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        verLibrosRegistrados();
                        break;
                    case 3:
                        verAutoresRegistrados();
                        break;
                    case 4:
                        mostrarAutoresPorAnio();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación");
                        break;
                    default:
                        System.out.println("Opción inválida, seleccione una de las opciones disponibles");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n¡Error! Debes ingresar un número entero válido");
                teclado.nextLine();
                opcion = -1;
            }
        }
    }

    private void buscarLibro() {
        System.out.println("\nIntroduce el titulo del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replaceAll(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("\n***Libro encontrado***");
            DatosLibros datos = libroBuscado.get();
            Optional<Libro> libroExistente = libroRepositorio.findByTituloIgnoreCase(datos.titulo());

            if (libroExistente.isPresent()) {
                System.out.println("\nEl libro '" + datos.titulo() + "' ya se encuentra registrado");
            } else {
                    Libro libro = new Libro(datos);
                    String nombreAutor = datos.autor().getFirst().autor();
                    Optional<Autor> autorExistente = autorRepositorio.findByNombre(nombreAutor);
                if (autorExistente.isPresent()) {
                    libro.setAutor(autorExistente.get());
                } else {
                    System.out.println("Nuevo autor detectado, guardando...");
                }
                    libroRepositorio.save(libro);
                    System.out.println(libro);
                }
            } else {
                System.out.println("\nLibro no encontrado");
            }
    }


    private void verLibrosRegistrados() {
        libros = libroRepositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void verAutoresRegistrados() {
        autor = autorRepositorio.findAll();
        if (autor.isEmpty()) {
            System.out.println("\nNo hay autores registrados");
        } else {
            autor.forEach(System.out::println);
        }
    }

    private void mostrarAutoresPorAnio() {
        System.out.println("\nIngrese el año vivo de autor(es) que desea buscar");
       try {
           var anio = teclado.nextInt();
           teclado.nextLine();
            if (anio < 0) {
                System.out.println("\nPor favor, debe ingresar un año realmente válido");
                return;
            }
           List<Autor> autoresVivos = autorRepositorio.buscarAutoresVivosEnAnio(anio);
           if (autoresVivos.isEmpty()) {
               System.out.println("\nNo se encontraron autores vivos registrados en el año: " + anio);
           } else {
               System.out.println("\n--- AUTORES VIVOS EN EL AÑO " + anio + " ---");
               autoresVivos.forEach(System.out::println);
           }
       } catch (InputMismatchException e) {
           System.out.println("\nError: Por favor ingrese un número de año válido");
           teclado.nextLine();
       }
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("\nEscriba el idioma que desea buscar los libros:");
        System.out.println("es - (español)");
        System.out.println("en - (inglés)");
        System.out.println("pt - (portugués)");

        var idioma = teclado.nextLine().toLowerCase();
        if (idioma.equals("es") || idioma.equals("en") || idioma.equals("pt")) {
            List<Libro> librosPorIdioma = libroRepositorio.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("\nNo se encontraron libros en el idioma seleccionado");
        } else {
            System.out.println("\n--- LIBROS EN EL IDIOMA " + "("+ idioma.toUpperCase() + ")" + " ---");
            librosPorIdioma.forEach(System.out::println);
            }
        } else {
            System.out.println("\nOpción inválida. Por favor utilice uno de los códigos mostrados (es, en, pt)");
        }
    }
}