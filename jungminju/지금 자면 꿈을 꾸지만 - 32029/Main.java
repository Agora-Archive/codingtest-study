import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int A;
    static int B;
    static int [] tasks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        tasks = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) {
            tasks[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tasks);

        int answer = 0;
        for(int X = 0; X<A; X++) {
            for(int i=1; i<=N; i++) { //i-1 번째에 잠을 자고 과제를 하는 경우의 수 계산
                answer = Math.max(answer, findTasks(X, i, B*X));
            }
        }

        System.out.println(answer);
    }

    static int findTasks(int X, int start, int sleep) {
        int count = 0;
        int time=0;

        //안자고 한 과제 시간 더하기
        for(int i=1; i<start; i++) {
            if( time+A <= tasks[i] ) {
                time+=A;
                count++;
            }
        }

        //잔 시간 더하기
        time+=sleep;

        //잔 후에 과제 시간 더하기
        for(int i = start; i<=N; i++) {
            if(time+(A-X) <= tasks[i]) {
                time+=(A-X);
                count++;
            }
        }

       return count;
    }

}
