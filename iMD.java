package net.imedicaldoctor.imd;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Fragments.downloadFragment;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class iMD extends Application {

    /* renamed from: G2 */
    private static Application f83451G2;

    /* renamed from: A2 */
    public int f83452A2;

    /* renamed from: B2 */
    public CompressHelper f83453B2;

    /* renamed from: C2 */
    public VBHelper f83454C2;

    /* renamed from: D2 */
    public downloadFragment f83455D2;

    /* renamed from: E2 */
    private Timer f83456E2;

    /* renamed from: F2 */
    public Handler f83457F2 = new Handler() { // from class: net.imedicaldoctor.imd.iMD.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            iMD.this.m3308b();
        }
    };

    /* renamed from: X */
    public HashSet<String> f83458X;

    /* renamed from: Y */
    public String f83459Y;

    /* renamed from: Z */
    public String f83460Z;

    /* renamed from: s */
    public ArrayList<Bundle> f83461s;

    /* renamed from: z2 */
    public int f83462z2;

    /* renamed from: a */
    public static boolean m3309a() {
        return false;
    }

    /* renamed from: c */
    public static Application m3307c() {
        return f83451G2;
    }

    /* renamed from: d */
    public static Context m3306d() {
        return m3307c().getApplicationContext();
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    /* renamed from: b */
    public void m3308b() {
        try {
            iMDLogger.m3290j("iMD", "Checking Activation Code");
            if (this.f83453B2 == null) {
                this.f83453B2 = new CompressHelper(this);
            }
            if (this.f83454C2 == null) {
                this.f83454C2 = new VBHelper(this);
            }
            int i = 0;
            try {
                i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            } catch (Exception unused) {
            }
            CompressHelper compressHelper = this.f83453B2;
            compressHelper.m4890o0("ActivationCode|||||" + this.f83454C2.m3421l() + "|||||android-" + i).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.iMD.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(String str) throws Throwable {
                    String str2;
                    CompressHelper compressHelper2;
                    try {
                        String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                        if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                            if (splitByWholeSeparator[0].equals("0") && iMD.this.f83454C2.m3432a() != null) {
                                iMD.this.f83453B2.m4933b2(null);
                                iMDLogger.m3294f("SystemExitCAlled", "CheckActivationCode : " + str);
                                System.exit(0);
                                str2 = "nulled Activation Code";
                            }
                            return;
                        }
                        String str3 = splitByWholeSeparator[1];
                        if (str3.equals(PreferenceManager.getDefaultSharedPreferences(iMD.this).getString("ActivationCode", ""))) {
                            compressHelper2 = iMD.this.f83453B2;
                        } else {
                            LocalBroadcastManager.m43863b(iMD.this).m43861d(new Intent("reload"));
                            compressHelper2 = iMD.this.f83453B2;
                        }
                        compressHelper2.m4933b2(str3);
                        str2 = "Setted Activation Code";
                        iMDLogger.m3290j("iMD", str2);
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                    }
                }
            }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.iMD.3
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(Throwable th) throws Throwable {
                    try {
                        iMDLogger.m3294f("CheckActivationCode", "failed");
                    } catch (Exception unused2) {
                    }
                }
            }, new Action() { // from class: net.imedicaldoctor.imd.iMD.4
                @Override // io.reactivex.rxjava3.functions.Action
                public void run() throws Throwable {
                }
            });
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        this.f83453B2.m4978N0();
    }

    /* renamed from: e */
    public Bundle m3305e(ArrayList<Bundle> arrayList) {
        try {
            return arrayList.get(new Random().nextInt(arrayList.size()));
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            return null;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        try {
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark", false)) {
                AppCompatDelegate.m52839N(2);
            } else {
                AppCompatDelegate.m52839N(1);
            }
        } catch (Exception unused) {
        }
        if (m3309a()) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().detectCustomSlowCalls().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().build());
        }
        super.onCreate();
        FirebaseCrashlytics.m18030d().m18031c();
        Timer timer = new Timer();
        this.f83456E2 = timer;
        timer.scheduleAtFixedRate(new TimerTask() { // from class: net.imedicaldoctor.imd.iMD.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                iMD.this.f83457F2.obtainMessage(1).sendToTarget();
            }
        }, 30000L, 600000L);
        f83451G2 = this;
    }
}
