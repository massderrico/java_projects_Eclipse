package ca.mcgill.ecse.carshop.controller;

//used for transferring error messages
public class ErrorTransfer {
	public static String errorMessage = null;
   
	public static void setMessage(String s) {
		errorMessage = s;
	}
	
	public static String getErrorMessage() {
		return errorMessage;
	}
	
	public static void reset() {
		errorMessage = null;
	}
	
}
