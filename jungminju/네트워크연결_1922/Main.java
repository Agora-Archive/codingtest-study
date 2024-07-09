import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Vertex implements Comparable<Vertex> {
        int from;
        int to;
        int cost;

        public Vertex(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Vertex o) {
            return this.cost-o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int vertexCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        int []parent = new int[vertexCount+1];

        for(int i=1; i<=vertexCount; i++){
            parent[i] = i; //본인으로 부모 노드 초기화
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        for(int i=0; i<edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Vertex(from, to, cost));
        }

        edgeCount=0;
        int answerCost=0;

        while (edgeCount+1!=vertexCount) { // vertexCount = edge+1;
            Vertex now = pq.poll(); // 1 0
            if(parent[now.to]!=parent[now.from]) { //find 함수가 다르다.
                int min = Math.min(parent[now.to], parent[now.from]);
                parent[now.to] = min;
                parent[now.from] = min;
                edgeCount++;
                answerCost+=now.cost;
            }
        }
        System.out.println(answerCost);
    }

    //find 함수와 유니온 함수.
}