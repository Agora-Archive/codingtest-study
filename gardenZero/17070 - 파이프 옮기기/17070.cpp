#include <bits/stdc++.h>
using namespace std;

int n;
int cx, cy, ct, nx, ny;

bool wall[16][16];
int arr[16][16];
int dx[3] = {0, 1, 1};
int dy[3] = {1, 0, 1};

queue<tuple<int, int, int>> q;

//갈 수 있는 곳인지 체크후 q에 넣기
void trying(int i){
    nx = cx + dx[i]; ny = cy + dy[i];

    if(nx >= n || ny >= n) return;

    if(i == 0 || i == 1){
        if(wall[nx][ny]) return;
    }else{
        if(wall[nx][ny] || wall[nx-1][ny] || wall[nx][ny-1]) return;
    }

    arr[nx][ny]++;
    q.push({nx, ny, i});
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n;

    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> input;
            if(input == 1) wall[i][j] = true;
        }
    }

    // 0: 가로 1: 세로 2: 대각선
    q.push({0, 1, 0});
    while(!q.empty()){
        tie(cx, cy, ct) = q.front(); q.pop();

        if(ct == 0){
            trying(0);
            trying(2);
        }else if(ct == 1){
            trying(1);
            trying(2);
        }else{
            trying(0);
            trying(1);
            trying(2);
        }
    }

    cout << arr[n-1][n-1];

    return 0;
}