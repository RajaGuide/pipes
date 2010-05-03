package com.tinkerpop.pipes.serial.util;

import com.tinkerpop.pipes.serial.AbstractPipe;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ProductPipe<S, T> extends AbstractPipe<S, ProductPipe.Pair> {

    private final T toProduct;
    private final Join join;

    public enum Join {
        RIGHT, LEFT
    }

    public ProductPipe(T toProduct, Join join) {
        this.toProduct = toProduct;
        this.join = join;
    }

    protected ProductPipe.Pair processNextStart() {
        S s = this.starts.next();
        if (this.join.equals(Join.RIGHT)) {
            return new Pair<S, T>(s, this.toProduct);
        } else {
            return new Pair<T, S>(this.toProduct, s);
        }
    }

    public class Pair<A, B> {
        private final A a;
        private final B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return this.a;
        }

        public B getB() {
            return this.b;
        }

        public String toString() {
            return "[" + a + "," + b + "]";
        }
    }
}
