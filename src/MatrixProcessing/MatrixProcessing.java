
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
    public static MatrixProcessing read(Scanner scanner) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        MatrixProcessing matrix =
                new MatrixProcessing(rows, cols);

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

    // Точка входу в програму
    public static void main(String[] args) {
        Scanner scanner =
                new Scanner(System.in).useLocale(Locale.US);

        MatrixProcessing first = read(scanner);
        MatrixProcessing second = read(scanner);

        try {
            MatrixProcessing result = first.add(second);
            result.print();

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
    }
}
