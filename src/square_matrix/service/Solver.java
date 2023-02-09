package square_matrix.service;


import java.text.DecimalFormat;

/**
 * Solver class that provides a solution for a system of linear equations.
 */
public class Solver {
    /*
        Format for the rounded values in the solution.
    */
    private static final DecimalFormat decimalFormat = new DecimalFormat("###.###");

    public static boolean isMatrixSymmetric(double matrix[][]) {
        int i, j, flag = 1;
        int row = matrix.length;
        int col = matrix[0].length;

        // Nested for loop for matrix iteration
        // Outer loop for rows
        for (i = 0; i < row; i++) {
            // Inner loop for columns
            for (j = 0; j < col; j++) {
                // Print matrix
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.println("");
        }

        // Matrix 2
        // Finding transpose of the matrix
        double[][] transpose = new double[row][col];

        // Again, nested for loop for matrix iteration
        // Outer loop for rows
        for (i = 0; i < row; i++) {
            // Inner loop for columns
            for (j = 0; j < col; j++) {
                // Print matrix elements
                transpose[j][i] = matrix[i][j];
            }
        }

        // Condition check over Matrix 1 with Matrix 2
        if (row == col) {
            // Outer loop for rows
            for (i = 0; i < row; i++) {
                // Inner loop for columns
                for (j = 0; j < col; j++) {
                    // Comparing two matrices
                    if (matrix[i][j] != transpose[i][j]) {
                        flag = 0;
                        break;
                    }
                }

                // Setting a flag value for symmetric matrix
                if (flag == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        // If it isn't a square matrix
        // then it can't be a symmetric matrix
        else {
            return false;
        }
        return false;
    }

    public static double[] solve(double[][] A, double[] b) {
        int n = A.length;
        double[] x = new double[n];

        // Compute the Cholesky decomposition of A
        double[][] L = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }
                if (i == j) {
                    L[i][i] = Math.sqrt(A[i][i] - sum);
                } else {
                    L[i][j] = (A[i][j] - sum) / L[j][j];
                }
            }
        }

        // Solve L * y = b using forward substitution
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * y[j];
            }
            y[i] = (b[i] - sum) / L[i][i];
        }

        // Solve L^T * x = y using backward substitution
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += L[j][i] * x[j];
            }
            x[i] = (y[i] - sum) / L[i][i];
        }

        round(x);
        return x;
    }

    /**
     * Rounds the values in the given array.
     *
     * @param rowPackedCopy the array of values to be rounded
     */
    private static void round(double[] rowPackedCopy) {
        // loop through the array
        for (int i = 0; i < rowPackedCopy.length; i++) {
            // round each value in the array
            rowPackedCopy[i] = Double.parseDouble(decimalFormat.format(rowPackedCopy[i]));
        }
    }
}
