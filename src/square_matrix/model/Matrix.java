package square_matrix.model;

public class Matrix {
    // A two-dimensional array to store the elements of the matrix
    private double[][] elements;

    // Number of rows of the matrix
    private int rows;

    // Number of columns of the matrix
    private int columns;

    // A one-dimensional array to store the matrix elements as a packed vector
    public double[] vectors;

    /**
     * Constructs a matrix with the given number of rows and columns
     *
     * @param rows    number of rows
     * @param columns number of columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new double[rows][columns];
    }

    /**
     * Constructs a matrix from the given two-dimensional array
     *
     * @param matrix two-dimensional array of elements
     */
    public Matrix(double[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;

        // Ensure all the rows of the matrix have the same length
        for (int var2 = 0; var2 < this.rows; ++var2) {
            if (matrix[var2].length != this.columns) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }
        this.elements = matrix;
    }

    /**
     * Constructs a matrix from the given packed vector and the number of rows
     *
     * @param rows packed vector of elements
     * @param var2 number of rows
     */
    public Matrix(double[] rows, int var2) {
        this.rows = var2;
        this.columns = var2 != 0 ? rows.length / var2 : 0;

        // Ensure that the array length is a multiple of the number of rows
        if (var2 * this.columns != rows.length) {
            throw new IllegalArgumentException("Array length must be a multiple of m.");
        } else {
            this.elements = new double[var2][this.columns];
            for (int var3 = 0; var3 < var2; ++var3) {
                for (int var4 = 0; var4 < this.columns; ++var4) {
                    this.elements[var3][var4] = rows[var3 + var4 * var2];
                }
            }
        }
    }

    public double[] getVectors() {
        return vectors;
    }

    /**
     * Sets the vector representation of this matrix.
     *
     * @param vectors the new vector representation
     */
    public void setVectors(double[] vectors) {
        this.vectors = vectors;
    }

    public double[][] getArray() {
        return elements;
    }
}


