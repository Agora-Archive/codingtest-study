import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] arr;
	static int a = 0, b = 0;
	static int input;
	static int result_x = 0, result_y = 0;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		arr = new int[n][n];
		// 0,0번째 등장하는 수는 n^2이므로 거기서부터 안쪽으로 채워나가는 식으로 진행해주겠다.
		input = n * n;
		if (n == 1) {
			System.out.print("1" + "\n" + "0 0");
			return;
		}
		for (int aa = 0; aa < (n + 1) / 2; aa++) {

			goDown(n);
			goRight(n);
			goUp(n);
			goLeft(n);

		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(arr[i][j] + " ");
			}
			if (i != n - 1) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
		System.out.println(++result_x + " " + ++result_y);
	}
	// 현재 좌표를 저장할 static int 변수 a,b생성
	// 그 좌표들을 인자로 받아와서 각 뱡향의 0이 아니거나 배열의 범위안에서 끝까지 가면서
	// 숫자를 넣어줌

	static void goDown(int n) {
		while (a < n && arr[a][b] == 0) {
			if (input == k) {
				result_x = a;
				result_y = b;
			}
			arr[a++][b] = input--;
		}
		a--;
		if (b < n - 1) {
			b++;
		}

	}

	static void goRight(int n) {
		while (b < n && arr[a][b] == 0) {
			if (input == k) {
				result_x = a;
				result_y = b;
			}
			arr[a][b++] = input--;
		}
		b--;
		if (a > 0) {
			a--;
		}
	}

	static void goUp(int n) {
		while (a >= 0 && arr[a][b] == 0) {
			if (input == k) {
				result_x = a;
				result_y = b;
			}
			arr[a--][b] = input--;
		}
		a++;
		if (b > 0) {
			b--;
		}
	}

	static void goLeft(int n) {
		while (b >= 0 && arr[a][b] == 0) {
			if (input == k) {
				result_x = a;
				result_y = b;
			}
			arr[a][b--] = input--;
		}
		b++;
		if (a < n - 1) {
			a++;
		}
	}
}
