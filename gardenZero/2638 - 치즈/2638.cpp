#include <bits/stdc++.h>
using namespace std;

int n, m, cx, cy, nx, ny;
bool isCheese[100][100];
bool isOut[100][100];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

queue<pair<int, int>> cq; //치즈큐
queue<pair<int, int>> aq; //공기큐

void spread(){

    // BFS를 통해 외부 공기 체크
    while(!aq.empty()){
        tie(cx, cy) = aq.front(); aq.pop();
        isOut[cx][cy] = true;  //치즈가 녹아 공기가 된 공간 처리

        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(isCheese[nx][ny] || isOut[nx][ny]) continue;

            isOut[nx][ny] = true;
            aq.push({nx, ny});
        }
    }
}

void check(){

    int cycle = cq.size();
    
    // 모든 치즈에 대해 외부공기 체크
    for(int i=0; i<cycle; i++){
        tie(cx, cy) = cq.front(); cq.pop();

        //외부 공기 체크
        int count = 0;
        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(isOut[nx][ny]) count++;
            if(count >= 2) break;
        }

	//녹을 치즈라면 상태 변경 후 외부공기 큐에 넣는다.
        if(count >= 2){
            isCheese[cx][cy] = false;
            aq.push({cx, cy});
            continue;
        }
        cq.push({cx, cy});
    }
}


int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n >> m;

	//입력
    int input;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> input;
            if(input == 1) {
                isCheese[i][j] = true;
                cq.push({i, j});
            }
        }
    }
    
    isOut[0][0] = true;
    aq.push({0,0});
    int result = 0;
    while(!cq.empty()){
        spread();  //외부공기 퍼트리기
        check();   //없어질 치즈 찾아서 없애기
        result++;
    }

    cout << result;
    
    return 0;
}