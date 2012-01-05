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
package com.thoughtworks.dinjector.generator;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Qdox-based implementation of DinjectorGenerator which parses Java source files to processSourcePath
 * parameter names lists.
 * 
 * @author Paul Hammant
 * @author Mauro Talevi
 * @author Guilherme Silveira
 */
public class QdoxDinjectorGenerator implements DinjectorGenerator {

    private static final String SPACE  = " ";
    private static final String NEWLINE = "\n";
    private static final String COMMA = ",";
    private static final String EMPTY = "";

    public QdoxDinjectorGenerator() {
    }

    public void processSourcePath(String sourcePath, String outputPath) throws IOException {
        JavaClass[] classes = getClassesSortedByName(sourcePath);
        processClasses(classes, outputPath);
    }

    private JavaClass[] getClassesSortedByName(String sourcePath) {
        JavaDocBuilder builder = new JavaDocBuilder();
        builder.addSourceTree(new File(sourcePath));
        JavaClass[] classes = builder.getClasses();
        Arrays.sort(classes);
        return classes;
    }

    public void processClasses(JavaClass[] classes, String outputPath) throws IOException {
        for (JavaClass javaClass : classes) {
            JavaMethod[] methods = javaClass.getMethods();
            for (JavaMethod javaMethod : methods) {
                boolean doIt = false;
                Annotation[] annotations = javaMethod.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.getType().getJavaClass().getName().endsWith(".Inject")) {
                        doIt = true;
                        break;
                    }
                }
                if (doIt) {
                    // make the injection shim class, in outpath.
                }
            }
        }
    }
}
