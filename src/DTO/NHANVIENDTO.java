/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author hp
 */
public class NHANVIENDTO implements thuoctinhvagiatri{
    public final int soluong=6;
    private String MANV;
    private String HONV;
    private String TENNV;
    private String NGAYSINH;
    private String SODIENTHOAI;
    private String CATRUC;
    public NHANVIENDTO(String MANV,String HONV,String TENNV,String NGAYSINH,String SODIENTHOAI,String CATRUC){
        this.MANV=MANV;
        this.HONV=HONV;
        this.TENNV=TENNV;
        this.NGAYSINH=NGAYSINH;
        this.SODIENTHOAI=SODIENTHOAI;
        this.CATRUC=CATRUC;
    }
    public NHANVIENDTO(){
    }
    public String[] giatri(){
        String[] a=new String[6];
        a[0]=MANV;
        a[1]=HONV;
        a[2]=TENNV;
        a[3]=NGAYSINH;
        a[4]=SODIENTHOAI;
        a[5]=CATRUC;
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[6];
        a[0]= "MANV";
        a[1]= "HONV";
        a[2]= "TENNV";
        a[3]= "NGAYSINH";
        a[4]= "SODIENTHOAI";
        a[5]= "CATRUC";
        return a;
    }
    public String khoachinh(){
        return "MANV";
    }
    public String giatrikhoachinh(){
        return MANV;    
    }
}
