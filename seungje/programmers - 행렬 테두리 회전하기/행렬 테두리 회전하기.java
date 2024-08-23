class Solution {
    static int[][] arr;
    static int[] ans;
    
    static void rotate(int[] query, int idx){ // 회전
        int x1 = query[0], y1 = query[1]; // 쿼리 추출
        int x2 = query[2], y2 = query[3];

        int row = x1, col = y1;
        int tmp = arr[row][col]; // 시작 점 값 저장 (지워지기 때문)
        int min = tmp;
        
        do{ // 회전 & 최소값 구하기
            if(min > arr[row][col]) min = arr[row][col];
            
            if(col == y1 && row < x2)      arr[row][col] = arr[++row][col];
            else if(row == x2 && col <y2)  arr[row][col] = arr[row][++col];
            else if(col == y2 && row > x1) arr[row][col] = arr[--row][col];
            else if(row == x1 && col > y1) arr[row][col] = arr[row][--col];
        }
        while(!(row == x1 && col == y1)); // 다시 원점으로 올 경우 STOP
        
        arr[x1][y1+1] = tmp; // 시작 점 값 저장
        
        ans[idx++] = min; // min값 저장
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        ans = new int[queries.length];
        
        arr = new int[rows+1][columns+1];
        for(int i =1; i<=rows; i++){
            for(int j = 1; j<=columns; j++){
                arr[i][j] = (i-1)*columns+ j;
            }
        }
        
        for(int i = 0; i<queries.length; i++){
            rotate(queries[i],i);
        }
        
        return ans; 
    }
}