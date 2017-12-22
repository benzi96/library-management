/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.PHIEUPHATDTO;
import DAO.truyvan;
import GUI.truyen;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class PHIEUPHATBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="PHIEUPHAT";
    public PHIEUPHATBUS()
    {
        Vector header= new Vector();
            header.add("Mã phiếu phạt");
            header.add("Mã phiếu mượn");
            header.add("Số tiền phạt");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public PHIEUPHATBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã phiếu phạt");
            header.add("Mã phiếu mượn");
            header.add("Số tiền phạt");
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
                row.add(kq.getInt(3));  
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(truyen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void them(PHIEUPHATDTO phat)
    {
        Vector row=new Vector();  
        String [] giatri=new String[3];
        giatri=phat.giatri();
        for(int i=0;i<phat.soluong;i++)
        {
            row.add(giatri[i]);
        }
            
            a.them(table,phat.soluong,giatri);
    }
    public void sua(int i, PHIEUPHATDTO phat)
    {
        String[] thuoctinh= new String[3];
        String [] giatri=new String[3];
        thuoctinh=phat.tenthuoctinh();
        giatri=phat.giatri();
        for(int j=0;j<phat.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,phat.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, PHIEUPHATDTO phat)
    {
        if(i>=0)
        {
            a.xoa(table,phat.khoachinh(),phat.giatrikhoachinh());
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
        PHIEUPHATDTO phat=new PHIEUPHATDTO();
        String[]thuoctinh=phat.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        PHIEUPHATDTO nv=new PHIEUPHATDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
        }
        rs=a.select(table,"*", khoa, nhap);
        laydulieu(rs);     
    }
    public int checkunique(String maphieu)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(maphieu.equals(kq.getString(2)))
                {             
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    int laymaphieu()
    {
        int maphieuphat=0;
        ResultSet rs=a.timchinhxac("bangthamso", "giatri","thamso","MAPHIEUPHAT");
     try {
            while(rs.next())
            {
                maphieuphat=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
     return maphieuphat;
    }
    public void layphieuphat()
    {
        ResultSet rs = null;
        rs=a.truyvan("PHIEUMUONTRUYEN");
        List<String> maphieumuon = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            while(rs.next())
            {
                String ngaytra="";
                try{
                ngaytra = rs.getString(7);
                }
                catch(Exception e){
                    ngaytra="";
                }
                System.out.println(ngaytra);
                if(ngaytra.equals("")){
                Calendar c = Calendar.getInstance();
                String ngaythue= rs.getString(5);
                int songay= rs.getInt(6);
                try {
                c.setTime(sdf.parse(ngaythue));
                c.add(Calendar.DATE, songay);
                String ngaytradunghan = sdf.format(c.getTime());
                Date date = new Date();
                String ngayhientai=sdf.format(date);
               
                    if(sdf.parse(ngayhientai).after(sdf.parse(ngaytradunghan)))
                    {
                       maphieumuon.add(rs.getString(1));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PHIEUPHATBUS.class.getName()).log(Level.SEVERE, null, ex);
                }
                }      
            }
            int ma=laymaphieu();
            for(int i=0;i<maphieumuon.size();i++)
            {           
            int j=checkunique(maphieumuon.get(i));
            if(j==1){
            PHIEUPHATDTO pp=new PHIEUPHATDTO(Integer.toString(ma),maphieumuon.get(i),20000);
            them(pp);     
            ma++;
            }
            }
            a.suamotthuoctinh("bangthamso", "giatri", Integer.toString(ma), "thamso", "MAPHIEUPHAT");
            truyvan();
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUPHATBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
