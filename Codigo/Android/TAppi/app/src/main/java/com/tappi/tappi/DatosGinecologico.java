package com.tappi.tappi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DatosGinecologico extends AppCompatActivity {
    private Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_ginecologico);
        btnGuardar= (Button) findViewById(R.id.btnGuardarGinecologico);

        btnGuardar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                    Intent intent= new Intent(getApplicationContext(),PrincipalPaciente.class);
                    startActivity(intent);



            }

        });
    }
}
