/***
 *
 * Copyright (c) 2012 ThoughtWorks
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.thoughtworks.dinjector;

import com.thoughtworks.dinjector.testmodel.Bar;
import com.thoughtworks.dinjector.testmodel.Baz;
import com.thoughtworks.dinjector.testmodel.Foo;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class ProofOfConceptTest {

    @Test
    public void proof_of_concept() {

        Bar bar = new Bar();
        Baz baz = new Baz();

        Foo.Foo_1442875930 reentrantHelper = new Foo.Foo_1442875930();

        Foo foo = (Foo) reentrantHelper.newInstance(bar, baz);

        assertThat(foo.getBar(), sameInstance(bar));
        assertThat(foo.getBaz(), sameInstance(baz));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
           foo = (Foo) reentrantHelper.newInstance(bar, baz);            
        }
        System.out.println("POC duration for 100,000: " + (System.currentTimeMillis() - start) + "ms");
        
    }

    @Test
    public void without_di_simulation() {

        Bar bar = new Bar();
        Baz baz = new Baz();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
           Foo foo =  new Foo(bar, baz);
        }
        System.out.println("Non-Injection duration for 100,000: " + (System.currentTimeMillis() - start) + "ms");
        
    }

    @Test
    public void what_di_containers_do_behind_the_scenes() throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Bar bar = new Bar();
        Baz baz = new Baz();

        Constructor<?> reentrantHelper = Foo.class.getConstructors()[0];
        
        Foo foo = (Foo) reentrantHelper.newInstance(bar, baz);

        assertThat(foo.getBar(), sameInstance(bar));
        assertThat(foo.getBaz(), sameInstance(baz));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            foo = (Foo) reentrantHelper.newInstance(bar, baz);
        }
        System.out.println("Reflection duration for 100,000: " + (System.currentTimeMillis() - start) + "ms");

    }


}