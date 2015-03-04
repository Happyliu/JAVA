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
public class GaineBusStationMap {
            
    public Map GetGaineBusStationMap(){
        Map g=new Map(21);
        
        //add route 1
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        
        //add route 2
        g.addEdge(8, 9);
        g.addEdge(9,10);
        g.addEdge(10, 11);
        g.addEdge(11, 12);
        g.addEdge(12, 6);
        g.addEdge(6, 13);
        g.addEdge(13, 14);
        
        //add route 3
        g.addEdge(15, 16);
        g.addEdge(16,17);
        g.addEdge(17, 18);
        g.addEdge(18, 3);
        g.addEdge(3, 19);
        g.addEdge(19, 10);
        g.addEdge(10, 20);
        
        return g;
}
}
