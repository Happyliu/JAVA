import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


public class Dijkstra {

	private int minDis=0;
	private int maxDis=Integer.MAX_VALUE;
	
	//a adjacency Matrix that contains the whole graph
	int[][] matrix;
	
	//start point
	int startIndex;
	
	//store distances from start point to all points
	HashMap<Integer,Integer> allDisMap=new HashMap<Integer,Integer>();
	
	//store the points which have find the shortest distance
	HashSet<Integer> findSet=new HashSet<Integer>();
	
	//load the matrix and start point
	public Dijkstra(int[][] matrix,int s){
		this.matrix=matrix;
		this.startIndex=s;
	}
	
	//
	public void find(){
		
		//initial the allDisMap
		for(int i=0;i<matrix.length;i++){	
			allDisMap.put(i,matrix[startIndex][i]);
		}
		//int j=0;
		while(findSet.size()!=matrix.length){
			int currentMinIndex=currentMinIndex();
			
			//for the currentMinIndex,we find the shortest path which though this point
			for(int i=0;i<matrix.length;i++){
				if(!findSet.contains(i)&&matrix[currentMinIndex][i]!=maxDis&&matrix[currentMinIndex][i]+allDisMap.get(currentMinIndex)<allDisMap.get(i)){
					allDisMap.put(i, matrix[currentMinIndex][i]+allDisMap.get(currentMinIndex));
				}
				

				findSet.add(currentMinIndex);
			}
			
			System.out.println("\nAs "+startIndex+" to "+currentMinIndex+" has the shortest distance,we though "+currentMinIndex+" to find the shortest path");
			System.out.println("--------------------------");
			display();
			//j++;
		}
	}
	
	//find a point(not in findSet) which has the shortest distance to start point
	public int currentMinIndex(){
		Iterator<Entry<Integer,Integer>> it=allDisMap.entrySet().iterator();
		
		int min=Integer.MAX_VALUE;
		int minIndex=-1;
		
		while(it.hasNext()){
			Entry<Integer,Integer> entry=it.next();
			if(!findSet.contains(entry.getKey())&&entry.getValue()<min){
				minIndex=entry.getKey();
				min=entry.getValue();
			}
		}
		
		return minIndex;
	}
	
	//display the result
	public void display(){
		Iterator<Entry<Integer,Integer>> it=allDisMap.entrySet().iterator();
		
		while(it.hasNext()){
			Entry<Integer,Integer> entry=it.next();
			System.out.println(startIndex+"---->"+entry.getKey()+":"+entry.getValue());
		}
	}
	
	
	
	
	
	
	
}















