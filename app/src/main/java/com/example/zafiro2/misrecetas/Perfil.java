package com.example.zafiro2.misrecetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;

public class Perfil extends AppCompatActivity {

    String usuario;
    String nombre;
    String apellido;
    String email;
    String CatFavorita;

    TextView txvUsuario;
    TextView txvCantidadRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        txvUsuario = findViewById(R.id.txvUsuarioPerfil);
        txvCantidadRecetas = findViewById(R.id.txvCantidadRecetas);
        txvUsuario.setText(usuario);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        int cantidadRecetas = databaseAccess.CantidadRecetas();
        txvCantidadRecetas.setText(Integer.toString(cantidadRecetas));


    }
}
