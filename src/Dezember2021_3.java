import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Dezember2021_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/inputs/input3.txt"));

        Integer[][] input = reader.lines()
                .map(Pattern.compile("")::splitAsStream)
                .map(x -> x.map(Integer::parseInt))
                .map(x -> x.toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        Aufgabe1(input);
        Aufgabe2(input);
    }

    private static void Aufgabe1 (Integer[][] input_array_int) {
        long gammaRate = 0;
        long epsilonRate = 0;

        int[] commonsValues = new int[input_array_int[0].length];

        for (Integer[] integers : input_array_int) {
            for (int ii = 0; ii < integers.length; ii++) {
                commonsValues[ii] += integers[ii];
            }
        }

        for (int i = 0; i < commonsValues.length; i++) {
            if ((float)commonsValues[i] / (float)input_array_int.length >= 0.5) {
                gammaRate += Math.pow(10, commonsValues.length - 1 - i);
            }
            else {
                epsilonRate += Math.pow(10, commonsValues.length - 1 - i);
            }
        }
        System.out.println(Integer.parseInt(Long.toString(gammaRate), 2) * Integer.parseInt(Long.toString(epsilonRate), 2));
    }

    private static void Aufgabe2 (Integer[][] input_array_int) {
        long genRating = 0;
        long scrubRating = 0;

        Integer[][] genValues = input_array_int;
        Integer[][] scrubValues = input_array_int;

        for (int ii = 0; ii < input_array_int[0].length; ii++) {
            int finalIi = ii;
            if (genValues.length > 1) {
                int finalCommon = getCommon(genValues, ii);
                genValues = Arrays.stream(genValues)
                        .filter(x -> {
                            if (x.length == 1) {
                                return true;
                            }
                            else {
                                return x[finalIi] == finalCommon;
                            }
                        })
                        .toArray(Integer[][]::new);
            }
            if (scrubValues.length > 1) {
                int finalCommon = getCommon(scrubValues, ii);
                scrubValues = Arrays.stream(scrubValues)
                        .filter(x -> {
                            if (x.length == 1) {
                                return true;
                            }
                            else {
                                return x[finalIi] == 1 - finalCommon;
                            }
                        })
                        .toArray(Integer[][]::new);
            }
        }
        for (int i = 0; i < genValues[0].length; i++) {
            genRating += genValues[0][i] * Math.pow(10, genValues[0].length - 1 - i);
        }
        for (int i = 0; i < scrubValues[0].length; i++) {
            scrubRating += scrubValues[0][i] * Math.pow(10, scrubValues[0].length - 1 - i);
        }

        System.out.println(Integer.parseInt(Long.toString(genRating), 2) * Integer.parseInt(Long.toString(scrubRating), 2));
    }
    private static int getCommon(Integer[][] array, int bit) {
        int common = 0;
        for (Integer[] integers : array) {
            common += integers[bit];
        }
        if ((float) common / (float)array.length >= 0.5) {
            common = 1;
        }
        else {
            common = 0;
        }
        return common;
    }
}
