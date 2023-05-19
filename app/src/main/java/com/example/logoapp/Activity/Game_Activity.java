package com.example.logoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logoapp.Adapter.ViewPagerAdapter;
import com.example.logoapp.R;

public class Game_Activity extends AppCompatActivity {

    ViewPager2 viewPager;
    ViewPagerAdapter adapter;

    ImageView imgback;
    int pos1, pos2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pos1 = getIntent().getIntExtra("pos1", 0);
        pos2 = getIntent().getIntExtra("pos2", 0);

        viewPager = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(Game_Activity.this, pos1, pos2, viewPager, true);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos2);


    }

}