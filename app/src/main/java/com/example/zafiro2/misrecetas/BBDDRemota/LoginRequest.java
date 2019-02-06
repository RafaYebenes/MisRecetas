package com.example.zafiro2.misrecetas.BBDDRemota;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String ruta = "http://mibonsai-cp5006.wordpresstemporal.com/MisRecetas/login.php";
    private Map<String, String> parametros;
    Date fecha = Calendar.getInstance().getTime();


    public LoginRequest(String usuario, String password,  Response.Listener<String> listener) {
        super(Method.POST,ruta, listener, null);
        parametros = new HashMap<>();

        parametros.put("usuario",usuario+" ");
        parametros.put("password",password+" ");


    }


    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
