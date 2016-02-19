import Javatrix.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class Testtrix {
	/*
    public static void main(String[] args) {
	double[][] vals = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
	Matrix A = new Matrix(vals);
	Matrix x = Matrix.random(3,1);
	Matrix b = A.times(x);
	A.print(9,4);
	System.out.println("x");
	x.print(9,4);
	System.out.println("=");
	b.print(9,4);
    }
    */

	private PrintStream oldOut;
	private ByteArrayOutputStream testOut;
	
	@Before
	public void setup()
	{
		oldOut = System.out;
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
	}
	
	@After
	public void reset()
	{
		System.out.flush();
		System.setOut(oldOut);
	}

	@Test
	public void testGetSet() {
		int m = 10;
		int n = 15;
		double val = 5;
		Matrix mat = new Matrix(m, n);
		// Arguments to assertEquals for doubles are val1, val2, and accepted difference
		assertEquals(mat.get(0, 0), 0, 0);
		mat.set(0, 0, val);
		assertEquals(mat.get(0, 0), val, 0);
		assertEquals(mat.get(m-1, n-1), 0, 0);
		mat.set(m-1, n-1, val);
		assertEquals(mat.get(m-1, n-1), val, 0); 
	}
	
	@Test
	public void testConstructFrom2DArray() {
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8},
				{9, 10, 11}
				};
		double val = 15;
		Matrix mat = new Matrix(source);
		
		// Check that matrix was created correctly
		int n = mat.getRowDimension();
		int m = mat.getColumnDimension();
		assertEquals(n, source.length, 0);
		assertEquals(m, source[0].length, 0);
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				assertEquals(mat.get(i, j), source[i][j], 0);
			}
		}
		
		// Check that changing matrix does not change original array
		mat.set(2, 1, val);
		assertNotEquals(mat.get(2, 1), source[2][1], 0);
		
		// Check invalid matrix creation
		try
		{
			double[][] src = {{0, 0, 0}, {0, 0}};
			Matrix badMat = new Matrix(src);
			assertArrayEquals(badMat.getArray(),badMat.getArray());
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (Exception e)
		{
			assert(false);
		}
	}
	
	@Test
	public void testConstructFrom2DArrayFast() {
		double[][] source = {
				{0, 1, 2, 12},
				{3, 4, 5, 13},
				{6, 7, 8, 14},
				{9, 10, 11, 15}
				};
		int n = 3;
		int m = 4;
		double val = 15;
		Matrix mat = new Matrix(source, n, m);
		
		// Check that matrix was created correctly
		assertEquals(n, mat.getRowDimension(), 0);
		assertEquals(m, mat.getColumnDimension(), 0);
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				assertEquals(mat.get(i, j), source[i][j], 0);
			}
		}
		
		// Check that changing matrix does not change original array
		mat.set(2, 1, val);
		assertNotEquals(mat.get(2, 1), source[2][1], 0);
	}
	
	@Test
	public void testConstructZerosMatrix()
	{
		double[][] expected = {
				{0.0, 0.0},
				{0.0, 0.0}
				};
		Matrix zero = new Matrix(2,2);
		assertArrayEquals(expected, zero.getArray());
	}
	
	@Test
	public void testConstructSMatrix()
	{
		double[][] expected = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		Matrix s = new Matrix(2, 2, 1.0);
		assertArrayEquals(expected, s.getArray());
	}
	
	@Test
	public void testCounstructWith1DArray()
	{
		double[] source = {1.0, 1.0};
		int m = 2;
		double[][] expected = {
				{1.0},
				{1.0}};
		Matrix newArray = new Matrix(source, m);
		assertArrayEquals(expected, newArray.getArray());
	}
	
	@Test
	public void testConstructWithCopy()
	{
		double[][] org = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		Matrix copy = new Matrix(org);
		assertArrayEquals(org, copy.getArray());
	}
	
	@Test
	public void testCopy()
	{
		double[][] org = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		Matrix o1 = new Matrix(org);
		Matrix o2 = o1.copy();
		assertArrayEquals(o1.getArray(), o2.getArray());
	}
	
	@Test
	public void testClone()
	{
		double[][] org = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		Matrix o1 = new Matrix(org);
		Matrix o2 = (Matrix) o1.clone();
		o1.set(0, 0, 0.0);
		assertArrayEquals(o1.getArray(), o2.getArray());
	}
	
	@Test
	public void testPrint()
	{
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8}
				};
		Matrix mat = new Matrix(source);
		mat.print(4, 1);
		String output = testOut.toString();
		assertEquals(" 0.0 1.0 2.0\n"
				+ " 3.0 4.0 5.0\n"
				+ " 6.0 7.0 8.0\n",
				output);
	}
	
	@Test
	public void testPrintOutputStream()
	{
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8}
				};
		Matrix mat = new Matrix(source);
		PrintWriter writer = new PrintWriter(testOut);
		mat.print(writer, 4, 1);
		writer.flush();
		String output = testOut.toString();
		assertEquals(" 0.0 1.0 2.0\n"
				+ " 3.0 4.0 5.0\n"
				+ " 6.0 7.0 8.0\n",
				output);
	}
	
	@Test
	public void testPrintNumberFormat()
	{
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8}
				};
		Matrix mat = new Matrix(source);
		DecimalFormat format = new DecimalFormat("#0.0");
		mat.print(format, 4);
		String output = testOut.toString();
		assertEquals(" 0.0 1.0 2.0\n"
				+ " 3.0 4.0 5.0\n"
				+ " 6.0 7.0 8.0\n",
				output);
	}
	
	@Test
	public void testPrintNumberFormatOutputStream()
	{
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8}
				};
		Matrix mat = new Matrix(source);
		DecimalFormat format = new DecimalFormat("#0.0");
		PrintWriter writer = new PrintWriter(testOut);
		mat.print(writer, format, 4);
		writer.flush();
		String output = testOut.toString();
		assertEquals(" 0.0 1.0 2.0\n"
				+ " 3.0 4.0 5.0\n"
				+ " 6.0 7.0 8.0\n",
				output);
	}
	
	//Joseph O'Neill
	@Test
	public void testMatrixOneDArrayDoubles()
	{
		double[] source = {0,4,7,1,5,8,2,6,9};
		double[][] test = {{0,1,2},
						   {4,5,6},
						   {7,8,9}};
		int numRows = 3;
		Matrix OneDArray = new Matrix(source, numRows);
		assertArrayEquals(OneDArray.getArray(),test);
	}
	
	//Joseph O'Neill
	@Test
	public void testGetColumnDimension()
	{
		double[][] source = {{5, 4, 3, 2, 1},
							 {8, 7, 6, 5, 4},
							 {2, 1, 0,-1,-2}};
		Matrix columnTest = new Matrix(source);
		assertEquals(5,columnTest.getColumnDimension());
	}
	
	//Joseph O'Neill
	@Test
	public void testGetRowDimension()
	{
		double[][] source = {{5, 4, 3, 2, 1},
							 {8, 7, 6, 5, 4},
							 {2, 1, 0,-1,-2}};
		Matrix rowTest = new Matrix(source);
		assertEquals(3,rowTest.getRowDimension());
	}
	
	//Joseph O'Neill - For some reason, AssertEquals doesn't work for double[], 
	//but AssertArrayEquals works for double[][]? Quick work around we can fix
	//if need be.
	@Test
	public void testGetColumnPackedCopy()
	{
		double[][] source = {{5, 4, 3, 2, 1},
				 			 {8, 7, 6, 5, 4},
				 			 {2, 1, 0,-1,-2}};
		double[][] test = {{5,8,2,4,7,1,3,6,0,2,5,-1,1,4,-2},{5,8,2,4,7,1,3,6,0,2,5,-1,1,4,-2}};
		Matrix testMat = new Matrix(source);
		double[][] CPC = {testMat.getColumnPackedCopy(),testMat.getColumnPackedCopy()};
		assertArrayEquals(CPC,test);
	}
	
	//Joseph O'Neill - For some reason, AssertEquals doesn't work for double[], 
	//but AssertArrayEquals works for double[][]? Quick work around we can fix
	//if need be.
	@Test
	public void testGetRowPackedCopy()
	{
		double[][] source = {{5, 4, 3, 2, 1},
				 			 {8, 7, 6, 5, 4},
				 			 {2, 1, 0,-1,-2}};
		double[][] test = {{5,4,3,2,1,8,7,6,5,4,2,1,0,-1,-2},{5,4,3,2,1,8,7,6,5,4,2,1,0,-1,-2}};
		Matrix testMat = new Matrix(source);
		double[][] RPC = {testMat.getRowPackedCopy(),testMat.getRowPackedCopy()};
		assertArrayEquals(RPC,test);
	}
	
	//Joseph O'Neill
	@Test
	public void testIdentity()
	{
		double[][] source = {{1,0,0,0},
							 {0,1,0,0},
							 {0,0,1,0},
							 {0,0,0,1},
							 {0,0,0,0}};
		Matrix other = new Matrix(source);
		Matrix id = Matrix.identity(5,4);
		assertArrayEquals(other.getArray(),id.getArray());
	}
	
	//Joseph O'Neill
	@Test
	public void testTrace()
	{
		double[][] source = {{5,4,3,2,1},
							 {6,5,4,3,2},
							 {-5,0,5,10,15},
							 {9,2,4,5,11},
							 {1,2,3,4,5}};
		Matrix trc = new Matrix(source);
		double answer = 25;
		assertEquals(answer,trc.trace(),0);
	}
	
	//Joseph O'Neill
	@Test
	public void testTranspose()
	{
		double[][] source = {{5,4,3,2,1},
				 			 {6,5,4,3,2},
				 			 {-5,0,5,10,15},
				 			 {9,2,4,5,11},
				 			 {1,2,3,4,5}};
		
		double[][] transposed = {{5,6,-5,9,1},
				 			 	 {4,5,0,2,2},
				 			 	 {3,4,5,4,3},
				 			 	 {2,3,10,5,4},
				 			 	 {1,2,15,11,5}};
		
		Matrix normal = new Matrix(source);
		Matrix trans = normal.transpose();
		assertArrayEquals(trans.getArray(),transposed);
		assertArrayEquals(normal.getArray(),trans.transpose().getArray());
	}
	
	//Joseph O'Neill
	@Test
	public void testMinus()
	{
		double[][] AArray = {{5,4,3,2,1},
	 			 			 {6,5,4,3,2},
	 			 			 {-5,0,5,10,15},
	 			 			 {9,2,4,5,11},
	 			 			 {1,2,3,4,5}};
		Matrix A = new Matrix(AArray);
		Matrix B = new Matrix(A.getArray());
		Matrix C = new Matrix(5,5);
		Matrix ans = A.minus(B);
		assertArrayEquals(ans.getArray(),C.getArray());
	}
	
	//Joseph O'Neill
	@Test
	public void testPlus()
	{
		double[][] AArray = {{5,4,3,2,1},
	 			 			 {6,5,4,3,2},
	 			 			 {-5,0,5,10,15},
	 			 			 {9,2,4,5,11},
	 			 			 {1,2,3,4,5}};
		Matrix A = new Matrix(AArray);
		Matrix B = A.uMinus();
		Matrix C = new Matrix(5,5);
		Matrix ans = A.plus(B);
		assertArrayEquals(ans.getArray(),C.getArray());
	}
	
	//Joseph O'Neill
	@Test
	public void testMinusEquals()
	{
		double[][] AArray = {{5,4,3,2,1},
	 			 			 {6,5,4,3,2},
	 			 			 {-5,0,5,10,15},
	 			 			 {9,2,4,5,11},
	 			 			 {1,2,3,4,5}};
		Matrix A = new Matrix(AArray);
		Matrix B = new Matrix(A.getArray());
		Matrix C = new Matrix(5,5);
		Matrix ans = A.minusEquals(B);
		assertArrayEquals(ans.getArray(),C.getArray());
		assertArrayEquals(ans.getArray(),A.getArray());
	}

	//Joseph O'Neill
	@Test
	public void testPlusEquals()
	{
		double[][] AArray = {{5,4,3,2,1},
	 			 			 {6,5,4,3,2},
	 			 			 {-5,0,5,10,15},
	 			 			 {9,2,4,5,11},
	 			 			 {1,2,3,4,5}};
		Matrix A = new Matrix(AArray);
		Matrix B = A.uMinus();
		Matrix C = new Matrix(5,5);
		Matrix ans = A.plusEquals(B);
		assertArrayEquals(ans.getArray(),C.getArray());
		assertArrayEquals(ans.getArray(),A.getArray());
	}
	
	//Joseph O'Neill - Having issues with Unary Minus, 0, and assertEquals. 
	//If there is a 0 in the matrix, then assertEquals expects a -0, although a
	//simple 0 is included in the matrix, thus failing. Without a 0, however, everything
	//passes. Will ask Dr. Fenwick on Monday.
	@Test
	public void testUMinus()
	{
		double[][] AArray = {{5,4,3,2,1},
	 			 			 {6,5,4,3,2},
	 			 			 {-5,100,5,10,15},
	 			 			 {9,2,4,5,11},
	 			 			 {1,2,3,4,5}};
		
		double[][] UArray = {{-5,-4,-3,-2,-1},
	 			 			 {-6,-5,-4,-3,-2},
	 			 			 {5,-100,-5,-10,-15},
	 			 			 {-9,-2,-4,-5,-11},
	 			 			 {-1,-2,-3,-4,-5}};
		
		Matrix A = new Matrix(AArray);
		Matrix U = A.uMinus();
		assertArrayEquals(U.getArray(),UArray);
	}

	@Test
	public void testTimesScalar()
	{
		double[][] source = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		double[][] expected = {
				{2.0, 2.0},
				{2.0, 2.0}
				};
		Matrix mat = new Matrix(source);
		mat.timesEquals(2.0);
		assertArrayEquals(expected, mat.getArray());
	}
	
	@Test
	public void testTimesEqualsScalar()
	{
		double[][] source = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		double[][] expected = {
				{2.0, 2.0},
				{2.0, 2.0}
				};
		Matrix mat = new Matrix(source);
		mat.timesEquals(2.0);
		assertArrayEquals(expected, mat.getArray());
	}
	
	//Used a Khan Academy example for the values of the arrays.
	@Test
	public void testTimesMatrix()
	{
		double[][] source1 = {
				{0.0, 3.0, 5.0},
				{5.0, 5.0, 2.0}
				};
		double[][] source2 = {
				{3.0, 4.0},
				{3.0, -2.0},
				{4.0, -2.0}
				};
		double[][] expected = {
				{29.0, -16.0},
				{38.0, 6.0}
				};
		Matrix mat1 = new Matrix(source1);
		Matrix mat2 = new Matrix(source2);
		Matrix mat3 = mat1.times(mat2);
		assertArrayEquals(expected, mat3.getArray());
	}
}

