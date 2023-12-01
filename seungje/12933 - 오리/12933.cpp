#include <iostream>
#include <string>
using namespace std;

void deleteIndex(string& arr, int del_index[]) {
	for (int i = 4; i >= 0; i--) arr.erase(del_index[i], 1);
}

int main() {
	string input, quack = "quack";
	int del_index[5] = { 0, };
	int index = 0, answer = 0;
	bool count = false;
	cin >> input;

	while (1) {
		for (int i = 0; i < input.length(); i++) {
			if (quack[index] == input[i]) del_index[index++] = i;
			if (index == 5) {
				deleteIndex(input, del_index); // 인덱스에 해당하는 문자 삭제
				index = 0;
				i -= 5;
				count = true;
			}
		}

		if (count) answer++;
		else if (input.length() > 0 || answer == 0) {
			answer = -1;
			break;
		}
		else break;
		index = 0;
		count = false;
	}

	cout << answer;
}