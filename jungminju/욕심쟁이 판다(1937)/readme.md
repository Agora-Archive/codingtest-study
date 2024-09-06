[⭐문제링크](https://www.acmicpc.net/problem/1937)

판다가 얼마나 욕심쟁이일지 궁금해서 선정했다.

## 문제요약
1. n × n의 크기의 대나무 숲이 있다. (각 인덱스에는 대나무의 수가 주어진다.)
2. 판다는 임의의 위치의 대나무를 먹는다.
3. 그 후 상하좌우 중 하나로 이동하는데, 이전 위치의 대나무보다 무조건! 더 많은 대나무가 있어야 한다.

**판다가 가장 많이 이동할 수 있는 횟수를 구하는 문제이다.**

이런 문제 유형을 안풀어봐서 연습해보고자 가져왔다.

![](https://velog.velcdn.com/images/jung-min-ju/post/14116684-98b2-4294-bfc2-afbfc710b9d1/image.gif)

🐼🐼🐼🐼🐼🐼 데굴대굴.. 🐼🐼🐼🐼🐼🐼




## 접근법
1. 해당 문제는 보자마자 메모이제이션이 떠올랐다.
2. 메모이제이션으로 가지치기를 할 것이기 때문에 dfs로도 시간이 충분할 것이라 판단했다.

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int input[][];
    static int dp[][];
    static int max=0;
    static int [] dirR = {-1,1,0,0};
    static int [] dirC = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, dp(i, j, 0));
            }
        }

        System.out.println(max);
    }

    static int dp(int x, int y, int exNum) {
        if(x==-1 || x==N || y==-1 || y==N) return 0; //배열 범위 조절
        if(input[x][y] <= exNum ) return 0;
        if(dp[x][y]>1) return dp[x][y];

        for(int i=0; i<4; i++) {
            int count = 1;
            count += dp(x+dirR[i], y+dirC[i], input[x][y]);
            dp[x][y] = Math.max(dp[x][y], count);
        }

        return dp[x][y];
    }
}


```

저번주 정원영 군이 큐를 야물딱지게 활용을 잘 하길래 이번 문제에 한 번 사용해보려 했지만, 생각해보니 c++이 아닌 자바는 class를 만들어야 하기 때문에 빠르게 철회했다. (요즘 자꾸 갈아타고 싶다)

메인 코드만 따보겠다.

### 1. 모든 인덱스 접근

> ### static int dp (int x, int y, int exNum) { ... }
* x : 조사할 x 인덱스
* y : 조사할 y 인덱스
* exNum : 이전 대나무보다 많은 대나무 여야만 코드가 동작하기에, 이전 대나무를 인자로 넘겨줬다.

```java
 for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, dp(i, j, 0));
            }
        }
```

일단 N*N 의 대나무 숲을 모두 한 번씩 접근해서 dp 함수를 불러준다.
그럼 해당 인덱스 i, j에서 움직을 수 있는 최대 움직임 횟수를 return 해줄 것이다.

우린 최대 움직임을 찾아야 하므로 Math.max 함수를 통해 항상 최대값을 갱신했다.

### 2. 최대 움직임 찾기

```java
  static int dp(int x, int y, int exNum) {
        if(x==-1 || x==N || y==-1 || y==N) return 0; //배열 범위 조절
        if(input[x][y] <= exNum ) return 0;
        if(dp[x][y]>1) return dp[x][y];

        for(int i=0; i<4; i++) {
            int count = 1;
            count += dp(x+dirR[i], y+dirC[i], input[x][y]);
            dp[x][y] = Math.max(dp[x][y], count);
        }

        return dp[x][y];
    }
```

해당 함수 진입 전, 총 3개의 if문이 있다.

> 1. 해당 x, y가 배열 범위를 벗어나는 지 체크
2. 현재 대나무가 이전 대나무보다 많은지 체크
3. 이미 메모이제이션된 값이 있을 시, 해당 값을 바로 return 하고 함수 종료

그 후 메인 for문에서 각 4방향을 돌며 조사한다.

> 1. 방향 첫 탐색 시, count는 1로 초기화 한다.
2. 해당 방향으로의 dp 함수로 return된 값은 다시 count에 저장한다.
3. 그 후 기존에 메모이제이션 되어 있는 dp[x][y] 값과, 이번에 새롭게 탐색한 횟수 중 큰 값을 dp 배열에 담는다.
4. 최종적인 해당 x, y에 대한 판다의 움직임 횟수 값을 return하고 함수가 종료된다.

이렇게 하면 통과는 된다!!!

![](https://velog.velcdn.com/images/jung-min-ju/post/829ef7e4-d795-478f-9fee-5716eac44191/image.png)

문제가 있다면 코드가 이쁘지가 않다... 통과만을 목적으로 짰기에 쓸데없는 부분이 너무 많다고 느꼈다.

그래서 다음과 같이 수정되었다!!!!!!!!!!


----

## 최종 코드

### 1. 모든 인덱스 접근

> ### static int dp (int x, int y) { ... }

⭐이전값을 넘겨주던 exNum 변수를 삭제했다!
-> 해당 로직은 dp 함수 내에서 처리해줄 것이다.


```java

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, dp(i, j));
            }
        }
```


### 2. 최대 움직임 찾기

```java
   static int dp(int x, int y) {
        if(dp[x][y]!=0) return dp[x][y];

        dp[x][y] = 1;
        for(int i=0; i<4; i++) {
            int nx = x + dirR[i];
            int ny = y + dirC[i];

            if(nx==-1 || nx==N || ny==-1 || ny==N) continue; //배열 범위 조절
            if(input[x][y] < input[nx][ny]){
                dp[x][y] = Math.max(dp[x][y], dp(nx, ny) + 1);
            }
        }
        return dp[x][y];
    }
```

if 문이 깔끔하게 줄었다.
> 1. 해당 x, y 값에 대한 메모이제이션 값이 있다면, 해당 값 바로 return

그리고 아까는 count 변수를 사용했지만, 음 쓸데없었다!

> 1. dp[x][y]에 바로 1을 박고 시작한다.
2. for문 으로 4방향을 돌되, **다음 x, y로 진입을 할 수 있는지 체크**하고 되면 함수 호출을 할 것이다.
>
⭐아깐 불필요한 경우도 함수호출이 무조건 일어나기에, 좋은 코드가 아니었다. 미리 체크하고 호출하자⭐
>
2-1. 다음 x, y 가 배열 범위를 초과하는지 검사한다.
2-2. 다음 진입할 대나무의 수가, 현재 대나무 수보다 많은지 검사한다.
3. 위 조건을 통과한 경우, 해당 방향으로의 탐색을 진행한다.
4. 탐색된 값과 기존 dp[x][y]에 있던 값 중 큰 값을 저장한다.

로직은 비슷하지만 코드가 더 보기 깔끔하고 효율성도 챙긴 것 같다.

![](https://velog.velcdn.com/images/jung-min-ju/post/7ff29c7a-1488-44d3-b8ef-626c68f88dfe/image.png)



