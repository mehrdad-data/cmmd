package net.imedicaldoctor.imd.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.iMDLogger;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.p020io.inputstream.ZipInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.IOUtils;

/* loaded from: classes2.dex */
public class InstallingFragment extends DialogFragment {

    /* renamed from: g4 */
    private Bundle f74685g4;

    /* renamed from: h4 */
    private String f74686h4;

    /* renamed from: i4 */
    private TextView f74687i4;

    /* renamed from: j4 */
    private TextView f74688j4;

    /* renamed from: k4 */
    private ProgressBar f74689k4;

    /* renamed from: l4 */
    private TextView f74690l4;

    /* renamed from: m4 */
    private ImageView f74691m4;

    /* renamed from: n4 */
    private View f74692n4;

    /* renamed from: o4 */
    private Timer f74693o4;

    /* renamed from: p4 */
    private int f74694p4;

    /* renamed from: q4 */
    private Activity f74695q4;

    /* renamed from: r4 */
    private boolean f74696r4;

    /* renamed from: s4 */
    private Button f74697s4;

    /* renamed from: t4 */
    private Button f74698t4;

    /* renamed from: u4 */
    private String f74699u4;

    /* renamed from: v4 */
    public Handler f74700v4 = new Handler() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            InstallingFragment.this.f74694p4++;
            if (InstallingFragment.this.f74694p4 == 7) {
                InstallingFragment.this.f74694p4 = 1;
            }
            String str = "";
            for (int i = 0; i < InstallingFragment.this.f74694p4; i++) {
                str = str + ".";
            }
            InstallingFragment.this.f74690l4.setText(str);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j3 */
    public void m4532j3() {
        this.f74696r4 = true;
        do {
        } while (this.f74696r4);
    }

    /* renamed from: l3 */
    static /* synthetic */ String m4530l3(InstallingFragment installingFragment, String str) {
        installingFragment.f74699u4 = str;
        return str;
    }

    /* renamed from: n3 */
    static /* synthetic */ boolean m4528n3(InstallingFragment installingFragment, boolean z) {
        installingFragment.f74696r4 = z;
        return z;
    }

    /* renamed from: o3 */
    static /* synthetic */ void m4527o3(InstallingFragment installingFragment) {
        installingFragment.m4532j3();
    }

