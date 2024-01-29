import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numerable[] = {"000","001", "010", "011", "100", "101", "110", "111"};
        ArrayList<String> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String Input = br.readLine();
        char[] InputArray = Input.toCharArray();

        for(char check : InputArray){
            int index = Integer.parseInt(String.valueOf(check));
            answer.add(numerable[index]);
        }

        char deleteZero[];
        deleteZero = answer.get(0).toCharArray();

        int rememberdex = 0; // 초기값을 0으로 설정
        for(char check : deleteZero){
            if(check == '0' && InputArray.length>1) {
                rememberdex++;
            } else {
                break; // 0이 아닌 문자를 만나면 반복 중단
            }
        }

        if (rememberdex > 0) {
            sb.append(deleteZero, rememberdex, deleteZero.length - rememberdex);
            answer.set(0, String.valueOf(sb));
        }

        if(rememberdex==0 && InputArray.length==1){
            System.out.println("0");
        }
        else{
            for(String print : answer){
                System.out.print(print);
            }
        }
    }
}


//잘 짠 코드

//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    String s = br.readLine();
//    StringBuilder sb = new StringBuilder();
//    String[] b = {"000","001","010","011","100","101","110","111"};
//
//        for(int i=0;i<s.length();i++) {
//        int a =  s.charAt(i)-'0';
//
//        sb.append(b[a]);
//        }
//
//        if(s.equals("0")) System.out.println(s);
//        else{
//        while(sb.charAt(0) == '0') sb = new StringBuilder(sb.substring(1));
//        System.out.println(sb);
//        }

