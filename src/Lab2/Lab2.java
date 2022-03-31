package Lab2;

public class Lab2 {

    private static Double eps = Math.pow(0.1, 7);

    public static void Newton() {
        Double[] Xk = {-Math.PI / 4, -2.3};
        Double[] Xk1 = {0.0, 0.0};
        Double[][] delta;
        Double norm = 1.0;
        int iteration = 0;
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-27s", " x1(k): " + Xk[0]);
        System.out.printf("%-27s", " x2(k): " + Xk[1]);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: ");
        System.out.println();
        while (norm > eps) {
            iteration++;
            double cos = Math.cos(Xk[0] - 0.5 * Xk[1]);
            Double[][] F = {{cos + 1, -0.5 * cos + 2 * Xk[1]}, {32 * Xk[0], -2 * Xk[1]}};
            Double[][] f = {{-(Math.sin(Xk[0] - 0.5 * Xk[1]) + Xk[0] + Xk[1] * Xk[1] - 5)}, {-(16 * Xk[0] * Xk[0] - Xk[1] * Xk[1] - 4)}};
            delta = Gauss_solution(F, f);
            Xk1[0] = Xk[0] + delta[0][0];
            Xk1[1] = Xk[1] + delta[1][0];
            norm = Math.max(Math.abs(Xk[0] - Xk1[0]), Math.abs(Xk[1] - Xk1[1]));
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-27s", " x1(k): " + Xk1[0]);
            System.out.printf("%-27s", " x2(k): " + Xk1[1]);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + norm);
            System.out.println();
            Xk = Xk1.clone();
        }
        System.out.println("Newton result: (" + Xk1[0] + ", " + Xk1[1] + ")");
    }

    public static void Secants() {
        Double[] Xk = {-Math.PI / 4, -2.3};
        Double[] Xk1 = {0.0, 0.0};
        Double[][] delta;
        Double norm = 1.0;
        int iteration = 0;
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-27s", " x1(k): " + Xk[0]);
        System.out.printf("%-27s", " x2(k): " + Xk[1]);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: ");
        System.out.println();
        iteration++;
        double cos = Math.cos(Xk[0] - 0.5 * Xk[1]);
        Double[][] F = {{cos + 1, -0.5 * cos + 2 * Xk[1]}, {32 * Xk[0], -2 * Xk[1]}};
        Double[][] f = {{-(Math.sin(Xk[0] - 0.5 * Xk[1]) + Xk[0] + Xk[1] * Xk[1] - 5)}, {-(16 * Xk[0] * Xk[0] - Xk[1] * Xk[1] - 4)}};
        delta = Gauss_solution(F, f);
        Xk1[0] = Xk[0] + delta[0][0];
        Xk1[1] = Xk[1] + delta[1][0];
        norm = Math.max(Math.abs(Xk[0] - Xk1[0]), Math.abs(Xk[1] - Xk1[1]));
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-27s", " x1(k): " + Xk1[0]);
        System.out.printf("%-27s", " x2(k): " + Xk1[1]);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: " + norm);
        System.out.println();
        F[0][0] = ((Math.sin(Xk1[0] - 0.5 * Xk1[1]) + Xk1[0] + Xk1[1] * Xk1[1] - 5) - (Math.sin(Xk[0] - 0.5 * Xk1[1]) + Xk[0] + Xk1[1] * Xk1[1] - 5)) / (Xk1[0] - Xk[0]);
        F[0][1] = ((Math.sin(Xk1[0] - 0.5 * Xk1[1]) + Xk1[0] + Xk1[1] * Xk1[1] - 5) - (Math.sin(Xk1[0] - 0.5 * Xk[1]) + Xk1[0] + Xk[1] * Xk[1] - 5)) / (Xk1[1] - Xk[1]);
        F[1][0] = ((16 * Xk1[0] * Xk1[0] - Xk1[1] * Xk1[1] - 4) - (16 * Xk[0] * Xk[0] - Xk1[1] * Xk1[1] - 4)) / (Xk1[0] - Xk[0]);
        F[1][1] = ((16 * Xk1[0] * Xk1[0] - Xk1[1] * Xk1[1] - 4) - (16 * Xk1[0] * Xk1[0] - Xk[1] * Xk[1] - 4)) / (Xk1[1] - Xk[1]);
        while (norm > eps && iteration < 20) {
            iteration++;
            f = new Double[][]{{-(Math.sin(Xk[0] - 0.5 * Xk[1]) + Xk[0] + Xk[1] * Xk[1] - 5)}, {-(16 * Xk[0] * Xk[0] - Xk[1] * Xk[1] - 4)}};
            delta = Gauss_solution(F, f);
            Xk1[0] = Xk[0] + delta[0][0];
            Xk1[1] = Xk[1] + delta[1][0];
            norm = Math.max(Math.abs(Xk[0] - Xk1[0]), Math.abs(Xk[1] - Xk1[1]));
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-27s", " x1(k): " + Xk1[0]);
            System.out.printf("%-27s", " x2(k): " + Xk1[1]);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + norm);
            System.out.println();
            F[0][0] = ((Math.sin(Xk1[0] - 0.5 * Xk1[1]) + Xk1[0] + Xk1[1] * Xk1[1] - 5) - (Math.sin(Xk[0] - 0.5 * Xk1[1]) + Xk[0] + Xk1[1] * Xk1[1] - 5)) / (Xk1[0] - Xk[0]);
            F[0][1] = ((Math.sin(Xk1[0] - 0.5 * Xk1[1]) + Xk1[0] + Xk1[1] * Xk1[1] - 5) - (Math.sin(Xk1[0] - 0.5 * Xk[1]) + Xk1[0] + Xk[1] * Xk[1] - 5)) / (Xk1[1] - Xk[1]);
            F[1][0] = ((16 * Xk1[0] * Xk1[0] - Xk1[1] * Xk1[1] - 4) - (16 * Xk[0] * Xk[0] - Xk1[1] * Xk1[1] - 4)) / (Xk1[0] - Xk[0]);
            F[1][1] = ((16 * Xk1[0] * Xk1[0] - Xk1[1] * Xk1[1] - 4) - (16 * Xk1[0] * Xk1[0] - Xk[1] * Xk[1] - 4)) / (Xk1[1] - Xk[1]);
            Xk = Xk1.clone();
        }
        System.out.println("Secants result: (" + Xk1[0] + ", " + Xk1[1] + ")");
    }

    public static void Gauss_Seidel() {
        Double[] Xk = {-Math.PI / 4, -2.3};
        Double[] Xk1 = {0.0, 0.0};
        double dlt = 1;
        Double norm = 1.0;
        int iteration = 0;
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-27s", " x1(k): " + Xk[0]);
        System.out.printf("%-27s", " x2(k): " + Xk[1]);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: ");
        System.out.println();
        while (norm > eps) {
            iteration++;
            Double[] Xkc = Xk.clone();
            while(dlt > eps){
                Xk1[0] = Xk[0] - (16 * Xk[0] * Xk[0] - Xk[1] * Xk[1] - 4)/(32 * Xk[0]);
                dlt = Math.abs(16 * Xk1[0] * Xk1[0] - Xk[1] * Xk[1] - 4);
                Xk[0] = Xk1[0];
            }
            dlt = 1;
            while(dlt > eps){
                Xk1[1] = Xk[1] - (Math.sin(Xk1[0] - 0.5 * Xk[1]) + Xk1[0] + Xk[1] * Xk[1] - 5)/(-0.5*Math.cos(Xk1[0] - 0.5 * Xk[1]) + 2 * Xk[1]);
                dlt = Math.abs(Math.sin(Xk1[0] - 0.5 * Xk1[1]) + Xk1[0] + Xk1[1] * Xk1[1] - 5);
                Xk[1] = Xk1[1];
            }
            dlt = 1;
            norm = Math.max(Math.abs(Xkc[0] - Xk1[0]), Math.abs(Xkc[1] - Xk1[1]));
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-27s", " x1(k): " + Xk1[0]);
            System.out.printf("%-27s", " x2(k): " + Xk1[1]);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + norm);
            System.out.println();
        }
        System.out.println("Gauss-Seidel result: (" + Xk1[0] + ", " + Xk1[1] + ")");
    }

    public static Double[][] Gauss_solution(Double matrix_A[][], Double matrix_f[][]) {
        Double solution[][] = new Double[matrix_A.length][1];
        Double max_abs;
        Double temp[];
        int max_abs_j;
        double a_i_i, a_i_i1;

        for (int i = 0; i < matrix_A.length; i++) {
//Поиск максимального элемента
            max_abs = Math.abs(matrix_A[i][i]);
            max_abs_j = i;
            for (int j = i; j < matrix_A.length; j++) {
                if (Math.abs(max_abs) < Math.abs(matrix_A[j][i])) {
                    max_abs = matrix_A[j][i];
                    max_abs_j = j;
                }
            }
//Перестановка строки с максимальным элементом
            if (max_abs_j != i) {
                temp = matrix_A[i];
                matrix_A[i] = matrix_A[max_abs_j];
                matrix_A[max_abs_j] = temp;
                temp = matrix_f[i];
                matrix_f[i] = matrix_f[max_abs_j];
                matrix_f[max_abs_j] = temp;
            }
//Деление строки на ведущий элемент
            a_i_i = matrix_A[i][i];
            for (int j = i; j < matrix_A.length; j++)
                matrix_A[i][j] /= a_i_i;
            matrix_f[i][0] /= a_i_i;
//Зануление столбца
            if (i < matrix_A.length - 1) {
                for (int j = i; j < matrix_A.length - 1; j++) {
                    a_i_i1 = matrix_A[j + 1][i];
                    for (int k = 0; k < matrix_A.length; k++) {
                        matrix_A[j + 1][k] -= matrix_A[i][k] * a_i_i1;
                    }
                    matrix_f[j + 1][0] -= matrix_f[i][0] * a_i_i1;
                }
            }
        }
//Обратный ход
        int count = 0;
        double temp_solution = 0;
        for (int i = matrix_A.length - 1; i > -1; i--) {
            temp_solution = matrix_f[i][0];
            for (int j = 0, k = matrix_A.length - 1; j < count && k > 0; j++, k--)
                temp_solution -= matrix_A[i][k] * solution[k][0];
            solution[i][0] = temp_solution;
            count++;
        }
        return solution;
    }

    public static void main(String[] args) {
        Lab2.Newton();
        System.out.println();
        Lab2.Secants();
        System.out.println();
        Lab2.Gauss_Seidel();
    }
}





