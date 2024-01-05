import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> input = new LinkedList<>();
        Stack<Integer> erase_Index = new Stack<>();
        String str = br.readLine();
        if (str.length() % 5!=0||str.charAt(0)!='q') {
            System.out.println("-1");
            return;
        }
        for (char x : str.toCharArray()) {
            input.add(x);
        }
        int ee;
        int findDuck = 0;
        char[] duck = { 'q', 'u', 'a', 'c', 'k' };
        while (!input.isEmpty()) {

            // 리스트를 탐색
            int index_input=0;
            int index = 0;
            for (char x : input) {
                if (index == 5) {
                    index = 0;
                }
                if (x == duck[index]) {
                    erase_Index.push(index_input);//문제발생
                    index++;
                }
                index_input++;
            }
            // 해당배열삭제
            while (!erase_Index.isEmpty()) {
                ee=erase_Index.pop();
                input.remove(ee);
            }
            findDuck++;
            
        }
        System.out.println(findDuck);
    }
}
