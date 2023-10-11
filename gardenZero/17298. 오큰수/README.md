# [Gold IV] 오큰수 - 17298 

[문제 링크](https://www.acmicpc.net/problem/17298) (_ans : 정답파일)

### 알게된 점
* 코드의 효율성이 떨어짐 (중복된 코드가 많음)
* 입력이 100만까지 있기 때문에 이중 for문으로 풀 경우 TLE(Time Limit Exceeded)가 발생함에 유의
* Stack을 활용하면 TLE를 피할 수 있음
* 이중 for문의 경우 O(n<sup>2</sup>)이고 Stack을 활용하면 O(n)이 됨

### 성능 요약

메모리: 19788 KB, 시간: 628 ms

### 분류

자료 구조, 스택

### 문제 설명

<p>크기가 N인 수열 A = A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 있다. 수열의 각 원소 A<sub>i</sub>에 대해서 오큰수 NGE(i)를 구하려고 한다. A<sub>i</sub>의 오큰수는 오른쪽에 있으면서 A<sub>i</sub>보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.</p>

<p>예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다. A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(2) = 8, NGE(3) = 8, NGE(4) = -1이다.</p>

### 입력 

 <p>첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에 수열 A의 원소 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub> (1 ≤ A<sub>i</sub> ≤ 1,000,000)이 주어진다.</p>

### 출력 

 <p>총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.</p>

### 해결방법

* Stack에 오큰수가 될 가능성이 있는 수를 저장
1. 해당 Index와 그보다 오른쪽에 있는 수들을 비교해야 하기 때문에 뒤에서부터 조회
2. 우선 해당 Index의 수와 Index+1에 위치한 수를 비교하고 이를 세가지 경우로 나눔
    1. Index+1에 수가 존재하지 않는 경우
        * outputArr에 -1을 push (오른쪽에 수가 존재하지 않기 때문)
    2. Index의 수가 Index+1의 수보다 작은 경우 (numArr[i] < numArr[i+1])
        * Index+1의 수가 Index 보다 오른쪽에 있고 그 중에서 가장 왼쪽에 있는 큰 수임
        * Index+1의 수는 그 전 loop에서 tmpArr에 push되었으므로 tmpArr.top을 통해 조회
        * outputArr에 tmpArr.top을 push
    3. Index의 수가 Index+1의 수보다 큰 경우
        * tmpArr에서 Index의 수보다 큰 수가 나올 때까지 pop
        * 나오는 경우 outputArr에 push
        * 나오지 않을 경우 outputArr에 -1을 push (해당 Index의 수 보다 큰 수가 오른쪽에 존재하지 않음)
3. 다음 loop에서 현재 Index의 수는 오큰수가 될 확률이 있기에 tmpArr에 push
