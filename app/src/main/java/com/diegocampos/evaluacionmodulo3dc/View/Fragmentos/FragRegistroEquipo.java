package com.diegocampos.evaluacionmodulo3dc.View.Fragmentos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoPresenter;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.RegistroEquipo.RegistroEquipoView;
import com.diegocampos.evaluacionmodulo3dc.Presenter.RegistroEquipoPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.View.actividades.OtraActividad;
import com.diegocampos.evaluacionmodulo3dc.databinding.FragmentFragRegistroEquipoBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FragRegistroEquipo extends Fragment implements RegistroEquipoView {

    private FragmentFragRegistroEquipoBinding vb2;

    String f, foto1, foto2;

    RegistroEquipoPresenter presenter;
    OtraActividad otraActividad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vb2 = FragmentFragRegistroEquipoBinding.inflate(inflater, container, false);
        View v = vb2.getRoot();

        presenter = new RegistroEquipoPresenterImpl(this);

        //fecha
        Calendar calendar = Calendar.getInstance(); //1 Returns instance with current date and time set
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        f = formatter.format(calendar.getTime());
        vb2.txtFecha.setText("Fecha de Ingreso: " + f);

        vb2.btnGuardarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarGuardarFotos();
                vb2.btnGuardarRegistro.setEnabled(true);
            }



        });

        vb2.btnGuardarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRegistro();
            }
        });

        vb2.btnFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OtraActividad)getActivity()).permisoCamara(vb2.img1);
            }
        });
        vb2.btnFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OtraActividad)getActivity()).permisoCamara(vb2.img2);
            }
        });


        return v;
    }


    @Override
    public void exitoRegistro() {
        Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorRegistro() {
        Toast.makeText(getContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorCodigo() {
        vb2.txtCodigoIngreso.setError("Campo Obligatorio");
    }

    @Override
    public void setErrorNombre() {
        vb2.txtNombre.setError("Campo Obligatorio");
    }

    @Override
    public void guardarRegistro() {

        String codigo = vb2.txtCodigoIngreso.getText().toString();
        String nombre = vb2.txtNombre.getText().toString();
        String marca = vb2.txtMarca.getText().toString();
        String modelo = vb2.txtModelo.getText().toString();
        String observaciones = vb2.txtObservaciones.getText().toString();

        String caja="";
        if(vb2.btnCajaSi.isChecked()){  caja = "SI"; }
        else if(vb2.btnCajaNo.isChecked()){ caja = "NO";}

        String cargador="";
        if(vb2.btnCargadorSi.isChecked()){  cargador = "SI"; }
        else if(vb2.btnCargadorNo.isChecked()){ cargador = "NO";}

        String manual="";
        if(vb2.btnManualSi.isChecked()){  manual = "SI"; }
        else if(vb2.btnManualNo.isChecked()){ manual = "NO";}

        String garantia="";
        if(vb2.btnGarantiaSi.isChecked()){  garantia = "SI"; }
        else if(vb2.btnGarantiaNo.isChecked()){ garantia = "NO";}

        String cargaSo="";
        if(vb2.btnCargaSOSi.isChecked()){  cargaSo = "SI"; }
        else if(vb2.btnCargaSONo.isChecked()){ cargaSo = "NO";}

        String monitor="";
        if(vb2.btnMonitorSi.isChecked()){  monitor = "SI"; }
        else if(vb2.btnMonitorNo.isChecked()){ monitor = "NO";}

        String audio="";
        if(vb2.btnAudioSi.isChecked()){  audio = "SI"; }
        else if(vb2.btnAudioNo.isChecked()){ audio = "NO";}

        String touchpad="";
        if(vb2.btnTouchSi.isChecked()){  touchpad = "SI"; }
        else if(vb2.btnTouchNo.isChecked()){ touchpad = "NO";}


        presenter.guardarRegistro(codigo, nombre, marca, modelo, f, caja, cargador, manual,
                garantia, cargaSo, monitor, audio, touchpad, observaciones,getContext());

    }


    @Override
    public void solicitarGuardarFotos() {
        ((OtraActividad)getActivity()).permisosAlmacenamiento(vb2.txtCodigoIngreso.getText().toString());
    }

    @Override
    public void exitoGuardarFotos() {

        Toast.makeText(getContext(), "Fotos Guardadas Exitosamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorGuardarFotos() {
        Toast.makeText(getContext(), "No se pudo guardar las fotos", Toast.LENGTH_SHORT).show();
    }

    public static void mostrarImagen(ImageView img, Bitmap bitmap){
        img.setImageBitmap(bitmap);
    }
}

