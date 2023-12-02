//
// Created by 정원영 on 11/18/23.
//

#include <iostream>

using namespace std;


int check(int arr[][5]){
    int count = 0;
    int count1, count2;
    //가로 세로 빙고 체크
    //count1은 가로 count2는 세로 동시 체크
    for(int i=0; i<5; i++){
        count1 = 0 , count2 = 0;
        for(int j=0; j<5; j++){
            if(arr[i][j] == 0) count1++;
            if(arr[j][i] == 0) count2++;
        }
        if(count1 == 5) count += 1;
        if(count2 == 5) count += 1;
    }

    //대각선 빙고 체크
    count1 = 0, count2 = 0;
    for(int i=0; i<5; i++){
        if(arr[i][i] == 0) count1++;
        if(arr[i][4-i] == 0) count2++;
    }
    if(count1 == 5) count += 1;
    if(count2 == 5) count += 1;

    if(count >= 3) return 1;
    else return 0;
}
int main(){
    int arr[5][5];
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            cin >> arr[i][j];
        }
    }

    int input;
    for(int i=0; i<25; i++){
        cin >> input;
        //배열 전체를 탐색하며 해당 숫자를 찾아 0으로 바꿈
        for(int j=0; j<5; j++){
            for(int k=0; k<5; k++){
                if(arr[j][k] == input){
                    arr[j][k] = 0;
                    j = 5;
                    k = 5;
                }
            }
        }
        //바꿀 때 마다 빙고 확인
        if(check(arr) == 1){
            cout << i+1;
            break;
        }
    }

    return 0;
}