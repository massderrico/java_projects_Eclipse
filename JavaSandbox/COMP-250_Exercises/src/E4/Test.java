package E4;

public class Test {
	
    public static void main(String[] args) {
		Sharer a = new Giver("Geoff", 10);
		Sharer b = new  Taker("Tina", 7);
		Sharer c = new Taker("Ted", 15);
		a.other = b; b.other = c; c.other = a;
		a.share(2); b.share(4); c.share(7);
		System.out.println( a.toString() + b.toString() + c.toString()); }
}