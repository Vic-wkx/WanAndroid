package com.wkxjc.wanandroid

class TestClass : Cloneable {
    var valueInt = 0
    var valueString = ""
    var valueBoolean = true
    val pack = Pack()

     fun copy(): TestClass {
        return TestClass().clone() as TestClass
    }
}