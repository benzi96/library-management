/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NHANVIENDTO;
import DAO.truyvan;
import GUI.truyen;
import java.sql.ResultSet;
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
 * @author hp
 */
public class NHANVIENBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="NHANVIEN";
    public NHANVIENBUS()
    {
        Vector header= new Vector();
            header.add("Mã nhân viên");
            header.add("Họ nhân viên");
            header.add("Tên nhân viên");
            header.add("Ngày sinh");
            header.add("Số điện thoại");
            header.add("Ca trực");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public NHANVIENBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã nhân viên");
            header.add("Họ nhân viên");
            header.add("Tên nhân viên");
            header.add("Ngày sinh");
            header.add("Số điện thoại");
            header.add("Ca trực");
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
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(truyen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void them(NHANVIENDTO nv)
    {
        Vector row=new Vector();  
        String [] giatri=new String[6];
        giatri=nv.giatri();
        for(int i=0;i<nv.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            a.them(table,nv.soluong,giatri);
    }
    public void sua(int i, NHANVIENDTO nv)
    {
        String[] thuoctinh= new String[6];
        String [] giatri=new String[6];
        thuoctinh=nv.tenthuoctinh();
        giatri=nv.giatri();
        for(int j=0;j<nv.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,nv.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, NHANVIENDTO nv)
    {
        if(i>=0)
        {
            a.xoa(table,nv.khoachinh(),nv.giatrikhoachinh());
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
        NHANVIENDTO nv=new NHANVIENDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
            case 6: khoa=thuoctinh[5];break;
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        NHANVIENDTO nv=new NHANVIENDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
            case 6: khoa=thuoctinh[5];break;
        }
        rs=a.select(table,"*", khoa, nhap);
        laydulieu(rs);     
    }
    public List autocomplete()
    {
        ResultSet rs;
        rs = a.select(table,"MANV");
        List<String> manv = new ArrayList<>();
        try {
            while(rs.next())
            {
                manv.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manv;
    }
     public int checknull(NHANVIENDTO nv)
    {
        String [] giatri=new String[10];
        giatri=nv.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
            return 0;
        }   
        else if(giatri[1].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập họ nhân viên");
            return 0;
        }
        else if(giatri[2].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên nhân viên");
            return 0;
        }
        else if(giatri[3].equals("--"))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày sinh thành viên");
            return 0;
        }
        else if(giatri[4].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại");
            return 0;
        }
        else if(giatri[5].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập ca trực");
            return 0;
        }
        else if(!giatri[4].matches("[0-9]+") || giatri[4].length() < 7)
        {
            JOptionPane.showMessageDialog(null, "Số điện thoại có chữ hoặc số điện thoại ít hơn 7 con số");
            return 0;
        }
        else
            return 1;       
    }
      public int checkunique(String manv,int i)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(manv.equals(kq.getString(1)))
                {
                    if(i==1)           
                    JOptionPane.showMessageDialog(null, "Mã nhân viên bị trùng");               
                    return 0;
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
