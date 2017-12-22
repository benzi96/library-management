/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.TACGIADTO;
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
public class TACGIABUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="TACGIA";
    public TACGIABUS()
    {
         Vector header= new Vector();
            header.add("Mã tác giả");
            header.add("Tên tác giả");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public TACGIABUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã tác giả");
            header.add("Tên tác giả");
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
    public void them(TACGIADTO tg)
    {
        Vector row=new Vector();  
        String [] giatri=new String[2];
        giatri=tg.giatri();
        for(int i=0;i<tg.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            a.them(table,tg.soluong,giatri);
    }
    public void sua(int i, TACGIADTO tg)
    {
        String[] thuoctinh= new String[2];
        String [] giatri=new String[2];
        thuoctinh=tg.tenthuoctinh();
        giatri=tg.giatri();
        for(int j=0;j<tg.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,tg.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, TACGIADTO tg)
    {
        if(i>=0)
        {
            a.xoa(table,tg.khoachinh(),tg.giatrikhoachinh());
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
        TACGIADTO tg=new TACGIADTO();
        String[]thuoctinh=tg.tenthuoctinh();
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
        TACGIADTO nv=new TACGIADTO();
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
        rs = a.select(table,"MATACGIA");
        List<String> matg = new ArrayList<>();
        try {
            while(rs.next())
            {
                matg.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matg;
    }
      public int checknull(TACGIADTO nxb)
    {
        String [] giatri=new String[10];
        giatri=nxb.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã tác giả");
            return 0;
        }   
        else if(giatri[1].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên tác giả");
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
                    JOptionPane.showMessageDialog(null, "Mã tác giả bị trùng");               
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
