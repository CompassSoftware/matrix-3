package Javatrix;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.IllegalArgumentException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Matrix implements Cloneable
{

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
    
   /**Joseph O'Neill
    * Constructs a matrix from a one-dimensional packed array
    *  
    *  
    * @param vals - One-dimensional array of doubles, packed by columns (ala Fortran).
    * @param m - Number of rows.
    * 
    * @throws java.lang.IllegalArgumentException - Array length must be a multiple of m.
    */
    
    public Matrix(double vals[], int m) throws IllegalArgumentException
    {
        if (m == 0 || vals.length%m != 0)
        {
        	throw new IllegalArgumentException("Array length must be a multiple of m.");
        }
        int n = vals.length/m;
        matArray = new double[m][n];
        for (int a = 0; a < m; a++) 
        {
        	for (int b = 0; b < n; b++) 
        	{
        		matArray[a][b] = vals[a+b*m];
            }
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
     * Generate matrix with random elements.
     * @param m the input of Row of the matrix
     * @param n the input of column of the matrix
     */
    public static Matrix random(int m, int n)
    {
    	Matrix A = new Matrix(m, n);
    	for (int i = 0; i < m; i++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			A.set(i, j, Math.random());
    	
    		}
    	}
    	return A;
    }
    
    /**
     * Print matrix
     * @param w Column width
     * @param d Number of digits after the decimal
     */
    public void print(int w, int d)
    {
    	String field = "%" + w + "." + d + "f";
    	String output = "";
    	for (int i = 0; i < getRowDimension(); i++)
    	{
    		for (int j = 0; j < getColumnDimension(); j++)
    		{
    			output += String.format(field, get(i,j));
    		}
    		output += '\n';
    	}
    	System.out.print(output);
    }

    /**
     * Print matrix
     * @param writer PrintWriter for output
     * @param w Column width
     * @param d Number of digits after the decimal
     */
    public void print(PrintWriter writer, int w, int d)
    {
    	String field = "%" + w + "." + d + "f";
    	String output = "";
    	for (int i = 0; i < getRowDimension(); i++)
    	{
    		for (int j = 0; j < getColumnDimension(); j++)
    		{
    			output += String.format(field, get(i,j));
    		}
    		output += '\n';
    	}
    	writer.print(output);
    }

    /**
     * Print matrix
     * @param form NumberFormat to format output
     * @param w Column width
     */
    public void print(NumberFormat form, int w)
    {
    	String output = "";
    	for (int i = 0; i < getRowDimension(); i++)
    	{
    		for (int j = 0; j < getColumnDimension(); j++)
    		{
    			String num = form.format(get(i,j));
    			output += String.format("%" + w + "s", num);
    		}
    		output += '\n';
    	}
    	System.out.print(output);
    }

    /**
     * Print matrix
     * @param writer PrintWriter for output
     * @param form NumberFormat to format output
     * @param w Column width
     */
    public void print(PrintWriter writer, NumberFormat form, int w)
    {
    	String output = "";
    	for (int i = 0; i < getRowDimension(); i++)
    	{
    		for (int j = 0; j < getColumnDimension(); j++)
    		{
    			String num = form.format(get(i,j));
    			output += String.format("%" + w + "s", num);
    		}
    		output += '\n';
    	}
    	writer.print(output);
    }

    /**
     * Read matrix from input stream
     * @param input BufferedReader for input
     */
    public static Matrix read(BufferedReader input) throws IOException
    {
    	ArrayList<ArrayList<Double>> rowList = new ArrayList<ArrayList<Double>>();
    	ArrayList<Double> row = new ArrayList<Double>();
    	String line = input.readLine();
    	Scanner scan;
    	while (line != null)
    	{
    		scan = new Scanner(line);
    		row = new ArrayList<Double>();
    		while (true)
    		{
    			try
    			{
    				double num = scan.nextDouble();
    				row.add(num);
    			}
    			catch (NoSuchElementException e)
    			{
    				break;
    			}
    		}
    		rowList.add(row);
    		line = input.readLine();
    	}
    	double[][] newArray = new double[rowList.size()][row.size()]; 
    	for (int i = 0; i < rowList.size(); i++)
    	{
    		for (int j = 0; j < row.size(); j++)
    		{
    			newArray[i][j] = rowList.get(i).get(j);
    		}
    	}
    	return new Matrix(newArray);
    }

    /**
     * Clones the Matrix object.
     * 
     * @return Matrix A clone of the matrix.
     */
    public Object clone() throws CloneNotSupportedException
    {
    	//return new Matrix(matArray);
    	return super.clone();
    }
    
    /**Joseph O'Neill
     * Get column dimension.
     * 
     * @return n - Number of Columns
     */
    
    public int getColumnDimension()
    {
    	int n = matArray[0].length;
    	return n;
    }
    
    /**Joseph O'Neill
     * Make a one-dimensional column packed copy of the internal array.
     * 
     * @return Matrix elements packed in a one-dimensional array by columns.
     */
    
    public double[] getColumnPackedCopy()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	double[] CPC = new double[m*n];
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			CPC[a+b*m] = matArray[a][b];
    		}
    	}
    	return CPC;
    }
    
    /**Joseph O'Neill
     * Get row dimension.
     * 
     * @return m - Number of Rows
     */
    
    public int getRowDimension()
    {
    	int m = matArray.length;
    	return m;
    }
    
    /**Joseph O'Neill
     * Make a one-dimensional row packed copy of the internal array.
     * 
     * @return Matrix elements packed in a one-dimensional array by rowss.
     */
    
    public double[] getRowPackedCopy()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	double[] RPC = new double[m*n];
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			RPC[a*n+b] = matArray[a][b];
    		}
    	}
    	return RPC;
    }
    
    /** Joseph O'Neill
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
    
    /**Joseph O'Neill
     * Matrix Trace
     * 
     * @return Sum of the diagonal elements.
     */
    
    public double trace()
    {
    	double traceThis = 0.0;
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	int min = 0;
    	
    	if (m < n)
    		{min = m;}
    	else 
    		{min = n;}
    	
    	for (int a = 0; a < min; a++)
    	{
    		traceThis += matArray[a][a];
    	}
    	return traceThis;
    }
    
    /**Joseph O'Neill
     * Matrix transpose
     * 
     * @return A'
     */
    
    public Matrix transpose()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	
    	Matrix trans = new Matrix(n,m); //Forgot to reverse this for Transpose!
    	double[][] transArray = trans.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			transArray[b][a] = matArray[a][b];
    		}
    	}
    	
    	return trans;
    }
    
    /**Joseph O'Neill
     * C = A - B
     * 
     * @param B - Another Matrix
     * @return A - B
     * @throws Dimensions must be equal
     */
    
    public Matrix minus(Matrix B)
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();

    	Matrix minusThis = new Matrix(m,n);
    	double AArray[][] = getArray();
    	double BArray[][] = B.getArray();
    	double CArray[][] = minusThis.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			CArray[a][b] = AArray[a][b] - BArray[a][b];
    		}
    	}
    	
    	return minusThis;
    }
    
    /**Joseph O'Neill
     * A = A - B
     * 
     * @param B - another Matrix
     * @return A - B
     * @throws Dimensions must be equal
     */
    
    public Matrix minusEquals(Matrix B)
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();

    	double AArray[][] = getArray();
    	double BArray[][] = B.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			AArray[a][b] = AArray[a][b] - BArray[a][b];
    		}
    	}
    	
    	return this;
    }
    
    /**
     * norm1 calculates and returns the one norm. 
     * The value that is the sum of the column with the greatest 
     * sum of the absolute value of its elements.
     * 
     * @return double sum of the max column
     */
    public double norm1()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	
    	double sum = 0;
    	for (int i = 0; i < n; i++)
    	{
    		double s = 0;
    		for (int j = 0; j < m; j++)
    		{
    			s += Math.abs(matArray[i][j]);
    		}
    		if (s > sum)
    		{
    			sum = s;
    		}
    	}
		return sum;
    	
    }
    
    /**
     * normF calculates and returns the Froebenius norm. 
     * The value that is the square root of the sum of the squares  
     * of all elements.
     * 
     * @return double square root of the sum of the squares
     */
    public double normF()
    {
    	
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	double matTemp = 0;
    	double temp = 0;
    	double result = 0;
    	for (int i = 0; i < m; i++) 
    	{
    		for (int j = 0; j < n; j++) 
    		{
    			result = Math.abs(result);
    			matTemp = Math.abs(matArray[i][j]);
    			if (matTemp > result)
    			{
    				temp = (matTemp > 0.0? result/matTemp: 0.0);
    				result = matTemp;
    			}
    			else temp = (result > 0.0? matTemp/result: 0.0);
    			temp = result * Math.sqrt(1.0 + temp * temp);
            }
    	}
		return temp;
    	
    }
   /**
    * normInf calculates and returns the infinity norm. 
    * The value that is the sum of the row with the greatest 
    * sum of the absolute value of its elements.
    * 
    * @return double sum of the max row
    */
    public double normInf()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	
    	double sum = 0;
    	for (int i = 0; i < m; i++)
    	{
    		double s = 0;
    		for (int j = 0; j < n; j++)
    		{
    			s += Math.abs(matArray[i][j]);
    		}
    		if (s > sum)
    		{
    			sum = s;
    		}
    	}
		return sum;
    	
    }
    
    /**Joseph O'Neill
     * C = A + B
     * 
     * @param B - Another Matrix
     * @return A + B
     * @throws Dimensions must be equal
     */
    
    public Matrix plus(Matrix B)
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();

    	Matrix plusThis = new Matrix(m,n);
    	double AArray[][] = getArray();
    	double BArray[][] = B.getArray();
    	double CArray[][] = plusThis.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			CArray[a][b] = AArray[a][b] + BArray[a][b];
    		}
    	}
    	
    	return plusThis;
    }
    
    /**Joseph O'Neill
     * A = A + B
     * 
     * @param B - another Matrix
     * @return A + B
     * @throws Dimensions must be equal
     */
    
    public Matrix plusEquals(Matrix B)
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	
    	double AArray[][] = getArray();
    	double BArray[][] = B.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			AArray[a][b] = AArray[a][b] + BArray[a][b];
    		}
    	}
    	
    	return this;
    }
    
    /**Joseph O'Neill
     * Unary minus
     * 
     * @return -matArray
     */
    
    public Matrix uMinus()
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	
    	Matrix neg = new Matrix(m,n);
    	double[][] negArray = neg.getArray();
    	
    	for (int a = 0; a < m; a++)
    	{
    		for (int b = 0; b < n; b++)
    		{
    			negArray[a][b] = -matArray[a][b];
    		}
    	}
    	
    	return neg;
    }
    
    /**
     * Creates a matrix from a subsection of this matrix
     *
     * @param i1 Starting row index
     * @param i2 Ending row index
     * @param j1 Starting column index
     * @param j2 Ending column index
     */
    public Matrix getMatrix(int i1, int i2, int j1, int j2)
    {
    	int rows = i2 - i1 + 1;
    	int cols = j2 - j1 + 1;
    	double[][] newArray = new double[rows][cols];
    	for (int i = 0; i < rows; i++)
    	{
        	for (int j = 0; j < cols; j++)
        	{
        		newArray[i][j] = matArray[i1 + i][j1 + j];
        	}
    	}
    	return new Matrix(newArray);
    }
    
    /**
     * Creates a matrix from a subsection of this matrix
     *
     * @param rows Rows to include in new matrix
     * @param j1 Starting column index
     * @param j2 Ending column index
     */
    public Matrix getMatrix(int[] rows, int j1, int j2)
    {
    	int cols = j2 - j1 + 1;
    	double[][] newArray = new double[rows.length][cols];
    	for (int i = 0; i < rows.length; i++)
    	{
        	for (int j = 0; j < cols; j++)
        	{
        		newArray[i][j] = matArray[rows[i]][j1 + j];
        	}
    	}
    	return new Matrix(newArray);
    }
    
    /**
     * Creates a matrix from a subsection of this matrix
     *
     * @param i1 Starting row index
     * @param i2 Ending row index
     * @param cols Columns to include in new matrix
     */
    public Matrix getMatrix(int i1, int i2, int[] cols)
    {
    	int rows = i2 - i1 + 1;
    	double[][] newArray = new double[rows][cols.length];
    	for (int i = 0; i < rows; i++)
    	{
        	for (int j = 0; j < cols.length; j++)
        	{
        		newArray[i][j] = matArray[i1 + i][cols[j]];
        	}
    	}
    	return new Matrix(newArray);
    }
    
    /**
     * Creates a matrix from a subsection of this matrix
     *
     * @param rows rows to include in new matrix
     * @param cols columns to include in new matrix
     */
    public Matrix getMatrix(int[] rows, int[] cols)
    {
    	double[][] newArray = new double[rows.length][cols.length];
    	for (int i = 0; i < rows.length; i++)
    	{
        	for (int j = 0; j < cols.length; j++)
        	{
        		newArray[i][j] = matArray[rows[i]][cols[j]];
        	}
    	}
    	return new Matrix(newArray);
    }
    
    /**
     * Multiplies the matrix by the scalar given.
     * 
     * @param s double
     * @return 
     */
    public Matrix times(double s)
    {
    	Matrix mat = new Matrix(this.matArray); 
    	for (int i = 0; i < mat.getArray().length; i++)
    	{
    		for (int j = 0; j < mat.getArray()[0].length; j++)
    		{
    			mat.getArray()[i][j] *= s;
    		}
    	}
    	return mat;
    }

    /**
     * Multiplies the matrix by the scalar given.
     * 
     * @param s double
     * @return 
     */
    public Matrix timesEquals(double s)
    {
    	for (int i = 0; i < matArray.length; i++)
    	{
    		for (int j = 0; j < matArray[0].length; j++)
    		{
    			matArray[i][j] *= s;
    		}
    	}
    	return this;
    }
    
    /**
     * Multiplies the matrix by the matrix given.
     * 
     * @param B Matrix
     * @return 
     */
    public Matrix times(Matrix B) throws IllegalArgumentException
    {
    	int m = getRowDimension();
    	int n = getColumnDimension();
    	int p = B.getRowDimension();
    	int q = B.getColumnDimension();
    	int sum = 0;
    	
    	if ((n != p))
    	{
        	throw new IllegalArgumentException("Matrix inner dimensions must agree.");
    	}
    	
		Matrix mat = new Matrix(matArray.length, B.getArray()[0].length);
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < q; j++)
			{
				for (int k = 0; k < p; k++)
				{
					sum += matArray[i][k] * B.getArray()[k][j];
				}
				mat.matArray[i][j] = sum;
				sum = 0;
			}
		}
    	return mat;
    }
    
    public Matrix arrayTimes(Matrix B)
    {
    	Matrix C = new Matrix(getRowDimension(), getColumnDimension());
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		C.set(i, j, get(i, j) * B.get(i, j));
        	}
    	}
    	return C;
    }
    
    public Matrix arrayTimesEquals(Matrix B)
    {
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		set(i, j, get(i, j) * B.get(i, j));
        	}
    	}
    	return this;
    }
    
    public Matrix arrayRightDivide(Matrix B)
    {
    	Matrix C = new Matrix(getRowDimension(), getColumnDimension());
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		C.set(i, j, get(i, j) / B.get(i, j));
        	}
    	}
    	return C;
    }
    
    public Matrix arrayRightDivideEquals(Matrix B)
    {
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		set(i, j, get(i, j) / B.get(i, j));
        	}
    	}
    	return this;
    }
    
    public Matrix arrayLeftDivide(Matrix B)
    {
    	Matrix C = new Matrix(getRowDimension(), getColumnDimension());
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		C.set(i, j, B.get(i, j) / get(i, j));
        	}
    	}
    	return C;
    }
    
    public Matrix arrayLeftDivideEquals(Matrix B)
    {
    	for (int i = 0; i < getRowDimension(); i++)
    	{
        	for (int j = 0; j < getColumnDimension(); j++)
        	{
        		set(i, j, B.get(i, j) / get(i, j));
        	}
    	}
    	return this;
    }
}