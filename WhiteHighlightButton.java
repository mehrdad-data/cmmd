package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.util.AttributeSet;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class WhiteHighlightButton extends HighlightButton {
    public WhiteHighlightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // net.imedicaldoctor.imd.Views.HighlightButton
    /* renamed from: h */
    public void mo3314h() {
        setBackgroundResource(C4804R.C4807drawable.f86494background_highlight_white);
        setRippleColor(getResources().getColor(C4804R.C4806color.f86121highlight_ripple_white));
    }
}
