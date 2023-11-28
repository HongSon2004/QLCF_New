/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author hongs
 */
public class HoaDon {
       String ban, hoTenNV, ngay, thoigian, tenSP[];
    int tongTien[], tienKhach, tienThua, soLuong[];
    int tienTongCong;

    public HoaDon() {
    }

    public HoaDon(String ban, String hoTenNV, String ngay, String thoigian, String[] tenSP, int[] tongTien, int tienKhach, int tienThua, int[] soLuong, int tienTongCong) {
        this.ban = ban;
        this.hoTenNV = hoTenNV;
        this.ngay = ngay;
        this.thoigian = thoigian;
        this.tenSP = tenSP;
        this.tongTien = tongTien;
        this.tienKhach = tienKhach;
        this.tienThua = tienThua;
        this.soLuong = soLuong;
        this.tienTongCong = tienTongCong;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String[] getTenSP() {
        return tenSP;
    }

    public void setTenSP(String[] tenSP) {
        this.tenSP = tenSP;
    }

    public int[] getTongTien() {
        return tongTien;
    }

    public void setTongTien(int[] tongTien) {
        this.tongTien = tongTien;
    }

    public int getTienKhach() {
        return tienKhach;
    }

    public void setTienKhach(int tienKhach) {
        this.tienKhach = tienKhach;
    }

    public int getTienThua() {
        return tienThua;
    }

    public void setTienThua(int tienThua) {
        this.tienThua = tienThua;
    }

    public int[] getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int[] soLuong) {
        this.soLuong = soLuong;
    }

    public int getTienTongCong() {
        return tienTongCong;
    }

    public void setTienTongCong(int tienTongCong) {
        this.tienTongCong = tienTongCong;
    }
    
        
}
