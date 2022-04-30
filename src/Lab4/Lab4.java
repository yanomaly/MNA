package Lab4;
import static java.lang.Math.*;

public class Lab4 {

    private static int a = -2;
    private static int b = 2;
    private static Double h;
    private static Double[][] dim;
    private static Double[][] dimC;

    public static double func1(double x){
        return sin(2 * x)*log(x + 5);
    }
    public static double func2(double x){return sqrt(2* abs(x) + pow(x, 2));}

    public static void dimFill1(int deg){
        h = (double)(b - a) / deg;
        dim = new Double[deg + 1][deg + 2];
        for (int i = 0; i < dim[0].length - 1; i++) {
            dim[i][0] = -2 + i * h;
            dim[i][1] = func1(dim[i][0]);
        }
        for (int i = 2; i < dim[0].length; i++) {
            for (int j = 0; j < dim[0].length - i; j++) {
                dim[j][i] = (dim[j][i - 1] - dim[j + 1][i - 1])/(dim[j][0] - dim[j + i - 1][0]);
            }
        }
    }
    public static void dimFill11(int deg){
        dimC = new Double[deg + 1][deg + 2];
        for (int i = 0; i < dimC[0].length - 1; i++) {
            dimC[i][0] = (a + b) / 2 + (b - a) / 2 * cos((2 * i + 1) * PI / (2 * (deg + 1)));
            dimC[i][1] = func1(dimC[i][0]);
        }
        for (int i = 2; i < dimC[0].length; i++) {
            for (int j = 0; j < dimC[0].length - i; j++) {
                dimC[j][i] = (dimC[j][i - 1] - dimC[j + 1][i - 1])/(dimC[j][0] - dimC[j + i - 1][0]);
            }
        }
    }
    public static void dimFill2(int deg){
        h = (double)(b - a) / deg;
        dim = new Double[deg + 1][deg + 2];
        for (int i = 0; i < dim[0].length - 1; i++) {
            dim[i][0] = -2 + i * h;
            dim[i][1] = func2(dim[i][0]);
        }
        for (int i = 2; i < dim[0].length; i++) {
            for (int j = 0; j < dim[0].length - i; j++) {
                dim[j][i] = (dim[j][i - 1] - dim[j + 1][i - 1])/(dim[j][0] - dim[j + i - 1][0]);
            }
        }
    }
    public static void dimFill22(int deg){
        dimC = new Double[deg + 1][deg + 2];
        for (int i = 0; i < dimC[0].length - 1; i++) {
            dimC[i][0] = (a + b) / 2 + (b - a) / 2 * cos((2 * i + 1) * PI / (2 * (deg + 1)));
            dimC[i][1] = func2(dimC[i][0]);
        }
        for (int i = 2; i < dimC[0].length; i++) {
            for (int j = 0; j < dimC[0].length - i; j++) {
                dimC[j][i] = (dimC[j][i - 1] - dimC[j + 1][i - 1])/(dimC[j][0] - dimC[j + i - 1][0]);
            }
        }
    }

    public static void exception1(int deg){
            double max = 0, max2 = 0;
            for (int k = 0; k < 101; k++) {
                double func = func1((double)a + (double)(b - a)/100 * k);
                double pol = 0;
                for (int i = 1; i < deg + 2; i++) {
                    double temp = 1;
                    for (int j = 0; j < i - 1; j++) {
                        temp *= ((double)a + (double)(b - a)/100 * k - dim[j][0]);
                    }
                    temp *= dim[0][i];
                    pol += temp;
                }
                max = max(max, abs(pol - func)) == max ? max : abs(pol - func);
            }
            for (int k = 0; k < 101; k++) {
                double func = func1((double)a + (double)(b - a)/100 * k);
                double pol = 0;
                for (int i = 1; i < deg + 2; i++) {
                    double temp = 1;
                    for (int j = 0; j < i - 1; j++) {
                        temp *= ((double)a + (double)(b - a)/100 * k - dimC[j][0]);
                    }
                    temp *= dimC[0][i];
                    pol += temp;
                }
                max2 = max(max2, abs(pol - func)) == max2 ? max2 : abs(pol - func);
            }
        System.out.printf("%-7s", "Deg: " + deg);
        System.out.printf("%-35s", ". Max delta: " + max);
        System.out.printf("%-35s", ". Max delta Chebyshev: " + max2 + "\n");
    }
    public static void exception2(int deg){
        double max = 0, max2 = 0;
        for (int k = 0; k < 101; k++) {
            double func = func2((double)a + (double)(b - a)/100 * k);
            double pol = 0;
            for (int i = 1; i < deg + 2; i++) {
                double temp = 1;
                for (int j = 0; j < i - 1; j++) {
                    temp *= ((double)a + (double)(b - a)/100 * k - dim[j][0]);
                }
                temp *= dim[0][i];
                pol += temp;
            }
            max = max(max, abs(pol - func)) == max ? max : abs(pol - func);
        }
        for (int k = 0; k < 101; k++) {
            double func = func2((double)a + (double)(b - a)/100 * k);
            double pol = 0;
            for (int i = 1; i < deg + 2; i++) {
                double temp = 1;
                for (int j = 0; j < i - 1; j++) {
                    temp *= ((double)a + (double)(b - a)/100 * k - dimC[j][0]);
                }
                temp *= dimC[0][i];
                pol += temp;
            }
            max2 = max(max2, abs(pol - func)) == max2 ? max2 : abs(pol - func);
        }
        System.out.printf("%-7s", "Deg: " + deg);
        System.out.printf("%-35s", ". Max delta: " + max);
        System.out.printf("%-35s", ". Max delta Chebyshev: " + max2 + "\n");
    }

    public static void main(String[] args) {
        System.out.println("f(x) = sin2x * ln(x + 5)");
        for (int i = 5; i < 25; i+=5) {
            dimFill1(i);
            dimFill11(i);
            exception1(i);
        }
        System.out.println(" ");
        System.out.println("f(x) = sqrt(2 * |x| + x^2)");
        for (int i = 5; i < 25; i+=5) {
            dimFill2(i);
            dimFill22(i);
            exception2(i);
        }
    }
}
