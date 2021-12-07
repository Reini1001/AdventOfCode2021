import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Dezember2021_7 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input7.txt"));
        Integer[] input = Arrays.stream(reader.readLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        Aufgabe1(input);
        Aufgabe2(input);
    }

    private static void Aufgabe1 (Integer[] input) {
        int[] fuelNeeded = new int[Arrays.stream(input).reduce(0, Integer::max) + 1];
        for (int i = 0; i < fuelNeeded.length; i++) {
            for (Integer in : input) {
                fuelNeeded[i] += Math.abs(i - in);
            }
        }
        int bestValue = fuelNeeded[0];
        for (int fuelNeed : fuelNeeded) {
            if (fuelNeed < bestValue) {
                bestValue = fuelNeed;
            }
        }
        System.out.println(bestValue);
    }

    private static void Aufgabe2 (Integer[] input) {
        int[] fuelNeeded = new int[Arrays.stream(input).reduce(0, Integer::max) + 1];
        for (int i = 0; i < fuelNeeded.length; i++) {
            for (Integer in : input) {
                for (int f = 1; f <= Math.abs(i - in); f++) {
                    fuelNeeded[i] += f;
                }
            }
        }
        int bestValue = fuelNeeded[0];
        for (int fuelNeed : fuelNeeded) {
            if (fuelNeed < bestValue) {
                bestValue = fuelNeed;
            }
        }
        System.out.println(bestValue);
    }
}
