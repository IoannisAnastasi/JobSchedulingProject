/**
 * @author Christoforos Kontzias 1134670
 * @date 21/02/2024
 * 
 */
package hw1;

/**
 * This class contains the negadecimal numbers and the algorithms to convert them to and from decimal/negadecimal
 */
public class NegadecimalNumber {
	private int negadecimal1;
	private int decimal1;
	/**
	 * constructor that initialises with value read from file
	 * @param neg1 negadecimal read from file
	 */
	public NegadecimalNumber(int neg1) {
		this.negadecimal1 = neg1;
		this.decimal1 = this.toDecimal(negadecimal1);
	}
	/**
	 * This method converts a number in base -10 to base 10
	 * @param negadecimalNum number in negadecimal form
	 * @return the number in decimal 
	 */
	public int toDecimal(int negadecimalNum) {
		int decimal = 0;
		int base = 1;
		while (negadecimalNum != 0) {
			int digit = negadecimalNum % 10; 
			negadecimalNum /= 10; 
			decimal += digit * base;
			base *= -10; 
		}
		return decimal;
	}
	/**
	 * This method converts a number in base 10 to base -10
	 * @param decimalNum number in decimal form
	 * @return the number in negadecimal
	 */
	public String toNegadecimal(int decimalNum) {
		String result = "";
		int number = decimalNum;
		while (number != 0) {
			int i = number % -10;
			number /= -10;
			if (i < 0) {
				i += Math.abs(-10);
				number++;
			}
			result = i + result;
		}
		return result;
	}
	/**
	 * getter method for negadecimal1 field
	 * @return the value of negadecimal1
	 */
	public int getNegadecimal1() {
		return this.negadecimal1;
	}
	/**
	 * getter method for decimal field
	 * @return the value of Decimal1
	 */
	public int getDecimal1() {
		return this.decimal1;
	}

}