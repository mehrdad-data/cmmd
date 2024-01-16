package net.imedicaldoctor.imd.Utils;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.webkit.WebBackForwardList;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.tool.xml.css.CSS;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.NestedScrollWebView;
import net.imedicaldoctor.imd.iMDLogger;

/* loaded from: classes2.dex */
public class iMDWebView extends NestedScrollWebView {

    /* renamed from: E2 */
    public int f83194E2;

    /* renamed from: F2 */
    public int f83195F2;

    /* renamed from: G2 */
    public ScaleGestureDetector f83196G2;

    /* renamed from: H2 */
    public GestureDetector f83197H2;

    /* renamed from: I2 */
    public ActionModeResponse f83198I2;

    /* renamed from: J2 */
    final int f83199J2;

    /* renamed from: K2 */
    public Context f83200K2;

    /* renamed from: L2 */
    public String f83201L2;

    /* renamed from: M2 */
    private ActionMode f83202M2;

    /* renamed from: N2 */
    private ActionMode.Callback f83203N2;

    /* renamed from: O2 */
    private ActionMode.Callback f83204O2;

    public iMDWebView(Context context) {
        super(context);
        this.f83199J2 = 15;
    }

    public iMDWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f83199J2 = 15;
        this.f83200K2 = context;
        this.f83197H2 = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: net.imedicaldoctor.imd.Utils.iMDWebView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }
        });
        this.f83196G2 = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: net.imedicaldoctor.imd.Utils.iMDWebView.2
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                iMDLogger.m3294f("iMDScale", scaleGestureDetector.getScaleFactor() + "");
                if (scaleGestureDetector.getScaleFactor() > 1.2d) {
                    iMDWebView.this.m3437h();
                } else if (scaleGestureDetector.getScaleFactor() < 0.8d) {
                    iMDWebView.this.m3436i();
                }
            }
        });
    }

    public iMDWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f83199J2 = 15;
    }

    /* renamed from: h */
    public void m3437h() {
        Context context;
        String str;
        int i;
        iMDLogger.m3296d("UTDWebView", "Zoom in : " + (getSettings().getTextZoom() + 15));
        if (getSettings().getTextZoom() >= 300) {
            context = this.f83200K2;
            str = "Can't Zoom In Anymore";
            i = 1;
        } else {
            m3433l();
            getSettings().setTextZoom(getSettings().getTextZoom() + 15);
            m3434k();
            PreferenceManager.getDefaultSharedPreferences(this.f83200K2).edit().putInt(this.f83201L2 + "zoom", getSettings().getTextZoom()).commit();
            context = this.f83200K2;
            str = getSettings().getTextZoom() + CSS.Value.f65657n0;
            i = 0;
        }
        CompressHelper.m4921e2(context, str, i);
    }

    /* renamed from: i */
    public void m3436i() {
        Context context;
        String str;
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append("Zoom Out : ");
        sb.append(getSettings().getTextZoom() - 15);
        iMDLogger.m3296d("UTDWebView", sb.toString());
        if (getSettings().getTextZoom() <= 25) {
            context = this.f83200K2;
            str = "Can't Zoom Out Anymore";
            i = 1;
        } else {
            m3433l();
            getSettings().setTextZoom(getSettings().getTextZoom() - 15);
            m3434k();
            PreferenceManager.getDefaultSharedPreferences(this.f83200K2).edit().putInt(this.f83201L2 + "zoom", getSettings().getTextZoom()).commit();
            context = this.f83200K2;
            str = getSettings().getTextZoom() + CSS.Value.f65657n0;
            i = 0;
        }
        CompressHelper.m4921e2(context, str, i);
    }

    @Override // android.webkit.WebView
    public void invokeZoomPicker() {
        iMDLogger.m3296d("UTDWebView", "Invoke zoom picker");
        super.invokeZoomPicker();
    }

    /* renamed from: j */
    public void m3435j() {
        ActionMode actionMode = this.f83202M2;
        if (actionMode == null) {
            return;
        }
        actionMode.finish();
    }

    /* renamed from: k */
    public void m3434k() {
        loadUrl("javascript:document.getElementById('orientation').scrollIntoView(true);");
        loadUrl("javascript:element=document.getElementById('orientation');element.parentNode.removeChild(element);");
    }

    /* renamed from: l */
    public void m3433l() {
        loadUrl("javascript:e = document.createElement('span'); e.setAttribute('id','orientation');document.caretRangeFromPoint(0, 0).insertNode(e);");
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.f83194E2 = i;
        this.f83195F2 = i2;
    }

    @Override // net.imedicaldoctor.imd.NestedScrollWebView, android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.f83197H2.onTouchEvent(motionEvent);
            this.f83196G2.onTouchEvent(motionEvent);
            return super.onTouchEvent(motionEvent);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            return true;
        }
    }

    @Override // android.webkit.WebView
    public WebBackForwardList saveState(Bundle bundle) {
        iMDLogger.m3296d("UTDWebView", "SaveState");
        return super.saveState(bundle);
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionMode actionMode = new ActionMode() { // from class: net.imedicaldoctor.imd.Utils.iMDWebView.3
            @Override // android.view.ActionMode
            public void finish() {
                ActionModeResponse actionModeResponse = iMDWebView.this.f83198I2;
                if (actionModeResponse != null) {
                    actionModeResponse.mo3481a();
                    iMDWebView.this.clearFocus();
                }
            }

            @Override // android.view.ActionMode
            public View getCustomView() {
                return null;
            }

            @Override // android.view.ActionMode
            public Menu getMenu() {
                return null;
            }

            @Override // android.view.ActionMode
            public MenuInflater getMenuInflater() {
                return null;
            }

            @Override // android.view.ActionMode
            public CharSequence getSubtitle() {
                return null;
            }

            @Override // android.view.ActionMode
            public CharSequence getTitle() {
                return null;
            }

            @Override // android.view.ActionMode
            public void invalidate() {
            }

            @Override // android.view.ActionMode
            public void setCustomView(View view) {
            }

            @Override // android.view.ActionMode
            public void setSubtitle(int i) {
            }

            @Override // android.view.ActionMode
            public void setSubtitle(CharSequence charSequence) {
            }

            @Override // android.view.ActionMode
            public void setTitle(int i) {
            }

            @Override // android.view.ActionMode
            public void setTitle(CharSequence charSequence) {
            }
        };
        ActionModeResponse actionModeResponse = this.f83198I2;
        if (actionModeResponse != null) {
            actionModeResponse.mo3480b();
        }
        this.f83202M2 = actionMode;
        return actionMode;
    }

    @Override // android.webkit.WebView
    public boolean zoomIn() {
        iMDLogger.m3296d("UTDWebView", "ZoomIn");
        return super.zoomIn();
    }
}
