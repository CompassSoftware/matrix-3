package Javatrix;
import java.lang.IllegalArgumentException;

public class Matrix {

    private double[][] matArray;
    
    /**
     * Constructs a matrix from an existing 2D array
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
	 * checking arguments
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
    * Constructs a matrix from a one-dimensional packed array
    *  
    *  
    * @param vals - One-dimensional array of doubles, packed by columns (ala Fortran).
    * @param m - Number of rows.
    * 
    * @throws java.lang.IllegalArgumentException - Array length must be a multiple of m.
    */
    
    public Matrix(double vals[], int m)
    {
        if (m == 0 || vals.length%m != 0)
        {
        	throw new IllegalArgumentException("Array length must be a multiple of m.");
        }
        int cols = vals.length;
        matArray = new double[m][cols];
        for (int a = 0; a < m; a++) 
        {
        	for (int b = 0; b < cols; b++) 
        	{
        		matArray[a][b] = vals[a+b*m];
            }
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
    
    /**
     * Gives access to the internal array of the matrix
     * 
     * @return a reference to the internal array
     */
    public double[][] getArray()
    {
    	
    	return matArray;
    }
    
    /**
     * Creates a deep copy of the internal array of the matrix
     * 
     * @return a deep copy of the internal array
     */
    public double[][] getArrayCopy()
    {
        double[][] array = new double[matArray.length][matArray[0].length];
        for (int i = 0; i < matArray.length; i++) {
            for (int j = 0; j < matArray[0].length; j++) {
                array[i][j] = matArray[i][j];
            }
        }
        return array;
    }
    
    /**
     * Get column dimension.
     * 
     * @return n - Number of Columns
     */
    
    public int getColumnDimension()
    {
    	int n = matArray[0].length;
    	return n;
    }
    
    /**
     * Get row dimension.
     * 
     * @return m - Number of Rows
     */
    
    public int getRowDimension()
    {
    	int m = matArray.length;
    	return m;
    }
    
    /**
     * Constructs a matrix from a copy of a 2-D array.
     * 
     * @param A The matrix to copy.
     * @return matrix Returns a copy of the matrix.
     */
    public static Matrix constructWithCopy(double[][] A)
    {
    	double[][] copyArray = new double[A.length][A[0].length];
    	
    	for (int i = 0; i < A.length; i++)
    	{
            for (int j = 0; j < A[0].length; j++)
            {
                copyArray[i][j] = A[i][j];
            }
        }
    	
    	Matrix copyMatrix = new Matrix(copyArray);
    	
        return copyMatrix;
    }
    
    /**
     * Makes a deep copy of a matrix.
     * 
     * @return Matrix A deep copy the matrix.
     */
    public Matrix copy()
    {
    	Matrix copyMatrix = new Matrix(this.matArray);
    	
        return copyMatrix;
    }
    
    /**
     * Clones the Matrix object.
     * 
     * @return Matrix A clone of the matrix.
     */
    public Matrix clone()
    {
    	return super.clone();
    }
    
    /**
     * Generate identity matrix
     * 
     * @param m - # of rows
     * @param n - # of columns
     * @return 	  An m-by-n matrix with ones on the diagonal and zeros elsewhere.
     */
    public static Matrix identity(int m, int n)
    {
    	Matrix ident = new Matrix(m,n);
    	double[][] fillErUp = ident.getArray();
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			if (a == b)
    				fillErUp[a][b] = 1.0;
    			else
    				fillErUp[a][b] = 0.0;
    		}
    	}
    	return ident;
    }
}
