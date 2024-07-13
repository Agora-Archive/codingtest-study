import java.io.*;
import java.util.*;

public class Main {
    static int cnt =0;
    static int [][] room;
    static boolean [][] visited;
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());
        StringTokenizer rc = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(rc.nextToken());
        int c = Integer.parseInt(rc.nextToken());
        int d = Integer.parseInt(rc.nextToken());//0북, 1동, 2남, 3서
        room = new int[N][M];
        visited = new boolean[N][M];//청소한 곳임
        for(int i =0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                room[i][j]=Integer.parseInt(input.nextToken());
            }
        }
        move(r, c, d);
        System.out.print(cnt);
    }
    static void move(int x, int y, int dir){
        if(room[x][y]==1){ //방문한 곳이거나 벽일 경우
            return;
        }
        if(!visited[x][y]){
            cnt++;
        }
        visited[x][y]=true;//1번 청소한다
        if(cantGo(x,y)){//2번 동서남북 4칸 중 0이 없을 경우
            int moveDir;
            if(dir >=2){//방향을 뒤집어줌
                moveDir=dir%2;
            }
            else{
                moveDir = dir+2;
            }
            //방향을 유지한 채로 뒤로 1보 이동
            move(x+dx[moveDir], y+dy[moveDir], dir);
        }
        else{
            for(int i =0; i<4; i++){
                dir = turn(dir);
                if(room[x+dx[dir]][y+dy[dir]]==0&&!visited[x+dx[dir]][y+dy[dir]]){
                    move(x+dx[dir],y+dy[dir], dir);
                    return;
                }
            }
        }
    }
    static int turn(int dir){
        return dir == 0 ? 3 : dir -1;
    }

    // 함수로 만들어주기
    static boolean cantGo(int x, int y){
        return (room[x+1][y]==1||visited[x+1][y])&&(room[x][y+1]==1||visited[x][y+1])&&(room[x-1][y]==1||visited[x-1][y])&&(room[x][y-1]==1||visited[x][y-1]);
    }
}
