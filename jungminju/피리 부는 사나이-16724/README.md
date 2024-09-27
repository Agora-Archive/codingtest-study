오늘 준비한 문제는
### ⭐[문제링크](https://www.acmicpc.net/problem/16724)

# 1. 문제요약
1. 입력값으로 방향이 위(U), 아래(D), 오른쪽(R), 왼쪽(L)로 주어집니다.
2. 모든 배열의 원소들이 몰리는 구역의 최소 개수를 구하면 됩니다.
3. 배열을 벗어나게 하는 방향은 주어지지 않습니다.

# 2. 접근 방법
처음 문제를 읽었을 때, 분리 집합 찾는 문제라는 생각이 들었습니다.
또한 중요한 점은 한 번 지나온 곳은 또 다시 탐색할 필요가 없다는 것이 먼제 들어 다음과 같이 코드를 작성했었습니다.

1. R,L,U,D를 각각 0,1,2,3으로 치환
	* 추후 dirX, dirY 배열 원소를 인덱스로 바로 접근하기 위함
2. 모든 배열 내 원소들을 for문으로 dfs 탐색

## 2-1. dfs 함수

해당 로직의 주요 코드입니다.

> * 해당 배열의 원소값은 바로 -1로 변환
    * 그 다음 원소의 값이 -1이라면, ans++
    * 그 다음 원소의 값이 -1이 아니라면, dfs 탐색 진행
    

```java
static void dfs(int r, int c){
        int nextDir = map[r][c];
        int nextR = r+dirR[nextDir];
        int nextC = c+dirC[nextDir];

        map[r][c] = -1;

        if(map[nextR][nextC]==-1) answer++;
        
        else dfs(nextR, nextC);
    }
```
    
    

코드는 다음과 같습니다.
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int map[][];
    static int[] dirR = {0,0,-1,1};
    static int[] dirC = {1,-1,0,0}; //R, L, U, D
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0; i<R; i++) {
           String string = br.readLine();
            for(int j=0; j<C; j++) {
                char c = string.charAt(j);
                if (c=='R') map[i][j] = 0;
                else if (c=='L') map[i][j]=1;
                else if(c=='U') map[i][j]=2;
                else map[i][j]=3;
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] !=-1) dfs(i, j);
            }
        }
        System.out.println(answer);

    }

    static void dfs(int r, int c){
        int nextDir = map[r][c];
        int nextR = r+dirR[nextDir];
        int nextC = c+dirC[nextDir];

        map[r][c] = -1;

        if(map[nextR][nextC]==-1) answer++;
        
        else dfs(nextR, nextC);
    }
}
```

두둥두둥

틀렸습니다~

틀린 이유는 처음에 한 번 지난 원소를 재탐색 하지 않고 바로 answer의 값을 올려버린 다는 점 때문이었습니다.

예외 사항은 다음과 같습니다.
![](https://velog.velcdn.com/images/jung-min-ju/post/d9dc3eff-a4a6-4bb2-98da-06654e9fb738/image.png)
해당 경우에 대한 safe-Zone은 1개이지만, 제 로직대로라면 출력값으로 2가 도출됩니다.

즉
**방문을 나타내는 배열과 하나의 분리 집합으로서 인정을 받았는지 유무를 나타내는 배열을 따로 관리해줘야 한다** 
는 것이 해당 문제의 포인트 였습니다.

# 3. 정답 코드

위의 접근방법과 동일하나, 다른 점은 

>dfs 내 함수에서 그 다음 원소값이 -1 일 떄, 그 다음 원소값이 이미 사이클로서 인증을 받았는지 아닌지 if문을 한 번 더 거쳐줍니다.
>
그 후 return 되기 직전, 현재 원소값은 분리집합에 포함됨을 나타내 줍니다.

## 3-1. dfs 함수
```java
 static void dfs(int r, int c) {
        int nextDir = map[r][c];
        int nextR = r+dirR[nextDir];
        int nextC = c+dirC[nextDir];

        map[r][c] = -1;

        if(map[nextR][nextC]==-1) {
            if(!isCycle[nextR][nextC]) answer++;
        }
        else dfs(nextR, nextC);
        
        isCycle[r][c] = true;
    }
```


## 3-2. 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int map[][];
    static int[] dirR = {0,0,-1,1};
    static int[] dirC = {1,-1,0,0}; //R, L, U, D
    static int answer=0;
    static boolean isCycle[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        isCycle = new boolean[R][C];

        for(int i=0; i<R; i++) {
           String string = br.readLine();
            for(int j=0; j<C; j++) {
                char c = string.charAt(j);
                if (c=='R') map[i][j] = 0;
                else if (c=='L') map[i][j]=1;
                else if(c=='U') map[i][j]=2;
                else map[i][j]=3;
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] !=-1) dfs(i, j);
            }
        }
        System.out.println(answer);

    }

    static void dfs(int r, int c) {
        int nextDir = map[r][c];
        int nextR = r+dirR[nextDir];
        int nextC = c+dirC[nextDir];

        map[r][c] = -1;

        if(map[nextR][nextC]==-1) {
            if(!isCycle[nextR][nextC]) answer++;
        }
        else dfs(nextR, nextC);
        
        isCycle[r][c] = true;
    }
}

```

![](https://velog.velcdn.com/images/jung-min-ju/post/371ca0f5-06a7-41a6-9dc8-646c455b9dc1/image.png)
