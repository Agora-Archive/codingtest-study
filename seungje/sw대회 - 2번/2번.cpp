#include <iostream>
using namespace std;

int main() {
	long long int v[71] = { 0,1,2,4 };
	int n;
	cin >> n;
	for (int i = 4; i <= n; i++) v[i] = v[i - 1] + v[i - 2] + v[i - 3];
	
	cout << v[n];
}