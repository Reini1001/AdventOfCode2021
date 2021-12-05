import java.io.*;
import java.util.Arrays;

public class Dezember2021_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input5.txt"));
        Integer[][][] input = reader.lines().map(x -> Arrays.stream(x.split(" -> ")).map(y -> Arrays.stream(y.split(",")).map(Integer::parseInt).toArray(Integer[]::new)).toArray(Integer[][]::new)).toArray(Integer[][][]::new);

        int[][] map = new int[1000][1000];

        for (Integer[][] line : input) {
            int x;
            int y;
            if (line[0][0].equals(line[1][0])) {
                x = line[0][0];
                if (line[0][1] > line[1][1]) {
                    for (y = line[0][1]; y >= line[1][1]; y--) {
                        map[x][y]++;
                    }
                }
                else {
                    for (y = line[0][1]; y <= line[1][1]; y++) {
                        map[x][y]++;
                    }
                }

            }
            else if (line[0][1].equals(line[1][1])) {
                y = line[0][1];
                if (line[0][0] > line[1][0]) {
                    for (x = line[0][0]; x >= line[1][0]; x--) {
                        map[x][y]++;
                    }
                }
                else {
                    for (x = line[0][0]; x <= line[1][0]; x++) {
                        map[x][y]++;
                    }
                }
            }
        }

        Aufgabe1(map);
        Aufgabe2(map, input);
    }

    private static void Aufgabe1 (int[][] map) {
        int counter = 0;
        for (int[] column : map) {
            for (int point : column) {
                if (point >= 2) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    private static void Aufgabe2 (int[][] map, Integer[][][] input) {
        for (Integer[][] line : input) {
            int x;
            int y;
            if (line[0][0] > line[1][0] && line[0][1] > line[1][1]) {
                x = line[0][0];
                y = line[0][1];
                for (int i = 0; i <= line[0][0] - line[1][0]; i++) {
                    map[x - i][y - i]++;
                }
            }
            else if (line[0][0] > line[1][0] && line[0][1] < line[1][1]) {
                x = line[0][0];
                y = line[0][1];
                for (int i = 0; i <= line[0][0] - line[1][0]; i++) {
                    map[x - i][y + i]++;
                }
            }
            else if (line[0][0] < line[1][0] && line[0][1] > line[1][1]) {
                x = line[0][0];
                y = line[0][1];
                for (int i = 0; i <= line[1][0] - line[0][0]; i++) {
                    map[x + i][y - i]++;
                }
            }
            else if (line[0][0] < line[1][0] && line[0][1] < line[1][1]) {
                x = line[0][0];
                y = line[0][1];
                for (int i = 0; i <= line[1][0] - line[0][0]; i++) {
                    map[x + i][y + i]++;
                }
            }
        }

        int counter = 0;
        for (int[] column : map) {
            for (int point : column) {
                if (point >= 2) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
