#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;

    int start, end;
    vector<pair<int, int> > v;
    while(n--){
        cin >> start >> end;
        v.push_back({start, end});
    }

    sort(v.begin(), v.end());

    //이을 수 있을 때 까지 시작과 끝지점을 이어서 길이를 재는 방식
    int tempStart = v[0].first, tempEnd = v[0].second;
    int sum = 0;
    for(int i=0; i<v.size(); i++){
        int vFirst = v[i].first, vSecond = v[i].second;
        if(tempEnd < vFirst){   //구간이 끊어진 경우
            sum += tempEnd - tempStart;
            tempStart = vFirst;
            tempEnd = vSecond;
        }else if(tempEnd < vSecond){    //이어지고 있는 경우
            tempEnd = vSecond;
        }
    }

    sum += tempEnd - tempStart;
    cout << sum;
    return 0;
}