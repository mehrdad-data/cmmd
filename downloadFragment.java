package net.imedicaldoctor.imd.Fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.grabner.circleprogress.CircleProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.common.net.UrlEscapers;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.tool.xml.html.HTML;
import com.p009dd.CircularProgressButton;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.ViewHolders.StatusAdapter;
import net.imedicaldoctor.imd.iMD;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.FileUtils;

/* loaded from: classes2.dex */
public class downloadFragment extends Fragment {

    /* renamed from: A4 */
    private static String f76611A4 = null;

    /* renamed from: B4 */
    public static Bundle f76612B4 = null;

    /* renamed from: C4 */
    public static int f76613C4 = 0;

    /* renamed from: D4 */
    public static HashMap<String, DisposableObserver<HttpURLConnection>> f76614D4 = null;

    /* renamed from: E4 */
    public static final String f76615E4 = "bytesDownloaded";

    /* renamed from: F4 */
    public static final String f76616F4 = "bytesTotal";

    /* renamed from: G4 */
    public static final String f76617G4 = "avgSpeed";

    /* renamed from: H4 */
    public static final String f76618H4 = "remaining";

    /* renamed from: I4 */
    public static final String f76619I4 = "Progress";

    /* renamed from: J4 */
    public static final String f76620J4 = "Title";

    /* renamed from: K4 */
    public static final String f76621K4 = "URL";

    /* renamed from: L4 */
    public static final String f76622L4 = "FileName";

    /* renamed from: M4 */
    public static final String f76623M4 = "MD5";

    /* renamed from: N4 */
    public static final String f76624N4 = "PartFileSize";

    /* renamed from: O4 */
    public static final String f76625O4 = "price";

    /* renamed from: P4 */
    public static final String f76626P4 = "Buy";

    /* renamed from: Q4 */
    public static final String f76627Q4 = "downloader";

    /* renamed from: R4 */
    public static final String f76628R4 = "retry";

    /* renamed from: S4 */
    public static final String f76629S4 = "completed";

    /* renamed from: T4 */
    public static final String f76630T4 = "Installed";

    /* renamed from: U4 */
    public static final String f76631U4 = "error";

    /* renamed from: V4 */
    public static final String f76632V4 = "fileSize";

    /* renamed from: W4 */
    public static final String f76633W4 = "Icon";

    /* renamed from: X4 */
    public static final String f76634X4 = "name";

    /* renamed from: Y4 */
    public static final String f76635Y4 = "type";

    /* renamed from: Z4 */
    public static final String f76636Z4 = "version";

    /* renamed from: a5 */
    public static final String f76637a5 = "Delta";

    /* renamed from: b5 */
    public static final String f76638b5 = "Update";

    /* renamed from: c5 */
    public static final String f76639c5 = "Rebuilding";

    /* renamed from: d5 */
    public static final String f76640d5 = "Parts";

    /* renamed from: e5 */
    public static final String f76641e5 = "folderSizeKey";

    /* renamed from: f5 */
    public static final String f76642f5 = "videoIdKey";

    /* renamed from: g5 */
    public static final String f76643g5 = "savePathKey";

    /* renamed from: h5 */
    public static final String f76644h5 = "LatestKey";

    /* renamed from: i5 */
    public static final String f76645i5 = "HiddenKey";

    /* renamed from: j5 */
    public static final String f76646j5 = "SpeedReceived";

    /* renamed from: n4 */
    private static ArrayList<Bundle> f76647n4;

    /* renamed from: o4 */
    private static ArrayList<Bundle> f76648o4;

    /* renamed from: p4 */
    private static HashMap<String, CircleProgressView> f76649p4;

    /* renamed from: q4 */
    private static HashMap<String, Runnable> f76650q4;

    /* renamed from: r4 */
    private static HashMap<String, Runnable> f76651r4;

    /* renamed from: s4 */
    private static HashMap<String, Runnable> f76652s4;

    /* renamed from: t4 */
    private static ArrayList<Bundle> f76653t4;

    /* renamed from: u4 */
    public static HashMap<String, Bundle> f76654u4;

    /* renamed from: v4 */
    private static ArrayList<Bundle> f76655v4;

    /* renamed from: w4 */
    private static ArrayList<Bundle> f76656w4;

    /* renamed from: x4 */
    private static Bundle f76657x4;

    /* renamed from: y4 */
    private static String f76658y4;

    /* renamed from: z4 */
    private static String f76659z4;

    /* renamed from: F3 */
    private Bundle f76660F3;

    /* renamed from: G3 */
    private Bundle f76661G3;

    /* renamed from: H3 */
    private Observable<String> f76662H3;

    /* renamed from: I3 */
    private Bundle f76663I3;

    /* renamed from: J3 */
    private View f76664J3;

    /* renamed from: K3 */
    public RecyclerView f76665K3;

    /* renamed from: L3 */
    private MenuItem f76666L3;

    /* renamed from: M3 */
    private ProgressBar f76667M3;

    /* renamed from: N3 */
    public SearchView f76668N3;

    /* renamed from: O3 */
    public VBHelper f76669O3;

    /* renamed from: P3 */
    private Bundle f76670P3;

    /* renamed from: Q3 */
    public long f76671Q3;

    /* renamed from: R3 */
    public Bundle f76672R3;

    /* renamed from: S3 */
    public SegmentedGroup f76673S3;

    /* renamed from: T3 */
    public FrameLayout f76674T3;

    /* renamed from: U3 */
    public Button f76675U3;

    /* renamed from: V3 */
    private boolean f76676V3;

    /* renamed from: W3 */
    private Timer f76677W3;

    /* renamed from: X3 */
    private Timer f76678X3;

    /* renamed from: Y3 */
    private String f76679Y3;

    /* renamed from: Z3 */
    private String f76680Z3;

    /* renamed from: a4 */
    private String f76681a4;

    /* renamed from: b4 */
    private boolean f76682b4;

    /* renamed from: c4 */
    private boolean f76683c4;

    /* renamed from: d4 */
    private Activity f76684d4;

    /* renamed from: e4 */
    public Typeface f76685e4;

    /* renamed from: f4 */
    public BetterLinearLayoutManager f76686f4;

    /* renamed from: g4 */
    public String f76687g4;

    /* renamed from: h4 */
    public boolean f76688h4;

    /* renamed from: i4 */
    CompressHelper f76689i4;

    /* renamed from: j4 */
    public DownloadsAdapter f76690j4;

