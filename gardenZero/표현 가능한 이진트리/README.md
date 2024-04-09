[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/150367)

## 알고리즘 분류
---
재귀, DFS

## 알게된 점
---
처음 풀었을 때 55분정도 걸렸는데 다 풀어내는 데는 2시간 넘게 걸림..

자꾸 Segmen Fault가 떠서 뭐가 문제인지 몰랐음

알고보니 주어지는 수가 long long 이라 오버플로우가 떴음

자료형을 잘 보고 풀자(이것때매 한시간 넘게 고생했음...)

## 나의 접근 방법
---
알다싶이 포화 이진트리의 노드는 각 층마다 1, 2, 4, 8, 16 ... 과 같이 증가함    
이는 각 층마다 x2씩 되는 구조임

그래서 포화 이진트리의 총 노드수를 구하려면 각 층의 누적합을 구하면 됨

![](images/2024-03-05-Programmers-표현%20가능한%20이진트리.png)


만약 주어진 수의 2진수를 구했는데 자리수가 부족하다면?     
앞에 개수가 맞도록 0을 추가해줘야함     
아래의 그림을 보면 조금더 이해가 쉬울 것 같음     

![](images/2024-03-05-Programmers-표현%20가능한%20이진트리-1.png)


10진수를 2진수로 변환하는 코드는 아래와 같음

그다음 이 트리가 유효한지 확인을 해야함 

```cpp
string find(long long input){
    string temp = "";
    //2진수로 변환
    while(input > 0){
        temp = to_string(input%2)+temp;
        input /= 2;
    }
    
    //포화 이진트리의 노드 수 구하기
    int tempSize = 1, tempInt = 1;
    while(tempSize < temp.length()){
        tempInt *= 2;
        tempSize += tempInt;
    }
    
    //앞에 0채우기
    int a = temp.length();
    for(int i=0; i<tempSize - a; i++){
        temp = "0"+temp;
    }
    return temp;
}
```

포화 이진트리 구조의 2진수를 구했다면 이 트리가 유효한 트리인지 확인해야 함

유효한 트리인지를 확인하려면 밑의 경우를 보면 됨

![](images/2024-03-05-Programmers-표현%20가능한%20이진트리-2.png)

1. 부모가 0인지 1인지 체크
2. 부모가 0이라면 모든 자식이 0이어야 정상적인 트리임
3. 부모가 1이라면 자식이 0이든 1이든 상관이 없음

그러면 모든 트리가 정상인지를 확인하려면 어떻게 해야할까?

1. 루트 부터 서브트리들이 정상인지 확인해야함
2. 아래의 서브트리들이 정상인지 판단하려면 그 아래의 서브트리가 정상인지 확인해야 함

결국 재귀적으로 풀어야한다.

![](images/2024-03-27-Programmers-표현%20가능한%20이진트리.png)

재귀의 반환값을 통해서 세가지 경우로 나누어 줬음
1. 만약 트리가 비정상적이라면 -1
2. 정상이고 노드가 하나도 없다면 0
3. 정상이고 노드가 하나라도 있다면 1

재귀의 코드는 아래와 같음
```cpp
int recursive(string temp, int start, int end){
	//노드가 하나인 경우
    if(temp.size() == 1){
        if(temp[0] == '1') return 1;
        return -1;
    }
    
    int mid = (start+end)/2;   //부모노드
    if(end-start == 2){    //트리가 3개의 노드로만 이루어 져있을때
        if(temp[mid] == '0'){ //부모가 0이라면 자식들도 다 0인지 체크해야함
            if(temp[mid-1] == '0' && temp[mid+1] == '0') return 0;
            else return -1;  //잘못된 트리니까 -1 반환
        }
        return 1; //부모가 0이라면 자식이 뭐든 상관없음
    }
    else{   //노드가 3개 이상일 때
        int left = recursive(temp, start, mid-1)
        int right = recursive(temp, mid+1, end);
        //왼쪽 오른쪽 중에 비정상 트리가 있을 때
        if(left == -1 || right == -1) return -1;
        
	    //비정상 트리가 없고 부모가 1인경우 자식이 어떤지 판단 x
        else if(temp[mid] == '1') return 1;

		//비정상 트리가 없고 부모가 0인경우 자식이 모두 0인지 판단해야 함
        else if(left == 0 && right == 0) return 0;

		//비정상 트리가 없지만 부모가 0인데 자식중에 1이 있는 경우
        else return -1;
    }
}
```


## 전체코드
```cpp
#include <bits/stdc++.h>
using namespace std;

string find(long long input){
    string temp = "";
    //2진수로 변환
    while(input > 0){
        temp = to_string(input%2)+temp;
        input /= 2;
    }
    
    //포화 이진트리의 노드 수 구하기
    int tempSize = 1, tempInt = 1;
    while(tempSize < temp.length()){
        tempInt *= 2;
        tempSize += tempInt;
    }
    
    //앞에 0채우기
    int a = temp.length();
    for(int i=0; i<tempSize - a; i++){
        temp = "0"+temp;
    }
    return temp;
}

int recursive(string temp, int start, int end){
	//노드가 하나인 경우
    if(temp.size() == 1){
        if(temp[0] == '1') return 1;
        return -1;
    }
    
    int mid = (start+end)/2;   //부모노드
    if(end-start == 2){    //트리가 3개의 노드로만 이루어 져있을때
        if(temp[mid] == '0'){ //부모가 0이라면 자식들도 다 0인지 체크해야함
            if(temp[mid-1] == '0' && temp[mid+1] == '0') return 0;
            else return -1;  //잘못된 트리니까 -1 반환
        }
        return 1; //부모가 0이라면 자식이 뭐든 상관없음
    }
    else{   //노드가 3개 이상일 때
        int left = recursive(temp, start, mid-1);
        int right = recursive(temp, mid+1, end);
        //왼쪽 오른쪽 중에 비정상 트리가 있을 때
        if(left == -1 || right == -1) return -1;
        
	    //비정상 트리가 없고 부모가 1인경우 자식이 어떤지 판단 x
        else if(temp[mid] == '1') return 1;

		//비정상 트리가 없고 부모가 0인경우 자식이 모두 0인지 판단해야 함
        else if(left == 0 && right == 0) return 0;

		//비정상 트리가 없지만 부모가 0인데 자식중에 1이 있는 경우
        else return -1;
    }
}

vector<int> solution(vector<long long> numbers) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    vector<int> answer;
    string temp;
    for(int i=0; i<numbers.size(); i++){
        temp = find(numbers[i]);
        if(recursive(temp, 0, temp.size()-1) != -1){
            answer.push_back(1);
        }else{
            answer.push_back(0);
        }
    }
    
    return answer;
}
```