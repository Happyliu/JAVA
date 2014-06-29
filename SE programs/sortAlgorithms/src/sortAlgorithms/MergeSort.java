package sortAlgorithms;

public class MergeSort {


	public double[] mergeSort(double[] a){
		if(a.length<=1){
			return a;
		}
		int mid=a.length/2;
		double[] left=new double[mid];
		double[] right;
		if(a.length%2==0){
			 right=new double[mid];
		}else{
			 right=new double[mid+1];
		}
		 double[] result=new double[a.length];
		 
		 for(int i=0;i<mid;i++){
			 left[i]=a[i];
		 }
		 int x=0;
		 for(int i=mid;i<a.length;i++){
			 right[x]=a[i];
			 x++;
		 }
		 
		 left=mergeSort(left);
		 System.out.print("left");
		 right=mergeSort(right);
		 System.out.print("right");
		 
		 result=merge(left,right);
		 return result;
	}
	
	public double[] merge(double[] left,double[] right){
		int indexl=0;
		int indexr=0;
		int indexre=0;
		System.out.println();
		double[] result=new double[left.length+right.length];
		
		while(indexl<left.length||indexr<right.length){
			if(indexl<left.length && indexr<right.length){
				if(left[indexl]<=right[indexr]){
					result[indexre]=left[indexl];
					indexre++;
					indexl++;
				}else{
					result[indexre]=right[indexr];
					indexre++;
					indexr++;
				}
			}else if(indexl<left.length){
				result[indexre]=left[indexl];
				indexre++;
				indexl++;
			}else{
				result[indexre]=right[indexr];
				indexre++;
				indexr++;
			}
		}
		return result;
	}
	
	
	
}
