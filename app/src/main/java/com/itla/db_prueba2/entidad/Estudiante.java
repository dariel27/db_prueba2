package com.itla.db_prueba2.entidad;

public class Estudiante {

    private Integer id_estudiante;
    private String nombre;
    private Integer carrera;
    private String maricula;

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCarrera() {
        return carrera;
    }

    public void setCarrera(Integer carrera) {
        this.carrera = carrera;
    }

    public String getMaricula() {
        return maricula;
    }

    public void setMaricula(String maricula) {
        this.maricula = maricula;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
