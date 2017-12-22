/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NXBDTO;
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
public class NXBBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="NXB";
    public NXBBUS()
    {
        Vector header= new Vector();
            header.add("Mã nhà xuất bản");
            header.add("Tên nhà xuất bản");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public NXBBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã nhà xuất bản");
            header.add("Tên nhà xuất bản");
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
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(truyen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void them(NXBDTO nxb)
    {
        Vector row=new Vector();  
        String [] giatri=new String[2];
        giatri=nxb.giatri();
        for(int i=0;i<nxb.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            a.them(table,nxb.soluong,giatri);
    }
    public void sua(int i, NXBDTO nxb)
    {
        String[] thuoctinh= new String[2];
        String [] giatri=new String[2];
        thuoctinh=nxb.tenthuoctinh();
        giatri=nxb.giatri();
        for(int j=0;j<nxb.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,nxb.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, NXBDTO nxb)
    {
        if(i>=0)
        {
            a.xoa(table,nxb.khoachinh(),nxb.giatrikhoachinh());
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
        NXBDTO nxb=new NXBDTO();
        String[]thuoctinh=nxb.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        NXBDTO nv=new NXBDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
        }
        rs=a.select(table,"*", khoa, nhap);
        laydulieu(rs);     
    }
    public List autocomplete()
    {
        ResultSet rs;
        rs = a.select(table,"MANXB");
        List<String> manxb = new ArrayList<>();
        try {
            while(rs.next())
            {
                manxb.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manxb;
    }
    public int checknull(NXBDTO nxb)
    {
        String [] giatri=new String[10];
        giatri=nxb.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhà xuất bản");
            return 0;
        }   
        else if(giatri[1].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên nhà xuất bản");
            return 0;
        }       
        else
            return 1;       
    }
    
      public int checkunique(String manxb,int i)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(manxb.equals(kq.getString(1)))
                {
                    if(i==1)
                    JOptionPane.showMessageDialog(null, "Mã nhà xuất bản bị trùng");               
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
      
}