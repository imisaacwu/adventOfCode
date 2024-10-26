package lib;

// Tuple
public abstract class T {
    public Object[] arr;

    public T(Object... values) {
        this.arr = values;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("(");
        for(Object o : arr) {
            out.append(o.toString()).append(",");
        }
        return out.substring(0, out.length()-1) + ")";
    }

    // Unit
    public static class _1<T0> extends T {
        public T0 v0;

        public _1(T0 v0) {
            super(v0);
            this.v0 = v0;
        }

        @Override
        public int hashCode() {
            return v0.hashCode();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if(other == this) { return true; }
            if(other == null || other.getClass() != this.getClass()) { return false; }
            _1<?> o = (_1<?>) other;
            if(o.v0.getClass() != this.v0.getClass()) { return false; }
            _1<T0> obj = (_1<T0>) o;
            return obj.v0.equals(this.v0);
        }
    }

    // Pair
    public static class _2<T0, T1> extends T {
        public T0 v0;
        public T1 v1;

        public _2(T0 v0, T1 v1) {
            super(v0, v1);
            this.v0 = v0;
            this.v1 = v1;
        }

        @Override
        public int hashCode() {
            return v0.hashCode()*17 + v1.hashCode()*31;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof _2<?,?>) {
                _2<?,?> o = (_2<?,?>) other;
                return new _1<>(this.v0).equals(new _1<>(o.v0)) &&
                        new _1<>(this.v1).equals(new _1<>(o.v1));
            }
            return false;
        }
    }

    // Triplet
    public static class _3<T0, T1, T2> extends T {
        public T0 v0;
        public T1 v1;
        public T2 v2;

        public _3(T0 v0, T1 v1, T2 v2) {
            super(v0, v1, v2);
            this.v0 = v0;
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public int hashCode() {
            return (new _1<>(v0).hashCode())*17 + (new _2<>(v1,v2).hashCode())*31;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof _3<?,?,?>) {
                _3<?,?,?> o = (_3<?,?,?>) other;
                return new _1<>(this.v0).equals(new _1<>(o.v0)) &&
                        new _2<>(this.v1,this.v2).equals(new _2<>(o.v1,o.v2));
            }
            return false;
        }
    }

    // Quartet
    public static class _4<T0, T1, T2, T3> extends T {
        public T0 v0;
        public T1 v1;
        public T2 v2;
        public T3 v3;

        public _4(T0 v0, T1 v1, T2 v2, T3 v3) {
            super(v0, v1, v2, v3);
            this.v0 = v0;
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }

        @Override
        public int hashCode() {
            return (new _2<>(v0,v1).hashCode())*17 + (new _2<>(v2,v3).hashCode())*31;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof _4<?,?,?,?>) {
                _4<?,?,?,?> o = (_4<?,?,?,?>) other;
                return new _2<>(this.v0,this.v1).equals(new _2<>(o.v0,o.v1)) &&
                        new _2<>(this.v2,this.v3).equals(new _2<>(o.v2,o.v3));
            }
            return false;
        }
    }
}