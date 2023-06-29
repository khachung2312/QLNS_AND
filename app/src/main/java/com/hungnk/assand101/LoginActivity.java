package com.hungnk.assand101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String Tag = "dangnhap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtname = findViewById(R.id.edt_username);
        EditText txtpass = findViewById(R.id.edt_password);
        Button btndangnhap = findViewById(R.id.btn_login);
        Button btndangki = findViewById(R.id.btn_register);
        CheckBox cbxGhiTk = findViewById(R.id.cbx_ghiNhoTK);



        ActivityResultLauncher dangnhap = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent i = result.getData();
                            Bundle acc = i.getExtras();
                            String u = acc.getString("username");
                            String p = acc.getString("password");
                            txtname.setText(u);
                            txtpass.setText(p);
                        }
                    }
                }
        );
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences lay = getSharedPreferences("abc", MODE_PRIVATE);
                String u = lay.getString("Username", "");
                String p = lay.getString("password", "");
                if (txtname.getText().toString().equals(u) && txtpass.getText().toString().equals(p)) {
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}