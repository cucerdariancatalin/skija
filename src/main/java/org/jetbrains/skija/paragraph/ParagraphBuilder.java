package org.jetbrains.skija.paragraph;

import org.jetbrains.skija.impl.Managed;
import org.jetbrains.skija.impl.Native;
import org.jetbrains.skija.impl.Stats;

public class ParagraphBuilder extends Managed {
    public ParagraphBuilder(ParagraphStyle style, FontCollection fc) {
        super(_nMake(Native.getPtr(style), Native.getPtr(fc)), _finalizerPtr);
        Stats.onNativeCall();
    }

    public ParagraphBuilder pushStyle(TextStyle style) {
        Stats.onNativeCall();
        _nPushStyle(_ptr, Native.getPtr(style));
        return this;
    }

    public ParagraphBuilder popStyle() {
        Stats.onNativeCall();
        _nPopStyle(_ptr);
        return this;
    }

    public ParagraphBuilder addText(String text) {
        Stats.onNativeCall();
        _nAddText(_ptr, text);
        return this;
    }

    public Paragraph build() {
        Stats.onNativeCall();
        return new Paragraph(_nBuild(_ptr));
    }

    public static final long  _finalizerPtr = _nGetFinalizer();
    public static native long _nMake(long paragraphStylePtr, long fontCollectionPtr);
    public static native long _nGetFinalizer();
    public static native void _nPushStyle(long ptr, long textStylePtr);
    public static native void _nPopStyle(long ptr);
    public static native void _nAddText(long ptr, String text);
    public static native long _nBuild(long ptr);
}