import java.util.ArrayList;
import java.util.HashMap;



public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0

		patients = new ArrayList<Patient>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
	    return 2*i;
	}

	private int rightChild(int i){
	    return 2*i+1;
	}
	
   private boolean isLeaf(int i){
        if ((i >= (patients.size() / 2)) && (i < patients.size()) && (i >= 1)) {
            return true;
        }
        return false;
    }
   
   private void swap(int i, int j){
	   
       Patient p1 = patients.get(i);
       Patient p2 = patients.get(j);
       String p1Name = p1.getName();
       String p2Name = p2.getName();
       
       patients.set(i, p2);
       patients.set(j, p1);
       nameToIndex.replace(p2Name, j, i);
       nameToIndex.replace(p1Name,i, j);

       
   }
   private boolean isEmpty() {
	   if (patients.size() > 1) {
		   return false;
	   }
	   else {
		   return true;
	   }
   }
    /*
    TODO: OPTIONAL
    TODO: Additional helper methods such as isLeaf(int i), isEmpty(), swap(int i, int j) could be useful for this assignment
     */

   
	public void upHeap(int i){
        	if(isEmpty()) {
        		return;
        	}
        	if (i <= 1) {
        		return;
        	}
        	else {
        		int p = parent(i);
			  
			  double pPriority = patients.get(p).getPriority();
			  double iPriority = patients.get(i).getPriority();
			  
			  if(pPriority <= iPriority) {
				 return;
			  }
			  else{
				  swap(i, p);
				  upHeap(p); 
			  }
			 
        	}
		
	}

	public void downHeap(int i){
		if(isEmpty()) {
    		return;
    	}
		if(isLeaf(i)) {
        	return;
        }
		if(i >= patients.size() || i < 1) {
			return;
		}
        int lcIndex = leftChild(i);
        int rcIndex = rightChild(i);
        
        if(lcIndex < patients.size() && rcIndex < patients.size()) {
        double lcPriority = patients.get(lcIndex).getPriority();
        double rcPriority = patients.get(rcIndex).getPriority();
        double iPriority = patients.get(i).getPriority();
        
        if(iPriority <= lcPriority && iPriority <= rcPriority) {
        	return;
        }
        else {
        if(lcPriority < iPriority && lcPriority <= rcPriority) {
        	swap(i, lcIndex);
        	downHeap(lcIndex);
        	
        }
        else if(rcPriority < iPriority && lcPriority > rcPriority) {
        	swap(i, rcIndex);
        	downHeap(rcIndex);
        	
        }
        }
        }
        else if(lcIndex == patients.size()-1) {
        double lcPriority = patients.get(lcIndex).getPriority();
        double iPriority = patients.get(i).getPriority();
        
        if(iPriority <= lcPriority) {
        	return;
        }
        else if(lcPriority < iPriority) {
        	swap(i, lcIndex);
        	downHeap(lcIndex);
        	
        }
        
        }
        

        
	}

	public boolean contains(String name){
		if(this.isEmpty()) {
    		return false;
    	}
		return  nameToIndex.containsKey(name);

	
	}

	public double getPriority(String name){
		if(this.isEmpty()) {
    		return -1;
    	}
		
        if(this.contains(name) == false) {
        	return -1;
        }
        else {
        	int p = nameToIndex.get(name);
        	return patients.get(p).getPriority();
        }
        
        
	}

	public double getMinPriority(){
		if(this.isEmpty()) {
			return -1;
		}
		else {
			return patients.get(1).getPriority();
		}
	}

	public String removeMin(){
        if(isEmpty()) {
        return null;
        }
        else {
        	String name = patients.get(1).getName();
//        	this.remove(name);
//        	return name;
        	swap(1, (patients.size()-1));
        	patients.remove(patients.size()-1);
        	nameToIndex.remove(name);
        	downHeap(1);
        	return name;
        }
	}

	public String peekMin(){
		if(this.isEmpty()) {
			return null;
		}
		else {
			return patients.get(1).getName();
		}
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */

	public boolean  add(String name, double priority){
        if(nameToIndex.containsKey(name))return false;
        
        Patient p = new Patient(name, priority);
        patients.add(p);
        nameToIndex.put(name, patients.size()-1);
        upHeap(patients.size()-1);
        return true;
        
		
		// TODO: Implement your code here & remove return statement
        
	}

	public boolean  add(String name){
        return add(name, Double.POSITIVE_INFINITY);
	}

	public boolean remove(String name){
		if(isEmpty()) {
	        return false;
	        }
		
		else if(contains(name)){
			int n = nameToIndex.get(name);
			if(nameToIndex.get(name) == patients.size()-1) {
			patients.remove(patients.size()-1);
        	nameToIndex.remove(name);
        	return !contains(name);
			}
			else {
				swap(n, patients.size()-1);
				patients.remove(patients.size()-1);
				nameToIndex.remove(name);
				downHeap(n);
				return !contains(name);
				
			}
	
			
		}
        return false;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	public boolean changePriority(String name, double priority){
        if(isEmpty()) {
        	return false;
        }
        if(!contains(name)) {
        	return false;
        }
        else {
        	int i = nameToIndex.get(name);
        	Patient current = patients.get(nameToIndex.get(name));
        	if(nameToIndex.get(name) == 1) {
        		patients.get(1).setPriority(priority);
        		downHeap(1);
        		return true;
        	}
        	else if (nameToIndex.get(name) > 1) {
				int p = parent(nameToIndex.get(name));
				Patient parent = patients.get(p);
				current.setPriority(priority);
        		
				if(parent.getPriority() <= current.getPriority()) {
					downHeap(i);
					return true;
				}
				else {
					upHeap(i);
					return true;
				}
			}
        	
        	
        	
        }
		// TODO: Implement your code here & remove return statement
        return false;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
		ArrayList<Patient> arr = new ArrayList<Patient>();
		if(isEmpty()) {
			return arr;
		}
		for (int i = 1; i < patients.size()-1; i++) {
			if(patients.get(i).getPriority() <= threshold) {
				arr.add(patients.get(i));
				this.remove(patients.get(i).getName());
			}
			
		}
		
        return arr;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
		ArrayList<Patient> arr = new ArrayList<Patient>();
		if(isEmpty()) {
			return arr;
		}
		for (int i = 1; i < patients.size()-1; i++) {
			if(patients.get(i).getPriority() >= threshold) {
				arr.add(patients.get(i));
				this.remove(patients.get(i).getName());
			}
		}
		
        return arr;
	}

	

	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
