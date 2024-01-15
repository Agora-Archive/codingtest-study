#include <iostream>
#include <string>
#include <sstream>
#include <deque>
#include <algorithm>
using namespace std;
int T, n;
string p, arr;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> T;

	while (T--) {
		deque<int> dq;
		cin >> p >> n;
		cin.ignore();
		cin >> arr;

		for (char& c : arr)
			if (c == '[' || c == ']' || c == ',') c = ' '; // 숫자 제외 공백

		stringstream ss(arr);
		int num;
		while (ss >> num) dq.push_back(num);

		bool isError = false;
		bool isR = false; // true : 역방향 false : 정방향
		for (int i = 0; i < p.size(); i++) {
			if (p[i] == 'R') isR = !isR;
			else if (p[i] == 'D' && !dq.empty()) {
				if (isR) dq.pop_back();
				else dq.pop_front();
			}
			else { cout << "error" << '\n'; isError = true; break; }
		}
		
		if (!isError) {
			cout << "[";
			if (isR) { // 역방향
				while (!dq.empty()) {
					cout << dq.back();
					dq.pop_back();
					if (!dq.empty()) cout << ',';
				}
			}
			else { // 정방향
				while (!dq.empty()) {
					cout << dq.front();
					dq.pop_front();
					if (!dq.empty()) cout << ',';
				}
			}
			
			cout << "]" << '\n';
		}
	}
}