public class Insect
		{
		   public int position;
		   public boolean movingRight;

		   //Method to intilize initializes the Insect given a position.
		      

		public void position (int initialPosition)
		   {
		      position = initialPosition;
		      movingRight = true;
		   }
		   
		   //Changes the direction the Insect is moving.
		   public void turn() 
		   {
		      movingRight = !movingRight;
		      
		   }
		   
		   //Moves the Insect one unit in the current direction.
		  
		   public void move() 
		   {
		      if (movingRight) 
		      {
		         position++;
		      } 
		      else 
		      {
		         position--;
		      }
		      
		   }
		   
		   
		      //Reports the current position of the Insect.
		     
		   public int getPosition() 
		   {
		      return position;
		   }
		   
		   public static void main(String[] args)
		   {
			  int pos=20;
		      Insect myInsect = new Insect();
		      myInsect.position(pos);
		      myInsect.move();
		      myInsect.move();
		      myInsect.turn();
		      myInsect.move();
		      myInsect.turn();
		      myInsect.move();
		      myInsect.turn();
		      myInsect.move();
		      myInsect.move();
		      System.out.println("Expected position "+pos+"; Current position " 
		            + myInsect.getPosition()); 
		   }
		}
	


