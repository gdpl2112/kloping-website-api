package com.hrs.plugins

import com.alibaba.fastjson.JSON
import org.jsoup.Jsoup

object SearchBaiduPicNum {
    private val u1: String = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&fp=result&ie=utf-8&oe=utf-8&word=%s&rn=%s&pn=%s"

    private val oneMax = 60;

    @JvmStatic
    fun searchPic(key: String, nums: Int): Array<String?>? {
        try {
            val keyword = key;
            var num = nums;
            var urlStr = String.format(u1, keyword, num, 0)
            println(urlStr)
            var jsoup = Jsoup.connect(urlStr)
                .ignoreContentType(true).timeout(3000)
                .header(
                    "Accept",
                    "text/*,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
                )
                .userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36 Edg/93.0.961.52"
                )
            jsoup.get()
            val cs = jsoup.cookieStore()
            val arr = arrayOfNulls<String>(num)
            var index = 0
            var index_ = 0
            do {
                urlStr = String.format(u1, keyword, num, index_)
                jsoup = Jsoup.connect(urlStr)
                    .ignoreContentType(true).timeout(3000)
                    .header(
                        "Accept",
                        "text/*,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
                    )
                    .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36 Edg/93.0.961.52"
                    ).cookieStore(cs)
                val htmlContent = jsoup.get()
                val jsonStr = htmlContent.body().text()
                val jsonObj = JSON.parseObject(jsonStr)
                val jsonArr = jsonObj.getJSONArray("data")

                for (i in 1..jsonArr.size) {
                    try {
                        arr[index++] = jsonArr.getJSONObject(i - 1).getString("middleURL")
                    } catch (e: Exception) {
                        continue
                    }
                }
                num -= oneMax
                index_ += oneMax
            } while (num > 0)
            return arr
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}