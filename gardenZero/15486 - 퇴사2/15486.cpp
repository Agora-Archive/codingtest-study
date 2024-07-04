#include <bits/stdc++.h>
using namespace std;

int tp[1500001][2]; //소요시간과 돈을 입력받는 배열
int dp[1500001]; //벌수 있는 돈의 최댓값을 저장하는 배열

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    
    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> tp[i][0] >> tp[i][1];
    }

    dp[0] = 0;
    for(int i=1; i<=n; i++){
        int day = n - i + 1;    //현재날을 구하기 위해 전체날 - 남은날 + 1
        int cost = tp[day][0];
        int value = tp[day][1];
        
        if(cost > i) dp[i] = dp[i-1];
        else dp[i] = max(dp[i-1], dp[i-cost] + value);
    }

    cout << dp[n];

    return 0;
}