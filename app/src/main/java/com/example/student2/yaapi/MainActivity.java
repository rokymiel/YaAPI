package com.example.student2.yaapi;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText dtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dtext=(EditText)findViewById(R.id.text);
        city();


    }

    void city(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://195.19.44.155/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetCity api= retrofit.create(GetCity.class);
        Call<List<City>> call = api.getCity();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                List<City> listC=  response.body();
                ListView list = (ListView) findViewById(R.id.list);
                ArrayList<String> aList = new ArrayList<String>();
                for (int i=0; i<listC.size();i++){
                    aList.add(listC.get(i).getName() +"12");

                }
                Toast.makeText(getApplicationContext(),listC.get(0).getName(),Toast.LENGTH_LONG).show();


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,aList);
                list.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.d("000",t.toString());
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }

        });

    }



    String apikey="trnsl.1.1.20180420T152910Z.dc75f50e5502a94d.e26d2bc80ca3baa25607b74db0fb0e4cc0df7343";
    void translate(String text, String lang){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TranslationInterface api = retrofit.create(TranslationInterface.class);
        Call<Translate> call = api.getTranslation(text,lang,apikey);
        call.enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {
                Translate translate = response.body();
                //TextView tv = (TextView) findViewById(R.id.translate);
                //tv.setText(translate.getText().get(0));

            }

            @Override
            public void onFailure(Call<Translate> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }

    public void trans(View view) {

        String ntext=dtext.getText().toString();
        translate(ntext,"en-ru");


    }
}
