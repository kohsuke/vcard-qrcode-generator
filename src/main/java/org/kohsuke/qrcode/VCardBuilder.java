package org.kohsuke.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Kohsuke Kawaguchi
 */
public class VCardBuilder {
    StringBuilder buf = new StringBuilder();

    public VCardBuilder() {
        buf.append("BEGIN:VCARD\nVERSION:3.0\n");
    }

    private String escape(String s) {
        s = s.replaceAll("([\\\\,;])", "\\\\$1");
        s = s.replaceAll("\\n", "\\\\n");
        return s;
    }

    public VCardBuilder with(String name, String v) {
        if (v!=null && v.length()>0) {
            buf.append(name).append(':').append(v).append('\n');
        }
        return this;
    }

    public String toString() {
        return buf.toString()+"END:VCARD";
    }

    public void writeQRCode(File out) throws IOException, WriterException {
        QRCodeWriter qr = new QRCodeWriter();
        BitMatrix matrix = qr.encode(toString(), BarcodeFormat.QR_CODE, 450, 450);
        FileOutputStream f = new FileOutputStream(out);
        try {
            MatrixToImageWriter.writeToStream(matrix, "png", f);
        } finally {
            f.close();
        }
    }
}
