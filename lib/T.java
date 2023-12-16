package lib;

// Tuple
public abstract class T {
    public Object[] arr;

    public T(Object... values) {
        this.arr = values;
    }

    // Pair
    public static class P<T0, T1> extends T {
        public T0 v0;
        public T1 v1;

        public P(T0 v0, T1 v1) {
            super(v0, v1);
            this.v0 = v0;
            this.v1 = v1;
        }
    }
}