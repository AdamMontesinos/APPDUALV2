package com.example.appdual.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdual.R;
import com.example.appdual.fetchData;

public class FLlista extends Fragment {

    public FLlista() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fllista = inflater.inflate(R.layout.fragment_f_llista, container, false);

        Button btnSearch = fllista.findViewById(R.id.BTNLlista);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTxtSearch();
            }
        });

        return fllista;
    }

    public void showTxtSearch(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Nombra la lista");

        final EditText input = new EditText(getActivity());
        input.setHint("Favoritos");
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
