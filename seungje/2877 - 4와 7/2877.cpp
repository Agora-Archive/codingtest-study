#include <iostream>
#include <math.h>
#include <queue>
using namespace std;
queue<int> q;

void makeTwoLose(int num,int bits) { 
	for (int i = bits-1; i >= 0; i--) {
		q.push(((num >> i) & 1));
	}
}

void printFourSeven() { // 출력
	while (!q.empty()) {
		if (q.front() == 0) cout << "4";
		else cout << "7";
		q.pop();
	}
}

int main() {
	int K = 0; // 입력
	
	cin >> K;
	int bits = 1;
	while (1) {
		int check = pow(2, bits + 1) - 1 ; // 1, 3, 7, 15, ...
		if (check > K) { 
			int tenLoseNum = K - (pow(2, bits) - 1);
			makeTwoLose(tenLoseNum, bits);
			printFourSeven();
			break;
		}
		bits++;
	}
}