import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "65\n" +
            "15434\n" +
            "27451\n" +
            "71770\n" +
            "12323\n" +
            "89652\n" +
            "21961\n" +
            "45195\n" +
            "93689\n" +
            "12822\n" +
            "14764\n" +
            "9599\n" +
            "52543\n" +
            "92102\n" +
            "99996\n" +
            "60474\n" +
            "39517\n" +
            "98171\n" +
            "12156\n" +
            "57495\n" +
            "42273\n" +
            "13482\n" +
            "27436\n" +
            "2790\n" +
            "25491\n" +
            "88752\n" +
            "59616\n" +
            "84555\n" +
            "29952\n" +
            "89786\n" +
            "62085\n" +
            "16739\n" +
            "73037\n" +
            "72402\n" +
            "705\n" +
            "76869\n" +
            "64282\n" +
            "18189\n" +
            "58082\n" +
            "8586\n" +
            "42424\n" +
            "12522\n" +
            "26438\n" +
            "18422\n" +
            "66829\n" +
            "47808\n" +
            "79022\n" +
            "43712\n" +
            "26240\n" +
            "50538\n" +
            "42801\n" +
            "72838\n" +
            "48934\n" +
            "39743\n" +
            "22761\n" +
            "58577\n" +
            "67393\n" +
            "50729\n" +
            "79435\n" +
            "61877\n" +
            "8320\n" +
            "71157\n" +
            "31523\n" +
            "62749\n" +
            "34363\n" +
            "67524\n";

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
            if(memo[depth][concurrency] >= memo[beforeIdx][beforeConcurrency] + arr[depth]){
                return;
            }
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