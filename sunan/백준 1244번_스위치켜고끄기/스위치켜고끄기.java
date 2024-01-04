import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] state;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int switch_num = Integer.parseInt(br.readLine());
        state = new boolean[switch_num];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switch_num; i++) {
            int statement = Integer.parseInt(st.nextToken());
            // 다 false로 초기화 되어있기 떄문에 켜져있을때만 체크해줌
            if (statement == 1) {
                state[i] = true;
            }
        }

        int student_num = Integer.parseInt(br.readLine());
        for (int i = 0; i < student_num; i++) {
            StringTokenizer st2= new StringTokenizer(br.readLine());

            int gender =Integer.parseInt(st2.nextToken());
            if (gender==1) {// 남자
                boy(Integer.parseInt(st2.nextToken()), switch_num);
            } else if(gender==2){// 여자
                girl(Integer.parseInt(st2.nextToken()), switch_num);
            }
        }
        int timing=0;
        StringBuilder sb = new StringBuilder();
        for (boolean x : state) {
            if(timing%20==0&&timing!=0)
            {
                sb.append("\n");
            }
            if (x) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
            timing++;
        }
        System.out.print(sb);
    }

    static void boy(int num, int sn) {
        for (int i = 1; i <= sn; i++) {
            if (i % num == 0||num==1) {
                if (state[i-1]) {
                    state[i-1] = false;
                } else {
                    state[i-1] = true;
                }
            }
        }
    }

    static void girl(int num, int sn) {
        int area;
        if (num == 1 || num == sn) {// 양쪽에 비교할 것이 없을때
            if (state[num - 1]) {
                state[num - 1] = false;
            } else {
                state[num - 1] = true;
            }
        } else {
            if (num > sn / 2) {
                area = sn - num;
            } else {
                area = num - 1;
            }
            for (int i = 1; i <= area; i++) {
                if (state[num - i - 1] == state[num + i - 1]) {
                    if (state[num - i - 1]) {
                        state[num - i - 1] = false;
                        state[num + i - 1] = false;
                    } else {
                        state[num - i - 1] = true;
                        state[num + i - 1] = true;
                    }

                }
//수정한 부분
                else{
                    break;
                }

            }
            if (state[num - 1]) {
                state[num - 1] = false;
            } else {
                state[num - 1] = true;
            }
        }
    }
}
