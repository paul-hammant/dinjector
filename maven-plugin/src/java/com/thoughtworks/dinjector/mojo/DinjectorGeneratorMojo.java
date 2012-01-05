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
package com.thoughtworks.dinjector.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.thoughtworks.dinjector.generator.DinjectorGenerator;
import com.thoughtworks.dinjector.generator.QdoxDinjectorGenerator;

import java.io.IOException;

/**
 * Maven Plugin to generate Injection Helpers via DinjectorGenerator
 *
 * @author Paul Hammant
 * @goal generate
 * @phase compile
 * @requiresDependencyResolution compile
 */
public class DinjectorGeneratorMojo extends AbstractMojo {

    /**
     * The directory containing the Java source files
     *
     * @parameter default-value="${project.build.sourceDirectory}"
     * @required
     */
    protected String sourceDirectory;

    /**
     * The directory where the Dinjector generator will write the output
     *
     * @parameter default-value="${project.build.outputDirectory}"
     * @required
     */
    protected String outputDirectory;

    /**
     * The Dinjector generator
     */
    private DinjectorGenerator generator = new QdoxDinjectorGenerator();

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Generating Injection Helpers from " + sourceDirectory + " to " + outputDirectory);
        try {
            generator.processSourcePath(sourceDirectory, outputDirectory);
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to generate Injection Helpers from "+sourceDirectory, e);
        }
    }

}
