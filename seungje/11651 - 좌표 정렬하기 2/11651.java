import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());
        ArrayList<Node> coordinate = new ArrayList<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coordinate.add(new Node(x,y));
        }

        Comparator<Node>comp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.y == o2.y){
                    return o1.x - o2.x;
                }
                return o1.y - o2.y;
            }
        };

        Collections.sort(coordinate,comp);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(coordinate.get(i).x + " " + coordinate.get(i).y  + '\n' );
        }

        System.out.print(sb);

    }
}