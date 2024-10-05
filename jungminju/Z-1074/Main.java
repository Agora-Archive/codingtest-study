import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int n, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        findInOrder(0, 0, n);
        System.out.println(answer);
    }

    static boolean findInOrder(int x, int y, int n) {
        if (n < 0) return false;
        if (x == r && y == c) return true;

        int nextSize = (int) Math.pow(2, n - 1);
        int half =  ((int) Math.pow(2, n)) / 2;
        int area = half*half;

        int range = getRange(x, y, nextSize);

        switch (range) {
            case 1: // 1번 위치
                if (findInOrder(x, y, n - 1)) return true;
                break;
            case 2: // 2번 위치
                answer+=area;
                if (findInOrder(x, y + nextSize, n - 1)) return true;
                break;
            case 3: // 3번 위치
                answer+=area*2;
                if (findInOrder(x + nextSize, y, n - 1)) return true;
                break;
            case 4: // 4번 위치
                answer+=area*3;
                if (findInOrder(x + nextSize, y + nextSize, n - 1)) return true;
                break;
        }
        return false;
    }

    static int getRange(int x, int y, int nextSize) {
        // 현재 좌표 (r, c)가 어느 사분면에 속하는지 판단
        if (r < x + nextSize && c < y + nextSize) {
            return 1; // 1사분면
        } else if (r < x + nextSize && c >= y + nextSize) {
            return 2; // 2사분면
        } else if (r >= x + nextSize && c < y + nextSize) {
            return 3; // 3사분면
        } else {
            return 4; // 4사분면
        }
    }
}
