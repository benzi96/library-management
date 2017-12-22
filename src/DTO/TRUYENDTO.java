/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class TRUYENDTO implements thuoctinhvagiatri {
    public final int soluong=10;
    private String MATRUYEN;
    private String TENTRUYEN;
    private String LOAITRUYEN;
    private String MANXB;
    private String MATACGIA;
    private String MATHELOAI;
    private int TONGSOLUONG;
    private int SOLUONGHIENCO;
    private int GIA;
    private String TOMLUOC;
    public TRUYENDTO(String MATRUYEN,String TENTRUYEN,String LOAITRUYEN,String MANXB,String MATACGIA,String MATHELOAI,int TONGSOLUONG,int SOLUONGHIENCO,int GIA,String TOMLUOC)
    {
        this.MATRUYEN=MATRUYEN;
        this.TENTRUYEN=TENTRUYEN;
        this.LOAITRUYEN=LOAITRUYEN;
        this.MANXB=MANXB;
        this.MATACGIA=MATACGIA;
        this.MATHELOAI=MATHELOAI;
        this.TONGSOLUONG=TONGSOLUONG;
        this.SOLUONGHIENCO=SOLUONGHIENCO;
        this.GIA=GIA;
        this.TOMLUOC=TOMLUOC;
    }

    public TRUYENDTO() {
    }
    public String[] giatri()
    {
        String[] a=new String[10];
            a[0]=MATRUYEN;
            a[1]=TENTRUYEN;
            a[2]=LOAITRUYEN;
            a[3]=MANXB;
            a[4]=MATACGIA;
            a[5]=MATHELOAI;
            a[6]=Integer.toString(TONGSOLUONG);
            a[7]=Integer.toString(SOLUONGHIENCO);
            a[8]=Integer.toString(GIA);
            a[9]=TOMLUOC;
        return a;
    }
    public String[] tenthuoctinh()
    {
        String[] a=new String[10];
        a[0]="MATRUYEN";
        a[1]="TENTRUYEN";
        a[2]="LOAITRUYEN";
        a[3]="MANXB";
        a[4]="MATACGIA";
        a[5]="MATHELOAI";
        a[6]="TONGSOLUONG";
        a[7]="SOLUONGHIENCO";
        a[8]="GIA";
        a[9]="TOMLUOC";
        return a;
    }
    public String khoachinh()
    {
        return "MATRUYEN";
    }
    public String giatrikhoachinh()
    {
        return MATRUYEN;
    }
}
