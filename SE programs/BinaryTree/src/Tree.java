
public class Tree {

	public Node root;
	
	public void insert(int key,String value){
		Node newnode=new Node(key,value);
		Node current=root;
		Node parent;
		if(root==null){
			root=newnode;
			return;
		}else{
			while(true){
				parent=current;
				if(current.key>newnode.key){
					current=current.leftChild;
					if(current==null){
						parent.leftChild=newnode;
						return;
					}
				}else{
					current=current.rightChild;
					if(current==null){
						parent.rightChild=newnode;
						return;
					}
				}
				
			}
		}
	}
	
	public int find(int k){
		Node curr=root;
		while(curr.key!=k){
			if(curr.key>k){
				if(curr.leftChild!=null)
				     curr=curr.leftChild;
				else{
				  System.out.println("find nothing !");
				  break;
				}
			}
			if(curr.key<k){
				if(curr.rightChild!=null)
				    curr=curr.rightChild;
				else{
				  System.out.println("find nothing !");
				  break;
				}
			}
			if(curr.key==k){
				System.out.println("we find "+Integer.toString(k)+curr.name+" in the tree");
				return curr.key;
		}
		}
         return 0;
	}
	
	
	public void display(Node curr){
		if(curr!=null){
			display(curr.leftChild);
			System.out.println(curr.key+" "+curr.name);
			display(curr.rightChild);
		}
	}
}
