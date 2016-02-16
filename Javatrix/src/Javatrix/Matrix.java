package Javatrix;
import java.lang.IllegalArgumentException;

public class Matrix {

    private double[][] matArray;
    
    /**
     * Constructs a matrix from an existing 2D array.
     *
     * @param matArray source matrix
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
     * Constructs a matrix quickly from an existing 2D array without
checking arguments
     *
     * @param matArray source matrix
     * @param m row length
     * @param n column length
     */
    public Matrix(double[][] matArray, int n, int m) {
        double[][] array = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = matArray[i][j];
            }
        }
        this.matArray = array;
    }
    
    /**
     * Constructs a m-by-n matrix of zeros.
     *
     * @param m row length
     * @param n column length
     */
    public Matrix(int m, int n) throws NegativeArraySizeException
    {
        try {
            double[][] zeroArray = new double[m][n];
            for (int i = 0; i < zeroArray.length; i++)
            {
                for (int j = 0; j < zeroArray[0].length; j++)
                {
                    zeroArray[i][j] = 0.0;
                }
            }
            matArray = zeroArray;
        } catch (NegativeArraySizeException ex) {
            System.out.println("Array size must not be negative");
        }        
    }
    
    /**
     * Constructs a m-by-n matrix of s.
     *
     * @param m row length
     * @param n column length
     * @param s the double the array is to be filled with
     */
    public Matrix(int m, int n, double s) throws NegativeArraySizeException
    {
        try {
            double[][] zeroArray = new double[m][n];
            for (int i = 0; i < zeroArray.length; i++)
            {
                for (int j = 0; j < zeroArray[0].length; j++)
                {
                    zeroArray[i][j] = s;
                }
            }
            matArray = zeroArray;
        } catch (NegativeArraySizeException ex) {
            System.out.println("Array size must not be negative");
        }        
    }
    
    /**
     * Gets the value at i, j
     *
     * @param i row
     * @param j column
     * @return the value at i, j
     */
    public double get(int i, int j)
    {
        return matArray[i][j];
    }
    
    /**
     * Sets the value at i, j
     *
     * @param i row
     * @param j column
     * @param val the new value
     */
    public void set(int i, int j, double val)
    {
        matArray[i][j] = val;
    }
    
    public double[][] getArray()
    {
    	return matArray;
    }
}
