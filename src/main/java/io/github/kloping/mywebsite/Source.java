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

    public static void setPrintln(String filePath) {
        try {
            PrintStream oldPrintStream = System.out;
            FileOutputStream bos = new FileOutputStream(filePath);
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

    public static void println(String t) {
        System.out.println("====================" + t + "=====================\r\n");
    }

    private static String today;
    private static File file = new File("./sh.txt");
    private static File fileMap = new File("./upMap.txt");
    public static final Map<String, Object> objs = new HashMap<>();
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

    public static void onCreate() {
    }


    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

    public static long getTimeFromNowTo(int day, int hour, int mil) {
        Date date = null;
        try {
            date = df.parse("2022-" + getToMon() + "-" + day + "-" + hour + "-" + mil);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        long now = System.currentTimeMillis();
        return millis - now;
    }

    public static String getToMon() {
        return toMon == null ? (toMon = new SimpleDateFormat("MM").format(new Date())) : toMon;
    }

    public static String getToYear() {
        return toYear == null ? (toYear = new SimpleDateFormat("yyyy").format(new Date())) : toYear;
    }

}
