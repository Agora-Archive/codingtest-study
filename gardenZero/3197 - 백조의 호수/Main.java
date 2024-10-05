import java.io.*;
import java.util.*;

class Node {
    int swanNum;
    int x, y;

    public Node(int swanNum, int x, int y) {
        this.swanNum = swanNum;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            Node temp = (Node)obj;
            return swanNum == temp.swanNum && x == temp.x && y == temp.y;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return (Integer.toString(swanNum) + Integer.toString(x) + Integer.toString(y)).hashCode();
    }
}

public class Main{
    static int r, c;
    static char[][] arr;
    static int[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Node> bq = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[r][c];
        visited = new int[r][c][2];

        String input;
        int swanNum = 1;
        for(int i=0; i<r; i++){
            input = br.readLine();
            for(int j=0; j<c; j++){
                arr[i][j] = input.charAt(j);
                if(arr[i][j] == 'L') {
                    bq.add(new Node(swanNum, i, j));
                    water.add(new Node(-1, i, j));
                    visited[i][j][0] = swanNum++;
                    visited[i][j][1] = -1;
                }
                else if(arr[i][j] == '.') {
                    water.add(new Node(-1, i, j));
                    visited[i][j][1] = -1;
                }
            }
        }

        int result = 0;
        while(!check()) {
            melt();
            result++;
        }

        System.out.println(result);

    }

    static boolean check() {

        Set<Node> lastPos = new HashSet<>();

        while (!bq.isEmpty()) {
            Node cn = bq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cn.x + dx[i], ny = cn.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny][0] == cn.swanNum) continue;
                //0이 아닌 애를 만난거면 다른 백조다!
                if (visited[nx][ny][0] != 0) return true;

                //만약 0을 만난거라면 벽인지 아닌지 구분
                //벽이라면 벽과 맞다은 부분이라고 현재 위치를 lastPos에 저장
                if (arr[nx][ny] == 'X') {
                    lastPos.add(new Node(cn.swanNum, cn.x, cn.y));
                }
                //벽이 아니라면 방문처리하고 큐에 다시 넣어
                else {
                    visited[nx][ny][0] = cn.swanNum;
                    bq.add(new Node(cn.swanNum, nx, ny));
                }
            }
        }

        bq.addAll(lastPos);
        return false;
    }

    static void melt() {

        Set<Node> lastPos = new HashSet<>();

        while (!water.isEmpty()) {
            Node cn = water.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cn.x + dx[i], ny = cn.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny][1] == cn.swanNum) continue;

                if (arr[nx][ny] == 'X') {
                    lastPos.add(new Node(cn.swanNum, nx, ny));
                    arr[nx][ny] = '.';
                } else {
                    water.add(new Node(cn.swanNum, nx, ny));
                }
                visited[nx][ny][1] = cn.swanNum;
            }
        }

        water.addAll(lastPos);
    }
}