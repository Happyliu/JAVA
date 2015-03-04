/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author liuzhao
 */
public class ScheduleInfo implements Serializable{
    
        private int siId;
    	private int transId;
	private LocalTime time;
	
        public ScheduleInfo(int siId,int transId,LocalTime time){
            this.siId=siId;
            this.transId=transId;
            this.time=time;
        }
        
        public int getTransId(){
                return transId;    
        }
        
        public LocalTime getTime(){
            return time;
        }
        
	public String getScheduleInfo(){
		return siId+" "+transId+" "+time;
	}
}
