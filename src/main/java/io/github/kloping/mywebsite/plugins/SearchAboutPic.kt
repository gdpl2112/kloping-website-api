package com.hrs.plugins

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.URLEncoder

object SearchAboutPic {

    @JvmStatic
    public fun searchPicAbout(imgUrl: String): Array<String?>? {
        var aboutKey = ""
        val iur: String?
        if (imgUrl.contains("{}")) {
            aboutKey = imgUrl.split("{}")[1]
            iur = imgUrl.split("{}")[0]
        } else
            iur = URLEncoder.encode(imgUrl, "utf-8")
        val jsoup = Jsoup.connect(
            "https://pic.sogou.com/ris?flag=1&from=pic_result_list&query=$iur"
        )
        val doc = jsoup.get();
        val doc2 = doc.getElementsByClass("picture-source")
        val docs = doc2.select("a")
        var N = getMaybeN(docs, aboutKey)
        var sourceUrl: String
        while (N < docs.size) {
            try {
                sourceUrl = docs[N++].attr("href")
                val v = getDetailImg(sourceUrl)
                return v
            } catch (e: Exception) {
                e.printStackTrace()
                continue
            }
        }
        return null
    }

    private fun getMaybeN(es: Elements, key: String): Int {
        if (key.isEmpty()) return 0
        for (i in 1..es.size) {
            if (es[i - 1].text().contains(key))
                return i - 1;
        }
        return 0
    }

    private fun getDetailImg(sourceUrl: String): Array<String?>? {
        val jsoup = Jsoup.connect(sourceUrl)
        var div1 = jsoup.get().getElementById("mp-editor")

        if (div1 == null) div1 = jsoup.get().getElementsByClass("post_body")[0]

        val imgs = div1?.select("img")
        val arr = imgs?.size?.let { arrayOfNulls<String>(it) }
        for (i in imgs?.indices!!) {
            arr?.set(i, imgs[i].attr("src"))
        }
        return arr;
    }
}