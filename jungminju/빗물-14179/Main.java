import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int RAIN = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String[] W = br.readLine().split(" ");
        int[] wall = new int[W.length];
        int Biggest = 0;
        int BiggestIndex = 0;
        for (int i = 0; i < W.length; i++) {
            wall[i] = Integer.parseInt(W[i]);
            if(wall[i]>=Biggest) {
                Biggest=wall[i];
                BiggestIndex = i; //가장 큰 값을 가지게 될 값의 제일 마지막 인덱스 저장
            }
        }
        CheckRain(BiggestIndex, wall); //순방향 체크
        CheckRainReverse(BiggestIndex, wall); //역순으로 체크
        System.out.println(RAIN);
    }

    public static void CheckRain(int BiggestIndex, int[] wall) { //오름차순 형태 계산
        for(int i=0; i<BiggestIndex; i++){
            if(wall[i]>wall[i+1]){
                RAIN += wall[i]-wall[i+1];
                wall[i+1] = wall[i];
            }
        }
    }

    public static void CheckRainReverse(int BiggestIndex, int[] wall) {
        if(BiggestIndex == wall.length-1) return; //가장 큰 벽이 wall 배열의 가장 왼쪽 벽일때, 역순 계산할 게 없으니 바로 return
        for(int i= wall.length-1; i>BiggestIndex; i--){
            if(wall[i]>wall[i-1]){
                RAIN += wall[i]-wall[i-1];
                wall[i-1] = wall[i];
            }
        }
    }

}
