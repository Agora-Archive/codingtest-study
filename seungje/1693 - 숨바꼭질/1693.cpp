#include <bits/stdc++.h>
using namespace std;
int ans;

void hide(int n, int k, int cnt) {
	if (cnt > ans) return;
	if (k > n) {
		ans = ans > cnt + (k - n) ? cnt + (k - n) : ans;

		if (k % 2 == 0) hide(n, k/2, cnt + 1);
		else {
			hide(n, (k - 1) / 2, cnt + 2);
			hide(n, (k + 1) / 2, cnt + 2);
		}
	}
	else if (k == n) ans = ans > cnt ? cnt : ans;
	else ans = ans > cnt + (n - k) ? cnt + (n - k) : ans;
}

int main() {
	int N, K;
	cin >> N >> K;

	if (N >= K) cout << N - K;
	else {
		ans = K - N;
		hide(N, K, 0);
		cout << ans;
	}

}