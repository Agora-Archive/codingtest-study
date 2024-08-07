⭐ [문제링크](https://www.acmicpc.net/problem/16139)

오늘은 이 문제를 들고 왔다!

## 문제요약
문제는 첫 줄에 주어진 문자열를 입력받고, 두번째 줄에는 질문의 개수을 입력받는다.

입력받았다면 N줄 만큼 입력을 받는데 첫번째 값은 찾으려는 문자이고, 두번째 값은 시작점, 세번째 값은 끝점이다.

start값과 end값 범위에서 찾으려는 문자가 몇개가 들어갔는지 확인해주는 문제이다.


## 접근법
문자열에 대한 알파벳에 대한 구간합을 먼저 구하고 알파벳과 구간이 주어졌을 때 출력해주었다.

## 코드

### ✔️알파벳에 대한 구간합 구하기
```java
        String target = br.readLine();

        int[][] A = new int[target.length()+1][26];

        for(int i=1; i<target.length()+1; i++){
            int value = target.charAt(i-1) - 'a';
            for(int j=0; j<A[i].length; j++){
                A[i][j] = A[i-1][j] + (value == j ? 1 : 0);
            }
        }
```
* 주어진 문자열 + 1 길이와 알파벳마다 구간합을 다르게 만들기 위해 26 길이의 2차원 배열을 만들었다.
* 문자열을 한글자씩 - ‘a’연산을 통해 인덱스를 구해주고 구간합을 구했다.

### ✔️출력
```java
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine() , " ");
            int value = st.nextToken().charAt(0) - 'a';
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(A[b+1][value] - A[a][value]).append("\n");
        }

        System.out.println(sb);
```
알파벳 value와 구간 a, b가 주어졌을 때, 구간합은 A[b + 1][value] - A[a][value]를 구하면 구간합을 구할 수 있다.

### ✔️전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();

        int[][] A = new int[target.length()+1][26];

        for(int i=1; i<target.length()+1; i++){
            int value = target.charAt(i-1) - 'a';
            for(int j=0; j<A[i].length; j++){
                A[i][j] = A[i-1][j] + (value == j ? 1 : 0);
            }
        }

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine() , " ");
            int value = st.nextToken().charAt(0) - 'a';
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(A[b+1][value] - A[a][value]).append("\n");
        }
        System.out.println(sb);

    }
}
```

![](https://velog.velcdn.com/images/jung-min-ju/post/102a36bb-9f9e-446a-96c2-4407ed2ea0ba/image.png)
