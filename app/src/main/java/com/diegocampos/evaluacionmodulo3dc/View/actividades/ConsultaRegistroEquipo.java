package com.diegocampos.evaluacionmodulo3dc.View.actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diegocampos.evaluacionmodulo3dc.Interactor.ConexionBD;
import com.diegocampos.evaluacionmodulo3dc.databinding.ActivityConsultaRegistroEquipoBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRegistroEquipo extends AppCompatActivity {

    private ActivityConsultaRegistroEquipoBinding vb3;

    String codigo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb3 = ActivityConsultaRegistroEquipoBinding.inflate(getLayoutInflater());
        View view = vb3.getRoot();
        setContentView(view);

        codigo = getIntent().getStringExtra("cod");

        mostrarConsulta();
    }

    public void mostrarConsulta() {
        ConexionBD conexion = new ConexionBD(getBaseContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigoIngreso = codigo;

        Cursor fila = bd.rawQuery("SELECT * FROM registroEquipos WHERE codigoIngreso='"+codigoIngreso+"'",null);

        if(fila.moveToFirst()) {

            vb3.txtCodigoIngreso3.setText("Código de Ingreso: " + fila.getString(0));
            vb3.txtNombre3.setText("Nombre Cliente: " + fila.getString(1));
            vb3.txtMarca3.setText("Código de Ingreso: " + fila.getString(2));
            vb3.txtModelo3.setText("Código de Ingreso: " + fila.getString(3));
            vb3.txtFecha3.setText("Fecha de Ingreso: " + fila.getString(4));

            if(fila.getString(5).equals("SI")){
                vb3.btnCajaSi3.setChecked(true);
            }
            else if(fila.getString(5).equals("NO")){
                vb3.btnCajaNo3.setChecked(true);
            }

            if(fila.getString(6).equals("SI")){
                vb3.btnCargadorSi3.setChecked(true);
            }
            else if(fila.getString(6).equals("NO")){
                vb3.btnCargadorNo3.setChecked(true);
            }

            if(fila.getString(7).equals("SI")){
                vb3.btnManualSi3.setChecked(true);
            }
            else if(fila.getString(7).equals("NO")){
                vb3.btnManualNo3.setChecked(true);
            }

            if(fila.getString(8).equals("SI")){
                vb3.btnGarantiaSi3.setChecked(true);
            }
            else if(fila.getString(8).equals("NO")){
                vb3.btnGarantiaNo3.setChecked(true);
            }

            if(fila.getString(9).equals("SI")){
                vb3.btnCargaSOSi3.setChecked(true);
            }
            else if(fila.getString(9).equals("NO")){
                vb3.btnCargaSONo3.setChecked(true);
            }

            if(fila.getString(10).equals("SI")){
                vb3.btnMonitorSi3.setChecked(true);
            }
            else if(fila.getString(10).equals("NO")){
                vb3.btnMonitorNo3.setChecked(true);
            }

            if(fila.getString(11).equals("SI")){
                vb3.btnAudioSi3.setChecked(true);
            }
            else if(fila.getString(11).equals("NO")){
                vb3.btnAudioNo3.setChecked(true);
            }

            if(fila.getString(12).equals("SI")){
                vb3.btnTouchSi3.setChecked(true);
            }
            else if(fila.getString(12).equals("NO")){
                vb3.btnTouchNo3.setChecked(true);
            }

            vb3.txtObservaciones3.setText(fila.getString(13));

            File ruta = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/MyApp/");
            }
            else{
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            }

            List<Bitmap> archivos = new ArrayList<>();

            File fotos[] = ruta.listFiles();

            if(fotos != null) {
                for (int i = 0; i < fotos.length; i++) {
                    if (fotos[i].getAbsolutePath().contains(codigo)) {
                        archivos.add(BitmapFactory.decodeFile(fotos[i].getAbsolutePath()));

                    }
                }
            }
            vb3.img01.setImageBitmap(archivos.get(0));
            vb3.img02.setImageBitmap(archivos.get(1));


            bd.close();
        }

        else{
            bd.close();
            Toast.makeText(getBaseContext(), "Registro No Encontrado", Toast.LENGTH_SHORT).show();
        }

    }

}