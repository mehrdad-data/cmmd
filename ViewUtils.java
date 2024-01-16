package net.imedicaldoctor.imd.Fragments;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public class ViewUtils {
    /* renamed from: a */
    public static boolean m4148a(View view, int i, int i2) {
        int m46392x0 = (int) (ViewCompat.m46392x0(view) + 0.5f);
        int m46388y0 = (int) (ViewCompat.m46388y0(view) + 0.5f);
        return i >= view.getLeft() + m46392x0 && i <= view.getRight() + m46392x0 && i2 >= view.getTop() + m46388y0 && i2 <= view.getBottom() + m46388y0;
    }
}
