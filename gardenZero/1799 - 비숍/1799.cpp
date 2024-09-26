#include <bits/stdc++.h>
using namespace std;

int n;
int arr[10][10];
set<int> usedDif;
int result;

bool canUse(int stCol, int dir) {

    int edge = dir == -1 ? -1 : n;
    for(int cx = 0, cy = stCol; cy != edge; cx++, cy += dir){
        int dif = dir == -1 ? cx - cy : cx + cy;
        
        if(cx < 0 || cx >= n || cy < 0 || cy >= n) continue;
        //둘 수 있고 반대각선이 안쓰였다면
        if(arr[cx][cy] && usedDif.find(dif) == usedDif.end()) return true;
    }

    return false;
}

void back(int stCol, int dir, int count) {
    //
    if(stCol == 2*n - 1 || stCol == - n) {
        result = max(result, count);
        return;
    } 

    //해당 대각선에 사용할 수 있는 칸이 하나도 없으면 다음 대각선
    if(!canUse(stCol, dir)) {
        back(stCol-dir, dir, count);
        return;
    }

    int edge = dir == -1 ? -1 : n;
    //dir은 col에 더해지는 수
    //왼쪽 위 시작일 때는 -1, 오른쪽 위 시작일때는 +1
    for(int cx = 0, cy = stCol; cy != edge; cx++, cy += dir){
        int dif = dir == -1 ? cx - cy : cx + cy;
        
        if(cx < 0 || cx >= n || cy < 0 || cy >= n) continue;
        //못두는 곳이거나 이미 쓰인 반대각선이면
        if(!arr[cx][cy] || usedDif.find(dif) != usedDif.end()) continue;

        usedDif.insert(dif);
        back(stCol-dir, dir, count+1);
        usedDif.erase(dif);
    }

}

int main(){

    cin >> n; 
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> arr[i][j];
        }
    }
    result = 0;
    back(0, -1, 0);
    back(n-1, 1, 0);
    
    cout << result;

    return 0;
}