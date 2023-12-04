#include <iostream>
#include <vector>
#define MAX 101
using namespace std;

int arr[MAX];
int sum[MAX][MAX];
int len = 0;

void Sum(int n, int x, vector<int>& ans) {
	if (x == 0 || n < 1) { //더이상 탐색하지 못함
		if (x == 0) { //합이 정답과 같을 때
			sum[len][0] = ans.size(); //현재 정답 벡터의 크기 저장
			for (int i = 1; i <= ans.size(); i++) { //정답 벡터의 원소 순서대로 저장
				sum[len][i] = ans.at(i - 1);
			}
			len++; //정답 개수 증가
		}
		return;
	}

	if (x >= arr[n]) { //현재 원소보다 남은 수가 많을 때
		ans.push_back(arr[n]); //벡터에 현재 수 넣기
		Sum(n - 1, x - arr[n], ans); //다음 재귀
		ans.pop_back(); //넣은거 빼기
	}
	Sum(n - 1, x, ans); //넣지 않았을 때 재귀
}

int main() {
	char fname[50];
	FILE* fp;
	int n, x;

	printf("파일 이름? ");
	gets_s(fname); //파일 이름 입력
	fp = fopen(fname, "r"); //파일 열기
	if (fp == NULL) {
		printf("파일이 존재하지 않습니다.\n");
		return 0;
	}
	
	fscanf(fp, "%d", &n); //파일에서 n 읽어오기
	for (int i = 1; i <= n; i++) { //파일에서 순서대로 정수 불러와 arr에 저장
		fscanf(fp, "%d", &arr[i]);
	}
	cout << "찾고자 하는 합? ";
	cin >> x; //부분집합 구할 합 입력
	
	vector<int>ans; 
	
	Sum(n, x, ans);

	if (len == 0)
		cout << "부분집합이 존재하지 않습니다.";
	else {
		cout << "[ ";
		for (int i = sum[0][0]; i > 0; i--) {
			cout << sum[0][i];
			if (i > 1)
				cout << ", ";
		}
		cout << " ]";
	}
	return 0;
}