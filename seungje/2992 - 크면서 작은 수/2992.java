class Main {
    static int N; 
    static char[] arr;
    static int [] cnt;
    static boolean flag;
    
    static void nextPer(char[] tmp, int len, int idx){
        if(flag) return;
        if( len == idx ){
            int newN = Integer.parseInt(new String(tmp));
            if(N<newN) {
                System.out.println(newN);
                flag = true;
            }
        }
        else{
            for(int i =0; i<10; i++){
                if(cnt[i] != 0){
                    tmp[idx] = (char) (i + '0');
                    cnt[i]--;
                    nextPer(tmp, len, idx+1);
                    cnt[i]++;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        N = Integer.parseInt(input);
        arr = input.toCharArray();
        cnt = new int[10];

        for(char c : arr) cnt[c-'0']++; // cnt 배열 초기화
        char[] tmp = new char[arr.length]; // tmp 배열 초기화
        
        nextPer(tmp, arr.length,0); // 순열 만들기
        if(!flag) System.out.println('0');
    }
}
