import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); //전구, 스위치 개수 입력

        boolean [] sw1 = new boolean[N]; //0번 스위치를 눌렀을 때, 전구 배열
        boolean [] sw2 = new boolean[N]; //0번 스위치를 안 눌렀을 때, 전구 배열
        boolean [] arr = new boolean[N]; //만들고자 하는 전구 상태

        String begin = br.readLine(); // 현재 전구 상태 입력
        for (int i=0;i<begin.length();i++){ //현재 전구 상태를 sw1, sw2에 복사해주기
            if (begin.charAt(i)=='0'){
                sw1[i]=false;
                sw2[i]=false;
            } 
            else{
                sw1[i]=true;
                sw2[i]=true;
            }
        }
        String ans = br.readLine(); //만들고자 하는 전구 상태 입력
        for (int i=0;i<N;i++){ //입력받은 상태 arr에 저장
            if (ans.charAt(i)=='0')
                arr[i]=false;
            else
                arr[i]=true;
        }
        int sum1=0, sum2 = 0; //sum1 : sw1로 했을 때 스위치 누른 횟수 sum2 : sw2로 했을 때 스위치 누른 횟수


        sw1[0]=!sw1[0]; 
        sw1[1]=!sw1[1]; //sw1의 0번째 스위치 눌러서 0번, 1번 전구 상태 바꿈
        sum1+=1; //sum1 횟수 1번 추가
        
        //i-1번째 전구가 만들고자 하는 상태와 다를 경우 i번째 스위치 눌러줌
        for (int i=1;i<N;i++){ //sw1
            if (sw1[i-1]!=arr[i-1]){
                sw1[i-1]=!sw1[i-1];
                sw1[i]=!sw1[i];
                if (i!=N-1)
                    sw1[i+1]=!sw1[i+1];
                sum1+=1;
            }
        }

        for (int i=1;i<N;i++){ //sw2
            if (sw2[i-1]!=arr[i-1]){
                sw2[i-1]=!sw2[i-1];
                sw2[i]=!sw2[i];
                if (i!=N-1)
                    sw2[i+1]=!sw2[i+1];
                sum2+=1;
            }
        }

        if (!Arrays.equals(sw1,arr)&&!Arrays.equals(sw2,arr)) //sw1과 sw2 두가지 경우 모두 다를 경우 -1출력
            System.out.println(-1);
        else if (Arrays.equals(sw1,arr)&&Arrays.equals(sw2,arr)) //둘 다 같을 경우 최솟 값 출력
            System.out.println(Math.min(sum1,sum2));
        else if (Arrays.equals(sw1,arr)) //sw1만 같을 경우 sum1 출력
            System.out.println(sum1);
        else //sw2만 같을 경우 sum2 출력
            System.out.println(sum2);
    }
}
