package javatrix;
import java.lang.IllegalArgumentException;

public class Matrix {

	private double[][] matArray;
	
	/**
	 * @param matArray Joseph checking in
	 */
	public Matrix(double[][] matArray) throws IllegalArgumentException{
		double[][] array = new double[matArray.length][matArray[0].length];
		int len = matArray[0].length;
		for (int i = 0; i < matArray.length; i++) {
			if (matArray[i].length != len) {
				throw new IllegalArgumentException();
			}
			for (int j = 0; j < matArray[0].length; j++) {
				array[i][j] = matArray[i][j];
			}
		}
		
		this.matArray = array;
	}
	
	/**
	 * Constructs a m-by-n matrix of zeros.
	 * 
	 * @param m row length
	 * @paran n column length
	 */
	public Matrix(int m, int n) throws NegativeArraySizeException
	{
		try {
			double[][] zeroArray = new double[m][n];
			for (int i = 0; i < zeroArray.length; i++)
			{
				for (int j = 0; j < zeroArray[].length; j++)
				{
					zeroArray[i][j] = 0.0;
				}
			}
		} catch (NegativeArraySizeException ex) {
			System.out.println("Array size must not be negative");
		}		
	}

	
	
}
