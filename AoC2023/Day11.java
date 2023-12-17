package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/input/Day11.txt"));
        List<List<Character>> img = new ArrayList<>();
        List<List<Character>> imgBig = new ArrayList<>();
        List<Galaxy> galaxies = new ArrayList<>();
        List<Galaxy> galaxiesBig = new ArrayList<>();
        while(file.hasNextLine()) {
            String s = file.nextLine();
            img.add(new String(s).chars().mapToObj(i->(char)i).collect(Collectors.toList()));
            imgBig.add(new String(s).chars().mapToObj(i->(char)i).collect(Collectors.toList()));
            if(!s.contains("#")) {
                List<Character> exp = new ArrayList<>();
                List<Character> expBig = new ArrayList<>();
                for(int i = 0; i < s.length(); i++) {
                    exp.add('.');
                    expBig.add('?');
                }
                img.add(exp);
                imgBig.add(expBig);
            }
        }
        for(int col = 0; col < img.get(0).size(); col++) {
            if(empty(img, col)) {
                for(int row = 0; row < img.size(); row++) {
                    img.get(row).add(col+1, '.');
                    imgBig.get(row).add(col+1, '?');
                }
                col++;
            }
        }
        for(int row = 0; row < img.size(); row++) {
            for(int col = 0; col < img.get(0).size(); col++) {
                if(img.get(row).get(col) == '#') {
                    galaxies.add(new Galaxy(row, col));
                }
            }
        }
        int kr = 0, kc = 0;
        for(int row = 0; row < imgBig.size(); row++) {
            if(Arrays.toString(imgBig.get(row).toArray()).replaceAll("[\\[\\], ]", "").matches("\\?{"+(imgBig.get(0).size())+"}")) {
                kr++;
            } else {
                for(int col = 0; col < imgBig.get(0).size(); col++) {
                    if(imgBig.get(row).get(col) == '?') {
                        kc++;
                    } else if(imgBig.get(row).get(col) == '#') {
                        galaxiesBig.add(new Galaxy(row + kr*999998, col + kc*999998));
                    }                    
                }
                kc = 0;
            }
        }

        long sum = 0, sumBig = 0;
        for(int g1 = 0; g1 < galaxies.size(); g1++) {
            for(int g2 = g1 + 1; g2 < galaxies.size(); g2++) {
                sum += dist(galaxies.get(g1), galaxies.get(g2));
                sumBig += dist(galaxiesBig.get(g1), galaxiesBig.get(g2));
            }
        }

        System.out.printf("Sum of all paths: %s\nSum of all expanded paths: %s\n", sum, sumBig);
    }

    public static boolean empty(List<List<Character>> img, int col) {
        for(List<Character> row : img) {
            if(row.get(col) == '#') { return false; }
        }
        return true;
    }

    private static int dist(Galaxy g1, Galaxy g2) {
        return Math.abs(g1.row - g2.row) + Math.abs(g1.col - g2.col);
    }

    public static class Galaxy {
        int row, col;

        public Galaxy(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Object o) {
            return o instanceof Galaxy && ((Galaxy)o).row == this.row && ((Galaxy)o).col == this.col;
        }

        public String toString() {
            return String.format("Galaxy: (%s,%s)", row, col);
        }
    }
}