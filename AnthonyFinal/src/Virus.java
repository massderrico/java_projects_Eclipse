
public class Virus {
	String name;
	int duration;
	boolean viral;
	
	public Virus() {
		// TODO Auto-generated constructor stub
	}
	public Virus(String name,int duration, boolean viral ) {
		this.name = name;
		this.duration = duration;
		this.viral = viral;
	}
	public String getName() {
		return name;
	}
	public int getDuration() {
		return duration;
	}
	public boolean getViral() {
		return viral;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setViral(boolean viral) {
		this.viral = viral;
	}
	
	public String toString() {
		return "Virus name: " +this.name + "\nDuration of virus: " + this.duration+ "\nIs virus Viral: " + this.viral;
	}
}
