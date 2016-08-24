package com.tappi.tappi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_log_in);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.v("login","ingreso con facebook");
                final String id= loginResult.getAccessToken().getUserId();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("WS","Entro al hilo");

                        if(conexion(id)){
                            Intent intent= new Intent(getApplicationContext(),PrincipalPaciente.class);
                            startActivity(intent);
                        }else{
                            Intent intent= new Intent(getApplicationContext(),datosBasicosRegistro.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }
                }

                ).start();

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
                Log.v("login",e.getMessage());

            }
        });

        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText(e.getMessage());
            }
        });*/

    }

    public boolean conexion(String nombre){
        Log.v("WS","Entro al hilo");
        StringBuilder resul = obtenerDatos(nombre);

        String resultado = resul.toString();

        try {
            JSONArray objetos = new JSONArray(resultado);


            if(objetos.length()==0){
                return false;
            }
            else{
                return true;
            }
        } catch (JSONException e) {
            Log.v("WS",e.getMessage());
        }

        return false;
    }

    private StringBuilder obtenerDatos(String nombre) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = new StringBuilder();

        try {
            url = new URL("http://192.168.0.2:8080/test.php?usu=" + nombre);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
