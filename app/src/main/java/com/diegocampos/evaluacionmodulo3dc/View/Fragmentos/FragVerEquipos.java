package com.diegocampos.evaluacionmodulo3dc.View.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.AdaptadorDatos;
import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposPresenterInterface;
import com.diegocampos.evaluacionmodulo3dc.Interfaces.VerEquipos.FragVerEquiposViewInterface;
import com.diegocampos.evaluacionmodulo3dc.Presenter.FragVerEquiposPresenterImpl;
import com.diegocampos.evaluacionmodulo3dc.R;
import com.diegocampos.evaluacionmodulo3dc.View.actividades.ConsultaRegistroEquipoViewImpl;

import java.util.ArrayList;


public class FragVerEquipos extends Fragment implements FragVerEquiposViewInterface {

    ListView listViewRegistros;
    AdaptadorDatos adaptador;

    FragVerEquiposPresenterInterface presentador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag_ver_equipos, container, false);
        listViewRegistros = v.findViewById(R.id.myListview);

        presentador = new FragVerEquiposPresenterImpl(this);
        presentador.llenarLista(getContext());
        return v;
    }


    @Override
    public void exitoVer(ArrayList<RegistroEquipoDatos> listaRegistros) {

        adaptador = new AdaptadorDatos(getContext(), listaRegistros);
        listViewRegistros.setAdapter(adaptador);
        listViewRegistros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                RegistroEquipoDatos rgd = listaRegistros.get(i);
                Toast.makeText(getContext(), rgd.getCodigo(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), ConsultaRegistroEquipoViewImpl.class);
                intent.putExtra("cod", rgd.getCodigo());
                startActivity(intent);

            }
        });
    }

    @Override
    public void errorVer() {
        Toast.makeText(getContext(), "Aun no hay Registros  ", Toast.LENGTH_SHORT).show();
    }
}