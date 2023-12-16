package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Grid {
    public List<List<Character>> grid;
    public int width, height;

    public Grid(Scanner in) {
        grid = new ArrayList<>();
        while(in.hasNextLine()) {
            grid.add(in.nextLine().chars().mapToObj(i->(char)i).collect(Collectors.toList()));
        }
        width = grid.get(0).size();
        height = grid.size();
    }

    public Grid(List<List<Character>> grid) {
        this.grid = grid;
        width = grid.get(0).size();
        height = grid.size();
    }

    public char get(int r, int c) { return grid.get(r).get(c); }
    public void set(int r, int c, char x) { grid.get(r).set(c, x); }

    public void rotateCW() {
        List<List<Character>> g = new ArrayList<>();
        for(int c = 0; c < width; c++) {
            List<Character> col = new ArrayList<>();
            for(int r = height-1; r >= 0; r--) {
                col.add(get(r, c));
            }
            g.add(col);
        }
        grid = g;
    }

    public void rotateCCW() {
        List<List<Character>> g = new ArrayList<>();
        for(int c = width-1; c >= 0; c--) {
            List<Character> col = new ArrayList<>();
            for(int r = 0; r < height; r++) {
                col.add(get(r, c));
            }
            g.add(col);
        }
        grid = g;
    }

    public void transpose() {

    }

    public List<Character> getRow(int row) {
        return grid.get(row);
    }

    public List<Character> getCol(int col) {
        List<Character> c = new ArrayList<>();
        for(List<Character> row : grid) { c.add(row.get(col)); }
        return c;
    }

    public String toString() {
        String s = "";
        for(List<Character> l : grid) {
            s += l.toString().replaceAll("[\\[\\],]", "") + "\n";
        }
        return s;
    }

    public int hashCode() {
        return grid.hashCode();
    }

    public boolean equals(Object other) {
        return other instanceof Grid && Arrays.equals(((Grid)other).grid.toArray(), this.grid.toArray());
    }
}