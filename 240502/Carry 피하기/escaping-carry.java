import java.io.*;
import java.util.*;

public class Main {

    static String testcase = "20\n" +
            "54131012\n" +
            "222411\n" +
            "11000310\n" +
            "1530111\n" +
            "15001011\n" +
            "21234311\n" +
            "12211121\n" +
            "101211\n" +
            "21114541\n" +
            "24115401\n" +
            "122001\n" +
            "2012114\n" +
            "12011412\n" +
            "21120235\n" +
            "2141525\n" +
            "5121521\n" +
            "32252150\n" +
            "23321012\n" +
            "12500125\n" +
            "12513510";


    static int n, answer = 0;
    static int[] arr, memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        memo = new int[n];
//        visited = new boolean[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

//        for (int i = 0; i < n; i++) {
            solve(0, 0, 0);
//        }

        System.out.println(answer);
    }

    static void solve(int depth, int cnt, int sum) {
        answer = Math.max(answer, cnt);
        if (depth >= n) {
            return;
        }

        for (int i = depth; i < n; i++) {
            if(check(sum, arr[i])){
                solve(depth + 1, cnt + 1, sum + arr[i]);
            }
        }

    }

    static boolean check(int sum, int target) {

        while(sum > 0 || target > 0){
            if((sum % 10 + target % 10) >= 10){
                return false;
            }
            sum /= 10;
            target /= 10;
        }
        return true;
    }
}