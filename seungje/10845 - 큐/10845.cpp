#include <iostream>
#include <string>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N; //명령의 수
	int num; //큐에 저장할 정수값
	int s = 0; //큐 포인터
	int count = 0; //큐에 저장된 정수의 개수
	string req; //명령
	int que[10001]; //큐

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> req;
		
		if (req == "push") {
			cin >> num;
			que[s] = num;
			count++; 
			s++; 
		}
		else if (req == "pop") {
			if (count > 0) {
				cout << que[0] << '\n';
				for (int j = 0; j < count; j++) {
					que[j] = que[j + 1];
				}
				count--;
				s--;
			}
			else {
				cout << -1 << '\n';
			}
		}
		else if (req == "size") {
			cout << count << '\n';
		}
		else if (req == "empty") {
			if(count > 0 )cout << 0 << '\n';
			else cout << 1 << '\n';
		}
		else if (req == "front") {
			if (count > 0) cout << que[0] << '\n';
			else cout << -1 << '\n';
		}
		else if (req == "back") {
			if(count>0) cout << que[s-1] << '\n';
			else cout << -1 << '\n';
		}
	}

	
	return 0;
}
