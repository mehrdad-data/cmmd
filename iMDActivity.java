package net.imedicaldoctor.imd;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.itextpdf.text.p014io.PagedChannelRandomAccessSource;

/* loaded from: classes2.dex */
public class iMDActivity extends AppCompatActivity {
    /* renamed from: n0 */
    public void m3302n0(Fragment fragment, Bundle bundle) {
        fragment.m44751k2(getIntent().getExtras());
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("HideStatusBar", false)) {
            getWindow().setFlags(PagedChannelRandomAccessSource.f60487g, PagedChannelRandomAccessSource.f60487g);
            View findViewById = findViewById(C4804R.C4808id.f86865detail_container);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.setMargins(0, -mo3301o0(), 0, 0);
            findViewById.setLayoutParams(layoutParams);
        }
        m52861V().mo52805O(false);
        m44690E().m44464r().m44278y(C4804R.C4808id.f86865detail_container, fragment).mo44289n();
    }

    /* renamed from: o0 */
    public int mo3301o0() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
        overridePendingTransition(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark", false)) {
            AppCompatDelegate.m52839N(2);
        } else {
            AppCompatDelegate.m52839N(1);
        }
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("wakelock", true)) {
            getWindow().addFlags(128);
        }
    }

    /* renamed from: p0 */
    public void m3300p0(Bundle bundle, Fragment fragment) {
        setContentView(C4804R.C4810layout.f87097activity_general_list);
        if (bundle == null) {
            fragment.m44751k2(getIntent().getExtras());
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("HideStatusBar", false)) {
                getWindow().setFlags(PagedChannelRandomAccessSource.f60487g, PagedChannelRandomAccessSource.f60487g);
                View findViewById = findViewById(C4804R.C4808id.container);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                layoutParams.setMargins(0, -mo3301o0(), 0, 0);
                findViewById.setLayoutParams(layoutParams);
            }
            m44690E().m44464r().m44301b(C4804R.C4808id.container, fragment).mo44289n();
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(C4804R.anim.f85970from_fade_in, C4804R.anim.f85971from_fade_out);
    }
}
