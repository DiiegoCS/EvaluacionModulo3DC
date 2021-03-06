package com.diegocampos.evaluacionmodulo3dc.View.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser.RegistroPresenter;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroUser.RegistroView;
import com.diegocampos.evaluacionmodulo3dc.Presenter.RegistroPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.R;

public class Registro extends AppCompatActivity implements RegistroView {

    EditText txtNombre, txtUser2, txtPass2;
    Button btnRegistrar ;
    ImageButton btnBack;


    RegistroPresenter presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtUser2 = findViewById(R.id.txtUser2);
        txtPass2 = findViewById(R.id.txtPass2);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnBack = findViewById(R.id.btnBack1);

        presentador = new RegistroPresenterImpl(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void registro(View view){
        presentador.registrar(txtNombre.getText().toString(),
                txtUser2.getText().toString(),
                txtPass2.getText().toString(), this);
    }

    @Override
    public void exito() {
        Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
        txtNombre.setText("");
        txtUser2.setText("");
        txtPass2.setText("");

    }

    @Override
    public void error() {
        Toast.makeText(this, "Error: No se pudo registrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorNombre() {
        txtNombre.setError("Complete el campo");
    }

    @Override
    public void setErrorUser() {
        txtUser2.setError("Complete el campo");
    }

    @Override
    public void setErrorPassword() {
        txtPass2.setError("Complete el campo");
    }
}