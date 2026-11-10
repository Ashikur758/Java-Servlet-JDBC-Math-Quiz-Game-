package service;

public class MathService {

    public static final double TOLERANCE = 0.01;

    /** Returns 1 if prime, otherwise 0. Numbers < 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) return 0;
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    /** Remainder of a % b. Divisor zero -> validation error. */
    public static int calculateMod(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Divisor must not be zero");
        return a % b;
    }

    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    public static double calculateTan(double degree) {
        double rad = Math.toRadians(degree);
        if (Math.abs(Math.cos(rad)) < 1e-10)
            throw new ArithmeticException("Tangent is undefined for " + degree + " degrees");
        return Math.tan(rad);
    }

    public static double calculateTrig(String fn, double degree) {
        switch (fn) {
            case "sin": return calculateSin(degree);
            case "cos": return calculateCos(degree);
            case "tan": return calculateTan(degree);
            default: throw new IllegalArgumentException("Unknown function " + fn);
        }
    }

    public static boolean closeEnough(double expected, double actual) {
        return Math.abs(expected - actual) <= TOLERANCE;
    }

    // quick self-test
    public static void main(String[] args) {
        System.out.println(isPrime(7) + " " + isPrime(8) + " " + isPrime(1));          // 1 0 0
        System.out.println(calculateMod(17, 5) + " " + calculateMod(-17, 5));          // 2 -2
        System.out.println(calculateSin(30) + " " + calculateCos(60) + " " + calculateTan(45));
        try { calculateMod(5, 0); } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }
}
