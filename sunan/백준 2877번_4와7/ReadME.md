## 노션링크
https://www.notion.so/2877-4-7-1a8ccbbcfb38420788091c047f20445e?pvs=4

# 2877번: 4와 7

| 시간 제한 | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| --- | --- | --- | --- | --- | --- |
| 1 초 | 128 MB | 2970 | 1677 | 1379 | 59.878% |
|  |  |  |  |  |  |

## 문제

창영이는 4와 7로 이루어진 수를 좋아한다. 창영이가 좋아하는 수 중에 K번째 작은 수를 구해 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에 K(1 ≤ K ≤ 109)가 주어진다.

## 출력

첫째 줄에 창영이가 좋아하는 숫자 중 K번째 작은 수를 출력한다.

## 예제 입력 1

```
1

```

## 예제 출력 1

```
4

```

## 예제 입력 2

```
2

```

## 예제 출력 2

```
7

```

## 예제 입력 3

```
3

```

## 예제 출력 3

```
44
```

## 문제 선정 이유

문제 이름이 제일 담백해서

### 걸린 시간

1시간 3분 42초

### 성공 여부

성공

## 문제 설명

이 문제는 시간 제한이 1초 인걸 보면 알겠지만 최대 10억 개의 숫자를 만들어서 탐색하는 것이 아니라 규칙을 찾아내서 그 숫자를 만들어 내는 것이다.

## 아이디어

우선 내가 생각한 것은 이러하다. 

전체 자리 수를 구해주고 그 자리 수에 맞춰서 k값을 이용해서 

4와 7이 들어갈 자리를 판별한다.

그래서 우선 규칙을 찾는 것이 매우 중요했는데

| K | 창영이가 좋아하는 수 | 2진수 |
| --- | --- | --- |
| 1 | 4 | 1 |
| 2 | 7 | 10 |
| 3 | 44 | 11 |
| 4 | 47 | 100 |
| 5 | 74 | 101 |
| 6 | 77 | 110 |
| 7 | 444 | 111 |

보이시나요? 규칙이?

일단 자리 수를 찾기 위해선 비트가 모두 1인 수에 집중해주세요

모두 비트가 1인 수는 모든 수가 4로 이루어져있습니다.

이 말은 무엇이냐 전의 수에서 자리 수가 +1가 갓 이루어진

친구거나 처음인 친구겠죠.

그래서 자리 수는 모두 1인 비트인 2진수인 k일 때

비트의 길이 만큼이 자리 수 입니다. 

아닐 때는 뭘까요?

binaryK.length의 -1의 수가 자리 수가 됩니다.

이유는 위에서 말한 바와 같습니다.

그렇게 자리 수를 찾아냈으면  

저의 필기 노트를 보여드리겠습니다. 여기서 규칙을 찾아보세요

-필기 노트-

4444 4447
4474

4444 4447 4474 4477
14+4+1
20 - 15 = 6
4747
8+4+2

44
3
2
1
444
그전의 수의 개수는 4개
1
0
10
477 2 4 4

744
2+4+ 4+1 = 11 5 101
5+4+2 =11 11+1 = 12
444
447
474
477
744
747
747 6 110
44 00
2+ 1 =3
74 3 10
2+2+1


!!! 미친 규칙 발견 

⇒ 전의 제곱 수들을 빼준 수를 2진수로 나타냈을 때 -1을 해준 배열이 바로 답이다.’

무슨 말이냐

k = 12일 때 747이 나와야 하는데

12 - 6(이건 4+2) =6
6의 2진수 110

110 - 001 = 101

747 101 

보이시나요?

이게 말이 되는거냐? 처음에 생각했습니다.

그래서 다른 수들도 시도해 보았는데 5번 연속 성공

우연이 계속되면 운명이라고 합니다.

규칙을 발견한 것이죠

결국 생긴 것은 4와 7이지만 2진수의 세계 안에 존재하기에

가능한 부분입니다.

그런데 여기서 주의할 점

k의 수에 따라 가끔 제곱 수들 더한 것을 제외했을 때 

2진수가 자릿수가 안 맞는 경우가 생길 때가 종종 있습니다.

그럴 땐 구해준 binaryK_result의 앞에 부족한 비트 수만큼 

어차피 4가 들어가야 하므로 그렇게 더해주면 완성입니다. 

## 풀이코드

```java
import java.io.*;
public class Main{
    static public void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        char []binaryK = Integer.toBinaryString(k).toCharArray();
        int position = binaryK.length;
        for(char x: binaryK){
            if(x=='0') {
                position = binaryK.length-1;
                break;
            }
        }
        boolean []arr = new boolean[position];
        int formal =0;
        for(int i =1 ; i<position; i++){
            formal += Math.pow(2, i);
        }
        k-=formal;
        char []binaryK_result = Integer.toBinaryString(k-1).toCharArray();
        if(binaryK_result.length<position){
            for(int i =0; i<position- binaryK_result.length; i++){
                sb.append("4");
            }
        }
        for(char x:binaryK_result){
            if(x=='1'){
                sb.append("7");
            }
            else{
                sb.append("4");
            }
        }
         System.out.println(sb);
    }
}
```

주석을 넣은 버전

```java
import java.io.*;

public class Main{
    static public void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        // k를 2진수로 나타내서
        //2진수로 k가 모두 1일때 그 1의 개수만큼 자리수가 된다.
        //그래서 2진수로 바꾸었을때 모두 1인지만 검사를 해줘도
        //맞으면 binaryK의 길이가 자리수
        //아니면 binaryK의 길이 -1 이 자리수가 된다.
        //일단 k를 2진수로 나타내주면
        char []binaryK = Integer.toBinaryString(k).toCharArray();
        int position = binaryK.length;
        for(char x: binaryK){
            if(x=='0') {
                position = binaryK.length-1;
                break;
            }
        }
        //7인값임을 저장할 boolean배열
        boolean []arr = new boolean[position];
        //자리수를 찾아냈다면 그 수는 예를들어 3자리 수면
        // 그전에 있었던 수의 개수는 2의 현재 수의 자리수-1의 제곱
        int formal =0;
        for(int i =1 ; i<position; i++){
            formal += Math.pow(2, i);
        }
        k-=formal;
        char []binaryK_result = Integer.toBinaryString(k-1).toCharArray();
				// 찾아낸 규칙 상으로는 7의 위치를 2진수가 표시할뿐
				// 앞에 잊혀진 4에 대해서는 아무런 방도가 없기에
				// 내가 따로 챙겨줘야한다.
        if(binaryK_result.length<position){
            for(int i =0; i<position- binaryK_result.length; i++){
                sb.append("4");
            }
        }
        for(char x:binaryK_result){
            if(x=='1'){
                sb.append("7");
            }
            else{
                sb.append("4");
            }
        }
        System.out.println(sb);
    }
}
```


성공

## 느낀 점

비트를 활용해서 이런 규칙을 발견해 나가고 그런 문제들도 등장하는 것을 보니

논리 회로 잘 배운 듯 하다.
