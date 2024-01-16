#include <iostream>

using namespace std;

int city[201][201] = {0};
bool visited[201];
int n;


void DFS(int node) {
    visited[node] = true; //방문처리
    for (int i = 1; i <= n; i++) {
        if (city[node][i] == 1 && !visited[i]) //도시가 연결되어 있고 방문되지 않았다면 DFS
            DFS(i);
    }
}


int main() {
    int m, next_trip;
    bool fail = false;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> city[i][j];
        }
    }
    cin >> next_trip;
    DFS(next_trip); //도시 연결 파악

    for (int i = 1; i < m; i++) {
        cin >> next_trip;
        if (!visited[next_trip]) { //방문되지 않았으면 연결되지 않은 것이므로 종료
            fail = true;
            break;
        }
    }
    if (fail)
        cout << "NO";
    else
        cout << "YES";
}