import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();

        int[][] A = new int[target.length()+1][26];

        for(int i=1; i<target.length()+1; i++){
            int value = target.charAt(i-1) - 'a';
            for(int j=0; j<A[i].length; j++){
                A[i][j] = A[i-1][j] + (value == j ? 1 : 0);
            }
        }

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine() , " ");
            int value = st.nextToken().charAt(0) - 'a';
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(A[b+1][value] - A[a][value]).append("\n");
        }
        System.out.println(sb);

    }
}