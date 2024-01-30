#include <iostream>
#include <vector>
using namespace std;
vector<vector<pair<int, int>>>v;
int V, node, dot, len;
int ans = 0, good_node = 1;

void DFS(int n, int visit[],int total_len) {
	visit[n] = true;
	if (ans <= total_len) {
		ans = total_len;
		good_node = n;
	}
	for (int i = 0; i < v[n].size(); i++) {
		int next_node = v[n][i].first;
		int next_len = v[n][i].second;
		if (!visit[next_node]) DFS(next_node, visit, total_len + next_len);
	}
}
int main() {
	cin >> V;
	v.resize(V + 1);

	for (int i = 1; i <= V; i++) {
		cin >> node;

		while (1) {
			cin >> dot;
			if (dot == -1) break;
			cin >> len;
			v[node].push_back({ dot,len });
		}
	}

	int visit[100001] = { 0, };
	DFS(1, visit, 0);
	for (int i = 0; i <= V; i++) visit[i] = 0;
	DFS(good_node, visit, 0);
	cout << ans;
}