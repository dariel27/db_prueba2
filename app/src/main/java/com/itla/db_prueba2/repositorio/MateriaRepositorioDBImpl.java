package com.itla.db_prueba2.repositorio;

import com.itla.db_prueba2.entidad.Materia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class MateriaRepositorioDBImpl implements MateriaRepositorio {

    private DbConexion dbConexion;
    private static final String TABLE = "materia";

    public MateriaRepositorioDBImpl(Context context){
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public void crear(Materia materia) {
        ContentValues coval = new ContentValues();

        coval.put("nombre",materia.getNombre());
        coval.put("creditos",materia.getCreditos());
        SQLiteDatabase db = dbConexion.getWritableDatabase();

        long id = db.insert(TABLE,null,coval);

        if(id <= 0){
            Log.i("MateriaRepositorio","No se pudo crear la materia");
        }else{
            Log.i("MateriaRepositorio","La materia se ha creado. ID = "+id);
        }

    }

    @Override
    public void actualizar(Materia materia) {

    }

    @Override
    public void eliminar(Materia materia) {

    }

    @Override
    public Materia buscar(int id) {
        return null;
    }

    @Override
    public List<Materia> MateriaDetallada() {
        List<Materia> materias = new ArrayList<>();

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.query(TABLE, null,null,null,null,null, null);

        while (cursor.moveToNext()){
            Materia mat = new Materia(0, "", 0);

            mat = new Materia(0,"",0);
            mat.setId_materia(cursor.getInt(cursor.getColumnIndex("id_materia")));
            mat.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            mat.setCreditos(cursor.getInt(cursor.getColumnIndex("creditos")));
            materias.add(mat);
        }
        cursor.close();
        db.close();
        return materias;
    }

    @Override
    public List<Materia> Materias() {
        List<Materia> materias = new ArrayList<>();

        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.query(TABLE, null,null,null,null,null, null);

        while (c.moveToNext()){
            Materia mat = new Materia(0,"",0);

            mat = new Materia(0,"",0);
            mat.setId_materia(c.getInt(c.getColumnIndex("id_materia")));
            mat.setNombre(c.getString(c.getColumnIndex("nombre")));
            mat.setCreditos(c.getInt(c.getColumnIndex("creditos")));
            materias.add(mat);
        }

        c.close();
        db.close();
        return materias;
    }
}
