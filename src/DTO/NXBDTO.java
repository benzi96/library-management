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
public class NXBDTO implements thuoctinhvagiatri{
    public final int soluong=2;
    private String MANXB;
    private String TENNXB;
    public NXBDTO(String MANXB,String TENNXB){
        this.MANXB=MANXB;
        this.TENNXB=TENNXB;
    }
    public NXBDTO(){
    }
    public String[] giatri(){
        String [] a=new String[2];
        a[0]= MANXB;
        a[1]= TENNXB;
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[2];
        a[0]= "MANXB";
        a[1]= "TENNXB";
        return a;
    }
    public String khoachinh(){
        return "MANXB";
    }
    public String giatrikhoachinh()
    {
        return MANXB;
    }
}
