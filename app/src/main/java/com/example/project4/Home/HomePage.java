package com.example.project4.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4.MovieDetail.MovieDetail;
import com.example.project4.R;
import com.example.project4.RecyclerViewAdapter;
import com.example.project4.Retrofit.IPostApi;
import com.example.project4.Retrofit.Response;
import com.example.project4.Retrofit.RetrofitBuilder;
import com.example.project4.Retrofit.SearchItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HomePage extends AppCompatActivity implements RecyclerViewAdapter.UserInterface {
    List<SearchItem> userDataList = new ArrayList<>();
    boolean isToggle;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ImageView noImage=(ImageView)findViewById(R.id.noImg);
        RecyclerView recyclerView = findViewById(R.id.rv);
        SearchView searchInput = (SearchView) findViewById(R.id.searchField);
        TextView noResult=(TextView)findViewById(R.id.noResult);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostApi ipostApi = retrofit.create(IPostApi.class);
        ImageView toggle_button = findViewById(R.id.toggle);
        Call<Response> responses = ipostApi.getPosts();
        responses.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                userDataList = response.body().getSearch();
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userDataList, HomePage.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


        toggle_button.setOnClickListener(view -> {
                    if (isToggle) {
                        isToggle=true;

                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userDataList, HomePage.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                        //recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                    else{
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userDataList, HomePage.this);
                        recyclerView.setLayoutManager(new  StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
                        //recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }

                }
        );

            /*
            Call<Response> responses = ipostApi.getPosts();
            responses.enqueue(new Callback<Response>() {
                                  @Override
                                  public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                      userDataList = response.body().getSearch();

                                      RecyclerView recyclerView = findViewById(R.id.rv);
                                      RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userDataList, HomePage.this);
                                      recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
                                      //recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                                      recyclerView.setAdapter(recyclerViewAdapter);
                                  }

                                  @Override
                                  public void onFailure(Call<Response> call, Throwable t) {

                                  }
                              }
            );
        });

*/


        searchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                Retrofit retrofit = RetrofitBuilder.getInstance();
                IPostApi ipostApi = retrofit.create(IPostApi.class);
                Call<Response> responses = ipostApi.getPostsbyQuery(query);
                responses.enqueue(new Callback<Response>() {
                                      @Override
                                      public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                          userDataList = response.body().getSearch();
                                          Toast.makeText(HomePage.this, response.body().getResponse(), Toast.LENGTH_LONG).show();

                                          if(response.body().getResponse().equals("False"))
                                          {
                                              noImage.setVisibility(View.VISIBLE);
                                              noResult.setVisibility(View.VISIBLE);
                                              recyclerView.setVisibility(View.GONE);

                                          }
                                          else
                                          {
                                              RecyclerView recyclerView = findViewById(R.id.rv);
                                              RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userDataList, HomePage.this);
                                              recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
                                              recyclerView.setAdapter(recyclerViewAdapter);
                                          }

                                      }

                                      @Override
                                      public void onFailure(Call<Response> call, Throwable t) {

                                      }
                                  }
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    @Override
    public void onUserClick(SearchItem userData, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView title = findViewById(R.id.name);
        TextView type = findViewById(R.id.type);
        TextView date = findViewById(R.id.year);
        TextView review = findViewById(R.id.imdb);
        ImageView poster = findViewById(R.id.img1);
        editor.putString("Title", userData.getTitle());
        editor.putString("Type", userData.getType());
        editor.putString("Year", userData.getYear());
        editor.putString("Rating", userData.getImdbID());
        editor.putString("Poster", userData.getPoster());
        editor.apply();
        Intent intent = new Intent(HomePage.this, MovieDetail.class);
        startActivity(intent);

    }
}

