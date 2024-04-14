/**
 * @author CHRISTOFOROS KONTZIAS 1134670
 * @date 23/02/2024
 * THIS CLASS IS RESPONSIBLE FOR TESTING EACH METHOD FOUND IN THE RichArray CLASS, USING DATA FROM THE EXAMPLES IN THE 
 * QUESTION.
 * 
 */
package hw1;
/**
 * THIS CLASS IS RESPONSIBLE FOR TESTING EACH METHOD FOUND IN THE RichArray CLASS, USING DATA FROM THE EXAMPLES IN THE 
 * QUESTION.
 */
public class RichArrayTest {
	/**
	 * the default constructor
	 */
	public RichArrayTest() {
		;  // this is used because I get javadoc errors if there is no user-defined default constructor
	}
	/**
	 * the testing and outputting of results is in the main class here
	 * @param args no arguments where used 
	 */
	public static void main(String[] args) {
		//test reverse
		int[][] dataForReverse = {{3, 1, 4, 1, 6}};
		RichArray reverseTest = new RichArray(dataForReverse);
		System.out.println("Reverse: " + reverseTest.reverse());

		//test rotateRight
		int[][] dataForRotateRight = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		RichArray rotateRightTest = new RichArray(dataForRotateRight);
		System.out.println("Rotate Right: " + rotateRightTest.rotateRight());

		//test rotateLeft
		RichArray rotateLeftTest = new RichArray(dataForRotateRight);
		System.out.println("Rotate Left: " + rotateLeftTest.rotateLeft());

		//test transpose
		RichArray transposeTest = new RichArray(dataForRotateRight);
		System.out.println("Transpose: " + transposeTest.transpose());

		// test ravel
		int[] dataForRavel = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		RichArray ravelTest = new RichArray(new int[][]{dataForRavel}); 
		System.out.println("Ravel with 4 columns: " + ravelTest.ravel(4));

		//test unravel
		RichArray unravelTest = new RichArray(dataForRotateRight);
		System.out.println("Unravel: " + unravelTest.unravel());

		//test reshape
		RichArray reshapeTest = new RichArray(dataForRotateRight);
		System.out.println("Reshape with 6 columns: " + reshapeTest.reshape(6));

		// test join
		int[][] dataForJoinLeft = {{1, 2, 3}, {4, 5, 6}};
		int[][] dataForJoinRight = {{10, 20, 30, 40}, {50, 60, 70, 80}};
		RichArray joinLeftTest = new RichArray(dataForJoinLeft);
		RichArray joinRightTest = new RichArray(dataForJoinRight);
		System.out.println("Join: " + joinLeftTest.join(joinRightTest));

		// test stack
		int[][] dataForStackBottom = {{10, 20, 30, 40}};
		RichArray stackTopTest = new RichArray(dataForJoinLeft);
		RichArray stackBottomTest = new RichArray(dataForStackBottom);
		System.out.println("Stack: " + stackTopTest.stack(stackBottomTest));

		// test slice
		int[][] dataForSlice = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		RichArray sliceTest = new RichArray(dataForSlice);
		System.out.println("Slice from (1,2) to (2,3): " + sliceTest.slice(1, 2, 2, 3));

		// test replace
		int[][] dataForReplaceOriginal = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}};
		int[][] dataForReplaceNew = {{55, 66, 77}, {88, 99, 100}};
		RichArray replaceOriginalTest = new RichArray(dataForReplaceOriginal);
		RichArray replaceNewTest = new RichArray(dataForReplaceNew);
		System.out.println("Replace starting at (1,2): " + replaceOriginalTest.replace(replaceNewTest, 1, 2));
	}
}
