package com.example.zafiro2.misrecetas.RecycledView;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RecetasViewHolder> {

    List<Receta> recetas;

    public static class RecetasViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView NombreReceta;
        TextView DescripcionReceta;


        RecetasViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            NombreReceta = (TextView)itemView.findViewById(R.id.txvNombreRecetaCV);
            DescripcionReceta = (TextView)itemView.findViewById(R.id.txvDescripcionRecetaCV);

        }

    }
    RVAdapter(List<Receta> recetas){
        this.recetas = recetas;
    }

    @Override
    public int getItemCount(){
        return recetas.size();
    }

    @Override
    public RecetasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adaptador_cardview_lista_recetas,viewGroup,false);
        RecetasViewHolder recetasViewHolder = new RecetasViewHolder(v);
        return recetasViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.RecetasViewHolder RecetasViewHolder, int i) {
        RecetasViewHolder.NombreReceta.setText(recetas.get(i).getNombre());
        RecetasViewHolder.DescripcionReceta.setText(recetas.get(i).getDescripcion());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
