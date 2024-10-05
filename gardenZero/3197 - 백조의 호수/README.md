 [문제 링크](https://www.acmicpc.net/problem/3197)

## 알고리즘 분류
---
BFS 심화

## 알게된 점
---
아이디어는 바로 떠올렸다. 바로 풀었고 난이도에 비해서 아이디어만 떠올리면 쉽다고 생각했다.

자바로 처음 풀어봤다. 와우 자바 생각보다 귀찮다. (내가 귀찮게 푼 것도 있다.)

## 나의 접근 방법
---
우선 빙하를 녹이는 방법과 백조가 만날 수 있는지 판단하는 방법 총 2가지를 구해야한다.

두 방법 모두 간단하게 BFS로 구현할 수 있다.
1. 빙하를 녹이는 방법
	- Queue에 물을 넣고 BFS를 돌린다.
	- 빙하를 만나면 물('.')로 바꾸고 방문 처리를 한다.
2. 백조가 만나는지 확인하는 방법
	- Queue에 백조 하나를 넣고 다른 백조를 만나는지 확인한다.
	- 만나지 못하고 BFS가 끝나면 못만나는 것이다.

**흠...**

**이렇게 하면 당연히 실패한다!**

그 이유는 조건을 보면 알 수 있다. **최대 1500 x 1500 이기에 이렇게 했다가는 당연히 시간 초과가 발생**한다.

#### 빙하 녹이기
우선 쉬운 빙하를 녹이는 방법 부터 생각해보자. 빙하는 하루마다 녹는다. 그렇기 때문에 한번의 BFS로는 끝나지 않는다. 그렇다고 **모든 물을 BFS에 넣고 돌릴 수는 없다.** 당연히 시간초과가 발생하기 때문이다.

이를 해결하기 위해서 우선 visited 배열을 초기화 하지 않는다. 그리고 빙하가 녹은 자리만 큐에 다시 넣는다.

빙하가 **녹은자리는 따로 저장해뒀다가 BFS가 끝나면 다시 Queue에 넣는다.**

> 굳이 Set에 저장하지 않고 처음에 Queue의 원소 개수를 구한다음, 그 만큼만 for 문을 돌아도 된다.

#### 백조가 만나는지 판단하기
사실 여기서 핵심 로직이 모두 나온다. 빙하가 녹는거야 그냥 visited만 남겨둬도 중복적인 연산을 안할 수 있다. 하지만 **백조는 매번 만나는지 판단**해줘야 한다.

그냥 매번 BFS를 돌려서는 무조건 시간초과가 발생한다. 그래서 조금이라도 속도를 올리기 위해, 백조 하나에 대해서 BFS를 돌리는 것이 아닌 두마리를 동시에 돌리기로 하였다. 

두 마리가 만나는지 판단하기 위해서는 Node에 현재 어떤 백조인지 나타내는 것이 필요하다. 그래서 아래와 같이 백조에 swanNum을 추가해줬다.

```java
class Node {
    int swanNum;
    int x, y;

    public Node(int swanNum, int x, int y) {
        this.swanNum = swanNum;
        this.x = x;
        this.y = y;
    }
}
```

해당 swanNum을 통해 **서로 다른 swanNum을 가진 노드가 만나면** 백조가 만난다는 것을 알 수 있다.

😑 **하지만 이걸로는 부족하다!** 상대는 1500 x 1500 이니까!

나는 굳이 가본길을 또 가봐야하나 라는 생각이 들었다. 하지만, 빙하가 녹는 거랑은 다른 문제다. 녹은 자리를 Queue에 넣어주면 되지만 백조가 만나는지 판단하는건 그렇게 할 수 없다. 근데 생각보다 비슷한 방법을 떠올 릴 수 있다.

**막혔던 곳 부터 다시 시작해보는 것이다.**

똑같이 visited 배열을 재사용한다. 이번에는 빙하를 만나면 해당 위치를 따로 저장해뒀다가 다음번에 사용하는 것이다.  

아래의 코드를 살펴보자. visited 배열에서 0은 미방문, 1은 백조1, 2는 백조2를 뜻한다.

```java
static boolean check() {

	Queue<Node> lastPos = new LinkedList<>();

	while (!bq.isEmpty()) {
		Node cn = bq.poll();

		for (int i = 0; i < 4; i++) {
			int nx = cn.x + dx[i], ny = cn.y + dy[i];

			if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
			if (visited[nx][ny][0] == cn.swanNum) continue;
			//0이 아닌 애를 만난거면 다른 백조다!
			if (visited[nx][ny][0] != 0) return true;

			//만약 0을 만난거라면 벽인지 아닌지 구분
			//벽이라면 벽과 맞다은 부분이라고 현재 위치를 lastPos에 저장
			if (arr[nx][ny] == 'X') {
				lastPos.add(new Node(cn.swanNum, cn.x, cn.y));
			}
			//벽이 아니라면 방문처리하고 큐에 다시 넣어
			else {
				visited[nx][ny][0] = cn.swanNum;
				bq.add(new Node(cn.swanNum, nx, ny));
			}
		}
	}

	//bq는 전역으로 설정해둔 백조의 위치를 저장해두는 Queue이다.
	bq.addAll(lastPos);
	return false;
}
```

## 코드
---

```java
import java.io.*;
import java.util.*;

class Node {
    int swanNum;
    int x, y;

    public Node(int swanNum, int x, int y) {
        this.swanNum = swanNum;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            Node temp = (Node)obj;
            return swanNum == temp.swanNum && x == temp.x && y == temp.y;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return (Integer.toString(swanNum) + Integer.toString(x) + Integer.toString(y)).hashCode();
    }
}

public class Main {
    static int r, c;
    static char[][] arr;
    static int[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Node> bq = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[r][c];
        visited = new int[r][c][2];

        String input;
        int swanNum = 1;
        for(int i=0; i<r; i++){
            input = br.readLine();
            for(int j=0; j<c; j++){
                arr[i][j] = input.charAt(j);
                if(arr[i][j] == 'L') {
                    bq.add(new Node(swanNum, i, j));
                    water.add(new Node(-1, i, j));
                    visited[i][j][0] = swanNum++;
                    visited[i][j][1] = -1;
                }
                else if(arr[i][j] == '.') {
                    water.add(new Node(-1, i, j));
                    visited[i][j][1] = -1;
                }
            }
        }

        int result = 0;
        while(!check()) {
            melt();
            result++;
        }

        System.out.println(result);

    }

    static boolean check() {

        Set<Node> lastPos = new HashSet<>();

        while (!bq.isEmpty()) {
            Node cn = bq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cn.x + dx[i], ny = cn.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny][0] == cn.swanNum) continue;
                //0이 아닌 애를 만난거면 다른 백조다!
                if (visited[nx][ny][0] != 0) return true;

                //만약 0을 만난거라면 벽인지 아닌지 구분
                //벽이라면 벽과 맞다은 부분이라고 현재 위치를 lastPos에 저장
                if (arr[nx][ny] == 'X') {
                    lastPos.add(new Node(cn.swanNum, cn.x, cn.y));
                }
                //벽이 아니라면 방문처리하고 큐에 다시 넣어
                else {
                    visited[nx][ny][0] = cn.swanNum;
                    bq.add(new Node(cn.swanNum, nx, ny));
                }
            }
        }

        bq.addAll(lastPos);
        return false;
    }

    static void melt() {

        Set<Node> lastPos = new HashSet<>();

        while (!water.isEmpty()) {
            Node cn = water.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cn.x + dx[i], ny = cn.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny][1] == cn.swanNum) continue;

                if (arr[nx][ny] == 'X') {
                    lastPos.add(new Node(cn.swanNum, nx, ny));
                    arr[nx][ny] = '.';
                } else {
                    water.add(new Node(cn.swanNum, nx, ny));
                }
                visited[nx][ny][1] = cn.swanNum;
            }
        }

        water.addAll(lastPos);
    }
}
```

## 번외
---
사실 코드에서 볼 수 있듯이 나는 백조의 위치를 저장할 때 Set을 사용했다. 

그 이유는 하나의 위치에서 여러개의 벽을 만났을 때, 동일한 위치가 여러변 Queue에 들어가기 때문이다.

그래서 HashSet을 썼지만 **실제로 해보니 이게 더 느렸다.** (사실 이렇게 풀고 대박이다 너무 잘했다 라고 생각했다.) 안에서 검증하는 작업과 HashTable을 만들과 관리하는데 더 시간이 들었던것 같다. 그래도 하나의 테크닉이니 봐두면 좋을 것 같다.

우선 HashSet에 넣어 중복된 점을 생략하려면 `equals()`와 `hashCode()`가 재정의 되어있어야한다.

```java
class Node {
    int swanNum;
    int x, y;

    public Node(int swanNum, int x, int y) {
        this.swanNum = swanNum;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            Node temp = (Node)obj;
            return swanNum == temp.swanNum && x == temp.x && y == temp.y;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return (Integer.toString(swanNum) + Integer.toString(x) + Integer.toString(y)).hashCode();
    }
}
```

주의할 점은 `hashCode`를 재정의 할 때는 각 필드 값을 모두 String으로 파싱 후 더해서 해싱해야한다.

단순히 `Integer.toString(x+y).hashCode()`로 하게 된다면 어떻게 될까?

(3, 4)와 (4, 3)의 결과가 똑같아 진다. 이는 분명 다른 좌표인데 같다고 되어버리는 것이다. 따라서 `hashCode()`를 재정의 할 때는 **핵심 필드값을 모두 반영**해 만들어야한다.
