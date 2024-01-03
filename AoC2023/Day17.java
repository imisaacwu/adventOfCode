package AoC2023;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lib.C;
import lib.Grid;
import lib.Utils;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = Utils.read("AoC2023/input/Day17.txt");
        Grid g = new Grid(input);
        
        System.out.printf("Least heat loss: %s\nLeat heat loss (for ultra crucible): %s\n", findPath(g, true), findPath(g, false));
    }

    public static int findPath(Grid g, boolean p1) {
        Comparator<Path> pathComp = new Comparator<>() {
            public int compare(Path a, Path b) {
                if(a.heat != b.heat) {
                    return a.heat - b.heat;
                } else if(a.node.dir == b.node.dir && a.node.chain != b.node.chain) {
                    return a.node.chain - b.node.chain;
                } else if(a.node.r != b.node.r) {
                    return a.node.r - b.node.r;
                }
                return a.node.c - b.node.c;
            }
        };
        PriorityQueue<Path> paths = new PriorityQueue<>(pathComp);
        Set<Node> searched = new HashSet<>();
        C end = new C(g.height-1, g.width-1);
        paths.add(new Path(new Node(1,0,Grid.dir.S,1),g.get(1,0)-'0'));
        paths.add(new Path(new Node(0,1,Grid.dir.E,1),g.get(0,1)-'0'));
        while(!paths.isEmpty()) {
            final Path curr = paths.poll();
            if(searched.contains(curr.node)) { continue; }
            searched.add(curr.node);
            if(curr.node.r == end.v1 && curr.node.c == end.v1 && (p1 || curr.node.chain >= 4)) {
                return curr.heat;
            }
            paths.addAll(getNeighbors(curr, g, p1));
        }
        return 0;
    }

    public static List<Path> getNeighbors(Path curr, Grid g, boolean p1) {
        List<Path> out = new ArrayList<>();
        if(p1 || curr.node.chain >= 4) {
            Node left = next(curr, Math.floorMod(curr.node.dir - 1, 4), 1);
            if(g.valid(left.r, left.c)) {
                out.add(new Path(left, curr.heat + g.get(left.r, left.c)-'0'));
            }
            Node right = next(curr, (curr.node.dir + 1) % 4, 1);
            if(g.valid(right.r, right.c)) {
                out.add(new Path(right, curr.heat + g.get(right.r, right.c)-'0'));
            }
        }
        if((p1 && curr.node.chain < 3) || (!p1 && curr.node.chain < 10)) {
            Node straight = next(curr, curr.node.dir, curr.node.chain + 1);
            if(g.valid(straight.r, straight.c)) {
                out.add(new Path(straight, curr.heat + g.get(straight.r, straight.c)-'0'));
            }
        }
        return out;
    }

    private static Node next(Path curr, int dir, int chain) {
        return new Node(curr.node.r + (dir == Grid.dir.N ? 1 : dir == Grid.dir.S ? -1 : 0),
                        curr.node.c + (dir == Grid.dir.E ? 1 : dir == Grid.dir.W ? -1 : 0), dir, chain);
    }

    public static record Path(Node node, int heat) {}
    public static record Node(int r, int c, int dir, int chain) {}
}