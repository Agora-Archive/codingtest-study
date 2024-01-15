import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int r,c;
    static int cnt =0;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        r = Integer.parseInt(str[1]);
        c = Integer.parseInt(str[2]);
        int k= (int)Math.pow(2, n);
        Z(0, 0, k);
        System.out.print(cnt);
    }
    public static void Z(int x, int y, int leng){
        // 4개로 나누었을때 해당 사각형에서 또 Z를 돌리는 방식으로 진행 해당 좌표가 나눌수없는 최소의 사각형이 될때까지
        if(c <x+leng/2 && r <y+leng/2){//첫 사각형
            //System.out.print("좌상 ");
            if(leng!=2){

                Z(x, y, leng/2);
            }
        }
        else if(c >=x+leng/2 && r <y+leng/2){// 우측 상단 사각형
            //System.out.print("우상 ");
            cnt+=(int)Math.pow((double) leng /2, 2);
            if(leng!=2){
                Z(x+leng/2, y, leng/2);
            }
        }
        else if(c <x+leng/2 && r >=y+leng/2){// 좌측 하단 사각형
            //System.out.print("좌하 ");
            cnt+=(int)Math.pow((double) leng /2, 2)*2;
            if(leng!=2) {
                Z(x, y + leng / 2, leng / 2);
            }
        }
        else if(c  >=x+leng/2 && r >=y+leng/2){// 우측 하단 사각형
            //System.out.print("우하 ");
            cnt+=(int)Math.pow((double) leng /2, 2)*3;
            if(leng/2==c && leng/2 ==r){// 이런 경우일 때 쓸데 없는 연산을 줄여줌
                System.out.print(cnt);
                return;
            }
            if(leng!=2){
                Z(x+leng/2, y+leng/2, leng/2);
            }
        }
    }
}
