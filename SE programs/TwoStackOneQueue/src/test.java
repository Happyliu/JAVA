import java.util.ArrayList;
import java.util.List;


public class test {

	public static void main(String[] args){
		TwoStackOneQueue q=new TwoStackOneQueue();
		q.push(1);
		q.push(2);
		q.push(3);
		
		System.out.println(q.pull());
		TwoStackOneQueue queue=new TwoStackOneQueue(); 
        List<Integer> re=new ArrayList<Integer>();  
        queue.push(1);  
        queue.push(2);  
        queue.push(3);  
        re.add(queue.pull());  
        queue.push(4);  
        re.add(queue.pull());  
        queue.push(5);  
        re.add(queue.pull());  
        re.add(queue.pull());  
        re.add(queue.pull());  
        System.out.println(re.toString());  
	}
}
