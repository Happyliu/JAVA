/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author liuzhao
 */
public class Carpool extends Transportation implements Serializable{
    
    private LocalTime startTime;
    private LocalTime arriveTime;
    private String sStation;
    private String dStation;
    private int seatsAvailable;
    private CarpoolProvider provider;
    private ArrayList<Passenger> passengers;
    
    public Carpool(){}
    public Carpool(LocalTime sTime, LocalTime aTime, String sStation, String dStation, int seats, CarpoolProvider p){
        this.transId=this.i;
        this.rate=0;
        this.transType="Carpool";
        this.startTime=sTime;
        this.arriveTime=aTime;
        this.sStation=sStation;
        this.dStation=dStation;
        this.seatsAvailable=seats;
        this.passengers=new ArrayList<Passenger>();
        this.i++;
        this.provider=p;
                              
    }
    public CarpoolProvider getProvider(){
        return provider;
    }
    public String getSStation(){
        return sStation;
    }
    public String getDStation(){
        return dStation;
    }
    /*public String getStartTime(){
        return String.valueOf(startTime.getHour())+String.valueOf(startTime.getMinute());
    }*/
    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }
    
    public int getCarpoolTime(){
        int runningTime=arriveTime.getHour()*60+arriveTime.getMinute()-startTime.getHour()*60-startTime.getMinute();
        //int waitingTime=startTime.getHour()*60+startTime.getMinute()-LocalTime.now().getHour()*60-LocalTime.now().getMinute();
        //int carpoolTime=runningTime+waitingTime;
        return runningTime;
    }
    
    public int getSearchCarpoolTime(){
        int runningTime=arriveTime.getHour()*60+arriveTime.getMinute()-startTime.getHour()*60-startTime.getMinute();
        int waitingTime=startTime.getHour()*60+startTime.getMinute()-LocalTime.now().getHour()*60-LocalTime.now().getMinute();
        int carpoolTime=runningTime+waitingTime;
        return carpoolTime;
    }
    
    public int getSeatsAvailable(){
        return seatsAvailable;
    }
    
    public void addPassenger(Passenger p){
        passengers.add(p);
        seatsAvailable--;
    }
    
    public String getBasicInfo(){
            String s1="Carpool No."+transId;
            String s2=getCarpoolTime()+"min";
            String s3="$"+getRate();
            String display=String.format("%-30s%11s%15s", s1,s2,s3);
            return display;
    }
    
    public String getSearchBasicInfo(){
            String s1="Carpool No."+transId;
            String s2=getSearchCarpoolTime()+"min";
            String s3="$"+getRate();
            String display=String.format("%-30s%11s%15s", s1,s2,s3);
            return display;
    }
    
    public String getDetailInfo(){
            String s1="Duration Time: "+getCarpoolTime()+"\n";
            String s2="Total Rate: $0"+"\n";
            String s3="Start Station: "+getSStation()+"\n";
            String s4="Destination Station: "+getDStation()+"\n";
            String s5="Start Time: "+startTime+"\n";
            String s6="Carpool Provider Name: "+getProvider().getPname()+"\n";
            String s=s1+s2+s3+s4+s5+s6;
            return s;
    }
    
    public String getSearchDetailInfo(){
            String s1="Duration Time: "+getSearchCarpoolTime()+"\n";
            String s2="Total Rate: $0"+"\n";
            String s3="Start Station: "+getSStation()+"\n";
            String s4="Destination Station: "+getDStation()+"\n";
            String s5="Start Time: "+startTime+"\n";
            String s6="Carpool Provider Name: "+getProvider().getPname()+"\n";
            String s=s1+s2+s3+s4+s5+s6;
            return s;
    }
}
