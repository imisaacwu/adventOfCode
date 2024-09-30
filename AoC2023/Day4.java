package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/input/Day4.txt"));
        int points = 0, wins, cards = 0, i = 1;
        Set<String> winning;
        int[] copies = new int[208];
        while(file.hasNextLine()) {
            cards += ++copies[i];
            wins = -1;
            String card = file.nextLine().split(": ")[1];
            winning = new HashSet<>(Arrays.asList(card.split("[|]")[0].split(" ")));
            winning.remove("");
            for(String s : card.split("[|]")[1].split(" ")) {
                if(winning.contains(s)) {
                    wins++;
                }
            }
            points += wins > -1 ? Math.pow(2, wins) : 0;

            // Part 2
            for(int copy = i + 1; copy <= i + wins + 1; copy++) {
                copies[copy] += copies[i];
            }
            i++;
        }
        System.out.printf("Total points: %s\nTotal cards: %s\n", points, cards);
        file.close();
    }
}