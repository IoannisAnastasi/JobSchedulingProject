/**
 *@author Christoforos Kontzias 1134670
 * @date 21/02/2024 
 */
package hw1;

/**
 * This class contains the logic to perform the mathematical calculations utilising NegadecimalNmber objects
 */
public class NegadecimalCalculator {
	private NegadecimalNumber n1;
	private NegadecimalNumber n2;
	private char operator;
	/**This constructor initializes based on the modified inputs received as parameters
	 * @param neg1 negadecimal number 1
	 * @param neg2 negadecimal number 2
	 * @param op   the operator
	 */
	public NegadecimalCalculator(NegadecimalNumber neg1,NegadecimalNumber neg2, char op) {
		n1 = neg1;
		n2 = neg2;
		operator = op;
	}
	/**
	 * this method gives the result in decimal after the calculations
	 * @return the result in base 10 form after the calculations
	 */
	public int getDecimalResult() {
		String result="";

		switch (this.operator) {
		case '+': result = String.valueOf(this.n1.getDecimal1() + this.n2.getDecimal1()); break;
		case '-': result = String.valueOf(this.n1.getDecimal1() - this.n2.getDecimal1()); break;
		case '*': result = String.valueOf(this.n1.getDecimal1() * this.n2.getDecimal1()); break;
		case '/': 
			if (this.n2.getDecimal1() == 0) {    //to avoid division by 0
				result = "0"; 
			} else {
				result = String.valueOf(this.n1.getDecimal1() / this.n2.getDecimal1());
			}
			break;
		default:
			System.exit(-1);
		}
		int n = Integer.parseInt(result);
		return n;
	} 


}

