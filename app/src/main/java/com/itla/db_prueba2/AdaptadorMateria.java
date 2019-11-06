package com.itla.db_prueba2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.db_prueba2.entidad.Materia;

import java.util.List;

public class AdaptadorMateria extends RecyclerView.Adapter<AdaptadorMateria.ViewHolder> {

    private List<Materia> listaMateria;

    public AdaptadorMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public List<Materia> getListaMateria(){
        return  listaMateria;
    }

    public void setListaDeMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    @NonNull
    @Override
    public AdaptadorMateria.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_materia_row,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMateria.ViewHolder holder, int position) {
        holder.txtNombre.setText((listaMateria.get(position).getNombre().toString()));
    }

    @Override
    public int getItemCount() {
        return listaMateria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //String
        TextView txtNombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.nomMateria);
        }
    }
}
