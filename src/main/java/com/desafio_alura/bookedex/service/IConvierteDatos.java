package com.desafio_alura.bookedex.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
