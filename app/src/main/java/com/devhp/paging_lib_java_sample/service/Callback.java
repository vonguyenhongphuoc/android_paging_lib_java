package com.devhp.paging_lib_java_sample.service;

public interface Callback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}
