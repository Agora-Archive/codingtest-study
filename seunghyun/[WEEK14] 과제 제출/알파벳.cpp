#include <iostream>
#include <algorithm>

using namespace std;

char board[21][21];
int arr[4][2] = {1, 0, -1, 0, 0, 1, 0, -1};
int r, c, ans;

bool check(int x, int y) { //보드를 넘어가는지 확인
    if (x >= 0 && x < r && y >= 0 && y < c)
        return true;
    else
        return false;
}

void find(bool visited[27], int x, int y, int sum) { //DFS함수
    int tx, ty;
    if (ans < sum) //현재값이 최댓값보다 크면 교체
        ans = sum;
    for (int i = 0; i < 4; i++) { //주변 칸 탐색
        tx = x + arr[i][0];
        ty = y + arr[i][1];

        if (check(tx, ty)) { //보드를 넘어가지 않고
            if (!visited[board[tx][ty] - 'A']) { //방문하지 않았다면
                visited[board[tx][ty] - 'A'] = true; //방문 처리
                find(visited, tx, ty, sum + 1); //DFS 호출
                visited[board[tx][ty] - 'A'] = false; //방문 취소
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> r >> c;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++)
            cin >> board[i][j];
    }
    bool visited[27] = {false};
    visited[board[0][0] - 'A'] = true;
    find(visited, 0, 0, 1);
    cout << ans;
}