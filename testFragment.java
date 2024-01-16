package net.imedicaldoctor.imd;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Fragments.Dictionary.CDicSearchActivity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.IOUtils;

/* loaded from: classes2.dex */
public class testFragment extends Fragment {

    /* renamed from: Q3 */
    public static Bundle f83469Q3;

    /* renamed from: F3 */
    public Button f83470F3;

    /* renamed from: G3 */
    public ProgressBar f83471G3;

    /* renamed from: H3 */
    public View f83472H3;

    /* renamed from: I3 */
    public TextView f83473I3;

    /* renamed from: J3 */
    public Timer f83474J3;

    /* renamed from: K3 */
    public long f83475K3;

    /* renamed from: L3 */
    public Bundle f83476L3;

    /* renamed from: M3 */
    public HttpURLConnection f83477M3;

    /* renamed from: N3 */
    public DisposableObserver<HttpURLConnection> f83478N3;

    /* renamed from: O3 */
    public ArrayList<Integer> f83479O3;

    /* renamed from: P3 */
    public Handler f83480P3 = new Handler() { // from class: net.imedicaldoctor.imd.testFragment.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            testFragment.this.m3276U2();
        }
    };

    /* renamed from: Q2 */
    private String m3281Q2(int i) {
        return m3278T2(i / 3600) + " : " + m3278T2((i % 3600) / 60) + " : " + m3278T2(i % 60);
    }

    /* renamed from: T2 */
    private String m3278T2(int i) {
        if (i == 0) {
            return "00";
        }
        if (i / 10 == 0) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    /* renamed from: N2 */
    public float m3284N2(int i, String str) {
        if (!f83469Q3.containsKey(str)) {
            f83469Q3.putIntegerArrayList(str, new ArrayList<>());
            return 0.0f;
        }
        ArrayList<Integer> integerArrayList = f83469Q3.getIntegerArrayList(str);
        if (integerArrayList.size() > 5) {
            integerArrayList.remove(0);
        }
        integerArrayList.add(Integer.valueOf(i));
        return m3283O2(integerArrayList) / 2.0f;
    }

    /* renamed from: O2 */
    public float m3283O2(ArrayList<Integer> arrayList) {
        Iterator<Integer> it2 = arrayList.iterator();
        long j = 0;
        while (it2.hasNext()) {
            j += it2.next().intValue();
        }
        return ((float) j) / arrayList.size();
    }

    /* renamed from: P2 */
    public byte[] m3282P2(String str, String str2) {
        try {
            ZipEntry nextEntry = new ZipInputStream(new BufferedInputStream(new FileInputStream(str))).getNextEntry();
            String str3 = "";
            if (nextEntry != null && nextEntry.isDirectory()) {
                str3 = nextEntry.getName();
            }
            iMDLogger.m3290j("findFileInZip", "folder name is " + str3);
            ZipFile zipFile = new ZipFile(str);
            InputStream inputStream = zipFile.getInputStream(new ZipEntry(str3 + str2));
            if (inputStream == null) {
                iMDLogger.m3294f("findFileInZip", "Nothing found");
                return null;
            }
            iMDLogger.m3294f("findFileInZip", "Yeeeeeessssss, Found it ");
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("findFileInZip", "Error in reading " + str2 + " insdie " + str);
            return null;
        }
    }

    /* renamed from: R2 */
    public String m3280R2(long j) {
        double d;
        if (j <= 0) {
            return "0";
        }
        int log10 = (int) (Math.log10(j) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(d / Math.pow(1024.0d, log10)) + StringUtils.SPACE + new String[]{"B", "KB", "MB", "GB", "TB"}[log10];
    }

    /* renamed from: S2 */
    public void m3279S2() {
        Timer timer = new Timer();
        this.f83474J3 = timer;
        timer.scheduleAtFixedRate(new TimerTask() { // from class: net.imedicaldoctor.imd.testFragment.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                testFragment.this.f83480P3.obtainMessage(1).sendToTarget();
            }
        }, SimpleExoPlayer.f32068s1, SimpleExoPlayer.f32068s1);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87190fragment_test, viewGroup, false);
        this.f83472H3 = inflate;
        final CompressHelper compressHelper = new CompressHelper(m44716w());
        ((Button) this.f83472H3.findViewById(C4804R.C4808id.f87044test_decompress)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.testFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle m4938a1 = new CompressHelper(testFragment.this.m44716w()).m4938a1("Dictionary");
                CDicSearchActivity.CDicSearchFragment cDicSearchFragment = new CDicSearchActivity.CDicSearchFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putBundle("DB", m4938a1);
                bundle2.putString("Dialog", "psoriasis");
                cDicSearchFragment.m44751k2(bundle2);
                cDicSearchFragment.m44870c3(true);
                cDicSearchFragment.m44844E2(testFragment.this, 0);
                cDicSearchFragment.mo29915h3(testFragment.this.m44820L(), "dictionaryDialog");
            }
        });
        this.f83470F3 = (Button) inflate.findViewById(C4804R.C4808id.f86824button);
        this.f83471G3 = (ProgressBar) inflate.findViewById(C4804R.C4808id.f86991progress);
        this.f83473I3 = (TextView) inflate.findViewById(C4804R.C4808id.f86945label);
        final String str = compressHelper.m4991J() + "/dbs.zi";
        this.f83471G3.setIndeterminate(false);
        f83469Q3 = new Bundle();
        final String str2 = compressHelper.m5004E1() + "/DBs.zi";
        this.f83470F3.setOnLongClickListener(new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.testFragment.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                String str3 = str2 + ".download";
                if (new File(str2).exists()) {
                    new File(str2).delete();
                }
                if (new File(str3).exists()) {
                    new File(str3).delete();
                    return true;
                }
                return true;
            }
        });
        this.f83470F3.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.testFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!testFragment.this.f83470F3.getText().equals("Download")) {
                    testFragment.this.f83470F3.setText("Download");
                    new AsyncTask() { // from class: net.imedicaldoctor.imd.testFragment.3.2
                        @Override // android.os.AsyncTask
                        protected Object doInBackground(Object[] objArr) {
                            long length = new File(compressHelper.m5004E1() + "/DBs.zi.download").length();
                            iMDLogger.m3294f("DownloadFile", "Stopping at " + length);
                            testFragment.this.f83476L3.putString("Close", "");
                            return null;
                        }
                    }.execute(new Object[0]);
                    return;
                }
                testFragment.this.f83470F3.setText("Stop");
                DisposableObserver<HttpURLConnection> disposableObserver = new DisposableObserver<HttpURLConnection>() { // from class: net.imedicaldoctor.imd.testFragment.3.1
                    @Override // io.reactivex.rxjava3.core.Observer
                    /* renamed from: b */
                    public void onNext(@NonNull HttpURLConnection httpURLConnection) {
                        testFragment.this.f83477M3 = httpURLConnection;
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onComplete() {
                        iMDLogger.m3294f("Completed", "Download Completed");
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onError(@NonNull Throwable th) {
                        th.printStackTrace();
                        iMDLogger.m3294f("onError", th.getLocalizedMessage());
                    }
                };
                testFragment.this.f83476L3 = new Bundle();
                Observable<HttpURLConnection> m4981M0 = compressHelper.m4981M0(str, str2, "2475815", "SQaNrBMGKrkr5+M0nJ2AxQ==", testFragment.this.f83476L3);
                String str3 = (compressHelper.m5004E1() + "/DBs.zi") + ".download";
                if (new File(str3).exists()) {
                    testFragment.this.f83475K3 = new File(str3).length();
                } else {
                    testFragment.this.f83475K3 = 0L;
                }
                m4981M0.mo6065a(disposableObserver);
                testFragment.this.f83478N3 = disposableObserver;
            }
        });
        m3279S2();
        return inflate;
    }

    /* renamed from: U2 */
    public void m3276U2() {
        if (this.f83470F3.getText().equals("Download")) {
            return;
        }
        String str = (new CompressHelper(m44716w()).m5004E1() + "/dbs.zi") + ".download";
        long length = new File(str).exists() ? new File(str).length() : 0L;
        double d = length + 0.0d;
        long j = length + 0;
        double doubleValue = Double.valueOf("2475815").doubleValue();
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        this.f83471G3.setProgress((int) (i > 0 ? (((float) d) / ((float) doubleValue)) * 100.0f : 0.0f));
        long j2 = j - this.f83475K3;
        if (j2 < 0) {
            j2 = 0;
        } else {
            this.f83475K3 = j;
        }
        float m3284N2 = m3284N2((int) j2, "dbs.zi");
        long j3 = m3284N2 > 0.0f ? ((long) (doubleValue - d)) / m3284N2 : 0L;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        String str2 = decimalFormat.format((d / 1024.0d) / 1024.0d) + " of " + decimalFormat.format((doubleValue / 1024.0d) / 1024.0d) + " MB(" + m3280R2(m3284N2) + "/s), " + m3281Q2((int) j3) + " remaining";
        if (i == 0) {
            str2 = "Preparing download";
        }
        this.f83473I3.setText(str2);
    }
}
