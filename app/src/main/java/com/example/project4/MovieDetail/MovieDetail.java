package com.example.project4.MovieDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project4.R;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        ImageView img=findViewById(R.id.img);
        TextView movie_name=findViewById(R.id.m_name);
        TextView movie_type=findViewById(R.id.m_type);
        TextView movie_year=findViewById(R.id.m_year);
        TextView movie_review=findViewById(R.id.imdb);
        //Toast.makeText(this, sharedPreferences.getString("Title", "default"), Toast.LENGTH_LONG).show();
        movie_name.setText(sharedPreferences.getString("Title", "default"));
        movie_type.setText(sharedPreferences.getString("Type", "default"));
        movie_year.setText(sharedPreferences.getString("Year", "default"));
        //movie_review.setText(sharedPreferences.getString("Rating", "default"));
        Glide.with(this)
                .load(sharedPreferences.getString("Poster", "default"))
                .placeholder(R.drawable.splash)
                .into(img);

    }
}