package com.itla.db_prueba2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itla.db_prueba2.entidad.Carrera;
import com.itla.db_prueba2.repositorio.CarreraRepositorio;
import com.itla.db_prueba2.repositorio.CarreraRepositorioDBImpl;

import java.util.List;

public class activity_inicio_carrera extends AppCompatActivity {

    CarreraRepositorio carreraRepositorio;
    Toast msj;
    Button btnNuevaCarrera;
    RecyclerView recyclerViewCarrera;
    AdaptadorCarrera adaptadorCarrera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_carrera);
        btnNuevaCarrera = findViewById(R.id.btnNuevaCarrera);

        carreraRepositorio = new CarreraRepositorioDBImpl(getApplicationContext());

        List<Carrera> carreras = carreraRepositorio.CarreraDetallada();

        if (carreras.size() < 0) {
            msj = Toast.makeText(getApplicationContext(), "NO EXISTEN DATOS", Toast.LENGTH_SHORT);
            msj.setGravity(Gravity.CENTER, 0, 0);
            msj.show();
            setDataReport(carreras);
        } else {
            Log.i("Cantidad: ", String.valueOf(carreras.size()));
            setDataReport(carreras);
        }

        btnNuevaCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarrera = new Intent(v.getContext(), activity_crear_carrera.class);
                startActivityForResult(intentCarrera,0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carreraRepositorio = new CarreraRepositorioDBImpl(getApplicationContext());

        List<Carrera> carreras = carreraRepositorio.CarreraDetallada();

        setDataReport(carreras);
    }

    void setDataReport(List<Carrera> carreras){

        recyclerViewCarrera = (RecyclerView) findViewById(R.id.recycleCarreras);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCarrera.setLayoutManager(layoutManager);

        adaptadorCarrera = new AdaptadorCarrera(carreras);
        recyclerViewCarrera.setAdapter(adaptadorCarrera);
    }
}