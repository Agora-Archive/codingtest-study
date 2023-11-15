import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main{
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk1 = new StringTokenizer(br.readLine());
		Stack<Integer>wall = new Stack<Integer>();
		int height = Integer.parseInt(tk1.nextToken());
		int width = Integer.parseInt(tk1.nextToken());
		StringTokenizer tk2 = new StringTokenizer(br.readLine());
		int []wallHeight= new int[width];
		//벽 각각의 높이 저장
		int min=height+1;
		for(int i=0; i<width; i++) {
			wallHeight[i]=Integer.parseInt(tk2.nextToken());
			if(wallHeight[i]<min)
				min=wallHeight[i];
		}
		//제일 낮은 벽을 전체에서 다 빼줌(바닥느낌-> 연산줄어듦)
		if(min!=0) {
			height-=min;
			for(int i=0; i<width; i++) {
				wallHeight[i]-=min;
			}	
		}
		int water=0;//^^
		for(int i=height; i>=1; i--) {
			//위에서 부터 한줄씩 검사를 해나감
			for(int j=1; j<=width; j++) {
				if(wallHeight[j-1]>=i) {//i는 검사하는 라인
					if(wall.isEmpty()) {//스택이 비어있다면 처음 맞딱뜨린 벽이다.
						wall.push(j);//2차원 좌표평면이라 가정했을때 x축의 값인 j를 stack에 넣어줌
					}
					else {
						water+=j-wall.pop()-1;
//x좌표의 차이만큼 높이는 어차피 1이니까 water에다 넣어줌 (붙어있더라도 어차피 0이라 상관x)
						wall.push(j);//이 벽도 넣어줘야 다음벽과 상호작용이 된다.
					}
				}
				
			}
			wall.pop();
		}
		System.out.println(water);
		
	}
}
