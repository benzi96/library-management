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
public class PHIEUPHATDTO implements thuoctinhvagiatri{
    public final int soluong=3;
    private String MAPHIEUPHAT;
    private String MAPHIEUMUON;
    private int SOTIENPHAT;
    public PHIEUPHATDTO(String MAPHIEUPHAT,String MAPHIEUMUON,int SOTIENPHAT){
        this.MAPHIEUPHAT=MAPHIEUPHAT;
        this.MAPHIEUMUON=MAPHIEUMUON;
        this.SOTIENPHAT=SOTIENPHAT;
    }
    public PHIEUPHATDTO(){
    }
    public String[] giatri(){
        String[] a=new String[3];
        a[0]=MAPHIEUPHAT;
        a[1]=MAPHIEUMUON;
        a[2]=Integer.toString(SOTIENPHAT);
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[3];
        a[0]= "MAPHIEUPHAT";
        a[1]= "MAPHIEUMUON";
        a[2]= "SOTIENPHAT";
        return a;
    }
    public String khoachinh(){
        return "MAPHIEUPHAT";
    }
    public String giatrikhoachinh()
    {
        return MAPHIEUPHAT;
    }
}
