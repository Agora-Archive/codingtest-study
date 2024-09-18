⭐ [문제링크](https://www.acmicpc.net/problem/2342)

오늘 제가 풀어볼 문제는 ddr 이란 문제입니다

## 1. 문제 요약
* 주어진 스텝 수열을 밟기 위해 최소한의 힘을 구하는 문제
* 발 이동에 따른 힘 계산
    * 중앙에서 다른 위치로 이동: 2
    * 인접 위치로 이동: 3
    * 반대편으로 이동: 4
    * 같은 위치를 다시 밟을 때: 1
* 두 발은 같은 위치에 있을 수 없음
* 입력은 1, 2, 3, 4로 이루어진 수열이며, 0으로 끝남

## 2. 접근

완전탐색으로 접근해보고자 했다. 그런데 생각해보니 그럼 시간적으로 최대 2^십만 이 걸리는데 문제의 요구조건인 2초에 적절하지 않았다.
(왼/오 선택지 ^ 움직이는 횟수)

그래서 그리디 적으로 접근해보고자 하였다.

1. 입력값만을 배열에 저장
2. 매 스텝마다 왼발(leftFoot)과 오른발(rightFoot) 위치 기록.
3. 스텝마다 왼발과 오른발 중 어느 쪽을 움직이는 것이 더 적은 에너지를 소모하는지 판단하여 발을 움직임.

코드는 다음과 같다.

## 3. 코드

### 3-1. 전체적 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int endIdx = 0;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100001];

        // 입력 처리
        for (int i = 0;; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp == 0) {
                endIdx = i; // 0이 나올 시 종료
                break;
            } else {
                arr[i] = temp;
            }
        }

        // 초기 양발의 위치는 모두 0에서 시작
        int lf = 0;
        int rf = 0;
        int answer = 0;

        // 스텝을 하나씩 처리하며 최소 에너지를 계산
        for (int i = 0; i < endIdx; i++) {
            int nextStep = arr[i];

            // 왼발을 움직일 때의 에너지 계산
            int lEnergy = calculate(lf, nextStep);
            // 오른발을 움직일 때의 에너지 계산
            int rEnergy = calculate(rf, nextStep);

            // 왼발이 움직이는 것이 더 적은 에너지를 소모하면 왼발을 움직임
            if (lEnergy < rEnergy) {
                answer += lEnergy;
                lf = nextStep; // 왼발의 위치 업데이트
            } else {
                answer += rEnergy;
                rf = nextStep; // 오른발의 위치 업데이트
            }
        }

        System.out.println(answer);
    }

    // 발의 이동에 따른 에너지를 계산하는 함수
    public static int calculate(int start, int end) {
        if (start == 0) return 2; // 시작점
        if (start == end) return 1; // 같은 위치
        int distance = Math.abs(start - end);
        if (distance == 1 || distance == 3) return 3; // 인접한 위치
        return 4; // 반대편 위치
    }
}

```

### 3-2. 에너지 계산

![](https://velog.velcdn.com/images/jung-min-ju/post/a90497bb-4714-4a60-82c7-44f1164ca14e/image.png)

>* 발 이동에 따른 힘 계산
* 중앙에서 다른 위치로 이동: 2
* 인접 위치로 이동: 3
* 반대편으로 이동: 4
* 같은 위치를 다시 밟을 때: 1

```java
    // 발의 이동에 따른 에너지를 계산하는 함수
    public static int calculate(int start, int end) {
        if (start == 0) return 2; // 시작점
        if (start == end) return 1; // 같은 위치
        int distance = Math.abs(start - end);
        if (distance == 1 || distance == 3) return 3; // 인접한 위치
        return 4; // 반대편 위치
    }
```




### 3-3. 최소 힘 계산
```java
        // 스텝을 하나씩 처리하며 최소 에너지를 계산
        for (int i = 0; i < endIdx; i++) {
            int nextStep = arr[i];

            // 왼발을 움직일 때의 에너지 계산
            int lEnergy = calculate(lf, nextStep);
            // 오른발을 움직일 때의 에너지 계산
            int rEnergy = calculate(rf, nextStep);

            // 왼발이 움직이는 것이 더 적은 에너지를 소모하면 왼발을 움직임
            if (lEnergy < rEnergy) {
                answer += lEnergy;
                lf = nextStep; // 왼발의 위치 업데이트
            } else {
                answer += rEnergy;
                rf = nextStep; // 오른발의 위치 업데이트
            }
        }

```

예제도 잘 나와서 될 거라고 생각했다.

헿 근데 틀렸당 1프로도 안갔다.

나는 어차피 5개의 선택지 중 무려 2개의 발을 갖고있으니, 매 선택마다 최선의 선택으로 골라도 문제가 없을 거라고 생각했는데, 아마 인접하는 경우(힘 :3) 인접하지만 시작점에서 출발하는 경우(힘:2) 가 계산이 안되고 있는 것 같다.

...

그래서 실패했다~ 어려웠다.

----

## 4. 정답코드

dp를 통해 구성된 코드이다. top-bottom 형식으로 이루어져 있다.

## 5. 접근법

1. dp[스텝][왼발위치][오른발위치] 로 구성된 3차원 배열 형성
2. top-bottom으로 구성되어 최종적으론 dp[0][0][0]에 정답이 담김
3. simulate(int level, int lf, int rf) :  남은 스텝의 최소 에너지들을 계산하여 dp 배열에 저장한다.
4. calculate(int start, int end) : 햔재 발의 위치에서 다음 목표 위치로의 힘을 계산한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int endIdx = 0;
    public static int[] arr;
    public static int[][][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100001];

        for(int i=0; ;i++) {
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 0) {
                endIdx = i;
                break;
            }else {
                arr[i] = temp;
            }
        }
        dp = new int[endIdx + 1][5][5];

        System.out.println(simulate(0, 0, 0));
    }

    public static int simulate(int level, int lf, int rf) {
        if(dp[level][lf][rf] > 0) return dp[level][lf][rf];
        if(endIdx == level) {
            return 0;
        }

        int temp1 = calculate(lf, arr[level]) + simulate(level + 1, arr[level], rf);
        int temp2 = calculate(rf, arr[level]) + simulate(level + 1, lf, arr[level]);

        return dp[level][lf][rf] = Math.min(temp1, temp2);

    }
    public static int calculate(int start, int end) {
        int distance = Math.abs(start - end);
        if(start == 0) return 2; //시작점
        else if(distance == 0) return 1; //같은 위치
        else if(distance == 1 || distance == 3) return 3; //인접
        else return 4; //반대편
    }

}
```

### 4-1. 최소 에너지 계산

1. 메모리제이션을 사용
2. 만약 현재 level이 0이라면, 끝난 것이므로 에너지를 계산하지 않고 0 반환
3. tmep1/temp2에
    - 왼/발 위치에서 목표 위치로의 힘 계산
    - 다음 스텝에서 return 되어 온 힘의 최소값

을 더해서 현재 step의 최소 힘을 기록한다.(top-bottom)


```java
    public static int simulate(int level, int lf, int rf) {
        if(dp[level][lf][rf] > 0) return dp[level][lf][rf];
        if(endIdx == level) {
            return 0;
        }

        int temp1 = calculate(lf, arr[level]) + simulate(level + 1, arr[level], rf);
        int temp2 = calculate(rf, arr[level]) + simulate(level + 1, lf, arr[level]);

        return dp[level][lf][rf] = Math.min(temp1, temp2);

    }
```


