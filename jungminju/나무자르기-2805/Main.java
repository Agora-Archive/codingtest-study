import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, max=0;
    public static void main(String[] args) throws IOException {
        int [] array =  init();
        int min=0;
        while (min<max){
            int mid = (min+max)/2;
            long sum=0;
            for(int target : array){
                if(target-mid>0) sum+=target-mid;
            }
            if (sum>=M) min=mid+1; //너무 많이 잘랐다는 뜻. 즉 하한선을 높여야함.
            else max = mid;// 너무 적게 잘랐다는 뜻. 상한선을 낮춰야 함.
        }
        System.out.println(min - 1);
    }

    public static int [] init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,array[i]);
        }
        return array;
    }

}