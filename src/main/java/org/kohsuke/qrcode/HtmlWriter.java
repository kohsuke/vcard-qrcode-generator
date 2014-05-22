package org.kohsuke.qrcode;

import com.google.zxing.WriterException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Kohsuke Kawaguchi
 */
public class HtmlWriter {
    private final PrintWriter out;
    private final File dir;
    private int iota=1;

    public HtmlWriter(File dir) throws IOException {
        this.dir = dir;
        this.out = new PrintWriter(new File(dir,"out.html"));
        out.println(IOUtils.toString(HtmlWriter.class.getResourceAsStream("/badges.html")));
    }

    public void add(String firstName, String lastName, String company, String email) throws IOException, WriterException {
        VCardBuilder vc = new VCardBuilder();
        vc.with("N",firstName+" "+lastName)
                .with("ORG", company)
//                .with("TEL","012-345-6789")
                .with("EMAIL", email);
        System.out.println(vc.toString());

        vc.writeQRCode(new File(dir,"qr"+iota+".png"));

        out.printf(
                "<div class='badge'>\n" +
                "    <img class='qrcode' src='qr%d.png'>\n" +
                "    <div class='firstName'>%s</div>\n" +
                "    <div class='lastName'>%s</div>\n" +
                "    <div class='company'>%s</div>\n" +
                "</div>\n", iota, firstName, lastName, company
        );
        iota++;
    }
    
    public void close() {
        out.println("</body></html>");
        out.close();
    }
}
