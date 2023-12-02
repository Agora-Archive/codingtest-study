#include <iostream>
#include <vector>
using namespace std;

int main(){
    int arrSize, inputNum;
    cin >> arrSize;

    vector<int> numArr, tmpArr, outputArr;
    for(int i=0; i<arrSize; i++){
        cin >> inputNum;
        numArr.push_back(inputNum);
    }

    //뒤에서부터 tmpArr수와 현재 수를 비교, tmpArr에는 큰 수 후보 저장
    int cur, next;
    for(int i=arrSize-1; i!=-1; i--){
        while(!tmpArr.empty() && numArr[i] < tmpArr.back()){
            tmpArr.pop_back();
        }
        if(tmpArr.empty()){
            outputArr.push_back(-1);
        }else{
            outputArr.push_back(tmpArr.back());
        }

        tmpArr.push_back(numArr[i]);
    }

    while(!outputArr.empty()){
        cout << outputArr.back() << " ";
        outputArr.pop_back();
    }

}