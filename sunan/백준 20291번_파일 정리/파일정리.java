import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        Stack <String> st = new Stack<>();
        int new_key =0;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            String str = br.readLine();
            int comma_location = str.indexOf(".");
            for(int j=comma_location+1; j<str.length(); j++){
                sb.append(str.charAt(j));//확장자 저장
            }
            if(!m.containsKey(sb.toString())){// 없으면 1로 생성과 동시에 초기화
                m.put(sb.toString(), 1);
                st.push(sb.toString());
                new_key++;
            }
            else{
                m.replace(sb.toString(), m.get(sb.toString())+1);// 있으면 1더한 값으로 최신화
            }
            sb.setLength(0);//버퍼 초기화
        }
        LinkedList<String> list = new LinkedList<>();
        for(int i=0; i<new_key; i++){
            list.add(st.pop());
        }
        Collections.sort(list);
        //출력 구문
        for(int i=0; i<new_key; i++){
            String word = list.pop();
            sb.append(word+" "+m.get(word)+"\n");
        }
        System.out.print(sb);
    }
}
