/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hongs
 */
public class SanPham {
    private String spMa, spLoai, spTen, spDonVi;
    Double gia;

    public SanPham() {
    }

    public SanPham(String spMa, String spLoai, String spTen, String spDonVi, Double gia) {
        this.spMa = spMa;
        this.spLoai = spLoai;
        this.spTen = spTen;
        this.spDonVi = spDonVi;
        this.gia = gia;
    }

    public String getSpMa() {
        return spMa;
    }

    public void setSpMa(String spMa) {
        this.spMa = spMa;
    }

    public String getSpLoai() {
        return spLoai;
    }

    public void setSpLoai(String spLoai) {
        this.spLoai = spLoai;
    }

    public String getSpTen() {
        return spTen;
    }

    public void setSpTen(String spTen) {
        this.spTen = spTen;
    }

    public String getSpDonVi() {
        return spDonVi;
    }

    public void setSpDonVi(String spDonVi) {
        this.spDonVi = spDonVi;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
    
}
