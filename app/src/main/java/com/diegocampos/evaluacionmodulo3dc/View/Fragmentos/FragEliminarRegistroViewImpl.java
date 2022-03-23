package com.diegocampos.evaluacionmodulo3dc.View.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.EliminarRegistro.FragEliminarRegistroViewInterface;
import com.diegocampos.evaluacionmodulo3dc.Presenter.FragEliminarRegistroPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.databinding.FragmentFragEliminarRegistroBinding;

import java.util.ArrayList;


public class FragEliminarRegistroViewImpl extends Fragment implements FragEliminarRegistroViewInterface {

    private FragmentFragEliminarRegistroBinding vb4;

    FragEliminarRegistroPresenterInterface presentador= new FragEliminarRegistroPresenterImpl(this);

    String codigo = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vb4 = FragmentFragEliminarRegistroBinding.inflate(inflater, container, false);
        View v = vb4.getRoot();



        vb4.btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigo = vb4.txtCodigoBusqueda.getText().toString();
                presentador.BuscarReg(getContext(), codigo);
            }
        });

        vb4.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentador.eliminarRegistro(getContext(), codigo);
            }
        });

        return v;
    }

    @Override
    public void exitoBuscarR(ArrayList<String> datos) {
        vb4.txtCodIngr.setText("Codigo de Ingreso: " +datos.get(0));
        vb4.txtNomCliente.setText("Nombre del Cliente: "+datos.get(1));
        vb4.txtMarcaEquipo.setText("Marca del Equipo: "+datos.get(2));
        vb4.txtModeloEquipo.setText("Model del Equipo: "+datos.get(3));
        vb4.txtFechaIng.setText("Fecha de Ingreso: "+datos.get(4));

        vb4.btnEliminar.setEnabled(true);
    }

    @Override
    public void errorBuscarR() {
        Toast.makeText(getContext(), "No se encontró registro", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void exitoEliminar() {
        vb4.txtCodIngr.setText("Codigo Inreso: ");
        vb4.txtNomCliente.setText("Nombre Cliente: ");
        vb4.txtMarcaEquipo.setText("Marca Equipo: ");
        vb4.txtModeloEquipo.setText("Modelo Equipo: ");
        vb4.txtFechaIng.setText("Fecha Ingreso: ");

        Toast.makeText(getContext(), "Registro eliminado exitosamente", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void errorEliminar() {
        Toast.makeText(getContext(), "UPS! No se pudo eliminar Registro", Toast.LENGTH_SHORT).show();

    }
}