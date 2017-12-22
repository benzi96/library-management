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
public class PHIEUMUONTRUYENDTO implements thuoctinhvagiatri{
    public final int soluong=7;
    private String MAPHIEUMUON;
    private String MANV;
    private String MATRUYEN;
    private String MATV;
    private String NGAYTHUE;
    private int THOIHAN; 
    private String NGAYTRA;
    public PHIEUMUONTRUYENDTO(String MAPHIEUMUON,String MANV,String MATRUYEN,String MATV,String NGAYTHUE,int THOIHAN,String NGAYTRA){
        this.MAPHIEUMUON=MAPHIEUMUON;
        this.MANV=MANV;
        this.MATRUYEN=MATRUYEN;
        this.MATV=MATV;
        this.NGAYTHUE=NGAYTHUE;
        this.THOIHAN=THOIHAN;
        this.NGAYTRA=NGAYTRA;
    }
    public PHIEUMUONTRUYENDTO(){
    }
    public String[] giatri(){
        String[] a=new String[10];
        a[0]=MAPHIEUMUON;
        a[1]=MANV;
        a[2]=MATRUYEN;
        a[3]=MATV;
        a[4]=NGAYTHUE;
        a[5]=Integer.toString(THOIHAN);
        a[6]=NGAYTRA;
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[10];
        a[0]= "MAPHIEUMUON";
        a[1]= "MANV";
        a[2]= "MATRUYEN";
        a[3]= "MATV";
        a[4]= "NGAYTHUE";
        a[5]= "THOIHAN";
        a[6]="NGAYTRA";
        return a;
    }
    public String khoachinh(){
        return "MAPHIEUMUON";
    }
        public String giatrikhoachinh()
    {
        return MAPHIEUMUON;
    }
}
