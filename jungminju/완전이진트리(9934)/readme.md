⭐ [문제링크](https://www.acmicpc.net/problem/9934)
## 🤔 나의 접근법
: 중위순회를 통해 완전이진트리 생성하기
-> 각 레벨마다 StringBuilder 객체를 생성하여 재귀함수를 돌며 각 레벨별 노드를 저장함

## ❤️ 알고리즘 공부

## 📒코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] array;
    static StringBuilder[] answer;
    public static void main(String[] args) throws IOException {
        init();
        int row = 0;
        makeTree(0, array.length-1, row);
        for(StringBuilder s : answer) {
            System.out.println(s);
        }
    }

    public static void makeTree(int start, int end, int row){
        int middle = (start+end)/2;
        int target = array[middle];
        //현재 노드 저장
        StringBuilder answerRow = answer[row];
        answerRow.append(target+" ");
        if(start!=end) { //배열의 인덱스 벗어나지 않기 위한 조건
            makeTree(start,middle-1, row+1); //왼쪽 노드 탐색
            makeTree(middle+1, end, row+1); //오른쪽 노드 탐색
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());
        double result = Math.pow(2, k) - 1;
        array = new int[(int) result];
        answer = new StringBuilder[k];
        for(int i=0 ; i<k; i++){
            answer[i] = new StringBuilder();
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0 ; i<result; i++){
            String s = st.nextToken();
            array[i] = Integer.parseInt(s);
        }
    }
}
```