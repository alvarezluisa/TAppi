package com.tappi.tappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class PrincipalPaciente extends AppCompatActivity {
    private TextView nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_principal_paciente);
        nombreUsuario= (TextView) findViewById(R.id.textNombrePrincipal);
        nombreUsuario.setText(getIntent().getExtras().getString("Nombre"));
        Log.v("Nombre",getIntent().getExtras().getString("Nombre"));

    }
}
