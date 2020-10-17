package processor;

public class MatrixUtils {


    public double[][] add(double[][] first, double[][] second, int n, int m) {
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = first[i][j] + second[i][j];
            }
        }
        return result;
    }

    public double[][] multiplyByConstant(double[][] matrix, int n, int m, double constant) {
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }
        return result;
    }

    public double[][] multiply(double[][] first, double[][] second, int n, int m, int m2) {
        double[][] result = new double[n][m2];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m2; k++) {
                for (int j = 0; j < m; j++) {
                    result[i][k] += first[i][j] * second[j][k];
                }
            }
        }
        return result;
    }

    public double[][] mainDiagonalTranspose(double[][] matrix, int n, int m) {
        double[][] result = new double[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public double[][] sideDiagonalTranspose(double[][] matrix, int n, int m) {
        double[][] result = new double[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[m - j - 1][n - i - 1] = matrix[i][j];
            }
        }
        return result;
    }

    public double[][] verticalLineTranspose(double[][] matrix, int n, int m) {
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][m - j - 1] = matrix[i][j];
            }
        }
        return result;
    }

    public double[][] horizontalLineTranspose(double[][] matrix, int n, int m) {
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[n - i - 1][j] = matrix[i][j];
            }
        }
        return result;
    }

    public double findDeterminant(double[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }
        double det = 0;
        for (int i = 0; i < n; i++) {
            det += matrix[i][0] * Math.pow(-1, i) * findDeterminant(minor(matrix, n, i, 0), n - 1);
        }
        return det;
    }

    private double[][] minor(double[][] matrix, int n, int excludedRow, int excludedCol) {
        double[][] result = new double[n - 1][n - 1];

        int row = 0;
        for (int i = 0; i < n - 1; i++) {
            if (row != excludedRow) {
                int col = 0;
                for (int j = 0; j < n - 1; j++) {
                    if (col != excludedCol) {
                        result[i][j] = matrix[row][col];
                    } else {
                        j--;
                    }
                    col++;
                }
            } else {
                i--;
            }
            row++;
        }
        return result;
    }

    public double[][] inverseMatrix(double[][] matrix, int n) {
        double det = findDeterminant(matrix, n);
        if (det == 0) {
            return null;
        }
        matrix = mainDiagonalTranspose(matrix, n, n);
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = Math.pow(-1, i+j) * findDeterminant(minor(matrix, n, i, j), n - 1);
            }
        }
        return multiplyByConstant(result, n, n, 1/det);
    }

}
