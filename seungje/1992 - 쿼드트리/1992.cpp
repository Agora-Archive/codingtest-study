#include <iostream>
#include <string>
using namespace std;
char tree[65][65];

void QTree(int col, int row, int size) {

	if (size == 1) {
		cout << tree[col][row];
		return;
	}

	bool onlyOne = true, onlyZero = true;
	for (int i = col; i < col + size; i++) {
		for (int j = row; j <row + size; j++) {
			if (tree[i][j] == '0') onlyOne = false;
			if (tree[i][j] == '1') onlyZero = false;
		}
	}

	if (onlyOne == true || onlyZero == true) {
		cout << onlyOne ? 1 : 0 ;
		return;
	}

	cout << "(";
	QTree(col, row, size/2);
	QTree(col, row + size/2, size/2);
	QTree(col + size/2, row, size/2);
	QTree(col + size/2, row + size/2, size/2);
	cout << ")";
}

int main() {
	int N;
	string input;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> input;
		for (int j = 0; j < N; j++) tree[i][j] = input[j];
	}

	QTree(0,0,N);
}