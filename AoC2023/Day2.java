package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/Day2.txt"));
        int game = 1, count = 0;
        int[][] min = new int[101][3];
        while(file.hasNextLine()) {
            String[] sets = file.nextLine().split(": ")[1].split("; ");
            for(String set : sets) {
                String[] cubes = set.split(", ");
                for(String cube : cubes) {
                    String[] data = cube.split(" ");
                    int c = data[1].equals("red") ? 0 : data[1].equals("green") ? 1 : 2;
                    min[game][c] = Math.max(min[game][c], Integer.parseInt(data[0]));
                }
            }
            game++;
        }

        for(int i = 1; i < min.length; i++) {
            // Part 1
            if(min[i][0] <= 12 && min[i][1] <= 13 && min[i][2] <= 14) {
                count += i;
            }

            // Part 2
            count += min[i][0] * min[i][1] * min[i][2];
        }

        System.out.println(count);
    }
}