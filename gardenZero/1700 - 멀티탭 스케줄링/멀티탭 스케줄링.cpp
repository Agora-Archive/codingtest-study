#include <iostream>
#include <vector>
using namespace std;

int hole, action;
vector<int> plugged;
vector<int> turn;

bool check(int index){
    for (int i = 0; i < plugged.size(); i++) {
        if (plugged[i] == turn[index]) return true;
    }

    if (plugged.size() < hole){
        plugged.push_back(turn[index]);
        return true;
    }
    else {
        int unplug = 0, lastIdx = -1;
        for (int i = 0; i < hole; i++) {
            bool noMore = true;
            for (int j = index + 1; j < turn.size(); j++) {
                if (plugged[i] == turn[j]) {
                    if (lastIdx < j) {
                        unplug = i;
                        lastIdx = j;
                    }
                    noMore = false;
                    break;
                }
            }
            if (noMore) {
                unplug = i; break;
            }
        }

        plugged[unplug] = turn[index];
        return false;
    }


}

int main() {
    cin >> hole >> action;

    int input, total = 0;
    for (int i = 0; i < action; i++) {
        cin >> input;
        turn.push_back(input);
    }

    for (int i = 0; i < action; i++) {
        if (!check(i)){
            total++;
        }
    }

    cout << total;

    return 0;
}