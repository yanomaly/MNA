package Lab3;
import static java.lang.Math.*;

public class Lab3 {
    private static int a = -2;
    private static int b = 2;
    private static Double h = 0.1;
    private static Double[] nodes = new Double[(int)((b - a)/h + 1)];
    private static Double[][] S;
    private static Double[][] M;

    public static Double[][] Gauss_solution(Double matrix_A[][], Double matrix_f[][]){
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
                if(Math.abs(max_abs) < Math.abs(matrix_A[j][i])) {
                    max_abs = matrix_A[j][i];
                    max_abs_j = j;
                }
            }
//Перестановка строки с максимальным элементом
            if(max_abs_j != i) {
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
            if(i < matrix_A.length - 1) {
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
        for (int i = matrix_A.length - 1; i > -1 ; i--) {
            temp_solution = matrix_f[i][0];
            for (int j = 0, k = matrix_A.length - 1; j < count && k > 0; j++, k--)
                temp_solution -= matrix_A[i][k] * solution[k][0];
            solution[i][0] = temp_solution;
            count++;
        }
        return solution;
    }
    public static double func(double x){
        return sin(2 * x)*log(x + 5);
    }
    public static double polinom(Double[][] ratio, double point){
        double value = 0;
        for (int i = 0; i < ratio.length; i++)
            value += pow(point, i) * ratio[i][0];
        return value;
    }
    public static double approximation(int n){
        double appr= 0;
        S = new Double[n + 1][n + 1];
        M = new Double[n + 1][1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = i; j < n + 1; j++) {
                double temp = 0;
                for (int k = 0; k < (b - a)/h + 1; k++)
                    temp += pow(a + k*h, i + j);
                S[i][j] = temp;
                S[j][i] = temp;
            }
        }
        for (int i = 0; i < n + 1; i++) {
            double temp = 0;
            for (int k = 0; k < (b - a)/h + 1; k++)
                temp += nodes[k] * pow(a + k*h, i);
            M[i][0] = temp;
        }
        Double[][] approx = Gauss_solution(S.clone(), M.clone());
        for (int i = 0; i < (b - a)/h + 1; i++)
           appr += pow(nodes[i] - polinom(approx, a + i*h), 2);
        System.out.println("Polynom: ");
            System.out.print(approx[approx.length - 1][0] + " * x^" + (approx.length - 1));
        for (int i = approx.length - 2; i >= 0; i--)
            System.out.print(" + " + approx[i][0] + " * x^" + i);
        System.out.println();
        return appr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < (b - a)/h + 1; i++)
            nodes[i] = func(a + i*h);
        System.out.println("Delta: \n" + approximation(3));
        System.out.println("Delta: \n" + approximation(6));
    }
}
