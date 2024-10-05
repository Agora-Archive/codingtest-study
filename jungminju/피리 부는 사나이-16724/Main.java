import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int map[][];
    static int[] dirR = {0,0,-1,1};
    static int[] dirC = {1,-1,0,0}; //R, L, U, D
    static int answer=0;
    static boolean isCycle[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        isCycle = new boolean[R][C];

        for(int i=0; i<R; i++) {
           String string = br.readLine();
            for(int j=0; j<C; j++) {
                char c = string.charAt(j);
                if (c=='R') map[i][j] = 0;
                else if (c=='L') map[i][j]=1;
                else if(c=='U') map[i][j]=2;
                else map[i][j]=3;
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] !=-1) dfs(i, j);
            }
        }
        System.out.println(answer);

    }

    static void dfs(int r, int c) {
        int nextDir = map[r][c];
        int nextR = r+dirR[nextDir];
        int nextC = c+dirC[nextDir];

        map[r][c] = -1;

        if(map[nextR][nextC]==-1) {
            if(!isCycle[nextR][nextC]) answer++;
        }
        else dfs(nextR, nextC);

        isCycle[r][c] = true;
    }
}
