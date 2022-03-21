package com.diegocampos.evaluacionmodulo3dc.View.Fragmentos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diegocampos.evaluacionmodulo3dc.Interactor.ConexionBD;
import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.AdaptadorDatos;
import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview.RegistroEquipoDatos;
import com.diegocampos.evaluacionmodulo3dc.R;

import java.util.ArrayList;


public class FragVerEquipos extends Fragment {

    ListView listViewRegistros;
    AdaptadorDatos adaptador;
    ArrayList<RegistroEquipoDatos> listaRegistros = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag_ver_equipos, container, false);
        listViewRegistros = v.findViewById(R.id.myListview);

        String codigo = "";
        String nombre = "";
        String fecha = "";

        ConexionBD conexion = new ConexionBD(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT codigoIngreso,nombre,fechaIngreso FROM registroEquipos ORDER BY fechaIngreso DESC", null);


        if (fila.moveToFirst()){
            do {
                codigo = fila.getString(0);
                nombre = fila.getString(1);
                fecha = fila.getString(2);

                    listaRegistros.add(
                            new RegistroEquipoDatos("Código de Ingreso: " + codigo, "Nombre Cliente: " + nombre, "Fecha de Ingreso: " + fecha));

            } while(fila.moveToNext());




            adaptador = new AdaptadorDatos(getContext(), listaRegistros);
            listViewRegistros.setAdapter(adaptador);
            listViewRegistros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    // Obtener el administrador de fragmentos a través de la actividad
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    // Definir una transacción
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // Remplazar el contenido principal por el fragmento
                    fragmentTransaction.replace(R.id.myFrame, new FragConsultaRegistro()).commit();


                    RegistroEquipoDatos rgd = listaRegistros.get(i);
                    Toast.makeText(getContext(), rgd.getNombre(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            Toast.makeText(getContext(), "Aun no hay Registros  ", Toast.LENGTH_SHORT).show();
        }

        fila.close();
        bd.close();


        return v;
    }
}