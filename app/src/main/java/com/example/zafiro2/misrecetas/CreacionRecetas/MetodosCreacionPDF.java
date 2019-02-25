package com.example.zafiro2.misrecetas.CreacionRecetas;

import android.os.Environment;

import java.io.File;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MetodosCreacionPDF {

    String fpath;


    public Boolean write(String fname, String fcontent, File carpeta) {
        try {

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            File pdf = new File(carpeta,fname+".pdf");

            fpath = pdf.getAbsolutePath();

            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);


            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(pdf.getAbsoluteFile()));
            document.open();
            document.add(new Paragraph(fname));

            Paragraph contenido = new Paragraph(fcontent);

            document.add(contenido);

            document.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }


    public String getFpath(){
        return fpath;
    }

}
