package com.android.ejemplomysql;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ProductoServicio {
    private RequestQueue requestQueue;
    private Context context;

    public ProductoServicio(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);

    }
    //url de los php
    String urlincertar = "http://192.168.3.9/android/insertar_producto.php";
    String urlbuscar = "http://192.168.3.9/android/buscar_producto.php?codigo=";

    String urleditar = "http://192.168.3.9/android/modificar_producto.php";

    String urleliminar ="http://192.168.3.9/android/eliminar_producto.php";

    public void agregarProducto(String codigo, String producto, String precio, String fabricante) {
        String url = urlincertar;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Toast.makeText(context, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show();
            Log.d("ProductoService", "Producto agregado exitosamente");
        }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            Log.e("ProductoService", "Error al agregar producto: " + error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo);
                parametros.put("producto", producto);
                parametros.put("precio", precio);
                parametros.put("fabricante", fabricante);
                Log.d("ProductoService", "Datos enviados: Codigo=" + codigo + ", Producto=" + producto + ", Precio=" + precio + ", Fabricante=" + fabricante);

                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void buscarProducto(String codigo) {
        String url = urlbuscar + codigo;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            // Procesar la respuesta y actualizar la UI
        }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void editarProducto(String codigo, String producto, String precio, String fabricante) {
        String url = urleditar;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Toast.makeText(context, "Producto editado exitosamente", Toast.LENGTH_SHORT).show();
        }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo);
                parametros.put("producto", producto);
                parametros.put("precio", precio);
                parametros.put("fabricante", fabricante);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void eliminarProducto(String codigo) {
        String url = urleliminar;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            Toast.makeText(context, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
        }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }
}

