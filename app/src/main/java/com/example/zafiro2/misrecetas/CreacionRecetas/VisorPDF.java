package com.example.zafiro2.misrecetas.CreacionRecetas;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class VisorPDF extends AppCompatActivity {
    PDFView p;
    String enlace;
    Receta receta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        receta = (Receta) bundle.getSerializable("receta");

        p  = (PDFView)findViewById(R.id.pdfView);
        enlace = receta.getArchivo();

        if(enlace.substring(0,3).equals("pdf")){
            cargarFromAssest();
        }
        else{
            cargarDesdeDirectorio();
        }

        cargarDesdeDirectorio();

    }

    public void cargarFromAssest(){
        p.fromAsset(enlace).load();
        Toast.makeText(this, "desde assest", Toast.LENGTH_SHORT).show();

    }
    public void cargarDesdeDirectorio(){

        this.p.fromFile(new File(receta.getArchivo()));
        Toast.makeText(this, receta.getArchivo(), Toast.LENGTH_SHORT).show();
    }
}
