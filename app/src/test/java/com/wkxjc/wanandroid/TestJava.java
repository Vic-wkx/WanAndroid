package com.wkxjc.wanandroid;

public class TestJava implements Cloneable {
    public int a = 0;
    public String b = "testJava";
    public PackJava packJava = new PackJava();

    public TestJava copy() {
        try {
            TestJava testJava = (TestJava) clone();
            testJava.packJava = packJava.copy();
            return testJava;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
