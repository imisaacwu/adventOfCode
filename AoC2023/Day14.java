package AoC2023;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import lib.Grid;
import lib.Utils;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        Grid g = new Grid(Utils.read("AoC2023/Day14.txt"));
        Map<String, Long> index = new HashMap<>();
        int firstLoad = 0;
        for(long cycle = 0; cycle < 1000000000; cycle++) {
            for(int nwse = 0; nwse < 4; nwse++) {
                rollNorth(g);
                if(cycle == 0 && nwse == 0) { firstLoad = loadNorth(g); }
                g.rotateCW();
            }
            String s = g.toString();
            if(index.containsKey(s)) {
                long delta = cycle - index.get(s);
                cycle += delta * ((1000000000-cycle) / delta);
            }
            index.put(s, cycle);
        }

        System.out.printf("Total load: %s\nTotal load after cycles: %s\n", firstLoad, loadNorth(g));
    }

    public static int loadNorth(Grid g) {
        int load = 0;
        for(int r = 0; r < g.height; r++) {
            for(char c : g.grid.get(r)) {
                if(c == 'O') {
                    load += g.height - r;
                }
            }
        }
        return load;
    }

    public static void rollNorth(Grid g) {
        for(int r = 0; r < g.height; r++) {
            for(int c = 0; c < g.width; c++) {
                if(g.get(r, c) == 'O') {
                    int k = 1;
                    while(r-k >= 0 && g.get(r-k, c) == '.') {
                        k++;
                    }
                    if(k > 1) {
                        g.set(r-(k-1), c, 'O');
                        g.set(r, c, '.');
                    }
                }
            }
        }
    }
}