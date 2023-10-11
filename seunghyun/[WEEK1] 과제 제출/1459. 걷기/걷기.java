import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken()); //집의 x좌표
        long y = Long.parseLong(st.nextToken()); //집의 y좌표
        int w = Integer.parseInt(st.nextToken()); //한 블록 가는데 걸리는 시간
        int s = Integer.parseInt(st.nextToken()); //대각선으로 한 블록 가로지르는 시간
        long sum1, sum2, sum3, result;

        //w로만 이동하는 경우
        sum1 = (x + y) * w;

        //대각선으로만 이동하는 경우
        if ((x + y) % 2 == 0) //이동거리가 짝수인 경우
            sum2 = Math.max(x,y) * s;
        else //이동거리가 홀수인 경우 : 대각선으로 이동 후 한번 직선 이동
            sum2 = (Math.max(x,y)-1) * s + w;

        //대각선으로 이동 후 남은거리 직선 이동
        sum3 = Math.min(x,y) * s + Math.abs(x-y) * w;

        //세가지 결과값 중 가장 작은 값 구하기
        result = Math.min(sum1,Math.min(sum2,sum3));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}