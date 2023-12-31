package com.hungnk.assand101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText txtname = findViewById(R.id.edt_User);
        EditText txtpass = findViewById(R.id.edt_Pass);
        EditText txtconfirm = findViewById(R.id.edt_Retypepass);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnBack = findViewById(R.id.btn_back);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtname.getText().toString();
                String pass = txtpass.getText().toString();
                String confirm = txtconfirm.getText().toString();
                if (name.equals("") || pass.equals("") || confirm.equals("") || !pass.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Dữ liệu sai", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences data = getSharedPreferences("abc", MODE_PRIVATE);
                    SharedPreferences.Editor ed = data.edit();
                    ed.putString("Username", name);
                    ed.putString("password", pass);
                    ed.apply();
                    Intent i = new Intent();
                    Bundle b = new Bundle();
                    b.putString("username", name);
                    b.putString("password", pass);
                    i.putExtras(b);
                    setResult(RESULT_OK, i);
                    finish();

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}