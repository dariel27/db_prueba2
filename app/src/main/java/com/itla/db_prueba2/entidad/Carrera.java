package com.itla.db_prueba2.entidad;

public class Carrera {

    private Integer id_carrera;
    private String nombre;
    private Integer cant_materia;

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    private Integer creditos;

   /* public Carrera(Integer id_materia, String nombre, Integer creditos) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.creditos = creditos;
    }*/

    public Integer getCant_materia() {
        return cant_materia;
    }

    public void setCant_materia(Integer cant_materia) {
        this.cant_materia = cant_materia;
    }



    public Integer getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Integer id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
