[문제링크](https://school.programmers.co.kr/learn/courses/30/lessons/60058?language=cpp)

## 알고리즘 분류
---
재귀

## 알게된 점
---
문자열을 자르는 규칙을 찾는게 제일 어려웠는데 이것도 높은 난이도는 아니었다.

사실 그렇게 어려운 문제는 아니었지만 문제를 차분히 읽을줄 알아야한다.

난독증이 있다면.. 이런 문제에 익숙해지는 것도 필요할 듯하다.

## 나의 접근 방법
---
사실 그냥 **시키는 대로** 하면 된다.

이외에 내가 알아내야하는 것은 딱 두가지이다.
1. 어떻게 **올바른 괄호 문자열인지 체크**하는 방법
2. **조건대로 두 문자열**로 쪼개는 방법

그렇다면 올바른 괄호 문자열인지 **어떻게 체크할까?**    
이는 간단히 **Stack**을 활용하면 된다.

**'(' 이들어오면 push**하고 **')'이 나오면 pop** 하면 된다.

```cpp
bool check(string p){
    vector<char> stack;
    int lc = 0, rc = 0;
    for(int i=0; i<p.length(); i++){
        if(p[i] == '('){
            stack.push_back(p[i]);
        }else{
            if(!stack.empty()) stack.pop_back();
            else return false;
        }
    }
    return true;
}
```

만약 ')'이 들어와 **pop하려고 하는데 stack이 비어있으면 잘못된 문자열**인 것이다.   
만약 **문자열 끝까지 아무런 문제**가 생기지 않으면 정상 문자열이다.


이 문제에서 가장 까다로운 부분은 **어떻게 문자열을 자를 것**인지이다.     
더이상 자를 수 없는 문자열을 어떻게 만들까?

'('의 개수와 ')'의 개수가 **처음으로 같아지는 순간**을 보면된다.    
문제에서는 **두 균형잡힌 문자열**로 나누라고 했기 때문에 앞쪽 문자열이 균형잡힌 문자열로 만들어야한다.
해당 순간 전까지는 **균형잡힌 문자열이 아니다.**    

```cpp
int find(string p){
    int lc = 0, rc = 0;
    for(int i=0; i<p.length(); i++){
        if(p[i] == '(') lc++;
        else rc++;
        
        if(lc == rc) return i;
    }
    return p.length()-1;
}
```

위 코드는 해당 순간을 찾는 함수이다.     
만약 끝까지 없었다면 p.length()-1을 반환하도록 했다.(사실 무조건 for문안에서 반환이 이루어진다.)

이후에는 그냥 문제에서 시킨대로 재귀함수를 선언해주기만 하면된다.

## 코드
---
```cpp
#include <bits/stdc++.h>
using namespace std;

//올바른 괄호 문자열인지 체크
bool check(string p){
    vector<char> stack;
    int lc = 0, rc = 0;
    for(int i=0; i<p.length(); i++){
        if(p[i] == '('){
            stack.push_back(p[i]);
        }else{
            if(!stack.empty()) stack.pop_back();
            else return false;
        }
    }
    return true;
}

//처음으로 '(' 개수와 ')' 개수가 같아지는 순간
int find(string p){
    int lc = 0, rc = 0;
    for(int i=0; i<p.length(); i++){
        if(p[i] == '(') lc++;
        else rc++;
        
        if(lc == rc) return i;
    }
    return p.length()-1;
}

//앞뒤 제거후 문자열 뒤집기
string reverse(string p){
    string result = "";
    for(int i=1; i<p.length()-1; i++){
        if(p[i] == '(') result += ')';
        else result += '(';
    }
    return result;
}

string recursive(string p){
    if(p == "") return p;
    
    string u = p.substr(0, find(p)+1);
    string v = p.length() == u.length() ? "" : p.substr(find(p)+1);
    
    if(check(u)) return u + recursive(v);         
    else return "(" + recursive(v) + ")" + reverse(u);
}


string solution(string p) {
    string answer;
    if(check(p)){
        answer = p;
    }else{
        answer = recursive(p);
    }
    
    return answer;
}
```