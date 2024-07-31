import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int[] p;
    static List<Integer> road;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        StringTokenizer m3 = new StringTokenizer(br.readLine());
        int realStart = Integer.parseInt(m3.nextToken());
        int realEnd = Integer.parseInt(m3.nextToken());

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        q.offer(new Node(realStart, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        p = new int[n + 1];
        dist[realStart] = 0;
        road = new ArrayList<>();

        while (!q.isEmpty()) {
            Node current = q.poll();
            if (current.cost > dist[current.idx]) {
                continue;
            }
            for (Node next : graph.get(current.idx)) {
                if (next.cost + current.cost < dist[next.idx]) {
                    p[next.idx] = current.idx;
                    dist[next.idx] = next.cost + current.cost;
                    q.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        findP(realEnd);
        road.add(realEnd);

        System.out.println(dist[realEnd]);
        System.out.println(road.size());
        for (int node : road) {
            System.out.print(node + " ");
        }
    }

    static void findP(int e) {
        if (p[e] != 0) {
            findP(p[e]);
            road.add(p[e]);
        }
    }
}
