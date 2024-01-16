package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nineoldandroids.animation.ObjectAnimator;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class ButtonFloat extends Button {

    /* renamed from: S2 */
    int f83372S2;

    /* renamed from: T2 */
    int f83373T2;

    /* renamed from: U2 */
    ImageView f83374U2;

    /* renamed from: V2 */
    Drawable f83375V2;

    /* renamed from: W2 */
    public boolean f83376W2;

    /* renamed from: X2 */
    float f83377X2;

    /* renamed from: Y2 */
    float f83378Y2;

    /* renamed from: Z2 */
    Integer f83379Z2;

    /* renamed from: a3 */
    Integer f83380a3;

    public ButtonFloat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83372S2 = 24;
        this.f83373T2 = 28;
        this.f83376W2 = false;
        setBackgroundResource(C4804R.C4807drawable.f86487background_button_float);
        setBackgroundColor(this.f83365M2);
        this.f83373T2 = 28;
        mo3334c();
        ImageView imageView = new ImageView(context);
        this.f83374U2 = imageView;
        imageView.setAdjustViewBounds(true);
        this.f83374U2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Drawable drawable = this.f83375V2;
        if (drawable != null) {
            this.f83374U2.setImageDrawable(drawable);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(C4846Utils.m3317a(this.f83372S2, getResources()), C4846Utils.m3317a(this.f83372S2, getResources()));
        layoutParams.addRule(13, -1);
        this.f83374U2.setLayoutParams(layoutParams);
        addView(this.f83374U2);
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    /* renamed from: c */
    protected void mo3334c() {
        this.f83360H2 = C4846Utils.m3317a(2.0f, getResources());
        this.f83361I2 = C4846Utils.m3317a(5.0f, getResources());
        setMinimumWidth(C4846Utils.m3317a(this.f83373T2 * 2, getResources()));
        setMinimumHeight(C4846Utils.m3317a(this.f83373T2 * 2, getResources()));
        this.f83359G2 = C4804R.C4807drawable.f86487background_button_float;
    }

    /* renamed from: d */
    public Bitmap m3342d(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    /* renamed from: e */
    public void m3341e() {
        ObjectAnimator m8976q1 = ObjectAnimator.m8976q1(this, "y", this.f83378Y2);
        m8976q1.mo8858o(new BounceInterpolator());
        m8976q1.mo8859n(1500L);
        m8976q1.mo8850y();
        this.f83376W2 = false;
    }

    /* renamed from: f */
    public boolean m3340f() {
        return this.f83376W2;
    }

    /* renamed from: g */
    public void m3339g() {
        ObjectAnimator m8976q1 = ObjectAnimator.m8976q1(this, "y", this.f83377X2);
        m8976q1.mo8858o(new BounceInterpolator());
        m8976q1.mo8859n(1500L);
        m8976q1.mo8850y();
        this.f83376W2 = true;
    }

    public Drawable getDrawableIcon() {
        return this.f83375V2;
    }

    public ImageView getIcon() {
        return this.f83374U2;
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    public TextView getTextView() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.Views.CustomView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f83367O2 != -1.0f) {
            canvas.drawBitmap(m3342d(m3344a()), new Rect(0, 0, getWidth(), getHeight()), new Rect(C4846Utils.m3317a(1.0f, getResources()), C4846Utils.m3317a(2.0f, getResources()), getWidth() - C4846Utils.m3317a(1.0f, getResources()), getHeight() - C4846Utils.m3317a(2.0f, getResources())), (Paint) null);
            invalidate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r0 != (-1)) goto L4;
     */
    @Override // net.imedicaldoctor.imd.Views.Button
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void setAttributes(android.util.AttributeSet r5) {
        /*
            r4 = this;
            java.lang.String r0 = "http://schemas.android.com/apk/res/android"
            java.lang.String r1 = "background"
            r2 = -1
            int r3 = r5.getAttributeResourceValue(r0, r1, r2)
            if (r3 == r2) goto L17
            android.content.res.Resources r0 = r4.getResources()
            int r0 = r0.getColor(r3)
        L13:
            r4.setBackgroundColor(r0)
            goto L20
        L17:
            int r0 = r5.getAttributeIntValue(r0, r1, r2)
            r4.f83359G2 = r0
            if (r0 == r2) goto L20
            goto L13
        L20:
            java.lang.String r0 = "http://schemas.android.com/apk/res-auto"
            java.lang.String r1 = "rippleColor"
            int r3 = r5.getAttributeResourceValue(r0, r1, r2)
            if (r3 == r2) goto L36
            android.content.res.Resources r1 = r4.getResources()
            int r1 = r1.getColor(r3)
        L32:
            r4.setRippleColor(r1)
            goto L42
        L36:
            int r1 = r5.getAttributeIntValue(r0, r1, r2)
            if (r1 == r2) goto L3d
            goto L32
        L3d:
            int r1 = r4.mo3343b()
            goto L32
        L42:
            java.lang.String r1 = "iconDrawable"
            int r1 = r5.getAttributeResourceValue(r0, r1, r2)
            if (r1 == r2) goto L54
            android.content.res.Resources r2 = r4.getResources()
            android.graphics.drawable.Drawable r1 = r2.getDrawable(r1)
            r4.f83375V2 = r1
        L54:
            java.lang.String r1 = "animate"
            r2 = 0
            boolean r5 = r5.getAttributeBooleanValue(r0, r1, r2)
            net.imedicaldoctor.imd.Views.ButtonFloat$1 r0 = new net.imedicaldoctor.imd.Views.ButtonFloat$1
            r0.<init>()
            r4.post(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Views.ButtonFloat.setAttributes(android.util.AttributeSet):void");
    }

    public void setDrawableIcon(Drawable drawable) {
        this.f83375V2 = drawable;
        try {
            this.f83374U2.setBackground(drawable);
        } catch (NoSuchMethodError unused) {
            this.f83374U2.setBackgroundDrawable(drawable);
        }
    }

    public void setIcon(ImageView imageView) {
        this.f83374U2 = imageView;
    }

    public void setRippleColor(int i) {
        this.f83362J2 = Integer.valueOf(i);
    }
}
