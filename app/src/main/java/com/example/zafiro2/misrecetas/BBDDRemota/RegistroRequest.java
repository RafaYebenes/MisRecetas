package com.example.zafiro2.misrecetas.BBDDRemota;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {

    private static final String ruta = "http://mibonsai-cp5006.wordpresstemporal.com/MisRecetas/registro.php";
    private Map<String, String> parametros;
    Date fecha = Calendar.getInstance().getTime();


    public RegistroRequest(String usuario, String password, String email, String nombre, String apellidos,  Response.Listener<String> listener) {
        super(Method.POST,ruta, listener, null);
        parametros = new HashMap<>();
        String fecha_alta = fecha.toString();
        //parametros.put("nombre",nombre+"");
        parametros.put("usuario",usuario);
        parametros.put("password",password+" ");
        parametros.put("email",email+" ");
        parametros.put("nombre", nombre+" ");
        parametros.put("apellidos", apellidos+" ");

    }


    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
