public class Main {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;

        int[] counting = new int[10];
        int c;

        while((c = in.read()) != '\n'){
            counting[c-'0']++;
        }

        for (int i = 9; i >= 0; i--) {
            while (counting[i]-- > 0) {
                System.out.print(i);
            }
        }
    }
}