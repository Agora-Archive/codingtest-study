import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Point {
        private final int a, b;

        Point(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        int d = p1.a * p2.b + p2.a * p3.b + p3.a * p1.b;
        d -= p2.a * p1.b + p3.a * p2.b + p1.a * p3.b;

        if (d > 0) return 1;
        else if (d < 0) return -1;
        else return 0;
    }

    public static boolean isPossible(int d1, int d2) {
        return d1 * d2 < 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point[] arr = new Point[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        if (isPossible(ccw(arr[0], arr[1], arr[2]), ccw(arr[0], arr[1], arr[3]))) {
            if (isPossible(ccw(arr[2], arr[3], arr[0]), ccw(arr[2], arr[3], arr[1]))) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}