package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import com.itextpdf.tool.xml.css.CSS;

/* loaded from: classes2.dex */
public class ProgressBarCircularIndeterminate extends CustomView {

    /* renamed from: N2 */
    static final String f83417N2 = "http://schemas.android.com/apk/res/android";

    /* renamed from: E2 */
    int f83418E2;

    /* renamed from: F2 */
    float f83419F2;

    /* renamed from: G2 */
    float f83420G2;

    /* renamed from: H2 */
    int f83421H2;

    /* renamed from: I2 */
    boolean f83422I2;

    /* renamed from: J2 */
    int f83423J2;

    /* renamed from: K2 */
    int f83424K2;

    /* renamed from: L2 */
    float f83425L2;

    /* renamed from: M2 */
    int f83426M2;

    public ProgressBarCircularIndeterminate(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83418E2 = Color.parseColor("#1E88E5");
        this.f83419F2 = 0.0f;
        this.f83420G2 = 0.0f;
        this.f83421H2 = 0;
        this.f83422I2 = false;
        this.f83423J2 = 1;
        this.f83424K2 = 0;
        this.f83425L2 = 0.0f;
        this.f83426M2 = 0;
        setAttributes(attributeSet);
    }

    /* renamed from: a */
    private void m3331a(Canvas canvas) {
        float width;
        if (this.f83419F2 < getWidth() / 2) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(m3329c());
            this.f83419F2 = this.f83419F2 >= ((float) (getWidth() / 2)) ? getWidth() / 2.0f : this.f83419F2 + 1.0f;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.f83419F2, paint);
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(m3329c());
        canvas2.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint2);
        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setColor(getResources().getColor(17170445));
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        if (this.f83421H2 >= 50) {
            if (this.f83420G2 >= getWidth() / 2) {
                width = getWidth() / 2.0f;
            }
            width = 1.0f + this.f83420G2;
        } else {
            if (this.f83420G2 >= (getWidth() / 2) - C4846Utils.m3317a(4.0f, getResources())) {
                width = (getWidth() / 2.0f) - C4846Utils.m3317a(4.0f, getResources());
            }
            width = 1.0f + this.f83420G2;
        }
        this.f83420G2 = width;
        canvas2.drawCircle(getWidth() / 2, getHeight() / 2, this.f83420G2, paint3);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
        if (this.f83420G2 >= (getWidth() / 2) - C4846Utils.m3317a(4.0f, getResources())) {
            this.f83421H2++;
        }
        if (this.f83420G2 >= getWidth() / 2) {
            this.f83422I2 = true;
        }
    }

    /* renamed from: b */
    private void m3330b(Canvas canvas) {
        int i = this.f83424K2;
        int i2 = this.f83426M2;
        if (i == i2) {
            this.f83423J2 += 6;
        }
        int i3 = this.f83423J2;
        if (i3 >= 290 || i > i2) {
            this.f83424K2 = i + 6;
            this.f83423J2 = i3 - 6;
        }
        int i4 = this.f83424K2;
        if (i4 > i2 + TIFFConstants.f63973G0) {
            this.f83426M2 = i4;
            this.f83424K2 = i4;
            this.f83423J2 = 1;
        }
        float f = this.f83425L2 + 4.0f;
        this.f83425L2 = f;
        canvas.rotate(f, getWidth() / 2, getHeight() / 2);
        Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.f83418E2);
        canvas2.drawArc(new RectF(0.0f, 0.0f, getWidth(), getHeight()), this.f83424K2, this.f83423J2, true, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(getResources().getColor(17170445));
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas2.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - C4846Utils.m3317a(4.0f, getResources()), paint2);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
    }

    /* renamed from: c */
    protected int m3329c() {
        int i = this.f83418E2;
        return Color.argb(128, (i >> 16) & 255, (i >> 8) & 255, (i >> 0) & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.Views.CustomView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f83422I2) {
            m3331a(canvas);
        }
        if (this.f83421H2 > 0) {
            m3330b(canvas);
        }
        invalidate();
    }

    protected void setAttributes(AttributeSet attributeSet) {
        int attributeIntValue;
        setMinimumHeight(C4846Utils.m3317a(32.0f, getResources()));
        setMinimumWidth(C4846Utils.m3317a(32.0f, getResources()));
        int attributeResourceValue = attributeSet.getAttributeResourceValue(f83417N2, CSS.Property.f65539a, -1);
        if (attributeResourceValue != -1) {
            attributeIntValue = getResources().getColor(attributeResourceValue);
        } else {
            attributeIntValue = attributeSet.getAttributeIntValue(f83417N2, CSS.Property.f65539a, -1);
            if (attributeIntValue == -1) {
                attributeIntValue = Color.parseColor("#1E88E5");
            }
        }
        setBackgroundColor(attributeIntValue);
        setMinimumHeight(C4846Utils.m3317a(3.0f, getResources()));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(getResources().getColor(17170445));
        if (isEnabled()) {
            this.f83405z2 = this.f83418E2;
        }
        this.f83418E2 = i;
    }
}
