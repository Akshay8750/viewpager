package com.e.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatActivity extends AppCompatActivity {
    private Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        btn1=findViewById(R.id.btn_one);
        btn2=findViewById(R.id.btn_two);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.S="shayari1";
                Intent intent=new Intent(CatActivity.this,ShayariActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.S="shayari2";
                Intent intent=new Intent(CatActivity.this,ShayariActivity.class);
                startActivity(intent);
            }
        });
    }
}
