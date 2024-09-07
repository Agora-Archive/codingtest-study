#include <bits/stdc++.h>
using namespace std;

int m, n;
int arr[500][500];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};
int dp[500][500];

int dfs(int cx, int cy){

    if(cx == m-1 && cy == n-1) return 1;
    if(dp[cx][cy] != -1) return dp[cx][cy];

    dp[cx][cy] = 0;
    int nx, ny;
    for(int i=0; i<4; i++){
        nx = cx + dx[i]; ny = cy + dy[i];
        if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        if(arr[nx][ny] >= arr[cx][cy]) continue; // 가려는 곳이 오르막길이면 패스
    
        dp[cx][cy] = dp[cx][cy] + dfs(nx, ny);
    }

    return dp[cx][cy];
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> m >> n;

    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            cin >> arr[i][j];
            dp[i][j] = -1;
        }
    }

    int result = dfs(0, 0);

    cout << result;

    return 0;
}