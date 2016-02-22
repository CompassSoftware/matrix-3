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
	
	static final private double[][] source ={
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{9, 10, 11}
			}; 

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
		int n = 3;
		int m = 2;
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
		mat.set(1, 1, val);
		assertNotEquals(mat.get(2, 1), source[1][1], 0);
	}
	
	@Test
	public void testConstructZerosMatrix()
	{
		double[][] source = {
				{0.0, 0.0},
				{0.0, 0.0}
				};
		Matrix zero = new Matrix(2,2);
		assertArrayEquals(source, zero.getArray());
	}
	
	@Test
	public void testConstructSMatrix()
	{
		double[][] source = {
				{1.0, 1.0},
				{1.0, 1.0}
				};
		Matrix s = new Matrix(2, 2, 1.0);
		assertArrayEquals(source, s.getArray());
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
		Matrix mat = new Matrix(source);
		mat.print(5, 1);
		String output = testOut.toString();
		assertEquals("  0.0  1.0  2.0\n"
				+ "  3.0  4.0  5.0\n"
				+ "  6.0  7.0  8.0\n"
				+ "  9.0 10.0 11.0\n",
				output);
	}
	
	@Test
	public void testPrintOutputStream()
	{
		Matrix mat = new Matrix(source);
		PrintWriter writer = new PrintWriter(testOut);
		mat.print(writer, 5, 1);
		writer.flush();
		String output = testOut.toString();
		assertEquals("  0.0  1.0  2.0\n"
				+ "  3.0  4.0  5.0\n"
				+ "  6.0  7.0  8.0\n"
				+ "  9.0 10.0 11.0\n",
				output);
	}
	
	@Test
	public void testPrintNumberFormat()
	{
		Matrix mat = new Matrix(source);
		DecimalFormat format = new DecimalFormat("#0.0");
		mat.print(format, 5);
		String output = testOut.toString();
		assertEquals("  0.0  1.0  2.0\n"
				+ "  3.0  4.0  5.0\n"
				+ "  6.0  7.0  8.0\n"
				+ "  9.0 10.0 11.0\n",
				output);
	}
	
	@Test
	public void testPrintNumberFormatOutputStream()
	{
		Matrix mat = new Matrix(source);
		DecimalFormat format = new DecimalFormat("#0.0");
		PrintWriter writer = new PrintWriter(testOut);
		mat.print(writer, format, 5);
		writer.flush();
		String output = testOut.toString();
		assertEquals("  0.0  1.0  2.0\n"
				+ "  3.0  4.0  5.0\n"
				+ "  6.0  7.0  8.0\n"
				+ "  9.0 10.0 11.0\n",
				output);
	}
	
	@Test
	public void testGetMatrixRange()
	{
		Matrix mat = new Matrix(source);
		int i1 = 0;
		int i2 = 1;
		int j1 = 0;
		int j2 = 2;
		int rows = i2 - i1 + 1;
		int cols = j2 - j1 + 1;
		Matrix mat1 = mat.getMatrix(i1, i2, j1, j2);
		assertEquals(mat1.getRowDimension(), rows);
		assertEquals(mat1.getColumnDimension(), cols);
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				assertEquals(mat1.get(i, j), mat.get(i + i1, j + j1), 0);
			}
		}
	}
	
	@Test
	public void testGetMatrixSelectRows()
	{
		Matrix mat = new Matrix(source);
		int[] rows = {0, 2};
		int j1 = 0;
		int j2 = 2;
		Matrix mat1 = mat.getMatrix(rows, j1, j2);
		for (int i = 0; i < rows.length; i++)
		{
			for (int j = 0; j <= j2 - j1; j++)
			{
				assertEquals(mat1.get(i, j), mat.get(rows[i], j + j1), 0);
			}
		}
	}
	
	@Test
	public void testGetMatrixSelectColumns()
	{
		Matrix mat = new Matrix(source);
		int[] columns = {0, 2};
		int i1 = 0;
		int i2 = 2;
		Matrix mat1 = mat.getMatrix(i1, i2, columns);
		for (int i = 0; i <= i2 - i1; i++)
		{
			for (int j = 0; j < columns.length; j++)
			{
				assertEquals(mat1.get(i, j), mat.get(i + i1, columns[j]), 0);
			}
		}
	}
	
	@Test
	public void testGetMatrixSelect()
	{
		Matrix mat = new Matrix(source);
		int[] rows = {1, 3};
		int[] columns = {0, 2};
		Matrix mat1 = mat.getMatrix(rows, columns);
		for (int i = 0; i < rows.length; i++)
		{
			for (int j = 0; j < columns.length; j++)
			{
				assertEquals(mat1.get(i, j), mat.get(rows[i], columns[j]), 0);
			}
		}
	}
}