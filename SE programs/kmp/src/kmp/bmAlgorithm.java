package kmp;

public class bmAlgorithm {

	public static int dist(char c,char[] t){
		int n=t.length;
	    if(c==t[n-1]){
			return n;
		}
		for(int i=n;i>=1;i--){
			if(t[i-1]==c){
				return max(1,n-1-i);
			}
		}
		return n;
	}
	
	private static int max(int x,int y){
		if(x>=y)
			return x;
		else{
			return y;
		}
	}


	public static void bm(String target,String source){
		int tlen=target.length();
		int slen=source.length();
		char[] schat=source.toCharArray();
		char[] tchat=target.toCharArray();
		
		if(target==null||source==null){
			System.out.println("error");
		}
		if(slen>tlen){
			System.out.println("error");;//target string must be longer than source
		}
		
		int i=slen;
		int j,k=0;
		while(i<tlen){
			j=slen;
			while(j>0&&(tchat[i-1]==schat[j-1])){
				j--;
				i--;
			}
			if(0==j){
				k++;	
				System.out.println("find");
				int pos=i;
				i = i+slen+1;
			}else{
				i=i+dist(tchat[i-1],schat);
			}
		}

	}
	
	
}
