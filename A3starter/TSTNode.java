// add your imports here

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here
    
    TSTNode(T element){
    	this.element = (T)element;	
    }
    
  

    TSTNode<T> findMax(){
    	TSTNode<T> returnNode = this;
    	
    	if(returnNode.element == null) {
    		return null;
    	}
    	else {
    		while (returnNode.right!=null) {
    			returnNode = returnNode.right;
    		}
    		
    	}

        return returnNode;
    }

    TSTNode<T> findMin(){
    	TSTNode<T> returnNode = this;
    	
    	if(returnNode.element == null) {
    		return null;
    	}
    	else {
    		while (returnNode.left!=null) {
    			returnNode = returnNode.left;
    		}
    		
    	}

        return returnNode;
    }

    
    int height(){
    	return getHeight(this);
    }
    
    private int getHeight(TSTNode<T> rootN) { //
       

        if (rootN == null) {
            return -1;
        } 
        else{
        	int maxLeft = getHeight(rootN.left);
        	int maxMid = getHeight(rootN.mid);
        	int maxRight = getHeight(rootN.right);
        	return 1 + Math.max(Math.max(maxLeft, maxMid),maxRight);
        }

        
    }
    

    // add your own helper methods if necessary
}