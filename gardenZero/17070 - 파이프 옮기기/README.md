[문제 링크](https://www.acmicpc.net/problem/17070)

## 알고리즘 분류
---
BFS

## 알게된 점
---
BFS로 간단히 풀 수 있을 것 같아서 풀었더니 됐다.

사실 그냥 간단한 문제다.

## 나의 접근 방법
---
범위가 최대 16x16이라 그냥 BFS돌려도 되겠다고 생각했다.

파이프가 향하고 있는 곳만 집중해서 보면 된다고 생각했다.

주의할 점은 두가지 뿐이다.
1. 파이프가 어떤 방향으로 놓여져 있는지
2. 해당 방향에따라 체크해야하는 곳은 어디인지

우선 갈 수 있는 곳인지 체크하고 큐에 넣기 위해 trying 함수를 만들어 사용했다.

queue에는 현재 좌표와 놓여져 있는 방향을 넣는다. 방향은 dx, dy에서도 동일하다.
- 0: 가로
- 1: 세로
- 2: 대각선

```cpp
void trying(int i){
    nx = cx + dx[i]; ny = cy + dy[i];

    if(nx >= n || ny >= n) return;

    if(i == 0 || i == 1){
        if(wall[nx][ny]) return;
    }else{
        if(wall[nx][ny] || wall[nx-1][ny] || wall[nx][ny-1]) return;
    }

    arr[nx][ny]++;
    q.push({nx, ny, i});
}
```

만약 모든 조건을 통과하면 queue에 집어넣는다.

아래와 같이 ct를 보고 현재 방향에 따라 trying하는 방향을 결정해주면 된다.

```cpp
q.push({0, 1, 0});
    while(!q.empty()){
        tie(cx, cy, ct) = q.front(); q.pop();

        if(ct == 0){
            trying(0);
            trying(2);
        }else if(ct == 1){
            trying(1);
            trying(2);
        }else{
            trying(0);
            trying(1);
            trying(2);
        }
    }
```

코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int n;
int cx, cy, ct, nx, ny;

bool wall[16][16];
int arr[16][16];
int dx[3] = {0, 1, 1};
int dy[3] = {1, 0, 1};

queue<tuple<int, int, int>> q;

//갈 수 있는 곳인지 체크후 q에 넣기
void trying(int i){
    nx = cx + dx[i]; ny = cy + dy[i];

    if(nx >= n || ny >= n) return;

    if(i == 0 || i == 1){
        if(wall[nx][ny]) return;
    }else{
        if(wall[nx][ny] || wall[nx-1][ny] || wall[nx][ny-1]) return;
    }

    arr[nx][ny]++;
    q.push({nx, ny, i});
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n;

    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> input;
            if(input == 1) wall[i][j] = true;
        }
    }

    // 0: 가로 1: 세로 2: 대각선
    q.push({0, 1, 0});
    while(!q.empty()){
        tie(cx, cy, ct) = q.front(); q.pop();

        if(ct == 0){
            trying(0);
            trying(2);
        }else if(ct == 1){
            trying(1);
            trying(2);
        }else{
            trying(0);
            trying(1);
            trying(2);
        }
    }

    cout << arr[n-1][n-1];

    return 0;
}
```