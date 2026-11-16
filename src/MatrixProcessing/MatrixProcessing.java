
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


    // Транспонування матриці
    public MatrixProcessing transpose(int type) {

        // Перевіряємо, чи вибраний правильний тип
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException(
                    "Invalid transpose type"
            );
        }

        int resultRows;
        int resultCols;

        // При транспонуванні відносно діагоналей
        // кількість рядків і стовпців міняється місцями
        if (type == 1 || type == 2) {
            resultRows = cols;
            resultCols = rows;
        } else {
            resultRows = rows;
            resultCols = cols;
        }

        // Створюємо нову матрицю для результату
        MatrixProcessing result =
                new MatrixProcessing(resultRows, resultCols);

        // Проходимо всі клітинки результуючої матриці
        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < resultCols; j++) {

                switch (type) {

                    // 1. Головна діагональ
                    case 1:
                        result.data[i][j] = data[j][i];
                        break;

                    // 2. Побічна діагональ
                    case 2:
                        result.data[i][j] =
                                data[rows - 1 - j][cols - 1 - i];
                        break;

                    // 3. Вертикальна вісь
                    case 3:
                        result.data[i][j] =
                                data[i][cols - 1 - j];
                        break;

                    // 4. Горизонтальна вісь
                    case 4:
                        result.data[i][j] =
                                data[rows - 1 - i][j];
                        break;
                }
            }
        }

        return result;
    }


    // Обчислення визначника матриці методом Гауса
    public double determinant() {

        // Визначник існує тільки для квадратної матриці
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Matrix must be square"
            );
        }

        // Створюємо копію матриці
        double[][] temp = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            temp[i] = data[i].clone();
        }

        double determinant = 1.0;

        // Проходимо по стовпцях
        for (int col = 0; col < rows; col++) {

            // Шукаємо рядок із найбільшим за модулем
            // елементом у поточному стовпці
            int pivotRow = col;

            for (int row = col + 1; row < rows; row++) {
                if (Math.abs(temp[row][col]) >
                        Math.abs(temp[pivotRow][col])) {
                    pivotRow = row;
                }
            }

            // Якщо головний елемент майже нульовий,
            // визначник дорівнює нулю
            if (Math.abs(temp[pivotRow][col]) < 1e-12) {
                return 0.0;
            }

            // Міняємо рядки місцями, якщо потрібно
            if (pivotRow != col) {
                double[] swap = temp[col];
                temp[col] = temp[pivotRow];
                temp[pivotRow] = swap;

                // Перестановка двох рядків змінює знак
                determinant = -determinant;
            }

            // Головний елемент діагоналі
            double pivot = temp[col][col];

            // Множимо визначник на головний елемент
            determinant *= pivot;

            // Обнуляємо елементи під головною діагоналлю
            for (int row = col + 1; row < rows; row++) {

                double factor = temp[row][col] / pivot;

                for (int j = col + 1; j < cols; j++) {
                    temp[row][j] -= factor * temp[col][j];
                }

                temp[row][col] = 0.0;
            }
        }

        return determinant;
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
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
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


                    // Транспонування матриці
                    case 4: {

                        // Меню вибору способу транспонування
                        System.out.println("1. Main diagonal");
                        System.out.println("2. Side diagonal");
                        System.out.println("3. Vertical line");
                        System.out.println("4. Horizontal line");

                        System.out.print("Your choice: ");
                        int type = scanner.nextInt();

                        // Зчитуємо матрицю
                        MatrixProcessing matrix = read(
                                scanner,
                                "Enter matrix size: ",
                                "Enter matrix:"
                        );

                        // Виконуємо транспонування
                        System.out.println("The result is:");
                        matrix.transpose(type).print();

                        break;
                    }


                    // Обчислення визначника матриці
                    case 5: {

                        MatrixProcessing matrix = read(
                                scanner,
                                "Enter matrix size: ",
                                "Enter matrix:"
                        );

                        double result = matrix.determinant();

                        System.out.println("The result is:");

                        // Округлюємо для коректного виведення
                        String formattedResult =
                                BigDecimal.valueOf(result)
                                        .setScale(
                                                6,
                                                java.math.RoundingMode.HALF_UP
                                        )
                                        .stripTrailingZeros()
                                        .toPlainString();

                        System.out.println(formattedResult);

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
