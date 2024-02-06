import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] triangle, answer;

        int T = Integer.parseInt(br.readLine());
        triangle = new int[T][];
        answer = new int[T][];

        for(int i=0; i<T; i++){
            int rowSize = i+1;
            triangle[i] = new int[rowSize];
            answer[i] = new int[rowSize];
            st = new StringTokenizer(br.readLine()," ");

            for(int j=0; j<rowSize; j++){
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer[0][0] = triangle[0][0];
        for(int i=0; i< answer.length-1; i++){
            for(int j=0; j<answer[i].length; j++){
                for(int k=j; k<=j+1; k++){
                    answer[i+1][k] = Math.max(answer[i+1][k], answer[i][j]+triangle[i+1][k]);
                }
            }
        }
        int max = 0;
        for(int value : answer[answer.length-1]){
            max = Math.max(max, value);
        }
        System.out.println(max);
    }
}

