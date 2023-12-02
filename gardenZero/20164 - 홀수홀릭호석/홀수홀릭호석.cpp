//
// Created by 정원영 on 11/27/23.
//

#include <iostream>
#include <string>
using namespace std;

int input;
int maxInt = 0, minInt = 100000000;
void findLoop(int a, int count);
int horse(string str);

int main(){
    cin >> input;
    findLoop(input, 0);
    cout << minInt << " " << maxInt;

    return 0;
}

void findLoop(int a, int count){
    int nowHorse = horse(to_string(a));
    //숫자가 두자리인 경우
    if(a <= 99 && a >= 10){
        findLoop(a/10 + a%10 , count + nowHorse);
    }
    //숫자가 한자리 인 경우
    else if(a < 10){
        count += a%2;
        if(count < minInt) minInt = count;
        if(count > maxInt) maxInt = count;
    }
    //숫자가 세자리 이상인 경우
    else{
        string temp = to_string(a);
        for(int i=1; i<=temp.length()-2; i++){
            for(int j=1; j<=temp.length()-i-1; j++){
                int next = stoi(temp.substr(0,i)) + stoi(temp.substr(i, j)) + stoi(temp.substr(i+j, temp.length()-i-j));
                findLoop(next, count +  nowHorse);
            }
        }
    }
}

int horse(string str){
    int count = 0;
    for(int i=0; i<str.length(); i++){
        if( (int)str[i]%2 == 1) count++;
    }
    return count;
}
