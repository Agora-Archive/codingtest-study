import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int row;
    static int column;
    static int K;
    static Map<Integer, Integer> inOrder = new HashMap<>();
    static Character ladder[][];
    static boolean isImpossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        column = Integer.parseInt(br.readLine());
        row = Integer.parseInt(br.readLine());
        String alphaInput = br.readLine();

        for (int i=1; i<=column; i++) {
            char alpha = alphaInput.charAt(i-1);
            inOrder.put(alpha-64, i);
        }

        ladder = new Character[row][column+1];

        for(int i=0; i<row; i++) {
            alphaInput = br.readLine();
            ladder[i][0] = '*';
            for(int j=1; j<column; j++) {
                ladder[i][j] = alphaInput.charAt(j-1);
            }
            ladder[i][column] = '*';
            if(ladder[i][1]=='?') K=i;
        }

        for(int j=1; j<column; j++){
            ladder[K][j]='*';
        }

        /////입력 끝///////

        for(int i=1; i<=column; i++){
            int topCal = findTop(i, 0);
            int bottomCal = findBottom(inOrder.get(i), row-1);

            if(topCal==bottomCal) {
                ladder[K][topCal] = '*';
                continue;
            }

            if (Math.abs(topCal - bottomCal) >= 2) {
                isImpossible = true;
                break;
            }

            ladder[K][Math.min(topCal, bottomCal)] = '-';
        }

        if(isImpossible) {
            for(int i=1; i<column; i++){
                System.out.print("x");
            }
        }
        else {
            for(int i=1; i<column; i++){
                System.out.print(ladder[K][i]);
            }
        }

    }

    static int findTop (int location, int top) { //2 0
        if(top == K) return location;

        //오른쪽으로 가는 경우
        if(ladder[top][location]=='-' ) {
            return findTop(location+1, top+1);
        }//왼쪽으로 가는 경우
        else if (ladder[top][location-1]== '-' ) {
            return findTop(location-1, top+1);
        } //이동할 사다리가 없는 경우
        else {
            return findTop(location, top+1);
        }
    }

    static int findBottom(int location, int bottom) {
        if(bottom == K) return location;

        //오른쪽으로 가기
        if(ladder[bottom][location]=='-') {
            return findBottom(location+1, bottom-1);
        }
        //왼쪽으로 가기
        else if( ladder[bottom][location-1]=='-'){
            return findBottom(location-1, bottom-1);
        }
        //이동할 사다리가 없는 경우
        else return findBottom(location, bottom-1);
    }

}