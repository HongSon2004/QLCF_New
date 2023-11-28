package entity;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String sdt;
    private boolean gioitinh;
    private boolean phanquyen = false;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSDT() {
        return sdt;
    }

    public void setSDT(String sdt) {
        this.sdt = sdt;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public boolean isPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(boolean phanquyen) {
        this.phanquyen = phanquyen;
    }


}
