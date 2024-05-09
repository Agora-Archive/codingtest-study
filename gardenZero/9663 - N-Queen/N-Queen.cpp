#include <bits/stdc++.h>
using namespace std;

int n;
int result = 0;
int arr[15][15];
int dy[3] = {-1, 0, 1};

void check(int x, int y){
    arr[x][y]++;
    for(int i=0; i<3; i++){
        int nx = x, ny = y;
        while(true){
            nx += 1; ny += dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            arr[nx][ny]++;
        }
    }
}

void uncheck(int x, int y){
    arr[x][y]--;
    for(int i=0; i<3; i++){
        int nx = x, ny = y;
        while(true){
            nx += 1; ny += dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            arr[nx][ny]--;
        }
    }
}

void recursive(int count){
    if(count == n){
        result++; return;
    }
    for(int i=0; i<n; i++){
        if(!arr[count][i]){
            check(count, i);
            recursive(count+1);
            uncheck(count, i);
        }
    }
}

int main(){
    cin >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            arr[i][j] = 0;
        }
    }
    
    recursive(0);

    cout << result;

    return 0;
}
