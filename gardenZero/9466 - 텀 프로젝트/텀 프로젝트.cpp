#include <bits/stdc++.h>
using namespace std;


int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    int tc;
    cin >> tc;

    int student, cur, nxt, result;
    int arr[100001];
    bool visited[100001];

    queue<int> q;
    while(tc--){
        cin >> student;
        for(int i=1; i<=student; i++){
            cin >> arr[i];
            visited[i] = false;
        }


        result = 0;
        vector<int> isTeam;
        for(int i=1; i<=student; i++){
            if(visited[i]) continue;    //이미 지나간 길이면 패스

            //팀을 이룰 수 있는지 체크
            q.push(i);
            cur = i;
            while(true){
                isTeam.push_back(cur);
                visited[cur] = true;
                nxt = arr[cur];
                if(visited[nxt]) break;
                cur = nxt;
            }

            //사이클이 있는지 체크
            int idx = -1;
            for(int j=0; j<isTeam.size(); j++){
                if(nxt == isTeam[j]) {
                    idx = j;
                    break;
                }
            }

            if(idx == -1){  //사이클이 없는 경우
                result += isTeam.size();
            }else{      //사이클이 생긴경우
                result += idx;
            }
            isTeam.clear();
        }

        cout << result << "\n";
    }
}
