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
public class THELOAIDTO implements thuoctinhvagiatri{
    public final int soluong=2;
    private String MATHELOAI;
    private String TENTHELOAI;
    public THELOAIDTO(String MATHELOAI,String TENTHELOAI){
    this.MATHELOAI=MATHELOAI;
    this.TENTHELOAI=TENTHELOAI;
    }
    public THELOAIDTO(){
    }
    public String[] giatri(){
        String[] a=new String[2];
        a[0]= MATHELOAI;
        a[1]= TENTHELOAI;
        return a;
    }
    public String[] tenthuoctinh(){
        String [] a=new String[2];
        a[0]= "MATHELOAI";
        a[1]= "TENTHELOAI";
        return a;
    }
    public String khoachinh(){
        return "MATHELOAI";
    }
    public String giatrikhoachinh(){
        return MATHELOAI;
    }
}
