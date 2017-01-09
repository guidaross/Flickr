package com.example.guilhermedaros.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<FlickrFeed> {

    GridView grid;

    private RecyclerView recyclerView;

    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtSearch = (EditText) findViewById(R.id.et_search);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    EditText edtSearch = (EditText) findViewById(R.id.et_search);

                    if (edtSearch.getText().toString() == "")
                        return true;
                    else {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://api.flickr.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                //.client(httpClient.build())
                                .build();

                        FlickrAPI flickrAPI = retrofit.create(FlickrAPI.class);
                        Call<FlickrFeed> call = flickrAPI.loadPhotos(edtSearch.getText().toString());

                        call.enqueue(MainActivity.this);
                    }
                    return true;
                }

                return false;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                //.client(httpClient.build())
                .build();

        FlickrAPI flickrAPI = retrofit.create(FlickrAPI.class);
        Call<FlickrFeed> call = flickrAPI.loadPhotos();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<FlickrFeed> call, Response<FlickrFeed> response) {

        List<CardImagem> cardImagems = new ArrayList<>();

        for (int i= 0; i < response.body().items.size(); i++){
            cardImagems.add(new CardImagem(response.body().items.get(i).title,
                    response.body().items.get(i).media.m,
                    response.body().items.get(i).published,
                    response.body().items.get(i).author,
                    response.body().items.get(i).tags));
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager llm = new GridLayoutManager(this, 3);


        recyclerView.setLayoutManager(llm);

        final FotoAdapter adapter = new FotoAdapter(this, cardImagems);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                in.putExtra("CardImagem", adapter.getImagem());

                startActivity(in);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<FlickrFeed> call, Throwable t) {
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
        t.printStackTrace();
    }
}
