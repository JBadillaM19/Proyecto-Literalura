package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Double numeroDescargas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idioma = !datosLibros.idiomas().isEmpty() ? datosLibros.idiomas().getFirst() : "Desconocido";
        this.numeroDescargas = datosLibros.numeroDescargas();

        if(!datosLibros.autor().isEmpty()) {
            this.autor = new Autor(datosLibros.autor().getFirst());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n----- LIBRO -----" +
                "\nTitulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNombre() : "Desconocido") +
                "\nIdioma: " + idioma +
                "\nNumero de descargas: " + numeroDescargas +
                "\n-----------------";
    }
}
