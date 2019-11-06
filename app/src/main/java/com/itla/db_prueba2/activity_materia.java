package com.itla.db_prueba2;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.itla.db_prueba2.entidad.Carrera;
import com.itla.db_prueba2.entidad.Materia;
import com.itla.db_prueba2.repositorio.MateriaRepositorio;
import com.itla.db_prueba2.repositorio.MateriaRepositorioDBImpl;

import android.os.Bundle;

public class activity_materia extends AppCompatActivity {

    MateriaRepositorio materiaRepositorio;
    Integer credit;
    EditText txtCredito, txtNombreMat;
    Button btnGuardarMat, btnCancelarMat;

    public void LimpiarEditText(ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if (v instanceof EditText) {
                ((EditText) v).setText("");
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        materiaRepositorio = new MateriaRepositorioDBImpl(this);
        txtCredito = findViewById(R.id.txtCreditos);
        txtNombreMat = findViewById(R.id.txtNombreMat);
        btnGuardarMat = findViewById(R.id.btnGuardarMat);
        btnCancelarMat = findViewById(R.id.btnCancelarMat);

        btnGuardarMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materia mat = new Materia(0, "", 0);

                mat.setNombre(txtNombreMat.getText().toString());
                credit = Integer.parseInt(txtCredito.getText().toString());
                mat.setCreditos(credit);

                materiaRepositorio.crear(mat);

                LimpiarEditText((ConstraintLayout) findViewById(R.id.acti_materia));

            }
        });

        btnCancelarMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
