package com.android.ejemplomysql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    EditText edtcodigo, edtproducto, edtprecio, edtfabricante;
    Button btneliminar, btnagregar, btnbuscar, btneditar;

    //url de los php
    String urlincertar = "http://192.168.121.202:80/android/insertar_producto.php";
    String urlbuscar = "http://192.168.121.202:80/android/buscar_producto.php?codigo=";

    String urleditar = "http://192.168.121.202:80/android/modificar_producto.php";

    String urleliminar ="http://192.168.121.202:80/android/eliminar_porducto.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtcodigo=findViewById(R.id.edtcodigo);
        edtfabricante=findViewById(R.id.edtfabricante);
        edtprecio=findViewById(R.id.edtprecio);
        edtproducto=findViewById(R.id.edtproducto);
        btnagregar=findViewById(R.id.btnagregar);
        btnbuscar=findViewById(R.id.btnbuscar);
        btneditar=findViewById(R.id.btneditar);
        btneliminar=findViewById(R.id.btneliminar);

        requestQueue = Volley.newRequestQueue(this);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio(urlincertar);

            }
        });
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = edtcodigo.getText().toString();
                buscarProducto(urlbuscar+codigo+"");
            }
        });
        // agregamos listener a boton editar
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usamos el mismo método por que sirve para lo mismo y contiene todos los parámetros
                //solo cambiamos el WEBSERVICE
                ejecutarServicio2(urleditar);
            }
        });
        // agregamos lsitener a boton eliminar
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProducto(urleliminar);
            }
        });
    }

    private void ejecutarServicio(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación Exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",edtcodigo.getText().toString());
                parametros.put("producto",edtproducto.getText().toString());
                parametros.put("precio",edtprecio.getText().toString());
                parametros.put("fabricante",edtfabricante.getText().toString());
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ejecutarServicio2(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("Producto actualizado correctamente")) {
                    Toast.makeText(getApplicationContext(), "Operación Exitosa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al actualizar producto", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",edtcodigo.getText().toString());
                parametros.put("producto",edtproducto.getText().toString());
                parametros.put("precio",edtprecio.getText().toString());
                parametros.put("fabricante",edtfabricante.getText().toString());
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void buscarProducto(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtproducto.setText(jsonObject.getString("producto"));
                        edtprecio.setText(jsonObject.getString("precio"));
                        edtfabricante.setText(jsonObject.getString("fabricante"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("Error", error.getMessage());

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    // agregamos método eliminar producto
    private void eliminarProducto(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EL PRODUCTO FUE ELIMINADO", Toast.LENGTH_SHORT).show();
                limpiarCajas(); //llamamos limpiar cajas
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",edtcodigo.getText().toString());
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    //creamos método limpiar cajas para aplicar en los métodos
    private void limpiarCajas(){
        edtproducto.setText("");
        edtprecio.setText("");
        edtfabricante.setText("");
        edtcodigo.setText("");
    }
}