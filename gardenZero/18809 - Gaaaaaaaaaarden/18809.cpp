#include <bits/stdc++.h>
using namespace std;

#define time first
#define bae second

int n, m, g, r;
int cx, cy, nx, ny;
char cc;
int arr[51][51];
vector<pair<int, int>> canUse;
vector<char> baeYang;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0 , 1};
int answer = 0;

void find(){
    pair<int, char> baeYangArr[51][51] = {};

	//초기화
    queue<tuple<int, int, char>> q;
    for(int i=0; i<baeYang.size(); i++){
        if(baeYang[i] != 'Z') {
            cx = canUse[i].first; cy = canUse[i].second;
            baeYangArr[cx][cy] = {1, baeYang[i]};
            q.push({cx, cy, baeYang[i]});
        }
    }


    int result = 0;
    while(!q.empty()){
        tie(cx, cy, cc) = q.front(); q.pop();

		// 배양액이 퍼져서 Queue에 들어왔지만 이전 단계에서 꽃을 피운 경우
        if(baeYangArr[cx][cy].bae == 'F') continue;

        for(int i=0; i<4; i++){
            nx = cx + dx[i]; ny = cy + dy[i];

            if(nx < 0 || nx > n || ny < 0 || ny > m) continue;
            // 호수이거나 같은 배양액이거나 꽃이 핀 곳이라면 continue
            if(arr[nx][ny] == 0) continue;
            if(baeYangArr[nx][ny].bae == cc || baeYangArr[nx][ny].bae == 'F') continue;

            if(baeYangArr[nx][ny].time == 0){  // 아무것도 없는 경우
                baeYangArr[nx][ny].time = baeYangArr[cx][cy].time + 1;
                baeYangArr[nx][ny].bae = cc;
                q.push({nx, ny, cc});
            }else{ // 상대 배양액이 있는 경우
                // 같은 시간에 도착한 경우
                if(baeYangArr[nx][ny].time == baeYangArr[cx][cy].time + 1){
                    baeYangArr[nx][ny].bae = 'F';
                    result++;
                }
            }
        }
    }
    answer = max(answer, result);
    
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); 

    cin >> n >> m >> g >> r;

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> arr[i][j];
            if(arr[i][j] == 2){
                canUse.push_back({i, j});
            }
        }
    }

    int canUseSize = canUse.size();
    for(int i=0; i<g; i++) baeYang.push_back('G');
    for(int i=0; i<r; i++) baeYang.push_back('R');
    for(int i=0; i<canUseSize - g - r; i++) baeYang.push_back('Z');

    sort(baeYang.begin(), baeYang.end());
    
    do{
        find();
    }while(next_permutation(baeYang.begin(), baeYang.end()));

    cout << answer;
    return 0;
}