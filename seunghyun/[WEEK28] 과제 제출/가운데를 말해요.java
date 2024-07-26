import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if (min.size() == max.size()) {
                if(!min.isEmpty() && min.peek()<input){
                    max.add(min.poll());
                    min.add(input);
                }
                else{
                    max.add(input);
                }
            }
            else{
                if(!max.isEmpty() && max.peek()>input){
                    min.add(max.poll());
                    max.add(input);
                }
                else{
                    min.add(input);
                }
            }
            sb.append(max.peek()).append("\n");
        }
        System.out.println(sb);

    }
}