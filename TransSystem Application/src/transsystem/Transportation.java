/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

/**
 *
 * @author liuzhao
 */
public abstract class Transportation {
    
    	protected int rate;
	protected String transType;
	protected static int i=101;
	protected int transId;
	
	public String getTransType(){
		return transType;
	}
	
	public int getTransId(){
		return transId;
	}
	
	public int getRate(){
		return rate;
	}
}
