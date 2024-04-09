---
title: "[C++] 백준 1261: 알고스팟"
date: 2024-03-13 +09:00
categories:
  - Study
  - Baekjoon
tags:
  - 백준
  - BFS
  - 다익스트라
---
[문제 링크](https://www.acmicpc.net/problem/1261)

## 알고리즘 분류
---
BFS, 다익스트라

## 알게된 점
---
다익스트라와 BFS를 좀 더 활용하는 계기가 되었음

정형화된 패턴은 아니었어서 좋았음

사실 생각해보면 우선순위 큐를 써서 전형적인 다익스트라를 써도 될 듯     
- 우선순위 큐에 의해 부순벽이 0인것 부터 모두 처리
- 부순 벽이 1..2..3 인 것 모두처리
- 우선순위 큐가 빌때까지 반복
와 같은 방법으로 구할 수 있을 것 같음

## 나의 접근 방법
---
처음에는 BFS로 풀려고 했음

dist 배열에 시작점 부터의 거리뿐만 아니라 벽을 깨부순 횟수도 적어 조건을 추가할 생각이었음   
벽을 부순 횟수가 적으면 queue에 추가하는 방식을 채택하려고 했음

그러다가 번뜩이는 아이디어가 떠오름!

그것은 바로 다익스트라를 활용하는 것이었음

dist 배열에 거리를 저장하는 것이 아니라 벽을 부순 횟수를 저장하는 방법임   
만약 빈방이라면 방을 옮기더라도 0을 더해주고 벽이라면 1을 더해주는 방식임   
만약 이전에 방문했던 방이라면 더 작은지 여부를 따진 후 push 해줌

그러면 큐가 비어서 while문을 빠져나간 시점에 (N, M)은 최소값이 되어있을 거임

그리고 값을 저장할 때는 if문으로 벽인지 체크하는 것이 아닌 방의 값(0 or 1)을 더해줌
(아주 좋은 방법인듯)

코드를 보면 더 이해가 쉬울 것 같음

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int n, m;
int dist[100][100];
string arr[100];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);    

    cin >> m >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++)
	        dist[i][j] = -1;
    }

    for(int i=0; i<n; i++) cin >> arr[i];

    queue<pair<int, int>> q;
    dist[0][0] = 0;
    q.push({0, 0});
    while(!q.empty()){
        int x, y;
        tie(x, y) = q.front(); q.pop();

        for(int i=0; i<4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(dist[nx][ny] != -1 && dist[nx][ny] <= dist[x][y] + (arr[nx][ny] - '0')) continue;
            //string으로 입력을 받았으므로 
            dist[nx][ny] = dist[x][y] + (arr[nx][ny] - '0');
            q.push({nx, ny});
        }
    }

    cout << dist[n-1][m-1];

    return 0;
}

```
