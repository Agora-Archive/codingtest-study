import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                int target = Integer.parseInt(st.nextToken());

                if (target < min) min = target;
                if (target > max) max = target;
            }
            System.out.println(min+" "+max);
        }
    }
}






