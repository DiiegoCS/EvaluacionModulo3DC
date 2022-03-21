package com.diegocampos.evaluacionmodulo3dc.View.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer.LoginPresenter;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.LoginUSer.Loginview;
import com.diegocampos.evaluacionmodulo3dc.Presenter.LoginPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.R;

public class Login extends AppCompatActivity implements Loginview {

    EditText txtUser, txtPass;
    Button btnLogin;
    ProgressBar barraProgreso;

    LoginPresenter presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        barraProgreso = findViewById(R.id.barraProgreso);

        presentador = new LoginPresenterImpl(this);

    }

    @Override
    public void mostrarProgreso() {
        barraProgreso.setVisibility(View.VISIBLE);
    }

    @Override
    public void esconderProgreso() {
        barraProgreso.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUser() {
        txtUser.setError("Campo usuario obligatorio");
    }

    @Override
    public void setErrorPassword() {
        txtPass.setError("Campo password obligatorio");
    }

    @Override
    public void exito(String nombre) {
        Intent intent = new Intent(this, OtraActividad.class);

        intent.putExtra("x", nombre);

        startActivity(intent);
    }

    @Override
    public void noExiste() {
        Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
    }

    public void solicitarValidacion(View view){
        presentador.validarUsuario(txtUser.getText().toString(), txtPass.getText().toString(), this);
    }

    public void abrirRegistro(View view){
        startActivity(new Intent(this, Registro.class));
    }
}