[문제링크](https://www.acmicpc.net/problem/1074)

오늘 가져온 문제는 z입니다.

## 문제요약
* 2^N × 2^N인 2차원 배열을 Z모양으로 탐색하려고 한다
* N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다
* N이 주어졌을 때, r행 c열을 몇 번째로 방문는지 출력하자

## 접근법
처음엔 그냥 분할정복으로 풀려고 했다. 자료구조에서 과제로 나왔던 문제와 똑같다고 생각했기 때문이다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer=-1;
    static int n, r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //2 3 1
        findInOrder(0, 0, n);
        System.out.println(answer);
    }

    static boolean findInOrder(int x, int y, int n) { //2 3 1
        if(n<0) return false;
        if(n==0) answer++;
        if(x==r && y==c && n==0) return true;

        int nextSize = (int) Math.pow(2, n-1);
        //1번 위치
        if(findInOrder(x, y, n-1)==true) return true;
        //2번 위치
        if(findInOrder(x, y+nextSize, n-1)==true) return true;
        //3번 위치
        if(findInOrder(x+nextSize, y, n-1)==true) return true;
        //4번 위치
        if(findInOrder(x+nextSize, y+nextSize,n-1)==true) return true;
        
        return false;
    }
}
```

![](https://velog.velcdn.com/images/jung-min-ju/post/42fd3191-e49a-4ea7-8c5a-5c475d248e9e/image.png)
아니 근데! 시간초과 났다.

문제를 다시 읽어보니 0.5 의 시간제한이 있었다.
그래서 음.. 모든 경우를 다 재귀로 도는 것이 아니라, 특정 r, c가 이미 주어졌으니 4분면 중 특정 r, c에 해당하는 분면만 재귀함수를 타고 들어가는 게 좋을 것 같았다.

여기서는 swich문을 이용했다. 코테 문제 풀면서는 처음 적용해보는 것 같다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int n, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        findInOrder(0, 0, n);
        System.out.println(answer);
    }

    static void findInOrder(int x, int y, int n) {
        if (n < 0) return;

        if (x == r && y == c) return;

        int nextSize = (int) Math.pow(2, n - 1);
        int range = getRange(x, y, nextSize);
        int half =  ((int) Math.pow(2, n)) / 2;
        int area = half*half;

        switch (range) {
            case 1: // 1번 위치
                findInOrder(x, y, n - 1);
                break;
            case 2: // 2번 위치
                answer+=area;
                findInOrder(x, y + nextSize, n - 1);
                break;
            case 3: // 3번 위치
                answer+=area*2;
                findInOrder(x + nextSize, y, n - 1);
                break;
            case 4: // 4번 위치
                answer+=area*3;
                findInOrder(x + nextSize, y + nextSize, n - 1);
                break;
        }
        return;
    }

    static int getRange(int x, int y, int nextSize) {
        // 현재 좌표 (r, c)가 어느 사분면에 속하는지 판단
        if (r < x + nextSize && c < y + nextSize) {
            return 1; // 1사분면
        } else if (r < x + nextSize && c >= y + nextSize) {
            return 2; // 2사분면
        } else if (r >= x + nextSize && c < y + nextSize) {
            return 3; // 3사분면
        } else {
            return 4; // 4사분면
        }
    }
}

```

![](https://velog.velcdn.com/images/jung-min-ju/post/203b3f75-4876-4c3f-a3f3-5a0f9aa8e30b/image.png)
