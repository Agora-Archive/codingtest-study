public class Main {
    static int[][] arr = new int[9][9];
    static boolean flag;

    static void sol(int i,int j){
        if(flag) return;
        if(i==9 && j==0){
            print();
            return;
        }

        if(arr[i][j]!=0){
            if(j==8) sol(i+1,0);
            else sol(i,j+1);
        }
        else{
            ArrayList<Integer> theNumbersWhatWeCanUseInThisBlock = LetsFindOutWhatNumbersWeCanUseInThisBlock(i,j);

            for(int nextNum : theNumbersWhatWeCanUseInThisBlock){
                arr[i][j] = nextNum;
                if(j==8 ) sol(i+1,0);
                else sol(i,j+1);
                arr[i][j] = 0;
            }
        }
    }

    static ArrayList<Integer> LetsFindOutWhatNumbersWeCanUseInThisBlock(int i, int j){
        HashSet<Integer> hs = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for(int l = 0; l<9; l++){
            hs.remove(arr[i][l]);
            hs.remove(arr[l][j]);
        }

        for (int row = 0; row <= 6; row += 3) {
            for (int col = 0; col <= 6; col += 3) {
                if ( (row <= i && i < row + 3) && (col <= j && j < col + 3)) {
                    for(int x = row; x < row+3; x++){
                        for(int y = col; y < col+3; y++){
                            hs.remove(arr[x][y]);
                        }
                    }
                    break;
                }
            }
        }

        ArrayList<Integer> tmp = new ArrayList<>(hs);
        Collections.sort(tmp);
        return tmp;
    }
    
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            char[] tmp = input.toCharArray();
            for (int j = 0; j < 9; j++) arr[i][j] = tmp[j] -'0';
        }
    }

    static void print(){
        flag = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) sb.append(arr[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();
        sol(0,0);
    }
}