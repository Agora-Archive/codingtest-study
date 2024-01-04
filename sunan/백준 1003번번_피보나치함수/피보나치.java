#include<iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, tmp;
    cin >> n;
    int arr_zero[2] = {1, 0};
    int arr_one[2] = {0, 1};
    int temp[2] = {0, 0};
    for (int i = 0; i < n; i++) {
        cin >> tmp;
        if (tmp == 1||tmp==0) {
            cout << arr_zero[tmp] << " " << arr_one[tmp] << "\n";
        }
        else {
            for (int j = 0; j < tmp-1; j++) {
                temp[0] = arr_zero[0] + arr_zero[1];
                temp[1] = arr_one[0] + arr_one[1];
                arr_zero[0] = arr_zero[1];
                arr_one[0] = arr_one[1];
                arr_zero[1] = temp[0];
                arr_one[1] = temp[1];
            }
            cout << arr_zero[1] << " " << arr_one[1] << "\n";
        }
        arr_zero[0] =1;
        arr_zero[1] = 0;
        arr_one[0] = 0;
        arr_one[1] = 1;
    }
}
