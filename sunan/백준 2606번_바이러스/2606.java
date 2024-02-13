import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<Integer> [] list;
    static boolean visited[];
    static int cnt =0;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //노드 개수
        //간선 개수
        int m = Integer.parseInt(br.readLine());
        //전역변수 초기화
        list = new LinkedList[n+1];
        visited = new boolean[n+1];
        for(int i =1; i<n+1; i++){
            list[i] = new LinkedList();
        }
        while(m--!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            list[front].add(back);
            list[back].add(front);
        }
        DFS(1);
        System.out.print(cnt-1);
    }
    static void DFS(int v){
        cnt++;
        visited[v]=true;
        Iterator<Integer> it = list[v].listIterator();
        while(it.hasNext()){
            int n = it.next();
            if(!visited[n]){
                DFS(n);
            }
        }
    }
}
