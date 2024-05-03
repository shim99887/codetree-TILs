import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    static String testcase = "3\n";
    static int n, answer;
    static int[] arr;

    static StringBuilder sb;

    static Set<int[]> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        answer = 0;
        arr = new int[n];
        set = new HashSet<>();

        solve(0);

        System.out.println(answer);
    }

    static void solve(int depth) {
        if (depth == n) {
            answer++;
            return;
        } else if(depth > n){
            return;
        }

        for (int i = 1; i <= 4; i++) {
            if (depth + i - 1 <= n - 1) {
                for (int j = depth; j < depth + i; j++) {
                    arr[j] = i;
                }
            }
                solve(depth + i);
        }
    }

}