#include <iostream>
#include <vector>

using namespace std;

int min_arr[3], max_arr[3], input[3], temp[3];
int n;

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> input[0] >> input[1] >> input[2];
        if (i == 0) {
            for (int j = 0; j < 3; j++) {
                max_arr[j] = input[j];
                min_arr[j] = input[j];
            }
            continue;
        }

        temp[0] = max(max_arr[0], max_arr[1]) + input[0];
        temp[1] = max(max_arr[0], max(max_arr[1], max_arr[2])) + input[1];
        temp[2] = max(max_arr[1], max_arr[2]) + input[2];
        for (int j = 0; j < 3; j++)
            max_arr[j] = temp[j];
        temp[0] = min(min_arr[0], min_arr[1]) + input[0];
        temp[1] = min(min_arr[0], min(min_arr[1], min_arr[2])) + input[1];
        temp[2] = min(min_arr[1], min_arr[2]) + input[2];
        for (int j = 0; j < 3; j++)
            min_arr[j] = temp[j];
    }
    int min = min_arr[0], max = max_arr[0];
    for (int i = 1; i < 3; i++) {
        if (min > min_arr[i])
            min = min_arr[i];
        if (max < max_arr[i])
            max = max_arr[i];
    }
    cout << max << " " << min;
}