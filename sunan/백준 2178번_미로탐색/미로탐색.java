import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int [][] maze ;
    static int []dx ={-1, 1, 0, 0};//상, 하, 좌, 우

    static int []dy ={0, 0, -1, 1};//상, 하, 좌, 우
    static boolean [][] visited;
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        visited = new boolean[N][M];
        BFS(0, 0);
        System.out.print(maze[N-1][M-1]);
    }
    static void BFS(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        visited[x][y]=true;
        while(!q.isEmpty()){
            Node xy = q.poll();
            for(int i =3; i>=0; i--){
                int next_x = xy.x+dx[i];
                int next_y = xy.y+dy[i];
                if(next_x<N && next_x>=0 && next_y<M && next_y>=0){
                    if(!visited[next_x][next_y]&& maze[next_x][next_y]==1){
                        q.offer(new Node(next_x, next_y));
                        maze[next_x][next_y] = maze[xy.x][xy.y] +1;
                        visited[next_x][next_y] =true;
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st_NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st_NM.nextToken());
        M = Integer.parseInt(st_NM.nextToken());
        maze=  new int[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                maze[i][j] =Character.getNumericValue(input.charAt(j));
            }
        }
    }
}
