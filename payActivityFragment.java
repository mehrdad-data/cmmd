package net.imedicaldoctor.imd.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.exoplayer2.extractor.p010ts.TsExtractor;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Map;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.Views.ProgressBarCircularIndeterminate;
import net.imedicaldoctor.imd.iMDLogger;

/* loaded from: classes2.dex */
public class payActivityFragment extends Fragment {

    /* renamed from: F3 */
    private View f76960F3;

    /* renamed from: G3 */
    private WebView f76961G3;

    /* renamed from: H3 */
    private boolean f76962H3;

    /* renamed from: I3 */
    private boolean f76963I3;

    /* renamed from: J3 */
    private WebResourceRequest f76964J3;

    /* renamed from: K3 */
    private ProgressBarCircularIndeterminate f76965K3;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R2 */
    public void m3557R2() {
        this.f76965K3.setVisibility(8);
    }

    /* renamed from: T2 */
    private void m3555T2() {
        this.f76965K3.setBackgroundColor(Color.parseColor("#1e88e5"));
        this.f76965K3.setVisibility(0);
    }

    /* renamed from: S2 */
    public WebResourceResponse m3556S2(WebView webView, WebResourceRequest webResourceRequest) {
        Map<String, String> requestHeaders;
        String uri = webResourceRequest.getUrl().toString();
        if (this.f76962H3) {
            return null;
        }
        try {
            requestHeaders = webResourceRequest.getRequestHeaders();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Exeption", e.getLocalizedMessage());
        }
        if (requestHeaders.get(HttpHeaders.f53964c) == null) {
            return null;
        }
        if (requestHeaders.get(HttpHeaders.f53964c).contains("x-www-form-urlencoded") && requestHeaders.containsKey(HttpHeaders.f53914E) && requestHeaders.get(HttpHeaders.f53914E).contains("shaparak")) {
            this.f76962H3 = true;
            this.f76963I3 = false;
            this.f76964J3 = webResourceRequest;
            m44716w().runOnUiThread(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    VBHelper vBHelper = new VBHelper(payActivityFragment.this.m44716w());
                    WebView webView2 = payActivityFragment.this.f76961G3;
                    webView2.loadUrl("javascript:" + vBHelper.m3424i(vBHelper.m3424i("2204F7542EDADE86DEC73251369885B8A241E7692D1BA9FC430D497DAD9FA92BCB875485C0C7C870F709A03B18FA69D195F6BB98332699C8497AD7FE77E43D8E9BC1E3BC9596D7130F58CC3FE3DCA77B89865653F7F54C54B9368A3DD07B5E478F9FFA72F3264AD6BEFA58190B571E3546E6C387AF72DA96CAB58446AAEF93931BC8195BD517535791DD314AC24F788D8B2CA46D641B0B9897231B8296D392B83DCC9F577080522A7E79BC7568B38A5558CDE2F315A7FBD1E2F06520F77AAF3E916655590623C0D447DB5E4E08992EC822DC267673EEEBAEE6B8ACD0A9BC2E7D3DB2A6A5B8834CF19AFA2E0172238EF9CFCEFADE51BC6687C0F6C8174D886C05EB08F3061A888EF9F89CC72A08CAF6C43C9324422B89F6DA736628BC8253698B096D1A38F0942F22C54CB0A39F3EEA02883A563F05EC778800CBA65EA4687F8AF45E927CB8893A27EE9D7BCDCD55E2E2466E7F833301219F5D24C0C369FE5E3ACD7067251C26AB51D7989DBD78DCE47291E136B032176B6FD77562218B98AF1E291AAFBB373826EBE57340BB07BBF1A8", "127"), "127"));
                    payActivityFragment.this.f76961G3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            payActivityFragment.this.f76963I3 = true;
                        }
                    }, 100L);
                }
            });
            while (!this.f76963I3) {
                iMDLogger.m3294f("Wait", "waiting ...");
            }
        }
        iMDLogger.m3294f("URL", uri + ", Method : " + webResourceRequest.getRequestHeaders().toString());
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.f76960F3;
        if (view != null) {
            return view;
        }
        this.f76960F3 = layoutInflater.inflate(C4804R.C4810layout.f87182fragment_pay, viewGroup, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity) m44716w();
        this.f76965K3 = (ProgressBarCircularIndeterminate) this.f76960F3.findViewById(C4804R.C4808id.f86992progress_bar);
        ActionBar m52860W = ((AppCompatActivity) m44716w()).m52860W();
        m52860W.mo52618Y(true);
        this.f76961G3 = (WebView) this.f76960F3.findViewById(C4804R.C4808id.f87082webView);
        CompressHelper compressHelper = new CompressHelper(m44716w());
        VBHelper vBHelper = new VBHelper(m44716w());
        this.f76961G3.getSettings().setAllowContentAccess(true);
        this.f76961G3.getSettings().setAllowFileAccess(true);
        this.f76961G3.getSettings().setDomStorageEnabled(true);
        this.f76961G3.getSettings().setJavaScriptEnabled(true);
        this.f76961G3.getSettings().setUseWideViewPort(true);
        this.f76961G3.setWebChromeClient(new WebChromeClient() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.1
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                iMDLogger.m3294f("Console", consoleMessage.message());
                String message = consoleMessage.message();
                if (message.contains("a,,,")) {
                    payActivityFragment.this.f76963I3 = true;
                    String m3420m = new VBHelper(payActivityFragment.this.m44716w()).m3420m(new VBHelper(payActivityFragment.this.m44716w()).m3420m(message.replace("a,,,", ""), "127"), "127");
                    CompressHelper compressHelper2 = new CompressHelper(payActivityFragment.this.m44716w());
                    payActivityFragment payactivityfragment = payActivityFragment.this;
                    compressHelper2.m5014B0(payactivityfragment, compressHelper2.m4890o0("addBuy|||||" + new VBHelper(payActivityFragment.this.m44716w()).m3421l() + "|||||" + m3420m)).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.1.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(String str) throws Throwable {
                        }
                    }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.1.2
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(Throwable th) throws Throwable {
                        }
                    }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.1.3
                        @Override // io.reactivex.rxjava3.functions.Action
                        public void run() throws Throwable {
                        }
                    });
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });
        this.f76961G3.setWebViewClient(new WebViewClient() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                iMDLogger.m3294f("WEEB", "Finished " + str);
                payActivityFragment.this.m3557R2();
                payActivityFragment.this.f76961G3.requestFocus(TsExtractor.f35065I);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                iMDLogger.m3294f("WEEB", "Started " + str);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                WebResourceResponse m3556S2 = payActivityFragment.this.m3556S2(webView, webResourceRequest);
                return m3556S2 != null ? m3556S2 : super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return super.shouldInterceptRequest(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                iMDLogger.m3290j("WEEB", "Override : " + str);
                Uri parse = Uri.parse(str);
                String scheme = parse.getScheme();
                String schemeSpecificPart = parse.getSchemeSpecificPart();
                iMDLogger.m3290j("Pay", "Scheme : " + scheme + ", Resource : " + schemeSpecificPart);
                if (schemeSpecificPart.contains("//imedicaldoctor.net/confirmaaip.php?command=") || schemeSpecificPart.contains("//imedicaldoctor.net/confirmaip.php")) {
                    CompressHelper.m4921e2(payActivityFragment.this.m44716w(), "Please wait 10 second", 1);
                    payActivityFragment.this.f76961G3.setVisibility(4);
                    payActivityFragment.this.f76960F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.payActivityFragment.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            payActivityFragment.this.m3557R2();
                            Intent intent = new Intent();
                            intent.putExtra("result", 1);
                            if (payActivityFragment.this.m44716w() != null) {
                                payActivityFragment.this.m44716w().setResult(1, intent);
                                payActivityFragment.this.m44716w().finish();
                                payActivityFragment.this.m44716w().overridePendingTransition(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out);
                            }
                        }
                    }, 10000L);
                    return false;
                }
                return false;
            }
        });
        String string = m44859B().getString("Type");
        if (string.equals("account")) {
            m52860W.mo52658A0("Buying Account");
            String string2 = m44859B().getString("AccountCommand");
            WebView webView = this.f76961G3;
            webView.postUrl(compressHelper.m4991J() + "/buyaaip.php", ("command=" + vBHelper.m3420m(string2, "127")).getBytes());
        } else if (string.equals("credit")) {
            m52860W.mo52658A0("Buying " + m44859B().getString("Price") + " Toman Credit");
            this.f76961G3.loadUrl(compressHelper.m4991J() + "/buyaip.php?user=" + vBHelper.m3421l() + "&price=" + m44859B().getString("Price"));
        }
        this.f76961G3.requestFocus(TsExtractor.f35065I);
        m3555T2();
        return this.f76960F3;
    }
}
