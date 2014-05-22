package org.kohsuke.qrcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Kohsuke Kawaguchi
 */
public class HtmlWriter {
    private final PrintWriter out;

    public HtmlWriter(PrintWriter out) {
        this.out = out;
    }

    public HtmlWriter(File f) throws FileNotFoundException {
        this(new PrintWriter(f));
    }


}
