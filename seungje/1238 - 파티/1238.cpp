#include <bits/stdc++.h>
using namespace std;
vector<vector<int>>road; // 각 도시별 최단 시간(거리)
vector<vector<pair<int, int>>>v; // 각 도시에 연결된 도로 정보

void dks(int s) {
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>pq; // 오름차순 우선순위 큐
	road[s][s] = 0; 
	pq.push({ 0,s });

	while (!pq.empty()) {
		int node = pq.top().second; // 현재 노드
		int arrive_t = pq.top().first; // 현재 노드까지 걸린 시간
		pq.pop();

		if (road[s][node] < arrive_t) continue; // 현재 노드까지 걸리는 최단 시간

		for (int i = 0; i < v[node].size(); i++) {
			int next_node = v[node][i].first; // 이동할 노드
			int step_time = v[node][i].second; // 현재 노드에서 이동할 노드까지 걸리는 시간
			int total_time = step_time + arrive_t; // 이동할 노드까지 걸리는 전체시간
			
			if (road[s][next_node] > total_time) { // 이미 저장된 이동시간보다 지금 가는게 빠를경우
				road[s][next_node] = total_time;
				pq.push({ total_time, next_node });
			}
		}
	}
}

int main() {
	int N, M, X;
	cin >> N >> M >> X;
	road.resize(N + 1, vector<int>(N + 1,100001)); // 모든 도시의 최단 거리를 최대값으로 초기화
	v.resize(N + 1);

	while (M--) {
		int a, b, t;
		cin >> a >> b >> t;
		v[a].push_back({ b,t });
	}
	for (int s = 1; s <= N; s++) dks(s); // s -> 모든 노드에 대한 최단 시간(거리) 계산
	

	int ans = -1;
	for (int i = 1; i <= N; i++) { // i -> X + X -> i 의 최댓값 찾기
		if (i == X) continue;
		int total_time = road[i][X] + road[X][i];
		ans = ans < total_time ? total_time : ans;
	}
	cout << ans;
}