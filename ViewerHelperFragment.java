package net.imedicaldoctor.imd.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import at.grabner.circleprogress.CircleProgressView;
import com.basusingh.beautifulprogressdialog.BeautifulProgressDialog;
import com.google.android.exoplayer2.C1688C;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.HTML;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.requery.android.database.sqlite.SQLiteDatabase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.CollapsingToolbar.CollapsingToolbarLayout;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Data.HistoryAdapter;
import net.imedicaldoctor.imd.Data.UnzipCompleted;
import net.imedicaldoctor.imd.Decompress;
import net.imedicaldoctor.imd.Fragments.Dictionary.CDicSearchActivity;
import net.imedicaldoctor.imd.Utils.ActionModeResponse;
import net.imedicaldoctor.imd.Utils.iMDWebView;
import net.imedicaldoctor.imd.Views.BlueHighlightButton;
import net.imedicaldoctor.imd.Views.GreenHighlightButton;
import net.imedicaldoctor.imd.Views.RedHighlightButton;
import net.imedicaldoctor.imd.Views.WhiteHighlightButton;
import net.imedicaldoctor.imd.Views.YellowHighlightButton;
import net.imedicaldoctor.imd.extractingFragment;
import net.imedicaldoctor.imd.iMD;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.FileUtils;
import org.apache.commons.p024io.IOUtils;
import org.ccil.cowan.tagsoup.Parser;
import org.ccil.cowan.tagsoup.XMLWriter;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.xml.sax.InputSource;

/* loaded from: classes2.dex */
public class ViewerHelperFragment extends Fragment implements ActionBar.TabListener {

    /* renamed from: u4 */
    private static final String f75825u4 = "ViewerHelperFragment";

    /* renamed from: v4 */
    private static extractingFragment f75826v4;

    /* renamed from: F3 */
    public boolean f75827F3;

    /* renamed from: G3 */
    public String f75828G3;

    /* renamed from: H3 */
    public String f75829H3;

    /* renamed from: I3 */
    public boolean f75830I3;

    /* renamed from: J3 */
    private DrawerLayout f75831J3;

    /* renamed from: K3 */
    public RecyclerView f75832K3;

    /* renamed from: L3 */
    public boolean f75833L3;

    /* renamed from: M3 */
    public Bundle f75834M3;

    /* renamed from: N3 */
    public int f75835N3;

    /* renamed from: O3 */
    public int f75836O3;

    /* renamed from: P3 */
    public String f75837P3;

    /* renamed from: Q3 */
    public SearchView f75838Q3;

    /* renamed from: R3 */
    public Menu f75839R3;

    /* renamed from: S3 */
    public ImageButton f75840S3;

    /* renamed from: T3 */
    public ImageButton f75841T3;

    /* renamed from: U3 */
    public TextView f75842U3;

    /* renamed from: V3 */
    public MenuItem f75843V3;

    /* renamed from: W3 */
    public TabHost f75844W3;

    /* renamed from: X3 */
    public String[] f75845X3;

    /* renamed from: Y3 */
    public String f75846Y3;

    /* renamed from: Z3 */
    public String f75847Z3;

    /* renamed from: a4 */
    public Activity f75848a4;

    /* renamed from: b4 */
    public View f75849b4;

    /* renamed from: c4 */
    public Bundle f75850c4;

    /* renamed from: d4 */
    public String f75851d4;

    /* renamed from: e4 */
    public String f75852e4;

    /* renamed from: f4 */
    public WebView f75853f4;

    /* renamed from: g4 */
    public JSONArray f75854g4;

    /* renamed from: h4 */
    public ProgressBar f75855h4;

    /* renamed from: i4 */
    public MenuItem f75856i4;

    /* renamed from: j4 */
    public String f75857j4;

    /* renamed from: k4 */
    public Toolbar f75858k4;

    /* renamed from: l4 */
    public ImageView f75859l4;

    /* renamed from: m4 */
    public TextView f75860m4;

    /* renamed from: n4 */
    public PopupWindow f75861n4;

    /* renamed from: o4 */
    public NestedScrollView f75862o4;

    /* renamed from: p4 */
    public CompressHelper f75863p4;

    /* renamed from: q4 */
    public long f75864q4;

    /* renamed from: r4 */
    public String f75865r4;

    /* renamed from: s4 */
    public Runnable f75866s4;

    /* renamed from: t4 */
    public Runnable f75867t4;

    /* renamed from: Q2 */
    public static String m4127Q2(String str, String str2) {
        int indexOf;
        if (str2.toLowerCase().equals("charis")) {
            return str;
        }
        while (true) {
            int indexOf2 = str.indexOf("@font-face");
            if (indexOf2 <= -1 || (indexOf = str.indexOf("}", indexOf2)) < indexOf2) {
                break;
            }
            int i = indexOf + 1;
            str = str.substring(0, indexOf2) + "" + (i < str.length() ? str.substring(i) : "");
        }
        return m4108a4(str, "\"Charis\"", "\"" + str2 + "\"");
    }

    /* renamed from: Y3 */
    public static String m4112Y3(String str) {
        int indexOf;
        while (true) {
            int indexOf2 = str.indexOf("line-height:");
            if (indexOf2 <= -1 || (indexOf = str.indexOf(";", indexOf2)) < indexOf2) {
                break;
            }
            int i = indexOf + 1;
            str = str.substring(0, indexOf2) + "" + (i < str.length() ? str.substring(i) : "");
        }
        return str;
    }

    /* renamed from: Z3 */
    public static String m4110Z3(String str) {
        int indexOf;
        while (true) {
            int indexOf2 = str.indexOf("text-align:");
            if (indexOf2 <= -1 || (indexOf = str.indexOf(";", indexOf2)) < indexOf2) {
                break;
            }
            int i = indexOf + 1;
            str = str.substring(0, indexOf2) + "" + (i < str.length() ? str.substring(i) : "");
        }
        return str;
    }

    /* renamed from: a4 */
    public static String m4108a4(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        int indexOf = sb.indexOf(str2);
        while (indexOf != -1) {
            sb.replace(indexOf, str2.length() + indexOf, str3);
            indexOf = sb.indexOf(str2, indexOf + str3.length());
        }
        return sb.toString();
    }

