/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import transsystem.Passenger.PassengerAlertSizeCompare;

/**
 *
 * @author liuzhao
 */
public class TransSystem implements Serializable{

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Station> allStations;
    private static ArrayList<Passenger> passengers;
    private static ArrayList<CarpoolProvider> providers;
    private static ArrayList<SearchResult> searchResults;
    private static ArrayList<Carpool> carpools;
    private static ArrayList<Alert> alerts;
    private static Passenger p;
    private static int plogin=0;
    private static CarpoolProvider pr;
    private static int prlogin=0;
    private static int systemRunningTime;
    
    public TransSystem(){
        allStations=new ArrayList<Station>();
        passengers=new ArrayList<Passenger>();
        providers=new ArrayList<CarpoolProvider>();
        searchResults=new ArrayList<SearchResult>();
        carpools=new ArrayList<Carpool>();
        //alerts=new ArrayList<Alert>();
        initialSystem();
        readPassengerToSystem();
        systemRunningTime=1;
    }
    
    public static ArrayList<Station> getStations(){ return allStations;}
    private static void setPassengers(ArrayList<Passenger> pl){passengers=pl;}
    public static ArrayList<Passenger> getPassengers(){ return passengers;}

    public static void setProviders(ArrayList<CarpoolProvider> providers) {
        TransSystem.providers = providers;
    }

    public static void setCarpools(ArrayList<Carpool> carpools) {
        TransSystem.carpools = carpools;
    }

    public static ArrayList<Carpool> getCarpools() {
        return carpools;
    }
    
    public static ArrayList<CarpoolProvider> getProviders(){ return providers;}
    //public static void setAlerts(ArrayList<Alert> al){alerts=al;}
    //public static ArrayList<SearchResult> getSearchResults(){ return searchResults;}
    
    public static Station getStation(String st){
        for(Station x:allStations){
            if(x.getName().equals(st)){
                return x;
            }      
        }
        return null;
    }
    
    public static Station getStation(int sid){
            for(Station x:allStations){
            if(x.getSid()==sid){
                return x;
            }      
        }
        return null;
    }
    
    public static void systemRunningTimeAdd(){
        systemRunningTime++;
    }
            
    public static Passenger getP() {
        return p;
    }

    public static void setP(Passenger p) {
        TransSystem.p = p;
    }
    
    public static int getPlogin() {
        return plogin;
    }

    public static void setPlogin(int plogin) {
        TransSystem.plogin = plogin;
    }

    public static CarpoolProvider getPr() {
        return pr;
    }

    public static int getPrlogin() {
        return prlogin;
    }

    public static void setPr(CarpoolProvider pr) {
        TransSystem.pr = pr;
    }

    public static void setPrlogin(int prlogin) {
        TransSystem.prlogin = prlogin;
    }
    
    public static SearchResult getSearchResult(int srid){
        for(SearchResult x: searchResults){
            if(x.getSrid()==srid){
                return x;
            }
        }
        return null;
    }
    
    public static void addStation(Station s){
            allStations.add(s);
    }
    

    public static void addPassenger(Passenger p){
           passengers.add(p);
    }
    
    public static void addProvider(CarpoolProvider provider){
           providers.add(provider);
    }
    
    public static void addCarpool(Carpool c){
           carpools.add(c);
    }   
    
    public static void addSearchResult(SearchResult sr){
            searchResults.add(sr);
    }
    
