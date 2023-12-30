#include <iostream>
#include <vector>
#define MAX 101
using namespace std;

int arr[MAX];
int sum[MAX][MAX];
int len = 0;
bool found = false; //문제의 전역변수

void Sum(int n, int x, vector<int>& ans) {
    if (found) //이미 찾았으면 모든 재귀 종료
        return;
	if (x == 0 || n < 1) { //더이상 탐색하지 못함
		if (x == 0) { //합이 정답과 같을 때
			 for (int i = 1; i <= ans.size(); i++) { //정답 벡터의 원소 순서대로 출력
                cout<<ans.at(i-1)<<" ";
			 }
             found = true;
             cout<<endl;
             return;
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
	gets(fname); //파일 이름 입력
	fp = fopen(fname, "r"); //파일 열기
	if (fp == NULL) {
		printf("파일이 존재하지 않습니다.\\n");
		return 0;
	}

	fscanf(fp, "%d", &n); //파일에서 n 읽어오기
	for (int i = 1; i <= n; i++) { //파일에서 순서대로 정수 불러와 arr에 저장
		fscanf(fp, "%d", &arr[i]);
	}
	cout << "찾고자 하는 합? ";
	cin >> x; //부분집합 구할 합 입력

	vector<int>ans;
	int suc = 0;
	Sum(n, x, ans);
    if (!found)
        cout<<"부분집합이 존재하지 않습니다.";

	return 0;
}
