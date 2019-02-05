package com.example.zafiro2.misrecetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zafiro2.misrecetas.ManejoFicheros.ListadoRecetas;
import com.example.zafiro2.misrecetas.RecycledView.ListaRecycled;

public class Main extends AppCompatActivity {

    public Button btnRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecetas = findViewById(R.id.btnRecetas);
        btnRecetas.setOnClickListener(mCorkyListener);
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==findViewById(R.id.btnRecetas).getId()){
                Intent intent = new Intent(getApplicationContext(), ListaRecycled.class);
                startActivity(intent);
            }
        }

    };
}
