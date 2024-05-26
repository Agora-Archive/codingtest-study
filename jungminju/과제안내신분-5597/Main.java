import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Boolean []check = new Boolean[30];
        Arrays.fill(check, Boolean.FALSE);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<28;i++){
            int index = Integer.parseInt(br.readLine())-1;
            check[index] = true;
        }
        for(int i=0;i<check.length; i++){
            if(check[i]==false) System.out.println(i+1);
        }
    }
}