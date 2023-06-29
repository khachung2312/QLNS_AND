package com.hungnk.assand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout btnPhongBan, btnNhanVien, btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnPhongBan = findViewById(R.id.btn_phongban);
        btnNhanVien = findViewById(R.id.btn_nhanvien);
        btnThoat = findViewById(R.id.btn_thoat);

        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPhongBanItent = new Intent(HomeActivity.this, PhongBanActivity.class);
                startActivity(toPhongBanItent);
                finish();
            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toNhanVienItent = new Intent(HomeActivity.this, NhanVienActivity.class);
                startActivity(toNhanVienItent);
                finish();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                finish();
            }
        });
    }
}