#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> diceVec;
vector<int> resultVec;
vector<int> selVec;
vector<int> sumA;
vector<int> sumB;
int loop;
long long tempSum = 0;
long long result = 0;

//count는 diceVec의 Index라고 보면됨
void recursive(int count, int bit){
    if(count == selVec.size()){
        if(bit == 1) sumA.push_back(tempSum);
        else sumB.push_back(tempSum);
        return;
    }
    
    //보고 있는 주사위가 아니라면 패스
    if(selVec[count] != bit) recursive(count+1, bit);
    else{
        for(int i=0; i<6; i++){
            tempSum += diceVec[count][i];
            recursive(count+1, bit);
            tempSum -= diceVec[count][i];
        }
    }
}

void findAnswer(){
    long long count = 0;
    count = findCount();

    if(count > result){
        result = count;
        resultVec.clear();
        for(int i=0; i<loop; i++) if(selVec[i] == 1) resultVec.push_back(i+1);
    }
}

//이진탐색으로 개수 파악
long long findCount(){
    long long count = 0;
    for(int i=0; i<sumA.size(); i++){
        int left = 0, right = sumB.size()-1, mid;
        //같은걸 찾는 것이 아니라 작은 것의 개수를 찾는 것임
        while(left <= right){
            mid = (left+right)/2;
            if(sumB[mid] < sumA[i]) left = mid+1;
            else right = mid-1;
        }
        
        count += left;
    }
    return count;
}

vector<int> solution(vector<vector<int>> dice) {
    diceVec = dice;
    loop = diceVec.size();
    
    for(int i=0; i<loop; i++){
        if(i<loop/2) selVec.push_back(0);
        else selVec.push_back(1);
    }
    
    //모든 경우를 파악
    do{
        recursive(0, 1);
        recursive(0, 0);
        sort(sumB.begin(), sumB.end());
        
        findAnswer();
        sumA.clear(); sumB.clear();
    }while(next_permutation(selVec.begin(), selVec.end()));

    
    return resultVec;
}