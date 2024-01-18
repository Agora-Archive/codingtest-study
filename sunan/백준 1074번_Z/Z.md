| 시간 제한 | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| --- | --- | --- | --- | --- | --- |
| 0.5 초 (추가 시간 없음) | 512 MB | 74393 | 29894 | 22375 | 40.746% |

## 문제

[1074번: Z](https://www.acmicpc.net/problem/1074)

한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.

!https://u.acmicpc.net/21c73b56-5a91-43aa-b71f-9b74925c0adc/Screen%20Shot%202020-12-02%20at%208.09.46%20AM.png

N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.

다음 예는 22 × 22 크기의 배열을 방문한 순서이다.

!https://u.acmicpc.net/adc7cfae-e84d-4d5c-af8e-ee011f8fff8f/Screen%20Shot%202020-12-02%20at%208.11.17%20AM.png

N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.

다음은 N=3일 때의 예이다.

!https://u.acmicpc.net/d3e84bb7-9424-4764-ad3a-811e7fcbd53f/Screen%20Shot%202020-12-30%20at%2010.50.47%20PM.png

## 입력

첫째 줄에 정수 N, r, c가 주어진다.

## 출력

r행 c열을 몇 번째로 방문했는지 출력한다.

## 제한

- 1 ≤ N ≤ 15
- 0 ≤ r, c < 2
    
    N
    

## 예제 입력 1

```
2 3 1

```

## 예제 출력 1

```
11

```

## 예제 입력 2

```
3 7 7

```

## 예제 출력 2

```
63

```

## 예제 입력 3

```
1 0 0

```

## 예제 출력 3

```
0

```

## 예제 입력 4

```
4 7 7

```

## 예제 출력 4

```
63

```

## 예제 입력 5

```
10 511 511

```

## 예제 출력 5

```
262143

```

## 예제 입력 6

```
10 512 512

```

## 예제 출력 6

```
786432
```

## 아이디어

우선 문제를 보면 2^n X 2^n 크기의 2차원 배열에서

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d8082087-3937-4e53-979c-1158c974cf3b/806f757b-a79e-43d2-9e22-beeeba328ecb/Untitled.png)

이런식으로 Z자를 그리며 순회하는 것을 볼 수 있다. 

만약 n이 3이라면

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d8082087-3937-4e53-979c-1158c974cf3b/1086244f-0e1b-45e7-b29a-70e120089798/Untitled.png)

전체를 4등분 하여 그안에서도 4칸에 한번의 z순회를 통해 순서대로 순회한다. 

이 문제의 시간을 보면 0.5초임을 확인할 수 있는데

처음엔 그냥 2차원 배열에 z순회 규칙에 맞게 방문 순번을 기입해두고 입력 받은 위치에 저장된 수를 출력하면 될 것이라 생각했다.

근데 시간이 0.5초 인지라 2^15 X 2^15 크기의 배열이 최대 크기의 배열인데 이렇게 하면 단순 계산으로 순번 삽입에만 1,073,741,824번의 연산 즉, 10억 이럼 절대 0.5초에 해결을 할 수가 없다.

그래서 한번 규칙이라도 있는지 생각을 해보았다.

출력해야 하는 것은 입력으로 받은 행과 열에 해당하는 수를 이 규칙으로 몇 번째로 순회 하는지 알아내는 것이다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d8082087-3937-4e53-979c-1158c974cf3b/bdcfb9c8-81b8-4325-81fd-fd5d818334e4/Untitled.png)

이걸 보면 만약 4행 4열의 수가 필요하다면 앞의 수들을 최대한 묶을 수 있는 만큼 묶어서

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d8082087-3937-4e53-979c-1158c974cf3b/9bb91449-bb86-4c55-b661-75b242b6b34e/Untitled.png)

각각의 순번은 구할 필요 없이 4,4 의 해당하는 칸의 순서를 알 수 있게 된다.

그렇다면 검정색의 작은 칸(4개 짜리)과 빨간 색(16개 짜리)의 개수를 더하고 그 다음 번에도 최대한 칸을 세서 구할 수 있는 만큼 구한 다음

z순회를 통해 순번을 찾아내면 될 것이다.

그래서 전반적인 알고리즘은 4개로 분할한 사각형들 중에서 순번을 구하지 않아도 되는 부분들은 제외하고 또 그 남은 사각형을 4등분 그런 식으로 반복하여 해당 좌표가 더 이상 나눠지지 않을때 까지의 수를 더해서 출력하면 되겠다.

## 풀이 과정

