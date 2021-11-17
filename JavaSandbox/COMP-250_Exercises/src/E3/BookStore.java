package E3;

public class BookStore {
	String name;
	Book[] books;
	public static void main(String[] arg) {
		Book b1 = new Book("Im not done", 25.0);
		Book b2 =new Book("Just do it", 300.0);
		Book b3 = new Book("This a book", "MD", 55.0);
		Book[] bookarray1 = {b1,b2}; 
		Book[] bookarray2 = {b3};
		BookStore bs1 = new BookStore("biiger store", bookarray1);
		BookStore bs2 = new BookStore("small store", bookarray2);
	
		System.out.println(bs1.getRecommendation());
		System.out.println(BookStore.betterEquipped(bs1, bs2).name);
 }
	public BookStore(String name) {
		this.name = name;
	}
	public BookStore(String name, Book[] books ) {
		this.name = name;
		this.books = books;
	}
	public void sale() {
		for (int i=0; i< books.length; i++) {
			books[i].price = books[i].price/2;
		}
	}
	public String getRecommendation() {
		Book mostexpensive = new Book("titiel" ,0.0);
		for (int i = 0; i < this.books.length; i++) {
			if (books[i].price > mostexpensive.price) {
				mostexpensive = books[i];
			}
		}
	return mostexpensive.title;
	}
	public static BookStore betterEquipped(BookStore bst1, BookStore bst2) {
		BookStore better = (bst1.books.length > bst2.books.length) ? bst1 :bst2;
		return better;
	}
	
}
