#include <bits/stdc++.h>
using namespace std;

int n, m, input;
int arr[1001][1001];
int point[1001];
int minIdx, maxIdx;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin >> n >> m;

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> arr[i][j];
        }
    }

    for(int i=0; i<n; i++) sort(arr[i], arr[i] + m);

    int result = 0x7f7f7f7f;
    while(true){
        int minVar = 0x7f7f7f7f, maxVar = -1;
        int minIdx;

        for(int i=0; i<n; i++){
            if(arr[i][point[i]] < minVar){
                minVar = arr[i][point[i]];
                minIdx = i;
            }
            if(arr[i][point[i]] > maxVar){
                maxVar = arr[i][point[i]];
            }
        }

        result = min(result, maxVar - minVar);

        point[minIdx]++;
        if(point[minIdx] >= m) break;
    }

    cout << result;
    return 0;
}