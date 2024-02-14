#include <iostream>
#include <vector>

using namespace std;

int dp[101][100001] = {0};

int main() {
    int n, k, w, v, sum;
    cin >> n >> k;
    vector<pair<int, int>> vec;
    for (int i = 1; i <= n; i++) {
        cin >> w >> v;
        vec.push_back({w, v});
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            if (vec[i - 1].first > j) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - vec[i - 1].first] + vec[i - 1].second);
            }
        }
    }
    cout << dp[n][k];
}