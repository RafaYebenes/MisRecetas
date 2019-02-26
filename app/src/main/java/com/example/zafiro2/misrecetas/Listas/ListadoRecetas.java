package com.example.zafiro2.misrecetas.Listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;
import com.example.zafiro2.misrecetas.CreacionRecetas.VisorPDF;
import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;

import java.util.ArrayList;

public class ListadoRecetas extends AppCompatActivity {
    TextView txvIntro;
    ListView lvRecetas;
    Toolbar tbCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_recetas);
        CargarObjetos();
        lvRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Receta receta = (Receta) lvRecetas.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),VisorPDF.class);
                intent.putExtra("enlace",receta.getArchivo());
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_con_categorias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mEntrantes:
                selecionarPorCategoria("Entrantes");
                break;
            case R.id.mPescado:
                selecionarPorCategoria("Pescados");
                break;
            case  R.id.mCarne:
                selecionarPorCategoria("Carnes");
                break;
            case R.id.mVerduras:
                selecionarPorCategoria("Verduras");
                break;
            case R.id.mEnsaladas:
                selecionarPorCategoria("Ensaladas");
                break;
            case R.id.mPostres:
                selecionarPorCategoria("Postres");
                break;
        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }


    public void selecionarPorCategoria(String Categoria){
        tbCategoria.setTitle(Categoria);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        ArrayList<Receta> arrayRecetas = databaseAccess.getTodosByCategoria(Categoria);
        lvRecetas.setVisibility(View.VISIBLE);
        txvIntro.setVisibility(View.INVISIBLE);

        Adaptador_lista_recetas adaptador_lista_recetas = new Adaptador_lista_recetas(this,arrayRecetas);
        lvRecetas.setAdapter(adaptador_lista_recetas);
    }

    public void CargarObjetos(){
         txvIntro = findViewById(R.id.txvIntro);
         lvRecetas = findViewById(R.id.lvRecetas);
         tbCategoria = findViewById(R.id.tbCategoria);
         tbCategoria.setTitle("Categorias");
    }
}

/*
metodos del onclick listview
);
 */