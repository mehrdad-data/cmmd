package net.imedicaldoctor.imd.Views;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

/* renamed from: net.imedicaldoctor.imd.Views.Utils */
/* loaded from: classes2.dex */
public class C4846Utils {
    /* renamed from: a */
    public static int m3317a(float f, Resources resources) {
        return (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    /* renamed from: b */
    public static int m3316b(View view) {
        return view.getId() == 16908290 ? view.getLeft() : view.getLeft() + m3316b((View) view.getParent());
    }

    /* renamed from: c */
    public static int m3315c(View view) {
        return view.getId() == 16908290 ? view.getTop() : view.getTop() + m3315c((View) view.getParent());
    }
}
