package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.idiom.M;
import io.github.kloping.mywebsite.services.impl.Idiom;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.github.kloping.mywebsite.services.impl.Idiom.idiom;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiIdiomController {

    @RequestMapping("/idiom")
    public M p1(String word) {
        word = word.trim();
        char c1 = word.charAt(0);
        if (idiom.containsKey(c1)) {
            if (idiom.get(c1).contains(word)) {
                String[] strings = new String[4];
                int i = 0;
                for (char c : word.toCharArray()) {
                    strings[i++] = Idiom.getCharPinYin(c);
                }
                return new M().setState(1).setWord(word).setPinyin(strings);
            }
        }
        return new M().setState(-1).setPinyin(null).setWord(word);
    }

    @RequestMapping("/idiom/random")
    public String p2() {
        return Idiom.INSTANCE.getRandom();
    }

}
