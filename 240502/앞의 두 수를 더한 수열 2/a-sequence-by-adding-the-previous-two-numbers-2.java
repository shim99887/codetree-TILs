import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Main {
    static String testcase = "11";

    static int n;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());
        memo = new int[n];

        System.out.println(fibo(n-1));
    }

    static int fibo(int depth){
        if(depth == 0 || depth == 1) {
            memo[depth] = 1;
            return memo[depth];
        }

        if(memo[depth] > 0){
            return memo[depth];
        }

        return fibo(depth - 1) + fibo(depth - 2);
    }
}