package com.example.mobiledois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityRecuperaSenha extends AppCompatActivity {

    EditText editEmailRecupera;
    Button btRecuperaSenha;
    FirebaseAuth auth;
    ProgressBar progRecuperaSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recupera_senha);

        editEmailRecupera = findViewById(R.id.editEmailRecupera);
        btRecuperaSenha = findViewById(R.id.btRecuperaSenha);
        progRecuperaSenha = findViewById(R.id.progRecuperaSenha);
        auth = FirebaseAuth.getInstance();

        progRecuperaSenha.setVisibility(View.VISIBLE);
        progRecuperaSenha.setVisibility(View.GONE);

        btRecuperaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailRecupera.getText().toString();

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progRecuperaSenha.setVisibility(View.VISIBLE);
                            Toast.makeText(getBaseContext(), "Enviado com Sucesso!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(
                                    MainActivityRecuperaSenha.this,
                                    MainActivity.class

                            );
                            startActivity(i);
                        }
                    }
                });
            }

        });



    }
}