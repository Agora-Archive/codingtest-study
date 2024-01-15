#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
int T, k, num;
char cmd;
bool visited[1000001];

int main() {
	cin >> T;

	for (int i = 0; i < T; i++) {
		cin >> k;

		priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> minH;
		priority_queue<pair<int,int>> maxH;

		for (int j = 0; j < k; j++) {
			cin >> cmd >> num;   // 입력
			if (cmd == 'I') {
				minH.push({ num,j }); // 입력받은 수, 순서 저장
				maxH.push({ num,j });
				visited[j] = true;
			}
			else {
				if (num == 1) { // 최댓값 삭제
					while (!maxH.empty()&& !visited[maxH.top().second]) maxH.pop();
					if (!maxH.empty()){
						visited[maxH.top().second] = false;
						maxH.pop();
					}
				}
				else { // 최솟값 삭제
					while (!minH.empty()&& !visited[minH.top().second])	minH.pop();
					if (!minH.empty()){
						visited[minH.top().second] = false;
						minH.pop();
					}
				}
			}
		}

		while (!maxH.empty() && !visited[maxH.top().second]) maxH.pop();
		while (!minH.empty() && !visited[minH.top().second]) minH.pop();

		if (minH.empty() && maxH.empty())cout << "EMPTY" << '\n';
		else cout << maxH.top().first << " " << minH.top().first << '\n';
	}
}