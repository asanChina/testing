package com.google.pengjie.second;

import android.util.Log;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

@Implements(Foo.class)
public class ShadowFoo {

    @RealObject Foo foo;

    @Implementation
    public void display() {
        Log.e("here", "here in ShadowFoo.display()" + (foo == null));
    }

    @Implementation
    protected void __constructor__() {
        Log.e("here", "here in ShadowFoo.__constructor__()");
    }
}
