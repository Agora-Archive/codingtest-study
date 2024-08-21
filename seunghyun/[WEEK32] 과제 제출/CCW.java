import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Point {
        private final int a;
        private final int b;

        Point(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static Point[] arr =new Point[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        arr[3] = arr[0];
        int d = 0;
        for (int i = 0; i < 3; i++) {
            d += arr[i].a * arr[i + 1].b;
        }
        for (int i = 1; i <= 3; i++) {
            d -= arr[i].a * arr[i - 1].b;
        }

        if (d < 0) {
            System.out.println(-1);
        } else if (d > 0) {
            System.out.println(1);
        } else {
            System.out.println(0);

        }
    }
}