/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author liuzhao
 */
public class Station implements Serializable{
    
    	private int sid;
	private String name;
	private ArrayList<Integer> rid;
	private ArrayList<ScheduleInfo> busSchedule;
	private ArrayList carpoolSchedule;
	
        public Station(int sid,String name){
            this.sid=sid;
            this.name=name;
            this.busSchedule=new ArrayList<ScheduleInfo>();
            this.rid=new ArrayList<Integer>();
            this.carpoolSchedule=new ArrayList();
        }
        
	public String getName(){
		return name;
	}
	
	public int getSid(){
		return sid;
	}
	
	public ArrayList<Integer> getRid(){
		return rid;
	}
	
        public ArrayList<ScheduleInfo> getBusSchedule(){
            return busSchedule;
        }
                
        public void addBusSchedule(ScheduleInfo s){
            this.busSchedule.add(s);
        }
        
        public void addRid(int rid){
            this.rid.add(rid);
        } 
        
	public int calculateWaitingTime(int bid,LocalTime curTime){
            int waittingTime=50;
            for(ScheduleInfo x:busSchedule){
                if(x.getTransId()==bid){
                    if(x.getTime().isAfter(curTime)){
                        int a=(x.getTime().getHour()*60+x.getTime().getMinute())-(curTime.getHour()*60+curTime.getMinute());
                        if(a<waittingTime){
                            waittingTime=a;
                        }
                    }
                }
            }
		return waittingTime;
	}
	
        public int calculateAlertWaitingTime(int bid,LocalTime arriveTime){
            int waittingTime=50;
            for(ScheduleInfo x:busSchedule){
                if(x.getTransId()==bid){
                    if(x.getTime().isBefore(arriveTime)){
                        int a=(arriveTime.getHour()*60+arriveTime.getMinute())-(x.getTime().getHour()*60+x.getTime().getMinute());
                        if(a<waittingTime){
                            waittingTime=a;
                        }
                    }
                }
            }
           return waittingTime; 
        }
        
	public ArrayList findCarpool(String sStation,Date time){
		return carpoolSchedule;
	}
	
	public void addCarpoolSchedule(int carpoolId,Date time){
		
	}
}
