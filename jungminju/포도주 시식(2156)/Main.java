import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static Integer[] dp;
    static int[] grapes;
    public static void main(String[] args) throws IOException {
       int N = init();
       System.out.println(dp(N));
    }

    public static int dp(int N){
        if(dp[N]==null) {
            dp[N] = Math.max(Math.max(dp(N - 2), dp(N - 3) + grapes[N - 1]) + grapes[N], dp(N - 1));
        }
        return dp[N];
    }

    public static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        grapes = new int[N+1];
        dp = new Integer[N+1];

        for(int i=1; i<=N; i++){
            grapes[i]=Integer.parseInt(br.readLine());
        }
        dp[0] = 0;
        dp[1]=grapes[1];

        if(N>=2){
            dp[2] = grapes[1]+grapes[2];
        }

        return N;

    }

}