
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<pair<int, int> > v;

int now = 301;
int check(int start){
    int idx = -1, max = 0;
    for(int i=start; i<v.size(); i++){
        if(v[i].first <= now){
            if(max < v[i].second){
                idx = i;
                max = v[i].second;
            }
            continue;
        }
    }
    return idx;
}


bool compare(pair<int, int> a, pair<int, int> b){
    if(a.first/100 ==  b.first/100){
        if(a.first < b.first){
            return true;
        }else{
            return false;
        }
    }else if(a.first < b.first){
        return true;
    }else{
        return false;
    }
}

int main(){
    int flowers;
    cin >> flowers;

    int stMonth, stDay, endMonth, endDay;

    for(int i=0; i<flowers; i++){
        cin >> stMonth >> stDay >> endMonth >> endDay;
        pair<int, int> p = make_pair(stMonth*100+stDay, endMonth*100+endDay);
        v.push_back(p);
    }

    sort(v.begin(), v.end(), compare);


    int count = 0;
    for(int i=0 ; i<v.size(); i++){
        int a = check(i);
        if(a == -1){
            cout << 0;
            return 0;
        }else{
            count++;
            now = v[a].second;
            if(1130 < now) break;
            i = a;
        }
    }

    if(now <= 1130){
        cout << 0;
    }else{
        cout << count;
    }

    return 0;
}