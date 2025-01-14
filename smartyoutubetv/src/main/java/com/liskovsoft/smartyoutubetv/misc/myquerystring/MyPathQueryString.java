package com.liskovsoft.smartyoutubetv.misc.myquerystring;

import com.liskovsoft.sharedutils.helpers.Helpers;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Overview: http://myurl.com/key/value/key2/value2/key3/value3<br/>
 * Regex: \/key\/([^\/]*)
 */
public class MyPathQueryString implements MyQueryString {
    private String mUrl;

    public MyPathQueryString(String url) {
        mUrl = url;
    }

    @Override
    public String get(String key) {
        if (mUrl == null) {
            return null;
        }

        final String template = "\\/%s\\/([^\\/]*)";
        Pattern pattern = Pattern.compile(String.format(template, key));
        Matcher matcher = pattern.matcher(mUrl);
        boolean result = matcher.find();
        return result ? matcher.group(1) : null;
    }

    @Override
    public void set(String key, String value) {
        if (mUrl == null) {
            return;
        }

        if (value == null) {
            return;
        }

        if (!replace(key, value)) {
            String pattern = mUrl.endsWith("/") ? "%s/%s" : "/%s/%s";
            mUrl += String.format(pattern, key, value);
        }
    }

    @Override
    public void set(String key, float value) {
        set(key, String.valueOf(value));
    }

    private boolean replace(String key, String newValue) {
        if (mUrl == null) {
            return false;
        }

        String originUrl = mUrl;

        final String template = "\\/%s\\/[^\\/]*";
        mUrl = mUrl.replaceAll(
                String.format(template, key),
                String.format("\\/%s\\/%s", key, newValue));

        return !mUrl.equals(originUrl);
    }

    @Override
    public void remove(String key) {
        if (mUrl == null) {
            return;
        }

        final String template = "\\/%s\\/[^\\/]*";
        mUrl = mUrl.replaceAll(String.format(template, key), "");
    }

    @NotNull
    @Override
    public String toString() {
        return mUrl;
    }

    @Override
    public boolean isEmpty() {
        return mUrl == null || mUrl.isEmpty();
    }

    public static MyPathQueryString parse(String url) {
        return new MyPathQueryString(url);
    }

    @Override
    public boolean isValid() {
        if (mUrl == null) {
            return false;
        }

        return Helpers.matchAll(mUrl, "^[^&=]*$", "\\/[^\\/]+\\/[^\\/]+");
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }
}
