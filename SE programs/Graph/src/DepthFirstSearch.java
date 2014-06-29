
public class DepthFirstSearch {

	private boolean marked[];
	private int count;
	
	public DepthFirstSearch(Graph G,int s){
		marked=new boolean[G.V()];
		dfs(G,s);
	}
	
	public void dfs(Graph G,int v){
		count++;
		marked[v]=true;
		for(int w:G.adj(v)){
			if(marked[w]==false)
			dfs(G,w);
		}
	}
	
	public int count(){
		return count;
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	

}
