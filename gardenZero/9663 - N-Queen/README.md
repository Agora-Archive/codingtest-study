[문제 링크](https://www.acmicpc.net/problem/9663)

## 알고리즘 분류
백트래킹

## 알게된 점
백트래킹 이라는 것이 완전탐색의 한 종류임    
근데 가지치기를 하며 시간 복잡도를 줄이는 것이 핵심임    
그리고 다중 for문을 써야할 때 많이 사용함

백트래킹의 자세한 내용은 Algorithm 백트래킹 포스팅에서 알 수 있음

## 나의 접근법
처음부터 그냥 완전탐색 해보면 될 것 같았음    
어차피 퀸이 들어갈 수 있는 곳은 한정적이니 백트래킹으로 풀면 시간이 오래걸리지 않을 듯 함

기본적인 아이디어는 아래와 같다 (실패)
1. 해당 칸으로 이동할 수 있는 arr 2차원 배열을 선언 
	- (해당 2차원배열은 해당 칸을 지날 수 있는 퀸의 개수)
2. 2차원 배열에 퀸을 하나씩 박아가며 경우의 수를 체크
3. 퀸을 박을때 8가지 방향을 모두 돌며 2차원 배열에 +1씩 해줌
4. n개가 되면 result++를 하고 마지막에 result 출력

하지만 이렇게 하면 시간 초과가 나게 됨 가지치기를 더해야함    
그 이유는 다양한데 아래와 같음
1. n개의 퀸을 놓아야 하기 때문에 각 행은 무조건 하나의 퀸을 사용함
	- 해당 행에 퀸을 놓았으면 그 행은 체크해볼 필요가 없음
2. 퀸을 놓을 때 위쪽은 고려하지 않아도 됨
	- 어차피 위쪽에서 퀸을 놓을때 해당 칸은 배제됨

글로만 보면 어려우니 아래의 그림과 같이 보면 좋을 것 같음

![](images/2024-02-12-BOJ-9663.png)

## 풀이

1. 각 행마다 퀸을 하나씩 놓아봄
	- 만약 위의 그림처럼 arr값이 0이 아니면 놓지 못하고 넘어감 (가지치기)
2. 퀸을 놓을 때 왼쪽 대각아래, 아래, 오른쪽 대각아래를 순회하면 arr 값을 1 늘림
3. 퀸을 n개 놓으면 재귀를 탈출함
4. 퀸을 뺄때는 똑같이 왼쪽 대각아래, 아래, 오른쪽 대각아래를 순회하면 arr 값을 1 빼줌

arr를 bool값이 아닌 int 값으로 한 이유는 퀸을 뺄 때 알 수 있음   
퀸을 뺄때 bool값으로 건드리게 되면 다른 퀸이 해당 칸으로 이동하는 경우를 배제 시켜 버리기 때문임

예를 들어 위의 사진에서 파란 퀸을 뺄 때 바로 아래칸은 빨간 퀸때문에 true를 유지해야함

## 전체코드
```c++
#include <bits/stdc++.h>
using namespace std;

int n;
int result = 0;
int arr[15][15];
int dy[3] = {-1, 0, 1};

void check(int x, int y){
    arr[x][y]++;
    for(int i=0; i<3; i++){
        int nx = x, ny = y;
        while(true){
            nx += 1; ny += dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            arr[nx][ny]++;
        }
    }
}

void uncheck(int x, int y){
    arr[x][y]--;
    for(int i=0; i<3; i++){
        int nx = x, ny = y;
        while(true){
            nx += 1; ny += dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            arr[nx][ny]--;
        }
    }
}

void recursive(int count){
    if(count == n){
        result++; return;
    }
    for(int i=0; i<n; i++){
        if(!arr[count][i]){
            check(count, i);
            recursive(count+1);
            uncheck(count, i);
        }
    }
}


int main(){
    cin >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            arr[i][j] = 0;
        }
    }
    
    recursive(0);

    cout << result;

    return 0;
}

```