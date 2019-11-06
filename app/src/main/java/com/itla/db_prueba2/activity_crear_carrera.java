package com.itla.db_prueba2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.db_prueba2.entidad.Carrera;
import com.itla.db_prueba2.entidad.Materia;
import com.itla.db_prueba2.repositorio.CarreraRepositorio;
import com.itla.db_prueba2.repositorio.CarreraRepositorioDBImpl;
import com.itla.db_prueba2.repositorio.MateriaRepositorio;
import com.itla.db_prueba2.repositorio.MateriaRepositorioDBImpl;

import java.util.ArrayList;
import java.util.List;


public class activity_crear_carrera extends AppCompatActivity {

    MateriaRepositorio materiaRepositorio;
    CarreraRepositorio carreraRepositorio;

    Toast msj;
    ArrayAdapter<String> adapter;
    Spinner spiMateria;
    List<Materia> materiaList = new ArrayList<>();
    Materia materiaEntity;
    Materia mtpos;
    Button btnCrearMateria;
    List<Integer> idmat;

    EditText etNombreCarrera;

    Button btnGuardar,btnCancelar,btnNuevaMateria,btnAgregarMateria;

    RecyclerView recycleViewMaterias;

    private void setMensaje(Toast msj, String mensaje){
        msj = Toast.makeText(getApplicationContext(),mensaje , Toast.LENGTH_SHORT);
        msj.setGravity(Gravity.CENTER, 0, 0);
        msj.show();

    }

    private void cargarSpinner(Spinner spiMat){

        materiaRepositorio = new MateriaRepositorioDBImpl(getApplicationContext());

        Materia mt1 = new Materia(0,"Seleccione una materia",0);
        materiaList = materiaRepositorio.Materias();

        if(materiaList == null )
            Log.i("Es: ", "null");

        if (materiaList.size() > 0) {
            Log.i("Pasa: ", "entro");
        } else {
            Log.i("Pasa: ", "no entro");
        }

        materiaList.add(mt1);

        ArrayAdapter<Materia> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,materiaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiMat.setAdapter(adapter);
        spiMat.setSelection(materiaList.size()-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carrera);


        idmat = new ArrayList<Integer>();
        carreraRepositorio = new CarreraRepositorioDBImpl(this);

        btnAgregarMateria = (Button) findViewById(R.id.btnAgregar2);
        btnCrearMateria = (Button) findViewById(R.id.btnCrearMateria);
        btnCancelar = (Button) findViewById(R.id.btnCancelarCarr);
        btnGuardar = (Button) findViewById(R.id.btnGuardarCarr);
        etNombreCarrera = (EditText) findViewById(R.id.txtNombreCarr);

        Spinner spi = (Spinner) findViewById(R.id.spMateria);
        cargarSpinner(spi);

        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                materiaEntity = (Materia) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recycleViewMaterias = (RecyclerView) findViewById(R.id.recyclerViewMaterias);
        recycleViewMaterias.setLayoutManager(new LinearLayoutManager(this));

        final AdaptadorMateria adapter = new AdaptadorMateria(new ArrayList<Materia>());
        recycleViewMaterias.setAdapter(adapter);

        btnAgregarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materiaEntity == null || materiaEntity.getId_materia() == 0){
                    setMensaje(msj,"Seleccione una materia");
                    return;
                }else{
                    // Log.i("Codigo: ", ((Materia) ((Spinner)findViewById(R.id.spinnerMateria)).getSelectedItem()).getId().toString() );
                    mtpos = (Materia) ((Spinner)findViewById(R.id.spMateria)).getSelectedItem();
                    idmat.add(mtpos.getId_materia());
                    adapter.getListaMateria().add(materiaEntity);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombreCarrera.getText().toString()==""){
                    msj = Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de la carrera", Toast.LENGTH_SHORT);
                    msj.setGravity(Gravity.CENTER, 0, 0);
                    msj.show();
                }else if((recycleViewMaterias.getChildCount()<=0)){
                    msj = Toast.makeText(getApplicationContext(), "Debe ingresar materias a la carrera", Toast.LENGTH_SHORT);
                    msj.setGravity(Gravity.CENTER, 0, 0);
                    msj.show();
                }else{
                    Carrera carr = new Carrera();
                    carr.setNombre(etNombreCarrera.getText().toString());

                    int idcarrera = carreraRepositorio.crear(carr);

                    setMensaje(msj,Integer.toString(idcarrera));

                    if(idcarrera>0){
                        carreraRepositorio = new CarreraRepositorioDBImpl(getApplicationContext());
                        long resultado = carreraRepositorio.materia_carrera(idcarrera, (List<Integer>) idmat);

                        if(resultado != 0){
                            setMensaje(msj,"Se ha creado la carrera exitosamente id: " + String.valueOf(resultado));
                        }else{
                            setMensaje(msj,"Hubo problemas al crear la carrera");
                        }
                    }
                }
            }
        });

        btnCrearMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vmat = new Intent(getApplicationContext(), activity_materia.class);
                startActivityForResult(vmat,0);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarSpinner((Spinner)findViewById(R.id.spMateria));
    }
}
