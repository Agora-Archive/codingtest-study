import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Provider;
import java.util.*;

public class Main {

    public static int V, E;
    public static Node[] graph;
    public static int[] parent;

    public static class Node{
        private final int a;
        private final int b;
        private final int c;

        public Node(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int find(int a){
        if(parent[a]!=a){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA!=rootB){
            parent[rootB] = rootA;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V];
        graph = new Node[E];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            Node input = new Node(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            graph[i]=input;
        }
        for(int i=0;i<V;i++){
            parent[i]=i;
        }

        Arrays.sort(graph, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c-o2.c;
            }
        });

        long result = 0L;
        for(int i=0;i<E;i++){
            Node temp = graph[i];
            int p1 = find(temp.a);
            int p2 = find(temp.b);
            if(p1==p2){
                continue;
            }
            result += temp.c;
            union(temp.a, temp.b);
        }
        System.out.println(result);
    }
}