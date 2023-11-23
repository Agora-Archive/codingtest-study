##
노션링크
https://www.notion.so/16719-ZOAC-50c9619804bf40209c67bbd8f4021166?pvs=4
# 16719번 : ZOAC

| 시간 제한 | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| --- | --- | --- | --- | --- | --- |
| 1 초 | 256 MB | 2121 | 1107 | 891 | 55.273% |

## 문제

2018년 12월, 처음 시작하게 된 ZOAC의 오프닝을 맡은 성우는 누구보다 화려하게 ZOAC를 알리려 한다.

앞 글자부터 하나씩 보여주는 방식은 너무 식상하다고 생각한 성우는 문자열을 보여주는 새로운 규칙을 고안해냈다!

규칙은 이러하다. 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자를 보여주는 것이다.

예를 들어 ZOAC를 보여주고 싶다면, A → AC → OAC → ZOAC 순으로 보여주면 된다.

바쁜 성우를 위하여 이 규칙대로 출력해주는 프로그램을 작성하시오.

## 입력

첫 번째 줄에 알파벳 대문자로 구성된 문자열이 주어진다. 문자열의 길이는 최대 100자이다.

## 출력

규칙에 맞게 순서대로 문자열을 출력한다.

## 예제 입력 1

```
ZOAC

```

## 예제 출력 1

```
A
AC
OAC
ZOAC

```

## 예제 입력 2

```
BAC

```

## 예제 출력 2

```
A
AC
BAC

```

## 예제 입력 3

```
STARTLINK

```

## 예제 출력 3

```
A
AI
AIK
AINK
ALINK
ARLINK
ARTLINK
SARTLINK
STARTLINK
```

## 힌트

ZOAC는 한양대학교 ERICA 알고리즘 학회 ‘영과일’에서 주최하는 알고리즘 대회 Zero One Algorithm Contest 의 약자이다.

아무런 하등 쓸모없는 힌트였다.

## 문제 선정 이유

만만해 보여서

### 걸린 시간

2시간30분

### 성공 여부
실패 

## 문제 설명

나는 이미지를 이렇게 그렸다.

문자열에서 한 개씩 등장을 시키면서 문자열을 출력하는데

그 문자열들의 순서는 사전 상의 순서를 지키며 출력 된다. 

## 아이디어

우선 내가 생각하기론 

아스키 값으로 입력 받은 문자열의 배열들을 생성해주고 

순서대로 넣어준다. 그와 동시에 사전 순으로 가장 앞에 있는

문자를 찾고 min이란 변수에 해당 배열의 인덱스 값을 저장해준다.

그러고 나서는 min기준 뒤쪽부터 정렬하여 그 순서대로 다시 뿅하고

등장 시키면 되는 부분이다.  

## 풀이코드

우선 말한대로 코드를 짜보았다. min포함 뒷부분의 ZOAC식 배열이다.

```java
package practice;

import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		//총 문자열의 개수만큼 저장해줌
		String [] words = new String[word.length()];
		//각 문자의 아스키값을 저장해줄 배열
		int []order = new int[word.length()];
		//한개씩 받아와서 order에 저장
		int min =200;
		for(int i=0; i<word.length(); i++) {
			order[i] = (int)word.charAt(i);
			//정수값으로 배열에 저장해주면서 min값을 찾아냄
			if(min>order[i]) {
				min=i;//인덱스로 저장해줌
			}
		}
		//뒷부분부터 해야지 사전순으로 했을때 순서대로 할 수 있다.
		//sort해줄 배열
		int []order_behind= new int[word.length()-min-1];//뒷부분의 배열
		int index = min;
		//값을 넣어준다.
		for(int i=0; i<order_behind.length; i++) {
			//index++을 앞에 넣은이유는 뒤에 넣었다간 배열참조 오류가나고 그렇다고 if문써서 검사하자니
			//너무 번거로워서 이렇게 했음
			index++;
			order_behind[i] = order[index];
		}
		//stringbuilder 생성
		StringBuilder sb = new StringBuilder();
		//기준점이 될 문자를 먼저 -1을 해줌 왜하는지는 곧 등장
		order[min] *=-1;
		//정렬해줌
		Arrays.sort(order_behind);
		//여기서 어떻게 문자열을 생성해서 넣을지 그민해보았는데
		//오늘 배운 salt값의 야매응용버전을 생각해냈다.
		for(int i=0; i<word.length(); i++) {
			//그것은 바로 -1을 곱해주어 음수일때는 숨겨주고
			//출력해줄 문자를 차례로 -1을 다시 곱해주어 
			//그 문자들을 차례로 읽어내서 sb에 넣고 words에 넣어주는 것이다. 
			order[i] = -1*(int)word.charAt(i);
		}
		//기준문자부터 끝까지 완성될때까지 do-while문 반복
		index =0;
		do {
			sb.setLength(0);
			for(int x: order) {
				if(x>0) {
					sb.append((char)x);
				}
			}
			//그다음 친구 양수 만들어주기
			for(int i=0; i<order.length; i++) {
				try {
					if(order[i]==(-1*order_behind[index])) {
						order[i] *=-1;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
			
			words[index++] =sb.toString();
			System.out.println(sb);
			
		}while(sb.toString().length()!=(word.length()-min-1));
		//앞의 문자열들
	}
}
/*
 * 전부다 ascii코드 숫자로 배열을 생성하여 해당 문자를 뒤에서부터 sort해줘서
 * 뭐부터 넣어줄지 결정한다.
 * */
```


