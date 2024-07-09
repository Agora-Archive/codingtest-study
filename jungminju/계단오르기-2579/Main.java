import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static Integer dp[];
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];	// 디폴트값이 null이므로 0으로 초기화 해주어야한다.
        dp[1] = arr[1];

        // N 이 1이 입력될 수도 있기 때문에 예외처리를 해줄 필요가 있다.
        if(N >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(find(N));

    }

    static int find(int N) {
        // 아직 탐색하지 않는 N번째 계단일 경우
        if(dp[N] == null) {
            System.out.println("dp가 null");
            dp[N] = Math.max(find(N - 2), find(N - 3) + find(N-1)) + arr[N];
            System.out.println("현재 dp"+N+"은 :" + dp[N]);
        }
        System.out.println("현재 dp"+N+"은 :" + dp[N]);
        return dp[N];
    }

}