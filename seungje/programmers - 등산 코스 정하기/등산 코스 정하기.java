import java.util.*;
class Solution {
    static ArrayList<Course>[] course;
    static int a = 50001;
    static int b = 200000000;
    
    static class Course{
        int to, w;
        Course(int to, int w){
            this.to = to; this.w = w;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 입력 및 초기화 부분
        course = new ArrayList[n+1];
        for(int i = 1; i<=n; i++) course[i] = new ArrayList<>();
        for(int[] path : paths){
            course[path[0]].add(new Course(path[1], path[2]));
            course[path[1]].add(new Course(path[0], path[2]));
        }
        
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) summitSet.add(summit);
        
        sol(n,summitSet,gates);
        
        return new int[]{a,b};
    }
    
    static void sol(int n, Set<Integer> summitSet,int[] gates){
        PriorityQueue<Course> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.w));
        int[] intensity = new int[n+1];
        Arrays.fill(intensity,2000000000);
        
        
        Set<Integer> gateSet = new HashSet<>(); // 도착지 확인용
        for(int gate : gates) {
            pq.add(new Course(gate,0));
            gateSet.add(gate);
            intensity[gate] = 0;
        }
        
        
        while(!pq.isEmpty()){
            Course c = pq.poll();
            int cur_i = c.to; // 현재 노드
            int cur_w = c.w;  // 지금까지의 intensity
            
            if(cur_w > intensity[cur_i]) continue;
            
            // 정점을 찍을 경우 a,b 최신화 후 탈출
            if (summitSet.contains(cur_i)) {
                if (cur_w < b) {
                    b = cur_w;
                    a = cur_i;
                } else if (cur_w == b && cur_i < a) {
                    a = cur_i;
                }
                continue;
            }

            
            for(Course tmp : course[cur_i]){ // 이동할 노드
                int w = Math.max(tmp.w, cur_w);
                
                if( w < intensity[tmp.to]){
                    intensity[tmp.to] = w;
                    pq.add(new Course(tmp.to,w));
                }
            }
        }
    }
}