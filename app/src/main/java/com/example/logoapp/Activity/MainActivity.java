package com.example.logoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logoapp.Logo_Class;
import com.example.logoapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnplay = findViewById(R.id.btnplay);

        if (Logo_Class.LOGO_CLASS.isEmpty()) {
            Logo_Class logo_class = new Logo_Class();
        }

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Level_Activity.class);
                startActivity(intent);
            }
        });
    }
}