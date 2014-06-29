
public class test {

	public static void main(String[] args){
		int minDis=0;
		int maxDis=Integer.MAX_VALUE;
		
        int[][] input = new int[][] { { minDis, 2, maxDis, 1, maxDis, maxDis, maxDis }, { maxDis, minDis, maxDis, 3, 10, maxDis, maxDis },  
                { 4, maxDis, minDis, maxDis, maxDis, 5, maxDis }, { maxDis, maxDis, 2, minDis, 2, 8, 4 },  
                { maxDis, maxDis, maxDis, maxDis, minDis, maxDis, 6 }, { maxDis, maxDis, maxDis, maxDis, maxDis, minDis, maxDis },  
                { maxDis, maxDis, maxDis, maxDis, maxDis, 1, minDis } };  
        int[][] in=new int[][]{{minDis,3,5,8,maxDis},{3,minDis,6,4,11},{5,6,minDis,2,maxDis},{8,4,2,minDis,10},
        		{maxDis,11,maxDis,10,minDis}
        };
        
        //input and in are test matrix, you choose the start point
        Dijkstra path = new Dijkstra(input, 0); 
        long startTime=System.currentTimeMillis();
        path.find();  
        long endTime=System.currentTimeMillis();
        System.out.println("\nAfter the iterations,we get the final Result:");
        path.display(); 
        System.out.println("\nTime to calculate the matrix's shortest path is : "+(endTime-startTime)+"ms");
	}
}
