#include <bits/stdc++.h>
using namespace std;

string find(long long input){
    string temp = "";
    //2진수로 변환
    while(input > 0){
        temp = to_string(input%2)+temp;
        input /= 2;
    }
    
    //포화 이진트리의 노드 수 구하기
    int tempSize = 1, tempInt = 1;
    while(tempSize < temp.length()){
        tempInt *= 2;
        tempSize += tempInt;
    }
    
    //앞에 0채우기
    int a = temp.length();
    for(int i=0; i<tempSize - a; i++){
        temp = "0"+temp;
    }
    return temp;
}

int recursive(string temp, int start, int end){
	//노드가 하나인 경우
    if(temp.size() == 1){
        if(temp[0] == '1') return 1;
        return -1;
    }
    
    int mid = (start+end)/2;   //부모노드
    if(end-start == 2){    //트리가 3개의 노드로만 이루어 져있을때
        if(temp[mid] == '0'){ //부모가 0이라면 자식들도 다 0인지 체크해야함
            if(temp[mid-1] == '0' && temp[mid+1] == '0') return 0;
            else return -1;  //잘못된 트리니까 -1 반환
        }
        return 1; //부모가 0이라면 자식이 뭐든 상관없음
    }
    else{   //노드가 3개 이상일 때
        int left = recursive(temp, start, mid-1);
        int right = recursive(temp, mid+1, end);
        //왼쪽 오른쪽 중에 비정상 트리가 있을 때
        if(left == -1 || right == -1) return -1;
        
	    //비정상 트리가 없고 부모가 1인경우 자식이 어떤지 판단 x
        else if(temp[mid] == '1') return 1;

		//비정상 트리가 없고 부모가 0인경우 자식이 모두 0인지 판단해야 함
        else if(left == 0 && right == 0) return 0;

		//비정상 트리가 없지만 부모가 0인데 자식중에 1이 있는 경우
        else return -1;
    }
}

vector<int> solution(vector<long long> numbers) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    vector<int> answer;
    string temp;
    for(int i=0; i<numbers.size(); i++){
        temp = find(numbers[i]);
        if(recursive(temp, 0, temp.size()-1) != -1){
            answer.push_back(1);
        }else{
            answer.push_back(0);
        }
    }
    
    return answer;
}