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
public class TACGIADTO implements thuoctinhvagiatri{
    public final int soluong=2;
    private String MATACGIA;
    private String TENTACGIA;
    public TACGIADTO(String MATACGIA,String TENTACGIA){
        this.MATACGIA=MATACGIA;
        this.TENTACGIA=TENTACGIA;
    }
    public TACGIADTO(){
    }
    public String[] giatri(){
        String[] a=new String[2];
        a[0]=MATACGIA;
        a[1]=TENTACGIA;
        return a;
    }
    public String[] tenthuoctinh(){
        String[] a=new String[2];    
        a[0]="MATACGIA";
        a[1]="TENTACGIA";
        return a;
    }
    public String khoachinh(){
        return "MATACGIA";
    }
    public String giatrikhoachinh(){
        return MATACGIA;
    }
}
