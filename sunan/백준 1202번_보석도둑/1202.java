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
            pq.add(new jewelry(Integer.parseInt(input.nextToken()),Integer.parseInt(input.nextToken())));
        }
        ArrayList<Integer> arr = new ArrayList<>();
        TreeMap<Integer, Integer> nearby = new TreeMap<>();
        while (K--!=0){
            int w= Integer.parseInt(br.readLine());
            if(nearby.containsKey(w)){
                nearby.put(w, nearby.get(w)+1);
            }
            else {
                nearby.put(w,1);
            }
        }
        long result =0;
        while(!nearby.isEmpty()&&!pq.isEmpty()){
            jewelry current = pq.poll();
            if(current.weight<=nearby.lastKey()){//담을 수 있는 보석이라면
                Integer a = nearby.ceilingKey(current.weight);
                if(a!=null){
                    //System.out.println(a+" 삭제");
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
