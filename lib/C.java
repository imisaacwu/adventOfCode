package lib;

// Coordinate
public class C extends T.P<Integer, Integer> {
    public final boolean ARRAY;

    public C(int x, int y, boolean array) {
        super(x, y);
        this.ARRAY = array;
    }
    public C(int x, int y) {
        this(x, y, true);
    }

    public void move(Grid.dir dir) {
        switch(dir) {
            case N:
                if(ARRAY) { v0--; } else { v1++; }
                break;
            case E:
                if(ARRAY) { v1++; } else { v0++; }
                break;
            case S:
                if(ARRAY) { v0++; } else { v1--; }
                break;
            case W:
                if(ARRAY) { v1--; } else { v0--; }
                break;
        }
    }

    public C rel(Grid.dir dir) {
        C c = new C(v0, v1);
        c.move(dir);
        return c;
    }
    public String toString() {
        return String.format(ARRAY ? "[%s][%s]" : "(%s, %s)", v0, v1);
    }

    public int hashCode() {
        return 17*v0 + 31*v1;
    }

    public boolean equals(Object other) {
        return other instanceof C && ((C)other).v0 == this.v0 && ((C)other).v1 == this.v1;
    }
}