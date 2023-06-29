package com.hungnk.assand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PhongBanActivity extends AppCompatActivity {
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        btnBack = findViewById(R.id.goBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackIntent = new Intent(PhongBanActivity.this, HomeActivity.class);
                startActivity(goBackIntent);
                finish();
            }
        });

    }
}