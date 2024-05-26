public class Main {
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] road = new int[n+1][n+1];
        ArrayList<Integer>[][] path = new ArrayList[n+1][n+1];
        
        for(int i =1; i<=n; i++) // 경로 배열 초기화
		        for(int j=1; j<=n; j++) 
				        path[i][j] = new ArrayList<>(1); 

        for(int i =1; i<=n; i++) // 최단 거리 초기화(INF로)
		        for(int j =1; j<=n; j++) 
				        if(i!=j) road[i][j] = INF;

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(road[i][j] > cost){ // 제일 적은 비용으로 갱신
                road[i][j] = cost;
                path[i][j].clear();
                path[i][j].add(i);
                path[i][j].add(j);
            }
        }


        for(int k = 1; k<=n; k++){ // 거쳐갈 곳
            for(int i = 1; i<=n; i++){ // 출발지
                for(int j = 1; j<=n; j++){ // 목적지
                    if(road[i][j] > road[i][k] + road[k][j]){ // 최단거리, 경로 갱신
                        road[i][j] = road[i][k] + road[k][j];
                        path[i][j].clear();
                        path[i][j].addAll(path[i][k]);
                        path[i][j].remove(path[i][j].size() - 1);
                        path[i][j].addAll(path[k][j]);
                    }
                }
            }
        }


        for(int i = 1; i<=n; i++){ // 모든 도시 -> 모든 도시의 최단 비용 출력
            for(int j = 1; j<=n; j++){
                if(road[i][j] == INF) System.out.println("0 ");
                else System.out.print(road[i][j] + " ");
            }
            System.out.println();
        }
        
        for(int i = 1; i<=n; i++){ // 모든 도시 -> 모든 도시의 최단 경로 출력
            for(int j = 1; j<=n; j++){
                System.out.print(path[i][j].size());
                for(Integer a : path[i][j]) System.out.print(" " + a);
                System.out.println();
            }
        }
  }
}