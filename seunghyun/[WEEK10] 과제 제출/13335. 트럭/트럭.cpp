#include <iostream>
#include <queue>

using namespace std;

int main() {
    queue<int> truck;
    queue<int> bridge;
    int n, w, l, weight, time = 0, sum = 0;

    cin >> n >> w >> l;
    for (int i = 0; i < n; i++) {
        cin >> weight;
        truck.push(weight);
    }

    weight = 0;
    while (!truck.empty()) {
        if (bridge.size() >= w) { //다리가 꽉찼다면 제일 앞 트럭 팝
            weight -= bridge.front();
            bridge.pop();
        }
        if (weight + truck.front() > l) //다리의 최대하중 넘어가면 0 푸시
            bridge.push(0);
        else {                       //최대하중 넘지 않으면 제일 앞 트럭 푸시
            bridge.push(truck.front());
            weight += truck.front();
            truck.pop();
        }
        time++; //시간 증가

    }
    cout << time + w; //제일 마지막 트럭은 다리의 길이만큼 이동해야하므로 시간에 다리의 길이만큼 더해주기
}