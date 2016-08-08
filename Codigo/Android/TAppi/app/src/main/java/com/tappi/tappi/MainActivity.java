package com.tappi.tappi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private Button cerrarsesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.textViewBienvenida);
        info.setText("Ingreso a la aplicacion");

        cerrarsesion=(Button)findViewById(R.id.btncerrarsesion);

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();
                finish();

            }
        });





    }
}
