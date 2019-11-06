package com.itla.db_prueba2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.itla.db_prueba2.entidad.Carrera;

import java.util.List;


public class AdaptadorCarrera extends RecyclerView.Adapter<AdaptadorCarrera.MyViewHolder>  {
    private List<Carrera> carreraLista;

    public AdaptadorCarrera(List<Carrera> carreraLista){
        this.carreraLista = carreraLista;
    }

    List<Carrera> getCarreraLista(){ return this.carreraLista; }

    @NonNull
    @Override
    public AdaptadorCarrera.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_carrera,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarrera.MyViewHolder holder, int position) {
        holder.txtNombreCarrera.setText((carreraLista.get(position).getNombre()).toString());
        holder.cantidadMaterias.setText((carreraLista.get(position).getCant_materia()).toString()  + " materias");
        holder.cantidadCreditos.setText((carreraLista.get(position).getCreditos()).toString() + " creditos");
    }

    @Override
    public int getItemCount() {
        return carreraLista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreCarrera, cantidadMaterias, cantidadCreditos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreCarrera = (TextView) itemView.findViewById(R.id.nombreCarrera);
            cantidadMaterias = (TextView) itemView.findViewById(R.id.numMateria);
            cantidadCreditos = (TextView) itemView.findViewById(R.id.cantCreditos);
        }
    }
}
