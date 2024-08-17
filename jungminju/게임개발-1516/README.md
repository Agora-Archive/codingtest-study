오늘 준비해온 문제는

🤓 [문제링크](https://www.acmicpc.net/problem/1516) 

게임 개발 입니다!

## 1. 문제 요약

건물을 지어야 하는데, 해당 건물을 짓기 위해선 선행 건물들을 모두 지어야 합니다.
모든 건물의 건축 최소 시간을 구하는 문제입니다.
<span style="color: coral; font-weight: bold;">특이점은, 건물은 동시에 지을 수 있다는 것 입니다.</span>


## 2. 접근법
처음엔 문제 이해를 제대로 못하고 엥 그냥 dp 아닌가 dfs 아닌가? 싶었지만 역시 아니었습니다.

그래서 다음과 같이 접근하였습니다.

1. 선행 건물들을 dp로 탐색합니다.
2. 선행 건물들 중 가장 오랜 시간이 걸리는 시간을 찾습니다.
3. 2번에서 찾은 시간 + 현재 건물 짓는데 걸리는 시간 을 해당 건물의 최소 시간으로 판단합니다.

해당 문제는 "동시에 지을 수 있다" 라는 조건이 걸려 있기 때문에 선행 건물들이 독립적인 순서로 지어진다 한들 가장 오랜 시간이 걸리는 하나의 선행건물만 찾아도 문제를 해결할 수 있습니다.

```java

public class Main {

    static int N;
    static int[] time;
    static int[] minTime;
    static List<Integer>[] buildingOrder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        minTime = new int[N+1];
        buildingOrder = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            buildingOrder[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int order = Integer.parseInt(st.nextToken());

            while (order != -1){
                buildingOrder[i].add(order);
                order = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            System.out.println(findLeastTime(i));
        }
    }

    static int findLeastTime(int num) {

        //방문된 건물(이미 최소 시간이 확정된 건물) 이라면 바로 최소 시간 return
        if (visited[num]) {
            return minTime[num];
        }

        // 선행 건물들 중 가장 오래 걸리는 시간을 저장하기 위한 변수
        int maxTime = 0;

        // 선행 건물이 있을 경우, 선행 건물들 중 가장 오래 걸리는 시간을 찾는다.
        for(int pre : buildingOrder[num]){
            maxTime = Math.max(maxTime, findLeastTime(pre));
        }

        // 현재 건물의 최소 시간 = 선행 건물들의 최소 시간 중 최대값 + 현재 건물 건설 시간
        minTime[num] = maxTime + time[num];
        visited[num] = true;  // 방문 체크

        return minTime[num];
    }
}

```

![업로드중..](blob:https://velog.io/17ea96a8-48ee-40d2-9ee6-203d02a6408d)


까리뽕삼하게 풀 수 있었습니다.

그런데 해당 문제는 **위상 정렬**의 기본 문제라고 합니다. 몰랐기에 해당 개념을 정리해왔습니다.

[위상 정렬 개념](https://velog.io/@jung-min-ju/%EC%9C%84%EC%83%81-%EC%A0%95%EB%A0%AC)


----

## 3. 위상정렬 ver. 풀이법

### 3-2. 해설
주요한 변수는 다음과 같습니다.
![](https://velog.velcdn.com/images/jung-min-ju/post/fd024a62-25b7-49b0-b9f3-016da7c169a2/image.png)

선행 건물들 중 최대 건축 시간만이 저장되는 이유는 

**건물은 동시에 지울 수 있다**

라는 조건으로 인하여, 가장 오랜 시간동안 짓는 단 하나의 선행 건물의 건축 시간은
사실상 선행 건물들을 짓기 위한 최소 시간입니다.

----

### 3-1. 전체 코드

```java

public class Main {

    static int N;
    static int[] time;
    static int[] inDegree;
    static int[] result;
    static List<Integer>[] buildingOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        inDegree = new int[N+1];
        result = new int[N+1];
        buildingOrder = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            buildingOrder[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            result[i] = time[i]; // 각 건물의 초기 시간 설정

            int order = Integer.parseInt(st.nextToken());

            while (order != -1){
                buildingOrder[order].add(i);  // order를 선행 건물로 하는 i를 추가
                inDegree[i]++;  // i의 진입 차수를 증가시킴
                order = Integer.parseInt(st.nextToken());
            }
        }

        topologicalSort();

        for(int i=1; i<=N; i++){
            System.out.println(result[i]);
        }
    }

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        // 진입 차수가 0인 건물들을 큐에 추가
        for(int i=1; i<=N; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : buildingOrder[current]) {
                // 선행 건물이 끝난 후의 최대 시간을 계산
                result[next] = Math.max(result[next], result[current] + time[next]);

                // 다음 건물의 진입 차수를 줄임
                inDegree[next]--;

                // 진입 차수가 0이 되면 큐에 추가
                if(inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}

```

----

### 3-2. 초기화

```java
for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            result[i] = time[i]; // 각 건물의 초기 시간 설정

            int order = Integer.parseInt(st.nextToken());

            while (order != -1){
                buildingOrder[order].add(i);  // order를 선행 건물로 하는 i를 추가
                inDegree[i]++;  // i의 진입 차수를 증가시킴
                order = Integer.parseInt(st.nextToken());
            }
        }

```

해당 코드에서 result 배열에는 각 건물의 초기 시간으로 설정해둡니다.
그 후 inDegree[] 역시 이때 초기화 합니다.


----

### 3-3. 위상정렬

메인이 되는 부분입니다.

>1. 진입차수가 0이라면 큐에 넣는다.
2. 큐가 빌 때까지 result[]을 업로드 한다.
3. 탐색된 노드에 대한 진입차수는 줄인다


```java

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        // 진입 차수가 0인 건물들을 큐에 추가
        for(int i=1; i<=N; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : buildingOrder[current]) {
                // 선행 건물이 끝난 후의 최대 시간을 계산
                result[next] = Math.max(result[next], result[current] + time[next]);

                // 다음 건물의 진입 차수를 줄임 (current의 진입 수를 없애는 것과 동일)
                inDegree[next]--;

                // 진입 차수가 0이 되면 큐에 추가
                if(inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
   }

```

여기서 헷갈리는 부분은 밑의 코드입니다.

```java
result[next] = Math.max(result[next], result[current] + time[next]);

```

* **result[next]**
:  현재까지 계산된 next 건물이 완성되는 최소 시간입니다.
-> 해당 값은 아직 선행 건물들의 최대 시간을 조사 중인 상태

* **result[current] + time[next]**
: current 건물을 짓고 난 후 next 건물을 짓는 데 걸리는 새로운 시간입니다.
-> 즉 current는 next 건물에 대한 선행 건물임


그렇기에 둘 중 더 큰 값을 비교해서 result[next]에 넣어야 

**해당 next 빌딩의 선행 건물들 중 가장 최대의 건축 시간 + 본인 건물 짓는 시간을 더한 값**

 구할 수 있습니다.

----




![](https://velog.velcdn.com/images/jung-min-ju/post/d9d549ed-e5a7-4050-958c-390258bafd6d/image.png)



