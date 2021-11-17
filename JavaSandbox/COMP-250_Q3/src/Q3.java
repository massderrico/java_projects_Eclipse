import java.util.Arrays;

public class Q3 {
	public static void main(String[] args) {
		int[] list = {0,4,5,9,8,3,2,1};
		boolean sorted = false;
		int start = 0;
		int end = list.length;
			while (sorted == false){
				sorted = true;
				for (int j=start; j<= end-2; j++){
					if (list[j] > list[j+1]){
						swap(list, j,j+1);
						sorted = false;
						}
				}
			if (sorted == true) {
				break;
			}
			end--;
			
		
			
			
			
			
			
			}
		System.out.println(Arrays.toString(list));
		System.out.println(sorted);
		System.out.println(start);
		System.out.println(end);
	}
			
    public static void swap(int[] list, int index1, int index2) {
		int tmp = list[index1];
		list[index1] = list[index2];
		list[index2] = tmp;
    }
			
}
