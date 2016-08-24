package com.tappi.tappi;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class datosBasicosRegistro extends AppCompatActivity {

    private Button btnGuardar;
    private Spinner selectGenero,selectSangre,selectEPS;
    private TextView nombre,apellido, direccionUsuario, fecha, cedula,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_basicos_registro);

        btnGuardar= (Button) findViewById(R.id.btnGuardarRegistro);

        nombre= (TextView) findViewById(R.id.TextNombre);
        apellido= (TextView) findViewById(R.id.TextApellido);
        direccionUsuario= (TextView) findViewById(R.id.TextDireccion);
        fecha = (TextView) findViewById(R.id.TextFechaNacimiento);
        cedula= (TextView) findViewById(R.id.TextCedula);
        correo= (TextView) findViewById(R.id.TextCorreo);

        selectGenero= (Spinner) findViewById(R.id.selectGenero);
        selectSangre= (Spinner) findViewById(R.id.SelectSangre);
        selectEPS= (Spinner) findViewById(R.id.SelectEPS);


        btnGuardar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("WS","Entro al hilo");

                        if(enviarDatos()){
                            if(selectGenero.getSelectedItemPosition()==1){
                                Intent intent= new Intent(getApplicationContext(),PrincipalPaciente.class);
                                intent.putExtra("Nombre",nombre.getText());
                                startActivity(intent);
                            }else{
                                if(selectGenero.getSelectedItemPosition()==2){
                                    Intent intent= new Intent(getApplicationContext(),DatosGinecologico.class);
                                    intent.putExtra("Nombre",nombre.getText());
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(datosBasicosRegistro.this,"Debe seleccionar un genero",Toast.LENGTH_LONG).show();

                                }
                            }
                        }else{
                            Log.v("WS","Error en insercion");
                        }
                    }
                }

                ).start();








            }

        });
    }


    private boolean enviarDatos() {
        URL url = null;
        String linea=null;
        int respuesta = 0;


        String direccion= crearDireccion();

        try {
            url = new URL(direccion);
            Log.v("WS","creo URL");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            Log.v("WS","abrio conexion");

            respuesta = conexion.getResponseCode();
            Log.v("WS","tiene respuesta");

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    Log.v("WS","respues " +linea);
                }
                    return true;
            }
        } catch (Exception e) {
            Log.v("WS", e.getMessage());
        }
        return false;
    }

    private String crearDireccion() {
        Resources res= getResources();
        String[] generos= res.getStringArray(R.array.Generos);
        String[] tipoSangre= res.getStringArray(R.array.TipoSangre);
        String[] listaEps= res.getStringArray(R.array.EPS);
        String direccion="http://192.168.0.2:8080/InsertarUsuario.php";
        direccion+="?id="+getIntent().getExtras().getString("id");
        direccion+="&apellido="+apellido.getText();
        direccion+="&cedula="+cedula.getText();
        direccion+="&correo="+correo.getText();
        direccion+="&direccion="+direccionUsuario.getText();
        direccion+="&fechaNacimiento="+fecha.getText();

        direccion+="&genero="+generos[selectGenero.getSelectedItemPosition()];
        direccion+="&nombre="+nombre.getText();
        direccion+="&rol="+"P";
        direccion+="&tipoSangre="+tipoSangre[selectSangre.getSelectedItemPosition()];
        direccion+="&eps="+listaEps[selectEPS.getSelectedItemPosition()];







        return direccion;
    }


}
