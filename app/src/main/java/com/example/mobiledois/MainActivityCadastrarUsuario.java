package com.example.mobiledois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityCadastrarUsuario extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private EditText nomePessoa, emailPessoa;
    private Button btnSalvarPessoa, btnListar, btnApi, btnApi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastrar_usuario);

        nomePessoa = findViewById(R.id.nomePessoa);
        emailPessoa = findViewById(R.id.emailPessoa);
        btnSalvarPessoa = findViewById(R.id.btnSalvarPessoa);
        btnListar = findViewById(R.id.btnListar);
        btnApi = findViewById(R.id.btnApi);
        btnApi2 = findViewById(R.id.btnApi2);

        Pessoa p = new Pessoa();

        btnSalvarPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference pessoas = referencia.child("pessoas");
                p.setNome(String.valueOf(nomePessoa.getText()));
                p.setEmail(String.valueOf(emailPessoa.getText()));
                pessoas.push().setValue(p);
                limpar();
                Toast.makeText(getApplicationContext(),"Cadastrado com Sucesso!",Toast.LENGTH_LONG).show();

            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityCadastrarUsuario.this, MainActivityLogado.class);
                startActivity(i);
            }
        });

        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityCadastrarUsuario.this, MainActivityApiLista.class);
                startActivity(i);

            }
        });

        btnApi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityCadastrarUsuario.this, MainActivityApi2Lista.class);
                startActivity(i);
            }
        });

    }
    public void limpar(){
        nomePessoa.setText("");
        emailPessoa.setText("");

    }


}