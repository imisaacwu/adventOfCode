package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/Day9.txt"));
        int sum = 0, backSum = 0, k;
        while(file.hasNextLine()) {
            List<List<Integer>> seq = new ArrayList<>();
            seq.add(Stream.of(file.nextLine().split(" ")).mapToInt((e) -> { return Integer.parseInt(e); }).boxed().toList());
            k = 0;
            do {
                k++;
                List<Integer> next = new ArrayList<>();
                for(int i = 0; i < seq.get(k-1).size() - 1; i++) {
                    next.add(seq.get(k-1).get(i+1) - seq.get(k-1).get(i));
                }
                seq.add(next);
            } while(!Stream.of(seq.get(k).toArray(new Integer[]{})).allMatch(i -> i == 0));

            for(int i = k - 1; i > 0; i--) {
                seq.get(i).add(seq.get(i).get(seq.get(i).size()-1) + seq.get(i+1).get(seq.get(i+1).size()-1));
                seq.get(i).add(0, seq.get(i).get(0) - seq.get(i+1).get(0));
            }
            sum += seq.get(0).get(seq.get(0).size()-1) + seq.get(1).get(seq.get(1).size()-1);
            backSum += seq.get(0).get(0) - seq.get(1).get(0);
        }
        
        System.out.printf("Sum (front): %s\nSum (back): %s\n", sum, backSum);
    }
}