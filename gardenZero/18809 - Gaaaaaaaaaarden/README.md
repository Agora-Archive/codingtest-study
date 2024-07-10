---
title: "[C++] 백준 18809 : Gaaaaaaaaaarden"
date: 2024-07-09 +09:00
categories:
  - Study
  - Baekjoon
tags:
  - 백준
  - 백트래킹
  - BFS
pin: false
---
[문제 링크](https://www.acmicpc.net/problem/18809)

## 알고리즘 분류
---
백트래킹 + BFS

## 알게된 점
---
정말 골드1 수준이었다. 어려웠다. 아이디어는 금방 떠올렸지만 구현하는데 생각보다 오래걸렸다.

이전에 실패했던 문제를 다시 푼거였는데도 어려웠다.

## 나의 접근 방법
---
이 문제는 생각보다 주어지지 않아서 숨겨진(?) 조건이 있었다.

> 다른 배양액이 뿌려졌었던 곳이면 퍼져나가지 않는다.  
> (쉽게 말하면 오염됐던 땅에는 배양액이 퍼져나가지 않음)

우선 백트래킹을 위한 세팅을 해준다.  
입력을 통해서 배양액을 뿌릴 수 있는 좌표를 canUse에 저장해두었다.   
어떤 종류의 배양액을 뿌릴지 next_permutation로 정할 것이다.   
따라서, baeYang 벡터에 G, R, Z를 개수만큼 넣어주었다.  
'Z'의 경우 아무것도 뿌리지 않는다의 경우이다.

```cpp
int canUseSize = canUse.size();
for(int i=0; i<g; i++) baeYang.push_back('G');
for(int i=0; i<r; i++) baeYang.push_back('R');
for(int i=0; i<canUseSize - g - r; i++) baeYang.push_back('Z');
```

그림을 보면 이해가 좀 더 쉬울 것이다.

![](images/2024-07-02-BOJ-18809-1.png)

이런식으로 배양액을 뿌릴곳이 정해졌다면 find()함수로 BFS를 돌린다.

BFS를 돌리기 위해선 하나의 배열이 더 필요하다.  

> `pair<int, char> baeYangArr[51][51] = {};`

배양액이 도착한 시간과 어떤 배양액이 도착했는지 기록하는 배열이다.

baeYang 벡터에 있는 배양액을 기준으로 초기화를 해주고 queue에 넣어준다.   
'Z'인 경우 배양액을 뿌리지 않는 경우이기 때문에 건너뛰어준다.

```cpp
queue<tuple<int, int, char>> q;
for(int i=0; i<baeYang.size(); i++){
	if(baeYang[i] != 'Z') {
        cx = canUse[i].first; cy = canUse[i].second;
        baeYangArr[cx][cy] = {1, baeYang[i]};
        q.push({cx, cy, baeYang[i]});
    }
}
```

다음으로, 일반적인 BFS와 동일하게 좌표 유효성 체크, visited 체크 등을 한다.   
하지만 이 문제에서는 다양한 조건이 생긴다.  
1. **도착한 시간**도 같이 체크해주어야한다.
2. visited라도 **같은 시간에 도착한 다른 배양액**이라면 꽃을 피워야한다.
3. **가려는 곳이 꽃을 피운자리**라면 건너뛴다.
4. Queue에서 pop 했지만 **꽃이 핀 자리**라면 건너뛴다.
	- G가 퍼진 후에 Queue에 들어왔는데 이후에 R이 도착해서 꽃을 피운 경우
	- R이 퍼진 후에 Queue에 들어왔는데 이후에 G가 도착해서 꽃을 피운 경우

이러한 방식으로 여러번 BFS를 돌리며 최대값을 구하면 답이 나오게 된다.


## 코드
---
편의를 위해 BFS를 돌릴 때는 아래와 같이 define하여 코드를 짰다.
- first -> time
- second -> bae

```cpp
#include <bits/stdc++.h>
using namespace std;

#define time first
#define bae second

int n, m, g, r;
int cx, cy, nx, ny;
char cc;
int arr[51][51];
vector<pair<int, int>> canUse;
vector<char> baeYang;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0 , 1};
int answer = 0;

void find(){
    pair<int, char> baeYangArr[51][51] = {};

	//초기화
    queue<tuple<int, int, char>> q;
    for(int i=0; i<baeYang.size(); i++){
        if(baeYang[i] != 'Z') {
            cx = canUse[i].first; cy = canUse[i].second;
            baeYangArr[cx][cy] = {1, baeYang[i]};
            q.push({cx, cy, baeYang[i]});
        }
    }


    int result = 0;
    while(!q.empty()){
        tie(cx, cy, cc) = q.front(); q.pop();

		// 배양액이 퍼져서 Queue에 들어왔지만 이전 단계에서 꽃을 피운 경우
        if(baeYangArr[cx][cy].bae == 'F') continue;

        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];

            if(nx < 0 || nx > n || ny < 0 || ny > m) continue;
            // 호수이거나 같은 배양액이거나 꽃이 핀 곳이라면 continue
            if(arr[nx][ny] == 0) continue;
            if(baeYangArr[nx][ny].bae == cc || baeYangArr[nx][ny].bae == 'F') continue;

            if(baeYangArr[nx][ny].time == 0){  // 아무것도 없는 경우
                baeYangArr[nx][ny].time = baeYangArr[cx][cy].time + 1;
                baeYangArr[nx][ny].bae = cc;
                q.push({nx, ny, cc});
            }else{ // 상대 배양액이 있는 경우
                // 같은 시간에 도착한 경우
                if(baeYangArr[nx][ny].time == baeYangArr[cx][cy].time + 1){
                    baeYangArr[nx][ny].bae = 'F';
                    result++;
                }
            }
        }
    }
    answer = max(answer, result);
    
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); 

    cin >> n >> m >> g >> r;

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> arr[i][j];
            if(arr[i][j] == 2){
                canUse.push_back({i, j});
            }
        }
    }

    int canUseSize = canUse.size();
    for(int i=0; i<g; i++) baeYang.push_back('G');
    for(int i=0; i<r; i++) baeYang.push_back('R');
    for(int i=0; i<canUseSize - g - r; i++) baeYang.push_back('Z');

    sort(baeYang.begin(), baeYang.end());
    
    do{
        find();
    }while(next_permutation(baeYang.begin(), baeYang.end()));

    cout << answer;
    return 0;
}
```