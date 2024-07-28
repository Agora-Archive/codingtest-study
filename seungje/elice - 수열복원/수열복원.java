import java.io.*;
import java.util.*;
class Main {
    static ArrayList<Integer> res = new ArrayList<>();
    static ArrayList<Integer> now = new ArrayList<>();

    static void dfs(int num, int sum, int idx){
        if(idx == res.size()){
            now.add(sum+num);
            return;
        }
        dfs(num,sum,idx+1);
        dfs(num,sum+res.get(idx),idx+1);
    }
  
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);
        }

        pq.poll();
        for(int i = 0; pq.isEmpty()!=true; i++ ){
            Integer num = pq.poll();

            if(!now.contains(num)){
                dfs(num,0,0);
                res.add(num);
            }
            now.remove(num);
        }

        for(int a : res) System.out.print(a + " ");
    }
}