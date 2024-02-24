#include <bits/stdc++.h>
using namespace std;
#define s first
#define w second

pair<int, int> arr[8];
bool isused[8] = {false, };
int n;
int result = 0;

//깨진 계란 체크
int check(){
    int temp = 0;
    for(int i=0; i<n; i++){
        if(arr[i].s <= 0) temp++;
    }
    return temp;
}

void recursive(int now){
    bool flag = false;

    //마지막 계란까지 온 경우
    if(now == n){
        result = max(result, check());
        return;
    }

    if(arr[now].s <= 0){    //들고 있는 계란의 내구도가 없는 경우
        recursive(now+1);
    }else{
        for(int i=0; i<n; i++){     //들고있는 계란으로 다른 계란 한번씩 쳐보기
            if(arr[i].s > 0 && i != now){
                arr[now].s -= arr[i].w;
                arr[i].s -= arr[now].w;
                recursive(now+1);
                arr[now].s += arr[i].w;
                arr[i].s += arr[now].w;
                flag = true;
            }
        }
    }

    //해당 계란으로 다른 계란을 한번도 안친 경우
    if(!flag){
        recursive(now+1);
    }
}

int main(){
    cin >> n;
    for(int i=0; i<n; i++){
        cin >> arr[i].s >> arr[i].w;
    }

    recursive(0);

    cout << result;
    return 0;
}