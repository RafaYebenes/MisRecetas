package com.example.zafiro2.misrecetas.Objetos;

import java.io.Serializable;

public class usuario implements Serializable {

    String nombreUsuario;
    String nombre;
    String apellido;
    String email;
    String fecha_nacimiento;
    String latitud, longitud;
    String telefono;

    public usuario() {
    }

    public usuario(String nombreUsuarioç, String nombre, String apellido, String email) {
        this.nombreUsuario = nombreUsuarioç;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuarioç) {
        this.nombreUsuario = nombreUsuarioç;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
