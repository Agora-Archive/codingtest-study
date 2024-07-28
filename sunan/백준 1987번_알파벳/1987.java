//메모리 자린고비 코드
import java.io.*;
import java.util.*;

class Main {
    static char[][] board;
    static int max = -1, rows, cols;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        board = new char[rows + 2][cols + 2];
        
        for (int i = 1; i <= rows; i++) {
            String input = br.readLine();
            for (int j = 1; j <= cols; j++) {
                board[i][j] = input.charAt(j - 1);
            }
        }
        
        boolean[] visited = new boolean[26]; // 기존에 낭비되던 빈 배열의 개수가 줄어듦
        move(1, 1, visited, 0);
        System.out.print(max);
    }

    static void move(int x, int y, boolean[] visited, int count) {
        if (x < 1 || y < 1 || x > rows || y > cols || visited[board[x][y] - 'A']) {
            max = Math.max(max, count);
            return;
        }
        
        visited[board[x][y] - 'A'] = true; //방문처리하고
        move(x - 1, y, visited, count + 1);
        move(x + 1, y, visited, count + 1);
        move(x, y - 1, visited, count + 1);
        move(x, y + 1, visited, count + 1);
        visited[board[x][y] - 'A'] = false; // 해당 방문표시 초기화
    }
}
