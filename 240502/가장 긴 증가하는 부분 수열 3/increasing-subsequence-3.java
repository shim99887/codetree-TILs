import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "6\n" +
            "10 20 10 30 20 50\n";

    static int n;
    static int[] arr;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        memo = new int[n];

        String[] split = br.readLine().split(" ");

        for(int i=0; i < n; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        for(int i=0; i < n; i++){
            solve(i, -1, 1);
        }

        int answer = 0;
        for(int i=0; i < n; i++){
            answer = Math.max(answer, memo[i]);
        }

        System.out.println(answer);
    }

    static void solve(int depth, int beforeDepth, int cnt){
        if(beforeDepth < 0){
            memo[depth] = Math.max(memo[depth], 1);
        } else {
            if(cnt < memo[depth]){
                return;
            }

            memo[depth] = cnt;
        }

        if(depth >= n){
            System.out.println(cnt);
            return;
        }

        for(int i = depth + 1; i < n; i++){
            if(arr[i] > arr[depth]){
                solve(i, depth, cnt + 1);
            }
        }


    }

}