    private static void initialSystem(){
        
            try{
                File file1=new File("Station.txt");
                Scanner input1=new Scanner(file1);
                while(input1.hasNextLine()){
                    //System.out.println(input1.next());
                    String[] s1=input1.next().split(",|-");
                    String sname;
                    if(s1.length==4){
                        sname=s1[1]+" "+s1[2]+" "+s1[3];
                    }else{
                        sname=s1[1]+" "+s1[2];
                    }
                    Station tempS=new Station(Integer.parseInt(s1[0]),sname);
                    allStations.add(tempS);      
            }      
            }catch(Exception ioe){
                System.out.println(ioe.toString());
            }
            
            try{
                File file2=new File("Schedule.txt");
                Scanner input2=new Scanner(file2);
                
                while(input2.hasNextLine()){                   
                    String[] s2=input2.next().split(",|:");
                    for(int i=0;i<21;i++){
                        if(Integer.parseInt(s2[0])==i){
                            allStations.get(i).addBusSchedule(new ScheduleInfo(Integer.parseInt(s2[1]),Integer.parseInt(s2[2]),LocalTime.of(Integer.parseInt(s2[3]),Integer.parseInt(s2[4]))));
                        }    
                    }
                }
                
            }catch(Exception ioe){
                System.out.println(ioe.toString());
            }
            
            try{
                File file3=new File("rID.txt");
                Scanner input3=new Scanner(file3);
                
                while(input3.hasNextLine()){                   
                    String[] s3=input3.next().split(",");
                    for(int i=0;i<21;i++){
                        if(Integer.parseInt(s3[0])==i){
                            allStations.get(i).addRid(Integer.parseInt(s3[1]));
                        }    
                    }
                }
                
            }catch(Exception ioe){
                System.out.println(ioe.toString());
            }          
            
            /*for(Station si:allStations){
               System.out.println(si.getBusSchedule().get(1).getScheduleInfo());
               System.out.println(si.getRid().get(0));
            }*/
    }
    
