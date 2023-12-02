/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import dao.LoaiSPDAO;
import dao.SanPhamDAO;
import entity.HoaDon;
import entity.LoaiSP;
import entity.SanPham;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
import java.util.Date;
import ui.DangNhap;

/**
 *
 * @author RAVEN
 */
public class Form_3 extends javax.swing.JPanel {
 private int count = 0;
 private Connection conn ;
    private ResultSet rs = null, rsCheck = null;
    private PreparedStatement ps = null;
    private Statement s = null;
    private boolean them = false, thayDoi = false;
    private DefaultTableModel model = new DefaultTableModel();
    HoaDon[] hoadon = new HoaDon[30];
    private boolean tinh = false;
    private double price;
    private int sttBan;
    private JButton tenban;
    private int dongTrongBangHoaDon;
    /**
     * Creates new form Form_1
     */
    public Form_3() {
        initComponents();
        init();
        
    }
     private void in(String file, String outPutName)   {
        //Tạo thư mục để lưu file outPut.
        String outputLoca = "C:\\Users\\hongs\\OneDrive\\Tài liệu\\NetBeansProjects\\QLCF_MAIN\\New folder";
        File locationOutput = new File(outputLoca);
        if(!locationOutput.exists())
            locationOutput.mkdir();
       
        //FileWriter fw = new FileWriter(output+outPutName+".txt")
        //fw.write(file);
        //FileOutputStream fileOutputStream = new FileOutputStream(output+outPutName+".txt")
        //fileOutputStream.write(file.getBytes());
       
        try {
           
            //Xuất ra file .txt ,khắc phục lỗi tiếng việt.
            File fileDir = new File(outputLoca + outPutName + ".txt");
            try (Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), "UTF8"))) {
                out.append(file);
                out.flush();
                out.close();
            }
           
            JOptionPane.showMessageDialog(null,
                    "In file thành công (" + outPutName + ".txt)");
           
        }
        catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "Lỗi in file !");
            System.out.println(e.getMessage());
        }
    }
    public void init(){
        this.fillCBLoai();
        lblNhanVien.setText(DangNhap.tenNV);
        lblThoiGian.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())));
        lblNgay.setText(String.valueOf(new SimpleDateFormat("EEEE dd/MM/yyyy").format(new java.util.Date())));
        Disabled();
        kiemTraHoaDon();
        model = (DefaultTableModel) bangHoaDon.getModel();
    }
    private void kn() {
        String url = "jdbc:sqlserver://localhost; databaseName=QLCF;username=sa;password=123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại thất bại");
        }
    }
    private void them() {
        int s = (Integer.parseInt(spnSoLuong.getValue().toString()) * Integer.parseInt(lblGia.getText()));
        lblThanhTien.setText(String.valueOf(s));
        String tenSP = cbxTenSanPham.getSelectedItem().toString();
        int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());

        model.addRow(new Object[]{
            tenSP, soLuong, s
        });
    }
    private void doimau() {
        if(hoadon[sttBan] != null) {
            tenban.setBackground(Color.yellow);
        } else tenban.setBackground(null);
    }
    private void luuDL(){
        
            int tongTien = 0;
            dongTrongBangHoaDon = model.getRowCount();
            String[] dsTen = new String[dongTrongBangHoaDon];
            int[] dsSL = new int[dongTrongBangHoaDon];
            int[] dsThanhTien = new int[dongTrongBangHoaDon];

            for (int i = 0; i < dongTrongBangHoaDon; i++) {
                tongTien += Integer.parseInt(model.getValueAt(i, 2).toString());
                dsTen[i] = model.getValueAt(i, 0).toString();
                dsSL[i] = Integer.parseInt(model.getValueAt(i, 1).toString());
                dsThanhTien[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
            }
            lblTongTien.setText(String.valueOf(tongTien));

            hoadon[sttBan] = new HoaDon();

            hoadon[sttBan].setHoTenNV(lblNhanVien.getText());
            hoadon[sttBan].setBan(lblBan.getText());
            hoadon[sttBan].setNgay(lblNgay.getText());
            hoadon[sttBan].setThoigian(lblThoiGian.getText());
            hoadon[sttBan].setTenSP(dsTen);
            hoadon[sttBan].setSoLuong(dsSL);
            hoadon[sttBan].setTongTien(dsThanhTien);
            hoadon[sttBan].setTienTongCong(tongTien);
            
    }
    private String dinhdanghoadon()   {
        Date dt = new Date();
        SimpleDateFormat d = new SimpleDateFormat("hh:mm dd:MM:yyyy");
        String gio = d.format(dt);
        
        String temp = "";
        temp += "         QUÁN CÀ PHÊ - TRÀ SỮA ABC\n\r"
                + "278 Tầm Vu, Hưng Lợi, Ninh Kiều, Cần Thơ\n"
                + "SĐT: 123456789\n\r"
                + "-----------------------------------------\n\r"
                + "            PHIÊU THANH TOÁN\n\r"
                + "Bàn số " + sttBan + "\n"
                + "STT  Tên món                  Số lượng  Thành Tiền\n";
                String[] tenmon = hoadon[sttBan].getTenSP();
                int[] soluong = hoadon[sttBan].getSoLuong();
                int[] thanhtien = hoadon[sttBan].getTongTien();
                for(int i=0;i<hoadon[sttBan].getSoLuong().length ;i++)
                {
                    String tam = String.format("%-6s%-28s%-10s%-15s", i+1,tenmon[i],soluong[i],thanhtien[i]);
                    temp += tam + "\n";
                }
        temp += "-------------------------------------------\n\r"
                + "Tông tiền: " +hoadon[sttBan].getTienTongCong() +"\n"
                + "Số tiền nhận: " +hoadon[sttBan].getTienKhach() + " vnd \n"
                + "Tiền thừa: " +hoadon[sttBan].getTienThua() + " vnd \n"
                + "Ngày " +gio +"\n"
                + "Nhân viên: " +hoadon[sttBan].getHoTenNV() +"\n"
                + "------------------------------------------\n"
                + "           Hẹn gặp lại quí khách\n"
                + "           Xin chân thành cảm ơn!";
        return temp;     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Ban1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblThanhTien = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        tfTienNhanCuaKhach = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        lblTrangThai = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbbLoai = new javax.swing.JComboBox<>();
        cbxTenSanPham = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txttienthua = new javax.swing.JTextField();
        lblThoiGian = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();

        Ban1.setText("Bàn 1");
        Ban1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ban1MouseClicked(evt);
            }
        });
        Ban1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban1ActionPerformed(evt);
            }
        });

        jButton2.setText("Bàn 2");

        jButton3.setText("Bàn 4");

        jButton4.setText("Bàn 3");

        jLabel1.setText("Bill ID -----");

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblThanhTien.setText("-----------------------");

        lblTongTien.setText("---------------------------");

        lblGia.setText("---------------");

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel11.setText("Bàn số: ");

        lblBan.setText("---------");

        tfTienNhanCuaKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTienNhanCuaKhachActionPerformed(evt);
            }
        });
        tfTienNhanCuaKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTienNhanCuaKhachKeyReleased(evt);
            }
        });

        jLabel8.setText("Tiền thừa:");

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setText("Số lượng:");

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        lblTrangThai.setText("--------------");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel10.setText("Họ tên nhân viên:");

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lblNhanVien.setText("-----------------------------------");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel12.setText("Ngày:");

        lblNgay.setText("-----------------------------------");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel14.setText("Thời gian:");

        cbbLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiActionPerformed(evt);
            }
        });

        cbxTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTenSanPhamActionPerformed(evt);
            }
        });

        jLabel4.setText("Giá: ");

        jLabel5.setText("Thành tiền:");

        bangHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm ", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bangHoaDon);

        jLabel6.setText("Tổng tiền:");

        jLabel7.setText("Tiền nhận của khách: ");

        lblThoiGian.setText("----------------------------------");

        btnIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIn.setText("In hóa đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Ban1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 28, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfTienNhanCuaKhach)
                                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnIn)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnThanhToan))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel4))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spnSoLuong)
                                                    .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(34, 34, 34)
                                        .addComponent(btnThem)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXoa)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhanVien)
                            .addComponent(jLabel12)
                            .addComponent(lblNgay)
                            .addComponent(jLabel14)
                            .addComponent(lblThoiGian)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(lblBan))
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblGia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblThanhTien))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(lblBan))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(10, 10, 10)
                                .addComponent(lblNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(11, 11, 11)
                                .addComponent(lblNgay)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel14)
                                .addGap(9, 9, 9)
                                .addComponent(lblThoiGian))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblTongTien))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tfTienNhanCuaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIn)
                            .addComponent(btnThanhToan))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#b29f94"), 0, getHeight(), Color.decode("#603813"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    private void Ban1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban1ActionPerformed
        // TODO add your handling code here:
          sttBan = 1;
        tenban = Ban1;
        lblBan.setText("1");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
        
    }//GEN-LAST:event_Ban1ActionPerformed

    private void Ban1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ban1MouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_Ban1MouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if(tfTienNhanCuaKhach.getText().equals("")) {lblTrangThai.setText("Chưa nhập tiền nhận");}
        if (tinh) {

            //Luu vao CSDL.
            luuThongKe();

            doimau();
            int n = JOptionPane.showConfirmDialog(null,"Bạn có muốn in hóa đơn","In hóa đơn",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(n == JOptionPane.YES_OPTION) {
                btnIn.doClick();

            }
            hoadon[sttBan] = null;
            Refresh();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tfTienNhanCuaKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTienNhanCuaKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTienNhanCuaKhachActionPerformed

    private void tfTienNhanCuaKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTienNhanCuaKhachKeyReleased
        int a = Integer.parseInt(tfTienNhanCuaKhach.getText().toString());
        int b = Integer.parseInt(lblTongTien.getText());
        try {
            if (a < b) {
                tinh = false;
                lblTrangThai.setText("Tiền nhận chưa đủ");
            } else {
                tinh = true;
                lblTrangThai.setText("OK");
                txttienthua.setText(String.valueOf(a - b));
            }
        } catch (Exception e) {
            lblTrangThai.setText("Không hợp lệ");
        }
    }//GEN-LAST:event_tfTienNhanCuaKhachKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {

            them();
            luuDL();
            //            int tongTien = 0;
            //            dongTrongBangHoaDon = model.getRowCount();
            //            String[] dsTen = new String[dongTrongBangHoaDon];
            //            int[] dsSL = new int[dongTrongBangHoaDon];
            //            int[] dsThanhTien = new int[dongTrongBangHoaDon];
            //
            //            for (int i = 0; i < dongTrongBangHoaDon; i++) {
                //                tongTien += Integer.parseInt(model.getValueAt(i, 2).toString());
                //                dsTen[i] = model.getValueAt(i, 0).toString();
                //                dsSL[i] = Integer.parseInt(model.getValueAt(i, 1).toString());
                //                dsThanhTien[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
                //            }
            //            lblTongTien.setText(String.valueOf(tongTien));
            //
            //            hoadon[sttBan] = new ClsHoaDon();
            //
            //            hoadon[sttBan].setHoTenNV(lblNhanVien.getText());
            //            hoadon[sttBan].setBan(lblBan.getText());
            //            hoadon[sttBan].setNgay(lblNgay.getText());
            //            hoadon[sttBan].setThoigian(lblThoiGian.getText());
            //            hoadon[sttBan].setTenSP(dsTen);
            //            hoadon[sttBan].setSoLuong(dsSL);
            //            hoadon[sttBan].setTongTien(dsThanhTien);
            //            hoadon[sttBan].setTienTongCong(tongTien);

            //          hoadon[sttBan].setTienKhach(Integer.parseInt(tfTienNhanCuaKhach.getText()));
            //          hoadon[sttBan].setTienThua(Integer.parseInt(lblTienThua.getText()));

            doimau();
            btnThanhToan.setEnabled(true);
            btnIn.setEnabled(true);
        } catch (Exception e) {
            lblTrangThai.setText("Chưa nhập thông tin gì");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click=JOptionPane.showConfirmDialog(null,
            "Bạn có chắc chắn muốn xóa sản phẩm này không?",
            "Thông báo", 2);

        int idRow = bangHoaDon.getSelectedRow();

        String[] tenmon = hoadon[sttBan].getTenSP();
        int[] soluong = hoadon[sttBan].getSoLuong();
        int[] thanhtien = hoadon[sttBan].getTongTien();

        if(click==JOptionPane.YES_OPTION){
            model.removeRow(idRow);
            luuDL();

            model.setRowCount(0);
            taiLaiDuLieu();
        }
        Refresh();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiActionPerformed
        String sp = cbbLoai.getSelectedItem().toString().trim();
        String sqlchon = "Select * from SanPham where SPLoai like N'" + sp + "'";
        count = 0;
        try {
            kn();
            Statement d = conn.createStatement();
            rs = d.executeQuery(sqlchon);
            cbxTenSanPham.removeAllItems();
            while (rs.next()) {
                cbxTenSanPham.addItem(rs.getString("TenSP"));
            }
            count++;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbLoaiActionPerformed

    private void cbxTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTenSanPhamActionPerformed
        if (count > 0) {
            String sp = cbxTenSanPham.getSelectedItem().toString().trim();
            String sqlchon = "Select GiaSP from SanPham where TenSP like N'%" + sp + "'";
            lblGia.setText("");
            try {
                kn();
                Statement d = conn.createStatement();
                rs = d.executeQuery(sqlchon);
                while (rs.next()) {
                    lblGia.setText(String.valueOf(rs.getInt("GiaSP")));
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cbxTenSanPhamActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        Date dt = new Date();
        SimpleDateFormat d = new SimpleDateFormat("hh-mm-ss dd-MM-yyyy");
        String gio = d.format(dt);
        String nameOut = "BAN"+tenban.getText().trim() +"_("+gio+")".trim();
        System.out.println(nameOut);
        in(dinhdanghoadon(), nameOut);
    }//GEN-LAST:event_btnInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ban1;
    private javax.swing.JTable bangHoaDon;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JComboBox<String> cbxTenSanPham;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTextField tfTienNhanCuaKhach;
    private javax.swing.JTextField txttienthua;
    // End of variables declaration//GEN-END:variables
    LoaiSPDAO ldao = new LoaiSPDAO();
    private void fillCBLoai() {
         cbbLoai.removeAllItems();
        cbbLoai.addItem("Thức uống nóng");
        cbbLoai.addItem("Thức uống đá");
        cbbLoai.addItem("Nước có ga");
        cbbLoai.addItem("Nước đóng chai");
        cbbLoai.addItem("Sinh tố");
        cbbLoai.addItem("Đồ ăn");
        cbbLoai.addItem("Trà sữa");
        
        
    }
    SanPhamDAO spdao = new SanPhamDAO();

    private void Enabled() {
        cbbLoai.setEnabled(true);
        cbxTenSanPham.setEnabled(true);
        spnSoLuong.setEnabled(true);
        btnThem.setEnabled(true);
    }

    private void taiLaiDuLieu() {
        if (hoadon[sttBan] != null) {
            String[] dsTen = hoadon[sttBan].getTenSP();
            int[] dsSL = hoadon[sttBan].getSoLuong();
            int[] dsThanhTien = hoadon[sttBan].getTongTien();
            for (int i = 0; i < hoadon[sttBan].getTenSP().length; i++) {
                model.addRow(new Object[]{
                    dsTen[i], dsSL[i], dsThanhTien[i]
                });
            }
            lblTongTien.setText(String.valueOf(hoadon[sttBan].getTienTongCong()));
        }
    }

    private void luuThongKe() {
       int a = Integer.parseInt(lblTongTien.getText());
        String sqlThongKe = "Insert into ThongKe (TKBan,TKTongTien,TKTienKhach,"
                + "TKTienThua,NVHoTen,TKNgay,TKThoiGian) values "
                + "( '" + lblBan.getText() + "'," + Integer.parseInt(lblTongTien.getText())
                + "," + Integer.parseInt(tfTienNhanCuaKhach.getText())
                + "," + Integer.parseInt(txttienthua.getText()) + " ,N'" + lblNhanVien.getText()
                + "',N'" + lblNgay.getText().substring(8,18) +
                "','" + lblThoiGian.getText() + "')";
        try {
            kn();
            Statement st = conn.createStatement();
            st.executeUpdate(sqlThongKe);
            conn.close();
            System.out.println("Lưu thành công");
//            hoadon[sttBan] = null;
            lblTrangThai.setText("Thanh toán thành công");

        } catch (Exception e) {
            System.out.println("Lưu thất bại");
            e.printStackTrace();
        }
    }

    private void Refresh() {
        tinh = false;
        lblBan.setText("");
        spnSoLuong.setValue(1);
        lblGia.setText("");
        lblThanhTien.setText("");
        lblTongTien.setText("");
        tfTienNhanCuaKhach.setText("");
        txttienthua.setText("");
        btnThanhToan.setEnabled(false);
        btnIn.setEnabled(false);
        tenban.setBackground(null);
        Disabled();
    }

    private void Disabled() {
        cbbLoai.setEnabled(false);
        cbxTenSanPham.setEnabled(false);
        spnSoLuong.setEnabled(false);
        btnThem.setEnabled(false);
    }
    private int getHours(String s) {
        String[] array = s.replace(":", " ").split("\\s");
        return Integer.parseInt(array[0]);
    }

    // Hàm lấy phút
    private int getMinute(String s) {
        String[] array = s.replace(":", " ").split("\\s");
        return Integer.parseInt(array[1]);
    }

    // Hàm kiểm tra hóa đơn để sử dụng các button
    private void kiemTraHoaDon() {
        if (bangHoaDon.getRowCount() == 0) {
            btnThanhToan.setEnabled(false);
            tfTienNhanCuaKhach.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
            tfTienNhanCuaKhach.setEnabled(true);
        }
    }
   
}
