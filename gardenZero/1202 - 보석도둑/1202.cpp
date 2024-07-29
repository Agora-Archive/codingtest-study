#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    pair<int, int> bosuk[300001];
    multiset<int> bag;

	//sort할 때 편하도록 가격을 pair.first로 입력받음
    for(int i=0; i<n; i++){
        cin >> bosuk[i].second >> bosuk[i].first;
    }
    sort(bosuk, bosuk+n);

    int input;
    for(int i=0; i<k; i++){
        cin >> input;
        bag.insert(input);
    }

    long long answer = 0;
    for(int i=n-1; i>=0; i--){
	    //넣을 수 있는 가방 중 가장 작은 것을 찾음
        auto it = bag.lower_bound(bosuk[i].second);  
        if(it == bag.end()) continue;   //없는 경우에 해당 보석은 못넣음
        bag.erase(it);
        answer += bosuk[i].first;
    }

    cout << answer; 

    return 0;
}