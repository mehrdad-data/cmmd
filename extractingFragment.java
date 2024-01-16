package net.imedicaldoctor.imd;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class extractingFragment extends DialogFragment {

    /* renamed from: g4 */
    private int f83445g4 = 0;

    /* renamed from: l3 */
    static /* synthetic */ int m3310l3(extractingFragment extractingfragment, int i) {
        int i2 = extractingfragment.f83445g4 + i;
        extractingfragment.f83445g4 = i2;
        return i2;
    }

    @Override // androidx.fragment.app.DialogFragment
    /* renamed from: X2 */
    public Dialog mo3313X2(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m44716w());
        View inflate = m44716w().getLayoutInflater().inflate(C4804R.C4810layout.f87151fragment_extracting, (ViewGroup) null);
        m44716w().setFinishOnTouchOutside(false);
        final TextView textView = (TextView) inflate.findViewById(C4804R.C4808id.f87060title_text);
        final String string = m44859B().getString("MESSAGE");
        textView.setText(string);
        new Timer().schedule(new TimerTask() { // from class: net.imedicaldoctor.imd.extractingFragment.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                extractingFragment.m3310l3(extractingFragment.this, 1);
                final String str = "";
                for (int i = 0; i < extractingFragment.this.f83445g4; i++) {
                    str = str + ".";
                }
                textView.post(new Runnable() { // from class: net.imedicaldoctor.imd.extractingFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TextView textView2 = textView;
                        textView2.setText(string + str);
                    }
                });
                if (extractingFragment.this.f83445g4 >= 3) {
                    extractingFragment.this.f83445g4 = 0;
                }
            }
        }, 0L, 1000L);
        builder.setView(inflate);
        return builder.create();
    }
}
