package com.example.zafiro2.misrecetas.BBDDRemota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.zafiro2.misrecetas.Main;
import com.example.zafiro2.misrecetas.R;
import com.example.zafiro2.misrecetas.Objetos.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    public Button btnLogin;
    public Button btnSingIn;
    public EditText edtNombreUsuario;
    public EditText edtPassword;
    String usuario;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNombreUsuario = findViewById(R.id.edtLoginUsuario);
        edtPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(mCorkyListener);
        btnSingIn = findViewById(R.id.btnSingIn);
        btnSingIn.setOnClickListener(mCorkyListener);
        edtNombreUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNombreUsuario.setText(" ");
                edtPassword.setText("");
            }
        });

    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==findViewById(R.id.btnLogin).getId()){
                 usuario = edtNombreUsuario.getText().toString();
                 password = edtPassword.getText().toString();
                if(ComprobarCampos()) {
                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonRespuesta = new JSONObject(response);
                                boolean ok = jsonRespuesta.getBoolean("success");
                                if (ok == true) {
                                    usuario newUser = new usuario();
                                    newUser.setNombreUsuario(jsonRespuesta.getString("usuario"));
                                    newUser.setNombre(jsonRespuesta.getString("nombre"));
                                    newUser.setApellido(jsonRespuesta.getString("apellidos"));
                                    newUser.setEmail(jsonRespuesta.getString("email"));

                                    newUser.setFecha_nacimiento(jsonRespuesta.getString("fecha_nacimiento"));
                                    newUser.setTelefono(jsonRespuesta.getString("telefono"));

                                    Toast.makeText(Login.this, "Conectado con el usuario: " + newUser.getNombreUsuario()+newUser.getApellido(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Main.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("usuario", newUser);
                                    intent.putExtra("bundleUser",bundle);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.getMessage();
                            }
                        }

                    };
                    LoginRequest l = new LoginRequest(usuario, password, respuesta);
                    RequestQueue cola = Volley.newRequestQueue(Login.this);
                    cola.add(l);
                }
            }
            if(v.getId()==findViewById(R.id.btnSingIn).getId()){
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);

            }
        }
    };

    public boolean ComprobarCampos(){
        int cont =0;
        if(usuario.length()==0){
            cont++;
            Toast.makeText(this, "Debes introducir tu nombre de usuario", Toast.LENGTH_SHORT).show();
        }
        if(password.length()==0){
            cont++;
            Toast.makeText(this, "Debes introducir tu contraseña", Toast.LENGTH_SHORT).show();
        }
        if(cont==2){
            return false;
        }
        else{
            return true;
        }
    }

}
