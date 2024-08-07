import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer=0;
        if(stations[0]!=1){ //처음 기지국의 위치가 0이 아닐때
            answer+=(stations[0]-w-1)/(w*2+1);
            if((stations[0]-w-1)%(w*2+1)>0){
                answer++;
            }
        }
        for(int i=1; i<stations.length; i++){ //첫기지국 이상, 마지막 기지국 이하 장소 계산
            int term = stations[i]-stations[i-1]-(2*w)-1;
            answer+=term/(w*2+1);
            if(term%(w*2+1)>0){
                answer++;
            }
        }
        if(n-stations[stations.length-1]>w){ //마지막 기지국이 n에 영향을 못미칠때 나머지계산
            answer+=(n-stations[stations.length-1]-w)/(w*2+1);
            if((n-stations[stations.length-1]-w)%(w*2+1)>0){
                answer++;
            }
        }
        return answer;
    }
}
