package com.tappi.tappi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private Button cerrarsesion,buscar;
    private TextView nombreBuscar;
    private TextView nombreR,id,cedula,apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.textViewBienvenida);
        info.setText("Ingreso a la aplicacion");
        buscar=(Button)findViewById(R.id.btnBuscar);
        nombreBuscar = (TextView)findViewById(R.id.TextNombreBuscar);
        cerrarsesion=(Button)findViewById(R.id.btncerrarsesion);

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();
                finish();

            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("WS","Entro al hilo");
                        conexion(nombreBuscar.getText().toString());
                    }
                }

                ).start();


            }
        });
    }
    public void conexion(String nombre){
        Log.v("WS","Entro al hilo");
            StringBuilder resul = obtenerDatos(nombre);

            String resultado = resul.toString();

            try {
                JSONArray objetos = new JSONArray(resultado);

                for (int i = 0; i < objetos.length(); i++) {
                    JSONObject objeto = objetos.getJSONObject(i);
                    nombreR = (TextView) findViewById(R.id.textNombreResult);
                    id = (TextView) findViewById(R.id.textView8);
                    cedula = (TextView) findViewById(R.id.textView10);
                    apellido = (TextView) findViewById(R.id.textView9);
                    id.setText(objeto.getInt("IDUsuario"));
                    nombreR.setText(objeto.getString("Nombre"));
                    cedula.setText(objeto.getString("Cedula"));
                    apellido.setText(objeto.getString("Apellido"));
                    Log.v("WS","asigno las valiables"+objeto.toString());
                }
            } catch (JSONException e) {
                Log.v("WS",e.getMessage());
            }


    }

    private StringBuilder obtenerDatos(String nombre) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = new StringBuilder();

        try {
            url = new URL("http://10.0.1.3/test.php?usu=" + nombre);
            Log.v("WS","creo URL");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            Log.v("WS","abrio conexion");

            respuesta = conexion.getResponseCode();
            Log.v("WS","tiene respuesta");

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    resul.append(linea);
                }
            }
        } catch (Exception e) {
            Log.v("WS", e.getMessage());
        }
        return resul;
    }
}
