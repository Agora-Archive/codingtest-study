#include <iostream>
#include <string>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m, sum = 0;
    string input;
    cin >> n >> m >> input;

    for (int i = 0; i < m; i++) {
        int temp = 0;
        if (input[i] == 'I') {
            while (true) {
                if (input[i + 1] != 'O' || input[i + 2] != 'I') //I를 이미 찾았으므로 뒤에 OI가 있는지 확인
                    break;
                temp++;
                if (temp == n) { //현재까지 찾은 io의 길이가 찾고자 하는 io의 길이와 같다면
                    temp--; //다음번 확인을 위해 길이 -1
                    sum++;
                }
                i += 2; //i+1, i+2를 확인 했으므로 i+2해주기
            }
        }
    }
    cout << sum;
}