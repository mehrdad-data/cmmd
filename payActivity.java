package net.imedicaldoctor.imd.Fragments;

import android.os.Bundle;
import android.view.MenuItem;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.iMDActivity;

/* loaded from: classes2.dex */
public class payActivity extends iMDActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m3300p0(bundle, new payActivityFragment());
        setContentView(C4804R.C4810layout.f87107activity_pay);
        if (bundle == null) {
            payActivityFragment payactivityfragment = new payActivityFragment();
            payactivityfragment.m44751k2(getIntent().getExtras());
            m44690E().m44464r().m44292k("something").m44301b(C4804R.C4808id.container, payactivityfragment).mo44289n();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
