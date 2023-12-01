#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

vector<int> tree; // 들어간 건물의 순서로 값을 저장하는 벡터
vector< vector<int>> perfectTwoLoseTree; // tree 구조의 건물 모양 출력용
1 6 4 3 5 2 7
void build(int start, int end, int depth) {
	// 현 시점 가장 높은 depth에 중앙값 저장 ( depth++ )
	perfectTwoLoseTree[depth++].push_back(tree[(start + end) / 2]);

	if (start == end) return; // 마지막 level 일 경우 종료
	build(start, (start+ end) / 2 - 1, depth); // 왼쪽 범위 재귀함수
	build((start + end) / 2 + 1, end, depth); // 오른쪽 범위 재귀함수
}

int main() {
	int K; 
	cin >> K; // depth 입력
	tree.resize(pow(2, K)); // 2^k 크기만큼 할당
	perfectTwoLoseTree.resize(K); //depth 만큼 2차원 벡터의 행 공간 할당

	for (int i = 1; i < tree.size(); i++) cin >> tree[i]; // 건물 순서대로 벡터에 삽입

	build(1, tree.size()-1, 0); // start : 1, end : 건물 개수, depth : 0

	for (int i = 0; i < K; i++) { // 2차원 벡터(tree) 출력
		for (int j = 0; j < perfectTwoLoseTree[i].size(); j++) {
			cout << perfectTwoLoseTree[i][j] << ' ';
		}
		cout << "\n";
	}
}