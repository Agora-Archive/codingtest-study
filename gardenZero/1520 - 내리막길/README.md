 [문제 링크](https://www.acmicpc.net/problem/1520)

## 알고리즘 분류
---
DP + DFS

## 알게된 점
---
메모이제이션이라는 말이 거창하긴 하지만 그냥 저장해두는 것 밖에 없는 것 같다.

dp와 dfs를 할 줄 안다면 간단한 문제같다.

## 나의 접근 방법
---
생각보다 쉽게 풀렸다. 기본적인 DFS에 DP를 살짝 첨가한 버전이다. 

원리는 간단하다. 한 지점에서 최종 지점까지 갈 수 있는 경로의 수는 정해져 있다.    
따라서, 한번 그 지점을 지나갔다면 경로의 수를 저장해두면 된다.

이런 방식을 **메모이제이션**이라고 한다.

DFS를 도는 코드는 아래와 같다.

```java
int dfs(int cx, int cy){

    if(cx == m-1 && cy == n-1) return 1;
    if(dp[cx][cy] != -1) return dp[cx][cy];

    dp[cx][cy] = 0;
    int nx, ny;
    for(int i=0; i<4; i++){
        nx = cx + dx[i]; ny = cy + dy[i];
        if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        if(arr[nx][ny] >= arr[cx][cy]) continue; // 가려는 곳이 오르막길이면 패스
    
        dp[cx][cy] = dp[cx][cy] + dfs(nx, ny);
    }

    return dp[cx][cy];
}
```

중요한 점은 dp배열을 -1로 초기화 해두고 한번이라도 바뀐다면 dfs를 진행하지 않는 것이다.(이 과정이 이미 경로의 수를 계산한 지점을 재계산하지 않는 것이다.)

이외에 dfs와 다른점은 오르막길 일때만 진행한다는 점 뿐이다.

코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int m, n;
int arr[500][500];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};
int dp[500][500];

int dfs(int cx, int cy){

    if(cx == m-1 && cy == n-1) return 1;
    if(dp[cx][cy] != -1) return dp[cx][cy];

    dp[cx][cy] = 0;
    int nx, ny;
    for(int i=0; i<4; i++){
        nx = cx + dx[i]; ny = cy + dy[i];
        if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        if(arr[nx][ny] >= arr[cx][cy]) continue; // 가려는 곳이 오르막길이면 패스
    
        dp[cx][cy] = dp[cx][cy] + dfs(nx, ny);
    }

    return dp[cx][cy];
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> m >> n;

    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            cin >> arr[i][j];
            dp[i][j] = -1;
        }
    }

    int result = dfs(0, 0);

    cout << result;

    return 0;
}
```