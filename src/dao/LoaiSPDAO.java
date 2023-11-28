/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.LoaiSP;
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
public class LoaiSPDAO extends CofeDAO<LoaiSP, String>{

    @Override
    public void insert(LoaiSP entity) {
        String sql = "insert into Loai(SPLoai) values(?)";
        XJdbc.update(sql, entity.getSPLoai());
    }

    @Override
    public void update(LoaiSP entity) {
        String sql = "update Loai set SPLoai=?";
        XJdbc.update(sql, entity.getSPLoai());
    }

    @Override
    public void delete(String id) {
        
    }

    @Override
    public LoaiSP selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LoaiSP> selectAll() {
         String sql="SELECT * FROM Loai";
        return this.selectBySql(sql);
    }

    @Override
    protected List<LoaiSP> selectBySql(String sql, Object... args) {
         List<LoaiSP> list=new ArrayList<LoaiSP>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    LoaiSP entity = new LoaiSP();
                    entity.setSPLoai(rs.getString("SPLoai"));
                    
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

   
    
}
