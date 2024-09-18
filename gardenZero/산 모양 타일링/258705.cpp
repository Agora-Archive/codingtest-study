#include <bits/stdc++.h>
using namespace std;

int dp[100001][2];

int solution(int n, vector<int> tops) {
    
    dp[0][0] = 2 + tops[0];
    dp[0][1] = 1;
    
    for(int i=1; i<n; i++){
        dp[i][0] = dp[i-1][0] * (2 + tops[i]) + dp[i-1][1] * (1 + tops[i]);
        dp[i][1] = dp[i-1][0] + dp[i-1][1];
        
        dp[i][0] %= 10007;
        dp[i][1] %= 10007;
    }
    
    return (dp[n-1][0] + dp[n-1][1]) % 10007;
}