package com.e.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Listener.IFirebaseLoadDOne;
import Model.Movie;
import Transformer.DepthPageTransformer;

public class MainActivity extends AppCompatActivity implements IFirebaseLoadDOne {
        ViewPager viewPager;
        MyAdapter adapter;
        DatabaseReference movies;
        IFirebaseLoadDOne iFirebaseLoadDOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies= FirebaseDatabase.getInstance().getReference("Movies");
        iFirebaseLoadDOne=this;
        loadMovie();
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true,new DepthPageTransformer());
    }

    private void loadMovie() {
        movies.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Movie> movieList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot movieSnapShot:dataSnapshot.getChildren())
                    movieList.add(movieSnapShot.getValue(Movie.class));
                iFirebaseLoadDOne.onFirebaseLoadSuccess(movieList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            iFirebaseLoadDOne.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Movie> movieList) {
     adapter=new MyAdapter(this,movieList);
     viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String Message) {
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
    }
}
