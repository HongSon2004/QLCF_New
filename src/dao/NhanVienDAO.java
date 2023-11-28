package dao;

import utils.XJdbc;
import entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends CofeDAO<NhanVien, String>{
    public void insert(NhanVien model){
        String sql="INSERT INTO NhanVien (MaNV, TenNV, SDT, GioiTinh, PhanQuyen) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaNV(), 
                model.getTenNV(), 
                model.getSDT(), 
                model.isGioitinh(),
                model.isPhanquyen());
    }
    
    public void update(NhanVien model){
        String sql="UPDATE NhanVien SET TenNV=?, SDT=?, GioiTinh=?, PhanQuyen=? WHERE MaNV=?";
        XJdbc.update(sql, 
                model.getMaNV(), 
                model.getTenNV(), 
                model.getSDT(), 
                model.isGioitinh(),
                model.isPhanquyen());
    }
    
    public void delete(String MaNV){
        String sql="DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, MaNV);
    }
    
    public List<NhanVien> selectAll(){
        String sql="SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }
    
    public NhanVien selectById(String manv){
        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NhanVien> selectBySql(String sql, Object...args){
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NhanVien entity=new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setTenNV(rs.getString("TenNV"));
                    entity.setSDT(rs.getString("SDT"));
                    entity.setGioitinh(rs.getBoolean("GioiTinh"));
                    entity.setPhanquyen(rs.getBoolean("PhanQuyen"));
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
    public List<NhanVien> selectByKeyword(String keyword){
        String sql="SELECT * FROM NhanVien WHERE TenNV LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
