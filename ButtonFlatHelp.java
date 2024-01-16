package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.itextpdf.tool.xml.css.CSS;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class ButtonFlatHelp extends Button {

    /* renamed from: S2 */
    TextView f83371S2;

    public ButtonFlatHelp(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83365M2 = Color.parseColor("#1d7021");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.Views.Button
    /* renamed from: b */
    public int mo3343b() {
        return Color.parseColor("#88DDDDDD");
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    /* renamed from: c */
    protected void mo3334c() {
        this.f83358F2 = 36;
        this.f83357E2 = 88;
        this.f83361I2 = 3;
        setMinimumHeight(C4846Utils.m3317a(36, getResources()));
        setMinimumWidth(C4846Utils.m3317a(this.f83357E2, getResources()));
        setBackgroundResource(C4804R.C4807drawable.f86497background_transparent);
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    public String getText() {
        return this.f83371S2.getText().toString();
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    public TextView getTextView() {
        return this.f83371S2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.Views.CustomView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f83367O2 != -1.0f) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(mo3343b());
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
            invalidate();
        }
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    protected void setAttributes(AttributeSet attributeSet) {
        int attributeIntValue;
        int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "text", -1);
        String string = attributeResourceValue != -1 ? getResources().getString(attributeResourceValue) : attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
        if (string != null) {
            TextView textView = new TextView(getContext());
            this.f83371S2 = textView;
            textView.setText(string.toUpperCase());
            this.f83371S2.setTextColor(this.f83365M2);
            this.f83371S2.setTypeface(null, 1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            this.f83371S2.setLayoutParams(layoutParams);
            addView(this.f83371S2);
        }
        int attributeResourceValue2 = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a, -1);
        if (attributeResourceValue2 != -1) {
            attributeIntValue = getResources().getColor(attributeResourceValue2);
        } else {
            attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a, -1);
            this.f83359G2 = attributeIntValue;
            if (attributeIntValue == -1) {
                return;
            }
        }
        setBackgroundColor(attributeIntValue);
    }

    @Override // net.imedicaldoctor.imd.Views.Button, android.view.View
    public void setBackgroundColor(int i) {
        this.f83365M2 = i;
        if (isEnabled()) {
            this.f83405z2 = this.f83365M2;
        }
        this.f83371S2.setTextColor(i);
    }

    @Override // net.imedicaldoctor.imd.Views.Button
    public void setText(String str) {
        this.f83371S2.setText(str.toUpperCase());
    }
}
