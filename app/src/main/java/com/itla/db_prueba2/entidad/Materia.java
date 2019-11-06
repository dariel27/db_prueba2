package com.itla.db_prueba2.entidad;

public class Materia {

    private Integer id_materia;
    private String nombre;
    private Integer creditos;

    public Materia(Integer id_materia, String nombre, Integer creditos) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
}
