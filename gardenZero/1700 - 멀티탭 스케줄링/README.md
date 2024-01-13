# [Gold I] 멀티탭 스케줄링 - 1700

[문제 링크](https://www.acmicpc.net/problem/1700)

### 알고리즘 분류
그리디

### 알게된 점
그리디는 확실히 첫 아이디어가 중요한 것 같음   
틀렸다고 뜨면 아이디어가 자체가 틀린 것은 아닌지 의심하는 것도 좋음   
그리디가 아닐 확률도 있음..

### 나의 접근법
처음에는 쉽게 그리디임을 알고 쉽게 코드를 짰음
1. 이미 꽂혀있으면 패스하자
2. 안 꽂혀있는데 빈자리 있으면 그곳에 넣기
3. 자리도 없으면 꽂혀 있는거 중에 가장 적게 나올 예정인 것을 빼기

위와 같은 단계로 진행하기 위해 입력을 받을 때 빈도수를 저장해두었음   
당연히 테스트 케이스도 통과!

매우매우 쉽게 풀었다고 생각했으나 3%에서 틀렸습니다 발생    
분명 맞는데 이렇게 터무니 없게 틀렸다고 한적은 처음이라 매우 당황함

그러나 아이디어 자체가 잘못된 것이었음    
3번 단계에서 한번도 안나오는 플러그가 있으면 해당 알고리즘이 정상작동함   
하지만 꽂혀있는 플러그가 한 번씩이라도 나온다면 오류가 발생함

도저히 못풀겠어서 결국 검색해봄   
알고보니 해당 알고리즘은 운영체제 시간에 배우는 페이지 교체 알고리즘이라고 있다고 함

## Belady의 페이지 교체 알고리즘
Belady의 페이지 교체 알고리즘에는 다양한 종류가 있음
- FIFO : 가장 오래된 페이지 교체
- OPT : 앞으로 가장 오랫동안 사용되지 않을 페이지 교체 (최적)
- LRU : 최근에 사용되지 않은 페이지 교체 (현실적이면서 그나마 최적)
- LFU : 참조 횟수가 가장 자주 일어난 것을 교체
- MFU : 참조 횟수가 가장 많은 페이지 교체
- NUR : 최근에 사용하지 않은 페이지 교체

해당 문제는 Belady의 OPT 알고리즘을 이용한 것임   
Belady의 가장 최적의 알고리즘이라 할 수 있음   
하지만 페이지 사용 순서를 알아야한다는 점에서 실생활에선 쓸 수 없음

OPT 알고리즘은 위에서 볼 수 있듯이 가장 오랫동안 사용되지 않을 페이지를 교체하는 것임
이 문제에서 플러그를 페이지로 본다면 완전히 동일함

## OPT를 적용한 정답
3번 단계를 두개의 단계로 나누어 최종적으로 4가지 단계가 나옴
1. 이미 꽂혀있으면 패스하자
2. 안 꽂혀있는데 빈자리 있으면 그곳에 넣기
3. 앞으로 한번도 안쓰일 플러그 빼기
4. 그런 플러그가 없다면 꽂혀있는 것들 중 가장 늦게 사용될 플러그 빼기

해당 알고리즘을 코드를 완성하면 아래와 같음
```c++
#include <iostream>
#include <vector>
using namespace std;

int hole, action;
vector<int> plugged;
vector<int> turn;

bool check(int input, int index){
    for(int i=0; i<hole; i++){
        if(plugged[i] == input){
            return true;
        }
    }

    for(int i=0; i<hole; i++){
        if(plugged[i] == 0){
            plugged[i] = input;
            return true;
        }
    }

    int unplug=0, lastIdx=-1;
    for(int i=0; i<hole; i++){
        int firstIdx = 1000;
        for(int j = index+1; j<turn.size(); j++){
            if(plugged[i] == turn[j]){
                firstIdx = j;
                break;
            }
        }

        if(lastIdx < firstIdx){
            unplug = i;
            lastIdx = firstIdx;
        }
    }

    plugged[unplug] = input;
    return false;
}

int main() {
    cin >> hole >> action;
    for(int i=0; i<hole; i++){
        plugged.push_back(0);
    }


    int input, total = 0;
    for (int i = 0; i < action; i++) {
        cin >> input;
        turn.push_back(input);
    }

    for (int i = 0; i < action; i++) {
        if (!check(turn[i], i)){
            total++;
        }
    }

    cout << total;



    return 0;
}
```


## 수정 전 코드
```c++

#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int hole, action;
vector<int> plugged;
int electro[101] = {0, };

bool check(int input){
    for(int i=0; i<hole; i++){
        if(plugged[i] == input){
            return true;
        }
    }

    for(int i=0; i<hole; i++){
        if(plugged[i] == 0){
            plugged[i] = input;
            return true;
        }
    }

    int min = 0;
    for(int i=0; i<hole; i++){
         if(electro[plugged[i]] < electro[plugged[min]]){
            min = i;
        }
    }
    plugged[min] = input;
    return false;
}

int main() {
    cin >> hole >> action;
    for(int i=0; i<hole; i++){
        plugged.push_back(0);
    }
    queue<int> q;

    int input, total = 0;
    for (int i = 0; i < action; i++) {
        cin >> input;
        electro[input]++;
        q.push(input);
    }

    for (int i = 0; i < action; i++) {
        input = q.front();
        q.pop();
        electro[input]--;
        if (!check(input)){
            total++;
        }
    }

    cout << total;



    return 0;
}
```


