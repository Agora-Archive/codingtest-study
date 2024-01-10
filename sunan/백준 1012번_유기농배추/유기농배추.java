import java.io.*;

public class Main {
    static int [][]arr;
    static int m,n,bae;
    static int cnt =0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            String[] str= br.readLine().split(" ");
            m=Integer.parseInt(str[0]);
            n=Integer.parseInt(str[1]);
            bae=Integer.parseInt(str[2]);
            arr=new int[m][n];
            int result =0;


            for(int j=0; j<bae; j++){
                String[] loc= br.readLine().split(" ");
                arr[Integer.parseInt(loc[0])][Integer.parseInt(loc[1])]++;//배추 심기
            }
            for(int j=0; j<m; j++){
                for(int k=0; k<n; k++){
                    DFS(j, k);
                    if(cnt>0){
                        result++;
                    }
                    cnt=0;
                }
            }
            sb.append(result+"\n");
        }
        System.out.print(sb);
    }
    public static void DFS(int a, int b){
        if(a<m &&b<n){
            if(arr[a][b]>0){
                arr[a][b]=0;
                cnt++;
                if(a!=m-1){
                    DFS(a+1, b);
                }
                if(b!=n-1){
                    DFS(a, b+1);
                }
                if(a!=0){
                    DFS(a-1, b);
                }
                if(b!=0){
                    DFS(a, b-1);
                }
            }
        }
    }
}
