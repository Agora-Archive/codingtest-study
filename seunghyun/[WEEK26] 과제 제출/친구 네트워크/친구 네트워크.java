import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, F;
    public static HashMap<String, String> network;
    public static HashMap<String, Integer> friendNum;

    public static String find(String name) {
        if (!network.get(name).equals(name)) { //부모 이름과 자신의 이름이 같지 않으면
            network.put(name, find(network.get(name))); //부모 찾아서 업데이트
        }
        return network.get(name); //부모 리턴하기
    }

    public static void union(String n1, String n2) { //새로운 친구 관계 맺기
        String p1 = find(n1); //두 부모 찾기
        String p2 = find(n2);
        int num1 = friendNum.get(p1);
        int num2 = friendNum.get(p2);
        if (!p1.equals(p2)) {
            network.put(p2, p1);
            friendNum.put(p1, num1 + num2); //부모의 친구 수 업데이트
        }
    }

    public static int getFriendNum(String name) {
        String parent = find(name); //부모 찾기
        return friendNum.get(parent); //부모를 기준으로 친구 수 가져오기
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        N = Integer.parseInt(br.readLine());
        network = new HashMap<>();
        friendNum = new HashMap<>();
        for (int i = 0; i < N; i++) {
            F = Integer.parseInt(br.readLine());
            network.clear();
            friendNum.clear();

            for (int j = 0; j < F; j++) {
                String n1, n2;
                st = new StringTokenizer(br.readLine());
                n1 = st.nextToken();
                n2 = st.nextToken();

                if (!network.containsKey(n1)) { //해당 이름이 처음 나왔다면 초기 세팅
                    network.put(n1, n1); //부모 자신으로
                    friendNum.put(n1, 1); //친구 수 1
                }

                if (!network.containsKey(n2)) { //해당 이름이 처음 나왔다면 초기 세팅
                    network.put(n2, n2); //부모 자신으로
                    friendNum.put(n2, 1); //친구 수 1
                }

                String p1 = find(n1);
                String p2 = find(n2);

                if (!p1.equals(p2)) { //두 부모가 일치하지 않으면 새로운 친구관계 맺기
                    union(n1, n2);
                }
                sb.append(getFriendNum(n1)).append("\n");
            }
        }
        System.out.println(sb);
    }
}