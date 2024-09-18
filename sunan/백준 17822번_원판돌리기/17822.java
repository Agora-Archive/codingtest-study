import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int [][] circle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NMT = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NMT.nextToken());
        M = Integer.parseInt(NMT.nextToken());
        circle = new int[N][M];
        int T = Integer.parseInt(NMT.nextToken());
        for(int i=0; i<N; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                circle[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        while (T--!=0){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(input.nextToken());
            int dir = Integer.parseInt(input.nextToken());
            int time = Integer.parseInt(input.nextToken());
            while (time--!=0){
                turnCircle(x, dir);
            }
            doDelete();
        }
        System.out.print(getResult());
    }
    static void turnCircle(int x, int dir){
        if(dir == 0){//시계
            for(int i=x-1; i<N; i+=x){
                ArrayDeque <Integer> newArr = new ArrayDeque<>();
                for(int j=0; j<M-1; j++){
                    newArr.addLast(circle[i][j]);
                }
                newArr.addFirst(circle[i][M-1]);
                for(int j=0; j<M; j++){
                    circle[i][j] = newArr.pollFirst();
                }
            }
        }else{
            for(int i=x-1; i<N; i+=x){
                ArrayDeque <Integer> newArr = new ArrayDeque<>();
                for(int j=1; j<M; j++){
                    newArr.addLast(circle[i][j]);
                }
                newArr.addLast(circle[i][0]);
                for(int j=0; j<M; j++){
                    circle[i][j] = newArr.pollFirst();
                }
            }
        }
    }
    static boolean didDelete;
    static void doDelete(){
        didDelete = false;
        for(int i =0; i<N; i++){
            for(int j=0; j<M; j++){
                if(circle[i][j]>0){
                    findDelete(i, j, circle[i][j]);
                }
            }
        }
        if(!didDelete){
            calCircle();
        }
    }
    static int [] dx = {0, 0, 1, -1};
    static int [] dy = {1, -1, 0, 0};
    static void findDelete(int x, int y, int target){
        if(x<0|| y<0 || x>=N || y>=M){
            return;
        }
        for(int i=0; i<4; i++){
            int mx = dx[i] + x;
            int my = dy[i] + y;
            if(mx>=0 && my>=0 && mx<N && my<M && circle[mx][my]==target){
                didDelete = true;
                circle[x][y] = -1;
                circle[mx][my] =-1;
                findDelete(mx, my, target);
            }
            else if(mx>=0 && mx<N && my==M && circle[mx][0]==target){
                didDelete = true;
                circle[x][y] = -1;
                circle[mx][0] =-1;
                findDelete(mx, 0, target);
            }else if(mx>=0 && mx<N && my==-1 && circle[mx][M-1]==target){
                didDelete = true;
                circle[x][y] = -1;
                circle[mx][M-1] =-1;
                findDelete(mx, M-1, target);
            }
        }

    }
    static void calCircle(){
        float average =0;
        float cnt = 0;
        ArrayList<int []> saveXY = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(circle[i][j]>=1){
                    average+= circle[i][j];
                    saveXY.add(new int[]{i, j});
                    cnt++;
                }
            }
        }
        average/=cnt;
        for (int [] xy: saveXY){
            int x = xy[0];
            int y = xy[1];
            if(circle[x][y]>average){
                circle[x][y]--;
            }else if(circle[x][y]<average){
                circle[x][y]++;
            }
        }
    }
    static int getResult(){
        int result =0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                result+= Math.max(circle[i][j], 0);
            }
        }
        return result;
    }
}
