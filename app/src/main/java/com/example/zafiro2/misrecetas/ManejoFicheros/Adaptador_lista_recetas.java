package com.example.zafiro2.misrecetas.ManejoFicheros;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Adaptador_lista_recetas extends RecyclerView.Adapter<Adaptador_lista_recetas.ViewHolder> {

    protected ArrayList<Receta> items;

    public int getCount(){
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Receta> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }


    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @NonNull
    @Override
    public Adaptador_lista_recetas.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adaptador_lista_recetas, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_lista_recetas.ViewHolder viewHolder, int i) {
        viewHolder.txvNombre.setText(items.get(i).getNombre());
        viewHolder.txvDescripcion.setText(items.get(i).getDescripcion());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txvNombre;
        private TextView txvDescripcion;
        public ViewHolder(View view) {
            super(view);
            txvNombre = view.findViewById(R.id.txvNombreReceta);
            txvDescripcion = view.findViewById(R.id.txvDescripcionReceta);

        }
    }
}
