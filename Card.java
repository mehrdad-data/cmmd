package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.itextpdf.tool.xml.css.CSS;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class Card extends CustomView {

    /* renamed from: E2 */
    TextView f83394E2;

    /* renamed from: F2 */
    int f83395F2;

    /* renamed from: G2 */
    int f83396G2;

    /* renamed from: H2 */
    int f83397H2;

    /* renamed from: I2 */
    int f83398I2;

    /* renamed from: J2 */
    int f83399J2;

    public Card(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83399J2 = Color.parseColor("#FFFFFF");
        setAttributes(attributeSet);
    }

    protected void setAttributes(AttributeSet attributeSet) {
        int parseColor;
        setBackgroundResource(C4804R.C4807drawable.f86490background_button_rectangle);
        int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a, -1);
        if (attributeResourceValue != -1) {
            parseColor = getResources().getColor(attributeResourceValue);
        } else {
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", CSS.Property.f65539a);
            parseColor = attributeValue != null ? Color.parseColor(attributeValue) : this.f83399J2;
        }
        setBackgroundColor(parseColor);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.f83399J2 = i;
        if (isEnabled()) {
            this.f83405z2 = this.f83399J2;
        }
        ((GradientDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(C4804R.C4808id.f87015shape_bacground)).setColor(this.f83399J2);
    }
}
