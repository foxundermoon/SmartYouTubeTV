package com.liskovsoft.smartyoutubetv.misc.myquerystring;

public interface MyQueryString {
    void remove(String key);
    String get(String key);
    void set(String key, String value);
    void set(String key, float value);
    boolean isEmpty();
    boolean isValid();
    boolean contains(String key);
}
