package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/input/Day6.txt"));
        // Part 1
        int[][] dist = new int[4][98];
        String[] times, records;
        int wins, total = 1;
        times = file.nextLine().split(":")[1].replaceAll("\\s{1,}", "-").split("-");
        records = file.nextLine().split(":")[1].replaceAll("\\s{1,}", "-").split("-");
        for(int race = 1; race < times.length; race++) {
            wins = 0;
            for(int hold = 1; hold < Integer.parseInt(times[race]) - 1; hold++) {
                dist[race-1][hold] = hold * (Integer.parseInt(times[race]) - hold);
                if(hold * (Integer.parseInt(times[race]) - hold) > Integer.parseInt(records[race])) {
                    wins++;
                }
            }
            total *= wins;
        }

        // Part 2
        // long time = Long.parseLong(file.nextLine().split(":")[1].replaceAll(" ", ""));
        // long record = Long.parseLong(file.nextLine().split(":")[1].replaceAll(" ", ""));
        long time = Long.parseLong(Arrays.toString(times).replaceAll("[\\[\\], ]", ""));
        long record = Long.parseLong(Arrays.toString(records).replaceAll("[\\[\\], ]", ""));
        wins = 0;
        for(int hold = 1; hold < time - 1; hold++) {
            if(hold * (time - hold) > record) {
                wins++;
            }
        }

        System.out.printf("Total ways to win: %s\nTotal ways to win (long race): %s\n", total, wins);
    }
}