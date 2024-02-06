#include <bits/stdc++.h>
using namespace std;
vector<int> v;
int ans[9];
bool visit[9];
int N, M, num;

void solve(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < M; i++) cout << ans[i] << " ";
		cout << "\n";
		return;
	}

	int cur = 0;
	for (int i = 0; i < v.size(); i++) {
		if (!visit[i] && cur != v[i]) {
			cur = ans[cnt] = v[i];
			visit[i] = true;
			solve(cnt + 1);
			visit[i] = false;
		}
	}
}

int main() {
	cin >> N >> M;
	while (N--) { cin >> num; v.push_back(num); }
	sort(v.begin(), v.end());

	solve(0);
}