import java.io.*;

public class Main {
	static StringBuilder sb= new StringBuilder();
	static String word;
	static boolean [] appear;//등장할 친구인지 아닌지 
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine();
		appear = new boolean[word.length()];
		
		Zoac(0, word.length()-1);
		br.close();
		System.out.println(sb);
	}
	private static void Zoac(int left, int right) {
		//종료 기준 마련
		if(left>right) {
			return;
		}
		//사전순으로 가장빠른 문자를 찾아서 boolean배열에서
		//그 값을 true로 만들어주자
		int min = left;
		for(int i=left; i<=right; i++) {
			if(word.charAt(min)>word.charAt(i)) {
				min = i;
			}
		}
		appear[min] =true;
		//make 구문
		for(int i=0; i<word.length(); i++) {
			if(appear[i]) {
				sb.append(word.charAt(i));
			}
		}
		sb.append("\n");
		Zoac(min+1, right);
		Zoac(left, min-1);
	}
}
