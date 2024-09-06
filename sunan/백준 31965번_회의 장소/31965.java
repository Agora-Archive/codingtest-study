import java.io.*;
import java.util.*;

public class Main{
    static long []houseNum;
    static long []preFix;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NQ = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NQ.nextToken());
        int Q = Integer.parseInt(NQ.nextToken());
        StringTokenizer House = new StringTokenizer(br.readLine());
        houseNum = new long[N+1];
        preFix = new long[N+1];
        for(int i=1; i<=N; i++){
            int house = Integer.parseInt(House.nextToken());
            houseNum[i]=house;
            preFix[i] = preFix[i-1]+house;
        }
        while (Q--!=0){
            StringTokenizer LR = new StringTokenizer(br.readLine());
            getLR(Integer.parseInt(LR.nextToken()), Integer.parseInt(LR.nextToken()));
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void getLR(int left, int right) {// 1 3 5 10 11  LR 2 2
        int l = Arrays.binarySearch(houseNum, left);
        if(l <0){
            l = -1*l-1;
        }
        int r = Arrays.binarySearch(houseNum, right);
        if(r <0){
            r = -1*r-2;
        }
        if(l>=r){
            sb.append("0"+"\n");
            return;
        }
        calCost(l, r);
    }
    static long getCost(int i, int j, int k) {
        long leftCost = (k - i + 1) * houseNum[k] - (preFix[k] - preFix[i - 1]);
        long rightCost = (preFix[j] - preFix[k - 1]) - (j - k + 1) * houseNum[k];
        return leftCost + rightCost;
    }
    static void calCost(int l, int r){
        long max = Math.max(getCost(l, r, l), getCost(l, r, r));
        long min = getCost(l, r, (l + r) / 2);
        sb.append((max-min)+"\n");
    }
}
