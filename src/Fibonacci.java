public class Fibonacci {
    // Dynamic Programming: bottom up
    // Run: O(n), space: O(n)
    public static long fibonacci(int n) {
        long[] result = new long[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }

        return result[n];
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci.fibonacci(4));
        System.out.println(Fibonacci.fibonacci(1000));
    }
}
