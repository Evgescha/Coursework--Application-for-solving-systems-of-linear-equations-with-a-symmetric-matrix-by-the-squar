package square_matrix.service;

import square_matrix.model.Matrix;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// File specification:
// [A], where A - number of rows and columns
// [1 -2 -3 4]
// [-5 -6 7 8] - where numbers - matrix data
// [] empty line
// [9 8 7] - where number - vector data

/**
 * FileOpener is a class used for opening a file containing matrix and vector data.
 **/
public class FileOpener {
    /*
    The method openFile is used for opening a file that contains matrix and vector data.
    @return Returns the matrix and vector data in the form of a Matrix object.
    @throws FileNotFoundException If the selected file is not found, this exception is thrown.
    */
    public static Matrix openFile() throws FileNotFoundException {
        // Creates an instance of JFileChooser to select a file.
        JFileChooser fileChooser = new JFileChooser();
        // The showDialog method returns the option selected by the user.
        int ret = fileChooser.showDialog(null, "Open file");
        // If the user approves the option, the selected file is processed.
        if (ret == JFileChooser.APPROVE_OPTION) {
            // The selected file is stored in the file variable.
            File file = fileChooser.getSelectedFile();
            // A scanner is created to read the data from the file.
            Scanner scanner = new Scanner(file);
            // The number of rows and cols in the matrix is read from the file.
            int rowAndCols = scanner.nextInt();
            // The matrix data is stored in a 2D array.
            double[][] matrixData = new double[rowAndCols][rowAndCols];
            // The matrix data is read from the file.
            for (int i = 0; i < rowAndCols; i++) {
                for (int j = 0; j < rowAndCols; j++) {
                    matrixData[i][j] = scanner.nextDouble();
                }
            }
            // Skipping two lines in the file.
            scanner.nextLine();
            scanner.nextLine();
            // The vector data is read from the file as a string.
            String vectorData = scanner.nextLine();
            // The vector data is converted to an array of double values.
            double[] vector = Arrays.stream(vectorData.split(" "))
                    .mapToDouble(Double::valueOf)
                    .toArray();
            // A matrix object is created using the matrix data.
            Matrix matrix = new Matrix(matrixData);
            // The vector data is set for the matrix object.
            matrix.setVectors(vector);
            // The matrix object is returned.
            return matrix;
        }
        // If no file is selected, null is returned.
        return null;
    }
}

