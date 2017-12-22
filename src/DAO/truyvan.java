/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.myconnect;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class truyvan {
    myconnect ketnoi=new myconnect("localhost","root","","qltv?useUnicode=yes&characterEncoding=UTF-8","MySQL");
    public void them(String table, int soluong, String[] giatri)
    {
        String qry="insert into "+table+" values(";
        for(int i=0;i<soluong;i++)
        {
            qry+="'"+giatri[i]+"'";
            if(i<soluong-1) qry+=",";
        }
        qry+=")";
        try {
            int i=ketnoi.executeUpdate(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
    }
    public void sua(String table, int soluong, String[] tenthuoctinh, String[] giatri)
    {
        String qry="update "+table+" set ";
        for(int i=0;i<soluong;i++)
        {
            qry+=tenthuoctinh[i]+"='"+giatri[i]+"'";
            if(i<soluong-1) qry+=",";
        }
        qry+=" where "+tenthuoctinh[0]+"='"+giatri[0]+"'";
        try {
            int i=ketnoi.executeUpdate(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
    }
    public void xoa(String table, String khoachinh, String giatri)
    {
        String qry="delete from "+table+" where "+khoachinh+"='"+giatri+"'";
        try {
            int i=ketnoi.executeUpdate(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
    }
    public ResultSet truyvan(String table)
    {
        ResultSet rs=null;
        String qry="select * from "+table;
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
    public ResultSet tim(String table, String khoa, String giatri)
    {
        ResultSet rs=null;
        String qry="select * from "+table+" where "+khoa+"='"+giatri+"'";
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
    public ResultSet select(String table,String cot, String khoa, String giatri)
    {
        ResultSet rs=null;
        String qry="select " +cot+" from "+table+" where "+khoa+" like '%"+giatri+"%'";
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
     public ResultSet timchinhxac(String table,String cot, String khoa, String giatri)
    {
        ResultSet rs=null;
        String qry="select " +cot+" from "+table+" where "+khoa+" = '"+giatri+"'";
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
    public ResultSet select(String table,String cot)
    {
        ResultSet rs=null;
        String qry="select " +cot+" from "+table;
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
    public ResultSet join(String tables, String tenthuoctinh, String dieukien)
    {
        ResultSet rs=null;
        String qry="select " +tenthuoctinh+" from "+tables+" where "+dieukien;
        try {
            rs=ketnoi.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
        return rs;
    }
    public void suamotthuoctinh(String table, String tenthuoctinh, String giatri, String khoa, String giatrikhoa)
    {
        String qry="update "+table+" set ";
       
            qry+=tenthuoctinh+"='"+giatri+"'";
       
        qry+=" where "+khoa+"='"+giatrikhoa+"'";
        try {
            int i=ketnoi.executeUpdate(qry);
        } catch (Exception ex) {
            Logger.getLogger(truyvan.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(qry);
    }
     
}
