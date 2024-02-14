import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static LinkedList<Integer> []list;
    static boolean []visited;
    static int n, v;
    static StringBuilder sb = new StringBuilder();//출력해 줄 버퍼
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String []str= br.readLine().split(" ");
        n = Integer.parseInt(str[0]);//정점의 개수
        int m = Integer.parseInt(str[1]);//간선의 개수
        v = Integer.parseInt(str[2]);//시작할 V의 번호
        visited = new boolean[n+1];


        list = new LinkedList[n+1];
        for(int i=1; i<=n; i++){//연결 리스트에 정점 초기화
            list[i]= new LinkedList();
        }
        while((m--)!=0){// 입력을 받아 간선 연결
            String []input= br.readLine().split(" ");
            int v1 =Integer.parseInt(input[0]);
            int v2 =Integer.parseInt(input[1]);
            list[v1].add(v2);
            list[v2].add(v1);
        }
        for(int i=1; i<=n; i++){//연결 리스트에 정점 초기화
            Collections.sort(list[i]);
        }
        DFS(v);
        sb.append("\n");//줄 구분
        visited = new boolean[n+1];//배열 초기화
        BFS(v);
        System.out.print(sb);
    }
    static void DFS(int start){
        visited[start]= true;
        sb.append(start+" ");
        Iterator<Integer> it = list[start].listIterator();
        while(it.hasNext()){
            int n = it.next();
            if(!visited[n]){
                DFS(n);
            }
        }
    }
    static void BFS(int start){
        Queue<Integer> q = new LinkedList<>();
        visited[start]= true;
        q.add(start);
        while(q.size()!=0){
            start=q.poll();
            sb.append(start+" ");
            Iterator<Integer> it1 = list[start].listIterator();
            while(it1.hasNext()){
                int n = it1.next();
                if(!visited[n]){
                    visited[n]= true;
                    q.add(n);
                }
            }
        }
    }
}
