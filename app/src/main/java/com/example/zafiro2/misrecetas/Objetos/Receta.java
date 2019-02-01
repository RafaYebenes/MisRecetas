package com.example.zafiro2.misrecetas.Objetos;

import java.util.List;

public class Receta {
    int id;
    String Nombre;
    String Categoria;
    String Descripcion;
    String Archivo;


    public Receta(int id, String nombre, String categoria, String descripcion, String archivo) {
        this.id = id;
        Nombre = nombre;
        Categoria = categoria;
        Descripcion = descripcion;
        Archivo = archivo;
    }

    public Receta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getArchivo() {
        return Archivo;
    }

    public void setArchivo(String archivo) {
        Archivo = archivo;
    }
}
