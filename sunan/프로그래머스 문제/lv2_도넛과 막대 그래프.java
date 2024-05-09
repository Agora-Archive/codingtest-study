import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, int[]> cnts = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            cnts.putIfAbsent(a, new int[2]);//여기서 등록해놔야 나
            cnts.putIfAbsent(b, new int[2]);
            cnts.get(a)[0] += 1;// 0에는 a 노드에서 출발하는 간선의 수,
            cnts.get(b)[1] += 1;// 1에는 b노드로 들어온 간선의 수가 저장
        }

        for (Map.Entry<Integer, int[]> entry : cnts.entrySet()) {// 이렇게 받아오면 
//키값과 value값을 따로 받아줄 필요가 없어서 간단함. 해당 엔트리의 key value를 편하게 
// 조회 및 수정 가능
            int key = entry.getKey();
            int[] road = entry.getValue();

            if (road[0] >= 2 && road[1] == 0) {//2개 이상 나가고 들어오는게 없다 == 시작노드 
                answer[0] = key;
            } else if (road[0] == 0 && road[1] > 0) {// 나가는거 없w                answer[2] += 1;
            } else if (road[0] >= 2 && road[1] >= 2) {
                answer[3] += 1;//2개 나가고 2개 들어가는 노드가 존재하면 8자 그래프의 중심이다.
            }
        }

        answer[1] = cnts.get(answer[0])[0] - answer[2] - answer[3]; //그래프의 개수는 
//시작노드에서 뻗어나온 간선 개수 거기서 막대, 8자 수 빼주면 도넛츠

        return answer;
    }
}
