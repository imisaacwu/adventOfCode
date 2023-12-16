package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lib.LL;
import lib.T;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/Day15.txt"));
        String s = file.nextLine();
        int sum = 0, power = 0;
        List<LL<T.P<String, Integer>>> boxes = new ArrayList<>();
        while(boxes.size() < 256) { boxes.add(new LL<>(null)); }
        for(String i : s.split(",")) {
            int hash = hash(i.split("-|=")[0]);
            LL<T.P<String, Integer>> box = boxes.get(hash);
            LL<T.P<String, Integer>>.Node curr = box.head;
            if(curr != null && curr.data.v0.equals(i.split("-|=")[0])) {
                if(i.contains("-")) {
                    box.head = box.head.next;
                } else {
                    box.head.data.v1 = Integer.parseInt(i.split("=")[1]);
                }
            } else {
                boolean done = false;
                while(curr != null && curr.next != null) {
                    if(curr.next.data.v0.equals(i.split("-|=")[0])) {
                        if(i.contains("-")) {
                            curr.next = curr.next.next;
                        } else {
                            curr.next.data.v1 = Integer.parseInt(i.split("=")[1]);
                        }
                        done = true;
                        break;
                    }
                    curr = curr.next;
                }
                if(!done && i.contains("=")) {
                    box.add(new T.P<String, Integer>(i.split("=")[0], Integer.parseInt(i.split("=")[1])));
                }
            }
            sum += hash(i);
        }
        for(int i = 0; i < boxes.size(); i++) {
            if(boxes.get(i).head != null) {
                LL<T.P<String, Integer>>.Node curr = boxes.get(i).head;
                int slot = 1;
                while(curr != null) {
                    power += (i+1) * slot++ * curr.data.v1;
                    curr = curr.next;
                }
            }
        }

        System.out.printf("Part 1: %s\nPart 2: %s\n", sum, power);
    }

    public static int hash(String s) {
        int n = 0;
        for(char c : s.toCharArray()) {
            n += c;
            n *= 17;
            n %= 256;
        }
        return n;
    }
}