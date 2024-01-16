package net.imedicaldoctor.imd.Fragments;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.text.p014io.PagedChannelRandomAccessSource;
import com.itextpdf.tool.xml.html.HTML;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.neokree.materialtabs.MaterialTabHost;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Data.HistoryAdapter;
import net.imedicaldoctor.imd.NotificationActivity;
import net.imedicaldoctor.imd.TabsPagerAdapter;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.extractingFragment;
import net.imedicaldoctor.imd.iMD;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.Charsets;
import org.apache.commons.p024io.FileUtils;

/* loaded from: classes2.dex */
public class mainActivity extends AppCompatActivity implements ActionBar.TabListener {

    /* renamed from: m3 */
    private static String f76896m3 = null;

    /* renamed from: n3 */
    public static int f76897n3 = 1234;

    /* renamed from: Q2 */
    public ViewPager f76898Q2;

    /* renamed from: R2 */
    private TabsPagerAdapter f76899R2;

    /* renamed from: S2 */
    private ActionBar f76900S2;

    /* renamed from: T2 */
    private MaterialTabHost f76901T2;

    /* renamed from: V2 */
    private extractingFragment f76903V2;

    /* renamed from: W2 */
    private InstallingFragment f76904W2;

    /* renamed from: Y2 */
    private boolean f76906Y2;

    /* renamed from: Z2 */
    private SlidingPaneLayout f76907Z2;

    /* renamed from: a3 */
    private Timer f76908a3;

    /* renamed from: b3 */
    public CompressHelper f76909b3;

    /* renamed from: c3 */
    public RecyclerView f76910c3;

    /* renamed from: d3 */
    private Toolbar f76911d3;

    /* renamed from: e3 */
    private TabLayout f76912e3;

    /* renamed from: f3 */
    private DrawerLayout f76913f3;

    /* renamed from: g3 */
    public String f76914g3;

    /* renamed from: h3 */
    private long f76915h3;

    /* renamed from: U2 */
    private String[] f76902U2 = {"Titles", "Databases", "Favorites", "Content", "Store", "Account"};

    /* renamed from: X2 */
    private ActionMode f76905X2 = null;

    /* renamed from: i3 */
    private String f76916i3 = "android.intent.action.DOWNLOAD_COMPLETE";

    /* renamed from: j3 */
    private IntentFilter f76917j3 = new IntentFilter(this.f76916i3);

