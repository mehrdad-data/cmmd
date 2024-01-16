package net.imedicaldoctor.imd.Fragments;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.iMDActivity;

/* loaded from: classes2.dex */
public class fabActivity extends iMDActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4804R.C4810layout.f87095activity_fab);
        m52851e0((Toolbar) findViewById(C4804R.C4808id.f87063toolbar));
        ((FloatingActionButton) findViewById(C4804R.C4808id.f86891fab)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.fabActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Snackbar.m25000s0(view, "Replace with your own action", 0).m24997v0("Action", null).mo25006f0();
            }
        });
    }
}
