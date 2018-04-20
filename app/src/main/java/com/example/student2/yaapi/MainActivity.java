package com.example.student2.yaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        dtext=(EditText)findViewById(R.id.text);


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
                TextView tv = (TextView) findViewById(R.id.translate);
                tv.setText(translate.getText().get(0));

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
