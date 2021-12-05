import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dezember2021_6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input6.txt"));
        Integer[] input = Arrays.stream(reader.readLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        Aufgabe1(input);
        Aufgabe2(input);
    }

    private static void Aufgabe1(Integer[] input) {
        System.out.println(fishByDay(input, 80));
    }

    private static void Aufgabe2(Integer[] input) {
        System.out.println(fishByDay(input, 256));
    }

    private static long fishByDay (Integer[] input, int days) {
        long d0 = 0;
        long d1 = 0;
        long d2 = 0;
        long d3 = 0;
        long d4 = 0;
        long d5 = 0;
        long d6 = 0;
        long d7 = 0;
        long d8 = 0;

        for (Integer integer : input) {
            switch (integer) {
                case 0 -> d0++;
                case 1 -> d1++;
                case 2 -> d2++;
                case 3 -> d3++;
                case 4 -> d4++;
                case 5 -> d5++;
                case 6 -> d6++;
                case 7 -> d7++;
                case 8 -> d8++;
                default -> throw new IllegalStateException("Unexpected value: " + integer);
            }
        }

        for (int day = 0; day < days; day++) {
            long temp = d0;
            d0 = d1;
            d1 = d2;
            d2 = d3;
            d3 = d4;
            d4 = d5;
            d5 = d6;
            d6 = d7 + temp;
            d7 = d8;
            d8 = temp;
        }

        return (d0 + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8);
    }
}
