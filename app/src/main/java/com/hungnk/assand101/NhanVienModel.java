package com.hungnk.assand101;

import java.io.Serializable;

public class NhanVienModel implements Serializable {
    private String maNV;
    private String hoTenNV;
    private String tenPhongBan;

    public NhanVienModel(String maNV, String hoTenNV, String tenPhongBan) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.tenPhongBan = tenPhongBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
