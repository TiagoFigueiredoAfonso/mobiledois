package com.example.mobiledois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityLogado extends AppCompatActivity {

    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference(); // busca do banco
    DatabaseReference minhasPessoas = referencia.child("pessoas");

    Button btSair, btnCadastrarUsuario;
    ListView listaPessoas;
    //String [] items = {"item1", "item2", "item3"}; //estático para teste
    ArrayList<String> ps;

    Query pessoasQuery = minhasPessoas.orderByChild("nome");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logado);

        btSair = findViewById(R.id.btSair);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);
        listaPessoas = findViewById(R.id.listaPessoas);

        ps = new ArrayList<>();

        pessoasQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dados : snapshot.getChildren()){
                    Pessoa p = snapshot.getValue(Pessoa.class);
                    Pessoa pes = dados.getValue(Pessoa.class);
                    ps.add(pes.getNome());
                }

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, ps);
                listaPessoas.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro " + error, Toast.LENGTH_LONG).show();
            }
        });



        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                 Toast.makeText(getApplicationContext(),"Até Breve!",Toast.LENGTH_LONG).show();

                Intent i = new Intent(
                       MainActivityLogado.this, MainActivity.class
                );
                startActivity(i);
            }
        });
        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivityLogado.this, MainActivityCadastrarUsuario.class
                );
                startActivity(i);
            }
        });



    }



}