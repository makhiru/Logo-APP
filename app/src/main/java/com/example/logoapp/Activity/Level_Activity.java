package com.example.logoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.logoapp.Adapter.Level_Adapter;
import com.example.logoapp.Logo_Class;
import com.example.logoapp.R;

public class Level_Activity extends AppCompatActivity {

    ImageView imgback;
    RecyclerView recyclerView;
    Level_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        imgback = findViewById(R.id.imgback);
        recyclerView = findViewById(R.id.recyclerview);

        adapter = new Level_Adapter(Level_Activity.this, Logo_Class.LOGO_CLASS);
        recyclerView.setLayoutManager(new LinearLayoutManager(Level_Activity.this));

        recyclerView.setAdapter(adapter);

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