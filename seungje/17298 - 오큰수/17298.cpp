#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N;
	cin >> N;
	stack<int> stack;
	vector<int> arr(N);
	vector<int> NGE(N);

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int i = N - 1; i >= 0; i--) {
		while (!stack.empty() && stack.top() <= arr[i]) {
			stack.pop();
		}
		if (stack.empty()) NGE[i] = -1;
		else NGE[i] = stack.top();

		stack.push(arr[i]);
	}

	for (int i = 0; i < N; i++) {
		cout << NGE[i] << " ";
	}
	return 0;
}