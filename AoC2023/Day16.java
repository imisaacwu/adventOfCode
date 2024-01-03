package AoC2023;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import lib.C;
import lib.Grid;
import lib.T;
import lib.Utils;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        Grid g = new Grid(Utils.read("AoC2023/input/Day16.txt"));
        int first = 0, most = -1;
        for(int i = 0; i < g.width; i++) {
            Set<T._2<C, Integer>> lit = new HashSet<>();
            C curr = new C(i, 0);
            Set<C> traveled = new HashSet<>();
            for(T._2<C, Integer> p : laser(g, new HashSet<>(), curr, Grid.dir.E)) { traveled.add(p.v0); }
            if(i == 0) { first = traveled.size(); }
            if(traveled.size() > most) { most = traveled.size(); }

            lit.clear();
            curr = new C(i, g.width - 1);
            traveled.clear();
            for(T._2<C, Integer> p : laser(g, new HashSet<>(), curr, Grid.dir.W)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }
        }
        for(int i = 0; i < g.height; i++) {
            Set<T._2<C, Grid.dir>> lit = new HashSet<>();
            C curr = new C(0, i);
            Set<C> traveled = new HashSet<>();
            for(T._2<C, Integer> p : laser(g, new HashSet<>(), curr, Grid.dir.S)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }

            lit.clear();
            curr = new C(g.height-1, i);
            traveled.clear();
            for(T._2<C, Integer> p : laser(g, new HashSet<>(), curr, Grid.dir.N)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }
        }

        System.out.printf("Tiles lit: %s\nMax tiles lit: %s\n", first, most);
    }

    public static Set<T._2<C, Integer>> laser(Grid g, Set<T._2<C, Integer>> traveled, C curr, int dir) {
        while(0 <= curr.v0 && curr.v0 < g.height && 0 <= curr.v1 && curr.v1 < g.width &&
              !traveled.contains(new T._2<>(curr, dir))) {
            T._2<C, Integer> pair = new T._2<>(curr, dir);
            traveled.add(pair);
            switch(g.get(curr)) {
                case '/':
                    switch(dir) {
                        case Grid.dir.N, Grid.dir.S:
                            dir = Grid.dir.right(dir);
                            break;
                        case Grid.dir.E, Grid.dir.W:
                            dir = Grid.dir.left(dir);
                            break;
                    }
                    break;
                case '\\':
                    switch(dir) {
                        case Grid.dir.N, Grid.dir.S:
                            dir = Grid.dir.left(dir);
                            break;
                        case Grid.dir.E, Grid.dir.W:
                            dir = Grid.dir.right(dir);
                            break;
                    }
                    break;
                case '|':
                    switch(dir) {
                        case Grid.dir.E, Grid.dir.W:
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.N), Grid.dir.N));
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.S), Grid.dir.S));
                            return traveled;
                        default:
                    }
                    break;
                case '-':
                    switch(dir) {
                        case Grid.dir.N, Grid.dir.S:
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.E), Grid.dir.E));
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.W), Grid.dir.W));
                            return traveled;
                        default:
                    }
                    break;
            }
            curr = curr.rel(dir);
        }
        return traveled;
    }
}