재귀로 4등분하는 코드를 작성해보았다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int [][] arr;
    static int [] target= new int[2];
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int r = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);
        int k= (int)Math.pow(2, n);
        arr= new int [k][k];
        target[1] = r;
        target[0] = c;
        Z(0,0,k);
    }
    static int cnt =0;
    public static int Z(int x, int y, int leng){
        // 4개로 나누었을때 해당 사각형에서 또 Z를 돌리는 방식으로 진행 해당 좌표가 나눌수없는 최소의 사각형이 될때까지
        if(leng==2){
            for(int i = x; i<x+2; i++){
                for(int j=y; j<y+2; j++){
                    cnt++;
                    if(i==target[0] && j==target[1]){
                        System.out.print(cnt-1);
                        return 0;
                    }
                }
            }
        }
        if(target[0] <=x+leng/2 && target[1] <=y+leng/2){//첫 사각형
            //System.out.print("좌상 ");
            return Z(x, y, leng/2);
        }
        else if(target[0] >=x+leng/2 && target[1] <=y+leng/2){// 우측 상단 사각형
            //System.out.print("우상 ");
            cnt+=(int)Math.pow((double) leng /2, 2);
            return Z(x+leng/2, y, leng/2);
        }
        else if(target[0] <=x+leng/2 && target[1] >=y+leng/2){// 좌측 하단 사각형
            //System.out.print("좌하 ");
            cnt+=(int)Math.pow((double) leng /2, 2)*2;
            return Z(x, y+leng/2, leng/2);
        }
        else if(target[0] >=x+leng/2 && target[1] >=y+leng/2){// 우측 하단 사각형
            //System.out.print("우하 ");
            cnt+=(int)Math.pow((double) leng /2, 2)*3;
            return Z(x+leng/2, y+leng/2, leng/2);
        }
        return 0;
    }
}
```

다른 것들은 다 결과가 나오는데 

마지막 테스트 케이스는 스택 오버 플로우가 난다.

근데 이럴 때엔 일단 내보고 어… 냈는데 메모리 초과라고 뜬다.

저 코드에서 문제가 있기에 여러가지 고치고 stack overflow문제를 해결했다.

그런데 문제 메모리 초과가 뜨는 이유가 뭘까 아래 코드에서

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {
    static int [][] arr;
    static int [] target= new int[2];
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int r = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);
        int k= (int)Math.pow(2, n);
        arr= new int [k][k];
        target[1] = r;
        target[0] = c;
        Z(0, 0, k);
    }
    static int cnt =0;
    public static void Z(int x, int y, int leng){
        // 4개로 나누었을때 해당 사각형에서 또 Z를 돌리는 방식으로 진행 해당 좌표가 나눌수없는 최소의 사각형이 될때까지
        if(leng<=2){
            for(int i = x; i<x+2; i++){
                for(int j=y; j<y+2; j++){
                    cnt++;
                    //System.out.println(j+" "+i);
                    if(i==target[0] && j==target[1]){
                        System.out.print(cnt-1);
                        exit(1);
                    }
                }
            }
        }
        if(leng/2==target[0] && leng/2 ==target[1]){
            cnt+=(int)Math.pow((double) leng /2, 2)*3;
            System.out.print(cnt);
            exit(1);
        }
        else if(leng!=0){
            if(target[0] <=x+leng/2 && target[1] <=y+leng/2){//첫 사각형
                //System.out.print("좌상 ");
                Z(x, y, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] <=y+leng/2){// 우측 상단 사각형
                //System.out.print("우상 ");
                cnt+=(int)Math.pow((double) leng /2, 2);
                Z(x+leng/2, y, leng/2);
            }
            else if(target[0] <=x+leng/2 && target[1] >=y+leng/2){// 좌측 하단 사각형
                //System.out.print("좌하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*2;
                Z(x, y+leng/2, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] >=y+leng/2){// 우측 하단 사각형
                //System.out.print("우하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*3;
                Z(x+leng/2, y+leng/2, leng/2);
            }
        }
    }
}
```

.

.

.

.

.

.

.

.

.

.

.

.

문제는 바로,,,,, 쓰지도 않으면서 만들어준 2차원 배열

그걸 제거해주고 if문에서 등호가 잘못 붙어 오류를 발생 시키는 걸 제외하고 나니까

 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {
    static int [] target= new int[2];
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int r = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);
        int k= (int)Math.pow(2, n);
        target[1] = r;
        target[0] = c;
        Z(0, 0, k);
    }
    static int cnt =0;
    public static void Z(int x, int y, int leng){
        // 4개로 나누었을때 해당 사각형에서 또 Z를 돌리는 방식으로 진행 해당 좌표가 나눌수없는 최소의 사각형이 될때까지
        if(leng<=2){
            for(int i = y; i<y+2; i++){
                for(int j=x; j<x+2; j++){
                    cnt++;
                    //System.out.println(j+" "+i);
                    if(j==target[0] && i==target[1]){
                        System.out.print(cnt-1);
                        return;
                    }
                }
            }
        }
        if(leng/2==target[0] && leng/2 ==target[1]){
            cnt+=(int)Math.pow((double) leng /2, 2)*3;
            System.out.print(cnt);
            exit(0);
        }

        else if(leng!=0){
            if(target[0] <x+leng/2 && target[1] <y+leng/2){//첫 사각형
                //System.out.print("좌상 ");
                Z(x, y, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] <y+leng/2){// 우측 상단 사각형
                //System.out.print("우상 ");
                cnt+=(int)Math.pow((double) leng /2, 2);
                Z(x+leng/2, y, leng/2);
            }
            else if(target[0] <x+leng/2 && target[1] >=y+leng/2){// 좌측 하단 사각형
                //System.out.print("좌하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*2;
                Z(x, y+leng/2, leng/2);
            }
            else if(target[0] >=x+leng/2 && target[1] >=y+leng/2){// 우측 하단 사각형
                //System.out.print("우하 ");
                cnt+=(int)Math.pow((double) leng /2, 2)*3;
                Z(x+leng/2, y+leng/2, leng/2);
            }
        }
    }
}
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d8082087-3937-4e53-979c-1158c974cf3b/af53486a-5ef4-415a-b0ed-603a3e781d44/Untitled.png)

성공

## 교훈

미처 까먹고 지우지 못한 위의 부분 코드 때문에 많은 시간을 날리게 될 수도 있으니 잘 확인하자. 그리고 재귀로 분할 정복법을 활용해서 푼 것 같은데 매우 뿌듯하다.
