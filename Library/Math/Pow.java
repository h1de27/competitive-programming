public class Pow {
    static long modPow(long a, long n, long mod) {
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(modPow(3, 45, 1000000007));
    }
}
