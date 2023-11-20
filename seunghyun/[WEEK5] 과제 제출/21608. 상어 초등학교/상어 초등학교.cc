#include <iostream>
#include <algorithm>
#define MAX 21
using namespace std;

void search(int arr[][5], int idx, int near[][MAX], int ans[][MAX], int n) { //앉을 자리 찾는 함수
	int candi[MAX * MAX][3];//앉을 수 있는 자리 후보 저장 배열
	int sum = 0;
	int near_cnt;//주변 좋아하는 학생 수 계산

	for (int i = 0; i < n; i++) { //자리별로 돌아가며 비어있으면 해당 자리 주변에 좋아하는 학생이 몇명인지 계산
		for (int j = 0; j < n; j++) {
			near_cnt = 0;
			if (ans[i][j] != 0)
				continue;
			if (i != 0) {
				if (ans[i - 1][j] > 0) {
					if (count(arr[idx] + 1, arr[idx] + 5, ans[i - 1][j]))
						near_cnt++;
				}
			}
			if (j != 0) {
				if (ans[i][j - 1] > 0) {
					if (count(arr[idx] + 1, arr[idx] + 5, ans[i][j - 1]))
						near_cnt++;
				}
			}
			if (j != n - 1) {
				if (ans[i][j + 1] > 0) {
					if (count(arr[idx] + 1, arr[idx] + 5, ans[i][j + 1]))
						near_cnt++;
				}
			}
			if (i != n - 1) {
				if (ans[i + 1][j] > 0) {
					if (count(arr[idx] + 1, arr[idx] + 5, ans[i + 1][j]))
						near_cnt++;
				}
			}
			if (near_cnt >= 0) { //주변에 좋아하는 학생이 없어도 일단 후보에 등록
				candi[sum][0] = i;
				candi[sum][1] = j;
				candi[sum++][2] = near_cnt;
			}
		}
	}

	int max[3] = { candi[0][0],candi[0][1],candi[0][2] };
	for (int i = 1; i < sum; i++) { //앉을 자리 선정
		if (candi[i][2] == max[2]) {
			if (near[candi[i][0]][candi[i][1]] > near[max[0]][max[1]]) { //주변 좋아하는 학생 수 같으면 주변에 비어있는 책상이 많은 자리로 선정
				max[0] = candi[i][0];
				max[1] = candi[i][1];
				max[2] = candi[i][2];
			}
		}
		if (candi[i][2] > max[2]) { //주변에 좋아하는 학생 수가 더 많으면 해당 자리로 변경
			max[0] = candi[i][0];
			max[1] = candi[i][1];
			max[2] = candi[i][2];
		}
	}
	ans[max[0]][max[1]] = arr[idx][0]; //결과로 나온 자리에 해당 학생 앉기
	near[max[0]][max[1]] = 0;
	if (max[0] != 0) {
		near[max[0] - 1][max[1]] -= 1;
	}
	if (max[0] != n - 1) {
		near[max[0] + 1][max[1]] -= 1;
	}
	if (max[1] != 0) {
		near[max[0]][max[1] - 1] -= 1;
	}
	if (max[1] != n - 1) {
		near[max[0]][max[1] + 1] -= 1;
	}
}

float satisfaction(int arr[][5], int ans[MAX][MAX], int n) { //만족도 계산 함수
	int sum = 0;
	int near_sum = 0; //좋아하는 학생 수 
	int idx = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			near_sum = 0;
			for (int k = 0; k < n * n; k++) {
				if (ans[i][j] == arr[k][0]) {
					idx = k;
					break;
				}
			}
			if (i != 0) {
				if (count(arr[idx] + 1, arr[idx] + 5, ans[i - 1][j]))
					near_sum++;
			}
			if (j != 0) {
				if (count(arr[idx] + 1, arr[idx] + 5, ans[i][j - 1]))
					near_sum++;
			}
			if (j != n - 1) {
				if (count(arr[idx] + 1, arr[idx] + 5, ans[i][j + 1]))
					near_sum++;
			}
			if (i != n - 1) {
				if (count(arr[idx] + 1, arr[idx] + 5, ans[i + 1][j]))
					near_sum++;
			}
			if (near_sum == 1)
				sum += 1;
			else if (near_sum == 2)
				sum += 10;
			else if (near_sum == 3)
				sum += 100;
			else if (near_sum == 4)
				sum += 1000;
		}
	}
	return sum;
}

int main() {
	int n;
	cin >> n;
	int arr[MAX * MAX][5];
	int ans[MAX][MAX] = { 0 }, near[MAX][MAX] = { 0 };
	for (int i = 0; i < n * n; i++) {
		cin >> arr[i][0] >> arr[i][1] >> arr[i][2] >> arr[i][3] >> arr[i][4];
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if ((i == 0 && (j == 0 || j == n - 1)) || (i == n - 1 && (j == 0 || j == n - 1)))
				near[i][j] = 2;
			else if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
				near[i][j] = 3;
			else
				near[i][j] = 4;
		}
	}
	ans[1][1] = arr[0][0];
	near[1][1] = 0;
	near[0][1] -= 1;
	near[1][0] -= 1;
	near[2][1] -= 1;
	near[1][2] -= 1;

	for (int i = 1; i < n * n; i++) {
		search(arr, i, near, ans, n);
	}

	cout << satisfaction(arr, ans, n);
	return 0;
}