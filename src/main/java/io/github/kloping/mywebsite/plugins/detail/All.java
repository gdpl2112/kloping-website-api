package io.github.kloping.mywebsite.plugins.detail;

import java.util.regex.Pattern;

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
    public static String doc1(String callbackJson) {
        int i = callbackJson.indexOf("(");
        int i1 = callbackJson.lastIndexOf(")");
        String js = callbackJson.substring(i + 1, i1);
        return js;
    }
    private static final String SINGLE_PATTERN = "[0-9|a-f|A-F]";
    private static final String PATTERN = SINGLE_PATTERN + SINGLE_PATTERN +
            SINGLE_PATTERN + SINGLE_PATTERN;
    /**
     * 把 \\u 开头的单字转成汉字，如 \\u6B65 ->　步
     *
     * @param str
     * @return
     */
    private static String ustartToCn(final String str) {
        StringBuilder sb = new StringBuilder().append("0x")
                .append(str.substring(2, 6));
        Integer codeInteger = Integer.decode(sb.toString());
        int code = codeInteger.intValue();
        char c = (char) code;
        return String.valueOf(c);
    }

    /**
     * 字符串是否以Unicode字符开头。约定Unicode字符以 \\u开头。
     *
     * @param str 字符串
     * @return true表示以Unicode字符开头.
     */
    private static boolean isStartWithUnicode(final String str) {
        if (null == str || str.length() == 0) {
            return false;
        }
        if (!str.startsWith("\\u")) {
            return false;
        }
        if (str.length() < 6) {
            return false;
        }
        String content = str.substring(2, 6);

        boolean isMatch = Pattern.matches(PATTERN, content);
        return isMatch;
    }

    /**
     * 字符串中，所有以 \\u 开头的UNICODE字符串，全部替换成汉字
     *
     * @param strParam
     * @return
     */
    public static String unicodeToCn(final String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; ) {
            String tmpStr = str.substring(i);
            if (isStartWithUnicode(tmpStr)) { // 分支1
                sb.append(ustartToCn(tmpStr));
                i += 6;
            } else { // 分支2
                sb.append(str.substring(i, i + 1));
                i++;
            }
        }
        return sb.toString();
    }
}
