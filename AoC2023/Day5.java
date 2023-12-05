package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/Day5.txt"));
        String[] seeds = file.nextLine().split(": ")[1].split(" ");
        ArrayList<Transform> changes = new ArrayList<>();
        while(file.hasNextLine()) {
            if(file.nextLine().endsWith(":")) {
                Transform t = new Transform();
                String s = file.nextLine();
                while(!s.isBlank() && file.hasNextLine()) {
                    String[] strs = s.split(" ");
                    long[] data = new long[3];
                    for(int i = 0; i < strs.length; i++) {
                        data[i] = Long.parseLong(strs[i]);
                    }
                    t.changes.add(new Range(data[1], data[1] + data[2], data[0] - data[1]));
                    s = file.nextLine();
                }
                changes.add(t);
            }
        }

        long lowest = Long.MAX_VALUE, lowestRange = Long.MAX_VALUE;

        // Part 1
        for(String s : seeds) {
            long loc = Long.parseLong(s);
            for(int t = 0; t < changes.size(); t++) {
                loc = changes.get(t).transform(loc);
            }
            lowest = Math.min(loc, lowest);
        }

        // Part 2
        for(int pair = 0; pair < seeds.length; pair += 2) {
            long start = Long.parseLong(seeds[pair]), range = Long.parseLong(seeds[pair + 1]);
            for(long seed = start; seed < start + range; seed++) {
                long loc = seed;
                for(int t = 0; t < changes.size(); t++) {
                    loc = changes.get(t).transform(loc);
                }
                lowestRange = Math.min(loc, lowestRange);
            }
        }

        System.out.printf("Lowest location from seeds: %s\nLowest location from seed ranges: %s\n", lowest, lowestRange);
    }

    private static class Transform {
        TreeSet<Range> changes;

        public Transform() {
            changes = new TreeSet<>();
        }

        public long transform(long l) {
            Iterator<Range> iter = changes.iterator();
            while(iter.hasNext()) {
                Range r = (Range)iter.next();
                if(r.has(l)) { return l + r.mod; }
            }
            return l;
        }
    }

    private static class Range implements Comparable<Range> {
        long start, end, mod;

        public Range(long start, long end, long mod) {
            this.start = start;
            this.end = end;
            this.mod = mod;
        }
        
        public boolean has(long l) { return start <= l && l < end; }
        
        public int compareTo(Range other) {
            return this.start - other.start < 0l ? -1 : 1;
        }
    }
}