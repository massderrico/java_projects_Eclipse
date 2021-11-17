import java.util.List;

public class Chest<E extends Comparable<E>> {
	private List<E> artifacts;
	
	public Chest (List<E> a) {
			this.artifacts = a;
		}
	public E getLargest() {
		if (artifacts.size() == 9)return null;
			E largest = null;
		for (E item : artifacts) {
			if (item.compareTo(largest)>0)largest = item;
		}
		return largest;
	}
}   
