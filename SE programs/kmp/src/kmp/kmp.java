package kmp;

import java.util.Arrays;

public class kmp {

	public static void main(String args[]){
		final String s="ababcababcd";
		String sourc="ab";
		bmAlgorithm k=new bmAlgorithm();
		k.bm(s, sourc);
		System.out.println(kmp1(s,sourc));
	}
	
	public static int kmp1(String target,String source){
		int tarlength=target.length();
		int sourlength=source.length();
		int j=0;
		int k=0;
		int[] result=preProcess(source);
		for(int i=0;i<tarlength;i++){
			while(j>0&&source.charAt(j)!=target.charAt(i)){
				j=result[j-1];
			}
			if(source.charAt(j)==target.charAt(i)){
				j++;
			}
			if(j==sourlength){
				k++;
				j=result[j-1];
				System.out.println("find a match string");
			}
		}
		return k;
	}
	
	public static int[] preProcess(String source){
		int[] result=new int[source.length()];
		result[0]=0;
		int j=0;
		for(int i=1;i<source.length();i++){
			while(j>0&&source.charAt(j)!=source.charAt(i)){
				j=result[0];
			}
			if(source.charAt(j)==source.charAt(i)){
				j++;
			}
			result[i]=j;
		}
		System.out.println(Arrays.toString(result));
		return result;
	}
}
