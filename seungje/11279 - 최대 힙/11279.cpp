#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int N,x;
	cin >> N;
	priority_queue<int> que;

	for (int i = 0; i < N; i++) {
		cin >> x;
		if (x == 0) {
			if (que.size() > 0) {
				cout << que.top() << " ";
				que.pop();
			}
			else {
				cout << 0 << " ";
			}
		}
		else {
			que.push(x);
			
		}
	}
}