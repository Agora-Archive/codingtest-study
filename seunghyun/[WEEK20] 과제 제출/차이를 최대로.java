import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int result=0,n;
    public static boolean[] visited;
    public static int[] temp;
    public static int[] input;

    public static void find(int cnt){
        if(cnt==n){ //모든 수 탐색했으면 식 계산
            int sum = 0;
            for(int i=0;i<n-1;i++){
                sum+=Math.abs(temp[i]-temp[i+1]);
            }
            result=Math.max(result,sum); //result 업데이트
            return;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                temp[cnt]=input[i];
                visited[i]=true;
                find(cnt+1);
                visited[i]=false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new int[n];
        temp = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++)
            input[i] = Integer.parseInt(st.nextToken());
        find(0);
        System.out.println(result);

    }
}

