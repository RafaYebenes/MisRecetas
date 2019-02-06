package com.example.zafiro2.misrecetas.BBDDRemota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zafiro2.misrecetas.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    EditText edtNombre;
    EditText edtApellidos;
    EditText edtEmail;
    EditText edtFecha;
    Button btnGuardar;

    String usuario;
    String password;
    String nombre;
    String Apellidos;
    String email;
    String fecha_nacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarObjetos();



    }

    public void cargarObjetos(){
        edtUserName = findViewById(R.id.edtNombreUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        edtNombre = findViewById(R.id.edtNombreReg);
        edtApellidos = findViewById(R.id.edtApellido);
        edtEmail = findViewById(R.id.edtEmail);
        btnGuardar = findViewById(R.id.btnGuardarRegistro);
        btnGuardar.setOnClickListener(mCorkyListener);

    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==findViewById(R.id.btnGuardarRegistro).getId()){
               // ejecutarServicio("https://subsidized-cargoes.000webhostapp.com/registro.php");
               getValores();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok==true){
                                Toast.makeText(Registro.this, "Conseguido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Registro.this, "Fallo al resitrar", Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };
                RegistroRequest r = new RegistroRequest(usuario,password,email,nombre,Apellidos,respuesta);
                RequestQueue cola = Volley.newRequestQueue(Registro.this);
                cola.add(r);
            }

        }
    };


    public void getValores(){
         usuario = edtUserName.getText().toString();
         password = edtPassword.getText().toString();
         nombre = edtNombre.getText().toString();
         Apellidos = edtApellidos.getText().toString();


    }

    public boolean comprobarLonguitudCampos(){
        int cont = 0;
        if(usuario.length()>3&&usuario.length()<20){
            cont++;
        }else{
            Toast.makeText(this, "Nombre usuario: Caracteres Minimos 3, Maximos 20", Toast.LENGTH_SHORT).show();
        }
        if(password.length()>3&&password.length()>20){
            cont++;
        }else{
            Toast.makeText(this, "Contraseña: Caracteres Minimos 3, Maximos 20", Toast.LENGTH_SHORT).show();
        }
        if(nombre.length()>3&&nombre.length()<20){
            cont++;
        }else{
            Toast.makeText(this, "Nombre usuario: Caracteres Minimos 3, Maximos 20", Toast.LENGTH_SHORT).show();
        }
        if(cont==3){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean comprobarCamposVacios(){

        if(usuario.length()==0){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
        }
        if(password.length()==0){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
        }
        if(nombre.length()==0){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
        }
        if(Apellidos.length()==0){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
        }
        if(email.length()==0) {
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
        }
       // if()
    }


}