    /* renamed from: i3 */
    public static void m4095i3(File file, File file2) throws IOException {
        if (file.isDirectory()) {
            if (!file2.exists()) {
                file2.mkdir();
            }
            String[] list = file.list();
            for (int i = 0; i < file.listFiles().length; i++) {
                m4095i3(new File(file, list[i]), new File(file2, list[i]));
            }
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[102400];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: A3 */
    public void mo4146A3(int i) {
        if (this.f75854g4.length() > 0) {
            try {
                WebView webView = this.f75853f4;
                webView.loadUrl("javascript:setHighlightClass(\"" + this.f75854g4.getString(this.f75835N3) + "\")");
                WebView webView2 = this.f75853f4;
                webView2.loadUrl("javascript:goToSelectedItem(\"" + this.f75854g4.getString(i) + "\")");
                this.f75835N3 = i;
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }
        m4120V2();
    }

    /* renamed from: B3 */
    public void m4145B3() {
        if (m44859B().containsKey("gotoHighlight")) {
            String string = m44859B().getString("gotoHighlight");
            WebView webView = this.f75853f4;
            webView.loadUrl("javascript:gotoHighlight('" + string + "');");
            m44859B().remove("gotoHighlight");
        }
    }

    /* renamed from: C3 */
    public void mo4144C3(String str) {
        iMDLogger.m3290j("Viewer activity , Gotosection", str);
        WebView webView = this.f75853f4;
        webView.loadUrl("javascript:document.getElementById(\"" + str + "\").scrollIntoView(true);");
    }

    /* renamed from: D3 */
    public boolean mo4143D3() {
        return true;
    }

    /* renamed from: E3 */
    public Boolean m4142E3(String str) {
        return Boolean.FALSE;
    }

    /* renamed from: F3 */
    public void m4141F3() {
        CircleProgressView circleProgressView = (CircleProgressView) this.f75849b4.findViewById(C4804R.C4808id.f86995progress_view);
        if (circleProgressView != null) {
            circleProgressView.setVisibility(8);
        }
    }

    /* renamed from: G3 */
    public void m4140G3() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) m44716w().getSystemService("input_method");
            if (m44716w().getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(m44716w().getCurrentFocus().getWindowToken(), 0);
            }
            m44716w().getCurrentFocus().clearFocus();
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: H2 */
    public void mo4139H2(Intent intent) {
        super.mo4139H2(intent);
        m44716w().overridePendingTransition(C4804R.anim.f85970from_fade_in, C4804R.anim.f85971from_fade_out);
    }

    /* renamed from: H3 */
    public void m4138H3(Boolean bool) {
        try {
            LinearLayout linearLayout = (LinearLayout) m44716w().findViewById(C4804R.C4808id.f86894find_layout);
            if (bool.booleanValue()) {
                this.f75843V3.setVisible(false);
                linearLayout.setVisibility(8);
            } else {
                linearLayout.setVisibility(0);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: I3 */
    public String m4137I3() {
        String str = this.f75863p4.m4856z1() + "/highlights.db";
        if (!new File(str).exists()) {
            SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null).execSQL("create virtual table highlight using fts4 (dbName, dbTitle, dbAddress, dbDate, dbDocName, type, text, note, save)");
        }
        return str;
    }

    /* renamed from: J3 */
    public boolean mo4136J3(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("dark", false);
    }

    /* renamed from: K3 */
    public Boolean mo4135K3(String str) {
        CompressHelper compressHelper = this.f75863p4;
        String m4972P0 = compressHelper.m4972P0();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from favorites where dbName='");
        sb.append(this.f75863p4.m4963S0(this.f75850c4.getString("Name")));
        sb.append("' AND dbAddress='");
        sb.append(this.f75863p4.m4963S0(str));
        sb.append("'");
        return compressHelper.m4907i1(compressHelper.m4946Y(m4972P0, sb.toString())) == null ? Boolean.FALSE : Boolean.TRUE;
    }

    /* renamed from: L3 */
    public void m4134L3() {
        try {
            ArrayList<Bundle> m4946Y = this.f75863p4.m4946Y(m4137I3(), "select rowid as _id, dbName, dbTitle, dbAddress, dbDate, dbDocName, type, text, note, save from highlight where dbName='" + this.f75850c4.getString("Name").replace("'", "''") + "' AND dbAddress='" + this.f75863p4.m4963S0(mo4079s3()) + "'");
            if (m4946Y == null) {
                m4946Y = new ArrayList<>();
            }
            this.f75853f4.loadUrl("javascript:highlighter.removeAllHighlights();");
            ArrayList arrayList = new ArrayList();
            arrayList.add("type:textContent");
            ArrayList arrayList2 = new ArrayList();
            Iterator<Bundle> it2 = m4946Y.iterator();
            while (it2.hasNext()) {
                Bundle next = it2.next();
                if (next.getString("save").length() > 0) {
                    String string = next.getString("save");
                    String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(string, "$");
                    String string2 = next.getString("type");
                    String[] splitByWholeSeparator2 = StringUtils.splitByWholeSeparator(string2, ",");
                    if (!string2.equals("0") && splitByWholeSeparator2.length >= 2) {
                        Long valueOf = Long.valueOf(splitByWholeSeparator2[1]);
                        if (this.f75864q4 != valueOf.longValue()) {
                            long longValue = this.f75864q4 - valueOf.longValue();
                            string = String.valueOf(Integer.valueOf(splitByWholeSeparator[0]).intValue() + longValue) + "$" + String.valueOf(Integer.valueOf(splitByWholeSeparator[1]).intValue() + longValue) + "$" + splitByWholeSeparator[2] + "$" + splitByWholeSeparator[3] + "$" + splitByWholeSeparator[4];
                        }
                        arrayList.add(string);
                    }
                    arrayList2.add("Update highlight set type='base," + this.f75864q4 + "' where rowid=" + next.getString("_id"));
                    arrayList.add(string);
                }
            }
            if (arrayList2.size() > 0) {
                this.f75863p4.m4882r(m4137I3(), (String[]) arrayList2.toArray(new String[0]), 0);
            }
            String join = TextUtils.join("|", arrayList);
            this.f75853f4.loadUrl("javascript:highlighter.deserialize('" + join + "');");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            iMDLogger.m3294f("ViewerActivity", "Error in loading highlights " + e.toString());
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: M0 */
    public void mo3640M0(Activity activity) {
        super.mo3640M0(activity);
        this.f75848a4 = activity;
    }

    /* renamed from: M3 */
    public void m4133M3(String str, String str2) {
        Bundle bundle = this.f75850c4;
        String m4942Z0 = CompressHelper.m4942Z0(bundle, str + ".jpg", str2);
        if (new File(m4942Z0).exists()) {
            return;
        }
        String str3 = str2.equals("small") ? "small.db" : "01234567".contains(str.substring(0, 1)) ? "images-1.db" : "images-2.db";
        String m4945Y0 = CompressHelper.m4945Y0(this.f75850c4, str2);
        if (!new File(m4945Y0).exists()) {
            new File(m4945Y0).mkdirs();
        }
        CompressHelper compressHelper = this.f75863p4;
        Bundle bundle2 = this.f75850c4;
        Bundle m4926d1 = compressHelper.m4926d1(compressHelper.m4952W(bundle2, "Select image from thumbs where id='" + str + "'", str3));
        if (m4926d1 != null) {
            try {
                FileUtils.writeByteArrayToFile(new File(m4942Z0), m4926d1.getByteArray("image"));
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("LoadImageIfnecessary", "Failed at writing to " + m4942Z0 + " loading image with id" + str + " in folder " + str2);
            }
        }
    }

    /* renamed from: N2 */
    public void m4132N2(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String m4963S0 = this.f75863p4.m4963S0(str);
        String m4963S02 = this.f75863p4.m4963S0(str2);
        String format = simpleDateFormat.format(new Date());
        String m4963S03 = this.f75863p4.m4963S0(this.f75850c4.getString("Title"));
        String m4963S04 = this.f75863p4.m4963S0(this.f75850c4.getString("Name"));
        CompressHelper compressHelper = this.f75863p4;
        String m4972P0 = compressHelper.m4972P0();
        compressHelper.m4885q(m4972P0, "Insert into favorites values (null, '" + m4963S04 + "', '" + m4963S03 + "', '" + m4963S02 + "', '" + format + "', '" + m4963S0 + "')");
        m4104c4();
    }

    /* renamed from: N3 */
    public void m4131N3() {
        try {
            this.f75861n4.dismiss();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
        }
    }

    /* renamed from: O2 */
    public void m4130O2() {
        Bundle bundle = this.f75850c4;
        if (bundle == null) {
            return;
        }
        this.f75863p4.m4872u0(bundle.getString("Name"), this.f75850c4.getString("Title"), this.f75851d4, this.f75852e4);
    }

    /* renamed from: O3 */
    public void m4129O3() {
        WebView webView = this.f75853f4;
        webView.loadUrl("javascript:getRect(" + this.f75853f4.getWidth() + "," + this.f75853f4.getHeight() + ")");
    }

    /* renamed from: P2 */
    public void m4128P2() {
        this.f75853f4.loadUrl("javascript:removeAllHighlights(\"aa\");");
        m4138H3(Boolean.TRUE);
    }

    /* renamed from: P3 */
    public boolean mo3571P3(ConsoleMessage consoleMessage) {
        iMDLogger.m3294f("Javascript Console Message", consoleMessage.message() + " - " + consoleMessage.sourceId() + " - " + consoleMessage.lineNumber());
        String[] split = consoleMessage.message().split(",,,,,");
        if (split[0].equals("baserange")) {
            this.f75864q4 = Long.valueOf(split[1]).longValue();
            m4134L3();
            if (m44859B() == null || !m44859B().containsKey("SEARCH")) {
                mo4067z3();
            }
        }
        if (split[0].equals("highlightAction")) {
            String str = split[1];
            String str2 = split[2];
            String mo4079s3 = mo4079s3();
            String str3 = this.f75852e4;
            m4109a3(mo4079s3, str3, "base," + this.f75864q4, str, "", str2);
        } else if (split[0].equals("defineAction")) {
            if (split.length == 1) {
                iMDLogger.m3294f("HelperFragment", "Dont have a word to define");
                return true;
            }
            m4085o3(split[1]);
        } else if (split[0].equals("dehighlightAction")) {
            m4114X3(split[1], this.f75851d4);
        } else if (split[0].equals("copyAction")) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(StringPart.DEFAULT_CONTENT_TYPE);
            String str4 = split[1];
            intent.putExtra("android.intent.extra.SUBJECT", this.f75852e4);
            intent.putExtra("android.intent.extra.TEXT", str4);
            mo4139H2(Intent.createChooser(intent, "Share via"));
        } else if (split[0].equals("shareAction")) {
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType(StringPart.DEFAULT_CONTENT_TYPE);
            String str5 = split[1];
            intent2.putExtra("android.intent.extra.SUBJECT", this.f75852e4);
            intent2.putExtra("android.intent.extra.TEXT", str5);
            mo4139H2(Intent.createChooser(intent2, "Share via"));
        } else if (split[0].equals("saveAction")) {
            this.f75853f4.loadUrl("javascript:window.getSelection().removeAllRanges();");
            String str6 = split[1];
            this.f75829H3 = str6;
            this.f75834M3.putString("mLastPosition", str6);
        } else if (split[0].equals(HTML.Tag.f65946y)) {
            final String str7 = split[1];
            new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.41

                /* renamed from: a */
                boolean f75946a;

                @Override // android.os.AsyncTask
                protected Object doInBackground(Object[] objArr) {
                    this.f75946a = false;
                    Document document = new Document();
                    String m4856z1 = ViewerHelperFragment.this.f75863p4.m4856z1();
                    try {
                        Parser parser = new Parser();
                        StringWriter stringWriter = new StringWriter();
                        parser.setContentHandler(new XMLWriter(stringWriter));
                        parser.parse(new InputSource(new StringReader(Jsoup.m1057c(str7, Whitelist.m272m()))));
                        String replaceAll = ViewerHelperFragment.this.f75852e4.replaceAll("[\\W]", "_");
                        PdfWriter m11942p1 = PdfWriter.m11942p1(document, new FileOutputStream(m4856z1 + "/" + replaceAll + ".pdf"));
                        document.open();
                        XMLWorkerHelper.m9974e().m9964o(m11942p1, document, new StringReader(stringWriter.toString()));
                        document.close();
                        FragmentActivity m44716w = ViewerHelperFragment.this.m44716w();
                        MediaScannerConnection.scanFile(m44716w, new String[]{m4856z1 + "/" + replaceAll + ".pdf"}, null, null);
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        this.f75946a = true;
                        String cls = getClass().toString();
                        iMDLogger.m3294f(cls, "Error in converting to pdf :" + e.toString());
                    }
                    return null;
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                    ViewerHelperFragment.f75826v4.mo27003Q2();
                    extractingFragment unused = ViewerHelperFragment.f75826v4 = null;
                    String m4856z1 = ViewerHelperFragment.this.f75863p4.m4856z1();
                    String replaceAll = ViewerHelperFragment.this.f75852e4.replaceAll("[\\W]", "_");
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    File file = new File(m4856z1 + "/" + replaceAll + ".pdf");
                    if (this.f75946a) {
                        CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Error occured in making pdf document", 1);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    if (file.exists()) {
                        intent3.setDataAndType(Uri.fromFile(file), "application/pdf");
                        ViewerHelperFragment.this.mo4139H2(intent3);
                    }
                }

                @Override // android.os.AsyncTask
                protected void onPreExecute() {
                    extractingFragment unused = ViewerHelperFragment.f75826v4 = new extractingFragment();
                    ViewerHelperFragment.f75826v4.m44701z2(true);
                    Bundle bundle = new Bundle();
                    bundle.putString("MESSAGE", "Generating PDF");
                    ViewerHelperFragment.f75826v4.m44751k2(bundle);
                    ViewerHelperFragment.f75826v4.m44870c3(false);
                    ViewerHelperFragment.f75826v4.mo29915h3(ViewerHelperFragment.this.m44820L(), "extracting");
                }
            }.execute(new Object[0]);
        } else if (split[0].equals("findAction")) {
            String str8 = split[1];
            this.f75854g4 = new JSONArray();
            try {
                this.f75854g4 = new JSONArray(str8);
                m4138H3(this.f75838Q3.getQuery().length() > 0 ? Boolean.FALSE : Boolean.TRUE);
                this.f75854g4.length();
                int i = this.f75835N3;
                int i2 = this.f75836O3;
                if (i < i2) {
                    this.f75835N3 = i2;
                } else {
                    this.f75835N3 = 0;
                }
                mo4146A3(this.f75835N3);
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("Error in reading findAction Json", e.toString());
                return false;
            }
        } else if (split[0].equals("coordinates")) {
            float f = m44782a0().getDisplayMetrics().density;
            int intValue = Float.valueOf(split[1]).intValue();
            int intValue2 = Float.valueOf(split[2]).intValue();
            Float.valueOf(split[3]).intValue();
            Float.valueOf(split[4]).intValue();
            int[] iArr = new int[2];
            this.f75853f4.getLocationInWindow(iArr);
            int i3 = ((int) (intValue * f)) + iArr[0];
            int i4 = ((int) (intValue2 * f)) + iArr[1];
            int i5 = (int) (100 * f);
            FragmentActivity m44716w = m44716w();
            m44716w();
            View inflate = ((LayoutInflater) m44716w.getSystemService("layout_inflater")).inflate(C4804R.C4810layout.f87308popup_test, (ViewGroup) null, false);
            PopupWindow popupWindow = new PopupWindow(inflate, (int) (220 * f), i5, false);
            this.f75861n4 = popupWindow;
            popupWindow.setWindowLayoutMode(-2, -2);
            this.f75861n4.setHeight(1);
            this.f75861n4.setWidth(1);
            this.f75861n4.showAtLocation(this.f75853f4, 0, i3, i4 - i5);
            ((WhiteHighlightButton) inflate.findViewById(C4804R.C4808id.f86928highlight_clear)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.42
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=removeHighlightFromSelectedText()");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('dehighlightAction,,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((YellowHighlightButton) inflate.findViewById(C4804R.C4808id.f86931highlight_yellow)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.43
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=highlightSelectedTextWithClass(\"highlightYellow\")");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('highlightAction,,,,,' + h.getText() + ',,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((GreenHighlightButton) inflate.findViewById(C4804R.C4808id.f86929highlight_green)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.44
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=highlightSelectedTextWithClass(\"highlightGreen\")");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('highlightAction,,,,,' + h.getText() + ',,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((BlueHighlightButton) inflate.findViewById(C4804R.C4808id.f86927highlight_blue)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.45
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=highlightSelectedTextWithClass(\"highlightBlue\")");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('highlightAction,,,,,' + h.getText() + ',,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((RedHighlightButton) inflate.findViewById(C4804R.C4808id.f86930highlight_red)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.46
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=highlightSelectedTextWithClass(\"highlightRed\")");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('highlightAction,,,,,' + h.getText() + ',,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((TextView) inflate.findViewById(C4804R.C4808id.f86869dictTv)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.47
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    iMDLogger.m3290j(getClass().getName(), "Define Clicked");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('defineAction,,,,,' + window.getSelection().toString())");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((TextView) inflate.findViewById(C4804R.C4808id.f86852copyTv)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.48
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('copyAction,,,,,' + window.getSelection().toString())");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
            ((TextView) inflate.findViewById(C4804R.C4808id.f86972noteTv)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.49
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:h=highlightSelectedTextWithClass(\"highlightNote\")");
                    ViewerHelperFragment.this.f75853f4.loadUrl("javascript:console.log('highlightAction,,,,,' + h.getText() + ',,,,,' + h.characterRange.start+'$'+h.characterRange.end+'$'+h.id+'$'+h.classApplier.cssClass+'$' );");
                    ViewerHelperFragment.this.m4075u3();
                }
            });
        } else if (split[0].equals("finisham")) {
            m4131N3();
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: Q0 */
    public void mo3503Q0(Bundle bundle) {
        super.mo3503Q0(bundle);
        this.f75863p4 = new CompressHelper(m44716w());
    }

    /* renamed from: Q3 */
    public boolean mo4126Q3(WebView webView, String str, String str2, JsResult jsResult) {
        return false;
    }

    /* renamed from: R2 */
    public void m4125R2(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m4125R2(file2);
            }
        }
        file.delete();
    }

    /* renamed from: R3 */
    public void mo3570R3(WebView webView, String str) {
    }

    /* renamed from: S2 */
    public String mo3979S2() {
        return null;
    }

    /* renamed from: S3 */
    public void mo3569S3(WebView webView, String str) {
        SearchView searchView;
        if (this.f75833L3) {
            this.f75833L3 = false;
            if (m44859B() != null && m44859B().containsKey("SECTION")) {
                String string = m44859B().getString("SECTION");
                if (string.length() > 0) {
                    mo4144C3(string);
                }
            }
            if (m44859B() != null && m44859B().containsKey("SEARCH")) {
                this.f75838Q3.m51655i0(TextUtils.join(" OR ", m44859B().getStringArray("SEARCH")), true);
                this.f75843V3.setVisible(true);
                m4140G3();
                m44859B().remove("SEARCH");
            }
            if (this.f75829H3 != null) {
                iMDLogger.m3290j("viewhelper", "Restoring " + this.f75829H3);
                this.f75853f4.loadUrl("javascript:highlighter.removeAllHighlights();");
                WebView webView2 = this.f75853f4;
                webView2.loadUrl("javascript:highlighter.deserialize('" + ("type:textContent|" + this.f75829H3) + "');");
                this.f75853f4.loadUrl("javascript:gotoHighlight('" + this.f75829H3 + "');");
                this.f75853f4.loadUrl("javascript:highlighter.removeAllHighlights();");
                this.f75853f4.loadUrl("javascript:element=document.getElementById('orientation');element.parentNode.removeChild(element);");
                this.f75829H3 = null;
            }
            String str2 = this.f75828G3;
            if (str2 != null && str2.length() > 0 && (searchView = this.f75838Q3) != null) {
                searchView.m51655i0(this.f75828G3, true);
                this.f75828G3 = null;
                this.f75838Q3.setIconified(false);
                this.f75838Q3.clearFocus();
            }
            this.f75853f4.loadUrl("javascript:divs = document.getElementsByTagName('div');div=divs[0];range = document.createRange();range.setStart(div,0);range.setEnd(div,0);console.log('baserange,,,,,' + (new rangy.WrappedRange(range)).getBookmark(document.body).start);");
        }
    }

    /* renamed from: T2 */
    public Observable<String> m4124T2() {
        return Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.8
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                String mo3979S2 = ViewerHelperFragment.this.mo3979S2();
                if (mo3979S2 != null) {
                    observableEmitter.onNext(mo3979S2);
                }
                observableEmitter.onComplete();
            }
        });
    }

    /* renamed from: T3 */
    public void m4123T3(String str) {
        mo4139H2(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* renamed from: U2 */
    public void m4122U2(final Runnable runnable, final Runnable runnable2) {
        final BeautifulProgressDialog beautifulProgressDialog = new BeautifulProgressDialog(m44716w(), BeautifulProgressDialog.f23703p, null);
        beautifulProgressDialog.m40335o("loading-1.json");
        beautifulProgressDialog.m40334p(true);
        new Thread(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(500L);
                    ViewerHelperFragment.this.f75849b4.post(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RunnableC434023 runnableC434023 = RunnableC434023.this;
                            if (ViewerHelperFragment.this.f75830I3) {
                                return;
                            }
                            beautifulProgressDialog.m40329u();
                        }
                    });
                } catch (InterruptedException unused) {
                }
            }
        }).start();
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.24
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    runnable.run();
                    observableEmitter.onNext("asdfadf");
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    e.printStackTrace();
                    ViewerHelperFragment.this.f75830I3 = true;
                    beautifulProgressDialog.m40349a();
                }
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.25
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                ViewerHelperFragment.this.f75830I3 = true;
                beautifulProgressDialog.m40349a();
                try {
                    runnable2.run();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.26
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                ViewerHelperFragment.this.f75830I3 = true;
                beautifulProgressDialog.m40349a();
                th.printStackTrace();
                FirebaseCrashlytics.m18030d().m18027g(th);
                runnable2.run();
            }
        });
    }

    /* renamed from: U3 */
    public void m4121U3(String str) {
        MediaPlayer.create(m44716w(), Uri.fromFile(new File(str))).start();
    }

    /* renamed from: V2 */
    public void m4120V2() {
        JSONArray jSONArray = this.f75854g4;
        if (jSONArray == null || jSONArray.length() == 0) {
            this.f75842U3.setText("Nothing Found");
            this.f75840S3.setEnabled(false);
            this.f75841T3.setEnabled(false);
            return;
        }
        this.f75840S3.setEnabled(true);
        this.f75841T3.setEnabled(true);
        TextView textView = this.f75842U3;
        textView.setText(String.valueOf(this.f75835N3 + 1) + " Of " + String.valueOf(this.f75854g4.length()));
    }

    /* renamed from: V3 */
    public void m4119V3(String str, String str2, String str3, final String str4) {
        Observable<byte[]> m4829d = Decompress.m4829d(str, str2);
        final File file = new File(str3);
        if (!file.exists()) {
            m4829d.m7300i6(Schedulers.m5369f()).m7193t4(AndroidSchedulers.m8490e()).m7339e6(new Consumer<byte[]>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.56
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(byte[] bArr) throws Throwable {
                    byte[] m4867w = ViewerHelperFragment.this.f75863p4.m4867w(bArr, str4, "127");
                    try {
                        if (file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        FileUtils.writeByteArrayToFile(file, m4867w);
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        iMDLogger.m3294f("getSound", "Error in writing sound to " + file.getAbsolutePath() + " : " + e.toString());
                    }
                    file.deleteOnExit();
                    ViewerHelperFragment.this.m4121U3(file.getAbsolutePath());
                }
            });
            return;
        }
        m4121U3(file.getAbsolutePath());
        file.deleteOnExit();
    }

    /* renamed from: W3 */
    public String m4117W3(Context context, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + "\n");
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("defaultfont", false)) {
                sb2 = m4127Q2(sb2, C1688C.f31135s);
            }
            if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean("justify", true)) {
                sb2 = m4110Z3(sb2);
            }
            String m4112Y3 = m4112Y3(sb2);
            return (mo4136J3(context) && m4112Y3.contains("</body>")) ? m4108a4(m4112Y3, "</body>", "<script src=\"file:///android_asset/jquery.js\" ></script><script src=\"file:///android_asset/reverse1.js\" ></script><script src=\"file:///android_asset/reverse2.js\" ></script></body>") : m4112Y3;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            return "";
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: X0 */
    public void mo4116X0() {
        super.mo4116X0();
    }

    /* renamed from: X3 */
    public void m4114X3(String str, String str2) {
        try {
            String m4963S0 = this.f75863p4.m4963S0(str);
            this.f75863p4.m4885q(m4137I3(), "delete from highlight where save match '" + m4963S0 + "' AND rowid in (select rowid from highlight where dbName='" + this.f75863p4.m4963S0(this.f75850c4.getString("Name")) + "' AND dbAddress='" + this.f75863p4.m4963S0(mo4079s3()) + "')");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            iMDLogger.m3294f("RemoveHighlight", "Error occured  " + str2);
        }
    }

    /* renamed from: Z2 */
    public void m4111Z2(final String str, final String str2) {
        String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str2, "/");
        final String str3 = splitByWholeSeparator[splitByWholeSeparator.length - 1];
        final String str4 = this.f75850c4.getString("Name") + " - " + this.f75851d4;
        final downloadFragment downloadfragment = ((iMD) m44716w().getApplicationContext()).f83455D2;
        final CircleProgressView circleProgressView = (CircleProgressView) this.f75849b4.findViewById(C4804R.C4808id.f86995progress_view);
        Bundle m3755B3 = downloadfragment.m3755B3(str4);
        if (m3755B3 == null) {
            new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("You don't have this video on your device, do you wish to download it ?").mo26266y("Yes", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    CompressHelper compressHelper = ViewerHelperFragment.this.f75863p4;
                    StringBuilder sb = new StringBuilder();
                    sb.append("fileSizes|||||");
                    String str5 = str;
                    sb.append(str5.replace(ViewerHelperFragment.this.f75863p4.m4991J() + "/", ""));
                    compressHelper.m4890o0(sb.toString()).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.10.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(String str6) throws Throwable {
                            String[] splitByWholeSeparator2 = StringUtils.splitByWholeSeparator(str6, ";;;");
                            if (splitByWholeSeparator2.length != 11) {
                                CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Can't find file, please try again later", 1);
                                return;
                            }
                            circleProgressView.setVisibility(0);
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC432310 = DialogInterface$OnClickListenerC432310.this;
                            ViewerHelperFragment.this.f75865r4 = str4;
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 1; i2 < 11; i2++) {
                                arrayList.add(splitByWholeSeparator2[i2]);
                            }
                            String join = StringUtils.join(arrayList, ",,,");
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC4323102 = DialogInterface$OnClickListenerC432310.this;
                            downloadfragment.m3687y3(ViewerHelperFragment.this.f75850c4.getString("Title") + " - " + str3, str, str2, splitByWholeSeparator2[0], str3, str4, join);
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC4323103 = DialogInterface$OnClickListenerC432310.this;
                            downloadfragment.m3722X3(str4, circleProgressView);
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC4323104 = DialogInterface$OnClickListenerC432310.this;
                            downloadfragment.m3728U3(str4, ViewerHelperFragment.this.f75866s4);
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC4323105 = DialogInterface$OnClickListenerC432310.this;
                            downloadfragment.m3726V3(str4, ViewerHelperFragment.this.f75867t4);
                            DialogInterface$OnClickListenerC432310 dialogInterface$OnClickListenerC4323106 = DialogInterface$OnClickListenerC432310.this;
                            downloadfragment.m3712c4(str4);
                            CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Download Started", 0);
                        }
                    }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.10.2
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(Throwable th) throws Throwable {
                            try {
                                th.printStackTrace();
                                CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Error occured ", 0);
                            } catch (Exception e) {
                                FirebaseCrashlytics.m18030d().m18027g(e);
                            }
                        }
                    }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.10.3
                        @Override // io.reactivex.rxjava3.functions.Action
                        public void run() throws Throwable {
                        }
                    });
                }
            }).mo26284p("No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).m52864I();
        } else if (m3755B3.containsKey("downloader")) {
            CompressHelper.m4921e2(m44716w(), "We are downloading a file for this document, please be patient", 1);
        } else {
            circleProgressView.setVisibility(0);
            this.f75865r4 = str4;
            downloadfragment.m3722X3(str4, circleProgressView);
            downloadfragment.m3728U3(str4, this.f75866s4);
            downloadfragment.m3726V3(str4, this.f75867t4);
            downloadfragment.m3712c4(str4);
        }
    }

    /* renamed from: a3 */
    public void m4109a3(String str, String str2, String str3, String str4, String str5, String str6) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String m4963S0 = this.f75863p4.m4963S0(this.f75850c4.getString("Name"));
        String m4963S02 = this.f75863p4.m4963S0(this.f75850c4.getString("Title"));
        String m4963S03 = this.f75863p4.m4963S0(str);
        String m4963S04 = this.f75863p4.m4963S0(str2);
        String m4963S05 = this.f75863p4.m4963S0(str3);
        String m4963S06 = this.f75863p4.m4963S0(str4);
        String m4963S07 = this.f75863p4.m4963S0(str5);
        String m4963S08 = this.f75863p4.m4963S0(str6);
        String format = simpleDateFormat.format(new Date());
        CompressHelper compressHelper = this.f75863p4;
        String m4137I3 = m4137I3();
        compressHelper.m4885q(m4137I3, "Insert into highlight (dbName, dbTitle, dbAddress, dbDate, dbDocName, type, text, note, save) values ('" + m4963S0 + "', '" + m4963S02 + "', '" + m4963S03 + "', '" + format + "', '" + m4963S04 + "' , '" + m4963S05 + "', '" + m4963S06 + "', '" + m4963S07 + "', '" + m4963S08 + "')");
        if (m4963S08.contains("highlightNote")) {
            m4076t4(m4963S08);
        }
    }

    /* renamed from: b3 */
    public void m4107b3(String str, String str2) {
    }

    /* renamed from: b4 */
    public void m4106b4(String str) {
        try {
            String m4117W3 = m4117W3(m44716w(), str);
            WebView webView = this.f75853f4;
            webView.loadUrl("javascript:" + m4117W3);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("GetJavascript", "Can't read " + str);
        }
    }

    /* renamed from: c3 */
    public void m4105c3() {
        try {
            String str = this.f75850c4.getString("Name") + " - " + this.f75851d4;
            downloadFragment downloadfragment = ((iMD) m44716w().getApplicationContext()).f83455D2;
            CircleProgressView circleProgressView = (CircleProgressView) this.f75849b4.findViewById(C4804R.C4808id.f86995progress_view);
            Bundle m3755B3 = downloadfragment.m3755B3(str);
            if (m3755B3 == null || !m3755B3.containsKey("downloader")) {
                return;
            }
            circleProgressView.setVisibility(0);
            downloadfragment.m3722X3(str, circleProgressView);
            downloadfragment.m3726V3(str, this.f75867t4);
            downloadfragment.m3728U3(str, this.f75866s4);
        } catch (Exception unused) {
        }
    }

    /* renamed from: c4 */
    public void m4104c4() {
        iMDLogger.m3296d("sendFavorite", "Sending FavoriteChanged message");
        Intent intent = new Intent("net.imedicaldoctor.imd.favorite");
        intent.putExtra("Test", "Random data for test");
        LocalBroadcastManager.m43863b(m44716w()).m43861d(intent);
    }

    /* renamed from: d3 */
    public void m4103d3() {
        try {
            this.f75844W3.clearAllTabs();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: d4 */
    public void m4102d4(String str) {
        try {
            ((AppCompatActivity) m44716w()).m52860W().mo52658A0(str);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: e1 */
    public boolean mo3709e1(MenuItem menuItem) {
        int i;
        int itemId = menuItem.getItemId();
        if (itemId == C4804R.C4808id.f86898fix_highlight) {
            final EditText editText = new EditText(m44716w());
            editText.setTextColor(m44782a0().getColor(C4804R.C4806color.f86093black));
            new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Enter Offset for highlights").setView(editText).mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.12
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    long longValue = Long.valueOf(editText.getText().toString()).longValue();
                    try {
                        ArrayList arrayList = new ArrayList();
                        StringBuilder sb = new StringBuilder();
                        sb.append("select rowid as _id, dbName, dbTitle, dbAddress, dbDate, dbDocName, type, text, note, save from highlight where dbName='");
                        sb.append(ViewerHelperFragment.this.f75850c4.getString("Name").replace("'", "''"));
                        sb.append("' AND dbAddress='");
                        ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                        sb.append(viewerHelperFragment.f75863p4.m4963S0(viewerHelperFragment.mo4079s3()));
                        sb.append("'");
                        String sb2 = sb.toString();
                        ViewerHelperFragment viewerHelperFragment2 = ViewerHelperFragment.this;
                        ArrayList<Bundle> m4946Y = viewerHelperFragment2.f75863p4.m4946Y(viewerHelperFragment2.m4137I3(), sb2);
                        if (m4946Y == null) {
                            ViewerHelperFragment.this.f75863p4.m4925d2("There is no highlights in this document");
                            return;
                        }
                        Iterator<Bundle> it2 = m4946Y.iterator();
                        while (it2.hasNext()) {
                            Bundle next = it2.next();
                            if (next.getString("save").length() > 0) {
                                String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(next.getString("save"), "$");
                                String str = String.valueOf(Integer.valueOf(splitByWholeSeparator[0]).intValue() + longValue) + "$" + String.valueOf(Integer.valueOf(splitByWholeSeparator[1]).intValue() + longValue) + "$" + splitByWholeSeparator[2] + "$" + splitByWholeSeparator[3] + "$" + splitByWholeSeparator[4];
                                arrayList.add("Update highlight set save='" + str.replace("'", "''") + "' where rowid=" + next.getString("_id"));
                            }
                        }
                        ViewerHelperFragment viewerHelperFragment3 = ViewerHelperFragment.this;
                        viewerHelperFragment3.f75863p4.m4882r(viewerHelperFragment3.m4137I3(), (String[]) arrayList.toArray(new String[0]), 0);
                        ViewerHelperFragment.this.m4134L3();
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        e.printStackTrace();
                        iMDLogger.m3294f("ViewerActivity", "Error in changing highlights " + e.toString());
                    }
                }
            }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.11
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                }
            }).m52864I();
        }
        if (itemId == C4804R.C4808id.f87004remove_highlight) {
            new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("this will delete all the highlights.").mo26266y("From this document", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.15
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("delete from highlight where dbName='");
                    ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                    sb.append(viewerHelperFragment.f75863p4.m4963S0(viewerHelperFragment.f75850c4.getString("Name")));
                    sb.append("' AND dbAddress='");
                    ViewerHelperFragment viewerHelperFragment2 = ViewerHelperFragment.this;
                    sb.append(viewerHelperFragment2.f75863p4.m4963S0(viewerHelperFragment2.mo4079s3()));
                    sb.append("'");
                    String sb2 = sb.toString();
                    ViewerHelperFragment viewerHelperFragment3 = ViewerHelperFragment.this;
                    viewerHelperFragment3.f75863p4.m4885q(viewerHelperFragment3.m4137I3(), sb2);
                    ViewerHelperFragment.this.m4134L3();
                }
            }).mo26284p("From all documents of this database", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.14
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("delete from highlight where dbName='");
                    ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                    sb.append(viewerHelperFragment.f75863p4.m4963S0(viewerHelperFragment.f75850c4.getString("Name")));
                    sb.append("'");
                    String sb2 = sb.toString();
                    ViewerHelperFragment viewerHelperFragment2 = ViewerHelperFragment.this;
                    viewerHelperFragment2.f75863p4.m4885q(viewerHelperFragment2.m4137I3(), sb2);
                    ViewerHelperFragment.this.m4134L3();
                }
            }).mo26278s("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.13
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                }
            }).m52864I();
        }
        if (itemId == C4804R.C4808id.f86775action_home) {
            if (m44859B().containsKey("Dialog")) {
                try {
                    ((DialogFragment) m44798T()).mo27002R2();
                } catch (Exception unused) {
                    try {
                        m44855C().m44464r().m44309I(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out).mo44279x(this).mo44289n();
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            m44796U().m44464r().m44309I(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out).mo44279x(this).mo44289n();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } else {
                this.f75863p4.m4989J1(false);
            }
        }
        if (itemId == C4804R.C4808id.f86771action_favorites) {
            new Bundle();
            if (menuItem.getTitle().equals("Add Favorite")) {
                m4132N2(this.f75852e4, this.f75851d4);
                menuItem.setTitle("Remove Favorite");
                i = C4804R.C4807drawable.f86584ic_action_favorite_yellow;
            } else {
                mo4084p3(this.f75851d4);
                menuItem.setTitle("Add Favorite");
                i = C4804R.C4807drawable.f86582ic_action_favorite;
            }
            menuItem.setIcon(i);
            return true;
        } else if (itemId == C4804R.C4808id.f86786action_reload) {
            File file = new File(CompressHelper.m4945Y0(this.f75850c4, "base"));
            this.f75853f4.clearCache(true);
            this.f75853f4.loadDataWithBaseURL("file://" + file.getAbsolutePath() + "/", this.f75847Z3, "text/html", "utf-8", null);
            return true;
        } else {
            if (itemId == C4804R.C4808id.f86768action_close) {
                this.f75838Q3.m51655i0("", false);
                m4128P2();
                if (this.f75863p4.m4892n1()) {
                    m4103d3();
                    TabHost tabHost = this.f75844W3;
                    if (tabHost != null) {
                        tabHost.setVisibility(8);
                    }
                } else {
                    ActionBar m52860W = ((AppCompatActivity) m44716w()).m52860W();
                    if (m52860W != null) {
                        m52860W.mo52637N();
                        m52860W.mo52586s0(0);
                    }
                }
                this.f75843V3.setVisible(false);
            }
            if (itemId == C4804R.C4808id.f87086zoom_in) {
                m4072v4();
            }
            if (itemId == C4804R.C4808id.f87087zoom_out) {
                m4070w4();
            }
            if (itemId == C4804R.C4808id.f86779action_pdf) {
                if (this.f75850c4.containsKey("Damu")) {
                    Toast.makeText(m44716w(), "Can't make pdf in Damu mode", 1).show();
                    return true;
                }
                try {
                    ((PrintManager) m44716w().getSystemService("print")).print("iMD - " + this.f75850c4.getString("Title") + " - " + this.f75852e4, this.f75853f4.createPrintDocumentAdapter("iMD - " + this.f75850c4.getString("Title") + " - " + this.f75852e4), new PrintAttributes.Builder().build());
                    return true;
                } catch (Exception e3) {
                    FirebaseCrashlytics.m18030d().m18027g(e3);
                    if (f75826v4 == null) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.16

                            /* renamed from: a */
                            boolean f75885a;

                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                this.f75885a = false;
                                Document document = new Document();
                                String m4856z1 = ViewerHelperFragment.this.f75863p4.m4856z1();
                                try {
                                    Parser parser = new Parser();
                                    StringWriter stringWriter = new StringWriter();
                                    parser.setContentHandler(new XMLWriter(stringWriter));
                                    parser.parse(new InputSource(new StringReader(Jsoup.m1057c(ViewerHelperFragment.this.f75847Z3, Whitelist.m272m()))));
                                    String replaceAll = ViewerHelperFragment.this.f75852e4.replaceAll("[\\W]", "_");
                                    PdfWriter m11942p1 = PdfWriter.m11942p1(document, new FileOutputStream(m4856z1 + "/" + replaceAll + ".pdf"));
                                    document.open();
                                    XMLWorkerHelper.m9974e().m9964o(m11942p1, document, new StringReader(stringWriter.toString()));
                                    document.close();
                                    FragmentActivity m44716w = ViewerHelperFragment.this.m44716w();
                                    MediaScannerConnection.scanFile(m44716w, new String[]{m4856z1 + "/" + replaceAll + ".pdf"}, null, null);
                                } catch (Exception e4) {
                                    FirebaseCrashlytics.m18030d().m18027g(e4);
                                    this.f75885a = true;
                                    String cls = getClass().toString();
                                    iMDLogger.m3294f(cls, "Error in converting to pdf :" + e4.toString());
                                }
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                super.onPostExecute(obj);
                                ViewerHelperFragment.f75826v4.mo27003Q2();
                                extractingFragment unused2 = ViewerHelperFragment.f75826v4 = null;
                                String m4856z1 = ViewerHelperFragment.this.f75863p4.m4856z1();
                                String replaceAll = ViewerHelperFragment.this.f75852e4.replaceAll("[\\W]", "_");
                                ViewerHelperFragment.this.f75863p4.m4929c2(m4856z1 + "/" + replaceAll + ".pdf", "application/pdf");
                            }

                            @Override // android.os.AsyncTask
                            protected void onPreExecute() {
                                extractingFragment unused2 = ViewerHelperFragment.f75826v4 = new extractingFragment();
                                ViewerHelperFragment.f75826v4.m44701z2(true);
                                Bundle bundle = new Bundle();
                                bundle.putString("MESSAGE", "Generating PDF");
                                ViewerHelperFragment.f75826v4.m44751k2(bundle);
                                ViewerHelperFragment.f75826v4.m44870c3(false);
                                ViewerHelperFragment.f75826v4.mo29915h3(ViewerHelperFragment.this.m44820L(), "extracting");
                            }
                        }.execute(new Object[0]);
                    }
                }
            }
            return super.mo3709e1(menuItem);
        }
    }

    /* renamed from: e3 */
    public void mo3568e3(Menu menu) {
    }

    /* renamed from: e4 */
    public void m4101e4(String str) {
        ((CollapsingToolbarLayout) this.f75849b4.findViewById(C4804R.C4808id.f86847collapsing_toolbar)).setTitle(str);
    }

    /* renamed from: f3 */
    public void m4100f3(int i) {
        this.f75858k4.setNavigationIcon(C4804R.C4807drawable.f86484back_icon_small);
        this.f75858k4.setNavigationOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!ViewerHelperFragment.this.m44859B().containsKey("Dialog")) {
                    ViewerHelperFragment.this.f75863p4.m4998G1(false);
                    return;
                }
                try {
                    ViewerHelperFragment.this.m44855C().m44464r().m44309I(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out).mo44279x(ViewerHelperFragment.this).mo44289n();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    e.printStackTrace();
                    try {
                        ViewerHelperFragment.this.m44796U().m44464r().m44309I(C4804R.anim.f85978to_fade_in, C4804R.anim.f85979to_fade_out).mo44279x(ViewerHelperFragment.this).mo44289n();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        ImageButton imageButton = (ImageButton) this.f75849b4.findViewById(C4804R.C4808id.f86962menu_button);
        if (imageButton != null) {
            if (this.f75863p4.m4892n1()) {
                imageButton.setVisibility(0);
                imageButton.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) ViewerHelperFragment.this.m44716w().findViewById(C4804R.C4808id.f87019sliding_layout);
                        if (slidingPaneLayout != null) {
                            if (slidingPaneLayout.m42175l()) {
                                slidingPaneLayout.m42184c();
                            } else {
                                slidingPaneLayout.m42172o();
                            }
                        }
                    }
                });
            } else {
                imageButton.setVisibility(8);
            }
        }
        if (this.f75852e4 == null) {
            this.f75852e4 = "Unknown";
        }
        this.f75858k4.setTitle(this.f75852e4);
        if (mo3567m4()) {
            m4130O2();
        }
        try {
            CompressHelper compressHelper = this.f75863p4;
            String str = this.f75851d4;
            String str2 = this.f75852e4;
            compressHelper.m4997H(str, str2, this.f75850c4.getString("Name") + " --- " + this.f75850c4.getString("Title"));
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        this.f75859l4 = (ImageView) this.f75849b4.findViewById(C4804R.C4808id.f87064toolbar_image_view);
        this.f75858k4.m51503x(i);
        final Menu menu = this.f75858k4.getMenu();
        m4096h4(menu);
        mo3568e3(menu);
        this.f75858k4.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.5
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ViewerHelperFragment.this.mo3709e1(menuItem);
                return true;
            }
        });
        mo3978f4();
        this.f75849b4.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.6
            @Override // java.lang.Runnable
            public void run() {
                MenuItem findItem;
                String str3;
                if (menu.findItem(C4804R.C4808id.f86771action_favorites) != null) {
                    ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                    if (viewerHelperFragment.mo4135K3(viewerHelperFragment.mo4079s3()).booleanValue()) {
                        menu.findItem(C4804R.C4808id.f86771action_favorites).setIcon(C4804R.C4807drawable.f86584ic_action_favorite_yellow);
                        findItem = menu.findItem(C4804R.C4808id.f86771action_favorites);
                        str3 = "Remove Favorite";
                    } else {
                        menu.findItem(C4804R.C4808id.f86771action_favorites).setIcon(C4804R.C4807drawable.f86582ic_action_favorite);
                        findItem = menu.findItem(C4804R.C4808id.f86771action_favorites);
                        str3 = "Add Favorite";
                    }
                    findItem.setTitle(str3);
                }
            }
        }, 1000L);
    }

    /* renamed from: f4 */
    public void mo3978f4() {
        m4124T2().m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7339e6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.7
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                if (str != null) {
                    File file = new File(str);
                    if (file.exists()) {
                        ViewerHelperFragment.this.f75859l4.setImageURI(Uri.fromFile(file));
                    }
                }
            }
        });
    }

    /* renamed from: g3 */
    public void m4099g3() {
        mo4088l4();
        DrawerLayout drawerLayout = (DrawerLayout) this.f75849b4.findViewById(C4804R.C4808id.f86880drawer_layout);
        this.f75831J3 = drawerLayout;
        if (drawerLayout != null) {
            drawerLayout.m45146a(new DrawerLayout.DrawerListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.54
                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: a */
                public void mo3583a(View view) {
                    ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                    viewerHelperFragment.f75832K3.setAdapter(new HistoryAdapter(viewerHelperFragment.m44716w(), ViewerHelperFragment.this.f75831J3));
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: b */
                public void mo3582b(View view) {
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: c */
                public void mo3581c(int i) {
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: d */
                public void mo3580d(View view, float f) {
                }
            });
            RecyclerView recyclerView = (RecyclerView) this.f75849b4.findViewById(C4804R.C4808id.f86881drawer_view);
            this.f75832K3 = recyclerView;
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
                this.f75832K3.m42923n(new DividerItemDecoration(m44716w(), 1));
            }
        }
    }

    /* renamed from: g4 */
    public void m4098g4() {
        ImageButton imageButton = (ImageButton) this.f75849b4.findViewById(C4804R.C4808id.f86990previous_button);
        ImageButton imageButton2 = (ImageButton) this.f75849b4.findViewById(C4804R.C4808id.f86967next_button);
        this.f75841T3 = imageButton2;
        this.f75840S3 = imageButton;
        this.f75842U3 = (TextView) this.f75849b4.findViewById(C4804R.C4808id.f86895find_status_label);
        imageButton.setEnabled(false);
        imageButton2.setEnabled(false);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.50
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                int i = viewerHelperFragment.f75835N3;
                ViewerHelperFragment.this.mo4146A3(i == 0 ? viewerHelperFragment.f75854g4.length() - 1 : i - 1);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.51
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                ViewerHelperFragment.this.mo4146A3(viewerHelperFragment.f75835N3 == viewerHelperFragment.f75854g4.length() + (-1) ? 0 : ViewerHelperFragment.this.f75835N3 + 1);
            }
        });
        ActionBar m52860W = ((AppCompatActivity) m44716w()).m52860W();
        if (m52860W != null) {
            m52860W.mo52598m0(false);
        }
        if (!this.f75863p4.m4892n1()) {
            if (m52860W != null) {
                m52860W.mo52586s0(0);
                return;
            }
            return;
        }
        TabHost tabHost = this.f75844W3;
        if (tabHost != null) {
            tabHost.setVisibility(8);
        }
    }

    /* renamed from: h3 */
    public void m4097h3(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CharisSILB.ttf");
        arrayList.add("CharisSILBI.ttf");
        arrayList.add("CharisSILI.ttf");
        arrayList.add("CharisSILR.ttf");
        for (int i = 0; i < arrayList.size(); i++) {
            String str2 = (String) arrayList.get(i);
            String str3 = str + "/" + str2;
            if (!new File(str3).exists()) {
                try {
                    InputStream open = m44716w().getAssets().open(str2);
                    FileOutputStream fileOutputStream = new FileOutputStream(str3);
                    IOUtils.copyLarge(open, fileOutputStream);
                    fileOutputStream.close();
                    open.close();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }
    }

    /* renamed from: h4 */
    public void m4096h4(Menu menu) {
        MenuItem findItem;
        String str;
        this.f75839R3 = menu;
        if (menu.findItem(C4804R.C4808id.f86771action_favorites) != null) {
            if (mo4135K3(mo4079s3()).booleanValue()) {
                menu.findItem(C4804R.C4808id.f86771action_favorites).setIcon(C4804R.C4807drawable.f86584ic_action_favorite_yellow);
                findItem = menu.findItem(C4804R.C4808id.f86771action_favorites);
                str = "Remove Favorite";
            } else {
                menu.findItem(C4804R.C4808id.f86771action_favorites).setIcon(C4804R.C4807drawable.f86582ic_action_favorite);
                findItem = menu.findItem(C4804R.C4808id.f86771action_favorites);
                str = "Add Favorite";
            }
            findItem.setTitle(str);
        }
        menu.findItem(C4804R.C4808id.f86994progress_menu);
        if (menu.findItem(C4804R.C4808id.f86772action_find) == null) {
            return;
        }
        this.f75838Q3 = (SearchView) menu.findItem(C4804R.C4808id.f86772action_find).getActionView();
        this.f75843V3 = menu.findItem(C4804R.C4808id.f86768action_close);
        this.f75838Q3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.27

            /* renamed from: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment$27$1 */
            /* loaded from: classes2.dex */
            class C43461 implements TabHost.TabContentFactory {
                C43461() {
                }

                @Override // android.widget.TabHost.TabContentFactory
                public View createTabContent(String str) {
                    TextView textView = new TextView(ViewerHelperFragment.this.m44716w());
                    textView.setText(str);
                    return textView;
                }
            }

            /* renamed from: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment$27$2 */
            /* loaded from: classes2.dex */
            class C43472 implements TabHost.OnTabChangeListener {
                C43472() {
                }

                @Override // android.widget.TabHost.OnTabChangeListener
                public void onTabChanged(String str) {
                    ViewerHelperFragment.this.mo4077t3(str);
                }
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(String str2) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(String str2) {
                str2.split(" OR ");
                ViewerHelperFragment.this.mo4077t3(str2);
                ViewerHelperFragment.this.m4140G3();
                return true;
            }
        });
        this.f75838Q3.setOnCloseListener(new SearchView.OnCloseListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.28
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            /* renamed from: a */
            public boolean mo4058a() {
                ViewerHelperFragment.this.m4128P2();
                if (ViewerHelperFragment.this.f75863p4.m4892n1()) {
                    ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                    if (viewerHelperFragment.f75844W3 != null) {
                        viewerHelperFragment.m4103d3();
                        ViewerHelperFragment.this.f75844W3.setVisibility(8);
                    }
                } else if (((AppCompatActivity) ViewerHelperFragment.this.m44716w()).m52860W() != null) {
                    ((AppCompatActivity) ViewerHelperFragment.this.m44716w()).m52860W().mo52637N();
                    ((AppCompatActivity) ViewerHelperFragment.this.m44716w()).m52860W().mo52586s0(0);
                }
                return false;
            }
        });
        this.f75839R3 = menu;
        this.f75863p4.m4892n1();
    }

    /* renamed from: i4 */
    public void m4094i4(View view, Bundle bundle) {
        if (bundle != null && bundle.containsKey("Restoring")) {
            this.f75827F3 = true;
            if (bundle.containsKey("Find")) {
                this.f75828G3 = bundle.getString("Find");
                this.f75836O3 = bundle.getInt("FindIndex");
            }
            if (bundle.containsKey("mFinalHTML")) {
                this.f75847Z3 = bundle.getString("mFinalHTML");
            }
            if (bundle.containsKey("mTitle")) {
                this.f75852e4 = bundle.getString("mTitle");
            }
        }
        if (bundle != null && bundle.containsKey("mLastPosition")) {
            this.f75829H3 = bundle.getString("mLastPosition");
        }
        this.f75849b4 = view;
        this.f75853f4 = (WebView) view.findViewById(C4804R.C4808id.f87082webView);
        this.f75850c4 = m44859B().getBundle("DB");
        this.f75851d4 = m44859B().getString("URL");
        TabHost tabHost = (TabHost) view.findViewById(C4804R.C4808id.f86896findtabhost);
        this.f75844W3 = tabHost;
        if (tabHost != null) {
            tabHost.setup();
        }
        this.f75858k4 = (Toolbar) this.f75849b4.findViewById(C4804R.C4808id.f87063toolbar);
        this.f75862o4 = (NestedScrollView) this.f75849b4.findViewById(C4804R.C4808id.f87084webview_scrollview);
        m4099g3();
        this.f75866s4 = new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.52
            @Override // java.lang.Runnable
            public void run() {
                CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Download Completed", 1);
                ViewerHelperFragment.this.m4141F3();
            }
        };
        this.f75867t4 = new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.53
            @Override // java.lang.Runnable
            public void run() {
                CompressHelper.m4921e2(ViewerHelperFragment.this.m44716w(), "Download failed", 1);
                ViewerHelperFragment.this.m4141F3();
            }
        };
        if (mo4082q4()) {
            return;
        }
        ((AppBarLayout) this.f75849b4.findViewById(C4804R.C4808id.f86799appbar)).setExpanded(false);
    }

    /* renamed from: j3 */
    public void m4093j3(String str) {
        m4091k3(str, "base");
    }

    /* renamed from: j4 */
    public void m4092j4() {
        iMDWebView imdwebview = (iMDWebView) this.f75853f4;
        imdwebview.f83201L2 = this.f75850c4.getString("type");
        imdwebview.getSettings().setAllowFileAccess(true);
        imdwebview.getSettings().setDomStorageEnabled(true);
        imdwebview.getSettings().setJavaScriptEnabled(true);
        imdwebview.getSettings().setLoadWithOverviewMode(true);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(m44716w());
        WebSettings settings = imdwebview.getSettings();
        settings.setTextZoom(defaultSharedPreferences.getInt(this.f75850c4.getString("type") + "zoom", 100));
        imdwebview.setScrollbarFadingEnabled(true);
        imdwebview.setScrollBarStyle(16777216);
        imdwebview.setWebChromeClient(new WebChromeClient() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.36
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return ViewerHelperFragment.this.mo3571P3(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return ViewerHelperFragment.this.mo4126Q3(webView, str, str2, jsResult);
            }
        });
        final View view = this.f75849b4;
        imdwebview.setWebViewClient(new WebViewClient() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.37
            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                iMDLogger.m3290j("URL Requested", str);
                super.onLoadResource(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(final WebView webView, final String str) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(false);
                }
                view.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.37.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ViewerHelperFragment.this.mo3569S3(webView, str);
                        } catch (Exception e) {
                            FirebaseCrashlytics.m18030d().m18027g(e);
                        }
                    }
                }, 1000L);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, final String str, Bitmap bitmap) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(true);
                    ViewerHelperFragment.this.f75855h4.setIndeterminate(true);
                }
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                viewerHelperFragment.f75833L3 = true;
                viewerHelperFragment.f75849b4.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.37.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ViewerHelperFragment viewerHelperFragment2 = ViewerHelperFragment.this;
                        if (viewerHelperFragment2.f75833L3) {
                            try {
                                viewerHelperFragment2.mo3569S3(viewerHelperFragment2.f75853f4, str);
                            } catch (Exception e) {
                                FirebaseCrashlytics.m18030d().m18027g(e);
                                e.printStackTrace();
                            }
                        }
                    }
                }, SimpleExoPlayer.f32068s1);
                iMDLogger.m3290j("URL Requested", str);
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(false);
                }
                FragmentActivity m44716w = ViewerHelperFragment.this.m44716w();
                CompressHelper.m4921e2(m44716w, "Oh no! " + str, 0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                WebResourceResponse mo3566n4 = ViewerHelperFragment.this.mo3566n4(webView, webResourceRequest);
                return mo3566n4 != null ? mo3566n4 : super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                WebResourceResponse mo3565o4 = ViewerHelperFragment.this.mo3565o4(webView, str);
                return mo3565o4 != null ? mo3565o4 : super.shouldInterceptRequest(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                iMDLogger.m3290j("URL Requested", str);
                Uri parse = Uri.parse(str);
                String scheme = parse.getScheme();
                String schemeSpecificPart = parse.getSchemeSpecificPart();
                if (scheme.equals("note")) {
                    ViewerHelperFragment.this.m4076t4(schemeSpecificPart.replace("//", "").replace("soheilvbsoheilvbsoheilvb", "$"));
                    return true;
                }
                return ViewerHelperFragment.this.mo3564p4(webView, str, scheme, schemeSpecificPart);
            }
        });
        ((iMDWebView) this.f75853f4).f83198I2 = new ActionModeResponse() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.38
            @Override // net.imedicaldoctor.imd.Utils.ActionModeResponse
            /* renamed from: a */
            public void mo3481a() {
                ViewerHelperFragment.this.m4131N3();
            }

            @Override // net.imedicaldoctor.imd.Utils.ActionModeResponse
            /* renamed from: b */
            public void mo3480b() {
                ViewerHelperFragment.this.m4129O3();
            }
        };
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: k */
    public void mo3599k(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mo4077t3((String) tab.mo52562f());
    }

    /* renamed from: k3 */
    public void m4091k3(String str, String str2) {
        String m4945Y0 = CompressHelper.m4945Y0(this.f75850c4, str2);
        if (!new File(m4945Y0).exists()) {
            new File(m4945Y0).mkdirs();
        }
        File file = new File(m4945Y0 + '/' + str);
        if (file.exists()) {
            file.delete();
        }
        if (file.exists()) {
            return;
        }
        try {
            InputStream open = m44716w().getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            IOUtils.copyLarge(open, fileOutputStream);
            fileOutputStream.close();
            open.close();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("CopyJavascript", "Error in Copying " + str + " to " + m4945Y0 + "/" + str);
        }
    }

    /* renamed from: k4 */
    public void m4090k4() {
        iMDWebView imdwebview = (iMDWebView) this.f75853f4;
        imdwebview.f83201L2 = this.f75850c4.getString("type");
        imdwebview.getSettings().setAllowFileAccess(true);
        imdwebview.getSettings().setDomStorageEnabled(true);
        imdwebview.getSettings().setJavaScriptEnabled(true);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(m44716w());
        WebSettings settings = imdwebview.getSettings();
        settings.setTextZoom(defaultSharedPreferences.getInt(this.f75850c4.getString("type") + "zoom", 100));
        imdwebview.getSettings().setSupportZoom(true);
        imdwebview.getSettings().setBuiltInZoomControls(true);
        imdwebview.getSettings().setDisplayZoomControls(false);
        imdwebview.setWebChromeClient(new WebChromeClient() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.31
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return ViewerHelperFragment.this.mo3571P3(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return ViewerHelperFragment.this.mo4126Q3(webView, str, str2, jsResult);
            }
        });
        final View view = this.f75849b4;
        imdwebview.setWebViewClient(new WebViewClient() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.32
            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                iMDLogger.m3290j("URL Requested", str);
                ViewerHelperFragment.this.mo3570R3(webView, str);
                super.onLoadResource(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(final WebView webView, final String str) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(false);
                }
                view.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.32.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ViewerHelperFragment.this.mo3569S3(webView, str);
                    }
                }, 1000L);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, final String str, Bitmap bitmap) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(true);
                    ViewerHelperFragment.this.f75855h4.setIndeterminate(true);
                }
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                viewerHelperFragment.f75833L3 = true;
                viewerHelperFragment.f75849b4.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.32.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ViewerHelperFragment viewerHelperFragment2 = ViewerHelperFragment.this;
                        if (viewerHelperFragment2.f75833L3) {
                            try {
                                viewerHelperFragment2.mo3569S3(viewerHelperFragment2.f75853f4, str);
                            } catch (Exception e) {
                                FirebaseCrashlytics.m18030d().m18027g(e);
                                e.printStackTrace();
                            }
                        }
                    }
                }, SimpleExoPlayer.f32068s1);
                iMDLogger.m3290j("URL Requested", str);
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                MenuItem menuItem = ViewerHelperFragment.this.f75856i4;
                if (menuItem != null) {
                    menuItem.setVisible(false);
                }
                FragmentActivity m44716w = ViewerHelperFragment.this.m44716w();
                CompressHelper.m4921e2(m44716w, "Oh no! " + str, 0);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                WebResourceResponse mo3566n4 = ViewerHelperFragment.this.mo3566n4(webView, webResourceRequest);
                return mo3566n4 != null ? mo3566n4 : super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return super.shouldInterceptRequest(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                iMDLogger.m3290j("URL Requested", str);
                Uri parse = Uri.parse(str);
                String scheme = parse.getScheme();
                String schemeSpecificPart = parse.getSchemeSpecificPart();
                if (scheme.equals("note")) {
                    ViewerHelperFragment.this.m4076t4(schemeSpecificPart.replace("//", "").replace("soheilvbsoheilvbsoheilvb", "$"));
                    return true;
                }
                return ViewerHelperFragment.this.mo3564p4(webView, str, scheme, schemeSpecificPart);
            }
        });
        ((iMDWebView) this.f75853f4).f83198I2 = new ActionModeResponse() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.33
            @Override // net.imedicaldoctor.imd.Utils.ActionModeResponse
            /* renamed from: a */
            public void mo3481a() {
                ViewerHelperFragment.this.m4131N3();
            }

            @Override // net.imedicaldoctor.imd.Utils.ActionModeResponse
            /* renamed from: b */
            public void mo3480b() {
                ViewerHelperFragment.this.m4129O3();
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: l1 */
    public void mo3541l1() {
        super.mo3541l1();
        m4140G3();
        WebView webView = this.f75853f4;
        if (webView == null) {
            return;
        }
        webView.loadUrl("javascript:element=document.getElementById('orientation');element.parentNode.removeChild(element);");
    }

    /* renamed from: l3 */
    public void m4089l3(final String str) {
        m4083q3(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.29
            @Override // java.lang.Runnable
            public void run() {
                String m4945Y0 = CompressHelper.m4945Y0(ViewerHelperFragment.this.f75850c4, "base");
                if (!new File(m4945Y0).exists()) {
                    new File(m4945Y0).mkdirs();
                }
                File file = new File(m4945Y0 + '/' + str);
                if (file.exists()) {
                    file.delete();
                }
                if (file.exists()) {
                    return;
                }
                try {
                    InputStream open = ViewerHelperFragment.this.m44716w().getAssets().open(str);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    IOUtils.copyLarge(open, fileOutputStream);
                    fileOutputStream.close();
                    open.close();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    iMDLogger.m3294f("CopyJavascript", "Error in Copying " + str + " to " + m4945Y0 + "/" + str);
                }
            }
        }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.30
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    /* renamed from: l4 */
    public void mo4088l4() {
        if (PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("HideStatusBar", false)) {
            float dimension = m44782a0().getDimension(C4804R.dimen.f86462toolbar_padding);
            Toolbar toolbar = this.f75858k4;
            if (toolbar != null) {
                toolbar.setPadding(0, (int) dimension, 0, 0);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }

    /* renamed from: m3 */
    public void m4087m3() {
        m4086n3("base");
    }

    /* renamed from: m4 */
    public boolean mo3567m4() {
        return !m44859B().containsKey("Dialog");
    }

    /* renamed from: n3 */
    public void m4086n3(String str) {
        m4091k3("log4javascript.js", str);
        m4091k3("core.js", str);
        m4091k3("dom.js", str);
        m4091k3("domrange.js", str);
        m4091k3("wrappedrange.js", str);
        m4091k3("wrappedselection.js", str);
        m4091k3("rangy-cssclassapplier.js", str);
        m4091k3("rangy-highlighter.js", str);
        m4091k3("hightlight.js", str);
        m4091k3("find.js", str);
    }

    /* renamed from: n4 */
    public WebResourceResponse mo3566n4(WebView webView, WebResourceRequest webResourceRequest) {
        return null;
    }

    /* renamed from: o3 */
    public void m4085o3(String str) {
        Bundle m4938a1 = this.f75863p4.m4938a1("Dictionary");
        if (m4938a1 == null) {
            new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Dictionary is not installed . you can download it from downloads page.").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.40
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).mo26284p("Download Now", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.39
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ViewerHelperFragment.this.f75863p4.m4989J1(true);
                    ((iMD) ViewerHelperFragment.this.m44716w().getApplicationContext()).f83460Z = "\"Dictionary\"";
                }
            }).m52864I();
            return;
        }
        CDicSearchActivity.CDicSearchFragment cDicSearchFragment = new CDicSearchActivity.CDicSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putBundle("DB", m4938a1);
        bundle.putString("Dialog", str);
        cDicSearchFragment.m44751k2(bundle);
        cDicSearchFragment.m44870c3(true);
        cDicSearchFragment.mo29915h3(m44796U(), "dictionaryDialog");
    }

    /* renamed from: o4 */
    public WebResourceResponse mo3565o4(WebView webView, String str) {
        return null;
    }

    /* renamed from: p3 */
    public void mo4084p3(String str) {
        CompressHelper compressHelper = this.f75863p4;
        String m4972P0 = compressHelper.m4972P0();
        compressHelper.m4885q(m4972P0, "delete from favorites where dbName='" + this.f75863p4.m4963S0(this.f75850c4.getString("Name")) + "' AND dbAddress='" + this.f75863p4.m4963S0(str) + "'");
        m4104c4();
    }

    /* renamed from: p4 */
    public boolean mo3564p4(WebView webView, String str, String str2, String str3) {
        Log.e("Overriding", "Overriding");
        return true;
    }

    /* renamed from: q3 */
    public void m4083q3(final Runnable runnable, final Runnable runnable2) {
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.20
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    runnable.run();
                    observableEmitter.onNext("asdfadf");
                } catch (Exception unused) {
                }
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.21
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                try {
                    runnable2.run();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.22
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    iMDLogger.m3294f("Error occured", th.getMessage());
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        });
    }

    /* renamed from: q4 */
    public boolean mo4082q4() {
        return PreferenceManager.getDefaultSharedPreferences(m44851D()).getBoolean("NestedScroll", true);
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: r */
    public void mo3594r(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /* renamed from: r3 */
    public void m4081r3(final Runnable runnable, final Runnable runnable2) {
        final BeautifulProgressDialog beautifulProgressDialog = new BeautifulProgressDialog(m44716w(), BeautifulProgressDialog.f23703p, null);
        beautifulProgressDialog.m40335o("loading-1.json");
        beautifulProgressDialog.m40334p(true);
        beautifulProgressDialog.m40329u();
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.17
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    runnable.run();
                    observableEmitter.onNext("asdfadf");
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.18
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                beautifulProgressDialog.m40349a();
                try {
                    runnable2.run();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.19
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                beautifulProgressDialog.m40349a();
            }
        });
    }

    /* renamed from: r4 */
    public void m4080r4(Exception exc) {
        exc.printStackTrace();
        FirebaseCrashlytics.m18030d().m18027g(exc);
        AlertDialog.Builder builder = new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme);
        builder.mo26292l("Error occured in loading document. : " + exc).mo26284p("Go Back", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ViewerHelperFragment.this.f75863p4.m4998G1(false);
            }
        }).m52864I();
    }

    /* renamed from: s3 */
    public String mo4079s3() {
        return this.f75851d4;
    }

    /* renamed from: s4 */
    public void m4078s4(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme);
        builder.mo26292l("Error occured in loading document. : " + str).mo26284p("Go Back", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ViewerHelperFragment.this.f75863p4.m4998G1(false);
            }
        }).m52864I();
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: t */
    public void mo3591t(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /* renamed from: t3 */
    public void mo4077t3(String str) {
        String replace = str.replace(" OR ", "\",\"");
        this.f75853f4.loadUrl("javascript:removeAllHighlights(\"aa\");");
        WebView webView = this.f75853f4;
        webView.loadUrl("javascript:console.log('findAction,,,,,' + highlightAllOccurencesOfString([\"" + replace + "\"],\"aa\"))");
    }

    /* renamed from: t4 */
    public void m4076t4(String str) {
        String str2 = "select rowid as _id,* from highlight where save match '" + this.f75863p4.m4963S0(str) + "' AND rowid in (select rowid from highlight where highlight match 'dbName:" + this.f75850c4.getString("Name").replace("'", "''") + " AND dbAddress:" + this.f75863p4.m4963S0(mo4079s3()) + "')";
        iMDLogger.m3290j("Url", "sql : " + str2);
        ArrayList<Bundle> m4946Y = this.f75863p4.m4946Y(m4137I3(), str2);
        if (m4946Y == null || m4946Y.size() == 0) {
            iMDLogger.m3290j("Url", "note size is zero");
            CompressHelper.m4921e2(m44716w(), "Note Not Found", 1);
            return;
        }
        final Bundle bundle = m4946Y.get(0);
        final EditText editText = new EditText(m44716w());
        editText.setTextColor(m44782a0().getColor(C4804R.C4806color.f86093black));
        editText.setText(bundle.getString("note"));
        new AlertDialog.Builder(m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Note").setView(editText).mo26266y("Update", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.35
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                CompressHelper compressHelper = viewerHelperFragment.f75863p4;
                String m4137I3 = viewerHelperFragment.m4137I3();
                compressHelper.m4885q(m4137I3, "Update highlight set note = '" + obj.replace("'", "''") + "' where rowid=" + bundle.getString("_id"));
            }
        }).mo26284p("Delete", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.34
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ViewerHelperFragment viewerHelperFragment = ViewerHelperFragment.this;
                CompressHelper compressHelper = viewerHelperFragment.f75863p4;
                String m4137I3 = viewerHelperFragment.m4137I3();
                compressHelper.m4885q(m4137I3, "delete from highlight where rowid=" + bundle.getString("_id"));
                ViewerHelperFragment.this.m4134L3();
            }
        }).m52864I();
    }

    /* renamed from: u3 */
    public void m4075u3() {
        ((iMDWebView) this.f75853f4).m3435j();
        m44716w().onActionModeFinished(null);
    }

    /* renamed from: u4 */
    public String m4074u4() {
        String m4945Y0 = CompressHelper.m4945Y0(this.f75850c4, "tempp.db");
        if (!new File(m4945Y0).exists()) {
            try {
                SQLiteDatabase.openOrCreateDatabase(m4945Y0, (SQLiteDatabase.CursorFactory) null).execSQL("CREATE TABLE tempp (id varchar(255) PRIMARY KEY NOT NULL  UNIQUE , content Text, date Text);");
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                new File(m4945Y0).delete();
                try {
                    SQLiteDatabase.openOrCreateDatabase(m4945Y0, (SQLiteDatabase.CursorFactory) null).execSQL("CREATE TABLE tempp (id varchar(255) PRIMARY KEY NOT NULL  UNIQUE , content Text, date Text);");
                } catch (Exception unused) {
                }
            }
        }
        return m4945Y0;
    }

    /* renamed from: v3 */
    public Bundle m4073v3(ArrayList<Bundle> arrayList) {
        try {
            return arrayList.get(new Random().nextInt(arrayList.size()));
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            return null;
        }
    }

    /* renamed from: v4 */
    public void m4072v4() {
        ((iMDWebView) this.f75853f4).m3437h();
    }

    /* renamed from: w3 */
    public String m4071w3(ArrayList<String> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() != 0) {
                    return arrayList.get(new Random().nextInt(arrayList.size()));
                }
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }
        return null;
    }

    /* renamed from: w4 */
    public void m4070w4() {
        ((iMDWebView) this.f75853f4).m3436i();
    }

    /* renamed from: x3 */
    public void m4069x3(String str, String str2, String str3, final String str4) {
        final File file = new File(str3);
        if (!file.exists()) {
            Decompress.m4828e(str, str2, new UnzipCompleted() { // from class: net.imedicaldoctor.imd.Fragments.ViewerHelperFragment.55
                @Override // net.imedicaldoctor.imd.Data.UnzipCompleted
                /* renamed from: a */
                public void mo4057a(String str5) {
                    super.mo4057a(str5);
                }

                @Override // net.imedicaldoctor.imd.Data.UnzipCompleted
                /* renamed from: b */
                public void mo4056b(byte[] bArr) {
                    try {
                        FileUtils.writeByteArrayToFile(file, ViewerHelperFragment.this.f75863p4.m4867w(bArr, str4, "127"));
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        iMDLogger.m3294f("getSound", "Error in writing sound to " + file.getAbsolutePath() + " : " + e.toString());
                    }
                    file.deleteOnExit();
                    ViewerHelperFragment.this.m4121U3(file.getAbsolutePath());
                }
            });
            return;
        }
        m4121U3(file.getAbsolutePath());
        file.deleteOnExit();
    }

    /* renamed from: y3 */
    public String m4068y3(String str) {
        String m4074u4 = m4074u4();
        CompressHelper compressHelper = this.f75863p4;
        ArrayList<Bundle> m4946Y = compressHelper.m4946Y(m4074u4, "Select id,content from temp where id ='" + str + "'");
        if (m4946Y == null || m4946Y.size() == 0) {
            return null;
        }
        return m4946Y.get(0).getString("content");
    }

    /* renamed from: z3 */
    public void mo4067z3() {
        Bundle m4907i1;
        try {
            if (PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("lastred", false)) {
                CompressHelper compressHelper = this.f75863p4;
                if (compressHelper.m4907i1(compressHelper.m4946Y(m4137I3(), "select save from highlight where dbName='" + this.f75850c4.getString("Name").replace("'", "''") + "' AND dbAddress='" + this.f75863p4.m4963S0(mo4079s3()) + "' AND save like '%$highlightRed$%'")) == null) {
                    return;
                }
                this.f75853f4.loadUrl("javascript:gotoHighlight('" + m4907i1.getString("save") + "');");
            }
        } catch (Exception unused) {
        }
    }
}
