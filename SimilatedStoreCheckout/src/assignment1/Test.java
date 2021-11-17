package assignment1;



public class Test {

	public static void main(String[] args) {
		
		
		Basket basket = new Basket();
	     Fruit mkp1 = new Fruit("kiwi", 2.0, 100);
	     Fruit mkp2 = new Fruit("kiwi", 2.1, 100);
	     
	     basket.add(mkp1);
	     basket.add(mkp1);
	     basket.add(mkp1);
	     
	     System.out.println(basket.toString());
	     System.out.println(basket.remove(mkp1));
	     System.out.println(basket.remove(mkp1));
	     System.out.println("\n");
	     System.out.print(basket.toString());

        //System.out.println(Arrays.toString(basket.getProducts()));
	}

}
