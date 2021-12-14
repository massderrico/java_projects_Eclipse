import java.awt.Container;
import java.util.Scanner;

public class Exam_1
{
public static void main(String[] args) {
//Declare variables
Scanner keyIn = new Scanner(System.in);
String member, number; // save user's inputs
char[][] sequenceofMember; // save the correct sequence of members
int tag=0, index=0, indexMember=0, counter=0;

//Ask user for inputs
System.out.println("Please enter the members' first names: (follow by a space)");
member = keyIn.nextLine();

//Count the total number of members
while (indexMember < member.length()-1){
	if( member.charAt(indexMember) == ' '){
	counter ++;
	}
	indexMember++;
}

System.out.println("There are "+ (counter+1) + " member(s).");

System.out.println("Please enter the sequence number 1-"+(counter+1)+": (follow by a comma)");
number = keyIn.next();

//Save the info in 2-D char array
sequenceofMember = new char[counter+1][];

for(int indexNumber=0; indexNumber<number.length(); indexNumber ++)
	{for(index =0; index<member.length(); index++){ 
	if (member.charAt(index) == ' '|| index == member.length()-1){
	sequenceofMember[Integer.parseInt(number.substring(indexNumber, indexNumber+1))-1] = member.substring(tag, index++).toCharArray();
	tag = index;
	}
	}
}

//Display the info in 2-D array
System.out.println("Here is the sequence of the member(s): ");

for (int i=0; i < sequenceofMember.length; i++)
{
System.out.print("\nNo." + (i+1) + ": ");

for(int j=0; j< sequenceofMember[i].length; j++)
{
System.out.print(sequenceofMember[i][j]);
}
}

//Close the program
keyIn.close();
}
}
