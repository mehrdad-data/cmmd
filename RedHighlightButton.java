package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.util.AttributeSet;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class RedHighlightButton extends HighlightButton {
    public RedHighlightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // net.imedicaldoctor.imd.Views.HighlightButton
    /* renamed from: h */
    public void mo3314h() {
        setBackgroundResource(C4804R.C4807drawable.f86493background_highlight_red);
        setRippleColor(getResources().getColor(C4804R.C4806color.f86120highlight_ripple_red));
    }
}
