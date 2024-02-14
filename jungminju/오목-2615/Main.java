import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] baduk = new int[21][21];
    static int directionInfo[][] = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    static int count;
    public static void main(String[] args) throws IOException {
        init();

        for(int i=1; i<20; i++) {
            for(int j=1; j<20; j++) {
                int color = baduk[i][j];
                if(color==0) continue;
                int k;
                for(k=0; k<4; k++) { //방향별로 dfs 탐색
                    count=1; //카운트 초기화
                    if(!dfs(i,j,k)||!dfs(i,j,k+4)) continue; //양방향 동시 검사
                    if(count==5)break;
                }
                if(count==5) {
                    if(k==3) print(i+4,j-4,color);
                    else print(i,j,color);
                    return;
                }
            }
        }
        System.out.println(0);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=1; i<20; i++){ //바둑배열 초기화
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<20; j++){
                baduk[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static boolean dfs(int row, int col, int direction){
        if(count>5) return false;

        int nextRow = row+directionInfo[direction][0];
        int nextCol = col+directionInfo[direction][1];
        int color = baduk[row][col];

        if(color==baduk[nextRow][nextCol]) {
            count++;
            dfs(nextRow, nextCol, direction);
        }
        return true;
    }

    public static void print(int i, int j, int color){
        System.out.println(color);
        System.out.print(i+" "+j);
    }
}
