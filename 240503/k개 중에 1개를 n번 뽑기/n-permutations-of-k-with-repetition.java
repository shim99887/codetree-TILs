import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    static String testcase = "3 2\n";
    static int n, k;

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));
        sb = new StringBuilder();
        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        k = Integer.parseInt(split[1]);

        solve(0, new int[k]);

        System.out.print(sb);
    }

    static void solve(int depth, int[] arr) {
        if (depth == k) {
            for(int num: arr){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            arr[depth] = i;
            solve(depth + 1, arr);
        }
    }

}