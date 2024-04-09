import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char []arr;
    static boolean []visited;
    static int L, C;
    public static void main(String[] args) throws IOException {
        init();
        soonyeol(0, C, 0);
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
    // a c i t s w
    public static void soonyeol(int start, int end, int cnt){
        if(cnt==L){
            StringBuilder temp = new StringBuilder();
            int aeiou =0; int rest = 0;
            for(int i=0; i<C; i++){
                if(visited[i]){
                    if(arr[i]=='a'||arr[i]=='e'||arr[i]=='i'||arr[i]=='o'||arr[i]=='u'){
                       aeiou++;
                    }
                    else{
                        rest++;
                    }
                    temp.append(arr[i]);
                }
            }
            if(aeiou>0 && rest>1){
                sb.append(temp+"\n");
            }
            return;
        }
        for(int i=start; i<end; i++){
            visited[i]=true;
            soonyeol(i+1, end, cnt+1);
            //돌고나면 초기화
            visited[i]=false;
        }
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        visited= new boolean[C];
        StringTokenizer input = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            arr[i] = input.nextToken().charAt(0);
        }
        Arrays.sort(arr);
    }
}
