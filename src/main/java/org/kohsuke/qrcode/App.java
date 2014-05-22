package org.kohsuke.qrcode;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws WriterException, IOException {
        VCardBuilder vc = new VCardBuilder();
        vc.with("N","Kohsuke Kawaguchi")
                .with("ORG","CloudBees, Inc.")
                .with("TEL","012-345-6789")
                .with("EMAIL","kk@kohsuke.org");
        System.out.println(vc.toString());

        vc.writeQRCode(new File("qrcode.png"));


    }
}
