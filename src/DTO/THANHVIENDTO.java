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
public class THANHVIENDTO implements thuoctinhvagiatri{
    public int soluong=5;
    private String MATV;
    private String HOTV;
    private String TENTV;
    private String NGAYSINH;
    private String NGAYDANGKI;
    public THANHVIENDTO(String MATV,String HOTV,String TENTV,String NGAYSINH,String NGAYDANGKI){
        this.MATV=MATV;
        this.HOTV=HOTV;
        this.TENTV=TENTV;
        this.NGAYSINH=NGAYSINH;
        this.NGAYDANGKI=NGAYDANGKI;
    }

    public THANHVIENDTO() {
        
    }
    public String[] giatri(){
        String[] a=new String[10];
        a[0]=MATV;
        a[1]=HOTV;
        a[2]=TENTV;
        a[3]=NGAYSINH;
        a[4]=NGAYDANGKI;
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[10];
        a[0]= "MATV";
        a[1]= "HOTV";
        a[2]= "TENTV";
        a[3]= "NGAYSINH";
        a[4]= "NGAYDANGKI";
        return a;
    }
    public String khoachinh(){
        return "MATV";
    }
    public String giatrikhoachinh()
    {
        return MATV;
    }
}
