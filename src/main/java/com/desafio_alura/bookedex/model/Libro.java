package com.desafio_alura.bookedex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    @JsonAlias("id") private Long id;
    @JsonAlias("title") private String titulo;
    @JsonAlias("authors") private  Autor autor;
    @JsonAlias("languages") private String idioma;
    @JsonAlias("download_count") private Integer descargas;

    public Libro(Libro libro) {
        this.id = libro.id;
        this.titulo = libro.titulo;
        this.autor = libro.autor;
        this.idioma = libro.idioma;
        this.descargas = libro.descargas;

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

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }


}


