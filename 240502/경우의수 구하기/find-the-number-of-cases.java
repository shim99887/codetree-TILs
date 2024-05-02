import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Main {
    static String testcase = "2 2\n";

    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        BigDecimal temp = fact(m).divide(fact(n));
        temp = temp.divide(fact(m-n));

        System.out.println(temp.toString());
    }

    static BigDecimal fact(int n){
        BigDecimal temp = new BigDecimal(1);

        for(int i=1; i <= n; i++){
            temp = temp.multiply(new BigDecimal(i));
        }

        return temp;
    }
}