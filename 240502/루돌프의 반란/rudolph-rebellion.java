import java.io.*;
import java.util.*;

public class Main {

    static String testcase = "5 7 4 2 2\n" +
            "3 2\n" +
            "1 1 3\n" +
            "2 3 5\n" +
            "3 5 1\n" +
            "4 4 4";

    static int n, m, p, c, d, removeIdx;
    static List<Piece> santaList;
    static List<Piece> originSantaList;
    static List<Piece> removedSantaList;
    static List<Integer> removedIdxList;
    static Piece deer;

    static Piece[][] map;

    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new StringReader(testcase));
        StringBuilder sb = new StringBuilder();
        santaList = new ArrayList<>();
        originSantaList = new ArrayList<>();
        removedSantaList = new ArrayList<>();

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        p = Integer.parseInt(split[2]);
        c = Integer.parseInt(split[3]);
        d = Integer.parseInt(split[4]);

        split = br.readLine().split(" ");
        deer = new Piece(Integer.parseInt(split[0]) - 1, Integer.parseInt(split[1]) - 1);
        map = new Piece[n][n];
        removedIdxList = new ArrayList<>();

        map[deer.r][deer.c] = deer;

        for (int i = 0; i < p; i++) {
            split = br.readLine().split(" ");
            Piece santa = new Piece(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]) - 1);
            santa.index = Integer.parseInt(split[0]);
            santaList.add(santa);
            originSantaList.add(santa);
            map[santaList.get(i).r][santaList.get(i).c] = santaList.get(i);
        }

        santaList.sort(Comparator.comparingInt(o -> o.index));
        originSantaList.sort(Comparator.comparingInt(o -> o.index));

        for(int i=0; i < m; i++){
            deerMove();
            if(removeSanta(false)){
                break;
            };
            moveSanta();
            if(removeSanta(true)){
                break;
            };
        }
        for(int i=0; i < originSantaList.size(); i++){
            sb.append(originSantaList.get(i).score).append(" ");
        }

        System.out.println(sb);
    }

    static boolean removeSanta(boolean score){
        for(int i=0; i < removedIdxList.size(); i++){
            int idx = -1;
            for(int j=0; j <santaList.size(); j++){
                if(removedIdxList.get(i) == santaList.get(j).index){
                    idx = j;
                    break;
                }
            }

            if(idx >= 0){
                removedSantaList.add(santaList.remove(idx));
            }
        }

        if(score){
            for(Piece santa: santaList){
                santa.score++;
            }
        }
        removedIdxList.clear();
        return santaList.isEmpty();
    }

    static void deerMove() {
        Piece farSanta = santaList.get(0);
        int minDistance = santaList.get(0).distance(deer);

        for (int i = 1; i < santaList.size(); i++) {
            int distance = santaList.get(i).distance(deer);
            if (distance == minDistance) {
                if (farSanta.r < santaList.get(i).r) {
                    farSanta = santaList.get(i);
                } else if (farSanta.r == santaList.get(i).r) {
                    if (farSanta.c < santaList.get(i).c) {
                        farSanta = santaList.get(i);
                    }
                }
            } else if (distance < minDistance) {
                farSanta = santaList.get(i);
                minDistance = distance;
            }
        }

        int dr = Integer.compare(farSanta.r - deer.r, 0);
        int dc = Integer.compare(farSanta.c - deer.c, 0);

        map[deer.r][deer.c] = null;
        deer.r += dr;
        deer.c += dc;

        if(map[deer.r][deer.c] != null){
            santaDeerCollapse(map[deer.r][deer.c], dr, dc, false);
        }
        map[deer.r][deer.c] = deer;
    }

    static void moveSanta() {
        int idx = 0;
        for (Piece santa : santaList) {
            if (santa.faint) {
                santa.faintDuration++;
                if(santa.faintDuration == 1){
                    santa.faintDuration = 0;
                    santa.faint = false;
                }
                continue;
            }

            int dr = Integer.compare(deer.r - santa.r, 0);
            int dc = Integer.compare(deer.c - santa.c, 0);

            boolean drOver = false;

            if (dr < 0) {
                drOver = true;
            } else if (dr > 0) {
                if (dc <= 0) {
                    drOver = true;
                }
            }

            int tempDistance = calcDistance(santa.r + dr, santa.c, deer.r, deer.c);
            int tempDistance2 = calcDistance(santa.r, santa.c + dc, deer.r, deer.c);

            if (tempDistance == tempDistance2) {
                if (drOver) {
                    if (isIn(santa.r + dr, santa.c)) {
                        if(isSanta(santa, dr, 0)){
                            if (isIn(santa.r, santa.c + dc)) {
                                santaMoveCol(santa, dc);
                            }
                        } else {
                            santaMoveRow(santa, dr);
                        }
                    } else {
                        if (isIn(santa.r, santa.c + dc)) {
                            santaMoveCol(santa, dc);
                        }
                    }
                } else {
                    if (isIn(santa.r, santa.c + dc)) {
                        if(isSanta(santa, 0, dc)){
                            if (isIn(santa.r + dr, santa.c)) {
                                santaMoveRow(santa, dr);
                            }
                        } else {
                            santaMoveCol(santa, dc);
                        }
                    } else {
                        if (isIn(santa.r + dr, santa.c)) {
                            santaMoveRow(santa, dr);
                        }
                    }
                }
            } else {
                if (tempDistance < tempDistance2) {
                    if (isIn(santa.r + dr, santa.c)) {
                        if(isSanta(santa, dr, 0)){
                            if (isIn(santa.r, santa.c + dc)) {
                                santaMoveCol(santa, dc);
                            }
                        } else {
                            santaMoveRow(santa, dr);
                        }
                    } else {
                        if (isIn(santa.r, santa.c + dc)) {
                            santaMoveCol(santa, dc);
                        }
                    }
                } else {
                    if (isIn(santa.r, santa.c + dc)) {
                        if(isSanta(santa, 0, dc)){
                            if (isIn(santa.r + dr, santa.c)) {
                                santaMoveRow(santa, dr);
                            }
                        } else {
                            santaMoveCol(santa, dc);
                        }
                    } else {
                        if (isIn(santa.r + dr, santa.c)) {
                            santaMoveRow(santa, dr);
                        }
                    }
                }
            }

            idx++;
        }
    }

    /**
     * 산타랑 루돌프 만날 때
     *
     * @param santa   만난 산타
     * @param dr      변화량
     * @param dc      변화량
     * @param isSanta 산타가 부딪힌건지..
     */
    static void santaDeerCollapse(Piece santa, int dr, int dc, boolean isSanta) {
        santa.faint = true;
        if (isSanta) {
            santa.score += d;
            if (isIn(santa.r + dr * d * -1, santa.c + d * dc * -1)) {
                if (isBlank(santa, dr * d * -1, dc * d * -1)) {
                    santa.r = santa.r + dr * d * -1;
                    santa.c = santa.c + dc * d * -1;
                    map[santa.r][santa.c] = santa;
                } else {
                    collapse(santa, dr, dc, -d, isSanta);
                }
            } else {
                removedIdxList.add(santa.index);
            }
        } else {
            santa.faintDuration--;
            santa.score += c;
            if (isIn(santa.r + dr * c, santa.c + c * dc)) {
                if (isBlank(santa, dr * c, dc * c)) {
                    santa.r = santa.r + dr * c;
                    santa.c = santa.c + dc * c;
                    map[santa.r][santa.c] = santa;
                    map[deer.r][deer.c] = deer;
                } else {
                    map[deer.r][deer.c] = deer;
                    collapse(santa, dr, dc, c, isSanta);
                }
            } else {
                removedIdxList.add(santa.index);
            }
        }
    }

    static void collapse(Piece santa, int dr, int dc, int force, boolean isSanta) {
        if (isIn(santa.r + dr * force, santa.c + dc * force)) {
            if (isBlank(santa, dr * force, dc * force)) {
                santa.r = santa.r + dr * force;
                santa.c = santa.c + dc * force;
                map[santa.r][santa.c] = santa;
                return;
            }
            santa.r = santa.r + dr * force;
            santa.c = santa.c + dc * force;
            Piece collapseSanta = map[santa.r][santa.c];

            map[santa.r][santa.c] = santa;
            collapse(collapseSanta, dr, dc, isSanta ? -1 : 1, isSanta);
        } else {
            removedIdxList.add(santa.index);
        }
    }

    static void santaMoveCol(Piece santa, int dc) {
        if (isBlank(santa, 0, dc)) {
            map[santa.r][santa.c] = null;
            santa.c += dc;
            map[santa.r][santa.c] = santa;
        } else if (isDeer(santa, 0, dc)) {
            map[santa.r][santa.c] = null;
            santa.c += dc;
            santaDeerCollapse(santa, 0, dc, true);
        }
    }

    static void santaMoveRow(Piece santa, int dr) {
        if (isBlank(santa, dr, 0)) {
            map[santa.r][santa.c] = null;
            santa.r += dr;
            map[santa.r][santa.c] = santa;
        } else if (isDeer(santa, dr, 0)) {
            map[santa.r][santa.c] = null;
            santa.r += dr;
            santaDeerCollapse(santa, dr, 0, true);
        }
    }

    static boolean isDeer(Piece santa, int dr, int dc) {
        return santa.r + dr == deer.r && santa.c + dc == deer.c;
    }

    static boolean isBlank(Piece santa, int dr, int dc) {
        return map[santa.r + dr][santa.c + dc] == null;
    }

    static boolean isSanta(Piece santa, int dr, int dc) {
        return map[santa.r + dr][santa.c + dc] != null && ((santa.r + dr != deer.r) || (santa.c + dc != deer.c)) ;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    static int calcDistance(int r, int c, int ar, int ac) {
        return (r - ar) * (r - ar) + (c - ac) * (c - ac);
    }

    static class Piece {
        int r, c, faintDuration, index, score;
        boolean faint;

        Piece(int r, int c) {
            this.r = r;
            this.c = c;
            faint = false;
            faintDuration = 0;
            score = 0;
        }

        Piece(String r, String c) {
            this.r = Integer.parseInt(r);
            this.c = Integer.parseInt(c);
        }

        int distance(Piece another) {
            return (r - another.r) * (r - another.r) + (c - another.c) * (c - another.c);
        }
    }
}