import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int departure;
    static int destination;
    static int route[];
    static ArrayList<Node>[] arr;

    static class Node implements Comparable<Node>{
        int to; int cost;
        public Node(int to, int cost){
            this.to=to; this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        findRoute(departure);
//        for (int i =0; i<N+1; i++){
//                System.out.print(route[i] + " ");
//
//        }
        System.out.println(route[destination]);
    }

    public static void findRoute(int node) {
        route[departure] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(node,0));

        while(!pq.isEmpty()){
            Node temp = pq.poll();
            int cur_node = temp.to;
            int cur_cost = temp.cost;

            if(cur_cost > route[cur_node]) continue;

            for(int i = 0; i<arr[cur_node].size(); i++){
                int next_node = arr[cur_node].get(i).to;
                int next_cost = arr[cur_node].get(i).cost;
                if(next_cost + cur_cost < route[next_node]){
                    route[next_node] = next_cost + cur_cost;
                    pq.add(new Node(next_node,route[next_node]));
                }
            }
        }



    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new ArrayList[N+1];
        for(int i = 0; i<N+1; i++){
            arr[i] = new ArrayList<>();
        }

        route = new int[N+1];
        Arrays.fill(route,10000001);

        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            arr[to].add(new Node(from,point));
        }

        st = new StringTokenizer(br.readLine());

        departure = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

    }
}