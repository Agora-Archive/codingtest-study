import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer> [] list;
    static boolean visited[];
    static int cnt =0;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //노드 개수
        int m = Integer.parseInt(br.readLine());//간선 개수
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
        //정확한 탐색을 위한 정렬
        for(int i=1; i<=n; i++){
            Collections.sort(list[i]);
        }
        DFS(1);
        System.out.print(cnt-1);//1번 컴퓨터는 빼야
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
