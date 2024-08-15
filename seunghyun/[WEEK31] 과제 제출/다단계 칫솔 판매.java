import java.util.*;

class Solution {
    public int[] money;
    public Map<String,String> parent;
    public Map<String,Integer> num;

    public void sell(String seller, int result){
        if(seller.equals("-") || result < 1){ //부모가 없거나, 남은 돈이 없는 경우 리턴
            return;
        }

        int rest = result / 10; //부모에게 줄 돈

        result -= rest;

        money[num.get(seller)] += result; //남은돈 저장
        sell(parent.get(seller), rest); //부모에게 10% 주기
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        parent = new HashMap<>();
        num = new HashMap<>();
        money = new int[enroll.length];
        for(int i=0;i<enroll.length;i++){
            parent.put(enroll[i],referral[i]);
            num.put(enroll[i],i);
        }
        for(int i=0;i<seller.length;i++){
            sell(seller[i],amount[i]*100);
        }

        return money;
    }
}