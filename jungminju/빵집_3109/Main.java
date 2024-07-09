import java.io.*;
import java.util.*;

public class Main {
    static int [] dirR = {-1,0,1};
    static int [] dirC = {1,1,1};
    static boolean [][] pipe;
    static int pipeCount=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int R=Integer.parseInt(st.nextToken())+2;
        int C=Integer.parseInt(st.nextToken());

        pipe = new boolean[R][C];

        for(int i=1; i<R-1; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++){
                pipe[i][j] = s.charAt(j) == '.';
            }
        }

        for(int i=1; i<R-1; i++) {
            findRoute(i,0);
        }

        System.out.println(pipeCount);
    }

    public static boolean findRoute(int startX, int startY) {
        if (startY == pipe[0].length - 1) {
            pipeCount++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nextX = startX + dirR[i];
            int nextY = startY + dirC[i];

            if (pipe[nextX][nextY]) {
                pipe[nextX][nextY] = false;
                if (findRoute(nextX, nextY)) return true;
            }
        }
        return false;
    }

}