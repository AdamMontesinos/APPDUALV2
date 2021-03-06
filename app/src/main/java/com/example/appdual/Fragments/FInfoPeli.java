package com.example.appdual.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appdual.Class.Film;
import com.example.appdual.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.appdual.R.drawable.ic_baseline_star_outline_24;
import static com.example.appdual.R.drawable.ic_baseline_star_rate_24;

public class FInfoPeli extends Fragment {

    ImageView PortadaPeliGran;
    TextView myText2;
    TextView myRating2;
    TextView myDate;
    TextView myOverview2;

    boolean comprobacio = true;

    protected ImageButton guardar;
    protected ArrayList<Film> PelisSubidas;
    protected ArrayList<String> ArrayListas;

    FirebaseDatabase db;
    DatabaseReference ref;

    public FInfoPeli() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View infopeli = inflater.inflate(R.layout.fragment_f_info_peli, container, false);

            Film peli = (Film) this.getArguments().getSerializable("peli");


        guardar = infopeli.findViewById(R.id.btnguardar2);

            myText2 = infopeli.findViewById(R.id.nomView);
            myRating2 = infopeli.findViewById(R.id.rating);
            myDate = infopeli.findViewById(R.id.release);
            myOverview2 = infopeli.findViewById(R.id.overview);
            PortadaPeliGran = infopeli.findViewById(R.id.portadaView);

            myText2.setText(peli.getNombrepeli());
            myRating2.setText(peli.getRating());
            myDate.setText(peli.getReleaseDate());
            myOverview2.setText(peli.getOverview());

            String urlImg = "https://image.tmdb.org/t/p/original/" + peli.getBackdrop_path();
            Picasso.get().load(urlImg).into(PortadaPeliGran);



            //buscar si en la bbdd hi ha l'usuari, si hi ??s

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
            if (acct != null) {
                String personID = acct.getId();

                existeUser(personID);
                ref = ref.child(personID);

                Log.i("Test", personID);
            }

          //  existePeliSerie(peli);

            guardar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (comprobacio) {
                        ref.child(Integer.toString(peli.getId())).removeValue();
                        guardar.setImageDrawable(getResources().getDrawable(ic_baseline_star_outline_24));
                        comprobacio = false;
                    } else {
                        ref.child(Integer.toString(peli.getId())).setValue(peli);
                        guardar.setImageDrawable(getResources().getDrawable(ic_baseline_star_rate_24));
                        comprobacio = true;
                    }
                }
            });

            ArrayListas = new ArrayList<String>();

        // ref child(Llistes)
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("logTest ", "" + dataSnapshot.getChildrenCount());

                    ArrayListas.clear();

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        ArrayListas.add(postSnapshot.getValue().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.i("logTest", "Failed to read value.", error.toException());
                }
            });


        Spinner spinner = infopeli.findViewById(R.id.anadirlistas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ArrayListas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


/*
        ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("logTest ", "" + dataSnapshot.getChildrenCount());

                    PelisSubidas.clear();

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Film pelicula = postSnapshot.getValue(Film.class);
                        PelisSubidas.add(pelicula);
                        Log.i("logTest", pelicula.getNombrepeli());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.i("logTest", "Failed to read value.", error.toException());
                }
            });

        */

            return infopeli;
        }
    public void existePeliSerie (Film peli){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Films");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(Integer.toString(peli.getId()))) {
                    comprobacio = false;
                } else {
                    comprobacio = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void existeUser (String user){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.hasChild(user)) {
                    ref.child(user).setValue("");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}