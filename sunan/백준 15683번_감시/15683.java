import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<cctv> cctvList = new ArrayList<>();
    static PriorityQueue<Integer> result = new PriorityQueue<>();
    static int[] dir_1 = {0, 1, 2, 3}; // 동남서북
    static int[][] dir_2 = {{0, 2}, {1, 3}}; // 동서, 남북
    static int[][] dir_3 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}}; // 두 방향
    static int[] dir_4 = {0, 1, 2, 3}; // 동남서북 중 한 개 빼고 나머지
    static int[] dir_5 = {0, 1, 2, 3}; // 전부
    static int N, M;

    static class cctv {
        int x, y, type;

        public cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(input.nextToken());
                arr[i][j] = num;
                if (num > 0 && num < 6) {
                    cctvList.add(new cctv(i, j, num));
                }
            }
        }

        turnCctv(0, deepCopy(arr)); // 깊은 복사 적용
        System.out.println(result.poll());
    }

    // 이거 없어서 제대로 안나왔었음
    static int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }

    static void turnCctv(int len, int[][] map) {
        if (len == cctvList.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            result.add(cnt);
            return;
        }

        cctv currentCctv = cctvList.get(len);
        switch (currentCctv.type) {
            case 1:
                for (int x : dir_1) {
                    turnCctv(len + 1, goStraight(currentCctv.x, currentCctv.y, x, deepCopy(map)));
                }
                break;
            case 2:
                for (int[] x : dir_2) {
                    int[][] temp = deepCopy(map);
                    for (int y : x) {
                        temp = goStraight(currentCctv.x, currentCctv.y, y, temp);
                    }
                    turnCctv(len + 1, temp);
                }
                break;
            case 3:
                for (int[] x : dir_3) {
                    int[][] temp = deepCopy(map);
                    for (int y : x) {
                        temp = goStraight(currentCctv.x, currentCctv.y, y, temp);
                    }
                    turnCctv(len + 1, temp);
                }
                break;
            case 4:
                for (int x : dir_4) {
                    int[][] temp = deepCopy(map);
                    for (int i = 0; i < 4; i++) {
                        if (i != x) {
                            temp = goStraight(currentCctv.x, currentCctv.y, i, temp);
                        }
                    }
                    turnCctv(len + 1, temp);
                }
                break;
            case 5:
                int[][] temp = deepCopy(map);
                for (int x : dir_5) {
                    temp = goStraight(currentCctv.x, currentCctv.y, x, temp);
                }
                turnCctv(len + 1, temp);
                break;
        }
    }

    static int[][] goStraight(int x, int y, int dir, int[][] map) {
        if (map[x][y] == 0) {
            map[x][y] = -1; 
        }

        switch (dir) {
            case 0: 
                if (y + 1 < M && map[x][y + 1] != 6) {
                    return goStraight(x, y + 1, dir, map);
                }
                break;
            case 1:
                if (x + 1 < N && map[x + 1][y] != 6) {
                    return goStraight(x + 1, y, dir, map);
                }
                break;
            case 2:
                if (y - 1 >= 0 && map[x][y - 1] != 6) {
                    return goStraight(x, y - 1, dir, map);
                }
                break;
            case 3:
                if (x - 1 >= 0 && map[x - 1][y] != 6) {
                    return goStraight(x - 1, y, dir, map);
                }
                break;
        }
        return map;
    }
}
