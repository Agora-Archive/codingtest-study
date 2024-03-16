#include <bits/stdc++.h>
using namespace std;

int n, m;
int dist[100][100];
string arr[100];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);    

    cin >> m >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++)
	        dist[i][j] = -1;
    }

    for(int i=0; i<n; i++) cin >> arr[i];

    queue<pair<int, int>> q;
    dist[0][0] = 0;
    q.push({0, 0});
    while(!q.empty()){
        int x, y;
        tie(x, y) = q.front(); q.pop();

        for(int i=0; i<4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(dist[nx][ny] != -1 && dist[nx][ny] <= dist[x][y] + (arr[nx][ny] - '0')) continue;
            //string으로 입력을 받았으므로 
            dist[nx][ny] = dist[x][y] + (arr[nx][ny] - '0');
            q.push({nx, ny});
        }
    }

    cout << dist[n-1][m-1];

    return 0;
}