package E3;

public class Book {
	public String title;
	public String author;
	public double price;
	
	public static void main(String[] args) {
	 Book b1 = new Book("Im not done", 25.0);
	 Book b2 =new Book("Just do it", 300.0);
	 Book b3 = new Book("This a book", "MD", 55.0);
	 System.out.println(b3.title);
	 System.out.println(b3.isMoreExpensive(b2));
	}
	
	public Book(String title, double price) {
		this.title = title;
		this.price = price;
		author = "Anonymous";
	}
	public Book(String title,String author, double price) {
		this.title = title;
		this.price = price;
		this.author = author;
	}
	
	public void onSale() {
		price = price/2;
	}

	 public boolean isMoreExpensive(Book book2) {
		if (this.price > book2.price) {
			return true;
		}
		return false;
	 
	 }
}
