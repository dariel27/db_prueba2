package com.itla.db_prueba2.repositorio;
import  com.itla.db_prueba2.entidad.Carrera;
import java.util.List;

public interface CarreraRepositorio {

    int crear(Carrera carrera);
    long materia_carrera(int id_carrera, List<Integer> id_materia);
    void actualizar(Carrera carrera);
    void eliminar(Carrera carrera);
    Carrera buscar(int id);
    List<Carrera> CarreraDetallada();
    List<String> listaCarrera();
}