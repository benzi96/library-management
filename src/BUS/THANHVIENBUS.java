/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.THANHVIENDTO;
import DAO.truyvan;
import GUI.truyen;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class THANHVIENBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="THANHVIEN";
    public THANHVIENBUS()
    {
        Vector header= new Vector();
            header.add("Mã thành viên");
            header.add("Họ thành viên");
            header.add("Tên thành viên");
            header.add("Ngày sinh");
            header.add("Ngày đăng kí");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public THANHVIENBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã thành viên");
            header.add("Họ thành viên");
            header.add("Tên thành viên");
            header.add("Ngày sinh");
            header.add("Ngày đăng kí");
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
                try{
                row.add(kq.getString(5));
                }
                catch (Exception ex){
                    row.add("");
                }
                
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(truyen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void them(THANHVIENDTO tv)
    {
        Vector row=new Vector();  
        String [] giatri=new String[5];
        giatri=tv.giatri();
        for(int i=0;i<tv.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            a.them(table,tv.soluong,giatri);
    }
    public void sua(int i, THANHVIENDTO tv)
    {
        String[] thuoctinh= new String[5];
        String [] giatri=new String[5];
        thuoctinh=tv.tenthuoctinh();
        giatri=tv.giatri();
        for(int j=0;j<tv.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,tv.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, THANHVIENDTO tv)
    {
        if(i>=0)
        {
            a.xoa(table,tv.khoachinh(),tv.giatrikhoachinh());
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
        THANHVIENDTO tv=new THANHVIENDTO();
        String[]thuoctinh=tv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;  
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        THANHVIENDTO nv=new THANHVIENDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
        }
        rs=a.select(table,"*", khoa, nhap);
        laydulieu(rs);     
    }
    public List autocomplete()
    {
        ResultSet rs;
        rs = a.select(table,"MATV");
        List<String> matv = new ArrayList<>();
        try {
            while(rs.next())
            {
                matv.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matv;
    }
      public int checknull(THANHVIENDTO tv)
    {
        String [] giatri=new String[10];
        giatri=tv.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã thành viên");
            return 0;
        }   
        else if(giatri[1].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập họ thành viên");
            return 0;
        }
        else if(giatri[2].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên thành viên");
            return 0;
        }
        else if(giatri[3].equals("--"))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày sinh thành viên");
            return 0;
        }
        else if(giatri[4].equals("--"))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày đăng ký thành viên");
            return 0;
        }
        else
            return 1;       
    }
      public int checkunique(String matv,int i)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(matv.equals(kq.getString(1)))
                {
                    if(i==1)
                    JOptionPane.showMessageDialog(null, "Mã thành viên bị trùng");               
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
