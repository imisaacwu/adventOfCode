package lib;

import java.util.Objects;

// Coordinate
public class C extends T._2<Integer, Integer> {
    public final boolean ARRAY;

    public C(int v0, int v1, boolean array) {
        super(v0, v1);
        this.ARRAY = array;
    }
    public C(int v0, int v1) {
        this(v0, v1, true);
    }

    public void move(int dir) {
        switch(dir) {
            case Grid.dir.N:
                if(ARRAY) { v0--; } else { v1++; }
                break;
            case Grid.dir.E:
                if(ARRAY) { v1++; } else { v0++; }
                break;
            case Grid.dir.S:
                if(ARRAY) { v0++; } else { v1--; }
                break;
            case Grid.dir.W:
                if(ARRAY) { v1--; } else { v0--; }
                break;
        }
    }

    public void move(C delta) {
        if(this.ARRAY != delta.ARRAY) {
            throw new IllegalArgumentException("Coordinates are different types");
        }
        v0 += delta.v0;
        v1 += delta.v1;
    }

    public C rel(int dir, int n) {
        C c = new C(v0, v1, ARRAY);
        for(int i = 0; i < n; i++) {
            c.move(dir);
        }
        return c;
    }
    public C rel(int dir) {
        return rel(dir, 1);
    }
    public C rel(C delta) {
        return new C(v0+delta.v0,v1+delta.v1,ARRAY);
    }

    public void transpose() {
        int temp = v0;
        v0 = v1;
        v1 = temp;
    }

    @Override
    public String toString() {
        return String.format(ARRAY ? "[%s][%s]" : "(%s, %s)", v0, v1);
    }

    @Override
    public int hashCode() {
        return 17*v0 + 31*v1;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof C && Objects.equals(((C) other).v0, this.v0) && Objects.equals(((C) other).v1, this.v1);
    }
}