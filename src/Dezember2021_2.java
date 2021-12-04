import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dezember2021_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input2.txt"));

        String[][] input = reader.lines()
                .map(x -> x.split(" "))
                .toArray(String[][]::new);

        Aufgabe1(input);
        Aufgabe2(input);
    }

    private static void Aufgabe1 (String[][] input) {
        int depth = 0;
        int position = 0;

        for (String[] strings : input) {
            switch (strings[0]) {
                case ("up") -> depth -= Integer.parseInt(strings[1]);
                case ("down") -> depth += Integer.parseInt(strings[1]);
                case ("forward") -> position += Integer.parseInt(strings[1]);
                default -> System.out.println("Error: Default " + strings[0]);
            }
        }

        System.out.println(depth * position);
    }

    private static void Aufgabe2 (String[][] input) {
        int depth = 0;
        int position = 0;
        int aim = 0;

        for (String[] strings : input) {
            switch (strings[0]) {
                case ("up") -> aim -= Integer.parseInt(strings[1]);
                case ("down") -> aim += Integer.parseInt(strings[1]);
                case ("forward") -> {
                    position += Integer.parseInt(strings[1]);
                    depth += (Integer.parseInt(strings[1]) * aim);
                }
                default -> System.out.println("Error: Default " + Integer.parseInt(strings[0]));
            }
        }

        System.out.println(depth * position);
    }
}
