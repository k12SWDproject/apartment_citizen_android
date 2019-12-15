package com.example.projectswd.utils;

public interface CallBackData<T> {

    void success(T t);
    void fail(String msg);

}
