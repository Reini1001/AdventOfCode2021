import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Dezember2021_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input4.txt"));
        Integer[] bingoNumbers = Arrays.stream(reader.readLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        String[] input = reader.lines().map(x -> x += " ").toArray(String[]::new);

        StringBuilder sb = new StringBuilder();

        String bingoBordsString;

        for (String string : input) {
            if (sb.isEmpty() && string.length() == 1) {
                continue;
            }
            if (string.length() == 1) {
                sb.append(",");
                continue;
            }
            sb.append(string);
        }

        bingoBordsString = sb.toString();

        Integer[][] bingoBords = Arrays.stream(Arrays.stream(bingoBordsString.split(",")).toArray(String[]::new))
                .map(x -> Arrays.stream(x.split(" ")).filter(y -> !y.equals("")).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        Integer[][] winConditions = {
                {0, 5, 10, 15, 20},
                {1, 6, 11, 16, 21},
                {2, 7, 12, 17, 22},
                {3, 8, 13, 18, 23},
                {4, 9, 14, 19, 24},

                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9},
                {10, 11, 12, 13, 14},
                {15, 16, 17, 18, 19},
                {20, 21, 22, 23, 24}
        };

        Aufgabe1(bingoBords, bingoNumbers, winConditions);
        Aufgabe2(bingoBords, bingoNumbers, winConditions);
    }

    private static void Aufgabe1 (Integer[][] bingoBords, Integer[] bingoNumbers, Integer[][] winConditions) {
        boolean won = false;
        Integer[] winningBord = new Integer[25];
        int winningNumberIndex = -1;

        for (int i = 0; i < bingoNumbers.length; i++) {
            for (Integer[] bord : bingoBords) {
                for (Integer[] winCondition : winConditions) {
                    int count = 0;
                    for (Integer winConditionIndex : winCondition) {
                        if (Arrays.asList(Arrays.copyOfRange(bingoNumbers, 0, i + 1)).contains(bord[winConditionIndex])) {
                            count++;
                        }
                    }
                    if (count == 5) {
                        winningBord = bord;
                        winningNumberIndex = i;
                        won = true;
                        break;
                    }
                }
                if (won) {
                    break;
                }
            }
            if (won) {
                break;
            }
        }

        int finalWinningNumberIndex = winningNumberIndex;
        int unmarkedSum = Arrays.stream(winningBord).filter(x -> !Arrays.asList(Arrays.copyOfRange(bingoNumbers, 0, finalWinningNumberIndex + 1)).contains(x)).reduce(0, Integer::sum);

        System.out.println(unmarkedSum * bingoNumbers[winningNumberIndex]);
    }

    private static void Aufgabe2 (Integer[][] bingoBords, Integer[] bingoNumbers, Integer[][] winConditions) {
        int placement = 0;
        ArrayList<Integer[]> ranks = new ArrayList<>();

        for (int i = 0; i < bingoNumbers.length; i++) {
            for (int ii = 0; ii < bingoBords.length; ii++) {
                for (Integer[] winCondition : winConditions) {
                    int count = 0;
                    for (Integer winConditionIndex : winCondition) {
                        if (Arrays.asList(Arrays.copyOfRange(bingoNumbers, 0, i + 1)).contains(bingoBords[ii][winConditionIndex])) {
                            count++;
                        }
                    }
                    if (count == 5) {
                        ranks.add(new Integer[]{placement, ii, i});
                        placement++;
                    }
                }
            }
        }

        ArrayList<Integer[]> ranksDistinkt = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (Integer[] rank : ranks) {
            if (!values.contains(rank[1])) {
                values.add(rank[1]);
                ranksDistinkt.add(rank);
            }
        }

        Integer[] best = new Integer[]{0, 0, 0};

        for (Integer[] rankDistinkt : ranksDistinkt) {
            if (rankDistinkt[0] > best[0]) {
                best = rankDistinkt;
            }
        }

        Integer[] finalBest = best;
        System.out.println(bingoNumbers[best[2]] * Arrays.stream(bingoBords[best[1]]).filter(x -> !Arrays.asList(Arrays.copyOfRange(bingoNumbers, 0, finalBest[2] + 1)).contains(x)).reduce(0, Integer::sum));
    }
}
