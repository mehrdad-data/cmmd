package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.util.AttributeSet;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class GreenHighlightButton extends HighlightButton {
    public GreenHighlightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // net.imedicaldoctor.imd.Views.HighlightButton
    /* renamed from: h */
    public void mo3314h() {
        setBackgroundResource(C4804R.C4807drawable.f86492background_highlight_green);
        setRippleColor(getResources().getColor(C4804R.C4806color.f86119highlight_ripple_green));
    }
}
