package com.desafio_alura.bookedex.principal;

import com.desafio_alura.bookedex.model.DatosAutor;
import com.desafio_alura.bookedex.model.DatosLibro;
import com.desafio_alura.bookedex.model.Libro;
import com.desafio_alura.bookedex.model.Respuesta;
import com.desafio_alura.bookedex.service.ConsumoAPI;
import com.desafio_alura.bookedex.service.ConvierteDatos;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.server.DelegatingServerHttpResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
        private final String URL_BASE = "https://gutendex.com/books/?search=";
        private Scanner teclado = new Scanner(System.in);
        private ConsumoAPI consumoAPI = new ConsumoAPI();
        private ConvierteDatos conversor = new ConvierteDatos();


        public void muestraElMenu() {
            var opcion = -1;
            while (opcion != 0) {
                var menu = """
                        1 - Buscar libro por título
                        2 - Listar libros registrados 
                        3 - Listar autores registrados 
                        4 - Listar autores vivos en un determinado año 
                        5 - Listar libros por idioma
                        0 - Salir
                        """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        Respuesta respuesta = obtenerRespuestaGeneral();
                        mostrarLibros(respuesta);
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("""
                                --------------------------------
                                --------¡OPCIÓN INVÁLIDA!-------
                                --------------------------------
                                """);
                }
            }
        }

    private Respuesta obtenerRespuestaGeneral() {
        System.out.println("Introduce el nombre del libro a buscar: ");
        var tituloEncontrado = teclado.nextLine().trim();
        tituloEncontrado = URLEncoder.encode(tituloEncontrado, StandardCharsets.UTF_8);
        var json = consumoAPI.obtenerDatos(URL_BASE + tituloEncontrado);
        System.out.println(json);
        Respuesta datos = conversor.obtenerDatos(json, Respuesta.class);
        return datos;    }


    private void mostrarLibros(Respuesta respuesta) {
        if (respuesta != null) {
            respuesta.resultado().stream()
                    .forEach(libro -> {
                                System.out.println("Título: " + libro.titulo());
                                System.out.println("Autores: ");
                                libro.autores().forEach(autor -> System.out.println(" - " + autor.nombre()));
                                System.out.println("Idiomas: " + String.join(", ", libro.idiomas()));
                                System.out.println("Descargas: " + libro.descargas());
                                System.out.println("---------------------------");
                            });
        } else {
            System.out.println("No se encontraron resultados");
        }
    }

}
