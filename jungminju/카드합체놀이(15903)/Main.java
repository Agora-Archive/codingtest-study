import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static  PriorityQueue<Long> priorityQueue;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException  {
        priorityQueue = new PriorityQueue<>();
        init();

        while (m>0) {
            long a = priorityQueue.poll();
            long b = priorityQueue.poll();

            long result = a+b;
            priorityQueue.add(result);
            priorityQueue.add(result);
            m--;
        }
        long answer = 0;

        while (!priorityQueue.isEmpty()){
            answer+=priorityQueue.poll();
        }

        System.out.println(answer);
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<n; i++) {
            priorityQueue.add((long) Integer.parseInt(st.nextToken()));
        }
    }
}