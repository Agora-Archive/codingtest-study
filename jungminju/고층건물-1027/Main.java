import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] building;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        building = new int[N+1];
        int [] sight = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            building[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++){
            double standardSlope = findSlope(i, i+1);
            sight[i]++;
            sight[i+1]++;
            for(int j=i+2; j<=N; j++) {
               double newSlope =  findSlope(i, j);
               if(standardSlope < newSlope) {
                   standardSlope = newSlope;
                   sight[i]++;
                   sight[j]++;
               }
            }
        }

        int answer = 0;
        for(int i=1; i<=N; i++){
            answer = Math.max(answer, sight[i]);
        }
        System.out.println(answer);
    }

    static double findSlope(int j, int i) {
        return (double) (building[j] - building[i]) / (j - i);
    }
}