package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.ISearchPic;
import io.github.kloping.mywebsite.utils.BlogCodeUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchPicBaidu implements ISearchPic {
    private static final Map<String, String> HEADERS = new ConcurrentHashMap<>();

    static {
        HEADERS.put("Connection", "keep-alive");
        HEADERS.put("Pragma", "no-cache");
        HEADERS.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36 Edg/93.0.961.52");
        HEADERS.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng");
        HEADERS.put("Cookie", "BIDUPSID=1655A31A5C2A84D0C46273DFBE521586; PSTM=1627549013; BAIDUID=1655A31A5C2A84D0BE7716EEAC76577A:FG=1; __yjs_duid=1_2e3703f26ac714640e331fe7dcbd18c81627650408999; BDUSS=dUeExrWXdjVEdkVFcyNUxvcUZjMU0yZFVuY1l6VVI1dGU4U1RoakM5VUtyMTVoRUFBQUFBJCQAAAAAAAAAAAEAAACMimiYSFJTOTc0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoiN2EKIjdhbk; BDUSS_BFESS=dUeExrWXdjVEdkVFcyNUxvcUZjMU0yZFVuY1l6VVI1dGU4U1RoakM5VUtyMTVoRUFBQUFBJCQAAAAAAAAAAAEAAACMimiYSFJTOTc0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoiN2EKIjdhbk; BAIDUID_BFESS=1655A31A5C2A84D0BE7716EEAC76577A:FG=1; indexPageSugList=; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; BDRCVFR[5ig7pqb-tu6]=mk3SLVN4HKm; BDRCVFR[OEHfjv-pq1f]=K7PavkeTFPTUAN8ULuEQhPEUi4WU6; delPer=0; PSINO=3; BDRCVFR[77Ms7oRaB-6]=K7PavkeTFPTUAN8ULuEQhPEUi4WU6; H_PS_PSSID=31660_26350; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; userFrom=null; ab_sr=1.0.1_ZDMzNWFjZTFiOTU2NWI2OGYwZjljMTQ2MmI3ZmI0NmM3M2E4OGNjYWQ4NWVkMzBlNGNiMzhkNjZjNTZkZmQ2NDAxNjFmOGU2YjQzNDYyNTEzOWNhNTdmMzkxZGUxZTdhYTQ0YzRjZDA0NjU4Mjg2MjIzMWNiMWM5ZjNlZGRhZTljNmMxMjBiYzMyMzg3MTdiZTRjOGNkNjVlNDI0NDQ1NGNjZGU3YTVhMGExMjY5MjQwYTlhY2M4NzE5Njg2MmYw; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm");

    }

    private static final Pattern pattern = Pattern.compile("http[s]?://.*?[\"|;') ]");

    @Override
    public String[] searchPics(String keyword, Integer num) {
        try {
            if (!keyword.startsWith("%")) keyword = BlogCodeUtils.toUrlString(keyword, "UTF-8");
            String urlPic;
            urlPic = "https://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-2&cl=2&nc=1&ie=utf-8&&word=" + keyword;
            System.out.println(urlPic);
            URL url = new URL(urlPic);
            URLConnection connection = url.openConnection();
            HEADERS.forEach((k, v) -> {
                connection.addRequestProperty(k.trim(), v.trim());
            });
            connection.connect();
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024 * 1024];
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            bytes = baos.toByteArray();
            String html = new String(bytes, "UTF-8");
            Matcher matcher = pattern.matcher(html);
            List<String> strings = new CopyOnWriteArrayList<>();
            while (matcher.find()) {
                String u1 = matcher.group();
                u1 = u1.substring(0, u1.length() - 1);
                if (filterBaiduPics(u1)) continue;
                if (strings.contains(u1)) continue;
                else strings.add(u1);
            }
            return strings.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static boolean filterBaiduPics(String u1) {
        u1 = u1.trim();
        if (u1.equals("https://ss2.bdstatic.com/-0U0b8Sm1A5BphGlnYG/public03/e0772ded57d4725027b0a757c64a6940.png"))
            return true;
        if (!(u1.contains("JPEG") || u1.contains("jpg") || u1.contains("png"))) return true;
        if (u1.startsWith("https://dss1.bdstatic.com/")) return true;
        if (u1.startsWith("https://img0.baidu.com/it/")) return true;
        return false;
    }
}
