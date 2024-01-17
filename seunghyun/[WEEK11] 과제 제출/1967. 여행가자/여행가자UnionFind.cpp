#include <iostream>

using namespace std;

int n, m, arr[201];

int Find(int a) {
    if (arr[a] == a) //부모 노드가 자신과 같으면 종료
        return a;
    return arr[a] = Find(arr[a]); //재귀로 부모 찾음
}

void Union(int i, int j) {
    i = Find(i); //i의 부모
    j = Find(j); //j의 부모
    if (i == j) //i와 j 같으면 종료
        return;
    arr[i] = j; //다르면 i의 부모를 j로 바꿔서 합침
}

int main() {
    cin >> n >> m;
    int input;

    for (int i = 1; i <= n; i++)
        arr[i] = i;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> input;
            if (input == 1) //i와j가 연결되어 있음
                Union(i, j);  //i와 j연결하기

        }
    }
    int cur, next;
    bool trip = true;
    cin >> cur;
    for (int i = 1; i < m; i++) {
        cin >> next;
        if (Find(cur) != Find(next)) {//현재 위치와 다음 위치가 연결되어 있지 않음
            trip = false;
            break;
        }
        cur = next;
    }
    if (trip)
        cout << "YES";
    else
        cout << "NO";
}