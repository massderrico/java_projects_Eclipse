import java.util.Arrays;

public class Quiz1 {

	public static void main(String[] args) {
		
		String [][] fruitBasket = {{"apple","pineapple"},{"pie","peas"}};
		for (int i=0; i< fruitBasket.length;i++) {
			String[] fruit = fruitBasket[i];
			for(int j= fruit.length-1; j>= 0; j--) {
				fruit[j] = fruit[j].toUpperCase();
				
			}
		}
		
		System.out.println(Arrays.deepToString(fruitBasket));
	}
	
	
	
	
	public static int findNextLargest(int upperBound, int[] array) {
		int largest = 0;
		for (int i=0; i < array.length; i++) {
			if (array[i] > array[largest] && array[i] < upperBound) {
				largest = i;
			}
		}
		return largest;
	}

	
	
	
		
		

}
