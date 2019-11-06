package com.itla.db_prueba2.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.db_prueba2.entidad.Carrera;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositorioDBImpl implements CarreraRepositorio{

    private DbConexion DbConexion;
    private static final String TABLE = "carrera";
    private static final String TABLE_MATERIA_CARRERA = "carrera_materia";

    public CarreraRepositorioDBImpl(Context context){
        this.DbConexion = new DbConexion(context);
    }

    @Override
    public int crear(Carrera carrera) {

        ContentValues conva = new ContentValues();

        conva.put("nombre", carrera.getNombre());
        SQLiteDatabase db = DbConexion.getWritableDatabase();

        long id = db.insert(TABLE, null, conva);

        if (id > 0) {
            return (int) id;
        } else {
            return 0;
        }

    }

    @Override
    public long materia_carrera(int id_carrera, List<Integer> id_materia) {
        ContentValues conva = new ContentValues();

        SQLiteDatabase db = DbConexion.getReadableDatabase();

        long id = 0;

        for(int i=0; i < id_materia.size();i++){
            conva.put("id_carrera",id_carrera);
            conva.put("id_materia", id_materia.get(i));
            id = db.insert(TABLE_MATERIA_CARRERA,null,conva);
        }

        if(id!=0)
            return id;
        else
            return 0;
    }

    @Override
    public void actualizar(Carrera carrera) {

    }

    @Override
    public void eliminar(Carrera carrera) {

    }

    @Override
    public Carrera buscar(int id) {
        return null;
    }

    @Override
    public List<Carrera> CarreraDetallada() {

        List<Carrera> carreras = new ArrayList<>();

        SQLiteDatabase db = DbConexion.getReadableDatabase();

        //Cursor cursor = db.query(TABLE + "INNER JOIN carrera_materia cm ON c.idcarrera = cm.idcarrera INNER JOIN materia m ON m.idmateria = cm.idmateria",
        // new String[] {"c.nombre","count(m.idmateria) as cantidad_materia", "sum(m.creditos) AS creditos"},null,null,
        //"c.idcarrera",null,null,null);

        Cursor cursor = db.rawQuery("SELECT c.nombre as nombre, count(m.id_materia) AS cantidad_materia, sum(m.creditos) AS creditos\n" +
                "FROM carrera c  INNER JOIN carrera_materia cm ON c.id_carrera = cm.id_carrera\n" +
                "INNER JOIN materia m ON m.id_materia = cm.id_materia\n" +
                "GROUP BY c.id_carrera",null);

        Carrera carr;

        while (cursor.moveToNext()){

            String carrera = cursor.getString(cursor.getColumnIndex("nombre"));
            Integer cant_materia = cursor.getInt(cursor.getColumnIndex("cantidad_materia"));
            Integer creditos = cursor.getInt(cursor.getColumnIndex("creditos"));

            carr = new Carrera();
            carr.setNombre(carrera);
            carr.setCant_materia(cant_materia);
            carr.setCreditos(creditos);
            carreras.add(carr);
        }
        cursor.close();
        db.close();
        return carreras;
    }

    @Override
    public List<String> listaCarrera() {

        List<String> carreras = new ArrayList<>();

        SQLiteDatabase db = DbConexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id,nombre FROM carrera",null);
        Carrera carr;
        while (cursor.moveToNext()){
//            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
//            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            carreras.add(cursor.getString(0));
            carreras.add(cursor.getString(1));
        }

        cursor.close();
        db.close();
        return carreras;

    }
}
