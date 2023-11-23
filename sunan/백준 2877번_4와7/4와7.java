import java.io.*;

public class Main{
    static public void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        // k를 2진수로 나타내서
        //2진수로 k가 모두 1일때 그 1의 개수만큼 자리수가 된다.
        //그래서 2진수로 바꾸었을때 모두 1인지만 검사를 해줘도
        //맞으면 binaryK의 길이가 자리수
        //아니면 binaryK의 길이 -1 이 자리수가 된다.
        //일단 k를 2진수로 나타내주면
        char []binaryK = Integer.toBinaryString(k).toCharArray();
        int position = binaryK.length;
        for(char x: binaryK){
            if(x=='0') {
                position = binaryK.length-1;
                break;
            }
        }
        //7인값임을 저장할 boolean배열
        boolean []arr = new boolean[position];
        //자리수를 찾아냈다면 그 수는 예를들어 3자리 수면
        // 그전에 있었던 수의 개수는 2의 현재 수의 자리수-1의 제곱
        int formal =0;
        for(int i =1 ; i<position; i++){
            formal += Math.pow(2, i);
        }
        k-=formal;
        char []binaryK_result = Integer.toBinaryString(k-1).toCharArray();
        if(binaryK_result.length<position){
            for(int i =0; i<position- binaryK_result.length; i++){
                sb.append("4");
            }
        }
        for(char x:binaryK_result){
            if(x=='1'){
                sb.append("7");
            }
            else{
                sb.append("4");
            }
        }
        //7인값 찾아내기

        System.out.println(sb);
    }
}
