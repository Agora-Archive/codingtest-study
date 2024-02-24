import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args){
        result( input());
    }

    private static int[][] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int [][]timetable = new int [n][2];
        for(int i =0; i<n; i++){
            String [] input = br.readLine().split(" ");
            timetable[i][0] = Integer.parseInt(input[0]);
            timetable[i][1] = Integer.parseInt(input[1]);
        }
        return timetable;
    }
		private static void result(int [][]arr){
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
								if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        int cnt =0;
        int pre_endtime =0;
        for(int i =0; i< n; i++){
            if(pre_endtime<=arr[i][0]){
                pre_endtime=arr[i][1];
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
