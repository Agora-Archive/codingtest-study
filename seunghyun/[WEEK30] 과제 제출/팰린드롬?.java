import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static boolean[][] dp;
    public static ArrayList<Integer> arr;

    public static boolean find(int s, int e) {
        if(s>e){
            return true;
        }
        if(dp[s][e]){
            return dp[s][e];
        }
        if (s == e) {
            dp[s][e] = true;
            return true;
        }
        if (!arr.get(s).equals(arr.get(e))) {
            dp[s][e] = false;
            return false;
        }
        dp[s][e] = find(s + 1, e - 1);
        return dp[s][e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>(N + 1);
        dp = new boolean[N + 1][N + 1];
        arr.add(-1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int result = find(s, e) ? 1 : 0;
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}
