package com.thoughtworks.dinjector.testmodel;

import javax.inject.Inject;

public class Foo {

    private final Bar bar;
    private final Baz baz;

    @Inject
    public Foo(Bar bar, Baz baz) {
        this.bar = bar;
        this.baz = baz;
    }

    public Bar getBar() {
        return bar;
    }

    public Baz getBaz() {
        return baz;
    }

    public static void main(String[] args) {
        assert -1442875930 == "com.thoughtworks.dinjectorBar,com.thoughtworks.dinjector.Baz".hashCode();
    }

    // Class in this style, we're intending to generate with ASM
    // (Read up about ASMifier)
    // The classes will be written to target/classes and be
    // as if they were made in the original compilation
    // stage, but they'll be made by a second source parsing
    // stage, using QDox
    // The ASM version will not need to cast.
    public static class Foo_1442875930 implements Instantiator {
        
        public Object newInstance(Object... args) {
            return new Foo((Bar)args[0], (Baz) args[1]);
        }
        
    }

    /**
     * This interface should move to the main 'dinjector' module, package com.thoughtworks.dinjector
     * when the above inner class is killed in favor of a real
     * generator.
     */
    public interface Instantiator {
        Object newInstance(Object... args);
    }


}
