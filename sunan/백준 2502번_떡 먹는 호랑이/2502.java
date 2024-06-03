import java.io.*;
import java.util.*;

public class Main {
    static class pair{
        public int a, b;
        public pair(int a, int b){
            this.a = a;
            this.b = b;
        }
        public int getA(){
            return this.a;
        }
        public int getB(){
            return this.b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []str = br.readLine().split(" ");
        int D = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        List<pair> record = new ArrayList<pair>();
        record.add(new pair(1,0)); //A의 계수에 1 B의 계수 0 첫날 주는 떡 개수 = A
        record.add(new pair(0,1)); //B의 계수에 0 B의 계수 1 첫날 주는 떡 개수 = B
        for(int i=2; i<D; i++){
            record.add(new pair(record.get(i-2).getA()+record.get(i-1).getA(), //A
            record.get(i-2).getB()+record.get(i-1).getB())); //B
        }
        System.out.print(figureAB(record.get(D-1).getA(), record.get(D-1).getB(), K));
    }
    static String figureAB(int a, int b, int K){
        int A=0;
        while((K-++A*a)%b!=0){}
        return A+"\n"+(K-A*a)/b; // B는 따로 저장할 필요없이 A값을 기반으로 계산
    }
}
