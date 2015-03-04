/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author liuzhao
 */
public class Alert implements Serializable{
    
    private Station sStation;
    private Station dStation;
    private LocalTime arriveTime;
    private int advancedTime;
    private int active;
    private LocalTime alertTime;
    
    public Alert(Station s, Station d, LocalTime arriveTime,int advanceTime){
        this.sStation=s;
        this.dStation=d;
        this.arriveTime=arriveTime;
        this.advancedTime=advanceTime;
        this.active=0;
        this.alertTime=LocalTime.MIN;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    public Station getsStation() {
        return sStation;
    }

    public Station getdStation() {
        return dStation;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public int getAdvancedTime() {
        return advancedTime;
    }

    public int getActive() {
        return active;
    }

    public LocalTime getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(LocalTime alertTime) {
        this.alertTime = alertTime;
    }
    
    public String getAlertInfo(){
        return sStation.getName()+"\t"+dStation.getName()+"\t"+arriveTime+"\t"+advancedTime;
    }
}
