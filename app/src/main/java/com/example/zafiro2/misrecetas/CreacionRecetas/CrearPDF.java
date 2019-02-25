package com.example.zafiro2.misrecetas.CreacionRecetas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zafiro2.misrecetas.BBDDInterna.DatabaseAccess;
import com.example.zafiro2.misrecetas.Objetos.Receta;
import com.example.zafiro2.misrecetas.R;

import java.io.File;


public class CrearPDF extends AppCompatActivity {
    String[] ArrayOpciones = {"Carnes","Verduras","Pescados","Entrantes","Ensaladas","Postres"};
    Receta receta;
    EditText edtTitulo;
    EditText edtCategoria;
    EditText edtDescripcion;
    Button btnCategoria;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pdf);
        cargarObjetos();
    }


    public void cargarObjetos(){
        receta = new Receta();
        edtTitulo = findViewById(R.id.edtTituloReceta);
        edtCategoria = findViewById(R.id.edtCategoriaReceta);
        edtCategoria.setEnabled(false);
        btnCategoria = findViewById(R.id.btnCategoria);
        edtDescripcion = findViewById(R.id.edtDescripcionRecetas);
        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = onCreateDialog();
                dialog.show();
            }
        });
        btnGuardar = findViewById(R.id.btnGuardarReceta);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receta.setNombre(edtTitulo.getText().toString());
                receta.setDescripcion(edtTitulo.getText().toString());
                receta.setCategoria(edtCategoria.getText().toString());

                GenerarPDF(v,receta);
               if(receta!=null) {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstace(getApplicationContext());
                    databaseAccess.open();
                    databaseAccess.insertarReceta(receta);
                    databaseAccess.close();

                }
            }
        });
    }

    public void GenerarPDF(View v, Receta receta){

        MetodosCreacionPDF metodosCreacionPDF = new MetodosCreacionPDF();


        File directorio = new File (CrearPDF.this.getFilesDir(),receta.getNombre()+".pdf");

        if (metodosCreacionPDF.write(receta.getNombre(), receta.getDescripcion(),directorio)) {
            Toast.makeText(getApplicationContext(),receta.getNombre() + ".pdf Ha sido creado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Fallo en la creación",Toast.LENGTH_SHORT).show();
        }

        receta.setArchivo(metodosCreacionPDF.getFpath());
        Toast.makeText(this, receta.getArchivo(), Toast.LENGTH_SHORT).show();
    }


    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CrearPDF.this);
        builder.setTitle("Seleccione la categoria de la receta")
                .setItems(ArrayOpciones, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        edtCategoria.setText(ArrayOpciones[i]);

                    }
                });
        return builder.create();
    }


}