    /* renamed from: k4 */
    public Handler f76691k4 = new Handler(Looper.getMainLooper()) { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            downloadFragment.this.m3707e4();
        }
    };

    /* renamed from: l4 */
    public Handler f76692l4 = new Handler() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.7
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            downloadFragment.this.m3686z3();
        }
    };

    /* renamed from: m4 */
    public BroadcastReceiver f76693m4 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.25
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (downloadFragment.this.m3750G3()) {
                return;
            }
            SearchView searchView = downloadFragment.this.f76668N3;
            if (searchView != null) {
                searchView.clearFocus();
            }
            if (!downloadFragment.this.m3742N3()) {
                downloadFragment.this.m3737Q2();
            }
            downloadFragment.this.m3751F3();
        }
    };

    /* renamed from: net.imedicaldoctor.imd.Fragments.downloadFragment$17 */
    /* loaded from: classes2.dex */
    class DialogInterface$OnClickListenerC464917 implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC464917() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: net.imedicaldoctor.imd.Fragments.downloadFragment$18 */
    /* loaded from: classes2.dex */
    class DialogInterface$OnClickListenerC465018 implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC465018() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ActivityCompat.m48589C(downloadFragment.this.m44716w(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BetterLinearLayoutManager extends LinearLayoutManager {
        public BetterLinearLayoutManager(Context context) {
            super(context);
        }

        public BetterLinearLayoutManager(Context context, int i, boolean z) {
            super(context, i, z);
        }

        public BetterLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        /* renamed from: j2 */
        public boolean mo3670j2() {
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public class DownloadCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76752I;

        /* renamed from: J */
        private TextView f76753J;

        /* renamed from: K */
        private TextView f76754K;

        /* renamed from: L */
        private TextView f76755L;

        /* renamed from: M */
        private TextView f76756M;

        /* renamed from: N */
        private ImageView f76757N;

        /* renamed from: O */
        private Button f76758O;

        /* renamed from: P */
        private CircleProgressView f76759P;

        public DownloadCellViewHolder(View view) {
            super(view);
            this.f76752I = (TextView) view.findViewById(C4804R.C4808id.title);
            this.f76753J = (TextView) view.findViewById(C4804R.C4808id.f87035subtitle);
            this.f76754K = (TextView) view.findViewById(C4804R.C4808id.f86946latest);
            this.f76755L = (TextView) view.findViewById(C4804R.C4808id.f86862demo);
            this.f76757N = (ImageView) view.findViewById(C4804R.C4808id.image);
            this.f76758O = (Button) view.findViewById(C4804R.C4808id.f86877download_button);
            this.f76756M = (TextView) view.findViewById(C4804R.C4808id.f86864desc);
            this.f76758O.setTypeface(downloadFragment.this.f76685e4);
            this.f76759P = (CircleProgressView) view.findViewById(C4804R.C4808id.f86842circleView);
        }
    }

    /* loaded from: classes2.dex */
    public class DownloadsAdapter extends RecyclerView.Adapter {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: net.imedicaldoctor.imd.Fragments.downloadFragment$DownloadsAdapter$6 */
        /* loaded from: classes2.dex */
        public class View$OnClickListenerC46906 implements View.OnClickListener {

            /* renamed from: s */
            final /* synthetic */ Bundle f76773s;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: net.imedicaldoctor.imd.Fragments.downloadFragment$DownloadsAdapter$6$1 */
            /* loaded from: classes2.dex */
            public class C46911 extends DisposableObserver<String> {

                /* JADX INFO: Access modifiers changed from: package-private */
                /* renamed from: net.imedicaldoctor.imd.Fragments.downloadFragment$DownloadsAdapter$6$1$2 */
                /* loaded from: classes2.dex */
                public class DialogInterface$OnClickListenerC46932 implements DialogInterface.OnClickListener {
                    DialogInterface$OnClickListenerC46932() {
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.1.2.1
                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // io.reactivex.rxjava3.observers.DisposableObserver
                            /* renamed from: a */
                            public void mo3516a() {
                                super.mo3516a();
                                downloadFragment.this.f76666L3.setVisible(true);
                                downloadFragment.this.f76667M3.setIndeterminate(true);
                            }

                            @Override // io.reactivex.rxjava3.core.Observer
                            /* renamed from: b */
                            public void onNext(@NonNull String str) {
                                String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                                if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                                    if (splitByWholeSeparator.length > 1) {
                                        if (splitByWholeSeparator[1].contains("Not Enough Money")) {
                                            new AlertDialog.Builder(downloadFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("You don't have enough credit in your account. what do you want to do ?").mo26266y("Buy Database", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.1.2.1.2
                                                @Override // android.content.DialogInterface.OnClickListener
                                                public void onClick(DialogInterface dialogInterface2, int i2) {
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("http://imedicaldoctor.net/buydb.php?user=");
                                                    downloadFragment downloadfragment = downloadFragment.this;
                                                    sb.append(downloadfragment.f76669O3.m3420m(downloadfragment.f76689i4.m4889o1(), "127"));
                                                    sb.append("&db=");
                                                    View$OnClickListenerC46906 view$OnClickListenerC46906 = View$OnClickListenerC46906.this;
                                                    sb.append(downloadFragment.this.f76669O3.m3420m(view$OnClickListenerC46906.f76773s.getString("name"), "127"));
                                                    downloadFragment.this.f76689i4.m4973P(sb.toString());
                                                }
                                            }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.1.2.1.1
                                                @Override // android.content.DialogInterface.OnClickListener
                                                public void onClick(DialogInterface dialogInterface2, int i2) {
                                                }
                                            }).m52864I();
                                            return;
                                        } else {
                                            CompressHelper.m4921e2(downloadFragment.this.m3753D3(), splitByWholeSeparator[1], 1);
                                            return;
                                        }
                                    }
                                    return;
                                }
                                downloadFragment.this.f76689i4.m4933b2(splitByWholeSeparator[1]);
                                View$OnClickListenerC46906 view$OnClickListenerC46906 = View$OnClickListenerC46906.this;
                                downloadFragment.this.m3723X2(view$OnClickListenerC46906.f76773s, "Buy");
                                downloadFragment.this.m3729U2();
                                downloadFragment.this.m3730T3();
                                View$OnClickListenerC46906 view$OnClickListenerC469062 = View$OnClickListenerC46906.this;
                                downloadFragment.this.m3719Z2(view$OnClickListenerC469062.f76773s);
                            }

                            @Override // io.reactivex.rxjava3.core.Observer
                            public void onComplete() {
                                downloadFragment.this.f76666L3.setVisible(false);
                            }

                            @Override // io.reactivex.rxjava3.core.Observer
                            public void onError(@NonNull Throwable th) {
                                downloadFragment.this.f76666L3.setVisible(false);
                                CompressHelper.m4921e2(downloadFragment.this.m3753D3(), "Error in contacting server, try again later", 1);
                            }
                        };
                        downloadFragment downloadfragment = downloadFragment.this;
                        CompressHelper compressHelper = downloadfragment.f76689i4;
                        compressHelper.m5014B0(downloadfragment, compressHelper.m4890o0("BuyItem|||||" + downloadFragment.this.f76669O3.m3421l() + "|||||" + View$OnClickListenerC46906.this.f76773s.getString("name"))).mo6065a(disposableObserver);
                    }
                }

                C46911() {
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.reactivex.rxjava3.observers.DisposableObserver
                /* renamed from: a */
                public void mo3516a() {
                    super.mo3516a();
                    downloadFragment.this.f76666L3.setVisible(true);
                    downloadFragment.this.f76667M3.setIndeterminate(true);
                }

                @Override // io.reactivex.rxjava3.core.Observer
                /* renamed from: b */
                public void onNext(@NonNull String str) {
                    StringBuilder sb;
                    String str2;
                    String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                    if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                        CompressHelper.m4921e2(downloadFragment.this.m3753D3(), splitByWholeSeparator[1], 1);
                        return;
                    }
                    String str3 = splitByWholeSeparator[1];
                    Log.e("Price", "Response : " + str);
                    Log.e("Price", str3);
                    Log.e("Price 2", View$OnClickListenerC46906.this.f76773s.getString("price"));
                    if (!str3.equals(View$OnClickListenerC46906.this.f76773s.getString("price").replace(".0", ""))) {
                        Log.e("iMD", "Wrong price");
                        downloadFragment.this.m3736Q3();
                        return;
                    }
                    if (str3.equals("0")) {
                        sb = new StringBuilder();
                        sb.append("Are You Sure You Want To Buy ");
                        sb.append(View$OnClickListenerC46906.this.f76773s.getString("Title"));
                        str2 = " For Free ?";
                    } else {
                        sb = new StringBuilder();
                        sb.append("Are You Sure You Want To Buy ");
                        sb.append(View$OnClickListenerC46906.this.f76773s.getString("Title"));
                        sb.append(" For ");
                        sb.append(str3);
                        str2 = " Toman ?";
                    }
                    sb.append(str2);
                    new AlertDialog.Builder(downloadFragment.this.m3753D3(), C4804R.style.f88094alertDialogTheme).mo26292l(sb.toString()).mo26266y("Yes", new DialogInterface$OnClickListenerC46932()).mo26284p("No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).m52864I();
                }

                @Override // io.reactivex.rxjava3.core.Observer
                public void onComplete() {
                    downloadFragment.this.f76666L3.setVisible(false);
                }

                @Override // io.reactivex.rxjava3.core.Observer
                public void onError(@NonNull Throwable th) {
                    downloadFragment.this.f76666L3.setVisible(false);
                    CompressHelper.m4921e2(downloadFragment.this.m3753D3(), "Error in contacting server, try again later", 1);
                }
            }

            View$OnClickListenerC46906(Bundle bundle) {
                this.f76773s = bundle;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.e("imd", "here");
                if (!this.f76773s.containsKey("Buy")) {
                    if (PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).contains("DownloadServer")) {
                        downloadFragment.this.m3719Z2(this.f76773s);
                        return;
                    } else {
                        new AlertDialog.Builder(downloadFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Choose which server to download from. if you are connecting from Iran and choose Iran Server, traffic will be half-priced.").mo26266y("Iran Server", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.3
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).contains("DownloadServer")) {
                                    PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).edit().remove("DownloadServer").commit();
                                }
                                PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).edit().putString("DownloadServer", "idl").commit();
                                SearchView searchView = downloadFragment.this.f76668N3;
                                if (searchView != null) {
                                    searchView.clearFocus();
                                }
                                downloadFragment.this.m3737Q2();
                                Toast.makeText(downloadFragment.this.m44716w(), "You can change it later in the account tab", 1).show();
                            }
                        }).mo26284p("Germany Server", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.6.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).contains("DownloadServer")) {
                                    PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).edit().remove("DownloadServer").commit();
                                }
                                PreferenceManager.getDefaultSharedPreferences(downloadFragment.this.m44716w()).edit().putString("DownloadServer", HTML.Tag.f65923m0).commit();
                                Toast.makeText(downloadFragment.this.m44716w(), "You can change it later in the account tab", 1).show();
                                View$OnClickListenerC46906 view$OnClickListenerC46906 = View$OnClickListenerC46906.this;
                                downloadFragment.this.m3719Z2(view$OnClickListenerC46906.f76773s);
                            }
                        }).m52864I();
                        return;
                    }
                }
                C46911 c46911 = new C46911();
                downloadFragment downloadfragment = downloadFragment.this;
                CompressHelper compressHelper = downloadfragment.f76689i4;
                compressHelper.m5014B0(downloadfragment, compressHelper.m4890o0("QueryPrice|||||" + downloadFragment.this.f76669O3.m3421l() + "|||||" + this.f76773s.getString("name"))).mo6065a(c46911);
            }
        }

        public DownloadsAdapter() {
        }

        /* renamed from: d0 */
        private void m3661d0(final CircularProgressButton circularProgressButton) {
            circularProgressButton.setProgress(1);
            circularProgressButton.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.9
                @Override // java.lang.Runnable
                public void run() {
                    circularProgressButton.setProgress(0);
                    circularProgressButton.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            circularProgressButton.setProgress(1);
                        }
                    }, 100L);
                }
            }, 100L);
        }

        /* renamed from: e0 */
        private double m3660e0(Bundle bundle, String str) {
            try {
                if (bundle.containsKey(str)) {
                    return bundle.getDouble(str);
                }
                return 0.0d;
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                return 0.0d;
            }
        }

        /* renamed from: g0 */
        private long m3658g0(Bundle bundle, String str) {
            try {
                if (bundle.containsKey(str)) {
                    return bundle.getLong(str);
                }
                return 0L;
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                return 0L;
            }
        }

        /* renamed from: h0 */
        private String m3657h0(int i) {
            return m3655j0(i / 3600) + " : " + m3655j0((i % 3600) / 60) + " : " + m3655j0(i % 60);
        }

        /* renamed from: j0 */
        private String m3655j0(int i) {
            if (i == 0) {
                return "00";
            }
            if (i / 10 == 0) {
                return "0" + i;
            }
            return String.valueOf(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            TextView textView;
            View.OnClickListener onClickListener;
            TextView textView2;
            String m3738P3;
            TextView textView3;
            int color;
            TextView textView4;
            String str;
            DownloadCellViewHolder downloadCellViewHolder = (DownloadCellViewHolder) viewHolder;
            if (downloadFragment.f76647n4 == null || downloadFragment.f76647n4.size() - 1 < i) {
                return;
            }
            final Bundle bundle = (Bundle) downloadFragment.f76647n4.get(i);
            downloadCellViewHolder.f76752I.setText(bundle.getString("Title"));
            m3656i0(downloadCellViewHolder.f76757N, bundle);
            if (bundle.containsKey("LatestKey")) {
                downloadCellViewHolder.f76754K.setVisibility(0);
                if (bundle.getString("LatestKey").equals("0")) {
                    textView4 = downloadCellViewHolder.f76754K;
                    str = "Show Latest Version";
                } else {
                    textView4 = downloadCellViewHolder.f76754K;
                    str = "Show Free Version";
                }
                textView4.setText(str);
                downloadCellViewHolder.f76754K.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Bundle bundle2;
                        Bundle m3408y = downloadFragment.this.f76669O3.m3408y();
                        String str2 = downloadFragment.this.f76689i4.m5004E1() + "/DBs.db";
                        if (bundle.getString("LatestKey").equals("0")) {
                            Bundle bundle3 = downloadFragment.this.f76689i4.m4946Y(str2, "select id,Title,name,Version, IconName,folderSize, url, fileSize, md5,price, partfilesize,type from Dbs where name = '" + bundle.getString("name") + "' order by Version desc").get(0);
                            downloadFragment.this.m3723X2(bundle, "version");
                            bundle.putString("version", bundle3.getString("Version"));
                            downloadFragment.this.m3723X2(bundle, "URL");
                            bundle.putString("URL", downloadFragment.this.f76689i4.m5010C1("http://" + downloadFragment.this.f76680Z3 + ".imedicaldoctor.net" + bundle3.getString("url")));
                            downloadFragment.this.m3723X2(bundle, "fileSize");
                            bundle.putString("fileSize", bundle3.getString("fileSize"));
                            downloadFragment.this.m3723X2(bundle, "folderSizeKey");
                            bundle.putString("folderSizeKey", bundle3.getString("folderSize"));
                            downloadFragment.this.m3723X2(bundle, "MD5");
                            bundle.putString("MD5", bundle3.getString("md5"));
                            downloadFragment.this.m3723X2(bundle, "PartFileSize");
                            bundle.putString("PartFileSize", bundle3.getString("partfilesize"));
                            downloadFragment.this.m3723X2(bundle, "Parts");
                            String string = m3408y.getString(bundle.getString("name"));
                            String string2 = bundle3.getString("Version");
                            if (string2.length() == 6) {
                                string = string.substring(0, 6);
                            }
                            if (string.compareTo(string2) >= 0) {
                                downloadFragment.this.m3723X2(bundle, "Buy");
                            } else {
                                bundle.putString("Buy", "");
                            }
                            downloadFragment.this.m3723X2(bundle, "LatestKey");
                            bundle.putString("LatestKey", IcyHeaders.f35463C2);
                        } else if (bundle.getString("LatestKey").equals(IcyHeaders.f35463C2)) {
                            Iterator<Bundle> it2 = downloadFragment.this.f76689i4.m4946Y(str2, "select id,Title,name,Version, IconName,folderSize, url, fileSize, md5,price, partfilesize,type from Dbs where name = '" + bundle.getString("name") + "' order by Version desc").iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    bundle2 = null;
                                    break;
                                }
                                bundle2 = it2.next();
                                String string3 = m3408y.getString(bundle.getString("name"));
                                String string4 = bundle2.getString("Version");
                                Iterator<Bundle> it3 = it2;
                                if (string4.length() == 6) {
                                    string3 = string3.substring(0, 6);
                                }
                                if (string3.compareTo(string4) >= 0) {
                                    break;
                                }
                                it2 = it3;
                            }
                            if (bundle2 == null) {
                                Toast.makeText(downloadFragment.this.m44716w(), "There is no previous versions available (more than 6 month ago)", 1).show();
                                return;
                            }
                            downloadFragment.this.m3723X2(bundle, "version");
                            bundle.putString("version", bundle2.getString("Version"));
                            downloadFragment.this.m3723X2(bundle, "URL");
                            bundle.putString("URL", downloadFragment.this.f76689i4.m5010C1("http://" + downloadFragment.this.f76680Z3 + ".imedicaldoctor.net" + bundle2.getString("url")));
                            downloadFragment.this.m3723X2(bundle, "fileSize");
                            bundle.putString("fileSize", bundle2.getString("fileSize"));
                            downloadFragment.this.m3723X2(bundle, "folderSizeKey");
                            bundle.putString("folderSizeKey", bundle2.getString("folderSize"));
                            downloadFragment.this.m3723X2(bundle, "MD5");
                            bundle.putString("MD5", bundle2.getString("md5"));
                            downloadFragment.this.m3723X2(bundle, "PartFileSize");
                            bundle.putString("PartFileSize", bundle2.getString("partfilesize"));
                            downloadFragment.this.m3723X2(bundle, "Buy");
                            downloadFragment.this.m3723X2(bundle, "Parts");
                            downloadFragment.this.m3723X2(bundle, "LatestKey");
                            bundle.putString("LatestKey", "0");
                        }
                        downloadFragment.this.m3733S2();
                    }
                });
            } else {
                downloadCellViewHolder.f76754K.setVisibility(8);
            }
            if (bundle.containsKey("completed")) {
                if (bundle.containsKey(downloadFragment.f76630T4)) {
                    downloadCellViewHolder.f76753J.setText("ّInstalled");
                    downloadCellViewHolder.f76753J.setTextColor(downloadFragment.this.m44782a0().getColor(C4804R.C4806color.blue_dark));
                    downloadCellViewHolder.f76758O.setVisibility(0);
                    downloadCellViewHolder.f76758O.setText("Open");
                    downloadCellViewHolder.f76758O.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            downloadFragment.this.m3751F3();
                            ((mainActivity) downloadFragment.this.m44716w()).f76898Q2.setCurrentItem(1);
                            downloadFragment.this.f76664J3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    View$OnClickListenerC46863 view$OnClickListenerC46863 = View$OnClickListenerC46863.this;
                                    downloadFragment.this.f76689i4.m4886p1(bundle.getBundle("DB"));
                                }
                            }, 1000L);
                        }
                    });
                } else {
                    downloadCellViewHolder.f76753J.setText("Download Completed");
                    downloadCellViewHolder.f76753J.setTextColor(downloadFragment.this.m44782a0().getColor(C4804R.C4806color.f86117green_real));
                    downloadCellViewHolder.f76758O.setVisibility(8);
                }
                if (bundle.containsKey("Rebuilding")) {
                    downloadCellViewHolder.f76753J.setText("Rebuilding ...");
                }
                downloadCellViewHolder.f76755L.setVisibility(8);
                downloadCellViewHolder.f76759P.setVisibility(8);
            } else if (bundle.containsKey("downloader")) {
                downloadCellViewHolder.f76754K.setVisibility(8);
                downloadCellViewHolder.f76755L.setVisibility(8);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
                downloadCellViewHolder.f76758O.setVisibility(8);
                downloadCellViewHolder.f76759P.setVisibility(0);
                double m3660e0 = m3660e0(bundle, "bytesDownloaded");
                double m3660e02 = m3660e0(bundle, "bytesTotal");
                long m3658g0 = m3658g0(bundle, "avgSpeed");
                long m3658g02 = m3658g0(bundle, "remaining");
                int m3659f0 = m3659f0(bundle, "Progress");
                String str2 = decimalFormat.format((m3660e0 / 1024.0d) / 1024.0d) + " of " + decimalFormat.format((m3660e02 / 1024.0d) / 1024.0d) + " MB(" + downloadFragment.this.m3738P3(m3658g0) + "/s), " + m3657h0((int) m3658g02) + " remaining";
                downloadCellViewHolder.f76753J.setTextColor(downloadFragment.this.m44782a0().getColor(C4804R.C4806color.f86109darkGrey));
                if (m3660e0 == 0.0d) {
                    downloadCellViewHolder.f76759P.m41214u();
                    str2 = "Preparing Download";
                } else {
                    downloadCellViewHolder.f76759P.m41213v();
                }
                downloadCellViewHolder.f76753J.setText(str2);
                iMDLogger.m3296d("ONBindViewHolder", "Progress : " + m3659f0);
                downloadCellViewHolder.f76759P.setValue(m3659f0 == 0 ? 1.0f : m3659f0);
                downloadCellViewHolder.f76759P.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        downloadFragment.this.m3717a3(bundle);
                    }
                });
            } else {
                downloadCellViewHolder.f76758O.setVisibility(0);
                downloadCellViewHolder.f76759P.setVisibility(8);
                downloadCellViewHolder.f76755L.setVisibility(8);
                try {
                    if (bundle.containsKey("Buy") && downloadFragment.this.m3749H3(bundle.getString("name"))) {
                        downloadCellViewHolder.f76756M.setText(downloadFragment.this.m3735R2(bundle.getString("name")).equals("1year") ? "Free monthly updates for one year" : "Free monthly updates for two years");
                        downloadCellViewHolder.f76756M.setVisibility(0);
                    } else {
                        downloadCellViewHolder.f76756M.setVisibility(8);
                    }
                    if (bundle.containsKey("error")) {
                        downloadCellViewHolder.f76753J.setText(bundle.getString("error"));
                        textView3 = downloadCellViewHolder.f76753J;
                        color = downloadFragment.this.m44782a0().getColor(C4804R.C4806color.red);
                    } else {
                        if (downloadFragment.this.m3749H3(bundle.getString("name"))) {
                            textView2 = downloadCellViewHolder.f76753J;
                            m3738P3 = downloadFragment.this.m3738P3(Long.valueOf(bundle.getString("fileSize")).longValue()) + " - " + CompressHelper.m4957U0(bundle.getString("version"));
                        } else {
                            textView2 = downloadCellViewHolder.f76753J;
                            m3738P3 = downloadFragment.this.m3738P3(Long.valueOf(bundle.getString("fileSize")).longValue());
                        }
                        textView2.setText(m3738P3);
                        textView3 = downloadCellViewHolder.f76753J;
                        color = downloadFragment.this.m44782a0().getColor(C4804R.C4806color.f86109darkGrey);
                    }
                    textView3.setTextColor(color);
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    downloadCellViewHolder.f76753J.setText("Error occured, try again");
                    e.printStackTrace();
                }
                if (!bundle.containsKey("Update")) {
                    if (bundle.containsKey("Buy")) {
                        if (!bundle.getString("price").equals("0")) {
                            downloadCellViewHolder.f76758O.setText(bundle.getString("price") + " T");
                            if (downloadFragment.this.f76669O3.m3412u(bundle.getString("type"))) {
                                downloadCellViewHolder.f76755L.setVisibility(0);
                                textView = downloadCellViewHolder.f76755L;
                                onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.5
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        downloadFragment.this.m3723X2(bundle, "Buy");
                                        downloadFragment.this.m3733S2();
                                    }
                                };
                                textView.setOnClickListener(onClickListener);
                            }
                        }
                        downloadCellViewHolder.f76758O.setText("Free");
                    } else {
                        downloadCellViewHolder.f76758O.setText("Download");
                    }
                    downloadCellViewHolder.f76755L.setVisibility(8);
                } else if (bundle.containsKey("Buy")) {
                    if (!bundle.getString("price").equals("0")) {
                        downloadCellViewHolder.f76758O.setText(bundle.getString("price") + " T");
                        if (downloadFragment.this.f76669O3.m3412u(bundle.getString("type"))) {
                            downloadCellViewHolder.f76755L.setVisibility(0);
                            textView = downloadCellViewHolder.f76755L;
                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.4
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    downloadFragment.this.m3723X2(bundle, "Buy");
                                    downloadFragment.this.m3733S2();
                                    view.setVisibility(8);
                                }
                            };
                            textView.setOnClickListener(onClickListener);
                        }
                        downloadCellViewHolder.f76755L.setVisibility(8);
                    }
                    downloadCellViewHolder.f76758O.setText("Free");
                } else {
                    downloadCellViewHolder.f76758O.setText("Update");
                }
                downloadCellViewHolder.f76758O.setOnClickListener(new View$OnClickListenerC46906(bundle));
                downloadCellViewHolder.f76758O.setOnLongClickListener(new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.7
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(downloadFragment.this.m3753D3()).inflate(C4804R.C4810layout.f87235list_view_item_download, viewGroup, false);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.DownloadsAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    downloadFragment.this.m3751F3();
                }
            });
            return new DownloadCellViewHolder(inflate);
        }

        /* renamed from: f0 */
        public int m3659f0(Bundle bundle, String str) {
            if (bundle.containsKey(str)) {
                return bundle.getInt(str);
            }
            return 0;
        }

        /* renamed from: i0 */
        public void m3656i0(ImageView imageView, Bundle bundle) {
            RequestBuilder<Drawable> mo40145t;
            ArrayList arrayList = new ArrayList();
            arrayList.add("visualdx.png");
            arrayList.add("uptodate.png");
            arrayList.add("irandarou.png");
            if (arrayList.contains(bundle.getString("Icon"))) {
                mo40145t = Glide.m40316F(downloadFragment.this).mo40152g(Uri.parse("file:///android_asset/" + bundle.getString("Icon")));
            } else {
                mo40145t = Glide.m40316F(downloadFragment.this).mo40145t(downloadFragment.this.f76689i4.m5010C1("http://" + downloadFragment.this.f76680Z3 + ".imedicaldoctor.net/Icons/" + bundle.getString("Icon")));
            }
            mo40145t.m40191t2(imageView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            if (downloadFragment.f76647n4 == null) {
                return 0;
            }
            return downloadFragment.f76647n4.size();
        }
    }

    /* loaded from: classes2.dex */
    public class PackageCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76789I;

        /* renamed from: J */
        private Button f76790J;

        public PackageCellViewHolder(View view) {
            super(view);
            this.f76789I = (TextView) view.findViewById(C4804R.C4808id.title);
            this.f76790J = (Button) view.findViewById(C4804R.C4808id.f86877download_button);
        }
    }

    /* renamed from: I3 */
    private Observable<String> m3748I3(final Bundle bundle) {
        return Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.39
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                FileOutputStream fileOutputStream;
                boolean z;
                String str;
                String str2;
                String str3;
                C467439 c467439;
                ObservableEmitter<String> observableEmitter2;
                C467439 c4674392 = this;
                ObservableEmitter<String> observableEmitter3 = observableEmitter;
                String m4861y = downloadFragment.this.f76689i4.m4861y();
                StringBuilder sb = new StringBuilder();
                sb.append(m4861y);
                String str4 = "/";
                sb.append("/");
                sb.append(bundle.getString("FileName"));
                sb.append(".tmp");
                String sb2 = sb.toString();
                File file = new File(sb2);
                if (file.exists()) {
                    iMDLogger.m3290j("joinFiles", sb2 + " Already exists : " + downloadFragment.this.m3738P3(file.length()));
                    file.length();
                    downloadFragment.this.f76689i4.m4906j(file.getAbsolutePath());
                } else {
                    try {
                        if (!Boolean.valueOf(file.createNewFile()).booleanValue()) {
                            iMDLogger.m3294f("JoinFiles", "Error in creating " + sb2 + " Without error");
                        }
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        iMDLogger.m3294f("JoinFiles", "Error in creating " + sb2 + " With Error : " + e.toString());
                        observableEmitter3.onError(e);
                    }
                }
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e2) {
                    FirebaseCrashlytics.m18030d().m18027g(e2);
                    iMDLogger.m3294f("JoiningFiles", "Error in opening file output stream for " + file.getAbsolutePath());
                    fileOutputStream = null;
                }
                FileOutputStream fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 == null) {
                    observableEmitter3.onError(new Throwable("No Opening Stream"));
                }
                int i = 1;
                long j = 0;
                while (i < 11) {
                    String str5 = m4861y + str4 + bundle.getString("FileName") + "." + i;
                    File file2 = new File(str5);
                    if (file2.exists()) {
                        str = sb2;
                    } else {
                        iMDLogger.m3294f("JoinFiles", str5 + " don't exist");
                        str = sb2;
                        downloadFragment.this.f76689i4.m4906j(file.getAbsolutePath());
                        observableEmitter3.onError(new Throwable(str5 + " don't exist"));
                    }
                    try {
                        long length = file2.length();
                        FileInputStream fileInputStream = new FileInputStream(file2);
                        str2 = m4861y;
                        str3 = str4;
                        int i2 = 0;
                        while (i2 < length) {
                            int i3 = i2 + PlaybackException.f31866f3;
                            if (i3 > length) {
                                int i4 = ((int) length) - i2;
                                try {
                                    byte[] bArr = new byte[i4];
                                    fileInputStream.read(bArr, 0, i4);
                                    fileOutputStream2.write(bArr);
                                } catch (Exception e3) {
                                    e = e3;
                                    FirebaseCrashlytics.m18030d().m18027g(e);
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("Error combining parts of ");
                                    c467439 = this;
                                    sb3.append(bundle.getString("PartFileSize"));
                                    sb3.append(" with error : ");
                                    sb3.append(e);
                                    iMDLogger.m3294f("Join Files", sb3.toString());
                                    observableEmitter2 = observableEmitter;
                                    observableEmitter2.onError(e);
                                    i++;
                                    sb2 = str;
                                    m4861y = str2;
                                    str4 = str3;
                                    C467439 c4674393 = c467439;
                                    observableEmitter3 = observableEmitter2;
                                    c4674392 = c4674393;
                                }
                            } else {
                                byte[] bArr2 = new byte[PlaybackException.f31866f3];
                                fileInputStream.read(bArr2, 0, PlaybackException.f31866f3);
                                fileOutputStream2.write(bArr2);
                            }
                            i2 = i3;
                        }
                        fileInputStream.close();
                        j += length;
                        iMDLogger.m3290j("JoinFiles", "IN " + i + " :" + j + " , " + file.length());
                        c467439 = this;
                        observableEmitter2 = observableEmitter;
                    } catch (Exception e4) {
                        e = e4;
                        str2 = m4861y;
                        str3 = str4;
                    }
                    i++;
                    sb2 = str;
                    m4861y = str2;
                    str4 = str3;
                    C467439 c46743932 = c467439;
                    observableEmitter3 = observableEmitter2;
                    c4674392 = c46743932;
                }
                String str6 = m4861y;
                String str7 = str4;
                String str8 = sb2;
                ObservableEmitter<String> observableEmitter4 = observableEmitter3;
                C467439 c4674394 = c4674392;
                fileOutputStream2.close();
                iMDLogger.m3290j("Joining Files", "Compare " + file.length() + " With " + bundle.getString("fileSize"));
                long abs = Math.abs(file.length() - Long.valueOf(bundle.getString("fileSize")).longValue());
                if (abs != 0 && abs != 16) {
                    Log.d("Joining Files", "Comparing failed. deleteing all parts");
                    for (int i5 = 1; i5 < 11; i5++) {
                        String str9 = str6 + str7 + bundle.getString("FileName") + "." + i5;
                        new File(str9);
                        downloadFragment.this.f76689i4.m4906j(str9);
                    }
                    observableEmitter4.onError(new Throwable("Rebuild unsuccesfull"));
                    return;
                }
                file.setReadable(true, false);
                int i6 = 1;
                while (i6 < 11) {
                    StringBuilder sb4 = new StringBuilder();
                    String str10 = str8;
                    sb4.append(str10);
                    sb4.append(".");
                    sb4.append(i6);
                    String replace = sb4.toString().replace("tmp.", "");
                    File file3 = new File(replace);
                    try {
                        downloadFragment.this.f76689i4.m4906j(replace);
                    } catch (Exception e5) {
                        FirebaseCrashlytics.m18030d().m18027g(e5);
                        file3.deleteOnExit();
                    }
                    i6++;
                    str8 = str10;
                }
                File file4 = new File(str6 + str7 + bundle.getString("FileName"));
                if (bundle.containsKey("savePathKey")) {
                    file4 = new File(bundle.getString("savePathKey"));
                    if (!file4.getParentFile().exists()) {
                        file4.getParentFile().mkdirs();
                    }
                }
                if (file4.exists()) {
                    downloadFragment.this.f76689i4.m4906j(file4.getAbsolutePath());
                }
                try {
                    downloadFragment.this.m3740O3(file, file4);
                    z = true;
                } catch (Exception unused) {
                    z = false;
                }
                if (!z) {
                    observableEmitter4.onError(new Throwable("Renamed failed to " + file4.getAbsolutePath()));
                }
                observableEmitter.onComplete();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O3 */
    public void m3740O3(File file, File file2) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2 = null;
        try {
            fileChannel = new FileOutputStream(file2).getChannel();
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            fileChannel2 = new FileInputStream(file).getChannel();
            fileChannel2.transferTo(0L, fileChannel2.size(), fileChannel);
            fileChannel2.close();
            this.f76689i4.m4906j(file.getAbsolutePath());
            fileChannel2.close();
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            throw th;
        }
    }

    /* renamed from: S3 */
    private String m3732S3(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U2 */
    public void m3729U2() {
        VBHelper vBHelper = this.f76669O3;
        String m3410w = vBHelper.m3410w(vBHelper.m3421l());
        if (m3410w == null) {
            return;
        }
        String[] split = TextUtils.split(m3410w.replace("||", "::"), "::");
        String str = split[1];
        String[] split2 = TextUtils.split(split[3], ",");
        f76658y4 = split[5];
        new ArrayList(Arrays.asList(split2));
        if (f76657x4 == null) {
            f76657x4 = this.f76669O3.m3408y();
        }
        f76659z4 = split[4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y2 */
    public void m3721Y2() {
        String str;
        if (this.f76687g4 != null) {
            ((TextView) this.f76664J3.findViewById(C4804R.C4808id.f87029status_label)).setText(this.f76687g4);
            this.f76687g4 = null;
            return;
        }
        ArrayList<Bundle> arrayList = f76647n4;
        if (arrayList != null && arrayList.size() != 0) {
            m3718Z3();
            m3733S2();
            this.f76665K3.m42990O1(0);
            return;
        }
        if (!CompressHelper.f73782w) {
            String str2 = f76611A4;
            if (str2 == null || str2.length() <= 0) {
                if (this.f76673S3.getCheckedRadioButtonId() == C4804R.C4808id.f87075updates) {
                    str = "All your databases are up to date.";
                } else if (this.f76673S3.getCheckedRadioButtonId() == C4804R.C4808id.f86984paid) {
                    str = "You haven't purchased any databases yet";
                }
            }
            m3716a4("No Databases Found");
            return;
        }
        str = "Loading Databases";
        m3716a4(str);
    }

    /* renamed from: A3 */
    public void m3756A3(final Bundle bundle) {
        ArrayList parcelableArrayList;
        if (bundle.containsKey("completed") || (parcelableArrayList = bundle.getParcelableArrayList("Parts")) == null) {
            return;
        }
        boolean z = true;
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            if (!((Bundle) parcelableArrayList.get(i)).containsKey("completed")) {
                z = false;
            }
        }
        if (parcelableArrayList.size() >= 10 ? z : false) {
            iMDLogger.m3294f("Completed", bundle.getString("URL") + " Download completed ");
            bundle.putString("Rebuilding", "");
            bundle.putString("completed", "");
            m3723X2(bundle, "downloader");
            m3715b3(bundle);
            if (!bundle.getString("FileName").contains(".zip") || Long.valueOf(bundle.getString("fileSize")).longValue() <= 524288000) {
                this.f76689i4.m5017A0(m3753D3(), m3748I3(bundle)).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.40
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    /* renamed from: a */
                    public void accept(String str) throws Throwable {
                    }
                }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.41
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    /* renamed from: a */
                    public void accept(Throwable th) throws Throwable {
                        try {
                            String m4861y = downloadFragment.this.f76689i4.m4861y();
                            File file = new File(m4861y + "/" + bundle.getString("FileName"));
                            if (file.exists()) {
                                downloadFragment.this.f76689i4.m4906j(file.getAbsolutePath());
                            }
                            downloadFragment.this.m3723X2(bundle, "Rebuilding");
                            downloadFragment.this.m3723X2(bundle, "completed");
                            downloadFragment.this.m3723X2(bundle, "downloader");
                            downloadFragment.this.m3723X2(bundle, "error");
                            Bundle bundle2 = bundle;
                            bundle2.putString("error", "Rebuild failed : " + th.getMessage());
                            downloadFragment.this.m3715b3(bundle);
                        } catch (Exception unused) {
                        }
                    }
                }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.42
                    @Override // io.reactivex.rxjava3.functions.Action
                    public void run() throws Throwable {
                        downloadFragment.this.m3723X2(bundle, "Rebuilding");
                        downloadFragment.this.m3723X2(bundle, "completed");
                        downloadFragment.this.m3715b3(bundle);
                        if (downloadFragment.this.f76661G3.containsKey(bundle.getString("id"))) {
                            downloadFragment.this.f76661G3.remove(bundle.getString("id"));
                        }
                        LocalBroadcastManager.m43863b(downloadFragment.this.m3753D3()).m43861d(new Intent("checkzip"));
                        if (bundle.containsKey("videoIdKey")) {
                            String string = bundle.getString("videoIdKey");
                            if (downloadFragment.f76650q4.containsKey(string)) {
                                try {
                                    ((Runnable) downloadFragment.f76650q4.get(string)).run();
                                } catch (Exception e) {
                                    FirebaseCrashlytics.m18030d().m18027g(e);
                                }
                            }
                        }
                    }
                });
                return;
            }
            m3723X2(bundle, "Rebuilding");
            if (this.f76661G3.containsKey(bundle.getString("id"))) {
                this.f76661G3.remove(bundle.getString("id"));
            }
            m3715b3(bundle);
            LocalBroadcastManager.m43863b(m3753D3()).m43861d(new Intent("checkzip"));
        }
    }

    /* renamed from: B3 */
    public Bundle m3755B3(String str) {
        HashMap<String, Bundle> hashMap = f76654u4;
        if (hashMap != null && hashMap.containsKey(str)) {
            return f76654u4.get(str);
        }
        return null;
    }

    /* renamed from: C3 */
    public String m3754C3(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(StringUtils.splitByWholeSeparator(str, "/")));
        String str2 = (String) arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        try {
            str = StringUtils.join(arrayList, "/") + "/" + UrlEscapers.m19616b().mo19621b(str2);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        return str.replace("http:/", "http://");
    }

    /* renamed from: D3 */
    public Activity m3753D3() {
        return this.f76684d4;
    }

    /* renamed from: E3 */
    public String m3752E3(Bundle bundle) {
        StringBuilder sb;
        String str;
        String str2 = "<font color=\"#337a33\" size=\"18\"><b>" + bundle.getString("Title") + " </b></font></div><br/>";
        String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(bundle.getString("Titles"), "||||");
        for (int i = 0; i < splitByWholeSeparator.length; i++) {
            String str3 = "\n" + splitByWholeSeparator[i];
            if (i % 2 == 0) {
                sb = new StringBuilder();
                str = "<font color=\"#000000\" size=\"14\"><b>";
            } else {
                sb = new StringBuilder();
                str = "<font color=\"#777777\" size=\"14\"><b>";
            }
            sb.append(str);
            sb.append(str3);
            sb.append(" </b></font></div><br/>");
            String sb2 = sb.toString();
            if (i == splitByWholeSeparator.length - 1) {
                sb2 = sb2.replace("<br/>", "");
            }
            str2 = str2 + sb2;
        }
        return str2;
    }

    /* renamed from: F3 */
    public void m3751F3() {
        try {
            ((InputMethodManager) m3753D3().getSystemService("input_method")).hideSoftInputFromWindow(m3753D3().getCurrentFocus().getWindowToken(), 0);
            if (m3753D3().getCurrentFocus() != null) {
                m3753D3().getCurrentFocus().clearFocus();
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: G3 */
    public boolean m3750G3() {
        Bundle bundle = this.f76661G3;
        if (bundle == null) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (this.f76661G3.getBundle(str).containsKey("downloader")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: H3 */
    public boolean m3749H3(String str) {
        return this.f76670P3.containsKey(str);
    }

    /* renamed from: J3 */
    public boolean m3747J3() {
        ArrayList<Bundle> arrayList = ((iMD) m44716w().getApplicationContext()).f83461s;
        if (arrayList == null || arrayList.size() == 0) {
            return true;
        }
        return PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("loaddownload", false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x060a, code lost:
        if (r2.compareTo(r4) >= 0) goto L78;
     */
    /* JADX WARN: Removed duplicated region for block: B:131:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0662  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0674 A[LOOP:4: B:150:0x066e->B:152:0x0674, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02e7  */
    /* renamed from: K3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m3746K3() {
        /*
            Method dump skipped, instructions count: 1673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.downloadFragment.m3746K3():void");
    }

    /* renamed from: L3 */
    public Observable<String> m3745L3() {
        return Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.12
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    downloadFragment.this.m3746K3();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    e.printStackTrace();
                }
                observableEmitter.onComplete();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: M0 */
    public void mo3640M0(Activity activity) {
        super.mo3640M0(activity);
        this.f76684d4 = activity;
    }

    /* renamed from: M3 */
    public void m3744M3() {
        if (this.f76683c4) {
            return;
        }
        this.f76683c4 = true;
        this.f76665K3.m42981R1();
        final long currentTimeMillis = System.currentTimeMillis();
        this.f76671Q3 = currentTimeMillis;
        iMDLogger.m3294f("DownloadAndLoadDBs", "Successful");
        iMDLogger.m3294f("DownloadAndLoadDBs", "Loading Downloads");
        iMDLogger.m3294f("CompressHelper", "Loading DBs after download");
        Observable<String> m3745L3 = m3745L3();
        this.f76662H3 = m3745L3;
        Observable<String> m7193t4 = m3745L3.m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e());
        this.f76662H3 = m7193t4;
        m7193t4.m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.22
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.23
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    downloadFragment.this.f76683c4 = false;
                    th.printStackTrace();
                    iMDLogger.m3294f("downloadFragment", "Error in loaddownloads");
                    downloadFragment.this.m3716a4("Error occured . Tap to Try Again");
                    th.printStackTrace();
                } catch (Exception unused) {
                }
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.24
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
                iMDLogger.m3294f("downloadFragment", "Search called");
                if (downloadFragment.f76647n4 == null) {
                    downloadFragment.this.f76683c4 = false;
                    downloadFragment.this.m3716a4("Error occured in reading database . Tap to try again");
                    return;
                }
                long j = currentTimeMillis;
                downloadFragment downloadfragment = downloadFragment.this;
                if (j != downloadfragment.f76671Q3) {
                    Log.e("download", "other query executed");
                    return;
                }
                downloadfragment.f76683c4 = false;
                downloadFragment.this.m3721Y2();
            }
        });
    }

    /* renamed from: N2 */
    public float m3743N2(int i, String str) {
        if (!f76612B4.containsKey(str)) {
            f76612B4.putIntegerArrayList(str, new ArrayList<>());
            return 0.0f;
        }
        ArrayList<Integer> integerArrayList = f76612B4.getIntegerArrayList(str);
        if (integerArrayList.size() > 5) {
            integerArrayList.remove(0);
        }
        integerArrayList.add(Integer.valueOf(i));
        return m3741O2(integerArrayList) / 2.0f;
    }

    /* renamed from: N3 */
    public boolean m3742N3() {
        return this.f76674T3.getVisibility() != 8;
    }

    /* renamed from: O2 */
    public float m3741O2(ArrayList<Integer> arrayList) {
        Iterator<Integer> it2 = arrayList.iterator();
        long j = 0;
        while (it2.hasNext()) {
            j += it2.next().intValue();
        }
        return ((float) j) / arrayList.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01da A[SYNTHETIC] */
    /* renamed from: P2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m3739P2(android.os.Bundle r25) {
        /*
            Method dump skipped, instructions count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.downloadFragment.m3739P2(android.os.Bundle):void");
    }

    /* renamed from: P3 */
    public String m3738P3(long j) {
        double d;
        if (j <= 0) {
            return "0";
        }
        int log10 = (int) (Math.log10(j) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(d / Math.pow(1024.0d, log10)) + StringUtils.SPACE + new String[]{"B", "KB", "MB", "GB", "TB"}[log10];
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: Q0 */
    public void mo3503Q0(Bundle bundle) {
        super.mo3503Q0(bundle);
        LocalBroadcastManager.m43863b(m3753D3()).m43862c(this.f76693m4, new IntentFilter("reloadDownloads"));
    }

    /* renamed from: Q2 */
    public void m3737Q2() {
        this.f76674T3.setVisibility(8);
        SearchView searchView = this.f76668N3;
        if (searchView != null) {
            searchView.clearFocus();
        }
        m3716a4("Loading Databases");
        iMDLogger.m3294f("DownloadAndLoadDBs", "Starting");
        if (m3731T2().booleanValue()) {
            this.f76689i4.m4900l(this).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.19
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(String str) throws Throwable {
                }
            }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.20
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(Throwable th) throws Throwable {
                    try {
                        iMDLogger.m3294f("DownloadAndLoadDBs", "Error Occured");
                        downloadFragment.this.m3716a4("Failed loading databases .tap to try again");
                    } catch (Exception unused) {
                    }
                }
            }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.21
                @Override // io.reactivex.rxjava3.functions.Action
                public void run() throws Throwable {
                    ArrayList unused = downloadFragment.f76656w4 = null;
                    downloadFragment.this.f76663I3 = null;
                    downloadFragment.this.m3744M3();
                    LocalBroadcastManager.m43863b(downloadFragment.this.m44716w()).m43861d(new Intent("reloadaccountdownloads"));
                    downloadFragment.this.m3686z3();
                }
            });
            return;
        }
        iMDLogger.m3294f("DownloadAndLoadDBs", "No Permission");
        m3716a4("Storage Permission not granted. Tap to Allow");
    }

    /* renamed from: Q3 */
    public void m3736Q3() {
        iMDLogger.m3294f("RefereshDBs", "Clicked");
        try {
            this.f76689i4.m4906j(this.f76689i4.m5004E1() + "/DBs.db");
            this.f76689i4.m4906j(this.f76689i4.m5004E1() + "/DBs.z");
            this.f76689i4.m4906j(this.f76689i4.m5004E1() + "/DBs.md5");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        iMDLogger.m3294f("RefreshDBs", "DownloadingAndLoadDBS");
        if (m3742N3()) {
            return;
        }
        m3737Q2();
    }

    /* renamed from: R2 */
    public String m3735R2(String str) {
        if (this.f76670P3.containsKey(str)) {
            return this.f76670P3.getString(str);
        }
        return null;
    }

    /* renamed from: R3 */
    public Observable<Bundle> m3734R3() {
        return Observable.m7156x1(new ObservableOnSubscribe<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.9
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<Bundle> observableEmitter) throws Throwable {
                if (downloadFragment.this.f76661G3 == null) {
                    return;
                }
                for (String str : downloadFragment.this.f76661G3.keySet()) {
                    Bundle bundle = downloadFragment.this.f76661G3.getBundle(str);
                    if (downloadFragment.this.m3713c3(bundle) != 100) {
                        observableEmitter.onNext(bundle);
                    }
                }
                observableEmitter.onComplete();
            }
        });
    }

    /* renamed from: S2 */
    public void m3733S2() {
        if (this.f76683c4) {
            return;
        }
        iMDLogger.m3294f("download", "NotifyDatasetChange");
        this.f76665K3.getRecycledViewPool().m42673b();
        this.f76665K3.getAdapter().m42860G();
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: T0 */
    public void mo3545T0(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(C4804R.C4811menu.f87321download, menu);
        final SearchView searchView = (SearchView) menu.findItem(C4804R.C4808id.f86789action_search).getActionView();
        MenuItem findItem = menu.findItem(C4804R.C4808id.f86994progress_menu);
        this.f76666L3 = findItem;
        this.f76667M3 = (ProgressBar) findItem.getActionView();
        this.f76668N3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Store");
        final String str = this.f76679Y3;
        this.f76668N3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.26
            @Override // java.lang.Runnable
            public void run() {
                iMDLogger.m3290j("DownloadFragment", "Running post delay");
                downloadFragment.this.f76682b4 = true;
                downloadFragment.this.f76668N3.m51655i0(str, false);
                if (downloadFragment.this.f76681a4 == null) {
                    iMDLogger.m3290j("DownloadFragment", "mQuery is null");
                    return;
                }
                if (downloadFragment.this.f76681a4.length() == 0) {
                    iMDLogger.m3290j("DownloadFragment", "mQuery is 0 length !!");
                }
                if (downloadFragment.this.f76681a4 == null || downloadFragment.this.f76681a4.length() <= 0) {
                    return;
                }
                iMDLogger.m3290j("DownloadFragment", "mQuery is " + downloadFragment.this.f76681a4);
                if (downloadFragment.f76647n4 == null || downloadFragment.f76647n4.size() == 0) {
                    iMDLogger.m3290j("DownloadFragment", "setting query true");
                    downloadFragment downloadfragment = downloadFragment.this;
                    downloadfragment.f76668N3.m51655i0(downloadfragment.f76681a4, true);
                } else {
                    iMDLogger.m3290j("DownloadFragment", "setting query false");
                    downloadFragment downloadfragment2 = downloadFragment.this;
                    downloadfragment2.f76668N3.m51655i0(downloadfragment2.f76681a4, false);
                    downloadFragment.this.m3718Z3();
                }
                downloadFragment.this.m3751F3();
            }
        }, 10L);
        this.f76682b4 = false;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.27
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(String str2) {
                if (downloadFragment.this.f76682b4) {
                    if (downloadFragment.this.f76679Y3 == null && (str2 == null || str2.length() == 0)) {
                        return true;
                    }
                    downloadFragment.this.f76679Y3 = str2;
                    downloadFragment.this.f76681a4 = str2;
                    String unused = downloadFragment.f76611A4 = str2;
                    downloadFragment.this.m3744M3();
                    return true;
                }
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(String str2) {
                return false;
            }
        });
        ((ImageView) searchView.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                searchView.m51655i0("", false);
                searchView.clearFocus();
                String unused = downloadFragment.f76611A4 = null;
                downloadFragment.this.f76679Y3 = null;
                downloadFragment.this.f76681a4 = null;
                downloadFragment.this.m3744M3();
                downloadFragment.this.m3751F3();
            }
        });
        super.mo3545T0(menu, menuInflater);
    }

    /* renamed from: T2 */
    public Boolean m3731T2() {
        return Boolean.TRUE;
    }

    /* renamed from: T3 */
    public void m3730T3() {
        try {
            iMDLogger.m3296d("sendFavorite", "Sending FavoriteChanged message");
            LocalBroadcastManager.m43863b(m3753D3()).m43861d(new Intent("referesh.account"));
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f76685e4 = Typeface.createFromAsset(m44716w().getAssets(), "fonts/HelveticaNeue-Light.otf");
        this.f76661G3 = new Bundle();
        ((iMD) m44716w().getApplicationContext()).f83455D2 = this;
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87144fragment_download, viewGroup, false);
        this.f76689i4 = new CompressHelper(m44716w());
        this.f76669O3 = new VBHelper(m44716w());
        if (bundle != null && bundle.containsKey("Query")) {
            this.f76681a4 = bundle.getString("Query");
            iMDLogger.m3290j("DownloadFragment", "Loading mQuery " + this.f76681a4);
        }
        if (bundle != null && bundle.containsKey("LastStatus")) {
            this.f76687g4 = bundle.getString("LastStatus");
        }
        this.f76664J3 = inflate;
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C4804R.C4808id.f87001recycler_view);
        this.f76665K3 = recyclerView;
        recyclerView.m42923n(new DividerItemDecoration(m3753D3(), 1));
        BetterLinearLayoutManager betterLinearLayoutManager = new BetterLinearLayoutManager(m3753D3(), 1, false);
        this.f76686f4 = betterLinearLayoutManager;
        this.f76665K3.setLayoutManager(betterLinearLayoutManager);
        m44735q2(true);
        f76649p4 = new HashMap<>();
        f76650q4 = new HashMap<>();
        f76651r4 = new HashMap<>();
        f76652s4 = new HashMap<>();
        f76653t4 = new ArrayList<>();
        DownloadsAdapter downloadsAdapter = new DownloadsAdapter();
        this.f76690j4 = downloadsAdapter;
        this.f76665K3.setAdapter(downloadsAdapter);
        this.f76673S3 = (SegmentedGroup) this.f76664J3.findViewById(C4804R.C4808id.f87013segment);
        this.f76674T3 = (FrameLayout) this.f76664J3.findViewById(C4804R.C4808id.f86952load_screen);
        this.f76675U3 = (Button) this.f76664J3.findViewById(C4804R.C4808id.f86951load_button);
        this.f76674T3.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                downloadFragment.this.m3737Q2();
            }
        });
        this.f76675U3.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                downloadFragment.this.m3737Q2();
            }
        });
        this.f76675U3.setTypeface(this.f76685e4);
        f76611A4 = "";
        m3714b4();
        if (f76612B4 == null) {
            f76612B4 = new Bundle();
            f76614D4 = new HashMap<>();
            this.f76673S3.check(C4804R.C4808id.f86966newest);
            m3729U2();
            if (m3747J3()) {
                m3737Q2();
            }
        } else {
            this.f76673S3.check(f76613C4);
        }
        this.f76673S3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                downloadFragment.f76613C4 = i;
                downloadFragment.this.m3744M3();
            }
        });
        return this.f76664J3;
    }

    /* renamed from: U3 */
    public void m3728U3(String str, Runnable runnable) {
        if (f76650q4.containsKey(str)) {
            f76650q4.remove(str);
        }
        f76650q4.put(str, runnable);
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: V0 */
    public void mo3638V0() {
        super.mo3638V0();
        try {
            LocalBroadcastManager.m43863b(m3753D3()).m43859f(this.f76693m4);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
        }
    }

    /* renamed from: V2 */
    public int m3727V2(Bundle bundle, String str) {
        if (bundle.containsKey(str)) {
            return bundle.getInt(str);
        }
        return 0;
    }

    /* renamed from: V3 */
    public void m3726V3(String str, Runnable runnable) {
        if (f76651r4.containsKey(str)) {
            f76651r4.remove(str);
        }
        f76651r4.put(str, runnable);
    }

    /* renamed from: W2 */
    public Observable<String> m3725W2() {
        return Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.10
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
            }
        });
    }

    /* renamed from: W3 */
    public void m3724W3(String str, Runnable runnable) {
        if (f76652s4.containsKey(str)) {
            f76652s4.remove(str);
        }
        f76652s4.put(str, runnable);
    }

    /* renamed from: X2 */
    public void m3723X2(Bundle bundle, String str) {
        if (str.equals("downloader") && bundle.containsKey("id")) {
            Log.e("here", "here");
        }
        if (bundle.containsKey(str)) {
            bundle.remove(str);
        }
    }

    /* renamed from: X3 */
    public void m3722X3(String str, CircleProgressView circleProgressView) {
        if (f76649p4.containsKey(str)) {
            f76649p4.remove(str);
        }
        f76649p4.put(str, circleProgressView);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00aa, code lost:
        if (android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).contains("SearchResult") != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00ac, code lost:
        android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).edit().remove(r0).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00bf, code lost:
        android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).edit().putString(r0, r4.getString("Title")).commit();
        m3733S2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ee, code lost:
        if (android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).contains("ContentSearchResult") != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* renamed from: Y3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m3720Y3(android.os.Bundle r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "DownloadPath"
            boolean r1 = r5.equals(r0)
            if (r1 == 0) goto L53
            if (r4 == 0) goto Lf1
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto L2b
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            android.content.SharedPreferences$Editor r5 = r5.remove(r0)
            r5.commit()
        L2b:
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            java.lang.String r1 = "Path"
            java.lang.String r4 = r4.getString(r1)
            android.content.SharedPreferences$Editor r4 = r5.putString(r0, r4)
            r4.commit()
            androidx.fragment.app.FragmentActivity r4 = r3.m44716w()
            android.content.Context r4 = r4.getApplicationContext()
            net.imedicaldoctor.imd.iMD r4 = (net.imedicaldoctor.imd.iMD) r4
            r5 = 0
            r4.f83459Y = r5
            goto Lf1
        L53:
            java.lang.String r0 = "Tab"
            boolean r1 = r5.equals(r0)
            java.lang.String r2 = "Title"
            if (r1 == 0) goto L96
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto L7e
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            android.content.SharedPreferences$Editor r5 = r5.remove(r0)
            r5.commit()
        L7e:
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            java.lang.String r4 = r4.getString(r2)
            android.content.SharedPreferences$Editor r4 = r5.putString(r0, r4)
            r4.commit()
            goto Lf1
        L96:
            java.lang.String r0 = "SearchResult"
            boolean r1 = r5.equals(r0)
            if (r1 == 0) goto Lda
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto Lbf
        Lac:
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            android.content.SharedPreferences$Editor r5 = r5.remove(r0)
            r5.commit()
        Lbf:
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            android.content.SharedPreferences$Editor r5 = r5.edit()
            java.lang.String r4 = r4.getString(r2)
            android.content.SharedPreferences$Editor r4 = r5.putString(r0, r4)
            r4.commit()
            r3.m3733S2()
            goto Lf1
        Lda:
            java.lang.String r0 = "ContentSearchResult"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto Lf1
            androidx.fragment.app.FragmentActivity r5 = r3.m44716w()
            android.content.SharedPreferences r5 = android.preference.PreferenceManager.getDefaultSharedPreferences(r5)
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto Lbf
            goto Lac
        Lf1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.downloadFragment.m3720Y3(android.os.Bundle, java.lang.String):void");
    }

    /* renamed from: Z2 */
    public void m3719Z2(Bundle bundle) {
        long j;
        downloadFragment downloadfragment;
        ArrayList arrayList;
        String str;
        String str2;
        String str3;
        String str4;
        ArrayList arrayList2;
        String str5;
        String str6;
        final String str7;
        String str8;
        final int i;
        String str9;
        long j2;
        double d;
        double d2;
        Bundle bundle2 = bundle;
        if (bundle2.containsKey("downloader") || !bundle2.containsKey("PartFileSize") || StringUtils.splitByWholeSeparator(bundle2.getString("PartFileSize"), ",,,") == null) {
            return;
        }
        if (!bundle2.containsKey("Parts")) {
            m3739P2(bundle);
        }
        ArrayList parcelableArrayList = bundle2.getParcelableArrayList("Parts");
        String m4861y = this.f76689i4.m4861y();
        long usableSpace = new File(m4861y).getUsableSpace();
        try {
            j = Long.valueOf(bundle2.getString("fileSize")).longValue();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            j = 0;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        boolean z = PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("lessspace", false);
        String str10 = "/";
        String str11 = "fileSize";
        String str12 = "URL";
        if (bundle2.getString("folderSizeKey") != null) {
            Double valueOf = Double.valueOf(bundle2.getString("folderSizeKey"));
            if (valueOf.doubleValue() > j * 4) {
                valueOf = Double.valueOf(j * 2);
            }
            try {
                if (j <= 524288000 || !z) {
                    d = valueOf.doubleValue() + j;
                } else {
                    d = valueOf.doubleValue() + (j / 9);
                }
            } catch (Exception e2) {
                FirebaseCrashlytics.m18030d().m18027g(e2);
                e2.printStackTrace();
                d = 0.0d;
            }
            if (bundle2.containsKey("Delta")) {
                d = j * 2;
            }
            iMDLogger.m3294f("download", "Available : " + usableSpace);
            iMDLogger.m3294f("download", "FileSize : " + j);
            StringBuilder sb = new StringBuilder();
            sb.append("Required : ");
            long j3 = j;
            sb.append(String.valueOf((long) d));
            iMDLogger.m3294f("download", sb.toString());
            int i2 = 0;
            long j4 = 0;
            while (i2 < parcelableArrayList.size()) {
                Bundle bundle3 = (Bundle) parcelableArrayList.get(i2);
                ArrayList arrayList3 = parcelableArrayList;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(m4861y);
                sb2.append(str10);
                String str13 = str10;
                sb2.append(bundle3.getString("FileName"));
                String sb3 = sb2.toString();
                bundle3.getString("URL");
                String str14 = sb3 + ".download";
                if (new File(str14).exists()) {
                    j4 += new File(str14).length();
                }
                if (new File(sb3).exists()) {
                    j4 += new File(sb3).length();
                }
                i2++;
                parcelableArrayList = arrayList3;
                str10 = str13;
            }
            arrayList = parcelableArrayList;
            str = str10;
            iMDLogger.m3294f("download", "Used : " + j4);
            double d3 = d - ((double) j4);
            iMDLogger.m3294f("download", "Required Really : " + String.valueOf((long) d3));
            if (((double) usableSpace) < d3) {
                new AlertDialog.Builder(m3753D3(), C4804R.style.f88094alertDialogTheme).mo26292l("You need at least " + decimalFormat.format((d3 / 1024.0d) / 1024.0d) + " MB available . you already have " + decimalFormat.format((d2 / 1024.0d) / 1024.0d) + " MB Available. Please free up some space . \n After install " + decimalFormat.format((j3 / 1024.0d) / 1024.0d) + " MB would be freed").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.35
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                    }
                }).mo26284p("Change Download Path", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.34
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        HashSet<String> m4922e1 = downloadFragment.this.f76689i4.m4922e1();
                        ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
                        Iterator<String> it2 = m4922e1.iterator();
                        while (it2.hasNext()) {
                            String next = it2.next();
                            if (!next.contains("/.") && !next.contains("/sdcard/external_sd") && !next.contains("/mnt/sdcard/external_sd")) {
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("Path", next);
                                long usableSpace2 = new File(next).getUsableSpace();
                                DecimalFormat decimalFormat2 = new DecimalFormat("#,##0");
                                bundle4.putString("Title", downloadFragment.this.f76689i4.m4970Q(next));
                                bundle4.putString("Size", (decimalFormat2.format((usableSpace2 / 1024) / 1024) + " MB") + " - " + next);
                                arrayList4.add(bundle4);
                            }
                        }
                        String m4861y2 = downloadFragment.this.f76689i4.m4861y();
                        fileSizeSettingsList filesizesettingslist = new fileSizeSettingsList();
                        Bundle bundle5 = new Bundle();
                        bundle5.putString("type", "DownloadPath");
                        bundle5.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList4);
                        bundle5.putString("titleProperty", "Title");
                        bundle5.putString("selected", m4861y2);
                        filesizesettingslist.m44751k2(bundle5);
                        filesizesettingslist.m44870c3(true);
                        filesizesettingslist.m44844E2(downloadFragment.this, 0);
                        filesizesettingslist.mo29915h3(downloadFragment.this.m44820L(), "SettingListDownloadPath");
                    }
                }).m52864I();
                return;
            }
            downloadfragment = this;
        } else {
            downloadfragment = this;
            arrayList = parcelableArrayList;
            str = "/";
        }
        iMDLogger.m3296d("StartDownload", bundle2.getString("URL"));
        downloadfragment.m3723X2(bundle2, "retry");
        downloadfragment.m3723X2(bundle2, "Buy");
        String str15 = "error";
        downloadfragment.m3723X2(bundle2, "error");
        String str16 = "";
        bundle2.putString("downloader", "");
        m3715b3(bundle);
        String str17 = "id";
        String string = bundle2.getString("id");
        int i3 = 0;
        long j5 = 0;
        while (true) {
            str2 = str17;
            if (i3 >= arrayList.size()) {
                break;
            }
            ArrayList arrayList4 = arrayList;
            String string2 = ((Bundle) arrayList4.get(i3)).getString(str12);
            String str18 = string;
            StringBuilder sb4 = new StringBuilder();
            String str19 = str16;
            sb4.append("Started : ");
            sb4.append(string2);
            iMDLogger.m3296d("StartDownload", sb4.toString());
            Bundle bundle4 = (Bundle) arrayList4.get(i3);
            int i4 = i3;
            long j6 = j5;
            bundle4.putDouble("bytesDownloaded", 0.0d);
            bundle4.putDouble("bytesTotal", 0.0d);
            bundle4.putLong("remaining", 0L);
            bundle4.putLong("avgSpeed", 0L);
            bundle4.putInt("Progress", 0);
            downloadfragment.m3723X2(bundle4, str15);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(m4861y);
            String str20 = str;
            sb5.append(str20);
            sb5.append(bundle4.getString("FileName"));
            String sb6 = sb5.toString();
            if (bundle4.containsKey("completed")) {
                arrayList2 = arrayList4;
                long length = new File(sb6).length();
                str4 = m4861y;
                String str21 = str11;
                str3 = str12;
                long longValue = Long.valueOf(bundle2.getString(str21)).longValue();
                str6 = str15;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(string2);
                str5 = str21;
                sb7.append(" Already Downloaded. With File Size ");
                sb7.append(length);
                sb7.append(", Master file size : ");
                sb7.append(longValue);
                iMDLogger.m3296d("StartDownload", sb7.toString());
                if (length < longValue / 11) {
                    iMDLogger.m3296d("StartDownload", string2 + " Already Downloaded. Very Low file size. Restarting");
                    downloadfragment.f76689i4.m4906j(sb6);
                    downloadfragment.m3723X2(bundle4, "completed");
                }
                str7 = str18;
                str8 = str19;
                i = i4;
                j5 = j6;
                str9 = str3;
                int i5 = i + 1;
                str12 = str9;
                str16 = str8;
                string = str7;
                str17 = str2;
                arrayList = arrayList2;
                m4861y = str4;
                bundle2 = bundle;
                str = str20;
                i3 = i5;
                str15 = str6;
                str11 = str5;
            } else {
                str3 = str12;
                str4 = m4861y;
                arrayList2 = arrayList4;
                str5 = str11;
                str6 = str15;
            }
            if (bundle4.containsKey("downloader")) {
                iMDLogger.m3296d("StartDownload", string2 + " has Downloaderkey");
                str7 = str18;
                str8 = str19;
                i = i4;
                j5 = j6;
                str9 = str3;
                int i52 = i + 1;
                str12 = str9;
                str16 = str8;
                string = str7;
                str17 = str2;
                arrayList = arrayList2;
                m4861y = str4;
                bundle2 = bundle;
                str = str20;
                i3 = i52;
                str15 = str6;
                str11 = str5;
            } else {
                str9 = str3;
                final String string3 = bundle4.getString(str9);
                String str22 = sb6 + ".download";
                if (new File(str22).exists()) {
                    j2 = new File(str22).length();
                    j6 += j2;
                } else {
                    j2 = 0;
                }
                Bundle bundle5 = new Bundle();
                str8 = str19;
                bundle5.putString("Go", str8);
                downloadfragment.m3723X2(bundle4, "downloader");
                bundle4.putBundle("downloader", bundle5);
                Observable<HttpURLConnection> m7193t4 = downloadfragment.f76689i4.m4984L0(string3, sb6, bundle4).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e());
                str7 = str18;
                i = i4;
                DisposableObserver<HttpURLConnection> disposableObserver = new DisposableObserver<HttpURLConnection>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.36
                    @Override // io.reactivex.rxjava3.core.Observer
                    /* renamed from: b */
                    public void onNext(@NonNull HttpURLConnection httpURLConnection) {
                        Bundle bundle6 = downloadFragment.this.f76661G3.getBundle(str7);
                        if (bundle6.containsKey("Parts")) {
                            downloadFragment.this.m3715b3((Bundle) bundle6.getParcelableArrayList("Parts").get(i));
                        }
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onComplete() {
                        Bundle bundle6 = downloadFragment.this.f76661G3.getBundle(str7);
                        iMDLogger.m3290j("DownloadFile OnPartCompleted", bundle6.getString("URL"));
                        if (bundle6.containsKey("Parts")) {
                            Bundle bundle7 = (Bundle) bundle6.getParcelableArrayList("Parts").get(i);
                            downloadFragment.this.m3723X2(bundle7, "downloader");
                            bundle7.putString("completed", "");
                            downloadFragment.this.m3756A3(bundle6);
                        }
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onError(@NonNull Throwable th) {
                        final Bundle bundle6;
                        th.printStackTrace();
                        iMDLogger.m3294f("DownloadWithResume", "Error occured : " + th.getMessage() + ". url : " + string3);
                        if (downloadFragment.f76647n4 == null || (bundle6 = downloadFragment.this.f76661G3.getBundle(str7)) == null) {
                            return;
                        }
                        if (!bundle6.containsKey("Parts")) {
                            iMDLogger.m3294f("DownloadFile OnError", "Can't find partskey in " + bundle6.getString("URL"));
                        } else if (bundle6.containsKey("Parts")) {
                            ((Bundle) bundle6.getParcelableArrayList("Parts").get(i)).putString("error", th.getMessage());
                            if (!bundle6.containsKey("error")) {
                                bundle6.putString("error", th.getMessage());
                                downloadFragment.this.m3723X2(bundle6, "downloader");
                                downloadFragment.this.m3715b3(bundle6);
                                downloadFragment.this.m3717a3(bundle6);
                                int i6 = (bundle6.containsKey("Retry") ? bundle6.getInt("Retry") : 0) + 1;
                                downloadFragment.this.m3723X2(bundle6, "Retry");
                                bundle6.putInt("Retry", i6);
                                if (i6 < 20) {
                                    downloadFragment.this.f76664J3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.36.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            downloadFragment.this.m3719Z2(bundle6);
                                        }
                                    }, 3000L);
                                }
                            }
                            if (bundle6.getString("error").equals("Stopped")) {
                                downloadFragment.this.m3723X2(bundle6, "error");
                            }
                            if (bundle6.containsKey("videoIdKey")) {
                                String string4 = bundle6.getString("videoIdKey");
                                if (downloadFragment.f76651r4.containsKey(string4)) {
                                    try {
                                        ((Runnable) downloadFragment.f76651r4.get(string4)).run();
                                    } catch (Exception e3) {
                                        FirebaseCrashlytics.m18030d().m18027g(e3);
                                    }
                                }
                            }
                        }
                    }
                };
                m7193t4.mo6065a(disposableObserver);
                if (f76614D4.containsKey(string3)) {
                    f76614D4.remove(string3);
                }
                f76614D4.put(string3, disposableObserver);
                bundle4.putLong(f76646j5, j2);
                j5 = j6;
                int i522 = i + 1;
                str12 = str9;
                str16 = str8;
                string = str7;
                str17 = str2;
                arrayList = arrayList2;
                m4861y = str4;
                bundle2 = bundle;
                str = str20;
                i3 = i522;
                str15 = str6;
                str11 = str5;
            }
        }
        m3756A3(bundle);
        bundle.putLong(f76646j5, j5);
        if (downloadfragment.f76661G3.containsKey(bundle.getString(str2))) {
            downloadfragment.f76661G3.remove(bundle.getString(str2));
        }
        downloadfragment.f76661G3.putBundle(bundle.getString(str2), bundle);
    }

    /* renamed from: Z3 */
    public void m3718Z3() {
        if (this.f76683c4) {
            return;
        }
        RecyclerView.Adapter adapter = this.f76665K3.getAdapter();
        DownloadsAdapter downloadsAdapter = this.f76690j4;
        if (adapter != downloadsAdapter) {
            this.f76665K3.setAdapter(downloadsAdapter);
        }
    }

    /* renamed from: a3 */
    public void m3717a3(final Bundle bundle) {
        m3723X2(bundle, "downloader");
        m3715b3(bundle);
        this.f76689i4.m5017A0(m3753D3(), Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.37
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                ArrayList parcelableArrayList;
                iMDLogger.m3296d("StopDownload", bundle.getString("URL"));
                if (bundle.containsKey("Parts") && (parcelableArrayList = bundle.getParcelableArrayList("Parts")) != null) {
                    for (int i = 0; i < parcelableArrayList.size(); i++) {
                        Bundle bundle2 = (Bundle) parcelableArrayList.get(i);
                        if (bundle2.containsKey("downloader")) {
                            downloadFragment.this.m3723X2(bundle2.getBundle("downloader"), "Go");
                            bundle2.remove("downloader");
                        }
                    }
                    bundle.remove("downloader");
                    bundle.remove("Parts");
                }
            }
        })).m7339e6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.38
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                downloadFragment.this.m3715b3(bundle);
            }
        });
    }

    /* renamed from: a4 */
    public void m3716a4(String str) {
        RecyclerView recyclerView;
        StatusAdapter statusAdapter;
        this.f76665K3.setLayoutManager(new BetterLinearLayoutManager(m44716w(), 1, false));
        if (str.toLowerCase().contains("Tap to".toLowerCase())) {
            recyclerView = this.f76665K3;
            statusAdapter = new StatusAdapter(m44716w(), str) { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.11
                @Override // net.imedicaldoctor.imd.ViewHolders.StatusAdapter
                /* renamed from: d0 */
                public void mo3376d0() {
                    downloadFragment.this.m3736Q3();
                }
            };
        } else {
            recyclerView = this.f76665K3;
            statusAdapter = new StatusAdapter(m44716w(), str);
        }
        recyclerView.setAdapter(statusAdapter);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c2  */
    /* renamed from: b3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m3715b3(android.os.Bundle r5) {
        /*
            r4 = this;
            boolean r0 = r4.f76683c4
            if (r0 == 0) goto L5
            return
        L5:
            java.lang.String r0 = "videoIdKey"
            boolean r1 = r5.containsKey(r0)
            if (r1 == 0) goto L4b
            java.util.HashMap<java.lang.String, at.grabner.circleprogress.CircleProgressView> r1 = net.imedicaldoctor.imd.Fragments.downloadFragment.f76649p4
            java.lang.String r2 = r5.getString(r0)
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L2f
            java.util.HashMap<java.lang.String, at.grabner.circleprogress.CircleProgressView> r1 = net.imedicaldoctor.imd.Fragments.downloadFragment.f76649p4
            java.lang.String r2 = r5.getString(r0)
            java.lang.Object r1 = r1.get(r2)
            at.grabner.circleprogress.CircleProgressView r1 = (at.grabner.circleprogress.CircleProgressView) r1
            java.lang.String r2 = "Progress"
            int r2 = r4.m3727V2(r5, r2)
            float r2 = (float) r2
            r1.setValue(r2)
        L2f:
            java.util.HashMap<java.lang.String, java.lang.Runnable> r1 = net.imedicaldoctor.imd.Fragments.downloadFragment.f76652s4
            java.lang.String r2 = r5.getString(r0)
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L4a
            java.util.HashMap<java.lang.String, java.lang.Runnable> r1 = net.imedicaldoctor.imd.Fragments.downloadFragment.f76652s4
            java.lang.String r5 = r5.getString(r0)
            java.lang.Object r5 = r1.get(r5)
            java.lang.Runnable r5 = (java.lang.Runnable) r5
            r5.run()
        L4a:
            return
        L4b:
            java.util.ArrayList<android.os.Bundle> r0 = net.imedicaldoctor.imd.Fragments.downloadFragment.f76647n4
            if (r0 == 0) goto Le7
            android.os.Bundle r0 = r4.f76660F3
            if (r0 == 0) goto Le7
            java.lang.String r1 = "id"
            java.lang.String r2 = r5.getString(r1)
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto Le7
            androidx.recyclerview.widget.RecyclerView r0 = r4.f76665K3
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            net.imedicaldoctor.imd.Fragments.downloadFragment$BetterLinearLayoutManager r0 = (net.imedicaldoctor.imd.Fragments.downloadFragment.BetterLinearLayoutManager) r0
            int r2 = r0.m43136x2()     // Catch: java.lang.Exception -> L72
            int r0 = r0.m43194A2()     // Catch: java.lang.Exception -> L70
            goto L84
        L70:
            r0 = move-exception
            goto L74
        L72:
            r0 = move-exception
            r2 = 0
        L74:
            com.google.firebase.crashlytics.FirebaseCrashlytics r3 = com.google.firebase.crashlytics.FirebaseCrashlytics.m18030d()
            r3.m18027g(r0)
            java.lang.String r0 = "DownloadFragment"
            java.lang.String r3 = "Error in getting first and last visible position"
            net.imedicaldoctor.imd.iMDLogger.m3294f(r0, r3)
            r0 = 10000(0x2710, float:1.4013E-41)
        L84:
            android.os.Bundle r3 = r4.f76660F3
            java.lang.String r5 = r5.getString(r1)
            int r5 = r3.getInt(r5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Contains "
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = " . first : "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r3 = ", Last : "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "download"
            net.imedicaldoctor.imd.iMDLogger.m3294f(r3, r1)
            r1 = -1
            if (r2 != r1) goto Lc2
            androidx.recyclerview.widget.RecyclerView r5 = r4.f76665K3
            net.imedicaldoctor.imd.Fragments.downloadFragment$DownloadsAdapter r0 = new net.imedicaldoctor.imd.Fragments.downloadFragment$DownloadsAdapter
            r0.<init>()
            r5.setAdapter(r0)
            return
        Lc2:
            if (r5 < r2) goto Le7
            if (r5 > r0) goto Le7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "NotifyItemChanged "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            net.imedicaldoctor.imd.iMDLogger.m3296d(r3, r0)
            boolean r0 = r4.f76683c4
            if (r0 != 0) goto Le7
            androidx.recyclerview.widget.RecyclerView r0 = r4.f76665K3
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()
            r0.m42859H(r5)
        Le7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.downloadFragment.m3715b3(android.os.Bundle):void");
    }

    /* renamed from: b4 */
    public void m3714b4() {
        iMDLogger.m3294f("downloadFragment", "Start Reloading Tables");
        Timer timer = new Timer();
        this.f76677W3 = timer;
        timer.scheduleAtFixedRate(new TimerTask() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.8
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                downloadFragment.this.f76691k4.obtainMessage(1).sendToTarget();
            }
        }, SimpleExoPlayer.f32068s1, SimpleExoPlayer.f32068s1);
    }

    /* JADX WARN: Type inference failed for: r2v22, types: [java.lang.String] */
    /* renamed from: c3 */
    public int m3713c3(Bundle bundle) {
        double d;
        float f;
        String str;
        long j;
        String str2;
        double d2;
        Bundle bundle2;
        if (!bundle.containsKey("error") && bundle.containsKey("downloader")) {
            String m4861y = this.f76689i4.m4861y();
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("Parts");
            if (parcelableArrayList == null) {
                return 100;
            }
            double d3 = 0.0d;
            long j2 = 0;
            for (int i = 0; i < parcelableArrayList.size(); i++) {
                if (((Bundle) parcelableArrayList.get(i)).containsKey("completed")) {
                    d3 += new File(m4861y + "/" + bundle2.getString("FileName")).length();
                } else {
                    String str3 = m4861y + "/" + bundle2.getString("FileName") + ".download";
                    long length = new File(str3).exists() ? new File(str3).length() : 0L;
                    d3 += length;
                    j2 += length;
                }
            }
            double doubleValue = Double.valueOf(bundle.getString("fileSize")).doubleValue();
            float f2 = d3 > 0.0d ? (((float) d3) / ((float) doubleValue)) * 100.0f : 0.0f;
            long j3 = j2 - bundle.getLong(f76646j5);
            if (j3 < 0) {
                j3 = 0;
            } else {
                m3723X2(bundle, f76646j5);
                bundle.putLong(f76646j5, j2);
            }
            long time = new Date().getTime();
            if (bundle.containsKey("DateUpdated")) {
                f = f2;
                long j4 = bundle.getLong("DateUpdated");
                if (bundle.containsKey("bytesDownloaded")) {
                    str2 = "bytesDownloaded";
                    d2 = bundle.getDouble("bytesDownloaded");
                } else {
                    str2 = "bytesDownloaded";
                    d2 = 0.0d;
                }
                d = doubleValue;
                long j5 = (long) ((d3 - d2) / ((time - j4) / 1000.0d));
                ?? r2 = "Received: " + d3 + " , OldDownloaded : " + d2 + ", DAtenow : " + time + ", Date prev :" + j4;
                Log.e("New Speed", r2);
                m3723X2(bundle, "DateUpdated");
                bundle.putLong("DateUpdated", time);
                j = r2;
                str = str2;
            } else {
                d = doubleValue;
                f = f2;
                bundle.putLong("DateUpdated", time);
                str = "bytesDownloaded";
                j = 0;
            }
            m3723X2(bundle, str);
            m3723X2(bundle, "bytesTotal");
            m3723X2(bundle, "Progress");
            m3723X2(bundle, "remaining");
            m3723X2(bundle, "avgSpeed");
            bundle.putLong("avgSpeed", j);
            m3743N2((int) j3, bundle.getString("URL"));
            bundle.putDouble(str, d3);
            double d4 = d;
            bundle.putDouble("bytesTotal", d4);
            bundle.putLong("remaining", j > 0 ? ((long) (d4 - d3)) / j : 0L);
            bundle.putInt("Progress", (int) f);
            return f == 100.0f ? 50 : 0;
        }
        return 100;
    }

    /* renamed from: c4 */
    public boolean m3712c4(String str) {
        Bundle m3755B3 = m3755B3(str);
        if (m3755B3 == null) {
            return false;
        }
        m3719Z2(m3755B3);
        return true;
    }

    /* renamed from: d4 */
    public boolean m3710d4(String str) {
        Bundle m3755B3 = m3755B3(str);
        if (m3755B3 == null) {
            return false;
        }
        m3717a3(m3755B3);
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: e1 */
    public boolean mo3709e1(MenuItem menuItem) {
        if (menuItem.getItemId() == C4804R.C4808id.f87002referesh_button) {
            if (m3750G3()) {
                new AlertDialog.Builder(m3753D3(), C4804R.style.f88094alertDialogTheme).mo26292l("You are downloading some files, if you do this, your downloads would cancel ? are you sure ?").mo26266y("Yes", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.30
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloadFragment.this.m3736Q3();
                    }
                }).mo26284p("Hell, No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.29
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).m52864I();
                return true;
            }
            m3736Q3();
            return true;
        }
        return super.mo3709e1(menuItem);
    }

    /* renamed from: e4 */
    public void m3707e4() {
        try {
            m3734R3().m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.4
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(Bundle bundle) throws Throwable {
                    if (bundle.getInt("Progress") != 100) {
                        downloadFragment.this.m3715b3(bundle);
                        return;
                    }
                    iMDLogger.m3294f("downloadFragment", "Update " + bundle.getString("id") + " progress is 100!!!!");
                    downloadFragment.this.m3756A3(bundle);
                }
            }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.5
                @Override // io.reactivex.rxjava3.functions.Consumer
                /* renamed from: a */
                public void accept(Throwable th) throws Throwable {
                    try {
                        iMDLogger.m3294f("UpdateStatus", "Error occured in reloading tables : " + th.getMessage());
                        th.printStackTrace();
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        Log.e("FoundIT", "FoundIT . it was here in updatestatus");
                    }
                }
            });
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }

    /* renamed from: y3 */
    public void m3687y3(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Bundle bundle = new Bundle();
        bundle.putString("Title", str);
        bundle.putString("URL", str2);
        bundle.putString("FileName", str5);
        bundle.putString("fileSize", str4);
        bundle.putString("Icon", "");
        bundle.putString("name", str5);
        bundle.putString("videoIdKey", str6);
        bundle.putString("id", str6);
        bundle.putString(f76645i5, IcyHeaders.f35463C2);
        bundle.putString("PartFileSize", str7);
        bundle.putString("savePathKey", str3);
        if (f76654u4 == null) {
            f76654u4 = new HashMap<>();
        }
        if (f76647n4 == null) {
            f76647n4 = new ArrayList<>();
        }
        if (f76653t4 == null) {
            f76653t4 = new ArrayList<>();
        }
        f76654u4.put(str6, bundle);
        f76647n4.add(bundle);
        f76653t4.add(bundle);
    }

    /* renamed from: z3 */
    public void m3686z3() {
        iMDLogger.m3290j("CheckDBsMD5", "Checking DBs MD5");
        this.f76689i4.m4987K0(this.f76689i4.m4991J() + "/dbs.md5", this.f76689i4.m5004E1() + "/DBs.md5").m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.14
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.15
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    iMDLogger.m3294f("CheckDBSMD5", "Error in downloading dbs.md5 " + th.getMessage());
                    th.printStackTrace();
                } catch (Exception unused) {
                }
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.downloadFragment.16
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
                if (new File(downloadFragment.this.f76689i4.m5004E1() + "/DBs.md5").exists()) {
                    try {
                        String readFileToString = FileUtils.readFileToString(new File(downloadFragment.this.f76689i4.m5004E1() + "/DBs.md5"), "UTF-8");
                        CompressHelper compressHelper = downloadFragment.this.f76689i4;
                        if (readFileToString.equalsIgnoreCase(compressHelper.m5013B1(new File(downloadFragment.this.f76689i4.m5004E1() + "/DBs.db")).replace("\n", ""))) {
                            iMDLogger.m3290j("CheckDBsMD5", "MD5s match , don't delete it");
                        } else {
                            iMDLogger.m3290j("CheckDBsMD5", "MD5s dont match ,  delete it");
                            if (!downloadFragment.this.m3750G3()) {
                                iMDLogger.m3290j("CheckDBsMD5", "Isn't downloading . ");
                                downloadFragment.this.m3736Q3();
                            }
                        }
                    } catch (Exception e) {
                        FirebaseCrashlytics.m18030d().m18027g(e);
                        iMDLogger.m3294f("CheckDBsMD5", "Error in comparing md5 of dbs.db : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
