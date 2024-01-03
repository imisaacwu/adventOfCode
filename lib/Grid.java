package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    public class dir {
        public static final int N = 0;
        public static final int E = 1;
        public static final int S = 2;
        public static final int W = 3;

        public static int left(int dir) {
            return Math.floorMod(dir - 1, 4);
        }
    
        public static int right(int dir) {
            return (dir + 1) % 4;
        }
    
        public static int opp(int dir) {
            return (dir + 2) % 4;
        }
    }
    public List<List<Character>> grid;
    public int width, height;

    public static List<Grid> split(List<String> in, String s) {
        List<Grid> grids = new ArrayList<>();
        while(in.indexOf(s) > 0) {
            grids.add(new Grid(in.subList(0, in.indexOf(s))));
            in = in.subList(in.indexOf(s)+1, in.size());
        }
        return grids;
    }

    public Grid(List<String> in) {
        grid = new ArrayList<>();
        for(String s : in) {
            grid.add(s.chars().mapToObj(i->(char)i).collect(Collectors.toList()));
        }
        updateSize();
    }

    public char get(int r, int c) { return grid.get(r).get(c); }
    public char get(C coord) { return get(coord.v0, coord.v1); }
    public void set(int r, int c, char x) { grid.get(r).set(c, x); }
    public void set(C coord, char x) { set(coord.v0, coord.v1, x); }

    public boolean valid(int r, int c) { return 0 <= r && r < height && 0 <= c && c < width; }
    public boolean valid(C coord) { return valid(coord.v0, coord.v1); }

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
        updateSize();
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
        updateSize();
    }

    public void transpose() {
        List<List<Character>> g = new ArrayList<>();
        for(int c = 0; c < width; c++) {
            List<Character> toRow = new ArrayList<>();
            for(int r = 0; r < height; r++) {
                toRow.add(get(r, c));
            }
            g.add(toRow);
        }
        grid = g;
        updateSize();
    }

    public List<String> getRows() {
        List<String> out = new ArrayList<>();
        for(List<Character> c : grid) {
            out.add(c.toString().replaceAll("[\\[\\], ]", ""));
        }
        return out;
    }

    public List<Character> getRow(int row) {
        return grid.get(row);
    }

    public List<String> getCols() {
        List<String> out = new ArrayList<>();
        for(int col = 0; col < width; col++) {
            out.add(getCol(col).toString().replaceAll("[\\[\\], ]", ""));
        }
        return out;
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

    private void updateSize() {
        width = grid.get(0).size();
        height = grid.size();
    }
}