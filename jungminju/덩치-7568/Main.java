import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    static int []array;
    static int []answer;
    static int N;
    public static void main(String[] args) throws IOException {
        init();
        int [] last = new int[2]; //여기도 0.0 세팅
        answer[0]= last[1] = array[0]; //초기값 세팅
        for(int i=1;i<array.length; i++){
            System.out.println(i+"번째 반복 중 answer ");
            binarySearch(array[i], last, i);
            showAnswer(i);
        }
        System.out.println(array.length);
    }

    public static void showAnswer(int i){
        for(int answer : answer){
            System.out.print(answer+" ");
        }
        System.out.println();
    }
    public static void binarySearch(int target, int [] last, int nowIndex){
        System.out.println("target = " + target);
        System.out.println("last = " + last[1]);
        if(target>last[1]) {
            last[1] = answer[last[0]+1] = target;
            last[0] = last[0]+1;
        }
        if(target<last[1]) {
            if(nowIndex) return;
            last[1] = answer[0] = target;
            last[0] = 0;
        }
        System.out.println("last = "+"<"+last[0]+","+last[1]+">");
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        array = new int[N];
        answer = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
           array[i] = Integer.parseInt(st.nextToken());
        }
    }

}