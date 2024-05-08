#include <iostream>
#include <string>
#include <cstring>

#define MAXINT 101
using namespace std;

char arr[MAXINT][MAXINT]; //문자판 배열
int dp[MAXINT][MAXINT][MAXINT]; //메모이제이션을 위한 배열
int n, m, k, len, sum = 0, dir[4][2] = {0, 1, 0, -1, 1, 0, -1, 0};
string input; //찾고자 하는 문자열

int find(int idx, int x, int y) { //DFS함수
    if (dp[x][y][idx] != -1) {  //이미 지나갔던 길이라면 저장된 수 리턴
        return dp[x][y][idx];
    }
    if (idx >= len) //문자열을 다 찾았다면 경우의 수 1 추가
        return 1;

    dp[x][y][idx] = 0; //이미 지나갔다는 표시 (0이 유지되면 길이 없으니 그만하라는 표시)

    for (int i = 0; i < 4; i++) { //4방향 탐색
        for (int j = 1; j <= k; j++) { //한 방향을 k만큼 탐색
            int tx = x + dir[i][0] * j; //임시 x 좌표
            int ty = y + dir[i][1] * j; //임시 y 좌표
            if (tx < 1 || tx > n || ty < 1 || ty > m) //x, y 좌표가 문자판을 벗어나면 넘어가기
                continue;
            if (arr[tx][ty] == input[idx]) { //해당 위치의 문자가 찾는 문자와 일치
                dp[x][y][idx] += find(idx + 1, tx, ty); //다음 문자를 찾아 경우의 수 dp배열에 저장
            }
        }
    }
    return dp[x][y][idx];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> m >> k;
    for (int i = 1; i <= n; i++) { //문자판 입력
        for (int j = 1; j <= m; j++)
            cin >> arr[i][j];
    }

    memset(dp, -1, sizeof(dp));  //dp 배열 -1로 초기화

    cin >> input;
    len = input.length();
    for (int i = 1; i <= n; i++) { //첫번째 문자 찾아서 dfs함수 돌리기
        for (int j = 1; j <= m; j++) {
            if (arr[i][j] == input[0])
                sum += find(1, i, j);
        }
    }
    cout << sum;
}