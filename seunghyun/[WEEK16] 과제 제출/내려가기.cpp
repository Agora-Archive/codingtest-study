#include <iostream>
#include <vector>


using namespace std;

int min_arr[100001][3], max_arr[10001][3], input[3];
int n;

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> input[0] >> input[1] >> input[2];
        for (int j = 0; j < 3; j++) {
            max_arr[i][j] = input[j];
            min_arr[i][j] = input[j];
        }
        if (i == 0) {
            continue;
        }
        max_arr[i][0] = max(max_arr[i - 1][0], max_arr[i - 1][1]) + input[0];
        max_arr[i][1] = max(max_arr[i - 1][0], max(max_arr[i - 1][1], max_arr[i - 1][2])) + input[1];
        max_arr[i][2] = max(max_arr[i - 1][1], max_arr[i - 1][2]) + input[2];

        min_arr[i][0] = min(min_arr[i - 1][0], min_arr[i - 1][1]) + input[0];
        min_arr[i][1] = min(min_arr[i - 1][0], min(min_arr[i - 1][1], min_arr[i - 1][2])) + input[1];
        min_arr[i][2] = min(min_arr[i - 1][1], min_arr[i - 1][2]) + input[2];
    }
    int min = min_arr[n - 1][0], max = max_arr[n - 1][0];
    for (int i = 1; i < 3; i++) {
        if (min > min_arr[n - 1][i])
            min = min_arr[n - 1][i];
        if (max < max_arr[n - 1][i])
            max = max_arr[n - 1][i];
    }
    cout << max << " " << min;
}