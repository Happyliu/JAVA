/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author liuzhao
 */
public class Map {
    
    	private final int V;
	private int E;
	private ArrayList<Integer>[] adj;
	
	public Map(int V){
		if(V<0){
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		}
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
	}
	
	public Map(Map G){
		this(G.V());
		this.E=G.E();
		for(int v=1;v<G.V();v++){
			Stack<Integer> reverse=new Stack<Integer>();
			for(int w:G.adj[v]){
				reverse.push(w);
			}
			for(int w:reverse){
				adj[v].add(w);
			}
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v,int w){
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
        E++;
        adj[v].add(w);
        adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v){
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        return adj[v];
	}
	
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
