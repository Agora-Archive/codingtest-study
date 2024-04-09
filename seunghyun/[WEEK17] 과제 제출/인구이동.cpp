#include <iostream>
#include <vector>
#include <queue>

using namespace std;
vector<pair<int, int>> v;
queue<pair<int, int>> q;
int n, l, r, sum = 0;
int city[51][51], dir[4][2] = {1, 0, -1, 0, 0, 1, 0, -1};
bool visited[51][51];

bool check(int x, int y) {
    if (x < 1 || x > n || y < 1 || y > n)
        return false;
    return true;
}

void find(pair<int, int> input) {
    q.push(input); //bfs탐색을 위한 큐
    visited[input.first][input.second] = true; //방문 처리
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int tx = x + dir[i][0];
            int ty = y + dir[i][1];
            if (!visited[tx][ty]) {
                if (check(tx, ty)) {
                    int temp = abs(city[x][y] - city[tx][ty]);
                    if (temp >= l && temp <= r) {
                        v.push_back({tx, ty}); //연합 처리를 위한 벡터 저장
                        q.push({tx, ty});
                        visited[tx][ty] = true;
                        sum += city[tx][ty]; //인구수 계산
                    }
                }

            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> l >> r;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++)
            cin >> city[i][j];
    }
    bool flag = true;
    int ans = 0;
    while (flag) { //연합이 발생하지 않을 때까지 반복
        flag = false;
        for (int i = 1; i <= n; i++) { //모든 나라를 보며 방문되지 않은 경우 bfs실시
            for (int j = 1; j <= n; j++) {
                if (!visited[i][j]) {
                    v.push_back({i, j});
                    sum = city[i][j];
                    find({i, j});
                }
                if (v.size() > 1) { //벡터에 2개이상의 나라가 저장됐다면 연합 발생
                    flag = true;
                    for (int k = 0; k < v.size(); k++) {
                        city[v[k].first][v[k].second] = sum / v.size();
                    }
                }
                v.clear();
            }
        }
        if (flag)
            ans++;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                visited[i][j] = false;
        }

    }
    cout << ans;
}