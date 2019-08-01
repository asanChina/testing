package com.google.pengjie.second;

import android.util.Log;

public class Foo {

    public Foo() {
        Log.e("here", "here in Foo.constructor");
    }

    private Foo(Foo test) {

    }

    public void display() {
        Log.e("here", "here in Foo.display()");
    }
}
