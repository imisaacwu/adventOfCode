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
        Grid g = new Grid(Utils.read("AoC2023/Day16.txt"));
        int first = 0, most = -1;
        for(int i = 0; i < g.width; i++) {
            Set<T.P<C, Grid.dir>> lit = new HashSet<>();
            C curr = new C(i, 0);
            Set<C> traveled = new HashSet<>();
            for(T.P<C, Grid.dir> p : laser(g, new HashSet<>(), curr, Grid.dir.E)) { traveled.add(p.v0); }
            if(i == 0) { first = traveled.size(); }
            if(traveled.size() > most) { most = traveled.size(); }

            lit.clear();
            curr = new C(i, g.width - 1);
            traveled.clear();
            for(T.P<C, Grid.dir> p : laser(g, new HashSet<>(), curr, Grid.dir.W)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }
        }
        for(int i = 0; i < g.height; i++) {
            Set<T.P<C, Grid.dir>> lit = new HashSet<>();
            C curr = new C(0, i);
            Set<C> traveled = new HashSet<>();
            for(T.P<C, Grid.dir> p : laser(g, new HashSet<>(), curr, Grid.dir.S)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }

            lit.clear();
            curr = new C(g.height-1, i);
            traveled.clear();
            for(T.P<C, Grid.dir> p : laser(g, new HashSet<>(), curr, Grid.dir.N)) { traveled.add(p.v0); }
            if(traveled.size() > most) { most = traveled.size(); }
        }

        System.out.printf("Tiles lit: %s\nMax tiles lit: %s\n", first, most);
    }

    public static Set<T.P<C, Grid.dir>> laser(Grid g, Set<T.P<C, Grid.dir>> traveled, C curr, Grid.dir dir) {
        while(0 <= curr.v0 && curr.v0 < g.height && 0 <= curr.v1 && curr.v1 < g.width &&
              !traveled.contains(new T.P<>(curr, dir))) {
            T.P<C, Grid.dir> pair = new T.P<>(curr, dir);
            traveled.add(pair);
            switch(g.get(curr)) {
                case '/':
                    switch(dir) {
                        case N:
                            dir = Grid.dir.E;
                            break;
                        case E:
                            dir = Grid.dir.N;
                            break;
                        case S:
                            dir = Grid.dir.W;
                            break;
                        case W:
                            dir = Grid.dir.S;
                            break;
                    }
                    break;
                case '\\':
                    switch(dir) {
                        case N:
                            dir = Grid.dir.W;
                            break;
                        case E:
                            dir = Grid.dir.S;
                            break;
                        case S:
                            dir = Grid.dir.E;
                            break;
                        case W:
                            dir = Grid.dir.N;
                            break;
                    }
                    break;
                case '|':
                    switch(dir) {
                        case E:
                        case W:
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.N), Grid.dir.N));
                            traveled.addAll(laser(g, traveled, curr.rel(Grid.dir.S), Grid.dir.S));
                            return traveled;
                        default:
                    }
                    break;
                case '-':
                    switch(dir) {
                        case N:
                        case S:
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