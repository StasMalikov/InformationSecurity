public class MyParams {

    private double M = Math.pow(2, 32);
    private double A = 1664525;
    private double C = 1013904223;
    private double U0 = 2451;

    public MyParams(double m, long a, long c, int u0) {
        M = m;
        A = a;
        C = c;
        U0 = u0;
    }

    public MyParams() {}

    public double getM() {
        return M;
    }

    public double getA() {
        return A;
    }

    public double getC() {
        return C;
    }

    public double getU0() {
        return U0;
    }
}
