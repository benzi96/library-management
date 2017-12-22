/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.PHIEUMUONTRUYENDTO;
import DAO.truyvan;
import GUI.truyen;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class PHIEUMUONTRUYENBUS {
    public static  DefaultTableModel model= new DefaultTableModel();
    truyvan a=new truyvan();
    String table="PHIEUMUONTRUYEN";
    public PHIEUMUONTRUYENBUS()
    {
        Vector header= new Vector();
            header.add("Mã phiếu mượn");
            header.add("Mã nhân viên");
            header.add("Mã truyện");
            header.add("Mã thành viên");
            header.add("Ngày thuê");
            header.add("Thời hạn(ngày)");
            header.add("Ngày trả");
            if(model.getRowCount()==0)
            {
                model= new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }};
            }
    }
    public PHIEUMUONTRUYENBUS(int i)
    {
        laydulieu(a.truyvan(table));
    }
    public void laydulieu(ResultSet kq)
    {
         try {
            // TODO add your handling code here:
            reset();
            Vector header= new Vector();
            header.add("Mã phiếu mượn");
            header.add("Mã nhân viên");
            header.add("Mã truyện");
            header.add("Mã thành viên");
            header.add("Ngày thuê");
            header.add("Thời hạn(ngày)");
            header.add("Ngày trả");
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
                row.add(kq.getInt(6));
                try{
                    row.add(kq.getString(7));
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
    public int updatesoluong(String cot,String matruyen)
    {
        int soluong=0;
        ResultSet giatri=a.timchinhxac("TRUYEN", cot, "MATRUYEN", matruyen);
        try {
            while(giatri.next())
            {
                soluong=giatri.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soluong;
    }
    
    public int giamsoluong(String matruyen)
    {
        int soluonghienco=updatesoluong("SOLUONGHIENCO",matruyen);
        if(soluonghienco>0)
        {
        soluonghienco= soluonghienco - 1;
        System.out.println(soluonghienco);
        a.suamotthuoctinh("TRUYEN", "SOLUONGHIENCO",Integer.toString(soluonghienco), "MATRUYEN", matruyen);
        return 1;
        }
        else JOptionPane.showMessageDialog(null, "Hết số lượng");
        return 0;
    }
    public void tangsoluong(String matruyen)
    {
        int soluonghienco=updatesoluong("SOLUONGHIENCO",matruyen);
        int tongsoluong=updatesoluong("TONGSOLUONG",matruyen);
        soluonghienco= soluonghienco + 1;
        System.out.println(soluonghienco);
        if(soluonghienco<tongsoluong)
        a.suamotthuoctinh("TRUYEN", "SOLUONGHIENCO",Integer.toString(soluonghienco), "MATRUYEN", matruyen);
        else
            JOptionPane.showMessageDialog(null, "Đã trả từ lâu rồi!");
        
        
    }
    public void them(PHIEUMUONTRUYENDTO phieu)
    {
        Vector row=new Vector();  
        String [] giatri=new String[10];
        giatri=phieu.giatri();
        for(int i=0;i<phieu.soluong;i++)
        {
            row.add(giatri[i]);
        }
            model.addRow(row);
            if(giamsoluong(giatri[2])==1)
            a.them(table,phieu.soluong,giatri);
            
    }
    public void sua(int i, PHIEUMUONTRUYENDTO phieu)
    {
        String[] thuoctinh= new String[10];
        String [] giatri=new String[10];
        thuoctinh=phieu.tenthuoctinh();
        giatri=phieu.giatri();
        for(int j=0;j<phieu.soluong;j++)
        {
            model.setValueAt(giatri[j],i,j);
        }
            
            a.sua(table,phieu.soluong,thuoctinh,giatri);
    }
    public void xoa(int i, PHIEUMUONTRUYENDTO phieu)
    {
        if(i>=0)
        {
            a.xoa(table,phieu.khoachinh(),phieu.giatrikhoachinh());
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
        PHIEUMUONTRUYENDTO phieu=new PHIEUMUONTRUYENDTO();
        String[]thuoctinh=phieu.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break; 
            case 6: khoa=thuoctinh[5];break; 
            case 7: khoa=thuoctinh[6];break; 
        }
        rs=a.tim(table, khoa, nhap);
        laydulieu(rs);     
    }
    public void timgandung(String nhap, int i) {
        ResultSet rs=null;
        String khoa="";
        PHIEUMUONTRUYENDTO nv=new PHIEUMUONTRUYENDTO();
        String[]thuoctinh=nv.tenthuoctinh();
        switch(i){
            case 1: khoa=thuoctinh[0];break;
            case 2: khoa=thuoctinh[1];break;
            case 3: khoa=thuoctinh[2];break;
            case 4: khoa=thuoctinh[3];break;
            case 5: khoa=thuoctinh[4];break;
            case 6: khoa=thuoctinh[5];break;
            case 7: khoa=thuoctinh[6];break; 
            case 8: khoa="MONTH(NGAYTHUE)";break;
            case 9: khoa="MONTH(NGAYTRA)";break;
            case 10: khoa="YEAR(NGAYTHUE)";break;
            case 11: khoa="YEAR(NGAYTRA)";break;
        }
        rs=a.select(table,"*", khoa, nhap);
        laydulieu(rs);     
    }
    public int checknull(PHIEUMUONTRUYENDTO phieu)
    {
        String [] giatri=new String[10];
        giatri=phieu.giatri();
        if(giatri[0].equals(""))
        {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã phiếu mượn");
            return 0;
        }     
        else
            return 1;       
    }
    public int checkunique(String maphieu)
    {
        ResultSet kq;
        kq=a.truyvan(table);
        try {
            while(kq.next())
            {
                if(maphieu.equals(kq.getString(1)))
                {
                    JOptionPane.showMessageDialog(null, "Mã phiếu mượn bị trùng");               
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHIEUMUONTRUYENBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    public int checklungtung(PHIEUMUONTRUYENDTO phieu)
    {
        
        String[] giatri=new String[10];
        giatri=phieu.giatri();
        NHANVIENBUS nvb=new NHANVIENBUS();
        THANHVIENBUS tvb=new THANHVIENBUS();
        TRUYENBUS bus=new TRUYENBUS();
        
            
                if(nvb.checkunique(giatri[1],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "đã ghi lung tung mã nhân viên");               
                    return 0;
                }
                if(bus.checkunique(giatri[2],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "mã truyện lung tung");               
                    return 0;
                }
                if(tvb.checkunique(giatri[3],0)==1)
                {
                    JOptionPane.showMessageDialog(null, "mã thành viên lung tung");               
                    return 0;
                }
               
        return 1;
    }
}
