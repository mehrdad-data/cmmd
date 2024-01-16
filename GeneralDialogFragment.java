package net.imedicaldoctor.imd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import net.imedicaldoctor.imd.Data.CompressHelper;

/* loaded from: classes2.dex */
public class GeneralDialogFragment extends DialogFragment {

    /* renamed from: g4 */
    public View f77067g4;

    /* renamed from: h4 */
    public Fragment f77068h4;

    public GeneralDialogFragment() {
    }

    public GeneralDialogFragment(Fragment fragment) {
        this.f77068h4 = fragment;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.f77067g4 = layoutInflater.inflate(C4804R.C4810layout.f87141fragment_container_dialog, (ViewGroup) null);
        try {
            m44878T2().getWindow().requestFeature(1);
            m44878T2().getWindow().requestFeature(11);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        ((Button) this.f77067g4.findViewById(C4804R.C4808id.f86979open_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.GeneralDialogFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GeneralDialogFragment.this.mo27002R2();
                new CompressHelper(GeneralDialogFragment.this.m44716w()).m4883q1(GeneralDialogFragment.this.f77068h4.m44859B().getBundle("DB"), GeneralDialogFragment.this.f77068h4.m44859B().getString("URL"), null, null);
            }
        });
        ((Button) this.f77067g4.findViewById(C4804R.C4808id.f86846close_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.GeneralDialogFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GeneralDialogFragment.this.mo27002R2();
            }
        });
        this.f77067g4.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.GeneralDialogFragment.3
            @Override // java.lang.Runnable
            public void run() {
                GeneralDialogFragment.this.m44855C().m44464r().m44278y(C4804R.C4808id.f86903fragment_container, GeneralDialogFragment.this.f77068h4).mo44289n();
            }
        }, 50L);
        return this.f77067g4;
    }
}
