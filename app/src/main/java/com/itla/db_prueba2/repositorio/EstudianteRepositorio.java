package com.itla.db_prueba2.repositorio;

import  com.itla.db_prueba2.entidad.Estudiante;
import java.util.List;

public interface EstudianteRepositorio {

    void crear(Estudiante estudiante);
    void actualizar(Estudiante estudiante);
    void eliminar(Estudiante estudiante);
    Estudiante buscar(int id);
    List<Estudiante> EstudianteDetallada();
    List<String> listaEstudiante();
}
