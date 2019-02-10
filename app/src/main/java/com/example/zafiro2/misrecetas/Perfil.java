package com.example.zafiro2.misrecetas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.zafiro2.misrecetas.Objetos.usuario;
import com.example.zafiro2.misrecetas.Objetos.usuario;
import org.json.JSONException;
import org.json.JSONObject;

public class Perfil extends AppCompatActivity {

    String usuario;
    String nombre;
    String apellido;
    String email;
    String CatFavorita;
    usuario user;


    TextView txvNombrePerfil;
    TextView txvApellidosPerfil;
    TextView txvEmailPerfil;
    TextView txvCantidadRecetas;
    EditText edtFechaNacimiento;
    EditText edtTelefono;
    ImageButton imbPerfil;
    Button btnGuardaPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("usuarioBundle");
        user = (usuario) bundle.getSerializable("usuario");
        cargarObjetos();






    }




    public void cargarObjetos(){

        txvCantidadRecetas = findViewById(R.id.txvCantidadRecetas);
        edtFechaNacimiento = findViewById(R.id.eddFechaNacimientoPerfil);

        txvNombrePerfil = findViewById(R.id.txvNombrePerfil);
        txvNombrePerfil.setText(user.getNombre());

        txvApellidosPerfil = findViewById(R.id.txvApellidosPerfil);
        txvApellidosPerfil.setText(user.getApellido());

        txvEmailPerfil = findViewById(R.id.txvEmailPerfil);
        txvEmailPerfil.setText(user.getEmail());

        edtTelefono = findViewById(R.id.edtTelefono);
        edtTelefono.setEnabled(false);

        edtFechaNacimiento.setEnabled(false);
        imbPerfil = findViewById(R.id.imvPerfil);

        btnGuardaPerfil = findViewById(R.id.btnGuardarPerfil);
        btnGuardaPerfil.setVisibility(View.INVISIBLE);
        btnGuardaPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarPerfil();
                edtFechaNacimiento.setEnabled(false);
                edtTelefono.setEnabled(false);
            }
        });


        /* databaseAccess = DatabaseAccess.getInstace(this);
        int cantidadRecetas = databaseAccess.CantidadRecetas();
        txvCantidadRecetas.setText(Integer.toString(cantidadRecetas));*/
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mEdtitPerfil:
                cambiarEditablesTodosTextView();
                break;

        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }


    public void cambiarEditablesTodosTextView(){

        btnGuardaPerfil.setVisibility(View.VISIBLE);
        edtFechaNacimiento.setEnabled(true);
        edtTelefono.setEnabled(true);
    }

    public void actualizarPerfil(){


        user.setTelefono(Integer.parseInt(edtTelefono.getText().toString()));
        user.setFecha_nacimiento(edtFechaNacimiento.getText().toString());

        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonRespuesta = new JSONObject(response);
                    boolean ok = jsonRespuesta.getBoolean("success");
                    if (ok == true) {
                        Toast.makeText(Perfil.this, "Perfil Actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Perfil.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }

        };
        obtenerPerfilRequest l = new obtenerPerfilRequest(user.getNombreUsuario(),user.getTelefono(),user.getFecha_nacimiento(), respuesta);
        RequestQueue cola = Volley.newRequestQueue(Perfil.this);
        cola.add(l);
    }

}
