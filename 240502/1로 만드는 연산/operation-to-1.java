import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "1000000";

    static int n;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        memo = new int[n + 2];

        solve(n, 0);

        System.out.println(memo[1]);
    }

    static void solve(int number, int cnt){
        if(number <= 0){
            return;
        }
        if(number == 1){
            if(memo[number] == 0){
                memo[number] = cnt;
            } else {
                memo[number] = Math.min(memo[number], cnt);
            }
            return;
        }

        if(memo[number] > 0 && memo[number] < cnt){
            return;
        }

        if(number >= 3 && number % 3 == 0){
            solve(number / 3, cnt + 1);
        } else if(number >= 2 && number % 2 == 0){
            solve(number / 2, cnt + 1);
        } else {
            solve(number - 1, cnt + 1);
        }
    }
}