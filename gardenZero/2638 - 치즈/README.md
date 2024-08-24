[블로그 링크](https://gardenzeeero.github.io/posts/BOJ-2638/)

[문제 링크](https://www.acmicpc.net/problem/2638)

## 알고리즘 분류
---
BFS

## 알게된 점
---
생각보다 BFS라는걸 떠올리기가 쉽지 않았다.

외부공기와 내부공기를 판단할 방법이 떠오르지 않아 고생을 많이한 문제이다.

## 나의 접근 방법
---
우선 가장 큰 핵심은 외부공기와 내부공기를 판단하는 것이다.

처음에는 내부공기의 관점에서 해결하려고 노력했다.     
내부공기에서 주변을 살펴봤을 때 막혔는지 어떻게 판단하지 라는 생각을 많이 했다.

하지만 결론은, 외부 공기에 대한 의미를 곰곰히 생각해봐야했다.   
외부 공기란 **치즈밖에서 흘러들어온 공기**이다.

즉, **외부에서 시작된 공기**이다.

문제에서 테두리에는 치즈가 존재하지 않는다고 했기 때문에 **(0, 0)에는 치즈가 존재하지 않는다.**

따라서, **(0, 0)에서 BFS**를 돌리면 세가지 공간으로 구분된다.

1. 치즈
2. 외부공기
3. 치즈도 외부공기도 아닌 내부공기

그림을 보며 이해하면 더 쉬울 것이다.

![](images/2024-08-20-BOJ-2638-1.png)

그림에서 보다시피 (0, 0)에서 외부공기가 시작되고 BFS가 끝나면 녹을 치즈를 판단한다.

이를 코드로 나타내면 아래의 순서와 같다.
1. (0, 0)을 외부공기 큐에 넣는다.
2. 외부공기를 퍼트린 후 녹을 치즈를 판단한다.
3. 녹은 치즈를 외부공기 큐에 넣는다.
4. 만약 남은 치즈가 없다면 종료하고 있다면 2번으로 돌아간다.

코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int n, m, cx, cy, nx, ny;
bool isCheese[100][100];
bool isOut[100][100];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

queue<pair<int, int>> cq; //치즈큐
queue<pair<int, int>> aq; //공기큐

void spread(){

    // BFS를 통해 외부 공기 체크
    while(!aq.empty()){
        tie(cx, cy) = aq.front(); aq.pop();
        isOut[cx][cy] = true;  //치즈가 녹아 공기가 된 공간 처리

        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(isCheese[nx][ny] || isOut[nx][ny]) continue;

            isOut[nx][ny] = true;
            aq.push({nx, ny});
        }
    }
}

void check(){

    int cycle = cq.size();
    
    // 모든 치즈에 대해 외부공기 체크
    for(int i=0; i<cycle; i++){
        tie(cx, cy) = cq.front(); cq.pop();

        //외부 공기 체크
        int count = 0;
        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(isOut[nx][ny]) count++;
            if(count >= 2) break;
        }

	//녹을 치즈라면 상태 변경 후 외부공기 큐에 넣는다.
        if(count >= 2){
            isCheese[cx][cy] = false;
            aq.push({cx, cy});
            continue;
        }
        cq.push({cx, cy});
    }
}


int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n >> m;

	//입력
    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> input;
            if(input == 1) {
                isCheese[i][j] = true;
                cq.push({i, j});
            }
        }
    }
    
    isOut[0][0] = true;
    aq.push({0,0});
    int result = 0;
    while(!cq.empty()){
        spread();  //외부공기 퍼트리기
        check();   //없어질 치즈 찾아서 없애기
        result++;
    }

    cout << result;
    
    return 0;
}
```