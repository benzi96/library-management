/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.myconnect;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class khoitao {
    myconnect ketnoi=new myconnect("localhost","root","","qltv","MySQL");
    public void them()
    {
        try {
            Statement st=ketnoi.khoitao().createStatement();
            int luu;
            luu = st.executeUpdate("Create database IF NOT EXISTS QLTV;");
        } catch (Exception ex) {
            Logger.getLogger(khoitao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void taotable() throws SQLException
    {
        ketnoi=new myconnect("localhost","root","","qltv","MySQL");
        try {
            ketnoi.executeUpdate("CREATE TABLE nxb " +
"(" +
"MANXB varCHAR(10) primary key," +
"TENNXB varchar(30)  character set utf8" +
");" );
            ketnoi.executeUpdate("CREATE TABLE tacgia" +
"(" +
"MATACGIA varCHAR(10) primary key," +
"TENTACGIA VARCHAR(40)  character set utf8" +
");");
            ketnoi.executeUpdate("CREATE TABLE theloai" +
"(" +
"MATHELOAI varCHAR(10) PRIMARY KEY," +
"TENTHELOAI VARCHAR(30)  character set utf8" +
");");
            ketnoi.executeUpdate("CREATE TABLE truyen" +
"(" +
"MATRUYEN varchar(10) primary key," +
"TENTRUYEN varchar(40)  character set utf8," +
"LOAITRUYEN varchar(20)  character set utf8," +
"MANXB varchar(10)," +
"MATACGIA varchar(10)," +
"MATHELOAI varchar(10)," +
"TONGSOLUONG int," +
"SOLUONGHIENCO int," +
"GIA int," +
"TOMLUOC varchar(100) character set utf8,"+
" " +
"FOREIGN KEY(MANXB) REFERENCES NXB(MANXB)," +
"FOREIGN KEY(MATACGIA) REFERENCES TACGIA(MATACGIA)," +
"FOREIGN KEY(MATHELOAI) REFERENCES THELOAI(MATHELOAI)" +
");");
            ketnoi.executeUpdate("CREATE TABLE thanhvien" +
"(" +
"MATV varchar(10) primary key," +
"HOTV varchar(40) character set utf8,"+                   
"TENTV varchar(40)  character set utf8," +
"NGAYSINH date," +
"NGAYDANGKI date" +
");");
            ketnoi.executeUpdate("CREATE TABLE nhanvien" +
"(" +
"MANV varchar(10) primary key," +
"HONV varchar(40) character set utf8,"+
"TENNV varchar(40)  character set utf8," +
"NGAYSINH date," +
"SODIENTHOAI varchar(20)," +
"CATRUC varchar(10)  character set utf8" +
"" +
");");
            ketnoi.executeUpdate("CREATE TABLE phieumuontruyen" +
"(" +
"MAPHIEUMUON varchar(10) primary key," +
"MANV varchar(10)," +
"MATRUYEN varchar(10)," +
"MATV varchar(10)," +
"NGAYTHUE date," +
"THOIHAN int, " +
"NGAYTRA date," +                   
"FOREIGN KEY(MANV) REFERENCES NHANVIEN(MANV)," +
"FOREIGN KEY(MATRUYEN) REFERENCES TRUYEN(MATRUYEN)," +
"FOREIGN KEY(MATV) REFERENCES THANHVIEN(MATV)" +
");");
            ketnoi.executeUpdate("CREATE TABLE phieuphat" +
"(" +
"MAPHIEUPHAT varchar(10) primary key," +
"MAPHIEUMUON varchar(10)," +
"SOTIENPHAT int, " +
"FOREIGN KEY(MAPHIEUMUON) REFERENCES PHIEUMUONTRUYEN(MAPHIEUMUON)" +
");");
            ketnoi.executeUpdate("CREATE TABLE bangthamso" +
"(" +
"thamso varchar(30) primary key," +
"giatri int," +
");");

                    
        } catch (Exception ex) {
            Logger.getLogger(khoitao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
