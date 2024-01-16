package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.itextpdf.tool.xml.css.CSS;

/* loaded from: classes2.dex */
public class LayoutRipple extends CustomView {

    /* renamed from: E2 */
    int f83406E2;

    /* renamed from: F2 */
    float f83407F2;

    /* renamed from: G2 */
    int f83408G2;

    /* renamed from: H2 */
    View.OnClickListener f83409H2;

    /* renamed from: I2 */
    int f83410I2;

    /* renamed from: J2 */
    Integer f83411J2;

    /* renamed from: K2 */
    Float f83412K2;

    /* renamed from: L2 */
    Float f83413L2;

    /* renamed from: M2 */
    float f83414M2;

    /* renamed from: N2 */
    float f83415N2;

    /* renamed from: O2 */
    float f83416O2;

    public LayoutRipple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83407F2 = 10.0f;
        this.f83408G2 = 3;
        this.f83410I2 = Color.parseColor("#FFFFFF");
        this.f83414M2 = -1.0f;
        this.f83415N2 = -1.0f;
        this.f83416O2 = -1.0f;
        setAttributes(attributeSet);
    }

    /* renamed from: a */
    public Bitmap m3333a() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (this.f83411J2 == null) {
            this.f83411J2 = Integer.valueOf(m3332b());
        }
        paint.setColor(this.f83411J2.intValue());
        Float f = this.f83412K2;
        this.f83414M2 = f == null ? this.f83414M2 : f.floatValue();
        Float f2 = this.f83413L2;
        float floatValue = f2 == null ? this.f83415N2 : f2.floatValue();
        this.f83415N2 = floatValue;
        canvas.drawCircle(this.f83414M2, floatValue, this.f83416O2, paint);
        if (this.f83416O2 > getHeight() / this.f83408G2) {
            this.f83416O2 += this.f83407F2;
        }
        if (this.f83416O2 >= getWidth()) {
            this.f83414M2 = -1.0f;
            this.f83415N2 = -1.0f;
            this.f83416O2 = getHeight() / this.f83408G2;
            View.OnClickListener onClickListener = this.f83409H2;
            if (onClickListener != null) {
                onClickListener.onClick(this);
            }
        }
        return createBitmap;
    }

    /* renamed from: b */
    protected int m3332b() {
        int i = this.f83410I2;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.Views.CustomView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f83414M2 != -1.0f) {
            canvas.drawBitmap(m3333a(), new Rect(0, 0, getWidth(), getHeight()), new Rect(0, 0, getWidth(), getHeight()), (Paint) null);
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            return;
        }
        this.f83414M2 = -1.0f;
        this.f83415N2 = -1.0f;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0073, code lost:
        if (r7.getY() >= 0.0f) goto L6;
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
            if (r0 == 0) goto Lc0
            r6.f83402A2 = r1
            int r0 = r7.getAction()
            r2 = 0
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 != 0) goto L2d
            int r0 = r6.getHeight()
            int r4 = r6.f83408G2
            int r0 = r0 / r4
            float r0 = (float) r0
            r6.f83416O2 = r0
            float r0 = r7.getX()
            r6.f83414M2 = r0
            float r0 = r7.getY()
            r6.f83415N2 = r0
            goto Lb3
        L2d:
            int r0 = r7.getAction()
            r4 = 2
            r5 = 0
            if (r0 != r4) goto L7c
            int r0 = r6.getHeight()
            int r4 = r6.f83408G2
            int r0 = r0 / r4
            float r0 = (float) r0
            r6.f83416O2 = r0
            float r0 = r7.getX()
            r6.f83414M2 = r0
            float r0 = r7.getY()
            r6.f83415N2 = r0
            float r0 = r7.getX()
            int r4 = r6.getWidth()
            float r4 = (float) r4
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getX()
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 < 0) goto L75
            float r0 = r7.getY()
            int r4 = r6.getHeight()
            float r4 = (float) r4
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getY()
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto Lb3
        L75:
            r6.f83402A2 = r2
            r6.f83414M2 = r3
            r6.f83415N2 = r3
            goto Lb3
        L7c:
            int r0 = r7.getAction()
            if (r0 != r1) goto Lb3
            float r0 = r7.getX()
            int r4 = r6.getWidth()
            float r4 = (float) r4
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getX()
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 < 0) goto L75
            float r0 = r7.getY()
            int r4 = r6.getHeight()
            float r4 = (float) r4
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L75
            float r0 = r7.getY()
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 < 0) goto L75
            float r0 = r6.f83416O2
            r4 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 + r4
            r6.f83416O2 = r0
        Lb3:
            int r7 = r7.getAction()
            r0 = 3
            if (r7 != r0) goto Lc0
            r6.f83402A2 = r2
            r6.f83414M2 = r3
            r6.f83415N2 = r3
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Views.LayoutRipple.onTouchEvent(android.view.MotionEvent):boolean");
    }

    protected void setAttributes(AttributeSet attributeSet) {
        int attributeIntValue;
        int attributeIntValue2;
        int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a, -1);
        if (attributeResourceValue != -1) {
            attributeIntValue = getResources().getColor(attributeResourceValue);
        } else {
            attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a, -1);
            this.f83406E2 = attributeIntValue;
            if (attributeIntValue == -1) {
                attributeIntValue = this.f83410I2;
            }
        }
        setBackgroundColor(attributeIntValue);
        int attributeResourceValue2 = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "rippleColor", -1);
        if (attributeResourceValue2 != -1) {
            attributeIntValue2 = getResources().getColor(attributeResourceValue2);
        } else {
            attributeIntValue2 = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "rippleColor", -1);
            if (attributeIntValue2 == -1) {
                attributeIntValue2 = m3332b();
            }
        }
        setRippleColor(attributeIntValue2);
        this.f83407F2 = attributeSet.getAttributeFloatValue("http://schemas.android.com/apk/res-auto", "rippleSpeed", 20.0f);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.f83410I2 = i;
        if (isEnabled()) {
            this.f83405z2 = this.f83410I2;
        }
        super.setBackgroundColor(i);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f83409H2 = onClickListener;
    }

    public void setRippleColor(int i) {
        this.f83411J2 = Integer.valueOf(i);
    }

    public void setRippleSpeed(int i) {
        this.f83407F2 = i;
    }

    public void setxRippleOrigin(Float f) {
        this.f83412K2 = f;
    }

    public void setyRippleOrigin(Float f) {
        this.f83413L2 = f;
    }
}
