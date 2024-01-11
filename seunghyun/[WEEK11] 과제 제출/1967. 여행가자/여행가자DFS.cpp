#include <iostream>

using namespace std;

int city[201][201] = {0};
int n;

bool search(int trip, int next_trip, bool visited[201]) {

    if (trip == next_trip) //현재 위치와 다음 위치가 같음
        return true;

    if (city[trip][next_trip] == 1)  //현재 위치와 다음 위치가 이어져있음
        return true;

    for (int i = 1; i <= n; i++) { //모든 경우를 살펴봄
        if (city[trip][i] == 1 && !visited[i]) { //현재 위치와 이어진 곳이 방문되지 않았으면 재귀로 살펴봄
            visited[i] = true;
            if (search(i, next_trip, visited))
                return true;
        }
    }
    return false;
}


int main() {
    int m, trip = 0, next_trip;
    bool fail = false;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> city[i][j];
        }
    }
    for (int i = 0; i < m; i++) {
        bool visited[201] = {false};
        cin >> next_trip;
        if (trip != 0) {
            if (!search(trip, next_trip, visited)) {
                fail = true;
            }
        }
        trip = next_trip;
    }
    if (fail)
        cout << "NO";
    else
        cout << "YES";
}