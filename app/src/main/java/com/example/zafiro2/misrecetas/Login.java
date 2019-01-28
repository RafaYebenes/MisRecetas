package com.example.zafiro2.misrecetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    public Button btnLogin;
    public Button btnSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(mCorkyListener);
        btnSingIn = findViewById(R.id.btnSingIn);
        btnSingIn.setOnClickListener(mCorkyListener);
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==findViewById(R.id.btnLogin).getId()){
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
            if(v.getId()==findViewById(R.id.btnSingIn).getId()){
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        }
    };
}