오 완벽하게 동작한다.

그런데 여기서 큰 문제가 발생했다. 

그것은 무엇이냐…

.

.

.

내일 아침에 자바 수업이 있어서 빨리 자야 한다는 것이다.

그래서 잠시 STOP하고 내일 앞 부분을 처리해주겠다.


수업에 가기 전 일어나서 나머지를 풀려고 봤는데

어라라? 저기 T가 왜 있지?

그래서 한번 검사 코드를 짜서 출력 값을 확인해봤다.

STARTLINK
next is?:A	order_behind:A

next is?:I	order_behind:I
next is?:K	order_behind:K
next is?:L	order_behind:L
next is?:N	order_behind:N
next is?:R	order_behind:R
next is?:T	order_behind:T
next is?:T	order_behind:T

치명적인 단점이 발견되었다. 

그것은 바로 중복된 문자를 처리하지 못한다는 것이다.

원래 같으면 중복된 문자를 뒤에서부터 등장시키고 한개 시켰으면

그다음번에 나머지를 등장시켜야 하는데

이 코드에서는 동시에 모두 등장 해버린다. 

이걸 어떻게 고치냐?

⇒ 일단 뒤에서부터 받아오는 걸로 고치고 

그다음에 한개 등장시켰으면 끝까지 탐색해서 

더찾는게 아니라 거기서 stop해야한다.

Here we go

근데 왜 T가 두개가 나오지? 나올 이유가 없는데 했더니

그리고 그전에 애초에 order_behind에 잘못들어갔는게

가장 문제였다.

결과적으로 2개의 문제를 한번에 잡아낸 셈이 되어버렸달까?

게다가 min값 설정부터 잘못된걸 catch했다.→이게 진짜 근본실수

일석삼조 

좋았다 내가 찾아낸 정보로 이 모든 오류들을 해결하겠다.

