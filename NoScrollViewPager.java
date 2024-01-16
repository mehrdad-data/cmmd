package net.imedicaldoctor.imd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class NoScrollViewPager extends ViewPager {

    /* renamed from: X3 */
    private boolean f77079X3;

    public NoScrollViewPager(Context context) {
        super(context);
        this.f77079X3 = true;
    }

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f77079X3 = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f77079X3 && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f77079X3 && super.onTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.f77079X3 = z;
    }
}
