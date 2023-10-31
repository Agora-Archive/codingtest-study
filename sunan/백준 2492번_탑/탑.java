import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();// 마지막에 출력해줄 버퍼
    	Deque<Integer> sta = new LinkedList<>();
    	Deque<Integer> index = new LinkedList<>();
    	int n =Integer.parseInt(br.readLine()); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int []arr=new int[n];//일단은 입력을 받아와줄 배열
    	for(int i=0; i<n; i++) {
    		arr[i]= Integer.parseInt(st.nextToken());
    	}
    	for(int i=0; i<n; i++) {
    		while(!sta.isEmpty() && sta.peek()<=arr[i]) {
    			sta.pollFirst();
    			index.pollFirst();
    		}
    		if(sta.isEmpty()) {
    			sb.append(0+" ");
    		}
    		else {
    			sb.append(index.peek()+" ");
    		}
    		sta.addFirst(arr[i]);
    		index.addFirst(i+1);
    	}
    	
    	System.out.print(sb);
    }    
}
