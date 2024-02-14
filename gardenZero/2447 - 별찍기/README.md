[문제 링크](https://www.acmicpc.net/problem/2447)
## 알고리즘 분류
재귀

## 알게된 점
사실 내가 재귀를 별로 좋아하지 않아서 푼 문제임   
그닥 어려운 문제는 아니었으나 구현력을 기를려고 풀어봄   
이런 좌표를 가지고 푸는 문제는 길이를 변수로 지정해 놓으면 편함   

## 나의 접근법
딱 보니 가운데를 비우고 사각형 형태로 별들이 둘러싸는 형태였음   
재귀는 규칙성을 찾는 것이 중요함

근데 그 사각형 한변 길이가 3을 넘어가면 작은 사각형으로 큰 사각형을 만드는 형태임
![](images/2024-02-07-BOJ-2447.png)

그래서 위의 사진을 보면 초록 사각형은 빨간 사각형들을 모아서 만든 사각형이고

빨간 사각형은 파란 사각형을 모아서 만든 사각형임

그래서 처음에는 해당 규칙대로 출력할려 햇으나 그러면 줄바꿈때문에 안됨   
그래서 크기를 구해보니 한변길이의 최대가 2187이라 그냥 배열을 선언하고 넣어줌   

재귀의 구현은 각 사각형의 왼쪽의 좌표와 해당 사각형의 한변 길이를 전달해줌   
그리고 해당 사각형을 채울 문자도 넘겨줌

예를 들어 0, 0, 3, ' * '  이넘어가면 왼쪽 위 빨간 사각형이 나옴   
만약 ' * '  이 아니라 '   '이 넘어가면 해당 가운데 비어있는 사각형이 나옴   

재귀적으로 해결하기 위해서 모두 동일한 작업을 거치지만   
char c의 값만 바꿔주면 비어있는 사각형을 만들 수 있음   

## 코드
```c++
#include <bits/stdc++.h>
using namespace std;

char arr[2188][2188];

void recursive(int x, int y, int length, char c){
    int nl = length/3;
    if(length > 3){
        recursive(x, y, length/3, c);        // 왼쪽 위
        recursive(x+nl, y, length/3, c);     // 왼쪽 중간
        recursive(x+nl*2, y, length/3, c);   // 왼쪽 아래

        recursive(x, y+nl, length/3, c);        // 가운데 위
        recursive(x+nl, y+nl, length/3, ' ');   // 가운데 중간
        recursive(x+nl*2, y+nl, length/3, c);   // 가운데 아래

        recursive(x, y+nl*2, length/3, c);        // 오른쪽 위
        recursive(x+nl, y+nl*2, length/3, c);     // 오른쪽 중간
        recursive(x+nl*2, y+nl*2, length/3, c);   // 오른쪽 아래
    }else{
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                arr[x+i][y+j] = c;
            }
        }
        arr[x+1][y+1] = ' ';
    }
}


int main(){
    int n;
    cin >> n;
    recursive(1, 1, n, '*');

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cout << arr[i][j];
        }
        cout << "\n";
    }
    
    return 0;
}
```