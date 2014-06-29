
public class Linkpractice {

	public static void main(String[] args){
		Node h1=new Node(1);
		Node h2=new Node(2);
		Node h3=new Node(3);
		Node h4=new Node(4);
		h1.setNextnode(h2);
		h2.setNextnode(h3);
		h3.setNextnode(h4);
		
		Node h=h4;
		Reverse(h1);
		while(h!=null){
			System.out.println(h.getValue());
			h=h.nextNode();
		}
	}
	
	public static void Reverse(Node head){
		if(head==null||head.nextNode()==null){
			return;
		}
		Reverse(head.nextNode());
		head.nextNode().setNextnode(head);
		head.setNextnode(null);
		//return reverseHead;
	}
}
