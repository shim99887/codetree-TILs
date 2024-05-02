import java.io.*;
import java.util.*;

public class Main {

    static String testcase = "15\n" +
            "241032\n" +
            "6530050\n" +
            "12210124\n" +
            "46100501\n" +
            "533231\n" +
            "28290465\n" +
            "1101021\n" +
            "17245104\n" +
            "3745209\n" +
            "10001\n" +
            "10034301\n" +
            "43216500\n" +
            "19984654\n" +
            "53552499\n" +
            "20003100";


    static int n, answer = 0;
    static int[] arr, memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        memo = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solve(0, 0, 0);

        System.out.println(answer);
    }

    static void solve(int depth, int cnt, int sum) {
        answer = Math.max(answer, cnt);
        if (depth >= n) {
            return;
        }

        for (int i = depth; i < n; i++) {
            if (check(sum, arr[i])) {
                solve(i + 1, cnt + 1, sum + arr[i]);
            }
        }

    }

    static boolean check(int sum, int target) {

        while (sum > 0 || target > 0) {
            if ((sum % 10 + target % 10) >= 10) {
                return false;
            }
            sum /= 10;
            target /= 10;
        }
        return true;
    }
}