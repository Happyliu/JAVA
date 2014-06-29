import java.util.LinkedList;
import java.util.Queue;


public class BreathFirstPath {

	private static final int infi=Integer.MAX_VALUE;
	private boolean marked[];
	private int[] edgeTo;
	private int[] distTo;
	
	public BreathFirstPath(Graph g,int s){
		marked=new boolean[g.V()];
		distTo=new int[g.V()];
		edgeTo=new int[g.V()];
		bfs(g,s);
	}
	
	public void bfs(Graph g,int s){
		Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < g.V(); v++) distTo[v] = infi;
        distTo[s] = 0;
        marked[s] = true;
        q.add(s);
        
        while(!q.isEmpty()){
        	int v=q.poll();
        	for(int w:g.adj(v)){
        		if(!marked[w]){
        			edgeTo[w]=v;
        			distTo[w]=distTo[v]+1;
        			marked[w]=true;
        			q.add(w);
        		}
        	}
        }
	}
	
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    public int distTo(int v) {
        return distTo[v];
    }

    
    
    
}
