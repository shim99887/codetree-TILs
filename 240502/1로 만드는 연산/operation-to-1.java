import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "137686";

    static int n;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        memo = new int[n + 2];

        for(int i=2; i <= n; i++){
            int min = Integer.MAX_VALUE;

            if(i % 2 == 0 && min > memo[i / 2] + 1){
                min = memo[i / 2] + 1;
            }

            if(i % 3 == 0 && min > memo[i / 3] + 1){
                min = memo[i / 3] + 1;
            }

            if(min > memo[i-1] + 1){
                min = memo[i - 1] + 1;
            }

            memo[i] = min;
        }

        System.out.println(memo[n]);
    }

}