package com.diegocampos.evaluacionmodulo3dc.View.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.diegocampos.evaluacionmodulo3dc.R;


public class FragEliminarRegistro extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_eliminar_registro, container, false);




        return v;
    }
}