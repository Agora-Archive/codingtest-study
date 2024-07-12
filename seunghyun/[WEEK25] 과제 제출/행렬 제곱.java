import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static long B;
    public static int[][] base;

    public static int[][] find(long num){
        if(num == 1){
            return base;
        }
        int[][] save = find(num/2);

        if(num%2==1){
            return calc(calc(save,save),base);
        }
        else{
            return calc(save,save);
        }
    }

    public static int[][] calc(int[][] arr1, int[][] arr2){
        int[][] result = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int temp = 0;
                for(int k=0;k<N;k++){
                    temp += arr1[i][k]*arr2[k][j];
                }
                result[i][j] = temp%1000;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        base = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                base[i][j] = Integer.parseInt(st.nextToken())%1000;
            }
        }
        int[][] result = find(B);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.printf(result[i][j]+" ");
            }
            System.out.println();
        }
    }
}