    /* renamed from: k3 */
    private BroadcastReceiver f76918k3 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Cursor query;
            try {
                Long valueOf = Long.valueOf(intent.getLongExtra("extra_download_id", 0L));
                if (valueOf.longValue() == mainActivity.this.f76915h3 && (query = ((DownloadManager) mainActivity.this.getSystemService("download")).query(new DownloadManager.Query().setFilterById(valueOf.longValue()))) != null && query.moveToFirst() && query.getInt(query.getColumnIndex(NotificationCompat.f11770C0)) == 8) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setDataAndType(Uri.fromFile(new File(mainActivity.this.f76914g3)), "application/vnd.android.package-archive");
                    intent2.setFlags(268435456);
                    mainActivity.this.startActivity(intent2);
                }
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }
    };

    /* renamed from: l3 */
    public BroadcastReceiver f76919l3 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.18
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            mainActivity.this.m3611B0();
        }
    };

    /* loaded from: classes2.dex */
    public static class DatabaseCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76953I;

        /* renamed from: J */
        public ImageView f76954J;

        public DatabaseCellViewHolder(View view) {
            super(view);
            this.f76953I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76954J = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
        }
    }

    /* renamed from: C0 */
    private void m3610C0() {
        String[] strArr;
        int i;
        this.f76898Q2 = (ViewPager) findViewById(C4804R.C4808id.f86983pager);
        ((iMD) getApplication()).f83462z2 = this.f76898Q2.getId();
        this.f76900S2 = m52860W();
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(m44690E());
        this.f76899R2 = tabsPagerAdapter;
        this.f76898Q2.setAdapter(tabsPagerAdapter);
        this.f76900S2.mo52598m0(false);
        this.f76898Q2.setOffscreenPageLimit(6);
        this.f76898Q2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.22
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            /* renamed from: a */
            public void mo3500a(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            /* renamed from: c */
            public void mo3499c(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            /* renamed from: d */
            public void mo3498d(int i2) {
                ActionBar m52860W;
                CharSequence charSequence;
                mainActivity.this.f76900S2.mo52584t0(i2);
                if (mainActivity.this.f76909b3.m4892n1()) {
                    m52860W = mainActivity.this.m52860W();
                    charSequence = null;
                } else {
                    m52860W = mainActivity.this.m52860W();
                    charSequence = mainActivity.this.f76900S2.mo52575y(i2).mo52562f();
                }
                m52860W.mo52658A0(charSequence);
                mainActivity.this.m3595q0(i2);
            }
        });
        for (String str : this.f76902U2) {
            ActionBar.Tab mo52554n = this.f76900S2.mo52646H().mo52554n(this);
            if (!str.equals("Titles")) {
                if (str.equals("Databases")) {
                    i = C4804R.C4807drawable.f86730tab_databases;
                } else if (str.equals("Favorites")) {
                    i = C4804R.C4807drawable.f86732tab_favorite;
                } else if (!str.equals("Content")) {
                    if (str.equals("Store")) {
                        i = C4804R.C4807drawable.f86731tab_download;
                    } else if (str.equals("Account")) {
                        i = C4804R.C4807drawable.f86724tab_account;
                    } else {
                        this.f76900S2.mo52608h(mo52554n);
                    }
                }
                mo52554n.mo52556l(i);
                this.f76900S2.mo52608h(mo52554n);
            }
            mo52554n.mo52556l(C4804R.C4807drawable.f86733tab_search);
            this.f76900S2.mo52608h(mo52554n);
        }
        this.f76900S2.mo52586s0(2);
        int i2 = 1;
        if (PreferenceManager.getDefaultSharedPreferences(this).contains("Tab")) {
            String string = PreferenceManager.getDefaultSharedPreferences(this).getString("Tab", "");
            ArrayList arrayList = new ArrayList(Arrays.asList(this.f76902U2));
            if (arrayList.contains(string)) {
                i2 = arrayList.indexOf(string);
            }
        }
        this.f76898Q2.setCurrentItem(i2);
        this.f76900S2.mo52584t0(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public void m3609D0() {
        int i;
        this.f76898Q2 = (ViewPager) findViewById(C4804R.C4808id.f86983pager);
        ((iMD) getApplication()).f83462z2 = this.f76898Q2.getId();
        int i2 = 0;
        this.f76898Q2.setVisibility(0);
        TextView textView = (TextView) findViewById(C4804R.C4808id.f86954loading_first);
        if (textView != null) {
            textView.setVisibility(8);
        }
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(m44690E());
        this.f76899R2 = tabsPagerAdapter;
        this.f76898Q2.setAdapter(tabsPagerAdapter);
        this.f76898Q2.setOffscreenPageLimit(6);
        this.f76912e3.setupWithViewPager(this.f76898Q2);
        while (true) {
            String[] strArr = this.f76902U2;
            if (i2 >= strArr.length) {
                break;
            }
            String str = strArr[i2];
            TabLayout.Tab m24905z = this.f76912e3.m24905z(i2);
            if (!str.equals("Titles")) {
                if (str.equals("Databases")) {
                    i = C4804R.C4807drawable.f86730tab_databases;
                } else if (str.equals("Favorites")) {
                    i = C4804R.C4807drawable.f86732tab_favorite;
                } else if (!str.equals("Content")) {
                    if (str.equals("Store")) {
                        i = C4804R.C4807drawable.f86731tab_download;
                    } else if (str.equals("Account")) {
                        i = C4804R.C4807drawable.f86724tab_account;
                    } else {
                        i2++;
                    }
                }
                m24905z.m24866w(i);
                i2++;
            }
            m24905z.m24866w(C4804R.C4807drawable.f86733tab_search);
            i2++;
        }
        int i3 = 1;
        if (PreferenceManager.getDefaultSharedPreferences(this).contains("Tab")) {
            String string = PreferenceManager.getDefaultSharedPreferences(this).getString("Tab", "");
            ArrayList arrayList = new ArrayList(Arrays.asList(this.f76902U2));
            if (arrayList.contains(string)) {
                i3 = arrayList.indexOf(string);
            }
        } else {
            ArrayList<Bundle> arrayList2 = ((iMD) getApplicationContext()).f83461s;
            if (arrayList2 == null || arrayList2.size() == 0) {
                this.f76898Q2.setCurrentItem(4);
                return;
            }
        }
        this.f76898Q2.setCurrentItem(i3);
    }

    /* renamed from: G0 */
    private boolean m3606G0() {
        boolean z;
        Iterator<String> it2 = this.f76909b3.m4922e1().iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            File file = new File(next);
            String[] list = file.list(new FilenameFilter() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.4
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.endsWith(".zip") || str.endsWith(".zipp");
                }
            });
            if (list != null && list.length != 0) {
                return true;
            }
            String[] list2 = file.list(new FilenameFilter() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.5
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.endsWith(".zip.1") || str.endsWith(".zipp.1");
                }
            });
            if (list2 != null && list2.length != 0) {
                for (String str : list2) {
                    for (int i = 1; i < 11; i++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(next);
                        sb.append("/");
                        sb.append(str.replace(".zip.1", ".zip." + i).replace(".zipp.1", ".zipp." + i));
                        String sb2 = sb.toString();
                        if (!new File(sb2).exists() || new File(sb2).length() < 52428800) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                    if (z) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    /* renamed from: H0 */
    private void m3605H0() {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            getCurrentFocus().clearFocus();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: n0 */
    private void m3598n0(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m3598n0(file2);
            }
        }
        file.delete();
    }

    /* renamed from: y0 */
    private boolean m3585y0() {
        VBHelper vBHelper = new VBHelper(this);
        if (vBHelper.m3432a() == null) {
            finish();
            startActivity(new Intent(this, activationActivity.class));
            return false;
        }
        try {
            int length = TextUtils.split(vBHelper.m3410w(vBHelper.m3421l()).replace("||", "::"), "::").length;
            return true;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            return true;
        }
    }

    /* renamed from: A0 */
    public void m3612A0() {
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey("title")) {
            return;
        }
        String string = extras.getString("title");
        String string2 = extras.getString("content");
        String string3 = extras.getString(HTML.Tag.f65852C);
        getIntent().replaceExtras(new Bundle());
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("title", string);
        intent.putExtra("content", string2);
        intent.putExtra(HTML.Tag.f65852C, string3);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* renamed from: B0 */
    public void m3611B0() {
        try {
            if (m3606G0() && m44690E().m44466q0("Installing") == null) {
                InstallingFragment installingFragment = new InstallingFragment();
                this.f76904W2 = installingFragment;
                installingFragment.m44751k2(null);
                this.f76904W2.m44870c3(false);
                this.f76904W2.mo29915h3(m44690E(), "Installing");
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: E0 */
    public long m3608E0(String str) {
        Cursor query = ((DownloadManager) getSystemService("download")).query(new DownloadManager.Query());
        query.moveToFirst();
        new ArrayList();
        for (int i = 0; i < query.getCount(); i++) {
            query.moveToPosition(i);
            String string = query.getString(query.getColumnIndex("uri"));
            long j = query.getLong(query.getColumnIndex("_id"));
            iMDLogger.m3294f("URI", string);
            if (string.startsWith(str)) {
                return j;
            }
        }
        return 0L;
    }

    /* renamed from: F0 */
    public int m3607F0() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: I0 */
    public void m3604I0() {
        this.f76913f3.m45162K(3);
    }

    /* renamed from: J0 */
    public void m3603J0() {
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.16
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                Iterator<String> it2 = mainActivity.this.f76909b3.m4895m1().iterator();
                while (it2.hasNext()) {
                    String next = it2.next();
                    iMDLogger.m3294f("Root path : ", next);
                    mainActivity.this.f76909b3.f73785c = null;
                    mainActivity.this.f76909b3.m4968Q1(new File(next).listFiles());
                }
                byte[] bytes = mainActivity.this.f76909b3.f73785c.getBytes(Charsets.UTF_8);
                try {
                    CompressHelper compressHelper = mainActivity.this.f76909b3;
                    String encodeToString = Base64.encodeToString(CompressHelper.m4954V0(bytes), 0);
                    CompressHelper compressHelper2 = mainActivity.this.f76909b3;
                    compressHelper2.m4887p0("checkAccount|||||" + new VBHelper(mainActivity.this).m3421l() + "|||||" + encodeToString).m7300i6(Schedulers.m5370e()).m7193t4(Schedulers.m5370e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.16.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(String str) throws Throwable {
                            StringUtils.splitByWholeSeparator(str, "|||||");
                        }
                    }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.16.2
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(Throwable th) throws Throwable {
                        }
                    }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.16.3
                        @Override // io.reactivex.rxjava3.functions.Action
                        public void run() throws Throwable {
                        }
                    });
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
                observableEmitter.onComplete();
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(Schedulers.m5370e()).mo6065a(new DisposableObserver<String>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.17
            @Override // io.reactivex.rxjava3.core.Observer
            /* renamed from: b */
            public void onNext(@NonNull String str) {
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(@NonNull Throwable th) {
            }
        });
    }

    /* renamed from: K0 */
    public void m3602K0() {
        LocalBroadcastManager.m43863b(this).m43861d(new Intent("reload"));
        this.f76904W2.mo27002R2();
        this.f76904W2 = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.appcompat.app.AppCompatCallback
    /* renamed from: e */
    public void mo3601e(androidx.appcompat.view.ActionMode actionMode) {
        super.mo3601e(actionMode);
        iMDLogger.m3290j("ACtionMode", "onSupportActionModeStarted");
        Menu mo52409e = actionMode.mo52409e();
        if (((WebView) findViewById(C4804R.C4808id.f87082webView)) == null) {
            return;
        }
        for (int i = 0; i < mo52409e.size(); i++) {
            MenuItem item = mo52409e.getItem(i);
            Drawable icon = item.getIcon();
            if (icon != null) {
                icon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                icon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                item.setIcon(icon);
                if (!item.getTitle().equals("Share")) {
                    item.getTitle().equals("Web Search");
                }
            }
        }
        actionMode.mo52408f().inflate(C4804R.C4811menu.f87408webview2_menu, mo52409e);
        actionMode.mo52405k();
        LinearLayout linearLayout = (LinearLayout) findViewById(C4804R.C4808id.f86926highlight_bar);
        linearLayout.setVisibility(0);
        linearLayout.startAnimation(AnimationUtils.loadAnimation(this, C4804R.anim.f85977snackbar_show_animation));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.appcompat.app.AppCompatCallback
    /* renamed from: f */
    public void mo3600f(androidx.appcompat.view.ActionMode actionMode) {
        super.mo3600f(actionMode);
        iMDLogger.m3290j("ACtionMode", "onSupportActionModeFinished");
        if (((WebView) findViewById(C4804R.C4808id.f87082webView)) == null) {
            return;
        }
        final LinearLayout linearLayout = (LinearLayout) findViewById(C4804R.C4808id.f86926highlight_bar);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C4804R.anim.f85976snackbar_hide_animation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(8);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        linearLayout.startAnimation(loadAnimation);
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: k */
    public void mo3599k(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        ActionBar m52860W;
        CharSequence charSequence;
        this.f76898Q2.setCurrentItem(tab.mo52564d());
        f76896m3 = String.valueOf(tab.mo52564d());
        if (this.f76909b3.m4892n1()) {
            m52860W = m52860W();
            charSequence = null;
        } else {
            m52860W = m52860W();
            charSequence = tab.mo52562f();
        }
        m52860W.mo52658A0(charSequence);
        ((iMD) getApplication()).f83452A2 = tab.mo52564d();
        mo44676S();
    }

    /* renamed from: o0 */
    public void m3597o0() {
        Bundle m4969Q0;
        try {
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("openaftercrash", true)) {
                CompressHelper compressHelper = this.f76909b3;
                Bundle m4907i1 = compressHelper.m4907i1(compressHelper.m4946Y(compressHelper.m4971P1(), "SELECT * FROM recent order by id desc limit 1"));
                if (m4907i1 == null || (m4969Q0 = this.f76909b3.m4969Q0("Name", m4907i1.getString("dbName"))) == null) {
                    return;
                }
                this.f76909b3.m4883q1(m4969Q0, m4907i1.getString("dbAddress"), null, null);
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        iMDLogger.m3290j("ACtionMode", "onActionModeFinished");
        if (((WebView) findViewById(C4804R.C4808id.f87082webView)) == null) {
            return;
        }
        if (actionMode == null) {
            actionMode = this.f76905X2;
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
            r6.f76905X2 = r7
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
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.mainActivity.onActionModeStarted(android.view.ActionMode):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            return;
        }
        LocalBroadcastManager.m43863b(this).m43861d(new Intent("referesh.account.visible"));
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        SlidingPaneLayout slidingPaneLayout;
        if (this.f76909b3.m4892n1()) {
            boolean m4998G1 = this.f76909b3.m4998G1(false);
            if (!m4998G1 && (slidingPaneLayout = (SlidingPaneLayout) findViewById(C4804R.C4808id.f87019sliding_layout)) != null) {
                if (slidingPaneLayout.m42175l()) {
                    m4998G1 = this.f76909b3.m4998G1(true);
                } else {
                    slidingPaneLayout.m42172o();
                }
            }
            if (m4998G1) {
                return;
            }
        }
        if (m44690E().m44439z0() > 0) {
            m44690E().m44480l1();
            m44690E().m44479m(new FragmentManager.OnBackStackChangedListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.6
                @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
                public void onBackStackChanged() {
                    mainActivity mainactivity = mainActivity.this;
                    mainactivity.m3595q0(mainactivity.f76898Q2.getCurrentItem());
                }
            });
            this.f76906Y2 = false;
        } else if (this.f76906Y2) {
            super.onBackPressed();
        } else {
            this.f76906Y2 = true;
            CompressHelper.m4921e2(this, "Please click BACK again to exit", 0);
            new Handler().postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    mainActivity.this.f76906Y2 = false;
                }
            }, SimpleExoPlayer.f32068s1);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!this.f76909b3.m4892n1() || this.f76907Z2 == null || this.f76900S2 == null || this.f76898Q2 == null) {
            return;
        }
        iMDLogger.m3294f("mainActivity", "ON Configuration changed");
        iMDLogger.m3294f("mainActivity", "isOpen = " + this.f76907Z2.m42175l());
        iMDLogger.m3294f("mainActivity", "isSlidable = " + this.f76907Z2.m42174m());
        this.f76907Z2.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.8
            @Override // java.lang.Runnable
            public void run() {
                ActionBar actionBar;
                int i;
                if (mainActivity.this.f76907Z2.m42175l()) {
                    mainActivity mainactivity = mainActivity.this;
                    mainactivity.m3595q0(mainactivity.f76898Q2.getCurrentItem());
                    actionBar = mainActivity.this.f76900S2;
                    i = 2;
                } else {
                    mainActivity.this.m3596p0();
                    actionBar = mainActivity.this.f76900S2;
                    i = 0;
                }
                actionBar.mo52586s0(i);
            }
        }, 700L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        FrameLayout.LayoutParams layoutParams;
        View view;
        super.onCreate(bundle);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark", false)) {
            AppCompatDelegate.m52839N(2);
        } else {
            AppCompatDelegate.m52839N(1);
        }
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("wakelock", true)) {
            getWindow().addFlags(128);
        }
        try {
            this.f76909b3 = new CompressHelper(this);
            Timer timer = new Timer();
            this.f76908a3 = timer;
            timer.schedule(new TimerTask() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.10
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    mainActivity.this.m3584z0(false);
                }
            }, 40000L);
            setContentView(C4804R.C4810layout.f87102activity_main);
            this.f76909b3.m4892n1();
            setTitle("");
            Toolbar toolbar = (Toolbar) findViewById(C4804R.C4808id.f87063toolbar);
            this.f76911d3 = toolbar;
            toolbar.m51527R();
            m52851e0(this.f76911d3);
            m52860W();
            this.f76911d3.setNavigationOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    mainActivity.this.m3604I0();
                }
            });
            this.f76912e3 = (TabLayout) findViewById(C4804R.C4808id.f87042tabs);
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(C4804R.C4808id.f86880drawer_layout);
            this.f76913f3 = drawerLayout;
            drawerLayout.m45146a(new DrawerLayout.DrawerListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.12
                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: a */
                public void mo3583a(View view2) {
                    mainActivity mainactivity = mainActivity.this;
                    mainactivity.f76910c3.setAdapter(new HistoryAdapter(mainactivity, mainactivity.f76913f3));
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: b */
                public void mo3582b(View view2) {
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: c */
                public void mo3581c(int i) {
                }

                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: d */
                public void mo3580d(View view2, float f) {
                }
            });
            RecyclerView recyclerView = (RecyclerView) findViewById(C4804R.C4808id.f86881drawer_view);
            this.f76910c3 = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
            this.f76910c3.m42923n(new DividerItemDecoration(this, 1));
            registerReceiver(this.f76918k3, this.f76917j3);
            if (this.f76909b3.m4892n1()) {
                SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(C4804R.C4808id.f87019sliding_layout);
                this.f76907Z2 = slidingPaneLayout;
                slidingPaneLayout.setShadowResourceLeft(C4804R.C4807drawable.f86711slide_shadow);
                this.f76907Z2.setSliderFadeColor(Color.parseColor("#FFFFFF"));
                FrameLayout frameLayout = (FrameLayout) findViewById(C4804R.C4808id.f86865detail_container);
                if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("Fullscreen", true)) {
                    frameLayout.setLayoutParams(new SlidingPaneLayout.LayoutParams(-1, -1));
                }
                slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.13
                    @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
                    /* renamed from: a */
                    public void mo3579a(View view2, float f) {
                    }

                    @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
                    /* renamed from: b */
                    public void mo3578b(View view2) {
                        mainActivity mainactivity = mainActivity.this;
                        mainactivity.m3595q0(mainactivity.f76898Q2.getCurrentItem());
                        if (mainActivity.this.f76900S2 != null) {
                            mainActivity.this.f76900S2.mo52586s0(2);
                        }
                    }

                    @Override // androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
                    /* renamed from: c */
                    public void mo3577c(View view2) {
                        mainActivity.this.m3596p0();
                        if (mainActivity.this.f76900S2 != null) {
                            mainActivity.this.f76900S2.mo52586s0(0);
                        }
                    }
                });
                this.f76907Z2.m42172o();
            }
            iMDLogger.m3290j("OnCreate", "OnCreate");
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("HideStatusBar", false)) {
                if (this.f76909b3.m4892n1()) {
                    getWindow().setFlags(PagedChannelRandomAccessSource.f60487g, PagedChannelRandomAccessSource.f60487g);
                    layoutParams = (FrameLayout.LayoutParams) this.f76907Z2.getLayoutParams();
                    layoutParams.setMargins(0, -m3607F0(), 0, 0);
                    view = this.f76907Z2;
                } else {
                    getWindow().setFlags(PagedChannelRandomAccessSource.f60487g, PagedChannelRandomAccessSource.f60487g);
                    layoutParams = (FrameLayout.LayoutParams) this.f76913f3.getLayoutParams();
                    layoutParams.setMargins(0, -m3607F0(), 0, 0);
                    view = this.f76913f3;
                }
                view.setLayoutParams(layoutParams);
                float dimension = getResources().getDimension(C4804R.dimen.f86462toolbar_padding);
                AppBarLayout appBarLayout = (AppBarLayout) findViewById(C4804R.C4808id.f86799appbar);
                if (appBarLayout != null) {
                    appBarLayout.setPadding(0, (int) dimension, 0, 0);
                }
            }
            m3585y0();
            this.f76909b3.m4993I0(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.14
                @Override // java.lang.Runnable
                public void run() {
                    mainActivity.this.f76909b3.m4902k0();
                }
            }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.15
                @Override // java.lang.Runnable
                public void run() {
                    mainActivity.this.m3609D0();
                    if (mainActivity.this.getIntent().hasExtra(AppMeasurement.f42513b)) {
                        mainActivity.this.m3597o0();
                    }
                }
            });
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            unregisterReceiver(this.f76918k3);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LocalBroadcastManager.m43863b(this).m43859f(this.f76919l3);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @androidx.annotation.NonNull String[] strArr, @androidx.annotation.NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.m43863b(this).m43862c(this.f76919l3, new IntentFilter("checkzip"));
        new Handler().postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.2
            @Override // java.lang.Runnable
            public void run() {
                mainActivity.this.m3611B0();
            }
        }, 1000L);
        try {
            ((iMD) getApplicationContext()).m3308b();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        m3612A0();
        iMD imd = (iMD) getApplicationContext();
        String str = imd.f83460Z;
        if (str == null || str.length() <= 0) {
            return;
        }
        final String str2 = imd.f83460Z;
        imd.f83460Z = null;
        this.f76898Q2.setCurrentItem(4);
        new Handler().postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.3
            @Override // java.lang.Runnable
            public void run() {
                downloadFragment downloadfragment = ((iMD) mainActivity.this.getApplicationContext()).f83455D2;
                if (downloadfragment.m3742N3()) {
                    downloadfragment.m3737Q2();
                }
                downloadfragment.f76673S3.check(C4804R.C4808id.f86966newest);
                downloadfragment.f76668N3.m51655i0(str2, true);
            }
        }, 1000L);
    }

    /* renamed from: p0 */
    public void m3596p0() {
        int i;
        Fragment m44466q0;
        if (this.f76909b3.m4892n1()) {
            int currentItem = this.f76898Q2.getCurrentItem();
            String str = "android:switcher:" + this.f76898Q2.getId() + ":";
            while (i < this.f76902U2.length) {
                String str2 = str + i;
                FragmentManager m44690E = m44690E();
                if (i != currentItem) {
                    m44466q0 = m44690E.m44466q0(str2);
                    i = m44466q0 == null ? i + 1 : 0;
                    m44466q0.m44725t2(false);
                    m44466q0.m44840F2(false);
                } else {
                    Fragment m44469p0 = m44690E.m44469p0(C4804R.C4808id.container);
                    if (m44469p0 != null) {
                        Fragment m44466q02 = m44690E().m44466q0(str2);
                        if (m44466q02.m44756j0().endsWith(m44469p0.m44756j0())) {
                            m44466q02.m44725t2(false);
                            m44466q02.m44840F2(false);
                        }
                        m44469p0.m44725t2(false);
                        m44469p0.m44840F2(false);
                    } else {
                        m44466q0 = m44690E().m44466q0(str2);
                        if (m44466q0 == null) {
                        }
                        m44466q0.m44725t2(false);
                        m44466q0.m44840F2(false);
                    }
                }
            }
        }
        mo44676S();
    }

    /* renamed from: q0 */
    public void m3595q0(int i) {
        if (this.f76909b3.m4892n1()) {
            String str = "android:switcher:" + this.f76898Q2.getId() + ":";
            for (int i2 = 0; i2 < this.f76902U2.length; i2++) {
                String str2 = str + i2;
                FragmentManager m44690E = m44690E();
                if (i2 != i) {
                    Fragment m44466q0 = m44690E.m44466q0(str2);
                    if (m44466q0 != null) {
                        m44466q0.m44725t2(false);
                        m44466q0.m44840F2(false);
                    }
                } else {
                    Fragment m44469p0 = m44690E.m44469p0(C4804R.C4808id.container);
                    if (m44469p0 != null) {
                        Fragment m44466q02 = m44690E().m44466q0(str2);
                        if (m44466q02.m44756j0().endsWith(m44469p0.m44756j0())) {
                            m44466q02.m44725t2(false);
                            m44466q02.m44840F2(false);
                            m44469p0.m44725t2(true);
                            m44469p0.m44840F2(true);
                        } else {
                            m44469p0.m44725t2(false);
                            m44469p0.m44840F2(false);
                        }
                    } else {
                        Fragment m44466q03 = m44690E().m44466q0(str2);
                        if (m44466q03 != null) {
                            m44466q03.m44725t2(true);
                            m44466q03.m44840F2(true);
                        }
                    }
                }
            }
        }
        mo44676S();
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: r */
    public void mo3594r(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(C4804R.anim.f85970from_fade_in, C4804R.anim.f85971from_fade_out);
    }

    @Override // androidx.appcompat.app.ActionBar.TabListener
    /* renamed from: t */
    public void mo3591t(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (this.f76909b3.m4892n1()) {
            String str = "android:switcher:" + this.f76898Q2.getId() + ":" + tab.mo52564d();
            if (m44690E().m44466q0(str) != null) {
                m44690E().m44474n1(str, 1);
            }
        }
    }

    /* renamed from: x0 */
    public String m3586x0(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            int i = b & UnsignedBytes.f54281b;
            if (i < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }

    /* renamed from: z0 */
    public void m3584z0(final boolean z) {
        Log.e("checkAppUpdate", "checking app update");
        try {
            if (new File(this.f76909b3.m5004E1() + "/version.txt").exists()) {
                new File(this.f76909b3.m5004E1() + "/version.txt").delete();
            }
            this.f76909b3.m4987K0(this.f76909b3.m4991J() + "/v.txt", this.f76909b3.m5004E1() + "/version.txt").m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.19
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(String str) throws Throwable {
                }
            }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.20
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(Throwable th) throws Throwable {
                    try {
                        iMDLogger.m3294f("checkAppUpdate", "Error in checking update " + th.getMessage());
                        th.printStackTrace();
                        if (z) {
                            CompressHelper.m4921e2(mainActivity.this, "Error in checking update", 1);
                        }
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                    }
                }
            }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.21
                @Override // io.reactivex.rxjava3.functions.Action
                public void run() throws Throwable {
                    try {
                        String replace = FileUtils.readFileToString(new File(mainActivity.this.f76909b3.m5004E1() + "/version.txt"), "UTF-8").replace("\n", "");
                        int i = mainActivity.this.getPackageManager().getPackageInfo(mainActivity.this.getPackageName(), 0).versionCode;
                        final int intValue = Integer.valueOf(replace).intValue();
                        iMDLogger.m3290j("checkAppUpdate", "current version : " + i + " , UpdateVersion : " + intValue);
                        if (intValue > i) {
                            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity.this);
                            if (defaultSharedPreferences.getBoolean(intValue + "update", true) || z) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this, C4804R.style.f88094alertDialogTheme);
                                builder.mo26292l("An Update is available (version " + intValue + ") . \nWe strongly suggest that you update the app.").mo26266y("Download Update", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.21.3
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                        String packageName = mainActivity.this.getPackageName();
                                        try {
                                            mainActivity mainactivity = mainActivity.this;
                                            mainactivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                                        } catch (ActivityNotFoundException unused) {
                                            mainActivity mainactivity2 = mainActivity.this;
                                            mainactivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
                                        }
                                    }
                                }).mo26278s("Ignore this version", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.21.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(mainActivity.this).edit();
                                        edit.putBoolean(intValue + "update", false).commit();
                                    }
                                }).mo26284p("Remind me later", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.mainActivity.21.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                    }
                                }).m52864I();
                            }
                        } else if (z) {
                            CompressHelper.m4921e2(mainActivity.this, "You have the latest version", 1);
                        }
                        new File(mainActivity.this.f76909b3.m5004E1() + "/version.txt").delete();
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        if (z) {
                            CompressHelper.m4921e2(mainActivity.this, "Error in Checking update", 1);
                        }
                        iMDLogger.m3294f("checkAppUpdate", "Error in reading version.txt " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception unused) {
            Log.e("MainActivity", "Error in app update");
        }
    }
}
