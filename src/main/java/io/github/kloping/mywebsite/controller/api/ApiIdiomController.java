package io.github.kloping.mywebsite.controller.api;

import io.github.kloping.mywebsite.domain.bo.idiom.IdiomResult;
import io.github.kloping.mywebsite.services.impl.IdiomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.github.kloping.mywebsite.services.impl.IdiomService.idiom;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiIdiomController {

    @RequestMapping("/idiom")
    public IdiomResult p1(String word) {
        word = word.trim();
        char c1 = word.charAt(0);
        if (idiom.containsKey(c1)) {
            if (idiom.get(c1).contains(word)) {
                String[] strings = new String[4];
                int i = 0;
                for (char c : word.toCharArray()) {
                    strings[i++] = IdiomService.getCharPinYin(c);
                }
                return new IdiomResult().setState(1).setWord(word).setPinyin(strings);
            }
        }
        return new IdiomResult().setState(-1).setPinyin(null).setWord(word);
    }

    @RequestMapping("/idiom/random")
    public String p2() {
        return IdiomService.INSTANCE.getRandom();
    }
}
