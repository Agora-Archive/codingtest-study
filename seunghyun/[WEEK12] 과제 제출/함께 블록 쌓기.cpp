#include <iostream>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

int n, m, h, sum = 0;
int dp[51][1001] = {0};

int main() {
    string input; //입력을 위한 string
    vector<int> block[51]; //각 학생마다 블럭 벡터에 저장
    int num;
    cin >> n >> m >> h;
    cin.ignore();
    for (int i = 1; i <= n; i++) {
        getline(cin, input);
        stringstream stream;
        stream.str(input);
        while (stream >> num) //string 잘라서 벡터에 저장
            block[i].push_back(num);
    }


    for (int i = 0; i <= n; i++) //각 학생들 모두 0의 높이를 쌓을 확률 1로 초기화
        dp[i][0] = 1;
    for (int i = 1; i <= n; i++) { //학생 번호
        for (int j = 1; j <= h; j++) { //높이
            for (int k = 0; k < block[i].size(); k++) { //각 학생이 갖고 있는 블럭
                if (j >= block[i][k]) { //현재 높이보다 i번 학생의 k번째 블럭의 높이가 작다면
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - block[i][k]]) %
                               10007; //i-1번째 학생이 j-block[i][k]높이를 쌓을 경우의 수 + 현재 경우의 수
                }
            }
            dp[i][j] = (dp[i][j] + dp[i - 1][j]) % 10007; //i-1번째 학생이 블럭을 쌓지 않을 경우 더하기
        }
    }
    cout << dp[n][h]; //결과 출력

}