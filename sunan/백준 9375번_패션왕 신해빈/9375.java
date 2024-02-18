import java.io.*;
import java.util.*;
public class Main {
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
				//테스트케이스만큼 반복
        while(t--!=0){
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> clothe = new HashMap<>();
            while(n--!=0){
                // 공백을 기준으로 한줄에 구분해줄 것이 앞 뒤 단어 밖에 없으니 split 사용
                String [] input = br.readLine().split(" ");
                if(!clothe.containsKey(input[1])){//등록된 적 없는 부위에 입는 옷
                    clothe.put(input[1], 2);//등록+ null까지
                }
                else{
                    clothe.put(input[1], clothe.get(input[1])+1);//등록
                }
            }
						//중복이 허용되지 않는 리스트의 역할을 하는 Set사용
            Set<String> kind = clothe.keySet();
            //지금까지 저장된 clothe들을 kind(종류)에 저장하는 과정
            Iterator<String> it= kind.iterator();
            int cnt =1;
            while(it.hasNext()){
                String key = it.next();
                cnt*=clothe.get(key);
            }
            sb.append((cnt-1) +"\n");
        }
        System.out.print(sb);
    }
}
