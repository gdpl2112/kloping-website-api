package io.github.kloping.mywebsite;

import okhttp3.OkHttpClient;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author github-kloping
 * @version 1.0
 */
public class Source {

    private static String toMon = null;
    private static String toYear = null;

    static {
        SimpleDateFormat dfn = new SimpleDateFormat("/yyyy/MM/dd/HH_mm_ss/");
        String logPath = "./logs" + dfn.format(new Date());
        try {
            String path = logPath + "err.log";
            PrintStream oldPrintStream = System.err;
            new File(path).getParentFile().mkdirs();
            new File(path).createNewFile();
            FileOutputStream bos = new FileOutputStream(path, true);
            PrintStream printStream = new PrintStream(bos) {
                @Override
                public void write(int b) {
                    super.write(b);
                    oldPrintStream.write((int) b);
                }

                @Override
                public void write(byte[] buf, int off, int len) {
                    super.write(buf, off, len);
                    oldPrintStream.write(buf, off, len);
                }
            };
            System.setErr(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String path = logPath + "out.log";
            PrintStream oldPrintStream = System.out;
            new File(path).getParentFile().mkdirs();
            new File(path).createNewFile();
            FileOutputStream bos = new FileOutputStream(path, true);
            PrintStream printStream = new PrintStream(bos) {
                @Override
                public void write(int b) {
                    super.write(b);
                    oldPrintStream.write((int) b);
                }

                @Override
                public void write(byte[] buf, int off, int len) {
                    super.write(buf, off, len);
                    oldPrintStream.write(buf, off, len);
                }
            };
            System.setOut(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
