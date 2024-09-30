package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/input/Day1.txt"));
        int count = 0;
        while(file.hasNextLine()) {
            String s = file.nextLine();

            // Part 2
            s = s.replaceAll("one", "o1e")
                 .replaceAll("two", "t2o")
                 .replaceAll("three", "t3e")
                 .replaceAll("four", "4")
                 .replaceAll("five", "5e")
                 .replaceAll("six", "6")
                 .replaceAll("seven", "7n")
                 .replaceAll("eight", "e8t")
                 .replaceAll("nine", "n9e");

            // Part 1
            s = s.replaceAll("[a-z]", "");

            count += (s.charAt(0) - '0') * 10 + (s.charAt(s.length() - 1) - '0');
        }
        System.out.println(count);
        file.close();
    }
}