/**
 * @author CHRISTOFOROS KONTZIAS 1134670
 * @date 23/02/2024
 * this class is responsible for modifying user-defined arrays using methods.
 */
package hw1;

/**
 * this class is responsible for modifying user-defined arrays using methods.
 */
public class RichArray {
	private final int[][] nums;
	/**
	 * the constructor for a 2D implementation
	 * @param nums the data of the Rich array
	 */
	public RichArray(int[][] nums) {
		this.nums = arrUse(nums);
	}
	/**
	 * constructor for 1D implementation
	 * @param firstBatch 1d data
	 */
	public RichArray(int[] firstBatch) {
		nums = new int[1][];
		nums[0] = new int[firstBatch.length];
		for (int i = 0; i < firstBatch.length; i++) {
			nums[0][i] = firstBatch[i];
		}
	}
	/**
	 * 
	 * @param array the data that will be used
	 * @return 2D array return type
	 */
	private int[][] arrUse(int[][] array) {
		if (array == null) {
			return null;
		}
		int[][] toUse = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				toUse[i] = new int[array[i].length];
				for (int k = 0; k < array[i].length; k++) {
				toUse[i][k] = array[i][k];
			}
			}
		}
		return toUse;
	}

	/**
	 * gets the size of the numbers
	 * @return integer representing the size of the numbers
	 */
	public int size() {
		return nums.length;
	}

	/**
	 * this class will simply return -1
	 * @param row the row
	 * @param col the column
	 * @return will always return -1
	 */
	public int getElement(int row, int col) {
		if ((row >= 0 && row < nums.length) && col >= 0 && col < nums[row].length) {
			return nums[row][col];
		}
		return -1;
	}

	/**reverses the array
	 * 
	 * @return the revered Rich array
	 */
	public RichArray reverse() {
		int[][] reversedData = new int[nums.length][];
		for (int i = 0; i < nums.length; i++) {
			reversedData[i] = new int[nums[i].length];
			for (int j = 0; j < nums[i].length; j++) {
				reversedData[i][j] = nums[i][nums[i].length - 1 - j];
			}
		}
		return new RichArray(reversedData);
	}

	/**
	 * rotates to the right
	 * @return rotated Rich array
	 */
	public RichArray rotateRight() {
		int[][] rotatedData = new int[nums[0].length][nums.length];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				rotatedData[j][nums.length - 1 - i] = nums[i][j];
			}
		}
		return new RichArray(rotatedData);
	}
	/**
	 * rotates array to the left
	 * @return rotated Rich array
	 */
	public RichArray rotateLeft() {
		int[][] rotatedData = new int[nums[0].length][nums.length];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				rotatedData[nums[i].length - 1 - j][i] = nums[i][j];
			}
		}
		return new RichArray(rotatedData);
	}
	/**
	 * it transposes an array
	 * @return transposed Rich array
	 */
	public RichArray transpose() {
		int rows = nums.length;
		int cols = nums[0].length;
		int[][] transposed = new int[cols][rows];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				transposed[j][i] = nums[i][j];
			}
		}

		return new RichArray(transposed);
	}
	/**
	 * this mthod "ravels" the Rich array
	 * @param columns the number of columns
	 * @return a raveled ich array
	 */
	public RichArray ravel(int columns) {
		int totalElements = nums.length * nums[0].length;
		int rows = (totalElements + columns - 1) / columns; 
		int[][] raveledData = new int[rows][columns];
		int index = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++, index++) {
				if (index < totalElements) {
					raveledData[i][j] = nums[i / columns][i % columns];
				} else {
					raveledData[i][j] = 0; //if the last row is not full
				}
			}
		}

		return new RichArray(raveledData);
	}
	/**
	 * it unravels an array by making it 1D
	 * @return unraveled Rich array
	 */
	public RichArray unravel() {
		int totalElements = nums.length * nums[0].length;
		int[] unraveledData = new int[totalElements];
		int index = 0;

		for (int[] row : nums) {
			for (int element : row) {
				unraveledData[index++] = element;
			}
		}

		return new RichArray(unraveledData);
	}
	/**this method reshapes a Rich array
	 * 
	 * @param newColumns number of columns
	 * @return a reshaped Rich array
	 */
	public RichArray reshape(int newColumns) {
		int totalElements = nums.length * nums[0].length;
		int newRows = totalElements / newColumns;
		if (totalElements % newColumns != 0) {
			newRows++; // if there more I should add another
		}

		int[][] reshapedData = new int[newRows][newColumns];
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++, index++) {
				reshapedData[index / newColumns][index % newColumns] = nums[i][j];
			}
		}

		return new RichArray(reshapedData);
	}
	/**
	 * this method joins an element in an array
	 * @param other a Rich array
	 * @return joined Rich array
	 */
	public RichArray join(RichArray other) {
		if (this.nums.length != other.nums.length) {
			throw new IllegalArgumentException("Arrays must have the same number of rows to join.");
		}

		int newColumns = this.nums[0].length + other.nums[0].length;
		int[][] joinedData = new int[nums.length][newColumns];

		for (int i = 0; i < nums.length; i++) {
			int currentColumn = 0;

			for (int j = 0; j < this.nums[i].length; j++) {
				joinedData[i][currentColumn++] = this.nums[i][j];
			}

			for (int j = 0; j < other.nums[i].length; j++) {
				joinedData[i][currentColumn++] = other.nums[i][j];
			}
		}

		return new RichArray(joinedData);

	}
	/**
	 * this implements the stack
	 * @param other the array
	 * @return a Rich array as a stack
	 */
	public RichArray stack(RichArray other) {
		if (this.nums[0].length != other.nums[0].length) {
			return this;
		}

		int newRows = this.nums.length + other.nums.length;
		int[][] stackedData = new int[newRows][this.nums[0].length];

		for (int i = 0; i < this.nums.length; i++) {
			for (int j = 0; j < this.nums[i].length; j++) {
				stackedData[i][j] = this.nums[i][j];
			}
		}

		for (int i = 0; i < other.nums.length; i++) {
			for (int j = 0; j < other.nums[i].length; j++) {
				stackedData[i + this.nums.length][j] = other.nums[i][j];
			}
		}

		return new RichArray(stackedData);
	}
	/**
	 * this method slices the array
	 * @param firstRow the first rows index
	 * @param lastRow  the last row index
	 * @param firstColumn the first column index
	 * @param lastColumn the last column index
	 * @return sliced array
	 */
	public RichArray slice(int firstRow, int lastRow, int firstColumn, int lastColumn) {

		firstRow = Math.max(firstRow, 0);
		lastRow = Math.min(lastRow, nums.length - 1);
		firstColumn = Math.max(firstColumn, 0);
		lastColumn = Math.min(lastColumn, nums[0].length - 1);

		if (firstRow > lastRow || firstColumn > lastColumn) {
			return this;
		}

		int newRows = lastRow - firstRow + 1;
		int newColumns = lastColumn - firstColumn + 1;
		int[][] slicedData = new int[newRows][newColumns];

		for (int i = firstRow; i <= lastRow; i++) {
			for (int j = firstColumn; j <= lastColumn; j++) {
				slicedData[i - firstRow][j - firstColumn] = this.nums[i][j];
			}
		}

		return new RichArray(slicedData);
	}
	/**
	 * It replaces certain parts of an array
	 * @param other the array
	 * @param row row number
	 * @param column column number
	 * @return array with the replacement
	 */
	public RichArray replace(RichArray other, int row, int column) {
		int[][] newData = arrUse(this.nums);

		for (int i = 0; i < other.nums.length && (i + row) < this.nums.length; i++) {
			for (int j = 0; j < other.nums[i].length && (j + column) < this.nums[i].length; j++) {
				newData[row + i][column + j] = other.nums[i][j];
			}
		}

		return new RichArray(newData);
	}

	public String toString() {  //algorithm to print [] in order to make the output readable
	    if (nums == null || nums.length == 0) return "[]";

	    String result = "[";
	    for (int i = 0; i < nums.length; i++) {
	     if (i > 0) result += ", ";
	       result += "[";
	       for (int j = 0; j < nums[i].length; j++) {
	          result += nums[i][j];
	           if (j < nums[i].length - 1) {
	               result += ", ";
	           }
	        }
	        result += "]";
	    }
	    result += "]";
	    return result;
	}


}