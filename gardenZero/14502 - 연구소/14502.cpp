#include <bits/stdc++.h>
using namespace std;

#define x first
#define y second

int arr[9][9] ={0, };
int n, m;
vector<pair<int, int>> virus;
vector<pair<int, int>> blank;
vector<int> selected;
int answer = -1;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

/* 바이러스 퍼트리기 */
void bfs(){
    int barr[9][9] = {0, };

    queue<pair<int, int>> q;
    for(int i=0; i<virus.size(); i++){
        q.push(virus[i]);
        barr[virus[i].x][virus[i].y] = 2;
    }

    int cx, cy;
    while(!q.empty()){
        tie(cx, cy) = q.front(); q.pop();

        for(int i=0; i<4; i++){
            int nx = cx + dx[i], ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(arr[nx][ny] == 1 || barr[nx][ny] == 2) continue;
            barr[nx][ny] = 2;
	        q.push({nx, ny});
        }
    }

    int result = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(barr[i][j] != 2 && arr[i][j] != 1) result++;
        }
    }


    answer = max(answer, result);
}

/* 벽 세우고 BFS돌린 후 벽 내리기 */
void back(){
    for(int i=0; i < selected.size(); i++) {
         if(selected[i] == 1) {
            arr[blank[i].x][blank[i].y] = 1;
         }
    }

    bfs();

    for(int i=0; i < selected.size(); i++) {
         if(selected[i] == 1) {
            arr[blank[i].x][blank[i].y] = 0;
         }
    }
}

/* 입력 받고 어떤 벽을 세울지 정하기 */
int main(){
    cin >> n >> m;

    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> input;
            arr[i][j] = input;
            if(input == 2){
                virus.push_back({i, j});
            }
            if(input == 0) {
                blank.push_back({i, j});
                selected.push_back(0);
            }
        }
    }

    selected[0] = selected[1] = selected[2] = 1;
    sort(selected.begin(), selected.end());

    do{
        back();
    }while(next_permutation(selected.begin(), selected.end()));


    cout << answer;

    return 0;
}