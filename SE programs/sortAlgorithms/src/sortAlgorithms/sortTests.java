package sortAlgorithms;

import java.util.ArrayList;

public class sortTests {

	public static void main(String args[]){
			/*double[] arr={2,1,4,2,1,2,1,2,1,3,2,3,3,7,65,4.2,34,4.3,2.3,5.2,6.5,34};
			//System.out.println(arr.length);
			SelectionSort ss=new SelectionSort();
			double[] test={2,7,6,5,4,7,6,5};
			BubbleSort bs=new BubbleSort();
			MergeSort ms=new MergeSort();
			for(double x:test){
				System.out.print(x+" ");
			}
			System.out.println();
			double[] res=ms.mergeSort(test);
			//bs.bubbleSort3(arr);
			//bs.bubbleSort2(arr);
			//bs.bubbleSort(arr);
			//ss.insectionSort1(arr);
			//ss.selectionSort(arr);
			for(double x:res){
				System.out.print(x+" ");
			}*/
			
			ArrayList<Integer> al=new ArrayList<Integer>();
			al.add(13);
			al.add(2);
			al.add(1);
			al.add(23);
			al.add(8);
			al.add(3);
			al.add(45);
			al.add(27);
			
			 for(int i=1;i<al.size();i++){
				 if(al.get(i)<al.get(i-1)){
					 int temp=al.get(i-1);
					 al.set(i-1, al.get(i));
					 al.set(i, temp);
				 }
			 }
			 
			 System.out.println(al);
			 
			
	}
}