```java
package practice;

import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		//총 문자열의 개수만큼 저장해줌
		String [] words = new String[word.length()];
		//각 문자의 아스키값을 저장해줄 배열
		int []order = new int[word.length()];
		//한개씩 받아와서 order에 저장
		int min =0;
		for(int i=0; i<word.length(); i++) {
			order[i] = (int)word.charAt(i);
			//정수값으로 배열에 저장해주면서 min값을 찾아냄
			if(order[min]>order[i]) {
				min=i;//인덱스로 저장해줌
			}
		}
		//뒷부분부터 해야지 사전순으로 했을때 순서대로 할 수 있다.
		//sort해줄 배열
		int []order_behind= new int[word.length()-min-1];//뒷부분의 배열
		int index = min;
		//값을 넣어준다.
		for(int i=0; i<order_behind.length; i++) {
			//index++을 앞에 넣은이유는 뒤에 넣었다간 배열참조 오류가나고 그렇다고 if문써서 검사하자니
			//너무 번거로워서 이렇게 했음
			index++;
			order_behind[i] = order[index];
		}
		
		//stringbuilder 생성
		StringBuilder sb = new StringBuilder();
		//기준점이 될 문자를 먼저 -1을 해줌 왜하는지는 곧 등장
		order[min] *=-1;
		//정렬해줌
		Arrays.sort(order_behind);
		//여기서 어떻게 문자열을 생성해서 넣을지 그민해보았는데
		for(int i=0; i<word.length(); i++) {
			//그것은 바로 -1을 곱해주어 음수일때는 숨겨주고
			//출력해줄 문자를 차례로 -1을 다시 곱해주어 
			//그 문자들을 차례로 읽어내서 sb에 넣고 words에 넣어주는 것이다. 
			order[i] = -1*(int)word.charAt(i);
		}
		order[min] *=-1;
		//기준문자부터 끝까지 완성될때까지 do-while문 반복
		index =0;
		boolean once =true;
		do {
			sb.setLength(0);
			//make구문
			for(int i=min; i<word.length(); i++) {
				if(order[i]>0) {
					sb.append((char)order[i]);
				}
			}

			once =true;
			//그다음 등장할 친구 양수 만들어주기
			for(int i=order.length-1; i>=min; i--) {
				try {
					if(order[i]==(-1*order_behind[index])&&once) {
						order[i] *=-1;
						once = false;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
				}
			}
			index++;
			System.out.println(sb);
			
		}while(sb.toString().length()!=(word.length()-min));
		//앞의 문자열들 이제 처리해주도록 하자
		int []order_forward = new int[min];
		//이제 앞부분을 넣어준다.
		//근데 이미  - 처리가 된 애들이라 내림차순으로 정렬해줘야 하지만
		//나는 내림차순 정렬법에 대해서 알지못한다. in JAVA
		//실전에서 찾아보거나 할 수 없기에 
		//그냥 뽑아쓸때 거꾸로 쓰겠다. 헷갈리지만
		for(int i=0; i<min; i++) {
			order_forward[i]= order[i];
		}
		Arrays.sort(order_forward);// 오름차순이지만 음수기에 내림차순으로 생각해야한다.
		// 앞부분은 뒷부분의 반복
		index =min-1;
		StringBuilder fore = new StringBuilder();
		while(sb.length()!=word.length()){
			fore.setLength(0);
			//make구문
			for(int i=0; i<min; i++) {
				if(order[i]>0) {
					fore.append((char)order[i]);
				}
			}

			once =true;
			//그다음 등장할 친구 양수 만들어주기
			for(int i=0; i<min; i++) {
				try {
					if(order[i]==(order_forward[index])&&once) {
						order[i] *=-1;
						once = false;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
				}
			}
			index--;
			if(fore.length()!=0) {
				System.out.println(fore.toString()+sb.toString());	
			}
			if((fore.toString()+sb.toString()).equals(word)) {
				break;
			}
		}
	}
}
/
```

이걸로 끝이다. 제출을 했다…

그 결과는 처참하게 2%에서 오류 발생


인정할 수 없는 현실에 나는 손을 놓아버렸다.

의문의 중국인 반례 장인이 말했다.

“넌 지금 문제를 잘 못 이해했다.”

아…

그러하다. 나는 문제를 내 맘대로 이해한 것이다.

어떤 식으로 다들 푸는지 찾아보았다.

그랬더니 다들 재귀로 푼다고 하는 것이다.

내부 구현 틀은 나랑 동일했으나 획기적으로 코드가 짧기에 바로 

Let me do this

```java
import java.io.*;

public class Main {
	static StringBuilder sb= new StringBuilder();
	static String word;
	static boolean [] appear;//등장할 친구인지 아닌지 
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine();
		appear = new boolean[word.length()];
		
		Zoac(0, word.length()-1);
		br.close();
		System.out.println(sb);
	}
	private static void Zoac(int left, int right) {
		//종료 기준 마련
		if(left>right) {
			return;
		}
		//사전순으로 가장빠른 문자를 찾아서 boolean배열에서
		//그 값을 true로 만들어주자
		int min = left;
		for(int i=left; i<=right; i++) {
			if(word.charAt(min)>word.charAt(i)) {
				min = i;
			}
		}
		appear[min] =true;
		//make 구문
		for(int i=0; i<word.length(); i++) {
			if(appear[i]) {
				sb.append(word.charAt(i));
			}
		}
		sb.append("\n");
		Zoac(min+1, right);
		Zoac(left, min-1);
	}
}
```

![Untitled](16719%E1%84%87%E1%85%A5%E1%86%AB%20ZOAC%2050c9619804bf40209c67bbd8f4021166/Untitled%203.png)

재귀로 하니 코드의 길이가 1/3가 되었다.

## 배운점

너무 초반부터 틀린다 싶으면 그냥 문제 이해를 잘못한 것이니까

문제를 처음부터 꼼꼼히 다시 읽어보길 바란다.

그리고 재귀가 꽤 매력적인 듯 하다.ㅎㅎ
