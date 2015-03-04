/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author liuzhao
 */
public class Passenger implements Comparable<Passenger>, Comparator<Passenger>, Serializable{
    
    private static int j=1;
    private int cid;
    private String name;
    private ArrayList<Alert> alerts;

    public Passenger(){
        
    }
    
    public Passenger(String name) {
        this.cid = j;
        this.name = name;
        this.alerts=new ArrayList<Alert>();
        j++;
    }

    public void addAlert(Alert a){
        alerts.add(a);
    }
    
    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public static void setJ(int j) {
        Passenger.j = j;
    }
    
    public static int getJ() {
        return j;
    }

    
    @Override
    public int compareTo(Passenger o) {
        return o.getCid()-this.cid;
    }

    @Override
     public int compare(Passenger o1, Passenger o2) {
        int ret=o1.getName().compareTo(o2.getName());
        if(ret!=0){
            return ret;
        }
        return o1.getCid()-o2.getCid();
    } 
    
    class PassengerAlertSizeCompare implements Comparator<Passenger>{

        @Override
        public int compare(Passenger o1, Passenger o2) {
            int ret=o1.getAlerts().size()-o2.getAlerts().size();
            if(ret!=0){
                return ret;
            }
            return o1.getCid()-o2.getCid();
        }
    }
}
