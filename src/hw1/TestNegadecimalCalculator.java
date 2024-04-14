/**
 * @author Christoforos Kontzias 1134670
 * @date 21/02/2024
 */
package hw1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class does the file handling and tests weather expressions are valid. If they are it outputs the expression.
 * If it is not it prompts an error
 */
public class TestNegadecimalCalculator {
	/**
	 * default constructor created to enable javadoc creation (it threw errors due to the lack of comments on the default constructor)
	 */
	public TestNegadecimalCalculator() {
		;
	}

	/**
	 * This method goes through the file until reaches the end and calls the processExpression method on each line
	 * @param fileName the name of the file in the project's directory containing the expressions
	 * 
	 */

	public static void readExpressionsFromFile(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {

				processExpression(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method is to process each line and check weather the expression is correct.
	 * @param expression the text file's current line
	 * 
	 */
	private static void processExpression(String expression) {  
		String num1="";
		String num2="";
		char operator=' ';
		boolean opFound= false;
		boolean errorFound=false;
		boolean divByZero = false;
		for(int i=0;i<expression.length(); i++) {
			if((expression.charAt(i)>='a' && expression.charAt(i)<='z') || (expression.charAt(i)>='A' && expression.charAt(i)<='Z') ) {
				errorFound=true;
			}
			//separate the 2 parts of the expression
			else if((expression.charAt(i)>='0' && expression.charAt(i)<='9') || expression.charAt(i)== ' ') {
				if(opFound == false)
					num1+=expression.charAt(i);
				else
					num2+=expression.charAt(i);
			}


			else if(expression.charAt(i)=='+' || expression.charAt(i)=='-' || expression.charAt(i)=='*' || expression.charAt(i)=='/') {
				if(opFound == true) { //if an operator was found previously it means the expression is wrong
					errorFound=true;
				}
				else {
					operator=expression.charAt(i);
					opFound = true;
				}

			}

		}
		for(int i=0; i<num1.length()-1; i++) {
			if(num1.charAt(i)==' ')
				errorFound=true;
		} if(num1.length()>1)
			if(num1.charAt(num1.length()-1)!=' ')
				errorFound=true;

		for(int i=1; i<num2.length()-1; i++) {
			if(num2.charAt(i)==' ')
				errorFound=true;
		}if(num2.length()>1)
			if(num2.charAt(0)!=' ')
				errorFound = true;
		if(num2.length()==2 &&num2.charAt(1)=='0' && operator=='/') {
			errorFound = true;
			divByZero = true;
		}
		if(errorFound == false) {
			String N1 = removeSpace(num1);
			String N2 = removeSpace(num2);
			//create new NegadecimalNumber objects to utilise the methods
			NegadecimalNumber n1 = new NegadecimalNumber(Integer.parseInt(N1));
			NegadecimalNumber n2 = new NegadecimalNumber(Integer.parseInt(N2));
			NegadecimalCalculator exp = new NegadecimalCalculator(n1,n2,operator);
			int decimalResult = exp.getDecimalResult();
			NegadecimalNumber fin = new NegadecimalNumber(decimalResult);

			System.out.println(num1 + operator + num2 + " = " + fin.toNegadecimal(decimalResult));
		}
		else if(divByZero)
			System.out.println("Wrong Expression. It attempted to divide by zero");
		else System.out.println("Wrong Expression");

	}
	/**
	 * This method removes spaces from the start and end of a String 
	 * @param input the String to be formatted
	 * @return the resulting String
	 */
	public static String removeSpace(String input) {// remove spaces from the beginning and end of the string                              
		return input.stripLeading().stripTrailing();
	}
	/**The main method is used to call the readExpressionsFromFile method
	 * @param args this is not used
	 */
	public static void main(String[] args) {
		String fileName = "input.txt";
		readExpressionsFromFile(fileName);

	}

}
