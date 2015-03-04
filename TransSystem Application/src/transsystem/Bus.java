/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.util.ArrayList;

/**
 *
 * @author liuzhao
 */
public class Bus extends Transportation{
    
    
    private ArrayList<ScheduleInfo> scheduleInfos;
    private int status;
    
    	public Bus(int id){
		this.transId=id;
		this.rate=2;
		this.transType="Bus";
	}
        
}
