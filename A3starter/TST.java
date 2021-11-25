import java.util.ArrayList;
import java.util.Iterator;


public class TST<T extends Comparable<T>> implements Iterable<T>{
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }

    // TODO: implement the tree class here

    public void insert(T element){
        this.root = insertHelper(root, element);
    }
    
    
    private TSTNode<T> insertHelper(TSTNode<T> rootN, T e) {
        
    	if(rootN == null && e != null) {
        	return new TSTNode<T>(e);
        }
        else {
        	int cmp = e.compareTo(rootN.element);
        	
        	if(cmp <0) {
        		rootN.left = insertHelper(rootN.left, e);
        	}
        	else if(cmp ==0) {
        		rootN.mid = insertHelper(rootN.mid, e);
        	}
        	else if(cmp > 0){
        		rootN.right = insertHelper(rootN.right, e);
			}
        }
    	return rootN;
    }

    public void remove(T element){
        
    }

    public boolean contains(T element){ 
    	return searchFirst(this.root, element);
    	
    }

    private boolean searchFirst(TSTNode<T> rootN, T e) { // returns first occurence of a node(exclude middle child)
       
        if(rootN == null || e == null) {
        	return false;
        }
        
    	int cmp = e.compareTo(rootN.element);
    	if(cmp == 0) {
    		return true;
    	}
    	else if(cmp < 0) {
    		return searchFirst(rootN.left, e);
    	}
    	else {
    		return searchFirst(rootN.right, e);
    	}
        
        
     
       
    }
    
    public void rebalance(){
    	TSTIterator<T> i = new TSTIterator<T>(root);
        ArrayList<T> arr = new ArrayList<T>();
        while(i.hasNext()) {
        	arr.add(i.next());
        }
        this.root = insertHelper(new TSTNode<T>(arr.get(sortedArrayToBST(arr, 0, arr.size()-1))),arr.get(sortedArrayToBST(arr, 0, arr.size()-1 )));
        
    }
    private int sortedArrayToBST(ArrayList<T> arr, int start, int end) {
    	 
        /* Base Case */
        if (start > end) {
            return (Integer) null;
        }
 
        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        
        
        /* Recursively construct the left subtree and make it
         left child of root */
        sortedArrayToBST(arr, start, mid - 1);
        
        /* Recursively construct the right subtree and make it
         right child of root */
        sortedArrayToBST(arr, mid + 1, end);
         
        return mid;
    }
    // add your own helper methods if necessary

    
    /**
     * Caculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height(){
        
       return root.height();
        
    }
    
    


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        // TODO: implement the iterator method here
        return new TSTIterator<T>(root);
    }

    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root,"", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     * @param buffer String buffer
     * @param node Root node
     * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
     */
    public void inorderPrintAsList(){
        String buffer = "[";
        for (T element: this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0,len-2);
        buffer += "]";
        System.out.println(buffer);
    }
    
   
    
    
    
    
}