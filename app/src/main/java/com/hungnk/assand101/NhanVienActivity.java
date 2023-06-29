package com.hungnk.assand101;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NhanVienActivity extends AppCompatActivity {

    ArrayList<NhanVienModel> arrayNhanVien = new ArrayList<>();
    AdapterNV adapterNV;
    int index;

    ArrayList<NhanVienModel> arrayNhanVienTam = new ArrayList<>();

    ActivityResultLauncher<Intent> updateNV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        NhanVienModel nhanVienModel = (NhanVienModel) data.getSerializableExtra("nhanvien");
                        arrayNhanVien.set(index, nhanVienModel);
                        luuDuLieu();
                        adapterNV.notifyDataSetChanged();
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> addNV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        NhanVienModel nhanVienModel = (NhanVienModel) data.getSerializableExtra("nhanvien");
                        arrayNhanVien.add(nhanVienModel);
                        luuDuLieu();
                        adapterNV.notifyDataSetChanged();
                    }
                }
            }
    );

    private String FILE_NAME = "nv.txt";

    private void luuDuLieu () {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(arrayNhanVien);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {

        }

    }

    private void docDuLieu () {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayNhanVien = (ArrayList<NhanVienModel>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        ListView lvNhanVien = findViewById(R.id.lv_NhanVien);

        docDuLieu();

        if (arrayNhanVien.size() == 0) {
            arrayNhanVien.add(new NhanVienModel("NV01", "Anh", "Hành Chính"));
            arrayNhanVien.add(new NhanVienModel("NV02", "Trần Văn Nam", "Nhân Sự"));
            arrayNhanVien.add(new NhanVienModel("NV03", "Nguyễn Thị Hà", "Đào Tạo"));
            arrayNhanVien.add(new NhanVienModel("NV04", "Vũ Ngọc Hoàng", "Đào Tạo"));
            arrayNhanVien.add(new NhanVienModel("NV05", "Nguyễn Thị Mơ", "Nhân Sự"));
        }

        adapterNV = new AdapterNV(NhanVienActivity.this, arrayNhanVien);
        lvNhanVien.setAdapter(adapterNV);

        EditText edtSearch = findViewById(R.id.edt_Search);
        ImageButton btnSearch = findViewById(R.id.btn_Search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuKhoa = edtSearch.getText().toString().toLowerCase();
                arrayNhanVienTam.clear();

                for (NhanVienModel nv : arrayNhanVien) {
                    if (nv.getHoTenNV().toLowerCase().contains(tuKhoa)) {
                        arrayNhanVienTam.add(nv);
                    }
                }

                adapterNV = new AdapterNV(NhanVienActivity.this, arrayNhanVienTam);
                lvNhanVien.setAdapter(adapterNV);
            }
        });
    }





    private class AdapterNV extends BaseAdapter {

        Activity activity;
        ArrayList<NhanVienModel> list;

        public AdapterNV(NhanVienActivity activity, ArrayList<NhanVienModel> list) {
            this.activity = activity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_nhanvien, parent, false);

            NhanVienModel nhanVienModel = list.get(i);

            TextView tvMaNV = view.findViewById(R.id.tv_maNV);
            TextView tvTenNV = view.findViewById(R.id.tv_tenNV);
            TextView tvTenPB = view.findViewById(R.id.tv_phongBan);
            ImageButton btnRemove = view.findViewById(R.id.btn_remove);
            ImageButton btnEdit = view.findViewById(R.id.btn_edit);
            ImageButton btnGoBack = findViewById(R.id.btnGoBack);

            btnGoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


            Button btnAddNV = findViewById(R.id.btn_addNV);

            tvMaNV.setText(nhanVienModel.getMaNV());
            tvTenNV.setText(nhanVienModel.getHoTenNV());
            tvTenPB.setText(nhanVienModel.getTenPhongBan());




            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = i;
                    Intent intent = new Intent(NhanVienActivity.this, UpdateNhanVienActivity.class);
                    intent.putExtra("NhanViens", arrayNhanVien.get(i));
                    updateNV.launch(intent);

                }
            });

            btnAddNV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NhanVienActivity.this, AddNhanVienActivity.class);
                    addNV.launch(intent);
                }
            });


            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NhanVienActivity.this);
                    builder.setMessage("Xác nhận xoá ?");

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            arrayNhanVien.remove(i);
                            adapterNV.notifyDataSetChanged();
                            luuDuLieu();
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });



            return view;
        }
    }

}