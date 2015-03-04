/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;

/**
 *
 * @author liuzhao
 */
public class CarpoolProvider implements Serializable{
    
    private int pid;
    private String pname;
    private String carType;
    private int point;
    private static int i=1;
    
    public CarpoolProvider(){}
    
    public CarpoolProvider(String name, String carType){
        this.pid=i;
        this.pname=name;
        this.carType=carType;
        this.point=0;
        i++;
    }
    
    public CarpoolProvider(int pid, String name, String carType, int point){
        this.pid=pid;
        this.pname=name;
        this.carType=carType;
        this.point=point;
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        CarpoolProvider.i = i;
    }
    
    public int getPid(){
        return pid;
    }
    public String getPname(){
        return pname;
    }
    public String getCarType(){
        return carType;
    }
    public int getPoint(){
        return point;
    }
    public void addPoint(){
        this.point++;
    }
    public void setPname(String name){
        this.pname=name;
    }
    public void setCarType(String carType){
        this.carType=carType;
    }
    
}
