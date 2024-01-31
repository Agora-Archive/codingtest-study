#include <iostream>
#include <queue>
#include <vector>
using namespace std;
vector<vector<pair<int, int>>> v;
vector<int> tCost;
vector<int> road;
int n, m, cost;
int s, e;

void DKS() {
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int,int>>> pq;
	pq.push({0, s});
	tCost[s] = 0;

	while (!pq.empty()) {
		int now_cost = pq.top().first;
		int now_s = pq.top().second;
		pq.pop();

        if (now_cost > tCost[now_s]) continue;
        
		for (int i = 0; i < v[now_s].size(); i++) {
			int n_cost = v[now_s][i].first;
			int n_s = v[now_s][i].second;

			if (tCost[n_s] > now_cost + n_cost) {
				tCost[n_s] = now_cost + n_cost;
				road[n_s] = now_s;
				pq.push({ tCost[n_s] , n_s });
			}
		}
	}
}

int main() {
	cin >> n >> m;
	v.resize(n + 1);
	tCost.resize(n + 1, 100000001);
	road.resize(n + 1);

	while (m--) {
		cin >> s >> e >> cost;
		v[s].push_back({ cost,e });
	}
	cin >> s >> e;

	DKS();
	
	vector<int> ans;
	int num = e;
	while (num != 0) {
		ans.push_back(num);
		num = road[num];
	}

	cout << tCost[e] << '\n';
	cout << ans.size() << '\n';
	for (int i = ans.size() - 1; i >= 0; i--) cout << ans[i] << " ";
}