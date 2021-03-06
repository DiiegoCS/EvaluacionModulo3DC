package com.diegocampos.evaluacionmodulo3dc.View.actividades;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.ConsultaRegistroEquipo.ConsultaRegistroEquipoViewInterface;
import com.diegocampos.evaluacionmodulo3dc.Presenter.ConsultaRegistroEquipoPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.databinding.ActivityConsultaRegistroEquipoBinding;

import java.util.ArrayList;
import java.util.List;

public class ConsultaRegistroEquipoViewImpl extends AppCompatActivity implements ConsultaRegistroEquipoViewInterface {

    private ActivityConsultaRegistroEquipoBinding vb3;

    String codigoIngreso2 ="";
    private ConsultaRegistroEquipoPresenterInterface presentador = new ConsultaRegistroEquipoPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb3 = ActivityConsultaRegistroEquipoBinding.inflate(getLayoutInflater());
        View view = vb3.getRoot();
        setContentView(view);

       codigoIngreso2 = getIntent().getStringExtra("cod");

       vb3.btnBack2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

       presentador.mostrarRegistro(getBaseContext(), codigoIngreso2);
    }

    @Override
    public void exitoMostrar(ArrayList<String> listaRegistro, List<Bitmap> archivos) {

        vb3.txtCodigoIngreso3.setText("Código de Ingreso: " + listaRegistro.get(0));
        vb3.txtNombre3.setText("Nombre Cliente: " + listaRegistro.get(1));
        vb3.txtMarca3.setText("Código de Ingreso: " + listaRegistro.get(2));
        vb3.txtModelo3.setText("Código de Ingreso: " + listaRegistro.get(3));
        vb3.txtFecha3.setText("Fecha de Ingreso: " + listaRegistro.get(4));

        if(listaRegistro.get(5).equals("SI")){
            vb3.btnCajaSi3.setChecked(true);
        }
        else if(listaRegistro.get(5).equals("NO")){
            vb3.btnCajaNo3.setChecked(true);
        }

        if(listaRegistro.get(6).equals("SI")){
            vb3.btnCargadorSi3.setChecked(true);
        }
        else if(listaRegistro.get(6).equals("NO")){
            vb3.btnCargadorNo3.setChecked(true);
        }

        if(listaRegistro.get(7).equals("SI")){
            vb3.btnManualSi3.setChecked(true);
        }
        else if(listaRegistro.get(7).equals("NO")){
            vb3.btnManualNo3.setChecked(true);
        }

        if(listaRegistro.get(8).equals("SI")){
            vb3.btnGarantiaSi3.setChecked(true);
        }
        else if(listaRegistro.get(8).equals("NO")){
            vb3.btnGarantiaNo3.setChecked(true);
        }

        if(listaRegistro.get(9).equals("SI")){
            vb3.btnCargaSOSi3.setChecked(true);
        }
        else if(listaRegistro.get(9).equals("NO")){
            vb3.btnCargaSONo3.setChecked(true);
        }

        if(listaRegistro.get(10).equals("SI")){
            vb3.btnMonitorSi3.setChecked(true);
        }
        else if(listaRegistro.get(10).equals("NO")){
            vb3.btnMonitorNo3.setChecked(true);
        }

        if(listaRegistro.get(11).equals("SI")){
            vb3.btnAudioSi3.setChecked(true);
        }
        else if(listaRegistro.get(11).equals("NO")){
            vb3.btnAudioNo3.setChecked(true);
        }

        if(listaRegistro.get(12).equals("SI")){
            vb3.btnTouchSi3.setChecked(true);
        }
        else if(listaRegistro.get(12).equals("NO")){
            vb3.btnTouchNo3.setChecked(true);
        }

        vb3.txtObservaciones3.setText(listaRegistro.get(13));
        vb3.img01.setImageBitmap(archivos.get(0));
        vb3.img02.setImageBitmap(archivos.get(1));

    }

    @Override
    public void errorMostrar() {
        Toast.makeText(this, "No se pudo mostrar el registro", Toast.LENGTH_SHORT).show();
    }
/*
    public void mostrarConsulta() {
        ConexionBD conexion = new ConexionBD(getBaseContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigoIngreso = codigoIngreso2;

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
                    if (fotos[i].getAbsolutePath().contains(codigoIngreso2)) {
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

    }*/

}