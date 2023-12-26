#include <iostream>
using namespace std;

int arr[51][51] = { 0 };
int visited[51][51] = { 0 };

void check(int x, int y) {
	visited[x][y] = 1;
	if (arr[x - 1][y] == 1 && !visited[x-1][y]) {
		check(x - 1, y);
	}
	if (arr[x][y - 1] == 1 && !visited[x][y-1]) {
		check(x, y - 1);
	}
		
	if (arr[x + 1][y] == 1 && !visited[x+1][y]) {
		check(x + 1, y);
	}
		
	if (arr[x][y + 1] == 1 && !visited[x][y+1]) {
		check(x, y + 1);
	}
}

void clear(int arr[51][51]) {
	for (int i = 0; i < 51; i++) {
		for (int j = 0; j < 51; j++) {
			arr[i][j] = 0;
		}
	}
}

int main() {
	int t, m, n, k, x, y;

	int sum;
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> m >> n >> k;
		sum = 0;
		for (int j = 0; j < k; j++) {
			cin >> x >> y;
			arr[x][y] = 1;
		}
		for (int a = 0; a < m; a++) {
			for (int j = 0; j < n; j++) {
				if (arr[a][j] == 1 && !visited[a][j]) {
					check(a, j);
					sum++;
				}
			}
		}
		cout << sum << "\n";
		clear(arr);
		clear(visited);
	}
	
}