package AOC2022;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import lib.FileIO;

public class Day1 {
    public static void main(String[] args) {
        List<String> input = FileIO.read("AOC2022/input/Day1.txt");
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int curr = 0;
        for(String s : input) {
            if(s.equals("")) {
                pq.offer(curr);
                curr = 0;
            } else {
                curr += Integer.parseInt(s);
            }
        }

        System.out.printf("Part 1: %d\n", pq.peek());
        System.out.printf("Part 2: %d\n", pq.poll() + pq.poll() + pq.poll());
    }
}