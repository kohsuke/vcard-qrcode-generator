package org.kohsuke.qrcode;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws WriterException, IOException {
        HtmlWriter o = new HtmlWriter(new File("."));
        o.add("Kohsuke","Kawaguchi","CloudBees, Inc.","kk@kohsuke.org");
        o.close();
    }
}
