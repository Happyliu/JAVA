import java.util.Stack;


public class TwoStackOneQueue {

	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	
	public TwoStackOneQueue(){
		stack1=new Stack<Integer>();
		stack2=new Stack<Integer>();
	}
	
	public Integer pull(){
		Integer re=null;
		if(!stack2.empty())
		{
			re=stack2.pop();
		}else{
			while(!stack1.empty()){
				re=stack1.pop();
				stack2.push(re);
			}
		}
		return re;
	}
	
	public Integer push(int a){
		stack1.push(a);
		return a;
	}
}
