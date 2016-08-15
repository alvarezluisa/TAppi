package com.tappi.tappi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class datosBasicosRegistro extends AppCompatActivity {

    private Button btnGuardar;
    private Spinner selectGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_basicos_registro);
        btnGuardar= (Button) findViewById(R.id.btnGuardarRegistro);
        selectGenero= (Spinner) findViewById(R.id.selectGenero);

        btnGuardar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                if(selectGenero.getSelectedItemPosition()==1){
                    Intent intent= new Intent(getApplicationContext(),PrincipalPaciente.class);
                    startActivity(intent);
                }else{
                    if(selectGenero.getSelectedItemPosition()==2){
                        Intent intent= new Intent(getApplicationContext(),DatosGinecologico.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(datosBasicosRegistro.this,"Debe seleccionar un genero",Toast.LENGTH_LONG).show();

                    }
                }



            }

        });
    }


}
