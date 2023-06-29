package com.hungnk.assand101;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateNhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhan_vien);
        NhanVienModel nhanVienModel;

        EditText edtMaNV = findViewById(R.id.edt_maNV);
        EditText edtTenNV = findViewById(R.id.edt_tenNV);
        EditText edtTenPB = findViewById(R.id.edt_tenPB);
        Button btnLuu = findViewById(R.id.btn_luu);

        Intent intent = getIntent();
        nhanVienModel = (NhanVienModel) intent.getSerializableExtra("NhanViens");

        edtMaNV.setText(nhanVienModel.getMaNV());
        edtTenNV.setText(nhanVienModel.getHoTenNV());
        edtTenPB.setText(nhanVienModel.getTenPhongBan());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maNV = edtMaNV.getText().toString();
                String tenNV = edtTenNV.getText().toString();
                String tenPB = edtTenPB.getText().toString();

                NhanVienModel nhanVienModel = new NhanVienModel(maNV, tenNV, tenPB);

                Intent data = new Intent();
                data.putExtra("nhanvien", nhanVienModel);

                setResult(RESULT_OK, data);
                finish();

            }
        });

    }
}