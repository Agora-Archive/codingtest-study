## 알고리즘 분류
---
벨만포드

## 알게된 점
---
벨만 포드라는 알고리즘의 존재는 알고 있었지만 사용할 줄은 몰랐다.   
이번 기회에 확실하게 복습해서 좋았다.

기본적인 알고리즘은 잘 알지만 조금만 활용해야하면 문제가 생긴다.    
좀 더 다양한 시각으로 보도록 노력해야겠다.

## 나의 접근 방법
---
처음에는 모든 지점에 있어 갔다가 돌아오는 최단거리를 구해봐야한다고 생각했다.

하지만 최단거리를 구하기 위한 다익스트라와 플로이드는 이번 문제에는 사용하지 못한다.    
- 다익스트라: 음수 간선을 처리하지 못함
- 플로이드: 음수여도 되지만 사이클이 있으면 안됨

이번 문제는 **벨만 포드 알고리즘**을 사용해야한다.     
참고로, 시간복잡도는 O(VE)이다.

벨만 포드 알고리즘은 **한 지점에서 다른 지점까지의 최단거리**를 구할 수 있다.    
또한, **음수 사이클이 존재하는지 체크**할 수 있다.

음수 사이클이 존재한다는 것은 문제에서 요구하는 조건을 충족하는 것이다.
(사이클 내에서 시간이 줄어들며 돌아오는 노드가 존재한다는 것이니까)

하지만, **그냥 벨만포드를 사용하기에는 문제**가 있다.

그 이유는 아래의 그림을 보면 알 수 있다.

![](images/2024-08-04-BOJ-1865-2.png)

만약 **1번 노드를 출발점**으로 잡는다고 가정하자.

만약 2-3-4 사이클에서는 음수 사이클이 생기지 않고,    
6-7-8 사이클에서 음수 사이클이 생기면 어떻게 될까?

기본적인 벨만 포드 알고리즘에서는 **음수 사이클이 생기는 지 판단**하지 못한다.

문제에서는 아래의 두 조건이 주어지지 않는다.
- 출발점이 어디인지
- 모든 그래프가 연결되어있는지
따라서, **모든 지점을 시작점으로 잡고 벨만 포드를 돌려봐야한다**고 생각했다.

### 실패코드
---
```cpp
bool belman(int start){
    fill(dist + 1, dist + n + 1, INF);
    dist[start] = 0;

	//n-1개의 간선을 통해 최단거리 구하기
    int tempStart, tempEnd, tempTime;
    for(int i=0; i<n-1; i++){
        bool updated = false;
        for(auto val : road){
            tempStart = val.first.first; 
            tempEnd = val.first.second; 
            tempTime = val.second;
            
            if(dist[tempStart] == INF) continue;
            if(dist[tempStart] + tempTime < dist[tempEnd]) {
                dist[tempEnd] = dist[tempStart] + tempTime;
                updated = true;
            }
        }
        if(!updated) break;
    }

	//음의 사이클 판단
    bool flag = false;
    for(auto val : road){
        tempStart = val.first.first; 
        tempEnd = val.first.second; 
        tempTime = val.second;

        if(dist[tempStart] == INF) continue;
        if(dist[tempStart] + tempTime < dist[tempEnd]) flag = true;
    }

    return flag;
}

```

이경우, 시간복잡도는 O(V\*VE)가 된다.

조건을 따져보면 최악의 경우, 5 \* 500 \* 500 \* 2500 이 된다.    
즉, **시간초과가 나게된다.**

### 성공 방법
---
원래 벨만 포드 알고리즘에서 출발점은 하나이다.     
이는 각 점에 대한 최단경로 파악을 위해서 설정한 것이다.    
이 문제에서는 최단경로에 대한 정답을 요구하지 않는다.   

따라서, **출발점을 여러개로 잡아주면 된다.**    
출발점을 여러개 잡아도 **음의 사이클을 판단하는데에는 문제가 없기 때문**이다.    

즉, `if(dist[tempStart] == INF) continue;` 를 삭제해주면 된다.

꼬이는거 아닌가 라고 생각할 수 있다.    
하지만 음의 사이클을 판단하는 원리를 생각해보면 알 수 있다.     

어떤 출발 노드에서 출발하든 **N-1개의 간선을 사용한 후**에 dist 배열의 변화가 생기면 안된다.     

**이미 해당 간선을 지나갈 수 있는 출발 노드가 해당 간선을 지나갔기 때문이다.**
(이해가 안된다면 이 말을 곱씹어 보자)

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;
int dist[501];
map<pair<int, int>, int> road;
int tc, n, m, w, s, e, t;
int INF = 0x7f7f7f7f;

bool belman(){
    fill(dist + 1, dist + n + 1, INF);

    int tempStart, tempEnd, tempTime;
    for(int i=0; i<n-1; i++){
        bool updated = false;
        for(auto val : road){
            tempStart = val.first.first; 
            tempEnd = val.first.second; 
            tempTime = val.second;
            
            if(dist[tempStart] + tempTime < dist[tempEnd]) {
                dist[tempEnd] = dist[tempStart] + tempTime;
                updated = true;
            }
        }
        if(!updated) break;
    }

    //음의 사이클 판단
    bool flag = false;
    for(auto val : road){
        tempStart = val.first.first; 
        tempEnd = val.first.second; 
        tempTime = val.second;

        if(dist[tempStart] + tempTime < dist[tempEnd]) flag = true;
    }

    return flag;
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> tc;
    while(tc--){
        cin >> n >> m >> w;

        //입력받아 양방향으로 저장, 더 짧은 경로가 나오면 업데이트
        for(int i=0; i<m; i++){
            cin >> s >> e >> t;
            auto it = road.find({s, e});
            if(it != road.end() && (*it).second < t) continue;
            road[{s, e}] = t; road[{e, s}] = t;
        }

        //웜홀은 단방향 음수간선으로 받기
        for(int i=0; i<w; i++){
            cin >> s >> e >> t;
            t = -t;
            auto it = road.find({s, e});
            if(it != road.end() && (*it).second < t) continue;
            road[{s, e}] = t;
        }

        if(belman()) cout << "YES" << "\n";
        else cout << "NO" << "\n";

        road.clear();
    }

    return 0;
}
```

#### Reference
---
[벨만 포드 알고리즘 설명](https://innovation123.tistory.com/132)
