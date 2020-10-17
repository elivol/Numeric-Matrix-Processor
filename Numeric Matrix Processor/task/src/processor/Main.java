package processor;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static MatrixUtils utils;
    private final static String MENU = "1. Add matrices\n" +
              "2. Multiply matrix by a constant\n" +
              "3. Multiply matrices\n" +
              "4. Transpose matrix\n" +
              "5. Calculate a determinant\n" +
              "6. Inverse matrix\n" +
              "0. Exit\n" +
              "Your choice: ";
    private final static String ENTER_SIZE = "Enter size of matrix: ";
    private final static String ENTER_MATRIX = "Enter matrix:\n";
    private final static String ENTER_FIRST_SIZE = "Enter size of first matrix: ";
    private final static String ENTER_FIRST_MATRIX = "Enter first matrix:\n";
    private final static String ENTER_SECOND_SIZE = "Enter size of second matrix: ";
    private final static String ENTER_SECOND_MATRIX = "Enter second matrix:\n";
    private final static String ENTER_CONSTANT = "Enter constant: ";
    private final static String RESULT = "The result is:\n";
    private final static String ERROR = "The operation cannot be performed.\n";


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        utils = new MatrixUtils();

        int action;
        do {
            System.out.print(MENU);
            action = scanner.nextInt();
            switch (action) {
                case 1:
                    add();
                    break;
                case 2:
                    multiplyByConstant();
                    break;
                case 3:
                    multiply();
                    break;
                case 4:
                    transpose();
                    break;
                case 5:
                    determinant();
                    break;
                case 6:
                    inverse();
                    break;
            }
            System.out.println();
        }while (action != 0);

    }

    private static void add() {
        System.out.print(ENTER_FIRST_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_FIRST_MATRIX);
        double [][] first = initMatrix(n, m);

        System.out.print(ENTER_SECOND_SIZE);
        int k = scanner.nextInt();
        int p = scanner.nextInt();
        System.out.print(ENTER_SECOND_MATRIX);
        double [][] second = initMatrix(k, p);

        if (n != k || m != p) {
            System.out.print(ERROR);
        } else {
            double[][] result = utils.add(first, second, n, m);
            System.out.print(RESULT);
            printMatrix(result, n, m);
        }
    }

    private static void multiplyByConstant() {
        System.out.print(ENTER_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_MATRIX);
        double [][] matrix = initMatrix(n, m);

        System.out.print(ENTER_CONSTANT);
        double constant = scanner.nextDouble();

        double[][] result = utils.multiplyByConstant(matrix, n, m, constant);
        System.out.print(RESULT);
        printMatrix(result, n, m);
    }

    private static void multiply() {
        System.out.print(ENTER_FIRST_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_FIRST_MATRIX);
        double [][] first = initMatrix(n, m);

        System.out.print(ENTER_SECOND_SIZE);
        int k = scanner.nextInt();
        int p = scanner.nextInt();
        System.out.print(ENTER_SECOND_MATRIX);
        double [][] second = initMatrix(k, p);

        if (m != k) {
            System.out.print(ERROR);
        } else {
            double[][] result = utils.multiply(first, second, n, m, p);
            System.out.print(RESULT);
            printMatrix(result, n, p);
        }

    }

    public static void transpose() {
        final String menu = "1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice: ";
        System.out.print(menu);
        int option = scanner.nextInt();
        System.out.print(ENTER_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_MATRIX);
        double [][] matrix = initMatrix(n, m);

        switch (option) {
            case 1:
                matrix = utils.mainDiagonalTranspose(matrix, n, m);
                break;
            case 2:
                matrix = utils.sideDiagonalTranspose(matrix, n, m);
                break;
            case 3:
                matrix = utils.verticalLineTranspose(matrix, n, m);
                break;
            case 4:
                matrix = utils.horizontalLineTranspose(matrix, n, m);
                break;
        }
        System.out.print(RESULT);
        printMatrix(matrix, matrix.length, matrix[0].length);
    }

    public static void determinant() {
        System.out.print(ENTER_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_MATRIX);
        double [][] matrix = initMatrix(n, m);

        if (n != m) {
            System.out.print(ERROR);
        } else {
            System.out.print(RESULT);
            System.out.print(utils.findDeterminant(matrix, n));
        }

    }

    public static void inverse() {
        System.out.print(ENTER_SIZE);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(ENTER_MATRIX);
        double [][] matrix = initMatrix(n, m);

        if (n != m) {
            System.out.print(ERROR);
        } else {
            System.out.print(RESULT);
            matrix = utils.inverseMatrix(matrix, n);
            if (matrix == null) {
                System.out.println("This matrix doesn't have an inverse.");
            } else {
                printMatrix(matrix, n, n);
            }
        }
    }

    private static double[][] initMatrix(int n, int m) {
        double [][] matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    private static void printMatrix(double[][] matrix, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
