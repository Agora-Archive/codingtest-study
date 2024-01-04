#include<iostream>
#include<stdlib.h>
using namespace std;
static int bingo = 0;
static int cnt = 0;
static int arr[25] = { 0, };
void printbingo() {
	if (bingo > 2) {
		cout << cnt << "\n";
		
		exit(0);
	}
	else {
		return;
	}
}
void findn(int a) { //해당 수를 찾아 -1 해주는 함수
	for (int i = 0; i < 25; i++) {
		if (arr[i] == a) {
			arr[i] = -1;
			return;
		}
	}
}
void horizon(int a) {
	int hor = 0;
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			if (arr[i * 5 + j] < 0) {
				hor++;
			}
		}
		if (hor > 4) {
			bingo++;
			printbingo();
		}
		hor = 0;
	}

}
void vertical(int a) {
	int ver = 0;
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			if (arr[j * 5 + i] < 0) {
				ver++;
			}
		}
		if (ver > 4) {
			bingo++;
			printbingo();
		}
		ver = 0;
	}
}
void right_up(int a) {
	int up = 0;
	for (int i = 4; i <= 20; i += 4) {
		if (arr[i] < 0)
			up++;
	}
	if (up > 4) {
		bingo++;
		printbingo();
	}
}
void right_down(int a) {
	int down = 0;
	for (int i = 0; i < 25; i += 6) {
		if (arr[i] < 0)
			down++;
	}
	if (down > 4) {
		bingo++;
		printbingo();
	}
}
int main() {
	
	for (int i = 0; i < 25; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < 25; i++) {
		int n;
		cin >> n;
		cnt++;
		findn(n);
		horizon(n);
		vertical(n);
		right_down(n);
		right_up(n);
		bingo = 0;
	}
}
