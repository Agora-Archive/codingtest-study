import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] time;
    static int[] minTime;
    static List<Integer>[] buildingOrder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        minTime = new int[N+1];
        buildingOrder = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            buildingOrder[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int order = Integer.parseInt(st.nextToken());

            while (order != -1){
                buildingOrder[i].add(order);
                order = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            System.out.println(findLeastTime(i));
        }
    }

    static int findLeastTime(int num) {

        if (visited[num]) {
            return minTime[num];
        }

        // 선행 건물들 중 가장 오래 걸리는 시간을 저장하기 위한 변수
        int maxTime = 0;

        // 선행 건물이 있을 경우, 선행 건물들 중 가장 오래 걸리는 시간을 찾는다.
        for(int pre : buildingOrder[num]){
            maxTime = Math.max(maxTime, findLeastTime(pre));
        }

        // 현재 건물의 최소 시간 = 선행 건물들의 최소 시간 중 최대값 + 현재 건물 건설 시간
        minTime[num] = maxTime + time[num];
        visited[num] = true;  // 방문 체크

        return minTime[num];
    }
}
