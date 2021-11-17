import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	
	// Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf

		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}

		
		// this method takes in a datalist (ArrayList of type datum). It returns the calling DTNode object 
		// as the root of a decision tree trained using the datapoints present in the datalist variable and minSizeDatalist.
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {

			if (datalist.size() >= minSizeDatalist) {
				int[] votes = new int[2];
				for (Datum data : datalist)
				{
					votes[data.y]+=1;
				}
				if((votes[0] == datalist.size() && votes[1] == 0)||(votes[1] == datalist.size() && votes[0] == 0)) { //if all data have the same label,set node to leaf with that label 
					this.leaf = true;
					this.label = findMajority(datalist);
					return this;
					
				}
				else {
					double[] attAndThresh = findBestSplit(datalist); //get best split attribute, threshold and entropy
					int bestAttribute = (int)attAndThresh[0];
					double bestThreshold = attAndThresh[1];
					double bestAvgEntropy = attAndThresh[2];
					
					if(bestAvgEntropy >= calcEntropy(datalist)) { // if entropy of current data set is the same or greater than the split, make leaf node
						this.leaf = true;
						this.label = findMajority(datalist);
						return this;
					}

					this.leaf = false;
					this.attribute = bestAttribute;
					this.threshold = bestThreshold;
					ArrayList<Datum> data1 = new ArrayList<Datum>();
					ArrayList<Datum> data2 = new ArrayList<Datum>();
					
					for (Datum data : datalist) { // split the current data set based off the threshold
						if (data.x[this.attribute] < this.threshold){
							data1.add(data);
						}
						else {
							data2.add(data);
						}
					}
					DTNode leftNode = new DTNode(); 
					DTNode rightNode = new DTNode();
					this.left = leftNode.fillDTNode(data1); // create left node based off of data1
					this.right = rightNode.fillDTNode(data2); // create right node based off of data2
					return this;
					//}
				}
			}
			else {
				this.leaf = true; // if datalist has less than k elements, made leaf node set to majority label
				this.label = findMajority(datalist);
				return this;
			}
						
			
		}



		// This is a helper method. Given a datalist, this method returns the label that has the most
		// occurrences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist) {
			
			int [] votes = new int[2];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			
			if (votes[0] >= votes[1])
				return 0;
			else
				return 1;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			
			if (this.leaf == true) { //if current node is leaf return label
				return this.label; 
			}
			else{
				if(xQuery[this.attribute] < this.threshold) { // if current node is an internal node
					return this.left.classifyAtNode(xQuery); // go left if threshold is less than current value of attribute
				}
				else {
					return this.right.classifyAtNode(xQuery);// go right otherwise
				}
				
			}
		
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{ 
			if (dt2 instanceof DTNode) { // if input is instance of DTNode 
				
				if (this.leaf == true && ((DTNode)dt2).leaf == true) { // if current node and input are leaves
						return this.label == ((DTNode)dt2).label; // return comparison of labels
					
				}
				else if (this.leaf == false && ((DTNode)dt2).leaf == false) {
					if ((this.attribute == ((DTNode)dt2).attribute) && (this.threshold == ((DTNode)dt2).threshold)) { //if bode node are internal nodes
						return (this.left.equals(((DTNode)dt2).left)) && (this.right.equals(((DTNode)dt2).right)); // compare children
						}
					}
				
				}
				return false;
				
			
			}
	
	}

	/*
	 * this method finds best split
	 */
	private double[] findBestSplit(ArrayList<Datum> datalist){
		double[] split = new double[3];
		double bestAvgEntropy = Double.MAX_VALUE;
		int bestAttribute = -1;
		double bestThreshold = -1;
		
		for (int i = 0; i < datalist.get(0).x.length; i ++) { // x1, x2 etc
			for(int j = 0; j< datalist.size(); j++ ) { // checks threshold
				ArrayList<Datum> data1 = new ArrayList<Datum>();
				ArrayList<Datum> data2 = new ArrayList<Datum>();
				
				for(int k = 0; k< datalist.size(); k++ ) {	// iterates through datalist
					if (datalist.get(k).x[i] < datalist.get(j).x[i]) { // if data is less than threshold
						data1.add(datalist.get(k)); // add to data1
					}
					else {
						data2.add(datalist.get(k)); //add to data2
					}
					
				}
				if((data1.size() > 0) && (data2.size() > 0)){
				double currentAvgEntropy = calcAverageEntropy(data1, data2);
				

				if (bestAvgEntropy > currentAvgEntropy) {
					bestAvgEntropy = currentAvgEntropy;
					bestAttribute = i;
					bestThreshold = datalist.get(j).x[i];
				}
				}
		}	
		}
		split[0] = bestAttribute;
		split[1] = bestThreshold;
		split[2] = bestAvgEntropy;
		return split;
	}
	
	/*
	 * this method calculates the average entropy given 2 data sets from a split
	 */
	private double calcAverageEntropy(ArrayList<Datum> data1, ArrayList<Datum> data2) {
		double totalnumData = data1.size() + data2.size(); 
		double weight1 =  ((double)data1.size())/totalnumData;
		double weight2 =  ((double)data2.size())/totalnumData;
		double entropy1 = (weight1*calcEntropy(data1));
		double entropy2 = (weight2*calcEntropy(data2));
		double averageEnt  = entropy1 + entropy2;
		return averageEnt;
	}

	//Given a dataset, this returns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist) {
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		return this.rootDTNode.classifyAtNode( xQuery );
	}

	// Checks the performance of a DecisionTree on a dataset
	// This method is provided in case you would like to compare your
	// results with the reference values provided in the PDF in the Data
	// section of the PDF
	String checkPerformance( ArrayList<Datum> datalist) {
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
