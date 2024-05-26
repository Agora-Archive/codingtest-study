import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> quackIndex = new HashMap<>();

        char[] input = br.readLine().toCharArray();
        int[] checkQuack = new int[6];
        int leastQuack = 0;
        int minus = 0;

        quackIndex.put('q', 1);
        quackIndex.put('u', 2);
        quackIndex.put('a', 3);
        quackIndex.put('c', 4);
        quackIndex.put('k', 5);

        checkQuack[0] = input.length;

        if (input.length % 5 != 0) { //문자열의 길이가 5의 배수가 아닌경우, 프로그램 종료
            leastQuack = -1;
            System.out.println(leastQuack);
            br.close();
            return;
        }

        for (char quack : input) {
            if (!quackIndex.containsKey(quack)) { //들어온 문자열이 q,a,u,c,k 중 하나인지 검증.
                leastQuack = -1;
                break;
            }
            int index = 0;
            index = quackIndex.get(quack);
            checkQuack[index]++; //해당 인덱스에 ++를 해줌. 해당 문자열이 입력되었다는 의미를 지님
            if (!(checkQuack[index - 1] >= checkQuack[index])) { //이전 문자열의 갯수>=현재 문자열의 갯수 여야 함(순서는 q,u,a,c,k순임)
                leastQuack = -1;
                break;
            }
            if(quack=='k'){ //k가 등장할때
                if(checkQuack[1]>checkQuack[5] && minus==0){ //q>k && minus = 0
                    if(leastQuack==0){ //최소 오리수가 안세어졌을때, 해당 케이스는 일단 quack 하나가 완성된 거니 최소 오리수++
                        leastQuack++;
                    }
                    minus=checkQuack[1]-checkQuack[5]; //minus=q-k
                }
                if(checkQuack[1]-checkQuack[5]>minus) { //k가 들어왓는데, q-k가 기존 minus보다 크다면 새로운 오리가 등장한 것!
                    minus=checkQuack[1]-checkQuack[5];
                }
                if(checkQuack[1]==checkQuack[5] && leastQuack==0){ //q=k && 최소 오리수 안 세어진 경우
                    leastQuack++;
                }
            }
        }

        if (checkQuack[5] * 5 != input.length) { //모든 quack이 k까지 잘 입력되었는지 확인
            leastQuack = -1;
        } else {
            leastQuack += minus;
        }
        System.out.println(leastQuack);
        br.close();
    }
}
