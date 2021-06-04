package com.example.appdual.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdual.R;
import com.example.appdual.fetchData;

public class FBuscar extends Fragment {

    RecyclerView recyclerView;
    String nom[];

    public FBuscar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fbuscar = inflater.inflate(R.layout.fragment_f_buscar, container, false);

        Button btnSearch = fbuscar.findViewById(R.id.BPelisSisi);
        btnSearch.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
                showTxtSearch();
            }
        });

            recyclerView = fbuscar.findViewById(R.id.recyclerview);
            nom = getResources().getStringArray(R.array.Noms);

        return fbuscar;
    }

    public void showTxtSearch(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Busca Pelicula");

        final EditText input = new EditText(getActivity());
        input.setHint("Film");
        alert.setView(input);

        alert.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String movie = input.getText().toString();
                prueba(movie);
            }
        });

        alert.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }

    public void prueba(String text) {
        fetchData process = new fetchData(text,getActivity());
        process.execute();
    }
}