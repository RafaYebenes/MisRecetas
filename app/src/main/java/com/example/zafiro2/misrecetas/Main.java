package com.example.zafiro2.misrecetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zafiro2.misrecetas.BBDDRemota.Login;
import com.example.zafiro2.misrecetas.ManejoFicheros.ListadoRecetas;
import com.example.zafiro2.misrecetas.RecycledView.ListaRecycled;

public class Main extends AppCompatActivity {

    String usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        usuario = intent.getStringExtra("usuario");



    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_principal, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mpRecetas:
                Intent intent = new Intent(getApplicationContext(), ListaRecycled.class);
                startActivity(intent);
                break;
            case R.id.mpPerfil:
                Intent intent2 = new Intent(getApplicationContext(), Perfil.class);
                intent2.putExtra("usuario",usuario);
                startActivity(intent2);
                break;
            case  R.id.mpCerrarSesion:
                Intent intent1 = new Intent(getApplicationContext(),Login.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.mpConocenos:

                break;

        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }
}
