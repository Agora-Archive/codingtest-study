import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int num, cost=0;
        public Node(int num){
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node [][] nodes = new Node[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                nodes[i][j]= new Node(Integer.parseInt(st.nextToken()));
                if(i+j==0){
                    nodes[i][j].cost=0;// 0,0 일땐 cost가 0
                }
                else if(i==0&&j!=0 ){//제일 위쪽
                    if(nodes[i][j-1].num<=nodes[i][j].num){
                        nodes[i][j].cost = nodes[i][j].num-nodes[i][j-1].num+ nodes[i][j-1].cost +1;
                    }
                    else{
                        nodes[i][j].cost = nodes[i][j-1].cost;
                    }
                }
                else if (j==0&&i!=0) {// 제일 좌측
                    if(nodes[i-1][j].num<=nodes[i][j].num){
                        nodes[i][j].cost = nodes[i][j].num-nodes[i-1][j].num+nodes[i-1][j].cost +1;
                    }
                    else{
                        nodes[i][j].cost = nodes[i-1][j].cost;
                    }
                }
                else if(i*j!=0){
                    //위에서 내려오는 비용과 왼쪽에서 온 비용을 비교해서 dp 저장해주기
                    int aboveCost=nodes[i-1][j].cost;
                    int leftCost=nodes[i][j-1].cost;
                    if(nodes[i-1][j].num <=nodes[i][j].num){
                        aboveCost+=nodes[i][j].num-nodes[i-1][j].num+1;
                    }
                    if(nodes[i][j-1].num <=nodes[i][j].num){
                        leftCost+=nodes[i][j].num-nodes[i][j-1].num+1;
                    }
                    nodes[i][j].cost=Math.min(aboveCost, leftCost);
                }
            }
        }
        System.out.print(nodes[N-1][N-1].cost);
    }
}
