import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static int N, M, K;
    public static long[] input, tree;

    public static void make(int node, int left, int right) {
        if (left == right) {
            tree[node] = input[left];
        } else {
            make(node * 2, left, (left + right) / 2);
            make(node * 2 + 1, (left + right) / 2 + 1, right);
            tree[node] = tree[node * 2] * tree[node * 2 + 1] % 1000000007;
        }
    }

    public static void remake(int node, int left, int right, int idx, int num) {
        if (left > idx || right < idx) {
            return;
        }
        if (left == right) {
            tree[node] = num;
            input[idx] = num;
        } else {
            remake(node * 2, left, (left + right) / 2, idx, num);
            remake(node * 2 + 1, (left + right) / 2 + 1, right, idx, num);
            tree[node] = tree[node * 2] * tree[node * 2 + 1] % 1000000007;
        }
    }

    public static long calc(int node, int left, int right, int start, int end){
        if(start>right||end<left)
            return 1;
        if(start<=left&&end>=right){
            return tree[node];
        }
        return calc(node*2,left,(right+left)/2,start,end) * calc(node*2+1,(left+right)/2+1,right,start,end) % 1000000007;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new long[N];
        tree = new long[N * 4];
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(br.readLine());
        }
        make(1, 0, N - 1);

        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==1){
                remake(1,0,N-1,b-1,c);
            }
            else{
                System.out.println(calc(1,0,N-1,b-1,c-1));
            }
        }
    }
}