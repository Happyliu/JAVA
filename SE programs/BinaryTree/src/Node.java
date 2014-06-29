
public class Node {

	int key;
	String name;
	public Node leftChild;
	public Node rightChild;
	
	public Node(int key,String name){
		this.key=key;
		this.name=name;
	}
	
	public void print(){
		System.out.println(key+"has a value"+name);
	}
}
