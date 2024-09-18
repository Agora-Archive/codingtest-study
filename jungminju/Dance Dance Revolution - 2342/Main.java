import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int endIdx = 0;
    public static int[] arr;
    public static int[][][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100001];

        for(int i=0; ;i++) {
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 0) {
                endIdx = i;
                break;
            }else {

                arr[i] = temp;
            }
        }
        dp = new int[endIdx + 1][5][5];

        System.out.println(simulate(0, 0, 0));
    }

    public static int simulate(int level, int lf, int rf) {
        if(dp[level][lf][rf] > 0) return dp[level][lf][rf];
        if(endIdx == level) {
            return 0;
        }

        //재귀 호출로 다음 스텝 계산
        int temp1 = calculate(lf, arr[level]) + simulate(level + 1, arr[level], rf);
        int temp2 = calculate(rf, arr[level]) + simulate(level + 1, lf, arr[level]);

        return dp[level][lf][rf] = Math.min(temp1, temp2);
    }

    public static int calculate(int start, int end) {
        int distance = Math.abs(start - end);
        if(start == 0) return 2; //시작점
        else if(distance == 0) return 1; //같은 위치
        else if(distance == 1 || distance == 3) return 3; //인접
        else return 4; //반대편
    }

}