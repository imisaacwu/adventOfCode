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

        public String toString() {
            return "("+v0+","+v1+")";
        }

        @Override
        public int hashCode() {
            return v0.hashCode()*17 + v1.hashCode()*31;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if(other == this) { return true; }
            if(other == null || other.getClass() != this.getClass()) { return false; }
            P<?, ?> o = (P<?, ?>) other;
            if(o.v0.getClass() != this.v0.getClass() || o.v1.getClass() != this.v1.getClass()) { return false; }
            P<T0, T1> obj = (P<T0, T1>) o;
            return obj.v0.equals(this.v0) && obj.v1.equals(this.v1);
        }
    }
}