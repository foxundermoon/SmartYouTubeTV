package com.liskovsoft.smartyoutubetv.misc.myquerystring;

import org.jetbrains.annotations.NotNull;

public class MyNullQueryString implements MyQueryString {
    private final String mUrl;

    private MyNullQueryString(String url) {
        mUrl = url;
    }

    public static MyQueryString parse(String url) {
        return new MyNullQueryString(url);
    }

    @Override
    public void remove(String key) {
        
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void set(String key, float value) {
        
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @NotNull
    @Override
    public String toString() {
        return mUrl;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }
}
