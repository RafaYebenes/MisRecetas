package com.example.zafiro2.misrecetas;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;
import com.example.zafiro2.misrecetas.BBDDRemota.Login;
import com.example.zafiro2.misrecetas.BBDDRemota.LoginRequest;
import com.example.zafiro2.misrecetas.BBDDRemota.obtenerPerfilRequest;
import com.example.zafiro2.misrecetas.Dialogos.DialogoFecha;
import com.example.zafiro2.misrecetas.Objetos.usuario;
import com.example.zafiro2.misrecetas.Objetos.usuario;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    String usuario;
    String nombre;
    String apellido;
    String email;
    String CatFavorita;
    usuario user;
    boolean editable = false;

    TextView txvNombrePerfil;
    TextView txvApellidosPerfil;
    TextView txvEmailPerfil;
    TextView txvCantidadRecetas;
    EditText edtFechaNacimiento;
    EditText edtTelefono;
    ImageButton imbPerfil;
    Button btnGuardaPerfil;

    StringRequest stringRequest;
    ProgressBar progreso;
    private static final  String Carpeta_Principal = "imagenesAPP/";
    private static final String Carpeta_Imagen = "perfil";
    private static final String DirectorioImagen = Carpeta_Principal+Carpeta_Imagen;
    File file;
    Bitmap bitmap;
    private String path;

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

        edtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoFecha dialogoFecha = new DialogoFecha();
                dialogoFecha.show(getSupportFragmentManager(),"Fecha");
            }
        });

        /* databaseAccess = DatabaseAccess.getInstace(this);
        int cantidadRecetas = databaseAccess.CantidadRecetas();
        txvCantidadRecetas.setText(Integer.toString(cantidadRecetas));*/

        imbPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarFoto();

            }
        });

        imbPerfil.setEnabled(false);

        int cont = 0;

        if(user.getFecha_nacimiento().equals("null")){
             edtFechaNacimiento.setText(" ");
             cont++;
        }
        else{
            edtFechaNacimiento.setText(user.getFecha_nacimiento());
        }

        if( user.getTelefono().equals("null")){
             edtTelefono.setText(" ");
             cont++;
        }
        else{
            edtTelefono.setText( user.getTelefono());
        }

        if(cont==2){
            Toast.makeText(this, "Tu perfil esta incompleto, relleña los campos que te faltan", Toast.LENGTH_SHORT).show();
            cont =0;
        }
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

        if(editable==false) {
            btnGuardaPerfil.setVisibility(View.VISIBLE);
            edtFechaNacimiento.setEnabled(true);
            edtTelefono.setEnabled(true);
            imbPerfil.setEnabled(true);
            editable = true;
        }else{
            btnGuardaPerfil.setVisibility(View.INVISIBLE);
            edtFechaNacimiento.setEnabled(false);
            edtTelefono.setEnabled(false);
            imbPerfil.setEnabled(false);
            editable = false;
        }
    }

    public void actualizarPerfil(){


        user.setTelefono(edtTelefono.getText().toString());
        user.setFecha_nacimiento(edtFechaNacimiento.getText().toString());
        String imagen = imagenToString(bitmap);
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
        obtenerPerfilRequest l = new obtenerPerfilRequest(user.getNombreUsuario(),user.getTelefono(),user.getFecha_nacimiento(),imagen, respuesta);
        RequestQueue cola = Volley.newRequestQueue(Perfil.this);
        cola.add(l);
    }



    public String imagenToString(Bitmap bitmap){

        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,90,array);
        byte[] imagenByte = array.toByteArray();
        String foto = Base64.encodeToString(imagenByte,Base64.DEFAULT);
        return foto;

    }

    public void cargarFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Imagen"),10);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10){
            if(data!=null) {
                Uri path = data.getData();
                imbPerfil.setImageURI(path);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), path);
                    imbPerfil.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                return;
            }
        }
    }

}
