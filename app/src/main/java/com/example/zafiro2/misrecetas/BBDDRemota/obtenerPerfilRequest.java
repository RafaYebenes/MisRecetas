package com.example.zafiro2.misrecetas.BBDDRemota;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class obtenerPerfilRequest extends StringRequest {

    private static final String ruta = "http://mibonsai-cp5006.wordpresstemporal.com/MisRecetas/obtenerPerfil.php";

    private Map<String, String> parametros;



    public obtenerPerfilRequest(String usuario, String telefono, String fechaNacimiento,String imagen, Response.Listener<String> listener) {
        super(Request.Method.POST,ruta, listener, null);
        parametros = new HashMap<>();
        usuario = usuario.substring(0, usuario.length() - 2);
        parametros.put("usuario",usuario);
        parametros.put("telefono", telefono+" ");
        parametros.put("fechaNacimiento", fechaNacimiento+" ");
        parametros.put("imagen",imagen);
    }


    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
