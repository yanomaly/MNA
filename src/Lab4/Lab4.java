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
    public static void dimFill2(int deg){
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

    public static void polynom(){

    }

    public static void exception(){
        for (int i = 0; i < 101; i++) {

        }
    }

    public static void main(String[] args) {
        dimFill2(4);
        System.out.println(1);
    }
}
