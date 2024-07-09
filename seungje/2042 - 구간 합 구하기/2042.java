import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static long[] arr;

    public static void buildTree(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
        } else {
            buildTree(node * 2, left, (left + right) / 2);
            buildTree(node * 2 + 1, (left + right)/2 + 1, right);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

    }

    public static long sum(int node, int s, int e, int left, long right) {
        if (left > e || right < s) return 0;
        if (left <= s && e <= right) return tree[node];

        long l_sum = sum(node * 2, s, (s + e)/2, left, right);
        long r_sum = sum(node * 2 + 1, (s + e)/2 + 1, e, left, right);
        return l_sum + r_sum;
    }

    public static void exch(long val, int index, int node, int s, int e){
        if(index < s || index > e) return;
        if(s == e){
            arr[index] = val;
            tree[node] = val;
            return;
        }

        exch(val, index, node*2, s, (s + e)/2);
        exch(val, index, node*2 + 1, (s + e)/2 + 1, e);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringTokenizer st = new StringTokenizer(input);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        tree = new long[N*4];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        buildTree(1,1, N);

        for (int i = 0; i < M+K; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if(a == 1){
                exch(c, b, 1,1,N);
            }
            else{
                long sum = sum(1,1,N,b,c);
                System.out.println(sum);
            }
        }

    }
}