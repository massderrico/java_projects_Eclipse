import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    // TODO: implement the iterator class here
    // add your own helper methods if necessary
	
	private int index = 0;
	private LinkedList<T> result = new LinkedList<T>(); 
	
    public TSTIterator(TSTNode<T> rootN) {
    	inOrder(rootN);
    	
	}
    
    
    public void inOrder(TSTNode<T> rootN)
    {
    	if(rootN ==null) {
    		return;
    	}
    	//if(rootN != null && rootN.left == null && rootN.right == null && rootN.mid == null ) {
    		//	result.add(rootN.element);
    		//}
    		if (rootN.left != null) {
    	        inOrder(rootN.left);
    	    }
    	    if (rootN.mid != null) {
    	        inOrder(rootN.mid);
    	    }
    	    result.add(rootN.element);
    	    if (rootN.right != null) {
    	        inOrder(rootN.right);
    	    }
    	    

    	    
    	}

    
    
    
    
    
    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (index < result.size()){
        	return true;
        }
    	return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
       T e = result.get(index);
       index += 1;
       return e;
    }
}