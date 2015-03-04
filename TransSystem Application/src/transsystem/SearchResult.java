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
public class SearchResult {
    
    private static int i=0;
    private int srid;
    private Station s;
    private Station d;
    private ArrayList<Station> tl;//transfer stations
    private ArrayList<Integer> rl;//route list
    private ArrayList<Integer> bl;//bus list
    private int costTime;
    private int rate;
    
    public SearchResult(Station s,Station d,ArrayList<Station> tl,ArrayList<Integer> rl,ArrayList<Integer> bl,int costTime,int rate){
        this.srid=i;
        this.s=s;
        this.d=d;
        this.tl=tl;
        this.rl=rl;
        this.bl=bl;
        this.costTime=costTime;
        this.rate=rate;
        i++;
    }



    public int getSrid() {
        return srid;
    }

    public Station getS() {
        return s;
    }

    public Station getD() {
        return d;
    }

    public ArrayList<Integer> getBl() {
        return bl;
    }

    
    public ArrayList<Station> getTl() {
        return tl;
    }

    public ArrayList<Integer> getRl() {
        return rl;
    }


    public int getCostTime() {
        return costTime;
    }

    public int getRate() {
        return rate;
    }
    
    public String displayResult(){
            String s="";
            for (int i=0; i<rl.size();i++){
            if (i!=0){
                
                s = s + " - ";
            }
            
            s = s + "Bus Route " + String.valueOf(rl.get(i));
        
        }
        
        String display=String.format("%30s%15s%15s", s,String.valueOf(costTime)+"min", "$"+String.valueOf(rate));
        return display;
    }
    

    
    public String toString(){
        String s1="time: "+costTime+"\n";
        String s2="Rate: "+rate+"\n";
        String s3="";
        String s4="";
        String s5="";
        s3="Start Station: "+"Route "+rl.get(0)+" - "+s.getName()+"\n";
        for (int i=1; i<rl.size();i++){
            s4=s4+"Transfer Station: "+"Route "+rl.get(i-1)+" & "+"Route "+rl.get(i)+" - "+tl.get(i-1).getName()+"\n";           
        }
        s5="Destination Station: "+"Route "+rl.get(rl.size()-1)+" - "+d.getName()+"\n";
        String s=s1+s2+s3+s4+s5;
        return s;  
    }
    
}
