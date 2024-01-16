package net.imedicaldoctor.imd.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class ButtonFloatSmall extends ButtonFloat {
    public ButtonFloatSmall(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83373T2 = 20;
        this.f83372S2 = 20;
        mo3334c();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(C4846Utils.m3317a(this.f83372S2, getResources()), C4846Utils.m3317a(this.f83372S2, getResources()));
        layoutParams.addRule(13, -1);
        this.f83374U2.setLayoutParams(layoutParams);
    }

    @Override // net.imedicaldoctor.imd.Views.ButtonFloat, net.imedicaldoctor.imd.Views.Button
    /* renamed from: c */
    protected void mo3334c() {
        this.f83360H2 = C4846Utils.m3317a(2.0f, getResources());
        this.f83361I2 = 10;
        setMinimumHeight(C4846Utils.m3317a(this.f83373T2 * 2, getResources()));
        setMinimumWidth(C4846Utils.m3317a(this.f83373T2 * 2, getResources()));
        setBackgroundResource(C4804R.C4807drawable.f86487background_button_float);
    }
}
