package com.example.zafiro2.misrecetas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;
import com.example.zafiro2.misrecetas.BBDDRemota.Login;
import com.example.zafiro2.misrecetas.BBDDRemota.LoginRequest;
import com.example.zafiro2.misrecetas.BBDDRemota.obtenerPerfilRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Perfil extends AppCompatActivity {

    String usuario;
    String nombre;
    String apellido;
    String email;
    String CatFavorita;
    String[] arrayOpciones = new String[]{"Camara", "Galeria", "Salir"};

    TextView txvUsuario;
    TextView txvNombrePerfil;
    TextView txvApellidosPerfil;
    TextView txvEmailPerfil;
    TextView txvCantidadRecetas;
    EditText edtFechaNacimiento;
    ImageButton imbPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        cargarObjetos();

        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonRespuesta = new JSONObject(response);
                    boolean ok = jsonRespuesta.getBoolean("success");
                    if (ok == true) {
                        Toast.makeText(Perfil.this, "todo ok", Toast.LENGTH_SHORT).show();
                        String nombre = jsonRespuesta.getString("nombre");
                        String apellidos = jsonRespuesta.getString("apellidos");
                        String email = jsonRespuesta.getString("email");

                        txvNombrePerfil.setText(nombre);
                        txvApellidosPerfil.setText(apellidos);
                        txvEmailPerfil.setText(email);
                    } else {
                        Toast.makeText(Perfil.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }

        };
        obtenerPerfilRequest l = new obtenerPerfilRequest(usuario, respuesta);
        RequestQueue cola = Volley.newRequestQueue(Perfil.this);
        cola.add(l);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
       // int cantidadRecetas = databaseAccess.CantidadRecetas();
        //txvCantidadRecetas.setText(Integer.toString(cantidadRecetas));


    }


    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplicationContext());
        builder.setTitle("Elige una opción").setItems(arrayOpciones, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Toast.makeText(Perfil.this, arrayOpciones[which], Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }

    public void cargarObjetos(){

        txvCantidadRecetas = findViewById(R.id.txvCantidadRecetas);
        edtFechaNacimiento = findViewById(R.id.eddFechaNacimientoPerfil);
        txvNombrePerfil = findViewById(R.id.txvNombrePerfil);
        txvApellidosPerfil = findViewById(R.id.txvApellidosPerfil);
        txvEmailPerfil = findViewById(R.id.txvEmailPerfil);
        edtFechaNacimiento.setEnabled(false);
        imbPerfil = findViewById(R.id.imvPerfil);
        imbPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
            }
        });
    }


}
