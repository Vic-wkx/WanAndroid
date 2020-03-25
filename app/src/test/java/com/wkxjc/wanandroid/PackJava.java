package com.wkxjc.wanandroid;

public class PackJava implements Cloneable {
    public int a = 1;
    public String b = "haha";

    public PackJava copy() throws CloneNotSupportedException {
        return (PackJava) clone();
    }
}
