package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraElMenu() {
        System.out.println("\nEscoja una de las siguientes opciones: ");
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n1 - Buscar libro por titulo
                    2 - Ver libros registrados
                    3 - Ver autores registrados
                    4 - Filtrar autores vivos en un determinado año
                    5 - Filtrar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida, seleccione una de las opciones disponibles");
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
            System.out.println("Libro encontrado ");
            DatosLibros datos = libroBuscado.get();
            Libro libro = new Libro(datos);

            String nombreAutor = datos.autor().getFirst().autor();
            Optional<Autor> autorExistente = autorRepositorio.findByNombre(nombreAutor);
            if (autorExistente.isPresent()) {
                libro.setAutor(autorExistente.get());
            } else {
                System.out.println("Nuevo autor detectado, guardando...");
            }
            libroRepositorio.save(libro);
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
    }
}