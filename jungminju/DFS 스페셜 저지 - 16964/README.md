오늘 가져온 문제는

[⭐ 문제링크](https://www.acmicpc.net/problem/16964)

대놓고 DFS 문제이다!

## 문제 요약

1. 양방향 트리와 예측된 DFS 탐색 후 순서가 주어진다.
2. 시작 정점은 항상 1이고, 예측된 순서가 맞는지 판단하는 문제이다.

## 접근법

처음에는 DFS로 만들 수 있는 모든 경로를 찾아낸 후, 입력값과 비교하려고 했다. 그러나 해당 문제는 O/X만 판단하면된다.

**즉 경로를 찾는 도중, 예측과 일치하지 않으면 중간에 코드를 끝낼 수 있다.**

그렇기에 다음과 같은 접근법으로 시도했다.
![](https://velog.velcdn.com/images/jung-min-ju/post/b6a1dd71-3d8e-483b-92d3-e1f44983f52b/image.png)

위 트리에 대해 검증할 입력값은 1 3 2 4 로 주어졌다.

나는 여기서 마지막임을 알 수 있도록 뒤에 -1을 추가로 넣어줄 것이다.

내 로직은 아래와 같다.
> 1. 인접 리스트를 만든다.
2. 검증할 입력값에서 항상 now, next를 +1씩 늘려가며 선정한다.
3. now 인접 리스트에 next가 있어야만 해당 순서가 옮음이 보장된다.
4. 만약 next가 -1 인 경우, 앞의 순서는 모두 올바르다는 뜻이기에 맞다고 판단한다.
---
예외 : now 노드가 leaf Node일 때, 해당 단계에서 next가 올바른 순서인지 알 수 없다. 
-> why ? 해당 경우는 방문하지 않은 노드를 가진 부모 노드로 돌아가야 알 수 있다.

예외에 대한 케이스는 위 그래프에서 검증할 입력값이 1 2 4 3인 경우이다.

* now = 1, next = 2
1의 인접 리스트는 [2, 3]이므로 2가 존재하므로 올바릅니다.
* now = 2, next = 4
2의 인접 리스트는 [4]인데 4가 존재하므로 올바릅니다.
* now = 4, next = 3
4의 인접 리스트는 [2]인데, 해당 2 노드는 이미 방문한 노드입니다.
-> 3이 올바른 순서인지 알기 위해선 방문하지 않은 노드를 가지고 있는 부모 노드로 이동해야 합니다.


## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static boolean [] visited;
    static List<Integer> [] list;
    static int [] target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = V-1; //spanning tree
        visited = new boolean[V+1];
        target = new int[V+1];
        list = new List[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<V; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
        target[V] = -1;

        boolean check = false;

        if(target[0]!=1) {
            System.out.println(0);
            return;
        }

        for(int i=0; i<E; i++) {
          check = dfs(target[i], target[i+1]);
          if(!check) break;
        }

        if(!check) System.out.println(0);
        else System.out.println(1);
    }


    static boolean dfs(int now, int next) {
        if (next == -1) return true;

        for (int neighbor : list[now]) {
            if (neighbor == next || (list[now].size() == 1 && visited[neighbor])) {
                visited[now] = true;
                return true;
            }
        }

        return false;
    }

}
```

![](https://velog.velcdn.com/images/jung-min-ju/post/463e58ea-d867-4d49-85e4-ce4b1e7925aa/image.png)

솔직히 잘 짠 코드인지는 모르겠다.

그래서 여러 코드를 알아보았으나 내 코드가 꼼수 없이 쓴 코드 중 가장 빠르고 메모리 효율도 좋았다.

평가 받고 싶어서 이번 스터디의 문제로 가져와보았다.