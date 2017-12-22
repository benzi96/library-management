/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.TRUYENDTO;
import DAO.truyvan;
import GUI.truyen;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class TRUYENBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="truyen";
    public int tongsl=0;
    public int soluonghienco=0;
    public TRUYENBUS()
    {
        Vector header= new Vector();
            header.add("Mã truyện");
            header.add("Tên truyện");
            header.add("Loại truyện");
            header.add("Mã NXB");
            header.add("Mã tác giả");
            header.add("Mã thể loại");
            header.add("Tổng số lượng");
            header.add("Số lượng hiện có");
            header.add("Giá");
            header.add("Tóm lược");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public TRUYENBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã truyện");
            header.add("Tên truyện");
            header.add("Loại truyện");
            header.add("Mã NXB");
            header.add("Mã tác giả");
            header.add("Mã thể loại");
            header.add("Tổng số lượng");
            header.add("Số lượng hiện có");
            header.add("Giá");
            header.add("Tóm lược");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
            while(kq.next())
            {
                Vector row=new Vector();
                row.add(kq.getString(1));
                row.add(kq.getString(2));
                row.add(kq.getString(3));
                row.add(kq.getString(4));
                row.add(kq.getString(5));
                row.add(kq.getString(6));
                row.add(kq.getInt(7));
                row.add(kq.getInt(8));
                row.add(kq.getInt(9));
                row.add(kq.getString(10));
                tongsl+=kq.getInt(7);
                soluonghienco+=kq.getInt(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(truyen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void checksoluong(String [] giatri)
    {
        if (giatri[7].compareTo(giatri[6]) < 1);
        else 
        {
            giatri[7] = giatri[6];
            JOptionPane.showMessageDialog(null, "Số lượng hiện có lớn hơn tổng số lượng! Đã tự động sửa");
        }
    }
    public void them(TRUYENDTO tr)
    {
        Vector row=new Vector();  
        String [] giatri=new String[10];
        giatri=tr.giatri();
        checksoluong(giatri);
        for(int i=0;i<tr.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            a.them(table,tr.soluong,giatri);
    }
    public void sua(int i, TRUYENDTO tr)
    {
        String[] thuoctinh= new String[10];
        String [] giatri=new String[10];
        thuoctinh=tr.tenthuoctinh();
        giatri=tr.giatri();
        for(int j=0;j<tr.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,tr.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, TRUYENDTO tr)
    {
        if(i>=0)
        {
            a.xoa(table,tr.khoachinh(),tr.giatrikhoachinh());
            model.removeRow(i);  
        }
    }
    public void truyvan()
    {
        ResultSet rs = null;
        rs = a.truyvan(table);
        laydulieu(rs);
    }
    public void reset()
    {
        model.setRowCount(0);
    }
    public void timkiem(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        TRUYENDTO tr=new TRUYENDTO();
        String[]thuoctinh=tr.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
            case 6: khoa=thuoctinh[5];break;
            case 7: khoa=thuoctinh[6];break;
            case 8: khoa=thuoctinh[7];break;
            case 9: khoa=thuoctinh[8];break;
            case 10: khoa=thuoctinh[9];break;
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        TRUYENDTO nv=new TRUYENDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
            case 6: khoa=thuoctinh[5];break;
            case 7: khoa=thuoctinh[6];break;
            case 8: khoa=thuoctinh[7];break;
            case 9: khoa=thuoctinh[8];break;
            case 10: khoa=thuoctinh[9];break;
        }
//        rs=a.join("TRUYEN,NXB,THELOAI,TACGIA","MATRUYEN,TENTRUYEN,LOAITRUYEN,TENNXB,TENTACGIA,TENTHELOAI,TONGSOLUONG,SOLUONGHIENCO, GIA, TOMLUOC", "TRUYEN.MANXB=NXB.MANXB "
//                + "AND TRUYEN.MATACGIA=TACGIA.MATACGIA AND TRUYEN.MATHELOAI=THELOAI.MATHELOAI AND "+khoa+" like '%"+nhap+"%'");
        rs=a.select(table, "*", khoa, nhap);
        laydulieu(rs);     
    }
    public List autocomplete()
    {
        ResultSet rs;
        rs = a.select(table,"MATRUYEN");
        List<String> matruyen = new ArrayList<>();
        try {
            while(rs.next())
            {
                matruyen.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matruyen;
    }
    public int tongsoluong()
    {
        ResultSet kq;
        kq=a.truyvan(table);
        int a=0;
        try {
            while(kq.next())
            {
                a=a+kq.getInt(7);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
     public int checknull(TRUYENDTO tr)
    {
        String [] giatri=new String[10];
        giatri=tr.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã truyện");
            return 0;
        }   
        else if(giatri[1].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên truyện");
            return 0;
        }       
        else
            return 1;       
    }
    public int checkunique(String matr,int i)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(matr.equals(kq.getString(1)))
                {
                    if(i==1)
                    JOptionPane.showMessageDialog(null, "Mã truyện bị trùng");               
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    public int checklungtung(TRUYENDTO tr)
    {
        
        String[] giatri=new String[10];
        giatri=tr.giatri();
        NXBBUS nxb=new NXBBUS();
        TACGIABUS tgb=new TACGIABUS();
        THELOAIBUS tlb=new THELOAIBUS();
        
            
                if(nxb.checkunique(giatri[3],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "đã ghi lung tung mã nhà xuất bản");               
                    return 0;
                }
                if(tgb.checkunique(giatri[4],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "đã ghi lung tung mã tác giả");               
                    return 0;
                }
                if(tlb.checkunique(giatri[5],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "đã ghi lung tung mã tác thể loại");               
                    return 0;
                }                         
        return 1;
    }      
}
