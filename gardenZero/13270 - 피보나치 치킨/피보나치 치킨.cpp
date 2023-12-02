//
// Created by 정원영 on 12/2/23.
//
#include <iostream>
using namespace std;


int main(){
    int arr[100000];

    int input;
    cin >> input;
    arr[0] = 1;
    arr[1] = 1;
    int index = 2;
    while(arr[index-1] <= input){
        arr[index] = arr[index-1] + arr[index-2];
        index++;
    }
    index--;

    int max=0, min=0;
    if(input%2 == 0){
        min = input/2;
    }else{
        min = (input-3)/2 + 2;
    }

    if(input%3 == 0){
        max = input/3*2;
    }else if(input%3 == 1){
        max = (input-4)/3*2 + 2;
    }else{
        max = input/3*2 + 1;
    }
    cout << min << " " << max;

    return 0;
}