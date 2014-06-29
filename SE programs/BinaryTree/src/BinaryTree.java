
public class BinaryTree {
	
	public static void main(String[] args){
		Tree tr=new Tree();
		tr.insert(12, "Howard");
		tr.insert(8,"Kobe");
		tr.insert(13, "Harden");
		tr.insert(23, "james");
		tr.insert(23,"jardon");
		
		tr.display(tr.root);
		tr.find(2);
	}
	
}
