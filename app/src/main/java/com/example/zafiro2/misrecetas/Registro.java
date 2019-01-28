package com.example.zafiro2.misrecetas;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUserName = findViewById(R.id.edtNombreUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnGuardar = findViewById(R.id.btnGuardarRegistro);

        btnGuardar.setOnClickListener(mCorkyListener);

    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==findViewById(R.id.btnGuardarRegistro).getId()){
                ejecutarServicio("192.168.1.110:80/recetas/registro.php");
            }

        }
    };

    public void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Registro.this, "Exito en la operación", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registro.this, "Fallo en la operación", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario",edtUserName.getText().toString());
                parametros.put("password",edtPassword.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
