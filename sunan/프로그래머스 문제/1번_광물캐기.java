import java.util.*;
class Solution {
    static int [][]hardness;
    static int []gock;
    public int solution(int[] picks, String[] minerals) {
        int answer = 0, k =minerals.length/5;
        gock = picks;
        int gocks = gock[0]+gock[1]+gock[2];
        if(gocks==0){
            return answer;
        }
        if(minerals.length%5>0){
            k++;//5개 이하의 조가 편성되었을때
        }
        int minus = 0;
        if(k>gocks){//조의 수보다 곡괭이가 더 적으면 뒤에 조들은 버린다.
            minus = k -gocks; //버릴 조의 수
            k = gocks;
        }
        hardness = new int[k][4];// [k][0] 은 빈자리 [1]은 다이아 곡괭이로 캤을때의 피로도 [2]는 철곡, [3]은 돌곡
        int index=0;
        for(int i =0; i<minerals.length-minus; i++){
            score(minerals[i], index);
            if((i+1)%5==0){
	            hardness[index][0] =0;
              index++;  
            }
        }
        Arrays.sort(hardness, Comparator.comparingInt(o1->o1[3]));//돌곡 피로도 오름차순
        index=0;
        for(int i = k-1; i>=0; i--){// 돌곡기준으로 가장 피로도 높은 애들부터 다이아부터 소진시킴
            answer+=hardness[i][whichpick()];
        }
        return answer;
    }

    static int whichpick(){//곡괭이 재고 관리
        if(gock[0]>0){
            //다이아 곡 있을때
            gock[0]--;
            return 1;//1열이 다이아
        }
        else if(gock[1]>0){
            gock[1]--;
            return 2;
        }
        else if(gock[2]>0){
            return 3;
        }
        else{// 남은 곡괭이가 없다.
            return 0;//0리턴
        }
    }

    static void score(String a, int index){ //곡괭이 종류마다 피로도 적립
        if(a.equals("diamond")){
            // 다 -> 다곡
            hardness[index][1]+=1;
            // 다 -> 철곡
            hardness[index][2]+=5;
            // 다 -> 돌곡
            hardness[index][3]+=25;
        }
        else if(a.equals("iron")){
            
            // 철 -> 다곡
            hardness[index][1]+=1;
            // 철 -> 철곡
            hardness[index][2]+=1;
            // 철 -> 돌곡
            hardness[index][3]+=5;
        }
        else{
            // 돌 -> 다곡
            hardness[index][1]++;
            // 돌 -> 철곡
            hardness[index][2]++;
            // 돌 -> 돌곡
            hardness[index][3]++;
        }
    }
}
