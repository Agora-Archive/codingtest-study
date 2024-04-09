# [Gold V] 계란으로 계란치기 - 16987

[문제 링크](https://www.acmicpc.net/problem/16987)

## 알고리즘 분류
브루트포스, 백트래킹

## 알게된 점
브루트포스, 백트래킹을 알고 풀어도 먼가 구현이 애매했음

처음에 자료를 저장할 때 수정할 경우도 생각해서 저장해야겠음

백트래킹 구조를 익히는데 좀 더 집중해야겠음

## 나의 접근 방법
그냥 간단하게 맨 왼쪽부터 재귀적으로 모든 계란에 한번씩 쳐보면 된다고 생각함

당연히 해당 계란이 다른 계란에 의해서 깨져 있으면 패스임 (2번 조건)

가장 고난이었던 부분은 내구성이랑 무게를 저장하는 방법이었음    
처음에는 무게는 바뀌지 않으니까 무게를 Key값으로 하여 map을 써야겠다고 생각함

하지만 같은 무게의 계란이 존재 할 수 있었기 때문에 불가능 했음

그래서 그냥 pair를 활용하는 것이 가장 간단할 것이라고 생각함    
(사실 pair를 좌표 다룰때만 많이 써서 이런곳에서 쓸 생각을 못했음 허허)

뭐 사실 어려운건 없고 재귀적인 구조를 잘 이해하고 있다면     
아래의 그림과 코드만 보고도 이해가 가능 할 것 같음

![](images/2024-02-19-BOJ-16987.png)

코드의 진행은 아래와 같음
1. 0번 계란(now 매개변수로 전달)부터 재귀함수에 들어감
2. 0번 계란으로 0번 계란부터 모두 박치기 해봄 (당연히 0번으로 0번을 박치기 할 순 없으니 패스함)
3. 내구도를 변화 시키고 now+1을 재귀함수에 전달함 (당연히 2번단계에서 계란 하나 박치기 하면)
4. 1번 계란(now+1 된 계란)으로 0번 계란 부터 모두 박치기 해봄 (물론 계란이 깨져있으면 패스함)
5. 2번단계~~~ 와 같은 흐름으로 맨마지막 계란까지 진행함
6. check 함수를 통해 깨진 계란의 수를 체크함

여기서 주의할 점이 있는데    
now계란이 내구도가 남아있지만 
<b>다른 계란이 모두 깨져있어 재귀를 들어가지 못하는 경우임</b>

그래서 flag 변수를 통해서 해당 경우 recursive(now+1)에 진입할 수 있게 만들어 줌    
(now가 n이 되어야 깨진 계란 수를 체크하기 때문)
## 코드
```cpp
#include <bits/stdc++.h>
using namespace std;
#define s first
#define w second

pair<int, int> arr[8];
bool isused[8] = {false, };
int n;
int result = 0;

//깨진 계란 체크
int check(){
    int temp = 0;
    for(int i=0; i<n; i++){
        if(arr[i].s <= 0) temp++;
    }
    return temp;
}

void recursive(int now){
    bool flag = false;

    //마지막 계란까지 온 경우
    if(now == n){
        result = max(result, check());
        return;
    }

    if(arr[now].s <= 0){    //들고 있는 계란의 내구도가 없는 경우
        recursive(now+1);
    }else{
        for(int i=0; i<n; i++){     //들고있는 계란으로 다른 계란 한번씩 쳐보기
            if(arr[i].s > 0 && i != now){
                arr[now].s -= arr[i].w;
                arr[i].s -= arr[now].w;
                recursive(now+1);
                arr[now].s += arr[i].w;
                arr[i].s += arr[now].w;
                flag = true;
            }
        }
    }

    //해당 계란으로 다른 계란을 한번도 안친 경우
    if(!flag){
        recursive(now+1);
    }
}

int main(){
    cin >> n;
    for(int i=0; i<n; i++){
        cin >> arr[i].s >> arr[i].w;
    }

    recursive(0);

    cout << result;
    return 0;
}
```
