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

    int cur, next;
    for(int i=arrSize-1; i!=-1; i--){
        if(tmpArr.empty()){
            outputArr.push_back(-1);
        }else if(numArr[i] < tmpArr.back()){
            outputArr.push_back(tmpArr.back());
        }else{
            while(!tmpArr.empty()){
                if(numArr[i] < tmpArr.back()){
                    outputArr.push_back(tmpArr.back());
                    break;
                }
                tmpArr.pop_back();
            }
            if(tmpArr.empty()){
                outputArr.push_back(-1);
            }
        }
        tmpArr.push_back(numArr[i]);
    }

    while(!outputArr.empty()){
        cout << outputArr.back() << " ";
        outputArr.pop_back();
    }

}