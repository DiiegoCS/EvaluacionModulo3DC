package com.diegocampos.evaluacionmodulo3dc.Interactor.manejoListview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diegocampos.evaluacionmodulo3dc.R;

import java.util.List;

public class AdaptadorDatos extends BaseAdapter {

    Context context;
    List<RegistroEquipoDatos> lst;

    public AdaptadorDatos(Context context, List<RegistroEquipoDatos> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {return lst.size(); }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textViewCodigo;
        TextView textViewNombre;
        TextView textViewFecha;


        RegistroEquipoDatos listadatos = lst.get(i);

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.formato_registro_equipos, null);
        }

        textViewCodigo = view.findViewById(R.id.txtCodigolst);
        textViewNombre = view.findViewById(R.id.txtNombrelst);
        textViewFecha = view.findViewById(R.id.txtFechalst);


        textViewCodigo.setText(listadatos.getCodigo());
        textViewNombre.setText(listadatos.getNombre());
        textViewFecha.setText(listadatos.getFecha());



        return view;
    }
}
