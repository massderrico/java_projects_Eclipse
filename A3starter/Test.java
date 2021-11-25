
public class Test {
	public static void main(String[] args) {
		TST<Integer> Tree = new TST<Integer>();
		Tree.insert(5);
		Tree.insert(5);
		Tree.insert(3);
		Tree.insert(6);
		System.out.println(Tree.toString());
	}
}
