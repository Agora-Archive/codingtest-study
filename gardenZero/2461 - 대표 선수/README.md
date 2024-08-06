[문제 링크](https://www.acmicpc.net/problem/1202)

## 알고리즘 분류
---
투 포인터 확장

## 알게된 점
---
투포인터 감을 잘 못잡고 있었는데 풀고보니 투포인터였다.

투포인터 감을 좀 확실히 잡은 듯 하다.

## 나의 접근 방법
---
일단 다 체크해보지 않으면 어려울 것 같다는 생각이 들었다.

하지만 모든 조합을 해보면 1000 * 1000 의 조합이 나오고    
그 조합의 최대값 최소값을 구하려면 N번 체크해봐야한다.

곰곰히 생각해보면 결과,
**최댓값과 최솟값의 차이를 줄이려면 최솟값만 옮기면 된다**는 것을 알게되었다.

그래서 학급 별로 능력치를 정렬하였다.   
각 학급에서 선택된 학생의 인덱스를 저장하기위해 point 배열을 사용하였다.

차이를 줄이려면 최솟값을 옮겨야한다.

![](images/2024-07-30-BOJ-2461-1.png)

차이가 최소라면 정답을 업데이트한다.

만약 최솟값이 더이상 이동할 인덱스 없다면 종료한다.

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int n, m, input;
int arr[1001][1001];
int point[1001];
int minIdx, maxIdx;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n >> m;

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> arr[i][j];
        }
    }

    for(int i=0; i<n; i++) sort(arr[i], arr[i] + m);

    int result = 0x7f7f7f7f;
    while(true){
        int minVar = 0x7f7f7f7f, maxVar = -1;
        int minIdx;

        for(int i=0; i<n; i++){
            if(arr[i][point[i]] < minVar){
                minVar = arr[i][point[i]];
                minIdx = i;
            }
            if(arr[i][point[i]] > maxVar){
                maxVar = arr[i][point[i]];
            }
        }

        result = min(result, maxVar - minVar);

        point[minIdx]++;
        if(point[minIdx] >= m) break;
    }

    cout << result;
    return 0;
}
```