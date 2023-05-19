package com.example.logoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.logoapp.Adapter.Logo_Adapter;
import com.example.logoapp.Logo_Class;
import com.example.logoapp.R;

public class Base_Level_Activity extends AppCompatActivity {

    ImageView imgback;
    RecyclerView logorecyclerView;
    Logo_Adapter adapter;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_level);

        imgback = findViewById(R.id.imgback);
        logorecyclerView = findViewById(R.id.logorecycler);
        i = getIntent().getIntExtra("i", 0);

        adapter = new Logo_Adapter(Base_Level_Activity.this, Logo_Class.LOGO_CLASS.get(i), i);

        logorecyclerView.setLayoutManager(new GridLayoutManager(Base_Level_Activity.this, 4));
        logorecyclerView.setAdapter(adapter);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}