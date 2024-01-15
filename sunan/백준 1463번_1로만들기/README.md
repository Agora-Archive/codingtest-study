| 시간 제한 | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| --- | --- | --- | --- | --- | --- |
| 0.15 초 (https://www.acmicpc.net/problem/1463#) | 128 MB | 289296 | 98239 | 62653 | 32.857% |

## 문제

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력

첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.

## 출력

첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

## 예제 입력 1

```
2

```

## 예제 출력 1

```
1

```

## 예제 입력 2

```
10

```

## 예제 출력 2

```
3

```

## 힌트

10의 경우에 10 → 9 → 3 → 1 로 3번 만에 만들 수 있다.

## 선정 이유

처음에 만만해 보여서 5분만에 풀었다고 생각하고 제출했으나 틀려서 차일피일 미루다가 드디어 풀게됨

## 아이디어(실패 버전)

일단 2보단 3으로 나누었을 때가 제일 수를 작게 만들 수 있는 방법이다.

그래서  3을 우선적으로 나누는 방법을 우선으로 두고

그 다음 2로 나누고

그다음 1을 빼는 방식으로 진행해주었다.

근데 만약 2, 3으로 안 나누어 떨어지는데 1을 뺐을 때 3 또는 2로 나누어 떨어지게 된다면

먼저 1을 빼는 연산을 진행해준다.

근데 어떤 상황에서 3을 먼저 나눠주고 2를 먼저 나눠줘야 할 지에 대해서 고민해봐야 한다.

예를 들어 1111일때

지금의 규칙으로 해주면 12가 나온다.

그런데 사실 최소 연산수는 11이다.

40에서 -1 나누고 /3을 해주는 대신에

/2만 해준다.

```cpp
#include<iostream>
using namespace std;
static int a, index=0;
static int cnt = 0;
//static int dp[1000001];
void make() {
        if (a == 1) {
            return;
        }
        else if ((a - 1) % 9 == 0 ) {
            a--;
            cnt++;
        }
        else if (a % 3 == 0) {
	        a /= 3;
	        cnt++;
	    }
	    else if (a % 2 == 0) {
	        a /= 2;
	        cnt++;
	    }
		else {
			a--;
			cnt++;
		}
	   make();
}
int main() {
	cin >> a;
	make();
	cout << cnt;
}
```

5%에서 틀려버린다.

그리고 단박에 봐도 이런 식으로 푸는 게 옳지 않다고 느껴진다.

누가 지나가다 보더니 말했다.

“이거 ㅋㅋ dp 탑-다운이나 바텀-업으로 풀어야 함 ㅋㅋ”

그래서 그게 뭔데?

[참고 블로그](https://bada744.tistory.com/61)

### Top-Down

동적 계획 법(DP)의 방식 중 하나로 큰 문제→작은 문제 순서로 재귀를 통해 계산하는 방식

### Bottom-Up

마찬가지로 DP의 방식 중 하나인데 Top-Down과 반대로 작은 문제 → 큰 문제 순서로 반복문을 이용해서 계산하는 방식

## 풀이 코드(Let me do this~)

나는 Bottom-up으로 풀어보았다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

import static java.lang.System.exit;
public class Main {

    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] dp = new int[n+1];// 각 index에 1로 만드는데 필요한 연산수 적용
        dp[0]=dp[1]=0;
        for(int i=2; i<n+1; i++){
            dp[i]=dp[i-1]+1;//1을 빼주는 경우
            if(i%2==0){
                dp[i]=Math.min(dp[i], dp[i/2]+1);//2로 나눴을 때의+1이 2로 나누는 경우
            }
            if(i%3==0){
                dp[i]=Math.min(dp[i], dp[i/3]+1);//2로 나눴을 때의+1이 2로 나누는 경우
            }
        }
        System.out.print(dp[n]);
    }
}
```

이런 기법(?)들을 알아두면 훨씬 수월하게 풀 수 있을 것이라 생각된다.

실버 문제라도 배워가는 문제라고 생각된다.
