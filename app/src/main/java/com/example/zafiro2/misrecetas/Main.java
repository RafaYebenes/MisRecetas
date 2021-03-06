package com.example.zafiro2.misrecetas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.zafiro2.misrecetas.BBDDRemota.Login;
import com.example.zafiro2.misrecetas.CreacionRecetas.CrearPDF;
import com.example.zafiro2.misrecetas.Mapas.BlankFragment;
import com.example.zafiro2.misrecetas.RecycledView.ListaRecycled;
import com.example.zafiro2.misrecetas.Objetos.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Main extends AppCompatActivity {


    usuario user;
    private GoogleMap mMap;
    FloatingActionButton fbaNuevaReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundleUser");
        user = (usuario) bundle.getSerializable("usuario");

        cargarObjetos();

        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("usuario",user);

        BlankFragment bFragment = new BlankFragment();

        bFragment.setArguments(bundle1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftrans = fm.beginTransaction();
        FragmentTransaction replace = ftrans.replace(R.id.fragmentMap, bFragment);
        replace.commit();
    }

    public void cargarObjetos(){
        fbaNuevaReceta = findViewById(R.id.fbaNuevaReceta);
        fbaNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), CrearPDF.class);
                startActivity(intent1);
            }
        });
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",user);
                intent2.putExtra("usuarioBundle",bundle);
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
