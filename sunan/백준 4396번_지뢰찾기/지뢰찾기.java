package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==1){
        	String input = br.readLine();
        	if(input.charAt(0)=='*') {
        		String inputs = br.readLine();
        		if(inputs.charAt(0)=='x') {
        			System.out.print("*");
        		}
        		else {
        			System.out.print(".");
        		}
        	}else {
        		String inputss = br.readLine();
        		System.out.print("0");
        	}
            return;
        }
        char[][] arr = new char[n][n];
        int[][] mine = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == '*') {
                    if ((i > 0 && j > 0) && (i < n - 1 && j < n - 1)) {
                        // 여유칸이 1칸씩 이상 있을경우
                        mine[i - 1][j - 1]++;
                        mine[i][j - 1]++;
                        mine[i - 1][j]++;
                        mine[i + 1][j]++;
                        mine[i][j + 1]++;
                        mine[i + 1][j - 1]++;
                        mine[i - 1][j + 1]++;
                        mine[i + 1][j + 1]++;
                    } else if (i == 0 && (j > 0 && j < n - 1)) {
                        // 위쪽 가생이 끝쪽은 뺴고
                        mine[i][j - 1]++;
                        mine[i][j + 1]++;
                        mine[i + 1][j]++;
                        mine[i + 1][j - 1]++;
                        mine[i + 1][j + 1]++;
                    } else if (j == 0 && (i > 0 && i < n - 1)) {
                        // 왼쪽 가생이 끝은 뺴고
                        mine[i - 1][j]++;
                        mine[i - 1][j + 1]++;
                        mine[i][j + 1]++;
                        mine[i + 1][j + 1]++;
                        mine[i + 1][j]++;
                    } else if (j == n - 1 && (i > 0 && i < n - 1)) {
                        // 오른쪽 가생이 끝은 뺴고
                        mine[i - 1][j]++;
                        mine[i - 1][j - 1]++;
                        mine[i][j - 1]++;
                        mine[i + 1][j - 1]++;
                        mine[i + 1][j]++;
                    } else if (i == n-1 && (j > 0 && j < n - 1)) {
                        // 밑쪽 가생이 끝쪽은 뺴고
                        mine[i][j - 1]++;
                        mine[i][j + 1]++;
                        mine[i - 1][j]++;
                        mine[i - 1][j - 1]++;
                        mine[i - 1][j + 1]++;
                    } else if ((i == 0 && j == 0) ) {
                        // 왼쪽위꼭짓점
                        mine[0][1]++;
                        mine[1][1]++;
                        mine[1][0]++;
                    } else if ((i == n - 1 && j == n - 1) ) {
                        // 오른쪽아래꼭짓점
                        mine[i - 1][j - 1]++;
                        mine[i - 1][j]++;
                        mine[i][j - 1]++;
                    } else if ((i == 0 && j == n - 1) ) {
                        // 오른쪽 위 꼭짓점
                        mine[0][j - 1]++;
                        mine[1][j - 1]++;
                        mine[1][j]++;
                    } else if ((i == n - 1 && j == 0) ) {
                        // 왼쪽 아래 꼭지점
                        mine[i - 1][j]++;
                        mine[i - 1][j + 1]++;
                        mine[i][j + 1]++;
                    }
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        boolean [][]step = new boolean [n][n];
        boolean boom=false;
        for (int i = 0; i < n; i++) {
            String inputs = br.readLine();
            for (int j = 0; j < n; j++) {
                if(inputs.charAt(j)=='x'){
                    step[i][j]=true;
                    if(arr[i][j]=='*'){
                        boom=true;
                    }
                }
            }
        }
        if(boom){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j]=='*'){
                        sb.append('*');
                    }
                    else if(step[i][j]){
                        sb.append(mine[i][j]);
                    }
                    else{
                        sb.append('.');
                    }
                }
                sb.append('\n');
            }
        }
        else{
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(step[i][j]){
                        sb.append(mine[i][j]);
                    }
                    else{
                        sb.append('.');
                    }
                }
