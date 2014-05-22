package org.kohsuke.qrcode;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;

import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {

    private List<String> headers;

    public static void main(String[] args) throws Exception {
        new App().run();
    }

    public void run() throws Exception {
        CSVReader<String[]> csv = new CSVReaderBuilder<String[]>(new FileReader("input.csv"))
                .strategy(CSVStrategy.UK_DEFAULT)
                .entryParser(new DefaultCSVEntryParser())
                .build();

        headers = csv.readHeader();

        HtmlWriter o = new HtmlWriter(new File("."));
        List<String[]> rows = csv.readAll();
        Collections.sort(rows, comparator);

        for (String[] row : rows) {
            o.add(
                    row[column("First Name")],
                    row[column("Last Name")],
                    row[column("Company")],
                    row[column("Email")],
                    row[column("Work Phone")]);
        }
        o.close();
    }

    private int column(String name) {
        return headers.indexOf(name);
    }

    private Comparator<String[]> comparator = new Comparator<String[]>() {
        public int compare(String[] a, String[] b) {
            int r = cmp(a, b, column("First Name"));
            if (r!=0)   return r;
            return cmp(a, b, column("Last Name"));
        }

        public int cmp(String[] a, String[] b, int column) {
            return a[column].compareTo(b[column]);
        }
    };
}
