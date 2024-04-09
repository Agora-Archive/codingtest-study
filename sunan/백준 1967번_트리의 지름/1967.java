import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx, distance;
        Node(int idx, int distance){
            this.idx = idx;
            this.distance = distance;
        }
    }
    static boolean []visited;
    static int max =-1;
    static ArrayList<ArrayList<Node>>tree = new ArrayList<ArrayList<Node>>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i =1; i<=n+1; i++){
            tree.add(new ArrayList<Node>());
        }
        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree.get(start).add(new Node(end, cost));
            tree.get(end).add(new Node(start, cost));
        }

        for(int i=1; i<=n; i++){
            visited = new boolean[n+1];
            visited[i]= true;// dfs에서 방문안한 애들만 순회하기 위해 미리 true처리
            dfs(i, 0);
        }
        System.out.print(max);
    }
    static void dfs(int index, int cost){
        for(int i=0; i< tree.get(index).size(); i++){
            int idx_DFS =tree.get(index).get(i).idx;
            if(!visited[idx_DFS]){
                visited[idx_DFS]=true;
                dfs(idx_DFS, tree.get(index).get(i).distance+ cost);
            }
        }
        max = Math.max(max, cost);
    }
}
