package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day12 {
    private static final Map<Input, Long> memo = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException, OutOfMemoryError {
        Scanner file = new Scanner(new File("AoC2023/input/Day12.txt"));
        long perms = 0, unfoldedPerms = 0;
        while(file.hasNextLine()) {
            String[] in = file.nextLine().split(" ");
            List<Integer> groups = Arrays.asList(in[1].split(",")).stream().map(i -> Integer.valueOf(i)).toList();
            perms += combos(in[0], groups);
            unfoldedPerms += combos(unfoldSprings(in[0], 5), unfoldGroups(groups, 5)); 
        }
        System.out.printf("Possible arrangements: %s\nPossible unfolded arrangements: %s\n", perms, unfoldedPerms);
    }

    public static long combos(String s, List<Integer> groups) {
        Input input = new Input(s, groups);
        if(memo.containsKey(input)) { return memo.get(input); }
        if(s.isBlank()) {
            return groups.isEmpty() ? 1 : 0;
        }
        char front = s.charAt(0);
        long combos = 0;
        if(front == '.') {
            combos = combos(s.substring(1), groups);
        } else if(front == '?') {
            combos = combos("."+s.substring(1), groups) + combos("#"+s.substring(1), groups);
        } else {
            if(groups.isEmpty()) {
                combos = 0;
            } else {
                int damaged = groups.get(0);
                if(damaged <= s.length() && s.chars().limit(damaged).allMatch(c -> c == '#' || c == '?')) {
                    List<Integer> group = groups.subList(1, groups.size());
                    if(damaged == s.length()) {
                        combos = group.isEmpty() ? 1 : 0;
                    } else if(s.charAt(damaged) == '.') {
                        combos = combos(s.substring(damaged+1), group);
                    } else if(s.charAt(damaged) == '?') {
                        combos = combos("." + s.substring(damaged+1), group);
                    } else {
                        combos = 0;
                    }
                } else {
                    combos = 0;
                }
            }
        }
        memo.put(input, combos);
        return combos;
    }

    private static String unfoldSprings(String s, int n) {
        String out = "";
        for(int i = 0; i < n - 1; i++) { out += s + "?"; }
        return out + s;
    }

    private static List<Integer> unfoldGroups(List<Integer> groups, int n) {
        List<Integer> unfolded = new ArrayList<>();
        for(int i = 0; i < n; i++) { unfolded.addAll(groups); }
        return unfolded;
    }

    private static class Input {
        String spring;
        List<Integer> groups;

        public Input(String spring, List<Integer> groups) {
            this.spring = spring;
            this.groups = groups;
        }

        public boolean equals(Object other) {
            return other instanceof Input && ((Input)other).spring.equals(this.spring) && ((Input)other).groups.equals(this.groups);
        }

        public int hashCode() {
            return 17*spring.hashCode() + 31*groups.hashCode();
        }

        public String toString() {
            return spring + "\n";
        }
    }
}