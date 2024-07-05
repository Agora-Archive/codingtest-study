[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/258709)

## 알고리즘 분류
---
완전 탐색, 이진 탐색

## 알게된 점
---
조합 짜는 방식에는 다양한 방법이 있지만 이 방법이 가장 시간복잡도 적고 빠른 것 같다.

완전 탐색인건 알았는데 맨날 BFS로 풀다보니까 DFS는 애초에 생각을 잘 안하게 되는거 같다.

DFS도 좀 연습해야겠다.   

레벨 3는 역시나 손쉽게 풀만한 문제는 안나오는 것 같다.

## 나의 접근 방법
---
우선 이길 확률을 구하라고는 했지만 승+무+패 의 결과가 동일하기에 승이 많은 경우를 찾으면 된다.

두개의 조합이 필요하다고 생각했다.

1. **주사위를 어떻게 선택할** 것인지에 대한 조합
2. 선택한 주사위들을 던졌을 때의 **면의 조합**

1번 조합을 만들기 위해 selVec를 활용했다. SelVec은 어떤 주사위를 골랐는지 0과 1로 나타낸 것이다.    
selVec에 주사위 개수의 반만큼 1을 채워넣고 next_permutation을 활용했다.

예를 들어 4개의 주사위가 있다고 할 때 **0011을 채워넣어 놓고 next_permutation**을 사용했다.    
이 경우 **0은 B**가, **1은 A가 선택한 주사위**라고 생각했다.

2번 조합을 만들기 위해 재귀함수를 활용했다. 어떻게 보면 **DFS를 돌린거기도 하다**.     
재귀함수의 코드를 보면서 생각해보자.

```cpp
void recursive(int count, int bit){
    if(count == selVec.size()){
        if(bit == 1) sumA.push_back(tempSum);
        else sumB.push_back(tempSum);
        return;
    }
    
    //보고 있는 주사위가 아니라면 패스
    if(selVec[count] != bit) recursive(count+1, bit);
    else{
        for(int i=0; i<6; i++){
            tempSum += diceVec[count][i];
            recursive(count+1, bit);
            tempSum -= diceVec[count][i];
        }
    }
}
```

**bit를 통해서** 내가 합을 구하는 사람이 A인지 B인지 **구분했다.**     
**1이라면 A의 주사위 합**을 구하는 것이고 **2라면 B의 주사위 합**을 구하는 것이다.    

**tempSum에 값을 더해주며** 모든 주사위를 훑어봤다면 첫번째 조건문을 만족하여 **bit에 따라 vector에 넣는다**.   
재귀 함수를 완전히 돌게 되면 **bit가 1일땐 sumA**에 모든 합의 경우가(**중복 허용**) 저장된다.

그렇게 A와 B의 모든 합을 구하게 된다면 모든 원소를 조합해보며 A가 B보다 큰 경우의 수를 세면 된다.  

그림을 보면 조금 더 이해가 편할 것이다.

![](images/2024-05-14-Programmers-258709.png)

하지만 A와 B를 그냥 2중 반복문을 통해 검사하게 된다면 시간초과가 발생하게 된다.   
그 이유는 마지막 시간 복잡도 계산 부분에서 판단해본다.(**이미 코드 짜기 전에 해보고 짜야함**, 글의 흐름상)

그래서 sumB를 정렬한 후 이진탐색을 한다.   
이진 탐색을 할 때 평소와 다른점이 있다. 평소에는 특정 값을 찾기위해 탐색한다.

하지만 이경우에는 **특정 값보다 작은 원소의 개수**를 찾는다.    
따라서 작은 경우를 제외하고 같거나 큰 경우는 모드 left = mid + 1을 적용한다.

조건문을 탈출 할 경우 left의 값이 작은 것의 개수가 된다.   
그림을 보면 이해가 더 쉽다.

![](images/2024-05-14-Programmers-258709-1.png)

이런 방식으로 구한 count가 이전 조합들보다 크다면 업데이트 해주는 방식이다.

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> diceVec;
vector<int> resultVec;
vector<int> selVec;
vector<int> sumA;
vector<int> sumB;
int loop;
long long tempSum = 0;
long long result = 0;

//count는 diceVec의 Index라고 보면됨
void recursive(int count, int bit){
    if(count == selVec.size()){
        if(bit == 1) sumA.push_back(tempSum);
        else sumB.push_back(tempSum);
        return;
    }
    
    //보고 있는 주사위가 아니라면 패스
    if(selVec[count] != bit) recursive(count+1, bit);
    else{
        for(int i=0; i<6; i++){
            tempSum += diceVec[count][i];
            recursive(count+1, bit);
            tempSum -= diceVec[count][i];
        }
    }
}

void findAnswer(){
    long long count = 0;
    count = findCount();

    if(count > result){
        result = count;
        resultVec.clear();
        for(int i=0; i<loop; i++) if(selVec[i] == 1) resultVec.push_back(i+1);
    }
}

//이진탐색으로 개수 파악
long long findCount(){
    long long count = 0;
    for(int i=0; i<sumA.size(); i++){
        int left = 0, right = sumB.size()-1, mid;
        //같은걸 찾는 것이 아니라 작은 것의 개수를 찾는 것임
        while(left <= right){
            mid = (left+right)/2;
            if(sumB[mid] < sumA[i]) left = mid+1;
            else right = mid-1;
        }
        
        count += left;
    }
    return count;
}

vector<int> solution(vector<vector<int>> dice) {
    diceVec = dice;
    loop = diceVec.size();
    
    for(int i=0; i<loop; i++){
        if(i<loop/2) selVec.push_back(0);
        else selVec.push_back(1);
    }
    
    //모든 경우를 파악
    do{
        recursive(0, 1);
        recursive(0, 0);
        sort(sumB.begin(), sumB.end());
        
        findAnswer();
        sumA.clear(); sumB.clear();
    }while(next_permutation(selVec.begin(), selVec.end()));

    
    return resultVec;
}
```