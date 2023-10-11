import java.io.*;
import java.util.*;

public class Main {
    static class dpValue{
        int max,min;
        public dpValue(int max, int min){
            this.max=max;
            this.min=min;
        }
    }
    static int N;
    static ArrayList<Integer> num = new ArrayList<>();    //수식의 숫자 저장 리스트
    static ArrayList<Character> operator = new ArrayList<>();    //수식의 연산자 저장 리스트
    static dpValue[][] DP;        //각 구간의 최대값, 최소값 저장하는 메모이제이션
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int size = N/2+2;
        DP = new dpValue[size][size];

        String arr = br.readLine();
        for (int i=0;i<N;i++){
            if (i%2==0){
                int temp = arr.charAt(i)-'0';
                num.add(temp);
                int index = i/2+1;
                DP[index][index]=new dpValue(temp,temp);
            }
            else {
                operator.add(arr.charAt(i));
            }
        }
        for(int i=2;i<size;i++){
            for(int j=i-1;j>0;j--){
                cal(i,j);
            }
        }
        System.out.println(DP[1][size-1].max);
    }

    static void cal(int x, int y){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<x-y;i++){
            dpValue value1 = DP[y+i+1][x];
            dpValue value2 = DP[y][y+i];
            int operatorIndex = y+i-1;
            if(operator.get(operatorIndex) == '+'){	//사용 연산자 '+'
                max = Math.max(max,value1.max + value2.max);
                min = Math.min(min,value1.min + value2.min);
            }else if(operator.get(operatorIndex)=='-'){	//사용 연산자 '-'
                max = Math.max(value2.max - value1.min, max);
                min = Math.min(value2.min - value1.max,min);
            }else{		//사용 연산자 '*'
                int temp = value1.max * value2.max;
                int tempMin = temp;
                int tempMax = temp;

                temp = value1.max * value2.min;
                tempMax = Math.max(temp,tempMax);
                tempMin = Math.min(temp,tempMin);

                temp = value1.min * value2.max;
                tempMax = Math.max(temp,tempMax);
                tempMin = Math.min(temp,tempMin);

                temp = value1.min * value2.min;
                tempMax = Math.max(temp,tempMax);
                tempMin = Math.min(temp,tempMin);

                max = Math.max(max,tempMax);
                min = Math.min(min,tempMin);
            }
        }
        DP[y][x] = new dpValue(max,min);		//최대값, 최소값 저장
    }
}