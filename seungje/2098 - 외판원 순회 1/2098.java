class Main{
    static int [][] W, dp;
    static final int INF = 2000000000;
    static int max_bit,N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        max_bit = (int) pow(2,N)-1;
        W = new int[N][N];
        dp = new int[N][max_bit];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++) Arrays.fill(dp[i],-1);

        int ans = tsp(0,1);
        System.out.println(ans);
    }

    static int tsp(int now, int check){
        if(check == max_bit){
            if(W[now][0] == 0) return INF;
            else return W[now][0];
        }

        if( dp[now][check] != -1) return dp[now][check];
        dp[now][check] = INF;

        for(int i=0; i<N; i++){
            int next = check | (1<<i);

            if(W[now][i] == 0 || (check & (1<<i) ) != 0) continue;

            dp[now][check] = min(dp[now][check], tsp(i,next) + W[now][i]);
        }

        return dp[now][check];
    }
}