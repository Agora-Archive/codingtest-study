public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> ts = new TreeSet<>();
        int [] height = new int[N];
        long ans = 0;

        for(int t = 0; t<N; t++){
            int val = Integer.parseInt(br.readLine());

            if(ts.higher(val) == null){
                if(ts.lower(val) == null) height[val] = 1;
                else height[val] = height[ts.lower(val)]+1;
            }
            else{
                if(ts.lower(val) == null) height[val] = height[ts.higher(val)]+1;
                else height[val] = Math.max(height[ts.higher(val)], height[ts.lower(val)]) + 1;
            }

            ans += height[val];
            ts.add(val);
        }

        System.out.println(ans);
    }
}