package com.example.zafiro2.misrecetas.ManejoFicheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zafiro2.misrecetas.R;
import com.github.barteksc.pdfviewer.PDFView;

public class VisorPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);
        Intent intent = getIntent();
        String enlace = intent.getStringExtra("enlace");

        PDFView p = (PDFView)findViewById(R.id.pdfView);
        p.fromAsset(enlace).load();

    }
}
