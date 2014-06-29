import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class MapDemo {

	public static void main(String[] args){
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("01", "mike");
		map.put("02", "bob");
		map.put("03", "amy");
		map.put(null, "john");
		
		//System.out.println(map.get("02"));
		//Collection<String> col=map.values();
		Set<String> keyset=map.keySet();
		Iterator<String> it=keyset.iterator();
		while(it.hasNext()){
			String key=it.next();
		   System.out.println(key+" "+map.get(key));
		}
		
		String s="hello today is a nice day,and I want to go shopping ! do you want to go with.me!";
		StringTokenizer st=new StringTokenizer(s,". !",false);
		while(st.hasMoreTokens())
			System.out.println(st.nextToken());
		
	}
}
