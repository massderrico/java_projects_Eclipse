import java.util.ArrayList;
import java.util.List;

public class Person {
	String name;
	int age;
	
	
	public Person(String n,int a) {
		this.name  = n;
		this.age = a;
		
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("Jenny", 30);
		Person p2 = new Person("Kim", 35);
		Person p3 = new Person("Jenny", 20);
		Person p4 = new Person("Jenny", 30);
		List<Person> people = new ArrayList<>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);
		
		for (int k=0;k<people.size();k++) {
			System.out.println(people.get(k).name + "," + people.get(k).age + "\n");
		}
		
		for (int i=0;i<people.size();i++) {
			for (int j=0 ; j< people.size();j++) {
				if(i!= j) {
					if(people.get(i).name.equals(people.get(j).name) && people.get(i).age == people.get(j).age){
						people.remove(j);
					}
				}
				
			}
			
			
		}
		for (int k=0;k<people.size();k++) {
			System.out.println(people.get(k).name + "," + people.get(k).age + "\n");
		}
		
		
		String[] ppl = new String[3];
		
		
	}
	
	
}
