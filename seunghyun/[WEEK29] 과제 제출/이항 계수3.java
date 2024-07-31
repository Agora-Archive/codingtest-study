import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static long DIV = 1_000_000_007;

    public static long pow(long a, long b) {
        if (b == 1) {
            return a % DIV;
        }
        long temp = pow(a, b / 2);
        if (b % 2 == 0) {
            return temp * temp % DIV;
        }
        return (temp * temp % DIV) * a % DIV;
    }

    public static long fact(int a) {
        long result = 1L;
        while (a > 1) {
            result = (result * a) % DIV;
            a -= 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long up = fact(N) % DIV;
        long down = fact(K) * fact(N - K) % DIV;

        System.out.println(up * pow(down, DIV - 2) % DIV);
    }
}