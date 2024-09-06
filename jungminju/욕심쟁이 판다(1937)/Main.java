import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int input[][];
    static int dp[][];
    static int max=0;
    static int [] dirR = {-1,1,0,0}; //상, 하, 좌, 우
    static int [] dirC = {0,0,-1,1}; //상, 하, 좌, 우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, dp(i, j));
            }
        }

        System.out.println(max);
    }

    static int dp(int x, int y) {
        if(dp[x][y]!=0) return dp[x][y];

        dp[x][y] = 1;
        for(int i=0; i<4; i++) {
            int nx = x + dirR[i];
            int ny = y + dirC[i];

            if(nx==-1 || nx==N || ny==-1 || ny==N) continue; //배열 범위 조절
            if(input[x][y] < input[nx][ny]){
                dp[x][y] = Math.max(dp[x][y], dp(nx, ny) + 1);
            }
        }
        return dp[x][y];
    }
}

