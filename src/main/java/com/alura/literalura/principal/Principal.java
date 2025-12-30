package com.alura.literalura.principal;

import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {}
            var menu = """
                    \n1 - Buscar libro por titulo
                    2 - Ver libros registrados
                    3 - Ver autores registrados
                    4 - Filtrar autores vivos en un determinado año
                    5 - Filtrar libros por idiomar
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            //Vendrían los Switch cases
    }
}
