import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int T,n,k,cur,temp;

    public static char run(){
        cur = 0;
        temp = k;
        while(n>Math.abs(k)){
            cur+=k;
            n-=Math.abs(k);
            if(k<0){
                k=-(k-temp);
            }
            else
                k=-(k+temp);
        }
        if(k<0){
            cur-=n-1;
            return 'L';
        }
        else {
            cur += n-1;
            return 'R';
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            char result = run();
            sb.append(cur+" "+result+"\n");
        }
        System.out.println(sb);
    }
}