package com.example.zafiro2.misrecetas.ManejoFicheros;

import android.app.Activity;
import android.content.Context;
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

public class Adaptador_lista_recetas extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Receta> items;

    public Adaptador_lista_recetas(Activity activity, ArrayList<Receta> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
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

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inf.inflate(R.layout.adaptador_lista_recetas, null);


        }

        Receta dir = items.get(position);

        //enlazar cada elemento de tu layout a cada atributo de la clase
        TextView txvNombreReceta = v.findViewById(R.id.txvNombreReceta);



        txvNombreReceta.setText(dir.getNombre());


       // TextView txvCategoriaReceta = v.findViewById(R.id.txvCategoriaReceta);
        //txvCategoriaReceta.setText(dir.getCategoria());



        TextView txvDescripcionReceta = v.findViewById(R.id.txvDescripcionReceta);
        txvDescripcionReceta.setText(dir.getDescripcion());


        return v;
    }
}
