| 시간 제한 | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| --- | --- | --- | --- | --- | --- |
| 2 초 | 128 MB | 265205 | 102388 | 60937 | 37.368% |

## 문제

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

## 입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

## 출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

## 예제 입력 1

```
4 5 1
1 2
1 3
1 4 
2 4
3 4

```

## 예제 출력 1

```
1 2 4 3
1 2 3 4

```

## 예제 입력 2

```
5 5 3
5 4
5 2
1 2
3 4
3 1

```

## 예제 출력 2

```
3 1 2 5 4
3 1 4 2 5

```

## 예제 입력 3

```
1000 1 1000
999 1000

```

## 예제 출력 3

```
1000 999
1000 999
```

## 선정 이유

DFS와 BFS가 뭔지는 알고 있지만 BFS의 경우엔 직접 구현해본 적은 없기에 풀어보면 좋을 것 같다는 친구의 추천으로 선정하게 되었다.

## 아이디어

입력 받는 거야 그냥 N, M, V(탐색 시작 vertex)를 입력 받고 

간선의 개수만큼 입력 받아 그래프를 구성해준 다음 

DFS, BFS 탐색 함수를 각각 만들어서 버퍼에 넣어줄 생각이다.

- DFS 구현

그래서 기억도 자료구조에서 배운 코드의 기억을 더듬어 보며 필기노트도 보고 하며 DFS코드를 만들었는데… 

뭔가 잘못된 것인지 탐색 순서가 어떨 땐 정확하고 어떨 땐 반대로 나온다.

```java
import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    static LinkedList<Integer> []list;
    static boolean []visited;
    static int n, v;
    static StringBuilder sb = new StringBuilder();//출력해 줄 버퍼
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String []str= br.readLine().split(" ");
        n = Integer.parseInt(str[0]);//정점의 개수
        int m = Integer.parseInt(str[1]);//간선의 개수
        v = Integer.parseInt(str[2]);//시작할 V의 번호
        visited = new boolean[n+1];
        list = new LinkedList[n+1];
        for(int i=1; i<=n; i++){//연결 리스트에 정점 초기화
            list[i]= new LinkedList();
        }
        while((m--)!=0){// 입력을 받아 간선 연결
            String []input= br.readLine().split(" ");
            int v1 =Integer.parseInt(input[0]);
            int v2 =Integer.parseInt(input[1]);
            list[v1].add(v2);
            list[v2].add(v1);
        }
        DFS(v);
        System.out.print(sb);
    }
    static void DFS(int start){
        visited[start]= true;
        sb.append(start+" ");
        Iterator<Integer> it = list[start].listIterator();
        while(it.hasNext()){
            int n = it.next();
            if(!visited[n]){
                DFS(n);
            }
        }
    }
}
```

그 이유를 고민해본 결과 정렬되지 않은 데이터가 들어오기에 그렇게 된다는 것을 알게 되었다.

그럼 간선이 다 들어갔을 때 각 연결 리스트를 정렬해주면 된다.

근데 이렇게 했을 때 시간이 꽤나 많이 걸릴 것 같지만 일단은 진행해주겠다.

결과는 잘 나온다.

- BFS 구현

<BFS 너비우선탐색>

이름은 너무 익숙하고 무슨 개념인지는 알지만 실제로 구현해본 적은 없기에 막막하지만 해보겠다.

일단 너비이므로 같은 레벨에 존재하는 vertex들을 먼저 다 탐색을 해야 한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static LinkedList<Integer> []list;
    static boolean []visited;
    static int n, v;
    static StringBuilder sb = new StringBuilder();//출력해 줄 버퍼
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String []str= br.readLine().split(" ");
        n = Integer.parseInt(str[0]);//정점의 개수
        int m = Integer.parseInt(str[1]);//간선의 개수
        v = Integer.parseInt(str[2]);//시작할 V의 번호
        visited = new boolean[n+1];

        list = new LinkedList[n+1];
        for(int i=1; i<=n; i++){//연결 리스트에 정점 초기화
            list[i]= new LinkedList();
        }
        while((m--)!=0){// 입력을 받아 간선 연결
            String []input= br.readLine().split(" ");
            int v1 =Integer.parseInt(input[0]);
            int v2 =Integer.parseInt(input[1]);
            list[v1].add(v2);
            list[v2].add(v1);
        }
        for(int i=1; i<=n; i++){//연결 리스트에 정점 초기화
            Collections.sort(list[i]);
        }
        DFS(v);
        sb.append("\n");//줄 구분
        visited = new boolean[n+1];//배열 초기화
        BFS(v);
        System.out.print(sb);
    }
    static void DFS(int start){
        visited[start]= true;
        sb.append(start+" ");
        Iterator<Integer> it = list[start].listIterator();
        while(it.hasNext()){
            int n = it.next();
            if(!visited[n]){
                DFS(n);
            }
        }
    }
    static void BFS(int start){
        Queue<Integer> q = new LinkedList<>();
        visited[start]= true;
        q.add(start);
        while(q.size()!=0){
            start=q.poll();
            sb.append(start+" ");
            Iterator<Integer> it1 = list[start].listIterator();
            while(it1.hasNext()){
                int n = it1.next();
                if(!visited[n]){
                    visited[n]= true;
                    q.add(n);
                }
            }
        }
    }
}
```

결과는 다행히 잘나왔다.
