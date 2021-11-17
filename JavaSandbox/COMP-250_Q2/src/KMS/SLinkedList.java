package KMS;

public class SLinkedList {
	private SNode head;
	
	private class SNode {
		String element;
		SNode next;
	}
	
	private void swap (SNode node1, SNode node2) {
		String temp = node1.element;
		node1.element= node2.element;
		node2.element = temp;
	}
	
	public void swapPairs() {
		SNode node = this.head;
		while(node.next.next != null) {
			swap(node, node.next);
			node = node.next.next;
		}
	}
	
	
}
