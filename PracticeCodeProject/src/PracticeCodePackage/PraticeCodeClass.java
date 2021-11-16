package PracticeCodePackage;

import java.util.Arrays;

public class PraticeCodeClass {
	

	static int counter = 0;
	static int swaps = 0;
	
	public static void main(String[] args) {
		int[] a = {1,4,2,5,8};
		int[] bubble = BubbleSort(a);
		System.out.println(Arrays.toString(bubble));
		System.out.println(swaps);
		System.out.println(counter);
		
	}
	
	public static int[] BubbleSort(int [] list) {
		boolean sorted = false;
		while(sorted == false)	
			for (int i = 0; i < list.length; i++) {
				for(int j = 0; j < list.length - i-1; j++) {
					if (list[j] > list[j+1]) {
						swap(list, j, j+1);
						swaps++;
					}
					else {
						sorted = true;
						
				}
			}
			counter++;
		}
		return list;
		
		
	}
	
	public static void swap(int[] list, int index1, int index2) {
		int tmp = list[index1];
		list[index1] = list[index2];
		list[index2] = tmp;

}
}

