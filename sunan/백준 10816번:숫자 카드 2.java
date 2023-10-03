import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
public class Main{
    public static void main(String[] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();//마지막에 출력해줄 버퍼
    	HashMap<Integer, Integer> cnt = new HashMap<>();//hash맵 선언
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	for(int i= 0; i<n; i++) {//n개만큼 입력받으며 해시맵 key value정의해주기
    		int tmp = Integer.parseInt(st1.nextToken());
    		if(cnt.containsKey(tmp)) {
    			cnt.replace(tmp, cnt.get(tmp)+1);//기존 value의 +1로 대체
    		}
    		else{
    			cnt.put(tmp, 1); //처음이니까 1로 정의
    		}
    	}
    	int m = Integer.parseInt(br.readLine());
    	StringTokenizer st2 = new StringTokenizer(br.readLine());
    	for(int i= 0; i<m; i++) {
    		int tmp = Integer.parseInt(st2.nextToken());
    		if(cnt.containsKey(tmp)) {
    			sb.append(cnt.get(tmp)+" ");//sb에 삽입
    		}
    		else{
    			sb.append("0 ");//삽입
    		}
    	}
    	System.out.print(sb);//결과물 출력
    }    
}
