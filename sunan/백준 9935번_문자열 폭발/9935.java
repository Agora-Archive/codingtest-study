import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 임시 스트링 버퍼를 선언해주고 b전에 있는 문자들을 그곳에 임시 보관을 해준다.
		char[] arr = br.readLine().toCharArray();
		String bomb = br.readLine();// 폭발성 문자열
		StringBuffer temp = new StringBuffer("");
		Stack<Character> st = new Stack<>();
		boolean pass = true;// 폭발성일때 마지막문자를 넣을지 말지 판단
		// IF>> 검사했는데 진짜 폭발성 문자열이면 버퍼도 비우고 그다음 문자열로 넘어가면 된다.
		if (bomb.length() != 1) {

			for (char x : arr) {
				if (x == bomb.charAt(bomb.length() - 1) && !st.isEmpty()) {// 마지막에 있는 문자열
					for (int i = bomb.length() - 2; i >= 0; i--) {
						try {

							if (bomb.charAt(i) == st.peek()) {
								temp.append(st.pop());
								pass = false;
							} else {
								temp.reverse();
								for (char y : temp.toString().toCharArray()) {
									st.push(y);
								}
								pass = true;
								break;
							}
						} catch (EmptyStackException e) {
							temp.reverse();
							for (char y : temp.toString().toCharArray()) {
								st.push(y);
							}
							pass = true;
							break;
						}
					}
					temp.setLength(0);// temp를 비워줌
				}
				if (pass) {
					st.push(x);
				}
				pass = true;
			}
		} else {
			for (char x : arr) {
				if (x != bomb.charAt(0)) {
					st.push(x);
				}
			}
		}
		// NOT>> 아니면 임시 버퍼에 있는 문자열을 다시 넣어주도록 하면 된다.

		while (!st.isEmpty()) {
			temp.append(st.pop());
		}
		if (temp.length() == 0) {
			System.out.print("FRULA");
		} else {
			System.out.print(temp.reverse());
		}
	}
}
