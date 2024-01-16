package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes2.dex */
public class CustomView extends RelativeLayout {

    /* renamed from: C2 */
    static final String f83400C2 = "http://schemas.android.com/apk/res-auto";

    /* renamed from: D2 */
    static final String f83401D2 = "http://schemas.android.com/apk/res/android";

    /* renamed from: A2 */
    public boolean f83402A2;

    /* renamed from: B2 */
    boolean f83403B2;

    /* renamed from: s */
    final int f83404s;

    /* renamed from: z2 */
    int f83405z2;

    public CustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83404s = Color.parseColor("#E2E2E2");
        this.f83402A2 = false;
        this.f83403B2 = false;
    }

    @Override // android.view.View
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        this.f83403B2 = false;
    }

    @Override // android.view.View
    protected void onAnimationStart() {
        super.onAnimationStart();
        this.f83403B2 = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f83403B2) {
            invalidate();
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setBackgroundColor(z ? this.f83405z2 : this.f83404s);
        invalidate();
    }
}
