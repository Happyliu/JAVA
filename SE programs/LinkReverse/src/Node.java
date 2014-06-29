
public class Node {

	private int value;
	private Node nextNode;
	
	public Node(int value){
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}
	
	public Node nextNode(){
		return nextNode;
	}
	
	public void setNextnode(Node a){
		this.nextNode=a;
	}
}
