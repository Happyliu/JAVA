
public class test {

    public static void main(String[] args) {

    	Graph g=new Graph(10);
    	g.addEdge(3,5);
    	g.addEdge(3,6);
    	g.addEdge(3,7);
    	g.addEdge(1,2);
    	g.addEdge(2,5);
    	g.addEdge(1,6);
    	g.addEdge(1,7);
    	g.addEdge(1,9);
    	
    	g.toString();
    	System.out.println(g.toString());
        DepthFirstSearch search = new DepthFirstSearch(g,3);
        for (int v = 0; v < g.V(); v++) {
            if (search.marked(v))
                System.out.print(v + " ");
        }
    	
        System.out.println();
        if (search.count() != g.V()) System.out.println("NOT connected");
        else                         System.out.println("connected");
    }
}
