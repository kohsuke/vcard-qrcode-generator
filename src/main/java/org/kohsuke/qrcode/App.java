package org.kohsuke.qrcode;

import com.google.zxing.WriterException;
import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws WriterException, IOException {
        CSVReader<String[]> csv = new CSVReaderBuilder<String[]>(new FileReader("input.csv"))
                .strategy(CSVStrategy.UK_DEFAULT)
                .entryParser(new DefaultCSVEntryParser())
                .build();

        List<String> headers = csv.readHeader();

        HtmlWriter o = new HtmlWriter(new File("."));
        for (String[] row : csv.readAll()) {
            o.add(
                    row[headers.indexOf("First Name")],
                    row[headers.indexOf("Last Name")],
                    row[headers.indexOf("Company")],
                    row[headers.indexOf("Email")],
                    row[headers.indexOf("Work Phone")]);
        }
        o.close();
    }
}
