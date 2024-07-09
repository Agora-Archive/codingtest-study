import java.io.*;
import java.util.*;

public class Main {
    static int [] parent;
    static Set <Integer> knowPeople= new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringTokenizer knowlist = new StringTokenizer(br.readLine()); //아는 사람 입력
        int K = Integer.parseInt(knowlist.nextToken());
        parent = new int[N+1];// 부모노드를 저장해줄 배열
        for(int i =1; i<=N; i++){
            parent[i] = i;// union find 에선 제일 처음 부모배열은 자기 자신으로 초기화 해줌
        }
        if(K!=0){
            for(int i =0; i<K; i++){
                int knowNum = Integer.parseInt(knowlist.nextToken());
                knowPeople.add(knowNum);//거짓말임을 알고있는 사람들의 집합 생성
            }
        }
        else{
            System.out.print(M);
            return;
        }
        List<Integer>[] partyList = new ArrayList[M];// 각 파티별 파티 참석자를 저장해줄 List
        for(int i =0; i<M; i++){
            partyList[i]=new ArrayList<>();
        }
        for(int i =0; i<M; i++){
            StringTokenizer input = new StringTokenizer(br.readLine());
            int ppn = Integer.parseInt(input.nextToken());//party people number 파티 참석 숫자
            int standard = Integer.parseInt(input.nextToken());
            // 다른 그래프일 경우 연결 시켜줄 기준 standard 를 기준으로 나머지들을 연결시킨다
            // 어차피 union find로 부모를 찾기에 순서는 상관없음 ㅇㅇ
            partyList[i].add(standard);//파티 참석 리스트에 넣기 파티번호는 i+1
            for(int j =1; j<ppn; j++){
                int num = Integer.parseInt(input.nextToken());
                union(standard, num); //기준이랑 다른 사람들이 파티에서 겹치니까 union해주기
                partyList[i].add(num);//동일하게 나중에 파티참석 리스트와 비교하기 위해
            }
        }
        int cnt=0;
        for(int i=0; i<M; i++){
            boolean canGo = true;
            for(int x: partyList[i]){
                if(knowPeople.contains(find(x))){
                // union함수에서 제일 부모를 거짓말임을 아는 경우엔 거짓말을 아는 사람으로
                // 해놓아서 간단하게 아는사람 집합에 find로 제일 부모를 찾아서 있으면 못가고 
                //아니면 참석할 수 있다. 
                    canGo=false;
                    break;
                }
            }
            if(canGo) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }
    static void union(int x, int y){
        int px= find(x); //부모 찾기
        int py= find(y);
        if(knowPeople.contains(py)){
            parent[px]=py;//거짓말임을 알고있는 사람을 parent로 바꿔줌
            //이렇게 해야 부모를 검사했을때 거짓말임을 아는사람인지 비교하기가 쉽고
            //따로 탐색해서 들어갈 필요가 없음.! 
        }
        else{
            parent[py]=px;
            //아닌 경우엔 그냥 그대로
        }
    }
    static int find (int x){
	        if(parent[x]==x){// 제일 부모일때 return
            return x;
        }
        else{
            return find(parent[x]);
        }
    }
}
//리뷰용 재커밋
