package net.imedicaldoctor.imd;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class AutoResizeTextView extends TextView {

    /* renamed from: K2 */
    private static final int f73619K2 = -1;

    /* renamed from: A2 */
    private final SizeTester f73620A2;

    /* renamed from: B2 */
    private float f73621B2;

    /* renamed from: C2 */
    private float f73622C2;

    /* renamed from: D2 */
    private float f73623D2;

    /* renamed from: E2 */
    private float f73624E2;

    /* renamed from: F2 */
    private int f73625F2;

    /* renamed from: G2 */
    private int f73626G2;

    /* renamed from: H2 */
    private boolean f73627H2;

    /* renamed from: I2 */
    private boolean f73628I2;

    /* renamed from: J2 */
    private TextPaint f73629J2;

    /* renamed from: s */
    private final RectF f73630s;

    /* renamed from: z2 */
    private final SparseIntArray f73631z2;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface SizeTester {
        /* renamed from: a */
        int mo5162a(int i, RectF rectF);
    }

    public AutoResizeTextView(Context context) {
        this(context, null, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f73630s = new RectF();
        this.f73631z2 = new SparseIntArray();
        this.f73622C2 = 1.0f;
        this.f73623D2 = 0.0f;
        this.f73627H2 = true;
        this.f73628I2 = false;
        this.f73624E2 = TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics());
        this.f73621B2 = getTextSize();
        if (this.f73626G2 == 0) {
            this.f73626G2 = -1;
        }
        this.f73620A2 = new SizeTester() { // from class: net.imedicaldoctor.imd.AutoResizeTextView.1

            /* renamed from: a */
            final RectF f73632a = new RectF();

            @Override // net.imedicaldoctor.imd.AutoResizeTextView.SizeTester
            @TargetApi(16)
            /* renamed from: a */
            public int mo5162a(int i2, RectF rectF) {
                RectF rectF2;
                float f;
                AutoResizeTextView.this.f73629J2.setTextSize(i2);
                String charSequence = AutoResizeTextView.this.getText().toString();
                if (AutoResizeTextView.this.getMaxLines() == 1) {
                    this.f73632a.bottom = AutoResizeTextView.this.f73629J2.getFontSpacing();
                    rectF2 = this.f73632a;
                    f = AutoResizeTextView.this.f73629J2.measureText(charSequence);
                } else {
                    StaticLayout staticLayout = new StaticLayout(charSequence, AutoResizeTextView.this.f73629J2, AutoResizeTextView.this.f73625F2, Layout.Alignment.ALIGN_NORMAL, AutoResizeTextView.this.f73622C2, AutoResizeTextView.this.f73623D2, true);
                    if (AutoResizeTextView.this.getMaxLines() != -1 && staticLayout.getLineCount() > AutoResizeTextView.this.getMaxLines()) {
                        return 1;
                    }
                    this.f73632a.bottom = staticLayout.getHeight();
                    int i3 = -1;
                    for (int i4 = 0; i4 < staticLayout.getLineCount(); i4++) {
                        if (i3 < staticLayout.getLineRight(i4) - staticLayout.getLineLeft(i4)) {
                            i3 = ((int) staticLayout.getLineRight(i4)) - ((int) staticLayout.getLineLeft(i4));
                        }
                    }
                    rectF2 = this.f73632a;
                    f = i3;
                }
                rectF2.right = f;
                this.f73632a.offsetTo(0.0f, 0.0f);
                return rectF.contains(this.f73632a) ? -1 : 1;
            }
        };
        this.f73628I2 = true;
    }

    /* renamed from: e */
    private void m5167e() {
        if (this.f73628I2) {
            int i = (int) this.f73624E2;
            int measuredHeight = (getMeasuredHeight() - getCompoundPaddingBottom()) - getCompoundPaddingTop();
            int measuredWidth = (getMeasuredWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
            this.f73625F2 = measuredWidth;
            if (measuredWidth <= 0) {
                return;
            }
            RectF rectF = this.f73630s;
            rectF.right = measuredWidth;
            rectF.bottom = measuredHeight;
            m5163i(i);
        }
    }

    /* renamed from: f */
    private int m5166f(int i, int i2, SizeTester sizeTester, RectF rectF) {
        int i3 = i2 - 1;
        int i4 = i;
        while (i <= i3) {
            i4 = (i + i3) >>> 1;
            int mo5162a = sizeTester.mo5162a(i4, rectF);
            if (mo5162a >= 0) {
                if (mo5162a <= 0) {
                    break;
                }
                i4--;
                i3 = i4;
            } else {
                int i5 = i4 + 1;
                i4 = i;
                i = i5;
            }
        }
        return i4;
    }

    /* renamed from: g */
    private int m5165g(int i, int i2, SizeTester sizeTester, RectF rectF) {
        if (this.f73627H2) {
            String charSequence = getText().toString();
            int length = charSequence == null ? 0 : charSequence.length();
            int i3 = this.f73631z2.get(length);
            if (i3 != 0) {
                return i3;
            }
            int m5166f = m5166f(i, i2, sizeTester, rectF);
            this.f73631z2.put(length, m5166f);
            return m5166f;
        }
        return m5166f(i, i2, sizeTester, rectF);
    }

    /* renamed from: h */
    private void m5164h() {
        m5167e();
    }

    /* renamed from: i */
    private void m5163i(int i) {
        super.setTextSize(0, m5165g(i, (int) this.f73621B2, this.f73620A2, this.f73630s));
    }

    @Override // android.widget.TextView
    public int getMaxLines() {
        return this.f73626G2;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f73631z2.clear();
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        m5164h();
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        m5164h();
    }

    public void setEnableSizeCache(boolean z) {
        this.f73627H2 = z;
        this.f73631z2.clear();
        m5167e();
    }

    @Override // android.widget.TextView
    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.f73622C2 = f2;
        this.f73623D2 = f;
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        super.setLines(i);
        this.f73626G2 = i;
        m5164h();
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.f73626G2 = i;
        m5164h();
    }

    public void setMinTextSize(float f) {
        this.f73624E2 = f;
        m5164h();
    }

    @Override // android.widget.TextView
    public void setSingleLine() {
        super.setSingleLine();
        this.f73626G2 = 1;
        m5164h();
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
        this.f73626G2 = z ? 1 : -1;
        m5164h();
    }

    @Override // android.widget.TextView
    public void setTextSize(float f) {
        this.f73621B2 = f;
        this.f73631z2.clear();
        m5167e();
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        Context context = getContext();
        this.f73621B2 = TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        this.f73631z2.clear();
        m5167e();
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface) {
        if (this.f73629J2 == null) {
            this.f73629J2 = new TextPaint(getPaint());
        }
        this.f73629J2.setTypeface(typeface);
        m5167e();
        super.setTypeface(typeface);
    }
}
