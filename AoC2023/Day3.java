package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/input/Day3.txt"));
        List<char[]> schematic = new ArrayList<>();
        while(file.hasNextLine()) {
            schematic.add(file.nextLine().toCharArray());
        }
        
        int count = 0, ratios = 0;
        for(int row = 0; row < schematic.size(); row++) {
            for(int col = 0; col < schematic.get(0).length; col++) {
                // Part 1
                if((""+schematic.get(row)[col]).matches("[-@#$%&*+=/]")) {
                    count += sumParts(schematic, row, col);
                }

                // Part 2
                if(schematic.get(row)[col] == '*') {
                    ratios += gear(schematic, row, col);
                }
            }
        }

        System.out.printf("Sum of part #s: %s\nSum of gear ratios: %s", count, ratios);
    }

    public static int sumParts(List<char[]> schematic, int row, int col) {
        int sum = 0;
        for(int i = Math.max(0, row - 1); i <= Math.min(row + 1, schematic.size() - 1); i++) {
            for(int j = Math.max(0, col - 1); j <= Math.min(col + 1, schematic.get(0).length - 1); j++) {
                if('0' <= schematic.get(i)[j] && schematic.get(i)[j] <= '9') {
                    // Found one, expand
                    int k = 1;
                    String num = "" + schematic.get(i)[j];
                    
                    // left
                    while(0 <= j - k && '0' <= schematic.get(i)[j - k] && schematic.get(i)[j - k] <= '9') {
                        num = schematic.get(i)[j - k++] + num;
                    }

                    // right (offset to avoid checking the same number)
                    k = 1;
                    int offset = 0;
                    while(j + k < schematic.get(0).length && '0' <= schematic.get(i)[j + k] && schematic.get(i)[j + k] <= '9') {
                        num += schematic.get(i)[j + k++];
                        offset++;
                    }
                    j += offset;
                    sum += Integer.parseInt(num);
                }
            }
        }
        return sum;
    }

    public static int gear(List<char[]> list, int row, int col) {
        int ratio = 1, gears = 0;
        for(int i = Math.max(0, row - 1); i <= Math.min(row + 1, list.size() - 1); i++) {
            for(int j = Math.max(0, col - 1); j <= Math.min(col + 1, list.get(0).length - 1); j++) {
                if('0' <= list.get(i)[j] && list.get(i)[j] <= '9') {
                    // Found one, expand
                    gears++;
                    int k = 1;
                    String num = "" + list.get(i)[j];

                    // left
                    while(0 <= j - k && '0' <= list.get(i)[j - k] && list.get(i)[j - k] <= '9') {
                        num = list.get(i)[j - k++] + num;
                    }

                    // right (offset to avoid checking the same number)
                    k = 1;
                    int offset = 0;
                    while(j + k < list.get(0).length && '0' <= list.get(i)[j + k] && list.get(i)[j + k] <= '9') {
                        num += list.get(i)[j + k++];
                        offset++;
                    }
                    j += offset;
                    ratio *= Integer.parseInt(num);

                }
            }
        }
        return gears == 2 ? ratio : 0;
    }
}