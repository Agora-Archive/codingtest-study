import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static int n,d,ans;
    public static int[][] input;

    public static void find(int sum,int road){
        if(road==d){
            ans = Math.min(sum,ans);
        }
        if(road>=d)
            return;
        for(int i=0;i<n;i++){
            find(sum+d-road,d);
            if(input[i][0]>=road){
                find(sum+input[i][2]+input[i][0]-road,input[i][1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        input=new int[n][3];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            input[i][0]=Integer.parseInt(st.nextToken());
            input[i][1]=Integer.parseInt(st.nextToken());
            input[i][2]=Integer.parseInt(st.nextToken());
        }
        ans=d;
        for(int i=0;i<n;i++){
            if(input[i][0]<=d)
                find(input[i][0],input[i][0]);
        }
        System.out.println(ans);
    }
}

