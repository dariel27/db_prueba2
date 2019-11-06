package com.itla.db_prueba2.repositorio;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbConexion extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String NAME_DB = "db_prueba";

    public DbConexion(@Nullable Context context) {
        super(context, NAME_DB,null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE \"estudiante\" (\"id_estudiante\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"nombre\" TEXT NOT NULL,\"carrera\"\tINTEGER NOT NULL,\"matricula\"\tTEXT NOT NULL, FOREIGN KEY(\"carrera\") REFERENCES \"carrera\"(\"id_carrera\"));");
        // TABLA DE CARRERA
        db.execSQL("CREATE TABLE \"carrera\" (\"id_carrera\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \"nombre\"\tTEXT NOT NULL);");
        // TABLA DE MATERIA
        db.execSQL("CREATE TABLE \"materia\" (\"id_materia\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"nombre\" TEXT NOT NULL, \"creditos\" INTEGER NOT NULL);");
        // TABLA CARREAR_MATERIA
        db.execSQL("CREATE TABLE \"carrera_materia\" (\"id_carrera\"\tINTEGER NOT NULL,\"id_materia\"\tINTEGER NOT NULL,FOREIGN KEY(\"id_carrera\") REFERENCES \"carrera_materia\"(\"id_carrera\"),FOREIGN KEY(\"id_materia\") REFERENCES \"materia\"(\"id_materia\")\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
