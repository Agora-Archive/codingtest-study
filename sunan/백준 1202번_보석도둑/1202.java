import java.io.*;
import java.util.*;

public class Main{
    static class jewelry implements Comparable<jewelry>{
        int weight, value;
        public jewelry(int weight, int value){
            this.weight=weight;
            this.value=value;
        }
        @Override
        public int compareTo(jewelry o) {
            if(this.value==o.value){
                return this.weight-o.weight;
            }
            return o.value-this.value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NK = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NK.nextToken());
        int K = Integer.parseInt(NK.nextToken());
        PriorityQueue<jewelry> pq = new PriorityQueue<>();
        while (N--!=0){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());
            pq.add(new jewelry(w,v));
        }
        TreeMap<Integer, Integer> nearby = new TreeMap<>();
        while (K--!=0){
            int w= Integer.parseInt(br.readLine());
            nearby.put(w, nearby.getOrDefault(w,0)+1);
        }
        long result =0;
        while(!nearby.isEmpty()&&!pq.isEmpty()){
            jewelry current = pq.poll();
            if(current.weight<=nearby.lastKey()){//담을 수 있는 보석이라면
                Integer a = nearby.ceilingKey(current.weight);
                if(a!=null){
                    if(nearby.get(a)==1){
                        nearby.remove(a);
                    }
                    else {
                        nearby.put(a, nearby.get(a)-1 );
                    }
                    result+=current.value;
                }
            }
        }
        System.out.print(result);
    }
}
