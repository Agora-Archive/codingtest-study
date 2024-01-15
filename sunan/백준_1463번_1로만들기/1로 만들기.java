import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

import static java.lang.System.exit;
public class Main {

    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] dp = new int[n+1];// 각 index에 1로 만드는데 필요한 연산수 적용
        dp[0]=dp[1]=0;
        for(int i=2; i<n+1; i++){
            dp[i]=dp[i-1]+1;//1을 빼주는 경우
            if(i%2==0){
                dp[i]=Math.min(dp[i], dp[i/2]+1);//2로 나눴을 때의+1이 2로 나누는 경우
            }
            if(i%3==0){
                dp[i]=Math.min(dp[i], dp[i/3]+1);//2로 나눴을 때의+1이 2로 나누는 경우
            }
        }
        System.out.print(dp[n]);
    }
}
