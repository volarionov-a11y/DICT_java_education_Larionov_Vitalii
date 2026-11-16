
package MatrixProcessing;

import java.util.Scanner;
import java.util.Locale;
import java.math.BigDecimal;

public class MatrixProcessing {

    // Поля об'єкта: розміри та елементи матриці
    private final int rows;
    private final int cols;
    private final double[][] data;

    // Конструктор створює матрицю заданого розміру
    public MatrixProcessing(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException();
        }

        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    // Зчитування матриці з консолі

    public static MatrixProcessing read(
            Scanner scanner,
            String sizeMessage,
            String matrixMessage
    ) {
        System.out.print(sizeMessage);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        MatrixProcessing matrix =
                new MatrixProcessing(rows, cols);

        System.out.println(matrixMessage);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.data[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }


    // Метод додавання двох матриць
    public MatrixProcessing add(MatrixProcessing other) {

        // Матриці повинні мати однакові розміри
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException();
        }

        MatrixProcessing result =
                new MatrixProcessing(rows, cols);

        // Додаємо відповідні елементи
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] =
                        data[i][j] + other.data[i][j];
            }
        }

        return result;
    }


    // Множення матриці на константу
    public MatrixProcessing multiply(double constant) {

        // Створюємо нову матрицю такого самого розміру
        MatrixProcessing result =
                new MatrixProcessing(rows, cols);

        // Проходимо по всіх рядках
        for (int i = 0; i < rows; i++) {

            // Проходимо по всіх стовпцях
            for (int j = 0; j < cols; j++) {

                // Множимо кожен елемент на константу
                result.data[i][j] =
                        data[i][j] * constant;
            }
        }

        // Повертаємо новий об'єкт із результатом
        return result;
    }


    // Множення двох матриць
    public MatrixProcessing multiply(MatrixProcessing other) {

        // Перевіряємо, чи можна перемножити матриці
        if (cols != other.rows) {
            throw new IllegalArgumentException(
                    "Incompatible matrix sizes"
            );
        }

        // Створюємо матрицю результату
        MatrixProcessing result =
                new MatrixProcessing(rows, other.cols);

        // Перебираємо рядки першої матриці
        for (int i = 0; i < rows; i++) {

            // Перебираємо стовпці другої матриці
            for (int j = 0; j < other.cols; j++) {

                // Множимо відповідні елементи та додаємо
                for (int k = 0; k < cols; k++) {
                    result.data[i][j] +=
                            data[i][k] * other.data[k][j];
                }
            }
        }

        return result;
    }



    // Виведення матриці
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (j > 0) {
                    System.out.print(" ");
                }

                String value = BigDecimal.valueOf(data[i][j])
                        .stripTrailingZeros()
                        .toPlainString();

                System.out.print(value);
            }

            System.out.println();
        }
    }

    private static void showMenu() {

        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");

        System.out.print("Your choice: ");
    }

    // Точка входу в програму



    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in).useLocale(Locale.US);

        // Цикл роботи програми
        while (true) {

            // Виводимо меню
            showMenu();

            // Зчитуємо вибір користувача
            int choice = scanner.nextInt();

            // Завершення програми
            if (choice == 0) {
                break;
            }

            try {
                switch (choice) {

                    // Додавання двох матриць
                    case 1: {
                        MatrixProcessing first = read(
                                scanner,
                                "Enter size of first matrix: ",
                                "Enter first matrix:"
                        );

                        MatrixProcessing second = read(
                                scanner,
                                "Enter size of second matrix: ",
                                "Enter second matrix:"
                        );

                        System.out.println("The result is:");
                        first.add(second).print();
                        break;
                    }

                    // Множення матриці на константу
                    case 2: {
                        MatrixProcessing matrix = read(
                                scanner,
                                "Enter size of matrix: ",
                                "Enter matrix:"
                        );

                        System.out.print("Enter constant: ");
                        double constant = scanner.nextDouble();

                        System.out.println("The result is:");
                        matrix.multiply(constant).print();
                        break;
                    }

                    // Множення двох матриць
                    case 3: {
                        MatrixProcessing first = read(
                                scanner,
                                "Enter size of first matrix: ",
                                "Enter first matrix:"
                        );

                        MatrixProcessing second = read(
                                scanner,
                                "Enter size of second matrix: ",
                                "Enter second matrix:"
                        );

                        System.out.println("The result is:");
                        first.multiply(second).print();
                        break;
                    }

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "The operation cannot be performed."
                );
            }

            System.out.println();
        }
    }



}
