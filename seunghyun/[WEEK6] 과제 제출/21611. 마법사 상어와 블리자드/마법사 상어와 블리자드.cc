#include <iostream>
#include <queue>
#include <vector>
#define MAXTABLE 49
#define MAXMAGIC 100
using namespace std;

vector<int> explosion(vector<int>arr, int ball[3]) { //폭파 함수
	queue<int> q;
	vector<int> temp;
	int del[MAXTABLE], sum, num, exp;
	while (true) {
		sum = 0, num = 0, exp = 0;
		while (!temp.empty()) //temp벡터 비우기
			temp.pop_back();
		q.push(arr[0]); //첫번째 수 큐에 push
		for (int i = 1; i < arr.size(); i++) {
			if (arr[i] == q.front()) { //현재 수와 큐에 있는 수가 동일하면 계속 push
				q.push(arr[i]);
			}
			else {
				if (q.size() >= 4) { //큐에 저장된 수의 개수가 4이상이면 폭파
					sum = q.size(); //큐 사이즈 저장
					for (int i = 0; i < sum; i++) { //큐에 저장된 수 삭제
						ball[q.front() - 1]++; //ball배열에 해당 수 증가
						q.pop();
					}
					exp++;
				}
				while (!q.empty()) { //큐 비우면서 temp에 push_back
					temp.push_back(q.front());
					q.pop();
				}
				q.push(arr[i]);
			}
		}
		if (q.size() >= 4) { //큐 비우기
			sum = q.size();
			for (int i = 0; i < sum; i++) {
				ball[q.front()]++;
				q.pop();
			}
			exp++;
		}
		while (!q.empty()) {
			temp.push_back(q.front());
			q.pop();
		}
		arr = temp; //arr을 temp로 교체
		if (exp == 0)
			break;

	}
	return arr;
}

vector<int> realign(vector<int> arr, int size) {//재정렬 함수
	vector<int>temp;
	queue<int> q;
	q.push(arr[0]);
	for (int i = 1; i < arr.size(); i++) {
		if (q.front() == arr[i])//큐에 저장된 수와 현재 수 동일하면 계속 push
			q.push(arr[i]);
		else {//다르면 temp에 큐에 저장된 수 개수, 수 순서대로 push_back
			temp.push_back(q.size());
			temp.push_back(q.front());
			while (!q.empty())
				q.pop();
			q.push(arr[i]);
		}
		if (temp.size() == size)//현재 저장된 수의 개수가 사이즈와 같으면 종료
			break;
	}
	if (temp.size() < size) {
		if (!q.empty()) { //큐 비우기
			temp.push_back(q.size());
			temp.push_back(q.front());
		}
	}
	return temp;
}

vector<int> magic(vector<int> arr, int d, int s) { //마법 함수
	int min, sum, num = 0, j = 0, del[MAXTABLE]; //삭제를 위한 배열
	vector<int> temp;
	switch (d) {
	case 1:
		sum = 0, min = 7; //sum=삭제할 인덱스, min=sum을 증가할 인덱스
		for (int i = 0; i < s; i++) {
			sum = sum + min; //삭제할 인덱스 계산
			min += 8; //min에 8더하기
			del[num++] = sum - 1;
		}
		break;
	case 2:
		sum = 0, min = 3;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	case 3:
		sum = 0, min = 1;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	case 4:
		sum = 0, min = 5;
		for (int i = 0; i < s; i++) {
			sum = sum + min;
			min += 8;
			del[num++] = sum - 1;
		}
		break;
	}
	for (int i = 0; i < arr.size(); i++) {
		if (del[j] == i) { //삭제할 인덱스가 나오면 continue로 저장하지 않고 건너뛰기
			j++;
			continue;
		}
		temp.push_back(arr[i]);
	}
	return temp;
}

vector<int> set(int table[MAXTABLE][MAXTABLE], int n) { //격자 벡터로 변경 함수
	vector<int> arr;
	int a, b, c, i;
	a = n / 2; //행 인덱스
	b = n / 2 - 1; //열 인덱스
	c = 1; //이동 횟수
	if (table[a][b] != 0) //시작 수가 0이면 패스
		arr.push_back(table[a++][b]); //arr에 해당 수 push
	else
		a++;
	while (1) {
		c++;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) { //해당 수 0이면 push 하지 않고 continue
				b++;
				continue;
			}
			arr.push_back(table[a][b++]);
		}
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				a--;
				continue;
			}
			arr.push_back(table[a--][b]);
		}
		c++;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				b--;
				continue;
			}
			arr.push_back(table[a][b--]);
		}
		if (a == 0 && b == -1)
			break;
		for (int i = 0; i < c; i++) {
			if (table[a][b] == 0) {
				a++;
				continue;
			}
			arr.push_back(table[a++][b]);
		}
	}
	return arr;
}

int main() {
	vector<int> arr;
	int ball[3] = { 0 }, size = 0; //ball=각 수마다 폭파된 개수 저장 배열, size=상어의 위치를 제외한 격자의 크기

	int n, m, sum = 0;
	cin >> n >> m; //격자 크기, 마법 횟수 입력
	size = n * n - 1; //상어 위치를 제외하기 위해 -1
	int table[MAXTABLE][MAXTABLE], magic_table[MAXMAGIC][2]; //격자와 마법을 저장하기 위한 배열
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> table[i][j];
		}
	}
	for (int i = 0; i < m; i++) {
		cin >> magic_table[i][0] >> magic_table[i][1];
	}

	arr = set(table, n); //격자를 arr 벡터로 변경
	if (!arr.empty()) { //arr이 비어있으면 종료함
		for (int i = 0; i < m; i++) { 
			arr = magic(arr, magic_table[i][0], magic_table[i][1]); 
			arr = explosion(arr, ball);
			if (arr.empty()) //폭파 후 arr이 비어있으면 그대로 종료
				break;
			else
				arr = realign(arr, size);
		}
	}

	sum += ball[0] * 1 + ball[1] * 2 + ball[2] * 3; //점수 계산
	cout << sum;
}