/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NhanVien;
import entity.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.XJdbc;

/**
 *
 * @author hongs
 */
public class SanPhamDAO extends CofeDAO<SanPham, String>{

    @Override
    public void insert(SanPham entity) {
        String sql = "insert into SanPham(MaSP, SPLoai, TenSP, DonVi,GiaSP) values(?,?,?,?,?)";
        XJdbc.update(sql, 
                     entity.getSpMa(),
                     entity.getSpLoai(),
                     entity.getSpTen(),
                     entity.getSpDonVi(),
                     entity.getGia());
    }

    @Override
    public void update(SanPham entity) {
        String sql = "update SanPham set SPLoai = ?, TenSP=?,DonVi=?,GiaSP=? where MaSP=?";
        XJdbc.update(sql,
                entity.getSpLoai(),
                entity.getSpTen(),
                entity.getSpDonVi(),
                entity.getGia(),
                entity.getSpMa());
    }

    @Override
    public void delete(String MaSP) {
        String sql = "delete from SanPham where MaSP = ?";
        XJdbc.update(sql, MaSP);
    }

    @Override
    public SanPham selectById(String MaSP) {
        String sql="SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = this.selectBySql(sql, MaSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<SanPham> selectAll() {
        String sql="SELECT * FROM SanPham";
        return this.selectBySql(sql);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list=new ArrayList<SanPham>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    SanPham entity=new SanPham();
                    entity.setSpMa(rs.getString("MaSP"));
                    entity.setSpLoai(rs.getString("SPLoai"));
                    entity.setSpTen(rs.getString("TenSP"));
                    entity.setSpDonVi(rs.getString("DonVi"));
                    entity.setGia(rs.getDouble("GiaSP"));
                    list.add(entity);
                }
            } 
            finally{
                if(rs != null)
                    rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<SanPham> selectByLoaiSP(String maSP){
        String sql="SELECT * FROM SanPham WHERE MaSP=?";
        return this.selectBySql(sql, maSP);
    }

    public List<SanPham> selectByChuyenDe(String spLoai) {
        String sql="SELECT * FROM SanPham WHERE SPLoai like N";
        return this.selectBySql(sql, spLoai);
    }
    
}
