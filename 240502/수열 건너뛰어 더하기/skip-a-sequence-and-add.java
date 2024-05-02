import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "6\n" +
            "10\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "10\n" +
            "20\n";

    static int n;
    static int[] arr;
    static int[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        memo = new int[n][2];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        solve(0, 0, -1, -1);
        solve(1, 0, -1, -1);
        int max = Integer.MIN_VALUE;

        for(int i=0; i < 2; i++){
            max = Math.max(memo[n-1][i], max);
        }

        System.out.println(max);
    }

    static void solve(int depth, int concurrency, int beforeIdx, int beforeConcurrency) {
        if(depth == n-1){
            memo[depth][concurrency] = Math.max(memo[depth][concurrency], memo[beforeIdx][beforeConcurrency] + arr[depth]);
            return;
        }
        if (depth > n - 1) {
            return;
        }

        if(beforeIdx < 0){
            memo[depth][0] = Math.max(memo[depth][0], arr[depth]);
        } else {
            memo[depth][concurrency] = Math.max(memo[depth][concurrency], memo[beforeIdx][beforeConcurrency] + arr[depth]);
        }

        if (concurrency < 1) {
            solve(depth + 1, concurrency + 1, depth, concurrency);
            solve(depth + 2, 0, depth, concurrency);
        } else {
            solve(depth + 2, 0, depth, concurrency);
        }
    }

}