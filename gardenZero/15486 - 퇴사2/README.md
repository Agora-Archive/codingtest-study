[문제 링크](https://www.acmicpc.net/problem/15486)

## 알고리즘 분류
---
DP

## 알게된 점
---
따악 보니 배낭 문제였다. 

근데 입력도 안했는데 Segmentation Fault 떠서 한 20분은 날려먹었다.

그 이유는 너무 큰 배열을 main 함수 내에 선언해서 그런거였다.

전역변수에 배열을 선언해주니 굿~ 잘 되었다.

## 나의 접근 방법
---
배낭 문제를 잘 공부했다면 쉽게 풀 수 있는 문제다.

![](images/2024-07-02-BOJ-15486.png)

위의 경우를 생각해보면 **남은 일 수**가 배낭 문제에서 **남은 배낭의 용량**이라고 생각할 수 있다.

하지만 **다른점이 하나 존재**한다. 

배낭문제에서는 물건을 넣지 않으면 무게에 영향이 없다.    
하지만, 이 문제에서는 **일을 안하고 넘어갈 경우 남은 일수가 1일 줄어든다.**

![](images/2024-07-02-BOJ-15486-1.png)

설명은 위 그림으로 대체한다.

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int tp[1500001][2]; //소요시간과 돈을 입력받는 배열
int dp[1500001]; //벌수 있는 돈의 최댓값을 저장하는 배열

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    
    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> tp[i][0] >> tp[i][1];
    }

    dp[0] = 0;
    for(int i=1; i<=n; i++){
        int day = n - i + 1;    //현재날을 구하기 위해 전체날 - 남은날 + 1
        int cost = tp[day][0];
        int value = tp[day][1];
        
        if(cost > i) dp[i] = dp[i-1];
        else dp[i] = max(dp[i-1], dp[i-cost] + value);
    }

    cout << dp[n];

    return 0;
}
```
