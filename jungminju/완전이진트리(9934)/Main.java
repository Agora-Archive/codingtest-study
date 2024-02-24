import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] array;
    static StringBuilder[] answer; //걍 해야지~
    public static void main(String[] args) throws IOException {
        init();
        makeTree(0, array.length-1, 0);
        for(StringBuilder s : answer) {
            System.out.println(s);
        }
    }

    public static void makeTree(int start, int end, int row){
        int middle = (start+end)/2;
        int target = array[middle];
        //현재를 저장
        StringBuilder answerRow = answer[row];
        answerRow.append(target+" ");
        if(start!=end) {
            makeTree(start,middle-1, row+1); //왼쪽 탐색
            makeTree(middle+1, end, row+1); //오른쪽 탐색
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());
        double result = Math.pow(2, k) - 1;
        array = new int[(int) result];
        answer = new StringBuilder[k];
        for(int i=0 ; i<k; i++){
            answer[i] = new StringBuilder();
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0 ; i<result; i++){
            String s = st.nextToken();
            array[i] = Integer.parseInt(s);
        }
    }
}