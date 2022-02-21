package Lab1;

import static java.lang.Math.*;

public class Lab1 {

    double eps = Math.pow(0.1, 7);
    double[] ab = new double[]{1.0, 2.0};

    public double[] separation(){
        double x = 0.0;
        String fa = "", fb = "";
        int iteration = 0;
        while(abs(ab[0] - ab[1]) >= 0.1){
            x = (ab[0] + ab[1])/2;
            if(pow(4, ab[0]) - 5*ab[0] - 2 > 0)
                fa = "+";
            else
                fa = "-";
            if(pow(4, ab[1]) - 5*ab[1] - 2 > 0)
                fb = "+";
            else
                fb = "-";
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-10s", " a: " + ab[0]);
            System.out.printf("%-20s", " (a + b)\\2: " + x);
            System.out.printf("%-10s", " b: " +  ab[1]);
            System.out.printf("%-20s", " |a - b|: " + abs(ab[0] - ab[1]));
            System.out.printf("%-10s", " f(a): " + fa );
            System.out.printf("%-20s", " f(b): " + fb);
            System.out.println();
            if((pow(4, x) - 5*x - 2)*(pow(4, ab[0]) - 5*ab[0] - 2) < 0)
                ab[1] = x;
            else
                ab[0] = x;
            iteration++;
        }
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-10s", " a: " + ab[0]);
        System.out.printf("%-20s", " (a + b)\\2: " + x);
        System.out.printf("%-10s", " b: " +  ab[1]);
        System.out.printf("%-20s", " |a - b|: " + abs(ab[0] - ab[1]));
        System.out.printf("%-10s", " f(a): " + fa );
        System.out.printf("%-20s", " f(b): " + fb);
        System.out.println();
        return ab;
    }

    public double simpIter(){
        double x = (ab[0] + ab[1])/2, xk = log(5*x + 2)/log(4), temp;
        int iteration = 0;
        do{
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-25s", " x(k): " + x);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
            System.out.println();
            temp = xk;
            xk = log(5*temp + 2)/log(4);
            x = temp;
            iteration++;
        }while (abs(x - xk) > eps);
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-25s", " x(k): " + x);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
        System.out.println();
        return xk;
    }

    public double chebThirdOrder(){
        double x = (ab[0] + ab[1])/2, xk = x - (pow(4, x) - 5*x - 2)/(log(4)*pow(4, x) - 5) - pow(log(4), 2)*pow(4, x)*pow(pow(4, x) - 5*x - 2, 2)/(2*pow((log(4)*pow(4, x) - 5), 3)), temp;
        int iteration = 0;
        do{
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-25s", " x(k): " + x);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
            System.out.println();
            temp = xk;
            xk = temp - (pow(4, temp) - 5*temp - 2)/(log(4)*pow(4, temp) - 5) - pow(log(4), 2)*pow(4, temp)*pow(pow(4, temp) - 5*temp - 2, 2)/(2*pow((log(4)*pow(4, temp) - 5), 3));
            x = temp;
            iteration++;
        }while (abs(x - xk) > eps);
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-25s", " x(k): " + x);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
        System.out.println();
        return xk;
    }

    public double newton(){
        double x = (ab[0] + ab[1])/2, xk = x - (pow(4, x) - 5*x - 2)/(log(4)*pow(4, x) - 5), temp;
        int iteration = 0;
        do{
            System.out.printf("%-15s", "iteration: " + iteration);
            System.out.printf("%-25s", " x(k): " + x);
            System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
            System.out.println();
            temp = xk;
            xk = temp - (pow(4, temp) - 5*temp - 2)/(log(4)*pow(4, temp) - 5);
            x = temp;
            iteration++;
        }while (abs(x - xk) > eps);
        System.out.printf("%-15s", "iteration: " + iteration);
        System.out.printf("%-25s", " x(k): " + x);
        System.out.printf("%-25s", " |x(k) - x(k+1)|: " + abs(x - xk));
        System.out.println();
        return xk;
    }

    public static void main(String[] args) {
        Lab1 lab = new Lab1();
        lab.ab = lab.separation();
        System.out.println(lab.ab[0] + " " + lab.ab[1]);
        System.out.println("Simple iteration result: " + lab.simpIter());
        System.out.println("Chebyshev result: " + lab.chebThirdOrder());
        System.out.println("Newton result: " + lab.newton());
    }
}
