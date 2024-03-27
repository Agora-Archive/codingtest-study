#include <iostream>
#include <vector>
#include <queue>

#define INF 1e9 //최댓값 설정

using namespace std;

int n, m;
vector<pair<int, pair<int, int>>> v;
long long arr[501];

bool find() {
    arr[1] = 0;
    for (int i = 1; i <= n; i++) { //n번 반복(n-1번 + 마지막 확인을 위한 1번)
        for (int j = 0; j < m; j++) { //각 노드 반복
            int from = v[j].first; //시작 노드
            int next = v[j].second.first; //다음 노드
            long long dist = v[j].second.second; //거리

            if (arr[from] == INF) //방문되지 않았다면 통과
                continue;

            if (arr[next] > arr[from] + dist) { //거리 업데이트 가능하면 업데이트
                arr[next] = arr[from] + dist;
                if (i == n) //n번째 반복에도 업데이트가 일어나면 음수 사이클 존재
                    return false;
            }
        }
    }
    return true;
}

int main() {
    cin >> n >> m;
    int a, b, c;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        v.push_back({a, {b, c}});
    }
    for (int i = 1; i <= n; i++) {
        arr[i] = INF;
    }
    if (find()) {
        for (int i = 2; i <= n; i++) {
            if (arr[i] == INF)
                cout << -1 << "\n";
            else
                cout << arr[i] << "\n";
        }
    } else
        cout << -1;
}