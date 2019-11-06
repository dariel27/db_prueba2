package com.itla.db_prueba2.repositorio;
import  com.itla.db_prueba2.entidad.Carrera;
import com.itla.db_prueba2.entidad.Materia;

import java.util.List;

public interface MateriaRepositorio {

    void crear(Materia materia);
    void actualizar(Materia materia);
    void eliminar(Materia materia);
    Materia buscar(int id);

    List<Materia> MateriaDetallada();

    List<Materia> Materias();

}
