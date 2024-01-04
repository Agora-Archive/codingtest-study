import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int [][] arr;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = 4*(n-1)+1;
        arr = new int[k][k];
        for(int i =0; i<k; i++){
            for(int j=0; j<k; j++){
                arr[i][j]=0;
            }
        }
        int start =0, end =k-1;
        int new_k = k;
        int cnt_hol=0, cnt_jjak=0;
        for(int i=0; i<=k/2; i++) {
            if(i%2==0) {//짝수일 때
                for (int j = i; j < new_k; j++) {
                    arr[i][j]++;
                    arr[k-1-i][j]++;
                }
                for(int j=0; j<=cnt_jjak; j++){
                    if(start<=end){
                        arr[i][start]++;
                        arr[i][end]++;
                        arr[k-i-1][start]++;
                        arr[k-i-1][end]++;
                        start+=2;
                        end-=2;
                    }
                }
                new_k-=2;
                cnt_jjak++;
            }
            else{
                for(int p=0; p<=cnt_hol; p++){

                    if(start<=end&&i>0){
                        arr[i][start]++;
                        arr[i][end]++;
                        arr[k-i-1][start]++;
                        arr[k-i-1][end]++;
                        start+=2;
                        end-=2;
                    }
                }
                cnt_hol++;
            }
            start =0;
            end = k-1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<k; i++) {
            for(int j=0; j<k; j++) {
                if(arr[i][j]>0) {
                    sb.append("*");
                }
                else{
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
