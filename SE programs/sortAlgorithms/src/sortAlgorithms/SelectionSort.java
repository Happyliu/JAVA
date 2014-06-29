package sortAlgorithms;

public class SelectionSort {
	
	public void selectionSort(double[] arr){
		int step=0;
		double temp;
		for(int i=0;i<arr.length;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<arr[i]){
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
				step++;
			}
		}
		System.out.println(step);
	}

	public void insectionSort(double[] arr){
		double temp;
		int step=0;
		for(int j=1;j<arr.length;j++){
			for(int i=0;i<j;i++){
				if(arr[j]<arr[i]){
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
				step++;
			}
		}
		System.out.println(step);
	}
	
	public void insectionSort1(double[] arr){
		double temp;
		int step=0;
		int i,j;
		double t;
		for(j=1;j<arr.length;j++){
			t=arr[j];
			for(i=j;i>0;i--){
				if(t<arr[i-1]){
                     arr[i]=arr[i-1];
				}else{
					break;
				}
				step++;
			}
			arr[i]=t;
		}
		System.out.println(step);
	}
	
	
}
