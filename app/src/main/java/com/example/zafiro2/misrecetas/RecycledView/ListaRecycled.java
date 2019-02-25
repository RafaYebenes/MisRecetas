package com.example.zafiro2.misrecetas.RecycledView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;
import com.example.zafiro2.misrecetas.ManejoFicheros.Adaptador_lista_recetas;
import com.example.zafiro2.misrecetas.ManejoFicheros.VisorPDF;
import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess.*;

public class ListaRecycled extends AppCompatActivity {
    Toolbar tbCatRecy;
    RVAdapter rvAdapter;
    RecyclerView rv;
    ArrayList<Receta> arrayRecetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycled);
         tbCatRecy = findViewById(R.id.tbCatRecy);
         tbCatRecy.setTitle("Categorias");
         rv= (RecyclerView)findViewById(R.id.rv);
         rv.setHasFixedSize(true);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        arrayRecetas= databaseAccess.getTodasLasRecetas();

        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        rv.setLayoutManager(llm);


        rvAdapter = new RVAdapter(arrayRecetas);
        rv.setAdapter(rvAdapter);




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
        tbCatRecy.setTitle(Categoria);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        arrayRecetas= databaseAccess.getTodosByCategoria(Categoria);

        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        rv.setLayoutManager(llm);


       rvAdapter = new RVAdapter(arrayRecetas);
       rv.setAdapter(rvAdapter);

        rvAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Receta receta = (Receta) arrayRecetas.get(rv.getChildAdapterPosition(v));
                Intent intent = new Intent(getApplicationContext(),VisorPDF.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("receta",receta);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

    }
}
