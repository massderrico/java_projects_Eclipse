
class Cyborg implements Human,Machine {

	public static void main(String[] args) {
		
		Human h = new Cyborg();
		if (h instanceof Cyborg)
		    h.act();


	}
	
	@Override
	public void think() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
