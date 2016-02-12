package javatrix;
import java.lang.IllegalArgumentException;

public class Matrix {

	private double[][] matArray;
	
	/**
	 * @param matArray
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

	
	
}
