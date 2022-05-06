package io.github.kloping.mywebsite.plugins.detail;

/**
 * @author github.kloping
 */
public class All {
    public static String doc0(String callbackJson) {
        int i = callbackJson.indexOf("(");
        int i1 = callbackJson.lastIndexOf(");");
        String js = callbackJson.substring(i + 1, i1);
        return js;
    }
}
