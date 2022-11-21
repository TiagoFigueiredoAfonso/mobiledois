package com.example.mobiledois;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivityApi2Lista extends  AppCompatActivity{ //AppCompatActivity ListActivity


    SearchView searchView2;
    ListView listViewApi;
   // ArrayList<String> arrayListItensCopia;
   ArrayList<Usuario> usuarioArrayList = new ArrayList<>();
    ArrayList<Usuario> arrayListItensCopia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api2_lista);

        listViewApi = findViewById(R.id.listViewApi);
        searchView2 = findViewById(R.id.searchView2);


        new DownloadJSonAsyncTask().execute("https://jsonplaceholder.typicode.com/users");
    }

    public class DownloadJSonAsyncTask extends AsyncTask<String, Void, ArrayList<Usuario>> {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivityApi2Lista.this,
                    "Aguarde", "Fazendo download das " +
                            "informações...");
        }

        @Override
        protected ArrayList<Usuario> doInBackground(String... strings) {
            String urlString = strings[0];
            URL url;

            try {
                url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(15000000);
                httpURLConnection.connect();

                InputStream resposta = httpURLConnection.getInputStream();
                String texto = new Scanner(resposta).useDelimiter("\\A").next();

                if (texto != null) {
                    ArrayList<Usuario> usuarioArrayList = getDados(texto);
                    return usuarioArrayList;

                } else
                    return null;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        public ArrayList<Usuario> getDados(String texto) {



            try {
                JSONArray jsonArray = new JSONArray(texto);

                for (int i = 0; jsonArray.length() > i; i++) {
                    JSONObject usuarioJSObj = jsonArray.getJSONObject(i);

                    Usuario usuario = new Usuario();
                    usuario.setId(usuarioJSObj.getString("id"));
                    usuario.setNome(usuarioJSObj.getString("name"));
                    usuario.setSobrenome(usuarioJSObj.getString("username"));
                    usuario.setEmail(usuarioJSObj.getString("email"));

                    usuario.setEndereco_rua(usuarioJSObj.getJSONObject("address").
                            getString("street"));
                    usuario.setEndereco_complemento(usuarioJSObj.getJSONObject("address").
                            getString("suite"));
                    usuario.setEndereco_cidade(usuarioJSObj.getJSONObject("address").
                            getString("city"));
                    usuario.setEndereco_cep(usuarioJSObj.getJSONObject("address").
                            getString("zipcode"));
                    usuario.setGeo_lat(usuarioJSObj.getJSONObject("address").
                            getJSONObject("geo").getString("lat"));
                    usuario.setGeo_lon(usuarioJSObj.getJSONObject("address").
                            getJSONObject("geo").getString("lng"));

                    usuario.setTel(usuarioJSObj.getString("phone"));
                    usuario.setSite(usuarioJSObj.getString("website"));
                    usuario.setEmpresa_nome(usuarioJSObj.getJSONObject("company").
                            getString("name"));
                    usuario.setEmpresa_slogan(usuarioJSObj.getJSONObject("company").
                            getString("catchPhrase"));
                    usuario.setEmpresa_bs(usuarioJSObj.getJSONObject("company").
                            getString("bs"));

                    usuarioArrayList.add(usuario);
                    arrayListItensCopia.add(usuario); // copia



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return usuarioArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Usuario> usuarios) {
            super.onPostExecute(usuarios);


            dialog.dismiss();

            ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(
                    MainActivityApi2Lista.this,
                    android.R.layout.simple_list_item_1,
                    usuarios
            );
            //listView.setAdapter(meuAdapter);
            listViewApi.setAdapter(adapter);

            //search
            //expande item
            searchView2.setIconified(false);
            //fecha o teclado
            searchView2.clearFocus();

            searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                //este método é chamado no submit
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                //este método é chamado ao digitar
                @Override
                public boolean onQueryTextChange(String s) {
                    //forma 1: utilizar filter pronto (não busca letras dentro de palavras)
                    //MainActivity2.this.meuAdapter.getFilter().filter(s);

                    //forma 2: fazer a busca manualmente (busca letras dentro de palavras)
                    //método para atualizar itens do arraylist
                    fazerBusca(s);
                    //atualiza lista com arrayList alterado
                    adapter.notifyDataSetChanged();

                    return false;
                }
            });


        }
    }

    public void fazerBusca(String s) {
        //limpando o array
        usuarioArrayList.clear();

        s = s.toLowerCase();

        //percorre array de cópia com todos os itens e busca
        for (Usuario item : arrayListItensCopia) {
            if (item.toString().contains(s)) {
                //se item encontrado, adicionada de volta no array -
                usuarioArrayList.add(item);
            }
        }
    }
}

