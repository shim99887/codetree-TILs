import java.io.*;
import java.util.*;

public class Main {

    static String testcase = "5\n" +
            "522\n" +
            "6\n" +
            "84\n" +
            "7311\n" +
            "19\n";


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

        for (int i = 0; i < n; i++) {
            solve(i, new boolean[n]);
        }

        System.out.println(answer);
    }

    static void solve(int depth, boolean[] visited) {
        if (depth >= n) {
            return;
        }

        for (int i = depth; i < n; i++) {
            if (!visited[i] && check(arr[i], visited)) {
                visited[i] = true;
                solve(i + 1, visited);
                visited[i] = false;
            }
        }

        int temp = 0;

        for(int i=0; i < visited.length; i++){
            if(visited[i]){
                temp++;
            }
        }
        answer = Math.max(answer, temp);
    }

    static boolean check(int value, boolean[] visited) {
        List<Integer> arr2 = new ArrayList<>();
        arr2.add(value);
        int maxLen = String.valueOf(value).length();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                arr2.add(arr[i]);
                maxLen = Math.max(maxLen, String.valueOf(arr[i]).length());
            }
        }

        for (int i = 0; i < maxLen; i++) {
            int temp = 0;
            for (int j = 0; j < arr2.size(); j++) {
                temp += arr2.get(j) % 10;
                arr2.set(j, arr2.get(j) / 10);
            }

            if (temp > 10) {
                return false;
            }
        }
        return true;
    }
}