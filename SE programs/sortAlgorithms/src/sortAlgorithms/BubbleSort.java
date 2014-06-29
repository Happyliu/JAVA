package sortAlgorithms;

public class BubbleSort {

	public void bubbleSort(double[] arr){
		double temp;
		int step=0;
		for(int j=0;j<arr.length;j++){
		for(int i=0;i<arr.length-1;i++){
			if(arr[i]>arr[i+1]){
				temp=arr[i+1];
				arr[i+1]=arr[i];
				arr[i]=temp;
			}
            step++;
		}
	}
		System.out.println(step);
	}
	
	public void bubbleSort1(double[] arr){//scan from two sides
		double temp;
		int step=0;
		int up=arr.length-1;
		int low=0;
		int index=0;
		while(low<up){
			for(int i=up;i>low;i--){
				if(arr[i-1]>arr[i]){
					temp=arr[i-1];
					arr[i-1]=arr[i];
					arr[i]=temp;
					index=i-1;
				}
				step++;
			}
			low=index;
			for(int i=low;i<up;i++){
				if(arr[i]>arr[i+1]){
					temp=arr[i+1];
					arr[i+1]=arr[i];
					arr[i]=temp;
					index=i;
				}
				step++;
			}
			up=index;
		}
		System.out.println(step);
	}
	
	 public void bubbleSort2(double[] arr){
		 double temp;
		 int step=0;
		 int m=arr.length-1;
		 while(m>0){
			  m=0;
			 for(int i=0;i<arr.length-1;i++){
				 if(arr[i]>arr[i+1]){
						temp=arr[i+1];
						arr[i+1]=arr[i];
						arr[i]=temp;
						m=i;
					}
				 step++;
			 }
		 }
		 System.out.println(step);
	 }
	 
	 public void bubbleSort3(double[] arr){
		 double temp;
		 int step=0;
		 boolean flag=true;//use to check if the arr has sorted
		 while(flag){
			 flag=false;
			 for(int i=0;i<arr.length-1;i++){
				 if(arr[i]>arr[i+1]){
						temp=arr[i+1];
						arr[i+1]=arr[i];
						arr[i]=temp;
						flag=true;
					}
				 step++;
			 }
		 }
		 System.out.println(step);
	 }
	 
}
