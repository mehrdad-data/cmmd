package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public abstract class Button extends CustomView {

    /* renamed from: R2 */
    static final String f83356R2 = "http://schemas.android.com/apk/res/android";

    /* renamed from: E2 */
    int f83357E2;

    /* renamed from: F2 */
    int f83358F2;

    /* renamed from: G2 */
    int f83359G2;

    /* renamed from: H2 */
    float f83360H2;

    /* renamed from: I2 */
    int f83361I2;

    /* renamed from: J2 */
    Integer f83362J2;

    /* renamed from: K2 */
    View.OnClickListener f83363K2;

    /* renamed from: L2 */
    boolean f83364L2;

    /* renamed from: M2 */
    int f83365M2;

    /* renamed from: N2 */
    TextView f83366N2;

    /* renamed from: O2 */
    float f83367O2;

    /* renamed from: P2 */
    float f83368P2;

    /* renamed from: Q2 */
    float f83369Q2;

    public Button(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83360H2 = 12.0f;
        this.f83361I2 = 3;
        this.f83364L2 = true;
        this.f83365M2 = Color.parseColor("#1E88E5");
        this.f83367O2 = -1.0f;
        this.f83368P2 = -1.0f;
        this.f83369Q2 = -1.0f;
        mo3334c();
        this.f83364L2 = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "animate", true);
        setAttributes(attributeSet);
        this.f83405z2 = this.f83365M2;
        if (this.f83362J2 == null) {
            this.f83362J2 = Integer.valueOf(mo3343b());
        }
    }

    /* renamed from: a */
    public Bitmap m3344a() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth() - C4846Utils.m3317a(6.0f, getResources()), getHeight() - C4846Utils.m3317a(7.0f, getResources()), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.f83362J2.intValue());
        canvas.drawCircle(this.f83367O2, this.f83368P2, this.f83369Q2, paint);
        if (this.f83369Q2 > getHeight() / this.f83361I2) {
            this.f83369Q2 += this.f83360H2;
        }
        if (this.f83369Q2 >= getWidth()) {
            this.f83367O2 = -1.0f;
            this.f83368P2 = -1.0f;
            this.f83369Q2 = getHeight() / this.f83361I2;
            View.OnClickListener onClickListener = this.f83363K2;
            if (onClickListener != null && this.f83364L2) {
                onClickListener.onClick(this);
            }
        }
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public int mo3343b() {
        int i = this.f83365M2;
        int i2 = (i >> 8) & 255;
        int i3 = (i >> 0) & 255;
        int i4 = ((i >> 16) & 255) - 30;
        if (i4 < 0) {
            i4 = 0;
        }
        int i5 = i2 - 30;
        if (i5 < 0) {
            i5 = 0;
        }
        int i6 = i3 - 30;
        return Color.rgb(i4, i5, i6 >= 0 ? i6 : 0);
    }

    /* renamed from: c */
    protected void mo3334c() {
        setMinimumHeight(C4846Utils.m3317a(this.f83358F2, getResources()));
        setMinimumWidth(C4846Utils.m3317a(this.f83357E2, getResources()));
        setBackgroundResource(this.f83359G2);
        setBackgroundColor(this.f83365M2);
    }

    public float getRippleSpeed() {
        return this.f83360H2;
    }

    public String getText() {
        return this.f83366N2.getText().toString();
    }

    public TextView getTextView() {
        return this.f83366N2;
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            return;
        }
        this.f83367O2 = -1.0f;
        this.f83368P2 = -1.0f;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0073, code lost:
        if (r7.getY() >= 0.0f) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c4, code lost:
        if (r7.getAction() == 3) goto L16;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r6.invalidate()
            boolean r0 = r6.isEnabled()
            r1 = 1
            if (r0 == 0) goto Lc7
            r6.f83402A2 = r1
            int r0 = r7.getAction()
            if (r0 != 0) goto L2a
            int r0 = r6.getHeight()
            int r2 = r6.f83361I2
            int r0 = r0 / r2
            float r0 = (float) r0
            r6.f83369Q2 = r0
            float r0 = r7.getX()
            r6.f83367O2 = r0
            float r7 = r7.getY()
            r6.f83368P2 = r7
            goto Lc7
        L2a:
            int r0 = r7.getAction()
            r2 = 2
            r3 = 0
            r4 = 0
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 != r2) goto L7c
            int r0 = r6.getHeight()
            int r2 = r6.f83361I2
            int r0 = r0 / r2
            float r0 = (float) r0
            r6.f83369Q2 = r0
            float r0 = r7.getX()
            r6.f83367O2 = r0
            float r0 = r7.getY()
            r6.f83368P2 = r0
            float r0 = r7.getX()
            int r2 = r6.getWidth()
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getX()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L75
            float r0 = r7.getY()
            int r2 = r6.getHeight()
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L75
            float r7 = r7.getY()
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 >= 0) goto Lc7
        L75:
            r6.f83402A2 = r3
            r6.f83367O2 = r5
            r6.f83368P2 = r5
            goto Lc7
        L7c:
            int r0 = r7.getAction()
            if (r0 != r1) goto Lbf
            float r0 = r7.getX()
            int r2 = r6.getWidth()
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getX()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L75
            float r0 = r7.getY()
            int r2 = r6.getHeight()
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L75
            float r7 = r7.getY()
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 < 0) goto L75
            float r7 = r6.f83369Q2
            r0 = 1065353216(0x3f800000, float:1.0)
            float r7 = r7 + r0
            r6.f83369Q2 = r7
            boolean r7 = r6.f83364L2
            if (r7 != 0) goto Lc7
            android.view.View$OnClickListener r7 = r6.f83363K2
            if (r7 == 0) goto Lc7
            r7.onClick(r6)
            goto Lc7
        Lbf:
            int r7 = r7.getAction()
            r0 = 3
            if (r7 != r0) goto Lc7
            goto L75
        Lc7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Views.Button.onTouchEvent(android.view.MotionEvent):boolean");
    }

    protected abstract void setAttributes(AttributeSet attributeSet);

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.f83365M2 = i;
        if (isEnabled()) {
            this.f83405z2 = this.f83365M2;
        }
        try {
            ((GradientDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(C4804R.C4808id.f87015shape_bacground)).setColor(this.f83365M2);
            this.f83362J2 = Integer.valueOf(mo3343b());
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f83363K2 = onClickListener;
    }

    public void setRippleSpeed(float f) {
        this.f83360H2 = f;
    }

    public void setText(String str) {
        this.f83366N2.setText(str);
    }

    public void setTextColor(int i) {
        this.f83366N2.setTextColor(i);
    }
}
