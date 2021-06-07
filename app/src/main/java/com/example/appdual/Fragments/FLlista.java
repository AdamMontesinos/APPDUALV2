package com.example.appdual.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdual.R;
import com.example.appdual.fetchData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FLlista extends Fragment {

    FirebaseDatabase db;
    DatabaseReference ref;
    String personID;
    ArrayList<String> ArrayListas;

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            personID = acct.getId();
            existeUser(personID);

        }

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
                String nomLlista = input.getText().toString();
                String lista = nomLlista;
                ArrayListas = new ArrayList<String>();

                //comprovar si existeix el child Llistes
                // si no existeix s'ha de crear i afegir la llista
                // si existeix s'ha d'afegir la llista

                ref = ref.child("Listas");
                ref.child(lista).setValue("");
                ArrayListas.add(lista);
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

    public void existeUser (String user){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.hasChild(user)) {
                    Log.i("test", "hola");
                    ref.child(user).setValue("");
                    ref = ref.child(personID);
                }else{
                    ref = ref.child(personID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
