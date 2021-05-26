package com.example.appdual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.appdual.Fragments.FBuscar;
import com.example.appdual.Fragments.FLlista;
import com.example.appdual.Fragments.FSocial;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Menu extends AppCompatActivity {

    FBuscar fbuscar = new FBuscar();
    FLlista fllista = new FLlista();
    FSocial fsocial = new FSocial();

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton signout = findViewById(R.id.signoutgoogle);

        Button btn1 = (Button) findViewById(R.id.BPelis);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarFragment(fbuscar);
            }
        });

        Button btn2 = (Button) findViewById(R.id.BSocial);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarFragment(fllista);
            }
        });

        Button btn3 = (Button) findViewById(R.id.BPerfil);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarFragment(fsocial);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        mGoogleSignInClient.signOut();
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });


    }

    public void CargarFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.PantallaFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}



