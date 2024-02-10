#include <bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(false); cin.tie(0);
  int n, m;
  cin >> n >> m;

  int arr[1000][1000];
  int dist[1000][1000][2];
  int dx[4] = {-1, 0, 1, 0};
  int dy[4] = {0, -1, 0, 1};
  char input;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      cin >> input;
      arr[i][j] = input - '0';
      dist[i][j][0] = dist[i][j][1] = -1; 
    }
  }


  dist[0][0][0] = dist[0][0][1] = 1;
  queue<tuple<int, int, int > >q;
  q.push({0, 0, 0});
  while(!q.empty()){
    int x, y, broken;
    tie(x, y, broken) = q.front(); q.pop();
    
    if(x == n-1 && y == m-1){
      cout << dist[x][y][broken];
      return 0;
    }
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i], ny = y + dy[i];
      if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
      //길이고 지나간적 없을 때
      if(arr[nx][ny] == 0 && dist[nx][ny][broken] == -1){
        dist[nx][ny][broken] = dist[x][y][broken] + 1;
        q.push({nx, ny, broken});
      }

      //벽인데 벽 부슨적 없을 때
      if(arr[nx][ny] == 1 && !broken && dist[nx][ny][1] == -1){
        dist[nx][ny][1] = dist[x][y][broken] + 1;
        q.push({nx, ny, 1});
      }
    }
  }

  cout << -1;

  return 0;
  
}