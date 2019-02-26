package com.example.zafiro2.misrecetas.Mapas;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.zafiro2.misrecetas.BBDDRemota.CoordenadasRequest;
import com.example.zafiro2.misrecetas.BBDDRemota.Login;
import com.example.zafiro2.misrecetas.CreacionRecetas.CrearPDF;
import com.example.zafiro2.misrecetas.Main;
import com.example.zafiro2.misrecetas.Objetos.usuario;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlankFragment extends SupportMapFragment implements OnMapReadyCallback{

    private Location loc;
    private  LatLng latLng;
    private Marker posicionUsuario;
    private String[] ArrayOpciones = {"Enviar Mensaje", "Mandar Receta", "Llamar"};
    usuario user;
    ArrayList<usuario> listaUsuarios;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        user = new usuario();
       if(getArguments()!=null){
           user = (usuario) getArguments().getSerializable("usuario");
       }
       else{
           Toast.makeText(getActivity(), "Error al cargar", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);
        ObtenerCoordenadasUsuarios();
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
//            loc = new Location(locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Double lat = loc.getLatitude(), lon = loc.getLongitude();
            latLng = new LatLng(lat,lon);
            LatLng localizacion2 = new LatLng(lat+1,lon+1);
            //Toast.makeText(getActivity().getApplicationContext(), "Latitud: "+loc.getLatitude()+" Logitud: "+loc.getLongitude(), Toast.LENGTH_SHORT).show();
            float zoom = 17;
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
            posicionUsuario = map.addMarker(new MarkerOptions().position(latLng));
            //map.addMarker(new MarkerOptions().position(latLng));
            map.addMarker(new MarkerOptions().position(localizacion2));

            colocarUsuarios(map);

        }


    }

    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecione una Acción")
                .setItems(ArrayOpciones, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getContext(), "Has selecionado "+i, Toast.LENGTH_SHORT).show();

                    }
                });
        return builder.create();
    }


    public void ObtenerCoordenadasUsuarios(){

        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonRespuesta = new JSONObject(response);
                    boolean ok = jsonRespuesta.getBoolean("success");
                    if (ok == true) {
                         JSONArray jsonArray = jsonRespuesta.getJSONArray("");
                         for(int i = 0; i < jsonArray.length();i++) {
                             usuario user = (usuario) jsonArray.get(i);
                             listaUsuarios.add(user);
                         }
                    } else {
                        Toast.makeText(getContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }

        };
        CoordenadasRequest coordenadasRequest = new CoordenadasRequest(respuesta);
        RequestQueue cola = Volley.newRequestQueue(getContext());
        cola.add(coordenadasRequest);
    }


    public void colocarUsuarios(GoogleMap map){

        ArrayList<Marker> listaMarket = new ArrayList<Marker>();
        Float lat, lon;

        for(int i =0; i <listaUsuarios.size();i++){
            lat = listaUsuarios.get(i).getLatitud();
            lon = listaUsuarios.get(i).getLongitud();
            LatLng latLng=  new LatLng(lat, lon);
            Marker marker = map.addMarker(new MarkerOptions().position(latLng));
            marker.setTag(latLng);
            listaMarket.add(marker);
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Dialog dialog = onCreateDialog();
                dialog.show();
                return false;
            }
        });
    }
}
