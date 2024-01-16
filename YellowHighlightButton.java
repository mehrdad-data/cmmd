package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.util.AttributeSet;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class YellowHighlightButton extends HighlightButton {
    public YellowHighlightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // net.imedicaldoctor.imd.Views.HighlightButton
    /* renamed from: h */
    public void mo3314h() {
        setBackgroundResource(C4804R.C4807drawable.f86495background_highlight_yellow);
        setRippleColor(getResources().getColor(C4804R.C4806color.f86122highlight_ripple_yellow));
    }
}
