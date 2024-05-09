import java.util.*;
//점화식은 f(n+1) = 2f(n) + 1 또는 f(n) = 2^n -1
class Solution {
    static List<int[]> process = new ArrayList<>();
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        int[][] answer = process.stream().toArray(int [][]::new);//issue작성
        return answer;
    }
    static void hanoi(int n, int one, int two, int three){
        if(n==0){
            return;
        }
        hanoi(n-1, one, three, two);// 먼저 작은걸 1->2 저장되는건 one, three니까 이렇게 함수 호출
        process.add(new int[]{one, three}); //process에 저장해주는건 one -> three 
        hanoi(n-1, two, one, three); // 마찬가지로 2->3
    }
}
