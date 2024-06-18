package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

    public static  final String[] EMPTY_STRING_ARRAY = new String[0];

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            throw new InvalidDelimiterException("Los delimitadores no pueden estar vacíos");
        }
        final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9 ]", "");
        final int strLen = sanitizedStr.length();
        if (strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = sanitizedStr.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = sanitizedStr.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(sanitizedStr.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }

    public static String[] regexSubstringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final String regex = Pattern.quote(open) + "(.*?)" + Pattern.quote(close);
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        final List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }
}
