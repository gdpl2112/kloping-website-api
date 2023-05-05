package io.github.kloping.mywebsite.hangb;

import io.github.kloping.mywebsite.hangb.entity.VerifyQ;
import io.github.kloping.mywebsite.mapper.QVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author github.kloping
 */
@RestController
public class HangController {

    @Autowired
    QVMapper mapper;

    @RequestMapping("/vb0")
    public Boolean vb0(@RequestParam("qid") Long qid) {
        VerifyQ vq = mapper.selectById(qid);
        if (vq != null) {
            return vq.getExpire() > System.currentTimeMillis();
        }
        return false;
    }

    @RequestMapping("/getWsUrl")
    public String getWssUrl(HttpServletRequest request) {
        String host = request.getHeader("host");
        return String.format("ws://%s:80/hangws", host);
    }
}
