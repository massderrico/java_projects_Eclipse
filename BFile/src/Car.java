
public class Car {
	
	private String color;
	private int wheel;
	
	public Car(String c, int w) {
		this.color = c;
		this.wheel = w;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String t1 = "hello";
		String t2 = "hello";
		Car Mazda = new Car("red", 22);
		Bike B1 = new Bike("peleton", 22, true);
		System.out.println(B1.getWheels());
		

		System.out.println(Mazda);
	}

	
	
}