    public static void writePassengerToFile(){
        try{
            FileOutputStream fos=new FileOutputStream("Passengers.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(TransSystem.getPassengers());
            oos.writeObject(Passenger.getJ());
            oos.writeObject(TransSystem.getProviders());
            oos.writeObject(CarpoolProvider.getI());
            oos.writeObject(TransSystem.getCarpools());
            
            if(fos!=null) fos.close();
            if(oos!=null) oos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private static void readPassengerToSystem(){
        try{
            FileInputStream fos=new FileInputStream("Passengers.dat");
            ObjectInputStream oos=new ObjectInputStream(fos);

            TransSystem.setPassengers((ArrayList<Passenger>)(oos.readObject()));
            Passenger.setJ((Integer)oos.readObject());
            TransSystem.setProviders((ArrayList<CarpoolProvider>)(oos.readObject()));
            CarpoolProvider.setI((Integer)oos.readObject());
            TransSystem.setCarpools((ArrayList<Carpool>)(oos.readObject()));
            
            if(fos!=null) fos.close();
            if(oos!=null) oos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Station> searchBusRoute(String sName,String dName){//find the station list that from start station to destination station
            ArrayList<Station> s=new ArrayList<Station>();
            GaineBusStationMap gbsm=new GaineBusStationMap();
            Map g=gbsm.GetGaineBusStationMap();
            FindSP bfs = new FindSP(g, getStation(sName).getSid());
            Stack<Integer> al=bfs.pathTo(getStation(dName).getSid());
            while(!al.empty()){
                Station temp=getStation(al.pop());
                s.add(temp);
            }
            return s;
    }
    
    public static int calculateRunningTime(String sName,String dName){
            ArrayList<Station> s=new ArrayList<Station>();
            GaineBusStationMap gbsm=new GaineBusStationMap();
            Map g=gbsm.GetGaineBusStationMap();
            FindSP bfs = new FindSP(g, getStation(sName).getSid());
            return bfs.distTo(getStation(dName).getSid());
    }
    
    public static ArrayList<Station> findTransferStation(ArrayList<Station> s){//find transfer stations
        ArrayList<Station> sr=new ArrayList<Station>();
        if(s.size()>2){
            for(int i=1;i<s.size()-1;i++){
                ArrayList<Integer> s1=s.get(i-1).getRid();
                ArrayList<Integer> s2=s.get(i).getRid();
                ArrayList<Integer> s3=s.get(i+1).getRid();
                if(s2.size()==2){
                    if((s2.get(0)+s2.get(1))==s1.get(0)+s3.get(0))
                        sr.add(s.get(i));
                }
            }
        }
        return sr;
    }
    
    public static int calcuateBusPathRouteRate(ArrayList<Station> s){//calcuate the fee that customer need to pay by taking bus
        int i=2;
        if(s.size()==0)
            return i;
        for(int j=0;j<s.size();j++){
            i=i+2;
        }
        return i;
    }
    
    public static ArrayList<Integer> findRoute(Station s,Station d,ArrayList<Station> ts){  //use start station and transfer station list to calculate route list
        ArrayList<Integer> rl=new ArrayList<Integer>();
        if(ts.size()==0){ //put the first route id to the list
            if(s.getRid().size()==1){
                rl.add(s.getRid().get(0));
            }
            if(s.getRid().size()==2){
                if(d.getRid().size()==1){
                    rl.add(d.getRid().get(0));
                }else{
                    for(int x: d.getRid()){
                        if(s.getRid().get(0)==x){
                           rl.add(s.getRid().get(0));
                        }
                        if(s.getRid().get(1)==x){
                           rl.add(s.getRid().get(1));
                        }
                    }
                }
            }
        }else{
            if(s.getRid().size()==1){
                rl.add(s.getRid().get(0));
            }
            if(s.getRid().size()==2){
                for(int x:ts.get(0).getRid()){
                    if(s.getRid().get(0)==x){
                        rl.add(s.getRid().get(0));
                    }
                    if(s.getRid().get(1)==x){
                        rl.add(s.getRid().get(1));
                    }
                }
            }
            
            for(Station x : ts){
                    if(x.getRid().get(0)!=rl.get(rl.size()-1)){
                        rl.add(x.getRid().get(0));
                    }else{
                        rl.add(x.getRid().get(1));
                    }
            }
                
        }
  
        return rl;
        
    }
    
    public static ArrayList<Integer> findBusList(Station s,Station d,ArrayList<Station> ts,ArrayList<Integer> rl){
        ArrayList<Integer> bl=new ArrayList<Integer>();
        if(ts.size()==0){
            if(rl.get(0)==1){
                if(s.getSid()-d.getSid()<0){
                    bl.add(1);
                }else{
                    bl.add(2);
                }
            }if(rl.get(0)==2){
                
                if(s.getSid()==6){
                    if(d.getSid()>12){
                        bl.add(3);
                    }
                        bl.add(4);
                }else if(d.getSid()==6){
                    if(s.getSid()>12){
                        bl.add(4);
                    }
                    bl.add(3);
                }else{
                    if(s.getSid()-d.getSid()<0){
                        bl.add(3);
                    }
                    bl.add(4);
                }
                
            }if(rl.get(0)==3){
                if(s.getSid()==3&&d.getSid()==10){
                    bl.add(5);
                }
                if(s.getSid()==10&&d.getSid()==3){
                    bl.add(6);
                }
                if(s.getSid()==3){
                    if(d.getSid()>18){
                        bl.add(5);
                    }
                    bl.add(6);
                }
                if(s.getSid()==10){
                    if(d.getSid()>19){
                        bl.add(5);
                    }
                    bl.add(6);
                }
                if(d.getSid()==4){
                    if(s.getSid()>18)
                        bl.add(6);
                    bl.add(5);
                }
                if(d.getSid()==10){
                    if(s.getSid()>19)
                        bl.add(6);
                    bl.add(5);
                }
                if(s.getSid()-d.getSid()<0){
                    bl.add(5);
                }else{
                    bl.add(6);
                }
            }
        }
        if(ts.size()==1){
            if(ts.get(0).getSid()==3){
                if(s.getSid()==6){
                    bl.add(2);
                }else if(s.getSid()==10){
                    bl.add(6);
                }else if(s.getSid()<3){
                    bl.add(1);
                }else if(s.getSid()>3&&s.getSid()<8&&s.getSid()!=6){
                    bl.add(2);
                }else if(s.getSid()>14&&s.getSid()<19){
                    bl.add(5);
                }else if(s.getSid()>19){
                    bl.add(6);
                }
                
                if(d.getSid()==6){
                    bl.add(1);
                }else if(d.getSid()==10){
                    bl.add(5);
                }else if(d.getSid()<3){
                    bl.add(2);
                }else if(d.getSid()>3&&d.getSid()<8&&d.getSid()!=6){
                    bl.add(1);
                }else if(d.getSid()>14&&d.getSid()<19){
                    bl.add(6);
                }else if(d.getSid()>19){
                    bl.add(5);
                }
            }
            if(ts.get(0).getSid()==6){
                if(s.getSid()==3){
                    bl.add(1);
                }else if(s.getSid()==10){
                    bl.add(4);
                }else if(s.getSid()!=3&&s.getSid()<6){
                    bl.add(1);
                }else if(s.getSid()==7){
                    bl.add(2);
                }else if(s.getSid()>6&&s.getSid()<13&&s.getSid()!=10){
                    bl.add(3);
                }else if(s.getSid()>12&&s.getSid()<15){
                    bl.add(4);
                }
                
                if(d.getSid()==3){
                    bl.add(2);
                }else if(d.getSid()==10){
                    bl.add(3);
                }else if(d.getSid()!=3&&d.getSid()<6){
                    bl.add(2);
                }else if(d.getSid()==7){
                    bl.add(1);
                }else if(d.getSid()>6&&d.getSid()<13&&d.getSid()!=10){
                    bl.add(4);
                }else if(d.getSid()>12&&d.getSid()<15){
                    bl.add(3);
                }
            }
            if(ts.get(0).getSid()==10){
                if(s.getSid()==3){
                    bl.add(5);
                }else if(s.getSid()==6){
                    bl.add(3);
                }else if(s.getSid()<20&&s.getSid()>14){
                    bl.add(5);
                }else if(s.getSid()==20){
                    bl.add(6);
                }else if(s.getSid()>7&&s.getSid()<10){
                    bl.add(3);
                }else if(s.getSid()>10&&s.getSid()<15){
                    bl.add(4);
                }
                
                if(d.getSid()==3){
                    bl.add(6);
                }else if(d.getSid()==6){
                    bl.add(4);
                }else if(d.getSid()<20&&d.getSid()>14){
                    bl.add(6);
                }else if(d.getSid()==20){
                    bl.add(5);
                }else if(d.getSid()>7&&d.getSid()<10){
                    bl.add(4);
                }else if(d.getSid()>10&&d.getSid()<15){
                    bl.add(3);
                }
            }
        }
        if(ts.size()==2){
            if(ts.get(0).getSid()==3&&ts.get(1).getSid()==6){
                if(s.getSid()==6){
                    bl.add(2);
                }else if(s.getSid()==10){
                    bl.add(6);
                }else if(s.getSid()<3){
                    bl.add(1);
                }else if(s.getSid()>3&&s.getSid()<8&&s.getSid()!=6){
                    bl.add(2);
                }else if(s.getSid()>14&&s.getSid()<19){
                    bl.add(5);
                }else if(s.getSid()>19){
                    bl.add(6);
                }
                
                bl.add(1);
                
                if(d.getSid()==3){
                    bl.add(2);
                }else if(d.getSid()==10){
                    bl.add(3);
                }else if(d.getSid()!=3&&d.getSid()<6){
                    bl.add(2);
                }else if(d.getSid()==7){
                    bl.add(1);
                }else if(d.getSid()>6&&d.getSid()<13&&d.getSid()!=10){
                    bl.add(4);
                }else if(d.getSid()>12&&d.getSid()<15){
                    bl.add(3);
                }            
            }else if(ts.get(0).getSid()==6&&ts.get(1).getSid()==3){
                if(s.getSid()==3){
                    bl.add(1);
                }else if(s.getSid()==10){
                    bl.add(4);
                }else if(s.getSid()!=3&&s.getSid()<6){
                    bl.add(1);
                }else if(s.getSid()==7){
                    bl.add(2);
                }else if(s.getSid()>6&&s.getSid()<13&&s.getSid()!=10){
                    bl.add(3);
                }else if(s.getSid()>12&&s.getSid()<15){
                    bl.add(4);
                }
                
                bl.add(2);
                
                if(d.getSid()==6){
                    bl.add(1);
                }else if(d.getSid()==10){
                    bl.add(5);
                }else if(d.getSid()<3){
                    bl.add(2);
                }else if(d.getSid()>3&&d.getSid()<8&&d.getSid()!=6){
                    bl.add(1);
                }else if(d.getSid()>14&&d.getSid()<19){
                    bl.add(6);
                }else if(d.getSid()>19){
                    bl.add(5);
                }
            }else if(ts.get(0).getSid()==3&&ts.get(1).getSid()==10){
                if(s.getSid()==6){
                    bl.add(2);
                }else if(s.getSid()==10){
                    bl.add(6);
                }else if(s.getSid()<3){
                    bl.add(1);
                }else if(s.getSid()>3&&s.getSid()<8&&s.getSid()!=6){
                    bl.add(2);
                }else if(s.getSid()>14&&s.getSid()<19){
                    bl.add(5);
                }else if(s.getSid()>19){
                    bl.add(6);
                }
                                
                bl.add(5);
                
                if(d.getSid()==3){
                    bl.add(6);
                }else if(d.getSid()==6){
                    bl.add(4);
                }else if(d.getSid()<20&&d.getSid()>14){
                    bl.add(6);
                }else if(d.getSid()==20){
                    bl.add(5);
                }else if(d.getSid()>7&&d.getSid()<10){
                    bl.add(4);
                }else if(d.getSid()>10&&d.getSid()<15){
                    bl.add(3);
                }
            }else if(ts.get(0).getSid()==10&&ts.get(1).getSid()==3){
                if(s.getSid()==3){
                    bl.add(5);
                }else if(s.getSid()==6){
                    bl.add(3);
                }else if(s.getSid()<20&&s.getSid()>14){
                    bl.add(5);
                }else if(s.getSid()==20){
                    bl.add(6);
                }else if(s.getSid()>7&&s.getSid()<10){
                    bl.add(3);
                }else if(s.getSid()>10&&s.getSid()<15){
                    bl.add(4);
                }
                
                bl.add(6);
                
                if(d.getSid()==6){
                    bl.add(1);
                }else if(d.getSid()==10){
                    bl.add(5);
                }else if(d.getSid()<3){
                    bl.add(2);
                }else if(d.getSid()>3&&d.getSid()<8&&d.getSid()!=6){
                    bl.add(1);
                }else if(d.getSid()>14&&d.getSid()<19){
                    bl.add(6);
                }else if(d.getSid()>19){
                    bl.add(5);
                }
            }else if(ts.get(0).getSid()==6&&ts.get(1).getSid()==10){
                if(s.getSid()==3){
                    bl.add(1);
                }else if(s.getSid()==10){
                    bl.add(4);
                }else if(s.getSid()!=3&&s.getSid()<6){
                    bl.add(1);
                }else if(s.getSid()==7){
                    bl.add(2);
                }else if(s.getSid()>6&&s.getSid()<13&&s.getSid()!=10){
                    bl.add(3);
                }else if(s.getSid()>12&&s.getSid()<15){
                    bl.add(4);
                }
                                
                bl.add(4);
                
                if(d.getSid()==3){
                    bl.add(6);
                }else if(d.getSid()==6){
                    bl.add(4);
                }else if(d.getSid()<20&&d.getSid()>14){
                    bl.add(6);
                }else if(d.getSid()==20){
                    bl.add(5);
                }else if(d.getSid()>7&&d.getSid()<10){
                    bl.add(4);
                }else if(d.getSid()>10&&d.getSid()<15){
                    bl.add(3);
                }
            }else if(ts.get(0).getSid()==10&&ts.get(1).getSid()==6){
                if(s.getSid()==3){
                    bl.add(5);
                }else if(s.getSid()==6){
                    bl.add(3);
                }else if(s.getSid()<20&&s.getSid()>14){
                    bl.add(5);
                }else if(s.getSid()==20){
                    bl.add(6);
                }else if(s.getSid()>7&&s.getSid()<10){
                    bl.add(3);
                }else if(s.getSid()>10&&s.getSid()<15){
                    bl.add(4);
                }
                
                bl.add(3);
                
                if(d.getSid()==3){
                    bl.add(2);
                }else if(d.getSid()==10){
                    bl.add(3);
                }else if(d.getSid()!=3&&d.getSid()<6){
                    bl.add(2);
                }else if(d.getSid()==7){
                    bl.add(1);
                }else if(d.getSid()>6&&d.getSid()<13&&d.getSid()!=10){
                    bl.add(4);
                }else if(d.getSid()>12&&d.getSid()<15){
                    bl.add(3);
                }
            }
        }
        return bl;
    }
    
    public static int calculateAlertTotalTime(String s,String d,LocalTime arriveTime){
        LocalTime temTime=arriveTime;
        ArrayList<Integer> timeList=new ArrayList<Integer>();
        ArrayList<Station> transStation=findTransferStation(searchBusRoute(s,d));
        ArrayList<Integer> routeList=findRoute(getStation(s),getStation(d),transStation);
        ArrayList<Integer> busList=findBusList(getStation(s),getStation(d),transStation,routeList);
        
        //change transStation to a new station list to calculate total time
        transStation.add(0,getStation(s));
        transStation.add(getStation(d));
        
        int totalTime;
        if(transStation.size()==2){
            totalTime=calculateRunningTime(getStation(s).getName(),getStation(d).getName());
        }else{
             for(int i=transStation.size()-1;i>=1;i--){
            timeList.add(transStation.get(i).calculateAlertWaitingTime(busList.get(i-1), temTime));
            temTime=temTime.minusMinutes(timeList.get(transStation.size()-1-i)+calculateRunningTime(transStation.get(i-1).getName(),transStation.get(i).getName()));
            //System.out.println(temTime); 
            }
             totalTime=(arriveTime.getHour()*60+arriveTime.getMinute())-(temTime.getHour()*60+temTime.getMinute())+TransSystem.getStation(s).calculateAlertWaitingTime(busList.get(busList.size()-1), arriveTime);
        }

        return totalTime;
    }
    
    public static void setAlertBusRoute(String s,String d,LocalTime arriveTime,int aroundTime,int advancedTime){
        ArrayList<Station> sl=searchBusRoute(s,d);
        ArrayList<Station> tl=findTransferStation(sl);
        
    }
    
    public static int calculateTotalTime(String s,String d,LocalTime time){
        LocalTime temTime=time;
        ArrayList<Integer> timeList=new ArrayList<Integer>();
        ArrayList<Station> transStation=findTransferStation(searchBusRoute(s,d));
        ArrayList<Integer> routeList=findRoute(getStation(s),getStation(d),transStation);
        ArrayList<Integer> busList=findBusList(getStation(s),getStation(d),transStation,routeList);
        
        //change transStation to a new station list to calculate total time

        int totalTime;
        if(transStation.size()==0){
            totalTime=calculateRunningTime(getStation(s).getName(),getStation(d).getName());
        }else{
            transStation.add(0,getStation(s));
            transStation.add(getStation(d));
        
            for(int i=0;i<transStation.size()-1;i++){
            timeList.add(transStation.get(i).calculateWaitingTime(busList.get(i), temTime));
            temTime=temTime.plusMinutes(timeList.get(i)+calculateRunningTime(transStation.get(i).getName(),transStation.get(i+1).getName()));
            System.out.println(temTime); 
        }

          totalTime=(temTime.getHour()*60+temTime.getMinute())-(time.getHour()*60+time.getMinute());
        }

        
        return totalTime;
    } 
    
    public static ArrayList<Carpool> searchCarpool(String s,String d){
        ArrayList<Carpool> rs=new ArrayList<Carpool>();
        for(Carpool x: carpools){
            if(x.getSStation().equals(s)&&x.getDStation().equals(d)&&x.getStartTime().isAfter(LocalTime.now())){
                if(x.getSeatsAvailable()>0){
                    rs.add(x);
                }
                
            }
        }
        return rs;
    }
    
    public static ArrayList<Carpool> searchCarpool(String s, String d,LocalTime at,int adt){
        ArrayList<Carpool> rs=new ArrayList<Carpool>();
        for(Carpool x: carpools){
            if(x.getSStation().equals(s)&&x.getDStation().equals(d)&&x.getStartTime().isAfter(LocalTime.now())&&x.getArriveTime().minusMinutes(adt).isBefore(at)){
                if(x.getSeatsAvailable()>0){
                    rs.add(x);
                }
            }
        }
        return rs;
    }
    
    public static class TimerListener implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        if(plogin==1){
            for(Alert x:p.getAlerts()){
                if(x.getActive()==1&&x.getAlertTime().getHour()==LocalTime.now().getHour()&&x.getAlertTime().getMinute()==LocalTime.now().getMinute()){
                    JOptionPane.showMessageDialog(null, "You have to leave home to catch the bus at"+x.getsStation().getName());
                    x.setActive(0);
                }
            }
        }
        System.out.println("System runs "+systemRunningTime+" minutes!");
        TransSystem.systemRunningTimeAdd();
        }
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        TransSystem ts=new TransSystem();
        MainGUI n=new MainGUI();
        n.setVisible(true);
        Timer timer=new Timer(60*1000,new TimerListener());
        timer.start();
        
        System.out.println("System running:");
        System.out.println("Welcome to use Gainesville Transportation Assissant System !");
        
        //Collections.sort(passengers);
        //Collections.sort(passengers, new Passenger());
        //Collections.sort(passengers, new Passenger().new PassengerAlertSizeCompare());
        //for(Passenger x:passengers){
          //  System.out.println(x.getName());
        //}
      }       
}
