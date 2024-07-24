---
title: "[C++] 백준 14502 : 연구소"
date: 2024-07-016 +09:00
categories:
  - Study
  - Baekjoon
tags:
  - 백준
  - 백트래킹
  - BFS
---
[문제 링크](https://www.acmicpc.net/problem/14502)

## 알고리즘 분류
---
백트래킹 + BFS

## 알게된 점
---
삼성 문제라 지레 겁을 먹었는데 생각보다 괜찮았다. 코드 자체가 길다보니 빠른 시간내에는 풀기 힘들었다.

이전에 Gaaaaaaaaaarden을 풀었던 터라 비슷한 유형이라 풀기가 쉬웠다.    
- 연속 두번 비슷한 문제를 들고 왔다고 욕할 수 있지만 사실 여러 알고리즘을 섞는 문제는 언제다 옳다.

이제는 bfs와 백트래킹은 좀 확실히 된 것 같다.

한 번씩 bfs범위 설정에 문제를 겪는데 조심해야겠다.

이차원 배열을 {0, }과 같이 초기화해도 문제가 생기지않는다.

## 나의 접근 방법
---
이 문제는 크게 **두가지 단계**로 이루어진다.
1. 어떤 곳에 벽을 세울지 정하기
2. 바이러스를 퍼트리고 안전지대의 개수를 세기

#### 1. 어떤 곳에 벽 세울지 정하기
**우선 어떤 곳에 벽을 세울지 정하기 위해 selected 벡터를 이용한다.**   
blank 벡터에 0인 곳의 좌표를 저장한 후 selected 벡터가 1인 인덱스를 찾아 벽을 세운다.   
그 과정은 아래와 같다.

1. 0이 들어올 때마다 selected 벡터에 0을 추가해준다.
2. 0은 3개이상 존재한다고 하였으므로 0, 1, 2번 인덱스를 1로 만들어준다.
3. sorting 후 next_permutation을 통해 벽을 세울 곳을 지정해준다.

위와 같이 벽을 세울 곳이 정해졌다면 back 함수로 넘어간다.   
back 함수의 작동원리는 아래와 같다.
1. 벽을 세운다.
	1. selected 벡터에서 값이 1인 인덱스를 찾는다.
	2. black 벡터에서 해당 인덱스의 값을 얻는다.
	3. 해당 값은 벽으로 바꿀 곳의 좌표이므로 arr에서 해당 좌표의 값을 1로 바꾼다.
2. BFS을 돌려 안전구역의 수를 찾고 max값을 업데이트 한다.
3. 세웠던 벽을 원상 복구한다.
	1. 그 과정은 벽을 세원 원리와 같다.

#### 2. 바이러스 퍼트리기 
벽을 세울 곳을 정했다면 BFS를 통해 바이러스를 퍼트려야한다.   
다른 부분은 일반적인 BFS와 동일하다.

다른 점은 한 점에서 시작하는 것이 아니라 여러점에서 시작한다는 점 밖에 없다.   
barr에는 바이러스의 확산 여부를 저장하고 체크한다.   
arr에는 벽이 세워져 있는지 체크를 한다.

BFS가 모두 끝나고 나면 해당 좌표는 세가지 중 하나의 상태를 가지게 된다.
1. arr가 1이어서 벽인 상태
2. barr가 2이어서 바이러스가 퍼진 상태
3. arr가 0이고 barr도 0이어서 안전 지대인 상태

3인 경우를 체크하여 최대값을 구해주면 된다.

## 코드
---
편의를 위해 BFS를 돌릴 때는 아래와 같이 define하여 코드를 짰다.
- first -> x
- second -> y

```cpp
#include <bits/stdc++.h>
using namespace std;

#define x first
#define y second

int arr[9][9] ={0, };
int n, m;
vector<pair<int, int>> virus;
vector<pair<int, int>> blank;
vector<int> selected;
int answer = -1;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

void bfs(){
    int barr[9][9] = {0, };

    queue<pair<int, int>> q;
    for(int i=0; i<virus.size(); i++){
        q.push(virus[i]);
        barr[virus[i].x][virus[i].y] = 2;
    }

    int cx, cy;
    while(!q.empty()){
        tie(cx, cy) = q.front(); q.pop();

        for(int i=0; i<4; i++){
            int nx = cx + dx[i], ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(arr[nx][ny] == 1 || barr[nx][ny] == 2) continue;
            barr[nx][ny] = 2;
	        q.push({nx, ny});
        }
    }

    int result = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(barr[i][j] != 2 && arr[i][j] != 1) result++;
        }
    }


    answer = max(answer, result);
}

void back(){
    for(int i=0; i < selected.size(); i++) {
         if(selected[i] == 1) {
            arr[blank[i].x][blank[i].y] = 1;
         }
    }

    bfs();

    for(int i=0; i < selected.size(); i++) {
         if(selected[i] == 1) {
            arr[blank[i].x][blank[i].y] = 0;
         }
    }
}

int main(){
    cin >> n >> m;

    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> input;
            arr[i][j] = input;
            if(input == 2){
                virus.push_back({i, j});
            }
            if(input == 0) {
                blank.push_back({i, j});
                selected.push_back(0);
            }
        }
    }

    selected[0] = selected[1] = selected[2] = 1;
    sort(selected.begin(), selected.end());

    do{
        back();
    }while(next_permutation(selected.begin(), selected.end()));


    cout << answer;

    return 0;
}
```

