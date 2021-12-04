import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dezember2021_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input1.txt"));

        Integer[] input_array_int = reader.lines()
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Aufgabe1(input_array_int);
        Aufgabe2(input_array_int);
    }

    private static void Aufgabe1 (Integer[] input_array_int) {
        int zaehler = 0;
        int last = input_array_int[0];

        for (int eintrag : input_array_int) {
            if (eintrag > last) {
                zaehler++;
            }
            last = eintrag;
        }

        System.out.println(zaehler);
    }

    private static void Aufgabe2 (Integer[] input_array_int) {
        ArrayList<Integer> slidingWindow = new ArrayList<>();

        for (int i = 2; i < input_array_int.length; i++) {
            slidingWindow.add(input_array_int[i - 2] + input_array_int[i - 1] + input_array_int[i]);
        }

        int zaehler = 0;
        int last = slidingWindow.get(0);

        for (Integer eintrag : slidingWindow) {
            if (eintrag > last) {
                zaehler++;
            }
            last = eintrag;
        }

        System.out.println(zaehler);
    }
}
