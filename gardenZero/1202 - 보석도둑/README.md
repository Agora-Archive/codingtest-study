[문제 링크](https://www.acmicpc.net/problem/1202)

## 알고리즘 분류
---
그리디 + 이진탐색트리

## 알게된 점
---
multiset을 포함해 다양한 STL을 몰랐는데 이번에 정리해서 좋았다.

## 나의 접근 방법
---
처음 봤을 때는 knapsack 문제인가 생각했다.   
근데 가방에 하나 밖에 못넣는 다는 말을 보고 바로 깨달았다.   
**이건 그리디다.**

넣을 수 있는 보석은 딱 하나이기때문에 최대한 비싼 보석을 넣어야한다.

>비싼 보석부터 보면서 넣을 수 있는 가방이 있는지 체크해야한다.    
>넣을 수 있는 가방중에서도 가장 용량이 작은 가방을 선택해야한다.   

기본적으로 bag을 배열로 선언한 후 정렬하는 방법도 있다.   
하지만 이 방법으로 할 경우, **사용한 가방을 삭제하는 데 문제가 생긴다.**

**최악의 경우, 매번 O(n)의 시간이 걸릴 수 있다.**     
또는, 사용한 여부를 체크하는 boolean 배열을 선언해주어야 한다.(아래 사진 참고)

![](images/2024-07-23-BOJ-1202-2.png)
두 경우 모두 비효율 적이기 때문에 우리는 **multiset을 사용**할 수 있다.

multiset은 red_black tree를 기반으로 하기 때문에 **삽입, 삭제가 O(log n)**만에 가능하다.   
(set과 다른 점은 값의 중복이 가능하다, set은 Java의 TreeSet이랑 동일하다.)

또한, multiset을 사용할 경우 lower_bound, upper_bound가 사용가능하다.

>lower_bound : 이상의 개념 (크거나 같은 수 중 처음 나온 수)    
>upper_bound : 초과의 개녕 (큰 수 중 처음 나온 수)

두 메서드를 사용할 때는 주의 할 점이 있다.

![](images/2024-07-23-BOJ-1202-1.png)

두 경우 모두 m.end() 를 가리킬 수 있다는 것이다.    
이 경우, 출력시 문제가 생긴다. 따라서, 결과가 m.end()랑 다른지 체크해줘야한다.

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    pair<int, int> bosuk[300001];
    multiset<int> bag;

	//sort할 때 편하도록 가격을 pair.first로 입력받음
    for(int i=0; i<n; i++){
        cin >> bosuk[i].second >> bosuk[i].first;
    }
    sort(bosuk, bosuk+n);

    int input;
    for(int i=0; i<k; i++){
        cin >> input;
        bag.insert(input);
    }

    long long answer = 0;
    for(int i=n-1; i>=0; i--){
	    //넣을 수 있는 가방 중 가장 작은 것을 찾음
        auto it = bag.lower_bound(bosuk[i].second);  
        if(it == bag.end()) continue;   //없는 경우에 해당 보석은 못넣음
        bag.erase(it);
        answer += bosuk[i].first;
    }

    cout << answer; 

    return 0;
}
```