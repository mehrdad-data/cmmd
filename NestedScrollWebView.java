package net.imedicaldoctor.imd;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

/* loaded from: classes2.dex */
public class NestedScrollWebView extends WebView implements NestedScrollingChild {

    /* renamed from: D2 */
    public static final String f77073D2 = "NestedScrollWebView";

    /* renamed from: A2 */
    private final int[] f77074A2;

    /* renamed from: B2 */
    private int f77075B2;

    /* renamed from: C2 */
    private NestedScrollingChildHelper f77076C2;

    /* renamed from: s */
    private int f77077s;

    /* renamed from: z2 */
    private final int[] f77078z2;

    public NestedScrollWebView(Context context) {
        this(context, null);
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f77078z2 = new int[2];
        this.f77074A2 = new int[2];
        this.f77076C2 = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f77076C2.m46609a(f, f2, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f77076C2.m46608b(f, f2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f77076C2.m46607c(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f77076C2.m46604f(i, i2, i3, i4, iArr);
    }

    /* renamed from: e */
    public boolean m3487e() {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("NestedScroll", true);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.f77076C2.m46599k();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        if (m3487e()) {
            return this.f77076C2.m46597m();
        }
        return false;
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (m3487e()) {
            try {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                int m46619c = MotionEventCompat.m46619c(motionEvent);
                if (m46619c == 0) {
                    this.f77075B2 = 0;
                }
                int y = (int) motionEvent.getY();
                motionEvent.offsetLocation(0.0f, this.f77075B2);
                if (m46619c != 0) {
                    if (m46619c != 1) {
                        if (m46619c == 2) {
                            int i = this.f77077s - y;
                            if (dispatchNestedPreScroll(0, i, this.f77074A2, this.f77078z2)) {
                                i -= this.f77074A2[1];
                                obtain.offsetLocation(0.0f, this.f77078z2[1]);
                                this.f77075B2 += this.f77078z2[1];
                            }
                            int scrollY = getScrollY();
                            this.f77077s = y - this.f77078z2[1];
                            if (i < 0) {
                                int max = Math.max(0, scrollY + i);
                                int i2 = i - (max - scrollY);
                                if (dispatchNestedScroll(0, max - i2, 0, i2, this.f77078z2)) {
                                    int i3 = this.f77077s;
                                    int i4 = this.f77078z2[1];
                                    this.f77077s = i3 - i4;
                                    obtain.offsetLocation(0.0f, i4);
                                    this.f77075B2 += this.f77078z2[1];
                                }
                            }
                            obtain.recycle();
                            return super.onTouchEvent(obtain);
                        } else if (m46619c != 3) {
                            return false;
                        }
                    }
                    stopNestedScroll();
                } else {
                    this.f77077s = y;
                    startNestedScroll(2);
                }
                return super.onTouchEvent(motionEvent);
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                e.printStackTrace();
                try {
                    return super.onTouchEvent(motionEvent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        if (m3487e()) {
            this.f77076C2.m46594p(z);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.f77076C2.m46592r(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.f77076C2.m46590t();
    }
}
