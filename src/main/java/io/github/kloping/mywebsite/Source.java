package io.github.kloping.mywebsite;

import io.github.kloping.mywebsite.controller.UtilsController;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.github.kloping.mywebsite.utils.MyUtils.threads;

/**
 * @author github-kloping
 * @version 1.0
 */
public class Source {

    private static void setPrintln(String filePath) {
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
        threads.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int today = Integer.parseInt(getToday());
                    long time = -1;
                    while ((time = getTimeFromNowTo(today + 1, 0, 0)) < 10000) {
                    }
                    Thread.sleep(time);
                    save(UtilsController.t1 + ":" + UtilsController.t2);
                    UtilsController.t1 = 1;
                    UtilsController.t2 = 1;
                    Source.today = null;
                    run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        initMap();
        initValue();
    }

    private static void initValue() {
        if (objs.containsKey("t1"))
            UtilsController.t1 = Integer.valueOf(objs.get("t1").toString());
        if (objs.containsKey("t2"))
            UtilsController.t2 = Integer.valueOf(objs.get("t2").toString());
    }

    public static synchronized void updateMap() {
        objs.put("t1", UtilsController.t1);
        objs.put("t2", UtilsController.t2);
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileMap), true);
            for (String k : objs.keySet()) {
                String line = k + ":" + objs.get(k);
                pw.println(line);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initMap() {
        try {
            if (!fileMap.exists())
                fileMap.createNewFile();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileMap)));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!line.contains(":") || line.isEmpty()) continue;
                String[] ss = line.split(":");
                objs.put(ss[0], ss[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void save(String s) {
        try {
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true), true);
            pw.println(s);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getToday() {
        return today == null ? (today = new SimpleDateFormat("dd").format(new Date())) : today;
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
