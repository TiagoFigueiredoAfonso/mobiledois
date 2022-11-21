package com.example.mobiledois;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class MainActivityApiLista extends AppCompatActivity {

    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api_lista);

        textView5 = findViewById(R.id.textView5);
        String cep = "79102190";
        new DownloadJSonAsyncTask().execute("https://api.postmon.com.br/v1/cep/79102190");

    }

    public  class DownloadJSonAsyncTask extends AsyncTask<String, Void, Endereco>{

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivityApiLista.this, "Aguarda", "Fazendo Dowload");
        }

        @Override
        protected Endereco doInBackground(String... params) {

            String urlString = params[0];
            URL url;

            try {
                url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(15000000);
                httpURLConnection.connect();

                InputStream resposta = httpURLConnection.getInputStream();
                String text = new Scanner(resposta).useDelimiter("\\A").next();

                if (text != null) {
                    Endereco endereco = getDados(text);
                    return endereco;
                }else
                    return null;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Endereco endereco) {
            dialog.dismiss();

            textView5.setText(endereco.toString());
            super.onPostExecute(endereco);
        }

    }

    private Endereco getDados(String text) {

        Endereco endereco = new Endereco();

        try {
            JSONObject jsonObjectItem = new JSONObject(text);

            endereco.setComplemento(jsonObjectItem.getString("complemento"));
            endereco.setBairro(jsonObjectItem.getString("bairro"));
            endereco.setCidade(jsonObjectItem.getString("cidade"));
            endereco.setLogradouro(jsonObjectItem.getString("logradouro"));
            endereco.setArea_km_estado(jsonObjectItem.getJSONObject("estado_info").
                    getString("area_km2"));
            endereco.setCod_ibge_estado(jsonObjectItem.getJSONObject("estado_info").
                    getString("codigo_ibge"));
            endereco.setNome(jsonObjectItem.getJSONObject("estado_info").
                    getString("nome"));
            endereco.setCep(jsonObjectItem.getString("cep"));

            endereco.setArea_km_cidade(jsonObjectItem.getJSONObject("cidade_info").
                    getString("area_km2"));
            endereco.setCod_ibge_cidade(jsonObjectItem.getJSONObject("cidade_info").
                    getString("codigo_ibge"));
            endereco.setSigla_estado(jsonObjectItem.getString("estado"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return endereco;
    }
}