import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    static String testcase = "4\n" +
            "0 1 0 0\n" +
            "0 1 0 0\n" +
            "0 1 0 0\n" +
            "0 1 0 0\n";

    static int n, boomCnt, max;
    static int[][] map, bombMap;
    static int[][] bomb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(testcase));

        n = Integer.parseInt(br.readLine());
        boomCnt = 0;
        map = new int[n][n];
        bomb = new int[10][2];
        bombMap = new int[n][n];
        max = 0;

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                if (map[i][j] == 1) {
                    bomb[boomCnt][0] = i;
                    bomb[boomCnt][1] = j;
                    boomCnt++;
                }
            }
        }

        solve(0);

        System.out.println(max);
    }

    static void solve(int bombDepth) {
        if (bombDepth == boomCnt) {
            count();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            map[bomb[bombDepth][0]][bomb[bombDepth][1]] = i;
            solve(bombDepth + 1);
        }

    }

    static void count() {
        bombMap = new int[n][n];
        int answer = 0;
        for (int i = 0; i < boomCnt; i++) {
            int fr = bomb[i][0];
            int fc = bomb[i][1];
            if (bombMap[fr][fc] == 0) {
                bombMap[fr][fc] = 1;
                answer++;
            }
            if (map[fr][fc] == 1) {
                for (int j = 0; j < 2; j++) {
                    if (isIn(fr + j + 1, fc) && bombMap[fr + 1 + j][fc] == 0) {
                        bombMap[fr + 1 + j][fc] = 1;
                        answer++;
                    }
                }
                for (int j = 0; j < 2; j++) {
                    if (isIn(fr + j + 1, fc) && bombMap[fr + 1 + j][fc] == 0) {
                        bombMap[fr + 1 + j][fc] = 1;
                        answer++;
                    }
                }
            } else if (map[fr][fc] == 2) {
                if (isIn(fr + 1, fc) && bombMap[fr + 1][fc] == 0) {
                    bombMap[fr + 1][fc] = 1;
                    answer++;
                }
                if (isIn(fr - 1, fc) && bombMap[fr - 1][fc] == 0) {
                    bombMap[fr - 1][fc] = 1;
                    answer++;
                }
                if (isIn(fr, fc + 1) && bombMap[fr][fc + 1] == 0) {
                    bombMap[fr][fc + 1] = 1;
                    answer++;
                }
                if (isIn(fr, fc - 1) && bombMap[fr][fc - 1] == 0) {
                    bombMap[fr][fc - 1] = 1;
                    answer++;
                }
            } else {
                if (isIn(fr + 1, fc + 1) && bombMap[fr + 1][fc + 1] == 0) {
                    bombMap[fr + 1][fc + 1] = 1;
                    answer++;
                }
                if (isIn(fr - 1, fc - 1) && bombMap[fr - 1][fc - 1] == 0) {
                    bombMap[fr - 1][fc - 1] = 1;
                    answer++;
                }
                if (isIn(fr - 1, fc + 1) && bombMap[fr - 1][fc + 1] == 0) {
                    bombMap[fr - 1][fc + 1] = 1;
                    answer++;
                }
                if (isIn(fr + 1, fc - 1) && bombMap[fr + 1][fc - 1] == 0) {
                    bombMap[fr + 1][fc - 1] = 1;
                    answer++;
                }
            }
        }

        max = Math.max(max, answer);
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

}