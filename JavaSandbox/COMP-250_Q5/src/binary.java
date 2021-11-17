
public class binary {
	public static void main(String[] args) {
	int[] list1 = {-3,1,7,11,23,31,33,40,58,100};	
		binarySearch(list1, 7, 0, 9);
		System.out.println(left1 + "  " +right1);
	}
	
	static int mid;
	static int left1;
	static int right1;
	
	public static int binarySearch(int[] list, int key, int left,int right) {
		left1 = left;
		right1 = right;
		if (left <= right) {
			mid = (left + right)/2;
		if ( list[mid] == key )
		return mid;
		else if ( key < list[mid] )
		return binarySearch(list, key, left, mid - 1);
		else
		return binarySearch(list, key, mid + 1, right);
		}
		return -1;
		}   
}
