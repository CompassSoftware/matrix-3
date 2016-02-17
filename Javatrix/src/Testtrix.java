import Javatrix.*;
import static org.junit.Assert.*;
import org.junit.Test;

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
    }Test for matrix second time
    */

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
	public void testGetArray() {
		Matrix mat = new Matrix(3, 3);
		double[][] ar = mat.getArray();
		assertEquals(mat.get(1, 1), ar[1][1], 0);
		// Check that changing a value changes the internal array
		ar[1][1] = 5;
		assertEquals(mat.get(1, 1), ar[1][1], 0);
	}
	
	@Test
	public void testGetArrayCopy() {
		// Create matrix
		double[][] source = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8},
				{9, 10, 11}
				};
		double val = 15;
		Matrix mat = new Matrix(source);
		double[][] arrayA = mat.getArray();
		double[][] arrayB = mat.getArrayCopy();
		// Check that matrix was created correctly
		assertEquals(arrayA[0][0], arrayB[0][0], 0);
		assertEquals(arrayA[3][2], arrayA[3][2], 0);
		// Check that changing matrix does not change original array
		arrayA[2][1] = val;
		assertNotEquals(arrayA[2][1], arrayB[2][1], 0);
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
		assertEquals(mat.get(0, 0), source[0][0], 0);
		assertEquals(mat.get(3, 2), source[3][2], 0);
		// Check that changing matrix does not change original array
		mat.set(2, 1, val);
		assertNotEquals(mat.get(2, 1), source[2][1], 0);
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
	
}