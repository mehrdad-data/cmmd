package net.imedicaldoctor.imd;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

/* loaded from: classes2.dex */
public class iMDSlidingPaneLayout extends SlidingPaneLayout {

    /* renamed from: Z2 */
    boolean f83468Z2;

    public iMDSlidingPaneLayout(Context context) {
        super(context);
        this.f83468Z2 = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("lockfull", true);
    }

    public iMDSlidingPaneLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83468Z2 = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("lockfull", true);
    }

    public iMDSlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f83468Z2 = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("lockfull", true);
    }

    @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f83468Z2 || m42175l() || findViewById(C4804R.C4808id.f86962menu_button) == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
