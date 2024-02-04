import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int []dp ;
    static int []score;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        score = new int[n+1];
        for(int i=1; i<=n; i++)
            score[i]= Integer.parseInt(br.readLine());
        dp = new int[n+1];
        System.out.print(find(n));
    }
    static int find(int n){
        if(n < 1) return 0;
        if(dp[n]==0)
            dp[n] = Math.max(find(n-2), find(n-3)+score[n-1])+score[n];
        return dp[n];
    }
}
