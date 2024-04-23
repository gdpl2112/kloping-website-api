package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.mywebsite.plugins.dto.pvpQQH0.Data;
import io.github.kloping.mywebsite.plugins.dto.pvpQQH0.PvpQQH0;
import io.github.kloping.mywebsite.plugins.dto.pvpQQVoice.PvpQQVoice;
import io.github.kloping.mywebsite.plugins.dto.pvpQQVoice.Yy_4e;
import io.github.kloping.mywebsite.plugins.dto.pvpQQVoice.Yylb_34;
import io.github.kloping.mywebsite.plugins.interfaces.PvpQq0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author github-kloping
 * @version 1.0
 * @date 2021/12/30-11
 */
@Entity
public class PvpQq {
    {
        ParserConfig.getGlobalInstance().setAsmEnable(false);
    }

    public static final Map<String, Yy_4e[]> NAME2VOICE = new ConcurrentHashMap<>();
    public static final Map<String, Data> NAME2DATA = new ConcurrentHashMap<>();
    public static final Map<String, Integer> NAME2ID = new ConcurrentHashMap<>();
    public static final Map<Integer, String> ID2NAME = new ConcurrentHashMap<>();

    @AutoStand
    public PvpQq0 pvpQq;

    public static String c1(String arg) {
        int i1 = arg.indexOf("(");
        int i2 = arg.lastIndexOf(")");
        return arg.substring(i1 + 1, i2);
    }

    public static String c0(String arg) {
        int i1 = arg.indexOf("(");
        int i2 = arg.lastIndexOf(")");
        return arg.substring(i1 + 1, i2).replaceAll("故事站-英雄列表-", "");
    }

    public String getSkinPic(String arg) {
        try {
            Document document = Jsoup.connect(arg).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36 Edg/97.0.1072.69")
                    .get();
            String picStr = document.getElementsByClass("banner").get(0).getElementsByTag("img").get(0).attr("src");
            return picStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void m1() {
        PvpQQH0 g = pvpQq.get1("createHeroList");
        PvpQQVoice v = pvpQq.get0("createList");
    }

    public void m0() {
        PvpQQH0 g = new PvpQQH0();
        PvpQQVoice v = new PvpQQVoice();
        for (Data datum : g.getData()) {
            if (datum == null) continue;
            String name = datum.getTitle().replace("故事站-英雄列表-", "");
            int id = Integer.valueOf(datum.getHeroid()).intValue();
            NAME2ID.put(name, id);
            ID2NAME.put(id, name);
            NAME2DATA.put(name, datum);
        }
        for (Yylb_34 yylb34 : v.getYylb_34()) {
            int id = Integer.parseInt(yylb34.getYxid_a7());
            NAME2VOICE.put(ID2NAME.get(id), yylb34.getYy_4e());
        }
    }

    public Yy_4e[] getY4e(String name) {
        if (NAME2VOICE.isEmpty()) {
            m0();
        }
        if (NAME2VOICE.containsKey(name)) {
            return NAME2VOICE.get(name);
        } else {
            return null;
        }
    }

    public Data getD(String name) {
        if (NAME2DATA.isEmpty()) {
            m0();
        }
        if (NAME2DATA.containsKey(name)) {
            return NAME2DATA.get(name);
        } else {
            return null;
        }
    }
}
