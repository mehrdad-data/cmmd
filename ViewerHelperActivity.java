package net.imedicaldoctor.imd.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ActionMode;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.File;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.iMDActivity;

/* loaded from: classes2.dex */
public class ViewerHelperActivity extends iMDActivity {

    /* renamed from: Q2 */
    private ActionMode f75824Q2 = null;

    /* renamed from: q0 */
    private void m4147q0() {
        try {
            getCurrentFocus().clearFocus();
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    @Override // net.imedicaldoctor.imd.iMDActivity
    /* renamed from: o0 */
    public int mo3301o0() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        if (actionMode == null) {
            actionMode = this.f75824Q2;
        }
        if (Build.VERSION.SDK_INT <= 22) {
            if (!new File(new CompressHelper(this).m4856z1() + "/action.txt").exists()) {
                return;
            }
        }
        actionMode.getMenu().clear();
        WebView webView = (WebView) findViewById(C4804R.C4808id.f87082webView);
        if (webView != null) {
            webView.loadUrl("javascript:console.log('finisham,,,,,');");
        }
        super.onActionModeFinished(actionMode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x003e, code lost:
        if (new java.io.File(new net.imedicaldoctor.imd.Data.CompressHelper(r6).m4856z1() + "/action.txt").exists() != false) goto L11;
     */
    @Override // android.app.Activity, android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActionModeStarted(android.view.ActionMode r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ACtionMode"
            java.lang.String r1 = "onActionModeStarted"
            net.imedicaldoctor.imd.iMDLogger.m3290j(r0, r1)
            r0 = 2131362619(0x7f0a033b, float:1.8345024E38)
            android.view.View r1 = r6.findViewById(r0)
            android.webkit.WebView r1 = (android.webkit.WebView) r1
            if (r1 != 0) goto L13
            return
        L13:
            r6.f75824Q2 = r7
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 22
            if (r2 > r3) goto L40
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            net.imedicaldoctor.imd.Data.CompressHelper r5 = new net.imedicaldoctor.imd.Data.CompressHelper
            r5.<init>(r6)
            java.lang.String r5 = r5.m4856z1()
            r4.append(r5)
            java.lang.String r5 = "/action.txt"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            boolean r3 = r3.exists()
            if (r3 == 0) goto L93
        L40:
            boolean r1 = r1.isFocused()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L4f
            return
        L4f:
            android.view.Menu r1 = r7.getMenu()
            r1.clear()
            r1 = 30
            if (r2 <= r1) goto L62
            r1 = 100
            net.imedicaldoctor.imd.Fragments.C4456a.m3948a(r7, r1)
            r7.finish()
        L62:
            android.view.View r0 = r6.findViewById(r0)
            android.webkit.WebView r0 = (android.webkit.WebView) r0
            if (r0 == 0) goto L93
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "javascript:getRect("
            r1.append(r2)
            int r2 = r0.getWidth()
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            int r2 = r0.getHeight()
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.loadUrl(r1)
        L93:
            super.onActionModeStarted(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.ViewerHelperActivity.onActionModeStarted(android.view.ActionMode):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("wakelock", true)) {
            getWindow().addFlags(128);
        }
    }
}
