## ⭐[문제링크](https://www.acmicpc.net/problem/2469)

## 문제요약
오늘 풀어본 문제는 "사다리 타기" 문제입니다.
![](https://velog.velcdn.com/images/jung-min-ju/post/b8cd4416-c562-4fd4-b2cf-f35c9443b503/image.png)

간단합니다!

>1. 입력값으로 **참가자의 수 K**가 주어집니다. 위의 그림 같은 경우 참가자의 수는 10명이겠죠?
 (각 참가자들은 무조건 A-K번째의 알파벳을 차례대로 부여받습니다.)
2. 그 후 **가로줄의 개수인 n**이 주어집니다. 위 그림에서의 n은 5가 됩니다.
3. 그 후 **원하는 참가자들의 순서**가 주어집니다. 우리는 사다리를 해당 순서대로 만들어야 합니다.

첫 번째 가로줄 부터 n 번째 가로줄 까지의 사다리 모양을 차례대로 입력 받습니다.
빈 칸은 *, 사다리가 있는 구역은 -로 주어집니다.

![](https://velog.velcdn.com/images/jung-min-ju/post/64c9f0d8-54aa-4c76-b9d6-6fb7f60f31ef/image.png)

입력값에서 지정한 도착순서로 해당 사다리에서 만들어질 수 있도록 감추어진 물음표 가로 줄을 구성해야 한다. 

그런데 어떤 경우에는 그 감추어진 가로 줄을 어떻게 구성해도 원하는 순서를 얻을 수 없는 경우도 있다.  이 경우에는  ‘x’(소문자 엑스)로 구성된 길이 k-1인 문자열을 출력해야 한다.

## 접근법
각 참가자들이 사다리를 출발할때, 위에서부터 출발한 경우와 아래의 해당 참가자가 지정된 도착순서에서부터 올라온 경우가 만날 수 있는지를 검사해주었습니다.
![](https://velog.velcdn.com/images/jung-min-ju/post/bfe2fcec-f689-43b4-aa91-491adbcb816d/image.png)

1. 각 알파벳은 인덱스 1부터 부여받습니다.
2. 지정된 도착순서의 알파벳들은 map 자료구조를 만들어 <1의 알파벳 인덱스, 도착순서> 로 저장합니다.
3. for문을 차례대로 돌며 첫 번째 알파벳부터 K번째 알파벳까지 진행합니다.
3-1. 위에서부터 각 알파벳을 사다리를 진행시킵니다. 
   -> 본인인덱스로 시작하여 오른쪽으로 가면 +1, 왼쪽으로 가면 -1
3-2. 아래쪽에서부터 사다리를 진행시킵니다. 
  -> 해당 알파벳의 도착순서에서 시작하여 오른쪽으로 가면+1, 왼쪽으로 가면 -1
  ![](https://velog.velcdn.com/images/jung-min-ju/post/77cae253-7f7a-45d8-b7da-34fe5d79b3fa/image.png)

  
해당 사다리가 성립될 수 없는 조건은, 위에서부터 탐색한 값과 아래에서부터 탐색한 값의 차에 대한 절대값이 2이상인 경우입니다.

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int row;
    static int column;
    static int K;
    static Map<Integer, Integer> inOrder = new HashMap<>();
    static Character ladder[][];
    static boolean isImpossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        column = Integer.parseInt(br.readLine()); 
        row = Integer.parseInt(br.readLine()); 
        String alphaInput = br.readLine();

        for (int i=1; i<=column; i++) {
            char alpha = alphaInput.charAt(i-1);
            inOrder.put(alpha-64, i);
        }

        ladder = new Character[row][column+1];

        for(int i=0; i<row; i++) {
            alphaInput = br.readLine();
            ladder[i][0] = '*';
            for(int j=1; j<column; j++) {
                ladder[i][j] = alphaInput.charAt(j-1);
            }
            ladder[i][column] = '*';
            if(ladder[i][1]=='?') K=i;
        }

        for(int j=1; j<column; j++){
            ladder[K][j]='*';
        }

        for(int i=1; i<=column; i++){
            int topCal = findTop(i, 0);
            int bottomCal = findBottom(inOrder.get(i), row-1);

            if(topCal==bottomCal) {
                ladder[K][topCal] = '*';
                continue;
            }

            if (Math.abs(topCal - bottomCal) >= 2) {
                isImpossible = true;
                break;
            }
            ladder[K][Math.min(topCal, bottomCal)] = '-';
        }

        if(isImpossible) {
            for(int i=1; i<column; i++){
                System.out.print("x");
            }
        }
        else {
            for(int i=1; i<column; i++){
                System.out.print(ladder[K][i]);
            }
        }

    }

    static int findTop (int location, int top) { 
        if(top == K) return location;

        //오른쪽으로 가는 경우
        if(ladder[top][location]=='-' ) {
            return findTop(location+1, top+1);
        }//왼쪽으로 가는 경우
        else if (ladder[top][location-1]== '-' ) {
            return findTop(location-1, top+1);
        } //이동할 사다리가 없는 경우
        else {
            return findTop(location, top+1);
        }
    }

    static int findBottom(int location, int bottom) {
        if(bottom == K) return location;

        //오른쪽으로 가기
        if(ladder[bottom][location]=='-') {
            return findBottom(location+1, bottom-1);
        }
        //왼쪽으로 가기
        else if( ladder[bottom][location-1]=='-'){
            return findBottom(location-1, bottom-1);
        }
        //이동할 사다리가 없는 경우
        else return findBottom(location, bottom-1);
    }
}
```
![](https://velog.velcdn.com/images/jung-min-ju/post/6e72718e-0ccc-48fd-8cfe-599ed34e6334/image.png)
