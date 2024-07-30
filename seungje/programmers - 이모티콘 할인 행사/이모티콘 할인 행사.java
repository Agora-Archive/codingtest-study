```java
import java.util.*;
class Solution {
    static int[][] u;
    static int[] emo,hal;
    static int a, b;
    
    static void sol(){
        int cnt = 0, toal_buy = 0;
        
        for(int i = 0; i<u.length; i++){
            int buy = 0;
            
            for(int j = 0; j<hal.length; j++)
                if(u[i][0] <= hal[j]) 
                    buy += emo[j] - (emo[j] * hal[j] / 100);
            
            if(buy>=u[i][1]) cnt++;
            else toal_buy += buy;
        }
        
        
        if(a < cnt) { a = cnt; b = toal_buy; }
        else if(a==cnt && b<toal_buy) b = toal_buy;
    }
    
    static void dfs(int s){
        if(s==hal.length || hal[s]>40) return;
        sol();
        
        hal[s]+=10;
        dfs(s);
        hal[s]-=10;
        dfs(s+1);
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        u = users;
        emo = emoticons;
        hal = new int[emoticons.length];
        Arrays.fill(hal,10);
        
        dfs(0);
        
        return new int[]{a,b};
    }
}
```