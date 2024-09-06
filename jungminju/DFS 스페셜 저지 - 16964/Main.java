import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static boolean [] visited;
    static List<Integer> [] list;
    static int [] target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = V-1; //spanning tree
        visited = new boolean[V+1];
        target = new int[V+1];
        list = new List[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<V; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
        target[V] = -1;

        boolean check = false;

        if(target[0]!=1) {
            System.out.println(0);
            return;
        }

        for(int i=0; i<E; i++) {
          check = dfs(target[i], target[i+1]);
          if(!check) break;
        }

        if(!check) System.out.println(0);
        else System.out.println(1);
    }


    static boolean dfs(int now, int next) {
        if (next == -1) return true;

        for (int neighbor : list[now]) {
            if (neighbor == next || (list[now].size() == 1 && visited[neighbor])) {
                visited[now] = true;
                return true;
            }
        }
        return false;
    }

}