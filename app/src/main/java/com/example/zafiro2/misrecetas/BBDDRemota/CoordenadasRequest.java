package com.example.zafiro2.misrecetas.BBDDRemota;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class CoordenadasRequest  extends StringRequest {

    private static String url = "http://mibonsai-cp5006.wordpresstemporal.com/MisRecetas/obtenerUsuarioMapa.php";

    private Map<String, String> parametros;


    public CoordenadasRequest(Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
