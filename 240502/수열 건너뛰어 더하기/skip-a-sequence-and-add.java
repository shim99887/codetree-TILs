import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {

    //6
    static String testcase = "184\n" +
            "39408\n" +
            "25518\n" +
            "53448\n" +
            "32212\n" +
            "14364\n" +
            "30235\n" +
            "45454\n" +
            "86425\n" +
            "60605\n" +
            "9630\n" +
            "61989\n" +
            "45360\n" +
            "41678\n" +
            "3588\n" +
            "18258\n" +
            "33938\n" +
            "69590\n" +
            "86082\n" +
            "10614\n" +
            "33059\n" +
            "92083\n" +
            "40928\n" +
            "68583\n" +
            "73322\n" +
            "75049\n" +
            "70007\n" +
            "4623\n" +
            "42357\n" +
            "71799\n" +
            "97687\n" +
            "80455\n" +
            "88404\n" +
            "27346\n" +
            "9286\n" +
            "30774\n" +
            "10173\n" +
            "50932\n" +
            "44724\n" +
            "60262\n" +
            "67414\n" +
            "64498\n" +
            "52611\n" +
            "91242\n" +
            "8335\n" +
            "55602\n" +
            "22046\n" +
            "28933\n" +
            "30631\n" +
            "52927\n" +
            "63289\n" +
            "40369\n" +
            "78683\n" +
            "1304\n" +
            "14608\n" +
            "13686\n" +
            "96585\n" +
            "61865\n" +
            "84679\n" +
            "27370\n" +
            "35263\n" +
            "5847\n" +
            "79917\n" +
            "9901\n" +
            "32460\n" +
            "58128\n" +
            "95441\n" +
            "38152\n" +
            "51220\n" +
            "24234\n" +
            "34158\n" +
            "42335\n" +
            "59875\n" +
            "20924\n" +
            "85585\n" +
            "52571\n" +
            "64468\n" +
            "38880\n" +
            "66090\n" +
            "55165\n" +
            "47840\n" +
            "40788\n" +
            "40254\n" +
            "79840\n" +
            "52478\n" +
            "43657\n" +
            "33138\n" +
            "34356\n" +
            "94859\n" +
            "28139\n" +
            "32115\n" +
            "31536\n" +
            "20676\n" +
            "24929\n" +
            "15972\n" +
            "86108\n" +
            "85280\n" +
            "37140\n" +
            "61424\n" +
            "62754\n" +
            "61603\n" +
            "66897\n" +
            "11762\n" +
            "43113\n" +
            "43573\n" +
            "90074\n" +
            "96977\n" +
            "45326\n" +
            "91365\n" +
            "52819\n" +
            "9459\n" +
            "2241\n" +
            "9056\n" +
            "91010\n" +
            "53133\n" +
            "43945\n" +
            "71813\n" +
            "96545\n" +
            "55896\n" +
            "3660\n" +
            "54631\n" +
            "32194\n" +
            "27014\n" +
            "70828\n" +
            "95247\n" +
            "32068\n" +
            "98455\n" +
            "99467\n" +
            "43430\n" +
            "48177\n" +
            "6798\n" +
            "53772\n" +
            "22175\n" +
            "64827\n" +
            "45239\n" +
            "26570\n" +
            "65444\n" +
            "81560\n" +
            "63024\n" +
            "47617\n" +
            "29740\n" +
            "42309\n" +
            "27831\n" +
            "67557\n" +
            "10771\n" +
            "88206\n" +
            "9355\n" +
            "90193\n" +
            "74354\n" +
            "13351\n" +
            "14206\n" +
            "11400\n" +
            "68452\n" +
            "46398\n" +
            "91481\n" +
            "32947\n" +
            "2549\n" +
            "28768\n" +
            "63385\n" +
            "71794\n" +
            "46080\n" +
            "19132\n" +
            "83826\n" +
            "22550\n" +
            "35189\n" +
            "91771\n" +
            "77821\n" +
            "60268\n" +
            "88963\n" +
            "4\n" +
            "72839\n" +
            "12684\n" +
            "77615\n" +
            "85947\n" +
            "46958\n" +
            "89489\n" +
            "17556\n" +
            "1310\n" +
            "1837\n" +
            "32391\n" +
            "81853\n" +
            "10315\n" +
            "8259\n" +
            "50750\n" +
            "35211\n";

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