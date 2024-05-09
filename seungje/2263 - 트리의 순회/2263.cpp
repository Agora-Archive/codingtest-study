#include <bits/stdc++.h>
using namespace std;
stack<int>st;
vector<int> in, post;
int n, num, node;

void tree(int s, int e) {
	if (node < 0) return;
	for (int i = s; i <= e; i++) {
		if (in[i] == post[node]) {
			node--;
			tree(i + 1, e);  // in[i] 를 기준으로 오른쪽으로 탐색
			tree(s, i - 1);  // in[i] 를 기준으로 왼쪽으로 탐색
			st.push(in[i]);
			break;
		}
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) { cin >> num; in.push_back(num); }
	for (int i = 0; i < n; i++) { cin >> num; post.push_back(num); }

	node = n - 1;
	tree(0, n - 1);

	while (!st.empty()) {
		cout << st.top() << " ";
		st.pop();
	}
}