    /* renamed from: A3 */
    public void m4533A3() {
        Timer timer = new Timer();
        this.f74693o4 = timer;
        timer.scheduleAtFixedRate(new TimerTask() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.6
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                InstallingFragment.this.f74700v4.obtainMessage(1).sendToTarget();
            }
        }, 0L, 1000L);
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: M0 */
    public void mo3640M0(Activity activity) {
        super.mo3640M0(activity);
        this.f74695q4 = activity;
    }

    @Override // androidx.fragment.app.DialogFragment
    /* renamed from: X2 */
    public Dialog mo3313X2(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m44716w());
        View inflate = m44716w().getLayoutInflater().inflate(C4804R.C4810layout.f87158fragment_installing, (ViewGroup) null);
        this.f74692n4 = inflate;
        this.f74687i4 = (TextView) inflate.findViewById(C4804R.C4808id.f86942installing_label);
        this.f74688j4 = (TextView) this.f74692n4.findViewById(C4804R.C4808id.f86855database_name);
        this.f74690l4 = (TextView) this.f74692n4.findViewById(C4804R.C4808id.f86993progress_label);
        this.f74689k4 = (ProgressBar) this.f74692n4.findViewById(C4804R.C4808id.f86992progress_bar);
        this.f74691m4 = (ImageView) this.f74692n4.findViewById(C4804R.C4808id.f87031stethoscope);
        this.f74697s4 = (Button) this.f74692n4.findViewById(C4804R.C4808id.cancel_button);
        this.f74698t4 = (Button) this.f74692n4.findViewById(C4804R.C4808id.f86846close_button);
        Typeface createFromAsset = Typeface.createFromAsset(m44716w().getAssets(), "fonts/HelveticaNeue-Light.otf");
        this.f74687i4.setTypeface(createFromAsset);
        this.f74688j4.setTypeface(createFromAsset);
        this.f74689k4.setProgress(0);
        m44716w().setFinishOnTouchOutside(false);
        final CompressHelper compressHelper = new CompressHelper(m44716w());
        final VBHelper vBHelper = new VBHelper(m44716w());
        Observable m7193t4 = Observable.m7156x1(new ObservableOnSubscribe<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.1
            /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
                jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:193:0x05d0
                	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
                	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
                	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
                */
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@io.reactivex.rxjava3.annotations.NonNull io.reactivex.rxjava3.core.ObservableEmitter<android.os.Bundle> r44) throws java.lang.Throwable {
                /*
                    Method dump skipped, instructions count: 2432
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.InstallingFragment.C39141.mo3518a(io.reactivex.rxjava3.core.ObservableEmitter):void");
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e());
        final DisposableObserver<Bundle> disposableObserver = new DisposableObserver<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.2
            @Override // io.reactivex.rxjava3.core.Observer
            /* renamed from: b */
            public void onNext(@NonNull Bundle bundle2) {
                if (bundle2.containsKey("progress")) {
                    String string = bundle2.getString("labelText");
                    String string2 = bundle2.getString("progress");
                    TextView textView = InstallingFragment.this.f74688j4;
                    textView.setText(string + " ( " + string2 + "% )");
                    return;
                }
                String string3 = bundle2.getString("error");
                int i = bundle2.getInt("current");
                int i2 = bundle2.getInt("total");
                String string4 = bundle2.getString("dbName");
                InstallingFragment.this.f74687i4.setText(bundle2.getString("labelText"));
                InstallingFragment.this.f74688j4.setText(string4);
                InstallingFragment.this.f74689k4.setMax(i2);
                InstallingFragment.this.f74689k4.setProgress(i + 1);
                if (string3 == null || string3.length() == 0 || !InstallingFragment.this.f74696r4) {
                    return;
                }
                AlertDialog.Builder builder2 = new AlertDialog.Builder(InstallingFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme);
                builder2.setTitle("Error Occured in " + string4).mo26292l(string3).mo26266y("Ok . Continue", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.2.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        InstallingFragment.this.f74696r4 = false;
                    }
                }).mo26284p("Close App", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        Process.killProcess(Process.myPid());
                    }
                }).m52864I();
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                InstallingFragment.this.f74693o4.cancel();
                LocalBroadcastManager.m43863b(InstallingFragment.this.m4518x3()).m43861d(new Intent("reload"));
                InstallingFragment.this.mo27002R2();
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(@NonNull Throwable th) {
                iMDLogger.m3294f("InstallingFragment", "Error occured on installing : " + th.getMessage());
                th.printStackTrace();
                new AlertDialog.Builder(InstallingFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).setTitle("Error Occured in Extract").mo26292l(th.getLocalizedMessage()).mo26266y("Ok . Continue", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.2.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        InstallingFragment.this.mo27002R2();
                        LocalBroadcastManager.m43863b(InstallingFragment.this.m4518x3()).m43861d(new Intent("reload"));
                    }
                }).m52864I();
            }
        };
        m7193t4.mo6065a(disposableObserver);
        this.f74697s4.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                disposableObserver.mo5225j();
                new AlertDialog.Builder(InstallingFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).setTitle("What do you want to do ?").mo26292l(InstallingFragment.this.f74699u4).mo26266y("Delete This File", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View$OnClickListenerC39223 view$OnClickListenerC39223 = View$OnClickListenerC39223.this;
                        compressHelper.m4906j(InstallingFragment.this.f74699u4);
                        Process.killProcess(Process.myPid());
                    }
                }).mo26284p("Just Close App", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Process.killProcess(Process.myPid());
                    }
                }).m52864I();
            }
        });
        this.f74698t4.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.InstallingFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InstallingFragment.this.mo27002R2();
            }
        });
        m4533A3();
        builder.setView(inflate);
        return builder.create();
    }

    /* renamed from: w3 */
    public byte[] m4519w3(ZipFile zipFile, String str, Bundle bundle) {
        String str2;
        FileHeader fileHeader;
        try {
            Iterator<FileHeader> it2 = zipFile.m3229y().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str2 = null;
                    fileHeader = null;
                    break;
                }
                fileHeader = it2.next();
                String m2971k = fileHeader.m2971k();
                if (m2971k.endsWith("/" + str)) {
                    str2 = StringUtils.splitByWholeSeparator(m2971k, "/")[0];
                    break;
                }
            }
            if (str2 == null) {
                Log.e("findFileInZip", "Can't find " + str);
                return null;
            }
            if (bundle != null) {
                bundle.putString("Folder", str2);
            }
            iMDLogger.m3290j("findFileInZip", "folder name is " + str2);
            ZipInputStream m3228z = zipFile.m3228z(fileHeader);
            byte[] byteArray = IOUtils.toByteArray(m3228z);
            m3228z.close();
            return byteArray;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            iMDLogger.m3294f("findFileInZip", e.getMessage() + " - " + e.toString());
            return null;
        }
    }

    /* renamed from: x3 */
    public Activity m4518x3() {
        Activity activity = this.f74695q4;
        return activity == null ? m44716w() : activity;
    }

    /* renamed from: y3 */
    public Bundle m4517y3(String str, String str2, int i, int i2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("labelText", str);
        bundle.putString("dbName", str2);
        bundle.putInt("current", i);
        bundle.putInt("total", i2);
        bundle.putString("error", str3);
        return bundle;
    }

    /* renamed from: z3 */
    public void m4516z3(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("visualdx.png");
        arrayList.add("uptodate.png");
        arrayList.add("irandarou.png");
        String string = bundle.getString("IconName");
        String str = bundle.getString("Path") + "/" + string;
        if (arrayList.contains(string)) {
            if (new File(str).exists()) {
                new CompressHelper(m44716w()).m4906j(str);
            }
            try {
                InputStream open = m44716w().getAssets().open(string);
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                IOUtils.copyLarge(open, fileOutputStream);
                open.close();
                fileOutputStream.close();
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("replaceIcons", "Error in replacing icons " + str + " : " + e.toString());
                e.printStackTrace();
            }
        }
    }
}
