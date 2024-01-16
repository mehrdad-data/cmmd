package net.imedicaldoctor.imd.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import com.itextpdf.tool.xml.html.HTML;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.requery.android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.Views.ButtonFloatHelp;
import net.imedicaldoctor.imd.Views.ButtonFloatHelpBadge;
import net.imedicaldoctor.imd.iMD;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class accountFragment extends Fragment {

    /* renamed from: F3 */
    private View f76181F3;

    /* renamed from: G3 */
    private RecyclerView f76182G3;

    /* renamed from: H3 */
    private SwipeRefreshLayout f76183H3;

    /* renamed from: I3 */
    private ArrayList<String> f76184I3;

    /* renamed from: J3 */
    private Boolean f76185J3;

    /* renamed from: K3 */
    private String f76186K3;

    /* renamed from: L3 */
    private ArrayList<Bundle> f76187L3;

    /* renamed from: M3 */
    private ArrayList<String> f76188M3;

    /* renamed from: N3 */
    private Bundle f76189N3;

    /* renamed from: O3 */
    private String f76190O3;

    /* renamed from: P3 */
    public CompressHelper f76191P3;

    /* renamed from: Q3 */
    public VBHelper f76192Q3;

    /* renamed from: R3 */
    private Activity f76193R3;

    /* renamed from: S3 */
    private ButtonFloatHelp f76194S3;

    /* renamed from: T3 */
    private ButtonFloatHelpBadge f76195T3;

    /* renamed from: U3 */
    private String f76196U3;

    /* renamed from: V3 */
    private Boolean f76197V3;

    /* renamed from: W3 */
    Typeface f76198W3;

    /* renamed from: X3 */
    private BroadcastReceiver f76199X3 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            iMDLogger.m3296d("account", "let's referesh accounts");
            try {
                accountFragment.this.m3944Q2();
                accountFragment.this.f76182G3.getAdapter().m42860G();
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }
    };

    /* renamed from: Y3 */
    private BroadcastReceiver f76200Y3 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            accountFragment.this.f76183H3.post(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.6.1
                @Override // java.lang.Runnable
                public void run() {
                    accountFragment.this.f76183H3.setRefreshing(true);
                    accountFragment.this.m3945P2();
                }
            });
        }
    };

    /* renamed from: Z3 */
    public BroadcastReceiver f76201Z3 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            iMDLogger.m3290j("downloadFragment", "Received reload Downloads");
            accountFragment.this.m3946O2();
        }
    };

    /* loaded from: classes2.dex */
    public class AccountAdapter extends RecyclerView.Adapter {

        /* renamed from: d */
        HashMap<String, Integer> f76215d = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: net.imedicaldoctor.imd.Fragments.accountFragment$AccountAdapter$17 */
        /* loaded from: classes2.dex */
        public class View$OnClickListenerC447717 implements View.OnClickListener {
            View$OnClickListenerC447717() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Are you sure? your information won't be deleted. ").mo26266y("Yes", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.17.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CompressHelper compressHelper = accountFragment.this.f76191P3;
                        compressHelper.m4890o0("RemoveDevice|||||" + accountFragment.this.f76192Q3.m3421l()).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.17.2.1
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(String str) throws Throwable {
                                if (!str.contains("1|||||")) {
                                    iMDLogger.m3294f("Error", str);
                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "Error occured", 0);
                                    return;
                                }
                                PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().remove("DS").commit();
                                accountFragment.this.f76191P3.m4933b2(null);
                                Intent intent = new Intent(accountFragment.this.m44716w(), activationActivity.class);
                                Process.killProcess(Process.myPid());
                                accountFragment.this.mo4139H2(intent);
                            }
                        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.17.2.2
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(Throwable th) throws Throwable {
                                try {
                                    th.printStackTrace();
                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "Error occured ", 0);
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                }).mo26284p("No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.17.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).m52864I();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: net.imedicaldoctor.imd.Fragments.accountFragment$AccountAdapter$25 */
        /* loaded from: classes2.dex */
        public class View$OnClickListenerC449025 implements View.OnClickListener {
            View$OnClickListenerC449025() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (accountFragment.this.f76186K3.length() > 0) {
                    return;
                }
                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("This will backup your favorites & highlights to the iMD Server and will give you a identifier to restore it later.").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.25.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CompressHelper compressHelper = accountFragment.this.f76191P3;
                        String m4878s0 = compressHelper.m4878s0(compressHelper.m4972P0(), "select dbName,dbTitle,dbAddress,dbDate,dbDocName from favorites", "dbName,dbTitle,dbAddress,dbDate,dbDocName", null);
                        Bundle bundle = new Bundle();
                        bundle.putString("text", "");
                        accountFragment accountfragment = accountFragment.this;
                        final ProgressDialog show = ProgressDialog.show(accountFragment.this.m44716w(), "Backing up", "Please wait...", true);
                        accountFragment.this.f76191P3.m4890o0("SaveToFile|||||" + (m4878s0 + "###" + accountfragment.f76191P3.m4878s0(accountfragment.m3921n3(), "select dbName,dbTitle,dbAddress,dbDate,dbDocName,type,text,note,save from highlight", "dbName,dbTitle,dbAddress,dbDate,dbDocName,type,text,note,save", bundle))).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.25.2.1
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(String str) throws Throwable {
                                show.dismiss();
                                accountFragment accountfragment2 = accountFragment.this;
                                accountfragment2.f76186K3 = "Backup identifier : " + str;
                                accountFragment.this.f76182G3.getAdapter().m42860G();
                            }
                        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.25.2.2
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(Throwable th) throws Throwable {
                                show.dismiss();
                                CompressHelper.m4921e2(accountFragment.this.m44716w(), "Error in contacting server", 1);
                            }
                        });
                    }
                }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.25.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).m52864I();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: net.imedicaldoctor.imd.Fragments.accountFragment$AccountAdapter$3 */
        /* loaded from: classes2.dex */
        public class View$OnClickListenerC45073 implements View.OnClickListener {
            View$OnClickListenerC45073() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final EditText editText = new EditText(accountFragment.this.m44716w());
                editText.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Enter Credit Serial Number").setView(editText).mo26266y("Add Credit", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String obj = editText.getText().toString();
                        if (obj.length() == 0) {
                            CompressHelper.m4921e2(accountFragment.this.m44716w(), "You must enter a serial number", 1);
                            return;
                        }
                        CompressHelper compressHelper = accountFragment.this.f76191P3;
                        compressHelper.m4890o0("AddCredit|||||" + accountFragment.this.f76192Q3.m3421l() + "|||||" + obj).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.3.2.1
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(String str) throws Throwable {
                                String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                                if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), splitByWholeSeparator[1], 1);
                                    return;
                                }
                                accountFragment.this.f76191P3.m4933b2(splitByWholeSeparator[1]);
                                accountFragment.this.m3944Q2();
                                accountFragment.this.m3947N2();
                            }
                        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.3.2.2
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            /* renamed from: a */
                            public void accept(Throwable th) throws Throwable {
                                try {
                                    th.printStackTrace();
                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "Error occured on contacting server, try again later.", 1);
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).m52864I();
            }
        }

        public AccountAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            Bundle m3913d0 = m3913d0(i, accountFragment.this.f76184I3);
            if (!m3913d0.getString("Type").equals("Header") && m3913d0.getString("Type").equals("Item")) {
                String string = m3913d0.getString("Section");
                int i2 = m3913d0.getInt("Index");
                if (string.equals("Account Information") || string.equals("Help")) {
                    return 1;
                }
                if (string.equals("Your Databases")) {
                    return accountFragment.this.f76188M3.contains(TtmlNode.f38128r0) ? 1 : 2;
                } else if (string.equals("About Us")) {
                    if (i2 == 0) {
                        return 3;
                    }
                    return i2 > 5 ? 1 : 4;
                } else if (string.equals("Settings")) {
                    if (i2 == 2 || i2 == 1 || i2 == 3) {
                        return 5;
                    }
                    if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7 || i2 == 8 || i2 == 9 || i2 == 10 || i2 == 11 || i2 == 12 || i2 == 13 || i2 == 14 || i2 == 15 || i2 == 16 || i2 == 17 || i2 == 18 || i2 == 19 || i2 == 20 || i2 == 21 || i2 == 22 || i2 == 23 || i2 == 24) {
                        return 6;
                    }
                    return (i2 == 25 || i2 == 26 || i2 == 27 || i2 == 28) ? 1 : 5;
                } else if (string.equals("")) {
                    return (i2 == 1 || i2 == 3) ? 7 : 1;
                }
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            View view;
            View.OnClickListener onClickListener;
            View view2;
            View.OnLongClickListener onLongClickListener;
            MaterialRippleLayout materialRippleLayout;
            View.OnClickListener onClickListener2;
            TextView textView;
            String str;
            View view3;
            View.OnClickListener onClickListener3;
            TextView textView2;
            int i2;
            AccountTextViewHolder accountTextViewHolder;
            MaterialRippleLayout materialRippleLayout2;
            int color;
            Date date;
            TextView textView3;
            String str2;
            int i3;
            StringBuilder sb;
            String str3;
            Bundle m3913d0 = m3913d0(i, accountFragment.this.f76184I3);
            if (m3913d0.getString("Type").equals("Header")) {
                HeaderCellViewHolder headerCellViewHolder = (HeaderCellViewHolder) viewHolder;
                String string = m3913d0.getString("Text");
                if (string.equals("Settings")) {
                    try {
                        i3 = accountFragment.this.f76193R3.getPackageManager().getPackageInfo(accountFragment.this.f76193R3.getPackageName(), 0).versionCode;
                    } catch (Exception unused) {
                        i3 = 0;
                    }
                    headerCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view4) {
                            accountFragment accountfragment = accountFragment.this;
                            accountfragment.f76185J3 = Boolean.valueOf(!accountfragment.f76185J3.booleanValue());
                            accountFragment.this.f76182G3.getAdapter().m42860G();
                        }
                    });
                    if (accountFragment.this.f76185J3.booleanValue()) {
                        sb = new StringBuilder();
                        sb.append(string);
                        str3 = " ( Tap to Show )";
                    } else {
                        sb = new StringBuilder();
                        sb.append(string);
                        str3 = " ( Tap to Hide )";
                    }
                    sb.append(str3);
                    string = sb.toString() + " - App Version : " + i3;
                } else if (string.equals("Help")) {
                    string = "Help";
                }
                headerCellViewHolder.f76332I.setText(string);
            }
            if (m3913d0.getString("Type").equals("Item")) {
                String string2 = m3913d0.getString("Section");
                int i4 = m3913d0.getInt("Index");
                if (string2.equals("Account Information")) {
                    AccountTextViewHolder accountTextViewHolder2 = (AccountTextViewHolder) viewHolder;
                    String m4940a = accountFragment.this.f76191P3.m4940a();
                    if (!m4940a.equals("All")) {
                        if (m4940a.equals("Simple")) {
                            if (i4 == 0) {
                                accountTextViewHolder2.f76322I.setText("Your Account Credit Is");
                                accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                materialRippleLayout2 = accountTextViewHolder2.f76323J;
                                color = Color.parseColor("#ff1cab47");
                            } else if (i4 != 1) {
                                if (i4 == 2) {
                                    accountTextViewHolder2.f76322I.setText("Buy Credit");
                                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder2.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.red_dark));
                                    materialRippleLayout = accountTextViewHolder2.f76323J;
                                    onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.2
                                        @Override // android.view.View.OnClickListener
                                        public void onClick(View view4) {
                                            CompressHelper compressHelper = accountFragment.this.f76191P3;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("http://imedicaldoctor.net/buycredit.php?user=");
                                            accountFragment accountfragment = accountFragment.this;
                                            sb2.append(accountfragment.f76192Q3.m3420m(accountfragment.f76196U3, "127"));
                                            compressHelper.m4973P(sb2.toString());
                                        }
                                    };
                                } else if (i4 == 3) {
                                    accountTextViewHolder2.f76322I.setText("Enter Credit Serial");
                                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder2.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.blue));
                                    materialRippleLayout = accountTextViewHolder2.f76323J;
                                    onClickListener2 = new View$OnClickListenerC45073();
                                } else if (i4 != 4) {
                                    return;
                                } else {
                                    accountTextViewHolder2.f76322I.setText("Upgrade to VIP Account");
                                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder2.f76323J.setBackgroundColor(Color.parseColor("#c8a900"));
                                    materialRippleLayout = accountTextViewHolder2.f76323J;
                                    onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.4
                                        @Override // android.view.View.OnClickListener
                                        public void onClick(View view4) {
                                            CompressHelper compressHelper = accountFragment.this.f76191P3;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("http://imedicaldoctor.net/extendsubscription.php?user=");
                                            accountFragment accountfragment = accountFragment.this;
                                            sb2.append(accountfragment.f76192Q3.m3420m(accountfragment.f76196U3, "127"));
                                            compressHelper.m4973P(sb2.toString());
                                        }
                                    };
                                }
                                materialRippleLayout.setOnClickListener(onClickListener2);
                                return;
                            } else {
                                accountTextViewHolder2.f76322I.setText(accountFragment.this.f76190O3 + " Toman");
                                accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                                materialRippleLayout2 = accountTextViewHolder2.f76323J;
                            }
                        } else if (m4940a.contains("Active|")) {
                            if (i4 == 0) {
                                textView3 = accountTextViewHolder2.f76322I;
                                str2 = "VIP Account - Active Till";
                            } else if (i4 != 1) {
                                if (i4 == 2) {
                                    accountTextViewHolder2.f76322I.setText("Extend Subscription");
                                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder2.f76323J.setBackgroundColor(Color.parseColor("#0a7229"));
                                    materialRippleLayout = accountTextViewHolder2.f76323J;
                                    onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.5
                                        @Override // android.view.View.OnClickListener
                                        public void onClick(View view4) {
                                            CompressHelper compressHelper = accountFragment.this.f76191P3;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("http://imedicaldoctor.net/extendsubscription.php?user=");
                                            accountFragment accountfragment = accountFragment.this;
                                            sb2.append(accountfragment.f76192Q3.m3420m(accountfragment.f76196U3, "127"));
                                            compressHelper.m4973P(sb2.toString());
                                        }
                                    };
                                    materialRippleLayout.setOnClickListener(onClickListener2);
                                    return;
                                }
                                return;
                            } else {
                                long convert = TimeUnit.DAYS.convert(Math.abs(new Date().getTime() - new Date(Long.parseLong(TextUtils.split(m4940a.replace("|", ":"), ":")[1]) * 1000).getTime()), TimeUnit.MILLISECONDS);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                accountTextViewHolder2.f76322I.setText(simpleDateFormat.format(date) + " - " + convert + " Days Remaining");
                                accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                                materialRippleLayout2 = accountTextViewHolder2.f76323J;
                            }
                        } else if (!m4940a.contains("Expired|")) {
                            return;
                        } else {
                            if (i4 == 0) {
                                accountTextViewHolder2.f76322I.setText("VIP Account - Expired At");
                                accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                materialRippleLayout2 = accountTextViewHolder2.f76323J;
                                color = accountFragment.this.m44782a0().getColor(C4804R.C4806color.red_dark);
                            } else if (i4 != 1) {
                                if (i4 == 2) {
                                    accountTextViewHolder2.f76322I.setText("Extend Subscription");
                                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder2.f76323J.setBackgroundColor(Color.parseColor("#0a7229"));
                                    materialRippleLayout = accountTextViewHolder2.f76323J;
                                    onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.6
                                        @Override // android.view.View.OnClickListener
                                        public void onClick(View view4) {
                                            CompressHelper compressHelper = accountFragment.this.f76191P3;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("http://imedicaldoctor.net/extendsubscription.php?user=");
                                            accountFragment accountfragment = accountFragment.this;
                                            sb2.append(accountfragment.f76192Q3.m3420m(accountfragment.f76196U3, "127"));
                                            compressHelper.m4973P(sb2.toString());
                                        }
                                    };
                                    materialRippleLayout.setOnClickListener(onClickListener2);
                                    return;
                                }
                                return;
                            } else {
                                accountTextViewHolder2.f76322I.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.parseLong(TextUtils.split(m4940a.replace("|", ":"), ":")[1]) * 1000)));
                                accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                                materialRippleLayout2 = accountTextViewHolder2.f76323J;
                            }
                        }
                        color = accountFragment.this.m44782a0().getColor(C4804R.C4806color.white);
                    } else if (i4 != 0) {
                        return;
                    } else {
                        textView3 = accountTextViewHolder2.f76322I;
                        str2 = "VIP Account Forever";
                    }
                    textView3.setText(str2);
                    accountTextViewHolder2.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                    materialRippleLayout2 = accountTextViewHolder2.f76323J;
                    color = Color.parseColor("#c8a900");
                } else {
                    if (string2.equals("Help")) {
                        if (i4 != 0) {
                            AccountTextViewHolder accountTextViewHolder3 = (AccountTextViewHolder) viewHolder;
                            accountTextViewHolder3.f76322I.setText("سوالات و مشکلات شایع");
                            accountTextViewHolder3.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                            accountTextViewHolder3.f76322I.setTypeface(accountFragment.this.f76198W3);
                            accountTextViewHolder3.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                            materialRippleLayout = accountTextViewHolder3.f76323J;
                            onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.8
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view4) {
                                    accountFragment.this.f76191P3.m4973P("http://imedicaldoctor.net/faq.php");
                                }
                            };
                            materialRippleLayout.setOnClickListener(onClickListener2);
                            return;
                        }
                        accountTextViewHolder = (AccountTextViewHolder) viewHolder;
                        accountTextViewHolder.f76322I.setText("راهنمای استفاده از نرم افزار");
                        accountTextViewHolder.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        accountTextViewHolder.f76322I.setTypeface(accountFragment.this.f76198W3);
                        accountTextViewHolder.f76323J.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.7
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view4) {
                                accountFragment.this.f76191P3.m4973P("http://imedicaldoctor.net/guide-android.php");
                            }
                        });
                        materialRippleLayout2 = accountTextViewHolder.f76323J;
                    } else if (!string2.equals("Your Databases")) {
                        if (string2.equals("About Us")) {
                            if (i4 == 0) {
                                ((SimpleTextViewHolder) viewHolder).f76342I.setText("iMD - Medical Resources");
                                return;
                            }
                            if (i4 == 1) {
                                SocialCellViewHolder socialCellViewHolder = (SocialCellViewHolder) viewHolder;
                                socialCellViewHolder.f76344I.setText("http://imedicaldoctor.net");
                                socialCellViewHolder.f76345J.setImageResource(C4804R.C4807drawable.f86606imd);
                                view3 = socialCellViewHolder.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.12
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        accountFragment.this.f76191P3.m4973P("http://imedicaldoctor.net");
                                    }
                                };
                            } else if (i4 == 2) {
                                SocialCellViewHolder socialCellViewHolder2 = (SocialCellViewHolder) viewHolder;
                                socialCellViewHolder2.f76344I.setText("support@imedicaldoctor.net");
                                socialCellViewHolder2.f76345J.setImageResource(C4804R.C4807drawable.f86572gmail);
                                view3 = socialCellViewHolder2.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.13
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        accountFragment.this.f76191P3.m4973P("mailto:support@imedicaldoctor.net");
                                    }
                                };
                            } else if (i4 == 3) {
                                SocialCellViewHolder socialCellViewHolder3 = (SocialCellViewHolder) viewHolder;
                                socialCellViewHolder3.f76344I.setText("Telegram Channel");
                                socialCellViewHolder3.f76345J.setImageResource(C4804R.C4807drawable.f86735td_logo);
                                view3 = socialCellViewHolder3.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.14
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        accountFragment.this.f76191P3.m4973P("http://imedicaldoctor.net/t.php");
                                    }
                                };
                            } else if (i4 == 4) {
                                SocialCellViewHolder socialCellViewHolder4 = (SocialCellViewHolder) viewHolder;
                                socialCellViewHolder4.f76344I.setText("Telegram Group");
                                socialCellViewHolder4.f76345J.setImageResource(C4804R.C4807drawable.f86735td_logo);
                                view3 = socialCellViewHolder4.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.15
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        accountFragment.this.f76191P3.m4973P("http://imedicaldoctor.net/telegramandroid.php");
                                    }
                                };
                            } else if (i4 != 5) {
                                if (i4 == 6) {
                                    AccountTextViewHolder accountTextViewHolder4 = (AccountTextViewHolder) viewHolder;
                                    accountTextViewHolder4.f76322I.setText("Log Out");
                                    accountTextViewHolder4.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder4.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.red));
                                    materialRippleLayout = accountTextViewHolder4.f76323J;
                                    onClickListener2 = new View$OnClickListenerC447717();
                                    materialRippleLayout.setOnClickListener(onClickListener2);
                                    return;
                                } else if (i4 == 7) {
                                    AccountTextViewHolder accountTextViewHolder5 = (AccountTextViewHolder) viewHolder;
                                    accountTextViewHolder5.f76322I.setText("Exit app");
                                    accountFragment.this.f76197V3 = Boolean.FALSE;
                                    accountTextViewHolder5.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                    accountTextViewHolder5.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86109darkGrey));
                                    accountTextViewHolder5.f76323J.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.18
                                        @Override // android.view.View.OnClickListener
                                        public void onClick(View view4) {
                                            if (accountFragment.this.f76197V3.booleanValue()) {
                                                return;
                                            }
                                            Process.killProcess(Process.myPid());
                                        }
                                    });
                                    view2 = accountTextViewHolder5.f76323J;
                                    onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.19
                                        @Override // android.view.View.OnLongClickListener
                                        public boolean onLongClick(View view4) {
                                            accountFragment.this.f76197V3 = Boolean.TRUE;
                                            accountFragment.this.f76191P3.m4929c2(accountFragment.this.f76191P3.m4856z1() + "/zlogs.db", "*/*");
                                            return true;
                                        }
                                    };
                                    view2.setOnLongClickListener(onLongClickListener);
                                    return;
                                } else {
                                    return;
                                }
                            } else {
                                SocialCellViewHolder socialCellViewHolder5 = (SocialCellViewHolder) viewHolder;
                                socialCellViewHolder5.f76344I.setText("@imedicaldoctor");
                                socialCellViewHolder5.f76345J.setImageResource(C4804R.C4807drawable.f86613insta);
                                view3 = socialCellViewHolder5.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.16
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        accountFragment.this.f76191P3.m4973P("https://www.instagram.com/imedicaldoctor/");
                                    }
                                };
                            }
                            view3.setOnClickListener(onClickListener3);
                            return;
                        } else if (string2.equals("Settings")) {
                            if (i4 == 0) {
                                SettingCellViewHolder settingCellViewHolder = (SettingCellViewHolder) viewHolder;
                                settingCellViewHolder.f76340I.setText("Select Download Path");
                                view3 = settingCellViewHolder.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.20
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        HashSet<String> m4922e1 = accountFragment.this.f76191P3.m4922e1();
                                        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                        Iterator<String> it2 = m4922e1.iterator();
                                        while (it2.hasNext()) {
                                            String next = it2.next();
                                            if (!next.contains("/.") && !next.contains("/sdcard/external_sd") && !next.contains("/mnt/sdcard/external_sd")) {
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Path", next);
                                                long usableSpace = new File(next).getUsableSpace();
                                                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                                                bundle.putString("Title", accountFragment.this.f76191P3.m4970Q(next));
                                                bundle.putString("Size", (decimalFormat.format((usableSpace / 1024) / 1024) + " MB") + " - " + next);
                                                arrayList.add(bundle);
                                            }
                                        }
                                        String m4861y = accountFragment.this.f76191P3.m4861y();
                                        fileSizeSettingsList filesizesettingslist = new fileSizeSettingsList();
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("type", "DownloadPath");
                                        bundle2.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList);
                                        bundle2.putString("titleProperty", "Title");
                                        bundle2.putString("selected", m4861y);
                                        filesizesettingslist.m44751k2(bundle2);
                                        filesizesettingslist.m44870c3(true);
                                        filesizesettingslist.m44844E2(accountFragment.this, 0);
                                        filesizesettingslist.mo29915h3(accountFragment.this.m44820L(), "SettingListDownloadPath");
                                    }
                                };
                            } else if (i4 == 1) {
                                SettingCellViewHolder settingCellViewHolder2 = (SettingCellViewHolder) viewHolder;
                                settingCellViewHolder2.f76340I.setText("Select Landing Page");
                                view3 = settingCellViewHolder2.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.21
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                        String[] strArr = {"Titles", "Databases", "Favorites", "Content", "Store", "Account"};
                                        for (int i5 = 0; i5 < 6; i5++) {
                                            arrayList.add(accountFragment.this.m3924k3(strArr[i5]));
                                        }
                                        String string3 = PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getString("Tab", "");
                                        if (string3.length() == 0) {
                                            string3 = strArr[1];
                                        }
                                        settingsList settingslist = new settingsList();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("type", "Tab");
                                        bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList);
                                        bundle.putString("titleProperty", "Title");
                                        bundle.putString("selected", string3);
                                        settingslist.m44751k2(bundle);
                                        settingslist.m44870c3(true);
                                        settingslist.m44844E2(accountFragment.this, 0);
                                        settingslist.mo29915h3(accountFragment.this.m44820L(), "SettingListTab");
                                    }
                                };
                            } else if (i4 == 2) {
                                SettingCellViewHolder settingCellViewHolder3 = (SettingCellViewHolder) viewHolder;
                                settingCellViewHolder3.f76340I.setText("Main Server");
                                view3 = settingCellViewHolder3.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.22
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                        String[] strArr = {"Germany", "Iran"};
                                        for (int i5 = 0; i5 < 2; i5++) {
                                            arrayList.add(accountFragment.this.m3924k3(strArr[i5]));
                                        }
                                        String string3 = PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getString("MainServer", "Iran");
                                        settingsList settingslist = new settingsList();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("type", "MainDL");
                                        bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList);
                                        bundle.putString("titleProperty", "Title");
                                        bundle.putString("selected", string3);
                                        settingslist.m44751k2(bundle);
                                        settingslist.m44870c3(true);
                                        settingslist.m44844E2(accountFragment.this, 0);
                                        settingslist.mo29915h3(accountFragment.this.m44820L(), "SettingListTab");
                                    }
                                };
                            } else if (i4 != 3) {
                                if (i4 != 25) {
                                    if (i4 == 26) {
                                        AccountTextViewHolder accountTextViewHolder6 = (AccountTextViewHolder) viewHolder;
                                        accountTextViewHolder6.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                        accountTextViewHolder6.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.green));
                                        if (accountFragment.this.f76186K3.length() == 0) {
                                            textView = accountTextViewHolder6.f76322I;
                                            str = "Backup Favorites & Highlights";
                                        } else {
                                            textView = accountTextViewHolder6.f76322I;
                                            str = accountFragment.this.f76186K3;
                                        }
                                        textView.setText(str);
                                        accountTextViewHolder6.f76323J.setOnClickListener(new View$OnClickListenerC449025());
                                        view2 = accountTextViewHolder6.f76323J;
                                        onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.26
                                            @Override // android.view.View.OnLongClickListener
                                            public boolean onLongClick(View view4) {
                                                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("This will delete all of your favorites. are you sure ?").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.26.2
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                        CompressHelper compressHelper = accountFragment.this.f76191P3;
                                                        compressHelper.m4885q(compressHelper.m4972P0(), "delete from favorites");
                                                        accountFragment.this.m3920o3();
                                                    }
                                                }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.26.1
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                    }
                                                }).m52864I();
                                                return true;
                                            }
                                        };
                                    } else if (i4 == 27) {
                                        AccountTextViewHolder accountTextViewHolder7 = (AccountTextViewHolder) viewHolder;
                                        accountTextViewHolder7.f76322I.setText("Restore Favorites & Highlights");
                                        accountTextViewHolder7.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                        accountTextViewHolder7.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86110dark_blue));
                                        accountTextViewHolder7.f76323J.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.27
                                            @Override // android.view.View.OnClickListener
                                            public void onClick(View view4) {
                                                final EditText editText = new EditText(accountFragment.this.m44716w());
                                                editText.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                                                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Enter Backup Identifier.").setView(editText).mo26266y("Replace", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.27.3
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                        accountFragment.this.m3943R2(editText.getText().toString(), true);
                                                    }
                                                }).mo26284p("Merge", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.27.2
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                        accountFragment.this.m3943R2(editText.getText().toString(), false);
                                                    }
                                                }).mo26278s("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.27.1
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                    }
                                                }).m52864I();
                                            }
                                        });
                                        view2 = accountTextViewHolder7.f76323J;
                                        onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.28
                                            @Override // android.view.View.OnLongClickListener
                                            public boolean onLongClick(View view4) {
                                                new AlertDialog.Builder(accountFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("This will delete all of your highlights and notes. are you sure ?").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.28.2
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                        accountFragment accountfragment = accountFragment.this;
                                                        accountfragment.f76191P3.m4885q(accountfragment.m3921n3(), "delete from highlights");
                                                    }
                                                }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.28.1
                                                    @Override // android.content.DialogInterface.OnClickListener
                                                    public void onClick(DialogInterface dialogInterface, int i5) {
                                                    }
                                                }).m52864I();
                                                return true;
                                            }
                                        };
                                    } else if (i4 != 28) {
                                        if (i4 == 4) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder.f76337I.setText("Fullscreen Mode");
                                            settingCellSwitchViewHolder.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("Fullscreen", true));
                                            view = settingCellSwitchViewHolder.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.30
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("Fullscreen", settingCellSwitchViewHolder.f76338J.isChecked()).commit();
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "You must restart your app for this change to take effect", 0);
                                                }
                                            };
                                        } else if (i4 == 5) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder2 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder2.f76337I.setText("Hide List On Select");
                                            settingCellSwitchViewHolder2.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("HideList", true));
                                            view = settingCellSwitchViewHolder2.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.31
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("HideList", settingCellSwitchViewHolder2.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 6) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder3 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder3.f76337I.setText("Hide Status Bar");
                                            settingCellSwitchViewHolder3.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("HideStatusBar", false));
                                            view = settingCellSwitchViewHolder3.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.32
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("HideStatusBar", settingCellSwitchViewHolder3.f76338J.isChecked()).commit();
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "You must restart your app for this change to take effect", 0);
                                                }
                                            };
                                        } else if (i4 == 7) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder4 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder4.f76337I.setText("Dynamic Ripple Colors");
                                            settingCellSwitchViewHolder4.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("ripple", true));
                                            view = settingCellSwitchViewHolder4.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.33
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("ripple", settingCellSwitchViewHolder4.f76338J.isChecked()).commit();
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "You must restart your app for this change to take effect", 0);
                                                }
                                            };
                                        } else if (i4 == 8) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder5 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder5.f76337I.setText("Collapse Search Results");
                                            settingCellSwitchViewHolder5.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("SearchCollapsed", false));
                                            view = settingCellSwitchViewHolder5.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.34
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("SearchCollapsed", settingCellSwitchViewHolder5.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 9) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder6 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder6.f76337I.setText("Collapse Content Results");
                                            settingCellSwitchViewHolder6.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("ContentCollapsed", false));
                                            view = settingCellSwitchViewHolder6.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.35
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("ContentCollapsed", settingCellSwitchViewHolder6.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 10) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder7 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder7.f76337I.setText("Lock in Fullscreen");
                                            settingCellSwitchViewHolder7.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("lockfull", true));
                                            view = settingCellSwitchViewHolder7.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.36
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("lockfull", settingCellSwitchViewHolder7.f76338J.isChecked()).commit();
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "You must restart your app for this change to take effect", 0);
                                                }
                                            };
                                        } else if (i4 == 11) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder8 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder8.f76337I.setText("Use less space for install");
                                            settingCellSwitchViewHolder8.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("lessspace", false));
                                            view = settingCellSwitchViewHolder8.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.37
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("lessspace", settingCellSwitchViewHolder8.f76338J.isChecked()).commit();
                                                    if (settingCellSwitchViewHolder8.f76338J.isChecked()) {
                                                        CompressHelper.m4921e2(accountFragment.this.m44716w(), "This may cause problems on install process", 0);
                                                    }
                                                }
                                            };
                                        } else if (i4 == 12) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder9 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder9.f76337I.setText("Enable Swipe to Delete");
                                            settingCellSwitchViewHolder9.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("swipedelete", true));
                                            view = settingCellSwitchViewHolder9.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.38
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("swipedelete", settingCellSwitchViewHolder9.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 13) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder10 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder10.f76337I.setText("Use Delta Update");
                                            settingCellSwitchViewHolder10.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("delta", false));
                                            view = settingCellSwitchViewHolder10.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.39
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("delta", settingCellSwitchViewHolder10.f76338J.isChecked()).commit();
                                                    if (settingCellSwitchViewHolder10.f76338J.isChecked()) {
                                                        CompressHelper.m4921e2(accountFragment.this.m44716w(), "On some devices delta update can corrupt the main database and you must download the whole database again", 1);
                                                    } else {
                                                        LocalBroadcastManager.m43863b(accountFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
                                                    }
                                                }
                                            };
                                        } else if (i4 == 14) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder11 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder11.f76337I.setText("Use Collapsing Toolbar");
                                            settingCellSwitchViewHolder11.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("NestedScroll", true));
                                            view = settingCellSwitchViewHolder11.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.40
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("NestedScroll", settingCellSwitchViewHolder11.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 15) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder12 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder12.f76337I.setText("Show Popup in Access Medicine Tables");
                                            settingCellSwitchViewHolder12.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("amtable", true));
                                            view = settingCellSwitchViewHolder12.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.41
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("amtable", settingCellSwitchViewHolder12.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 16) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder13 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder13.f76337I.setText("Use Last Red Highlight as Starting Point");
                                            settingCellSwitchViewHolder13.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("lastred", false));
                                            view = settingCellSwitchViewHolder13.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.42
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("lastred", settingCellSwitchViewHolder13.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 17) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder14 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder14.f76337I.setText("Use Default System Font");
                                            settingCellSwitchViewHolder14.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("defaultfont", false));
                                            view = settingCellSwitchViewHolder14.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.43
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("defaultfont", settingCellSwitchViewHolder14.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 18) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder15 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder15.f76337I.setText("Load Download List Automatically");
                                            settingCellSwitchViewHolder15.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("loaddownload", false));
                                            settingCellSwitchViewHolder15.f76338J.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.44
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("loaddownload", settingCellSwitchViewHolder15.f76338J.isChecked()).commit();
                                                }
                                            });
                                            view2 = settingCellSwitchViewHolder15.f76337I;
                                            onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.45
                                                @Override // android.view.View.OnLongClickListener
                                                public boolean onLongClick(View view4) {
                                                    FragmentActivity m44716w;
                                                    String str4;
                                                    boolean z = !PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("showfreeversion", false);
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("showfreeversion", z).commit();
                                                    if (z) {
                                                        m44716w = accountFragment.this.m44716w();
                                                        str4 = "Showing Last Version : True";
                                                    } else {
                                                        m44716w = accountFragment.this.m44716w();
                                                        str4 = "Showing Last Version : False";
                                                    }
                                                    Toast.makeText(m44716w, str4, 1).show();
                                                    LocalBroadcastManager.m43863b(accountFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
                                                    return true;
                                                }
                                            };
                                        } else if (i4 == 19) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder16 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder16.f76337I.setText("Justify Texts");
                                            settingCellSwitchViewHolder16.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("justify", true));
                                            view = settingCellSwitchViewHolder16.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.46
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("justify", settingCellSwitchViewHolder16.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 20) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder17 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder17.f76337I.setText("Open Last Topic after crash");
                                            settingCellSwitchViewHolder17.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("openaftercrash", true));
                                            view = settingCellSwitchViewHolder17.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.47
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("openaftercrash", settingCellSwitchViewHolder17.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 21) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder18 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder18.f76337I.setText("Save Logs");
                                            settingCellSwitchViewHolder18.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("savelogs", false));
                                            view = settingCellSwitchViewHolder18.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.48
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("savelogs", settingCellSwitchViewHolder18.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 22) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder19 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder19.f76337I.setText("Enable Wake Lock");
                                            settingCellSwitchViewHolder19.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("wakelock", true));
                                            view = settingCellSwitchViewHolder19.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.49
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("wakelock", settingCellSwitchViewHolder19.f76338J.isChecked()).commit();
                                                }
                                            };
                                        } else if (i4 == 23) {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder20 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder20.f76337I.setText("Dark Theme");
                                            settingCellSwitchViewHolder20.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("dark", false));
                                            view = settingCellSwitchViewHolder20.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.50
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("dark", settingCellSwitchViewHolder20.f76338J.isChecked()).commit();
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("dark", false);
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "closing app for changes to take effect", 2);
                                                    accountFragment.this.f76181F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.50.1
                                                        @Override // java.lang.Runnable
                                                        public void run() {
                                                            Process.killProcess(Process.myPid());
                                                        }
                                                    }, SimpleExoPlayer.f32068s1);
                                                }
                                            };
                                        } else if (i4 != 24) {
                                            return;
                                        } else {
                                            final SettingCellSwitchViewHolder settingCellSwitchViewHolder21 = (SettingCellSwitchViewHolder) viewHolder;
                                            settingCellSwitchViewHolder21.f76337I.setText("Force Mobile Mode");
                                            settingCellSwitchViewHolder21.f76338J.setChecked(PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("mobile", false));
                                            view = settingCellSwitchViewHolder21.f76338J;
                                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.51
                                                @Override // android.view.View.OnClickListener
                                                public void onClick(View view4) {
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).edit().putBoolean("mobile", settingCellSwitchViewHolder21.f76338J.isChecked()).commit();
                                                    PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getBoolean("mobile", false);
                                                    CompressHelper.m4921e2(accountFragment.this.m44716w(), "closing app for changes to take effect", 2);
                                                    accountFragment.this.f76181F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.51.1
                                                        @Override // java.lang.Runnable
                                                        public void run() {
                                                            Process.killProcess(Process.myPid());
                                                        }
                                                    }, SimpleExoPlayer.f32068s1);
                                                }
                                            };
                                        }
                                        view.setOnClickListener(onClickListener);
                                        return;
                                    } else {
                                        AccountTextViewHolder accountTextViewHolder8 = (AccountTextViewHolder) viewHolder;
                                        accountTextViewHolder8.f76322I.setText("Delete Temp Files");
                                        accountTextViewHolder8.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                        accountTextViewHolder8.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.red));
                                        materialRippleLayout = accountTextViewHolder8.f76323J;
                                        onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.29
                                            @Override // android.view.View.OnClickListener
                                            public void onClick(View view4) {
                                                Iterator<String> it2 = accountFragment.this.f76191P3.m4922e1().iterator();
                                                while (it2.hasNext()) {
                                                    String next = it2.next();
                                                    String[] list = new File(next).list(new FilenameFilter() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.29.1
                                                        @Override // java.io.FilenameFilter
                                                        public boolean accept(File file, String str4) {
                                                            return str4.endsWith(".zip") || str4.endsWith(".zipp") || str4.endsWith(".download") || str4.endsWith(".md5") || str4.endsWith(".1") || str4.endsWith(".2") || str4.endsWith(".3") || str4.endsWith(".4") || str4.endsWith(".5") || str4.endsWith(".6") || str4.endsWith(".7") || str4.endsWith(".8") || str4.endsWith(".9") || str4.endsWith(".10");
                                                        }
                                                    });
                                                    if (list != null && list.length != 0) {
                                                        for (String str4 : list) {
                                                            new File(next + "/" + str4).delete();
                                                        }
                                                    }
                                                }
                                                new File(accountFragment.this.f76191P3.m4856z1() + "/zlogs.db").delete();
                                                CompressHelper.m4921e2(accountFragment.this.f76193R3, "All temp files deleted.", 1);
                                                new File(accountFragment.this.f76191P3.m4863x0()).delete();
                                            }
                                        };
                                    }
                                    view2.setOnLongClickListener(onLongClickListener);
                                    return;
                                }
                                AccountTextViewHolder accountTextViewHolder9 = (AccountTextViewHolder) viewHolder;
                                accountTextViewHolder9.f76322I.setText("Check App Update");
                                accountTextViewHolder9.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.white));
                                accountTextViewHolder9.f76323J.setBackgroundColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.f86109darkGrey));
                                materialRippleLayout = accountTextViewHolder9.f76323J;
                                onClickListener2 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.24
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        if (accountFragment.this.f76193R3 == null) {
                                            return;
                                        }
                                        ((mainActivity) accountFragment.this.f76193R3).m3584z0(true);
                                    }
                                };
                                materialRippleLayout.setOnClickListener(onClickListener2);
                                return;
                            } else {
                                SettingCellViewHolder settingCellViewHolder4 = (SettingCellViewHolder) viewHolder;
                                settingCellViewHolder4.f76340I.setText("Download Server");
                                view3 = settingCellViewHolder4.f18491a;
                                onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.23
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view4) {
                                        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                        String[] strArr = {"Germany", "Iran"};
                                        for (int i5 = 0; i5 < 2; i5++) {
                                            arrayList.add(accountFragment.this.m3924k3(strArr[i5]));
                                        }
                                        String str4 = PreferenceManager.getDefaultSharedPreferences(accountFragment.this.m44716w()).getString("DownloadServer", HTML.Tag.f65923m0).equals(HTML.Tag.f65923m0) ? "Germany" : "Iran";
                                        settingsList settingslist = new settingsList();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("type", "DL");
                                        bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList);
                                        bundle.putString("titleProperty", "Title");
                                        bundle.putString("selected", str4);
                                        settingslist.m44751k2(bundle);
                                        settingslist.m44870c3(true);
                                        settingslist.m44844E2(accountFragment.this, 0);
                                        settingslist.mo29915h3(accountFragment.this.m44820L(), "SettingListTab");
                                    }
                                };
                            }
                            view3.setOnClickListener(onClickListener3);
                            return;
                        } else {
                            return;
                        }
                    } else if (!accountFragment.this.f76188M3.contains(TtmlNode.f38128r0)) {
                        DatabaseButtonCellViewHolder databaseButtonCellViewHolder = (DatabaseButtonCellViewHolder) viewHolder;
                        final Bundle bundle = (Bundle) accountFragment.this.f76187L3.get(i4);
                        databaseButtonCellViewHolder.f76325I.setText(bundle.getString("Title"));
                        m3912e0(databaseButtonCellViewHolder.f76327K, bundle.getString("IconName"));
                        databaseButtonCellViewHolder.f76326J.setVisibility(8);
                        if (accountFragment.this.f76189N3.containsKey(bundle.getString("name"))) {
                            databaseButtonCellViewHolder.f76326J.setVisibility(0);
                            databaseButtonCellViewHolder.f76326J.setText("Valid until " + CompressHelper.m4957U0(accountFragment.this.f76189N3.getBundle(bundle.getString("name")).getString(DublinCoreProperties.f65371d)));
                            if (accountFragment.this.f76189N3.getBundle(bundle.getString("name")).getInt("expired") == 1) {
                                databaseButtonCellViewHolder.f76326J.setText("Subscription Ended on " + CompressHelper.m4957U0(accountFragment.this.f76189N3.getBundle(bundle.getString("name")).getString(DublinCoreProperties.f65371d)));
                                textView2 = databaseButtonCellViewHolder.f76326J;
                                i2 = SupportMenu.f12537c;
                            } else {
                                textView2 = databaseButtonCellViewHolder.f76326J;
                                i2 = -12303292;
                            }
                            textView2.setTextColor(i2);
                        } else {
                            databaseButtonCellViewHolder.f76326J.setVisibility(8);
                        }
                        final Bundle m4969Q0 = accountFragment.this.f76191P3.m4969Q0("Name", bundle.getString("name"));
                        if (m4969Q0 == null) {
                            databaseButtonCellViewHolder.f76328L.setText("Download");
                            view = databaseButtonCellViewHolder.f76328L;
                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.11
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view4) {
                                    ((mainActivity) accountFragment.this.m44716w()).f76898Q2.setCurrentItem(4);
                                    accountFragment.this.f76181F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.11.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            downloadFragment downloadfragment = ((iMD) accountFragment.this.m44716w().getApplicationContext()).f83455D2;
                                            if (downloadfragment.m3742N3()) {
                                                downloadfragment.m3737Q2();
                                            }
                                            downloadfragment.f76673S3.check(C4804R.C4808id.f86966newest);
                                            downloadfragment.f76668N3.m51655i0(bundle.getString("Title"), true);
                                        }
                                    }, 1000L);
                                }
                            };
                        } else if (m4969Q0.getString("Version").compareTo(bundle.getString("Version")) >= 0) {
                            databaseButtonCellViewHolder.f76328L.setText("Open");
                            view3 = databaseButtonCellViewHolder.f76328L;
                            onClickListener3 = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.9
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view4) {
                                    ((mainActivity) accountFragment.this.m44716w()).f76898Q2.setCurrentItem(1);
                                    accountFragment.this.f76181F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.9.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            View$OnClickListenerC45419 view$OnClickListenerC45419 = View$OnClickListenerC45419.this;
                                            accountFragment.this.f76191P3.m4886p1(m4969Q0);
                                        }
                                    }, 1000L);
                                }
                            };
                            view3.setOnClickListener(onClickListener3);
                            return;
                        } else {
                            databaseButtonCellViewHolder.f76328L.setText("Update");
                            view = databaseButtonCellViewHolder.f76328L;
                            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.10
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view4) {
                                    ((mainActivity) accountFragment.this.m44716w()).f76898Q2.setCurrentItem(4);
                                    accountFragment.this.f76181F3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.AccountAdapter.10.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            downloadFragment downloadfragment = ((iMD) accountFragment.this.m44716w().getApplicationContext()).f83455D2;
                                            downloadfragment.f76673S3.check(C4804R.C4808id.f86966newest);
                                            downloadfragment.f76668N3.m51655i0(bundle.getString("Title"), true);
                                        }
                                    }, 1000L);
                                }
                            };
                        }
                        view.setOnClickListener(onClickListener);
                        return;
                    } else {
                        accountTextViewHolder = (AccountTextViewHolder) viewHolder;
                        accountTextViewHolder.f76322I.setText("You Are Subscribed To All Databases");
                        accountTextViewHolder.f76322I.setTextColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.grey));
                        materialRippleLayout2 = accountTextViewHolder.f76323J;
                    }
                    color = accountFragment.this.m44782a0().getColor(C4804R.C4806color.white);
                }
                materialRippleLayout2.setBackgroundColor(color);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                    return new HeaderCellViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87230list_view_item_database_header, viewGroup, false));
                case 1:
                    AccountTextViewHolder accountTextViewHolder = new AccountTextViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87218list_view_item_account_text, viewGroup, false));
                    accountTextViewHolder.f76323J.setRippleColor(accountFragment.this.m44782a0().getColor(C4804R.C4806color.material_grey_300));
                    return accountTextViewHolder;
                case 2:
                    return new DatabaseButtonCellViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87227list_view_item_database_button, viewGroup, false));
                case 3:
                    return new SimpleTextViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87284list_view_item_simple_text, viewGroup, false));
                case 4:
                    return new SocialCellViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87292list_view_item_social, viewGroup, false));
                case 5:
                    return new SettingCellViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87281list_view_item_setting_text, viewGroup, false));
                case 6:
                    return new SettingCellSwitchViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87283list_view_item_setting_text_switch, viewGroup, false));
                case 7:
                    return new SeparatorViewHolder(LayoutInflater.from(accountFragment.this.m44716w()).inflate(C4804R.C4810layout.f87280list_view_item_separator, viewGroup, false));
                default:
                    return null;
            }
        }

        /* renamed from: d0 */
        public Bundle m3913d0(int i, ArrayList<String> arrayList) {
            Iterator<String> it2 = arrayList.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                String next = it2.next();
                if (i == i2) {
                    Bundle bundle = new Bundle();
                    if (next.endsWith("Information")) {
                        next = next + " - " + accountFragment.this.f76196U3;
                    }
                    bundle.putString("Text", next);
                    bundle.putString("Type", "Header");
                    return bundle;
                }
                int m3911f0 = i2 + m3911f0(next);
                if (i <= m3911f0) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("Section", next);
                    bundle2.putInt("Index", (i - (m3911f0 - m3911f0(next))) - 1);
                    bundle2.putString("Type", "Item");
                    return bundle2;
                }
                i2 = m3911f0 + 1;
            }
            return null;
        }

        /* renamed from: e0 */
        public void m3912e0(ImageView imageView, String str) {
            RequestBuilder<Drawable> mo40145t;
            ArrayList arrayList = new ArrayList();
            arrayList.add("visualdx.png");
            arrayList.add("uptodate.png");
            arrayList.add("irandarou.png");
            if (arrayList.contains(str)) {
                RequestManager m40316F = Glide.m40316F(accountFragment.this);
                mo40145t = m40316F.mo40152g(Uri.parse("file:///android_asset/" + str));
            } else {
                RequestManager m40316F2 = Glide.m40316F(accountFragment.this);
                mo40145t = m40316F2.mo40145t(accountFragment.this.f76191P3.m4991J() + "/Icons/" + str);
            }
            mo40145t.m40191t2(imageView);
        }

        /* renamed from: f0 */
        public int m3911f0(String str) {
            if (str.equals("Account Information")) {
                String m4940a = accountFragment.this.f76191P3.m4940a();
                if (m4940a.equals("All")) {
                    return 1;
                }
                return m4940a.equals("Simple") ? 5 : 3;
            } else if (str.equals("Help")) {
                return 2;
            } else {
                if (!str.equals("Your Databases")) {
                    if (str.equals("About Us")) {
                        return 8;
                    }
                    return str.equals("Settings") ? accountFragment.this.f76185J3.booleanValue() ? 0 : 29 : str.equals("") ? 5 : 0;
                } else if (accountFragment.this.f76188M3 == null) {
                    return 0;
                } else {
                    if (accountFragment.this.f76188M3.contains(TtmlNode.f38128r0)) {
                        return 1;
                    }
                    if (accountFragment.this.f76187L3 == null) {
                        return 0;
                    }
                    return accountFragment.this.f76187L3.size();
                }
            }
        }

        /* renamed from: g0 */
        public void m3910g0(Intent intent) {
        }

        /* renamed from: h0 */
        public int m3909h0(ArrayList<String> arrayList) {
            int i = 0;
            if (arrayList == null) {
                return 0;
            }
            Iterator<String> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                i = i + m3911f0(it2.next()) + 1;
            }
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return m3909h0(accountFragment.this.f76184I3);
        }
    }

    /* loaded from: classes2.dex */
    public class AccountTextViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76322I;

        /* renamed from: J */
        private MaterialRippleLayout f76323J;

        public AccountTextViewHolder(View view) {
            super(view);
            this.f76322I = (TextView) view.findViewById(C4804R.C4808id.text);
            this.f76323J = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
        }
    }

    /* loaded from: classes2.dex */
    public static class DatabaseButtonCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76325I;

        /* renamed from: J */
        public TextView f76326J;

        /* renamed from: K */
        public ImageView f76327K;

        /* renamed from: L */
        public Button f76328L;

        public DatabaseButtonCellViewHolder(View view) {
            super(view);
            this.f76325I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76326J = (TextView) view.findViewById(C4804R.C4808id.f86856database_subtitle);
            this.f76327K = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76328L = (Button) view.findViewById(C4804R.C4808id.f86824button);
        }
    }

    /* loaded from: classes2.dex */
    public static class DatabaseCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76329I;

        /* renamed from: J */
        public TextView f76330J;

        /* renamed from: K */
        public ImageView f76331K;

        public DatabaseCellViewHolder(View view) {
            super(view);
            this.f76329I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76330J = (TextView) view.findViewById(C4804R.C4808id.f86856database_subtitle);
            this.f76331K = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
        }
    }

    /* loaded from: classes2.dex */
    public static class HeaderCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76332I;

        public HeaderCellViewHolder(View view) {
            super(view);
            this.f76332I = (TextView) view.findViewById(C4804R.C4808id.f86913header_text);
        }
    }

    /* loaded from: classes2.dex */
    public class SeparatorViewHolder extends RecyclerView.ViewHolder {
        public SeparatorViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes2.dex */
    public class SettingCellDetailViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76334I;

        /* renamed from: J */
        private TextView f76335J;

        public SettingCellDetailViewHolder(View view) {
            super(view);
            this.f76334I = (TextView) view.findViewById(C4804R.C4808id.text);
            this.f76335J = (TextView) view.findViewById(C4804R.C4808id.f86866detail_text);
        }
    }

    /* loaded from: classes2.dex */
    public class SettingCellSwitchViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76337I;

        /* renamed from: J */
        private SwitchCompat f76338J;

        public SettingCellSwitchViewHolder(View view) {
            super(view);
            this.f76337I = (TextView) view.findViewById(C4804R.C4808id.text);
            this.f76338J = (SwitchCompat) view.findViewById(C4804R.C4808id.f87040switch_view);
        }
    }

    /* loaded from: classes2.dex */
    public class SettingCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76340I;

        public SettingCellViewHolder(View view) {
            super(view);
            this.f76340I = (TextView) view.findViewById(C4804R.C4808id.text);
        }
    }

    /* loaded from: classes2.dex */
    public class SimpleTextViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76342I;

        public SimpleTextViewHolder(View view) {
            super(view);
            this.f76342I = (TextView) view.findViewById(C4804R.C4808id.text);
        }
    }

    /* loaded from: classes2.dex */
    public static class SocialCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76344I;

        /* renamed from: J */
        public ImageView f76345J;

        public SocialCellViewHolder(View view) {
            super(view);
            this.f76344I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76345J = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P2 */
    public void m3945P2() {
        int i = 0;
        try {
            i = m44716w().getPackageManager().getPackageInfo(m44716w().getPackageName(), 0).versionCode;
        } catch (Exception unused) {
        }
        CompressHelper compressHelper = this.f76191P3;
        compressHelper.m4890o0("ActivationCode|||||" + this.f76192Q3.m3421l() + "|||||android-" + i).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                if (splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                    accountFragment.this.f76191P3.m4933b2(splitByWholeSeparator[1]);
                    accountFragment.this.m3944Q2();
                    accountFragment.this.m3947N2();
                    accountFragment.this.f76183H3.setRefreshing(false);
                } else if (splitByWholeSeparator[0].equals("0")) {
                    accountFragment.this.f76191P3.m4933b2(null);
                    iMDLogger.m3294f("system finish", "CheckActivationCode : " + str);
                    accountFragment.this.m44716w().finish();
                    System.exit(0);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    th.printStackTrace();
                    accountFragment.this.f76183H3.setRefreshing(false);
                } catch (Exception unused2) {
                }
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.4
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q2 */
    public void m3944Q2() {
        try {
            VBHelper vBHelper = this.f76192Q3;
            String[] split = TextUtils.split(vBHelper.m3410w(vBHelper.m3421l()).replace("||", "::"), "::");
            String[] split2 = TextUtils.split(split[3], ",");
            this.f76190O3 = split[5];
            this.f76188M3 = new ArrayList<>(Arrays.asList(split2));
            ArrayList<String> arrayList = new ArrayList<>();
            this.f76189N3 = new Bundle();
            Iterator<String> it2 = this.f76188M3.iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                if (next.contains("$$$")) {
                    int i = 0;
                    String str = StringUtils.splitByWholeSeparator(next, "$$$")[0];
                    if (str.contains("-expired")) {
                        str = str.replace("-expired", "");
                        i = 1;
                    }
                    arrayList.add(str);
                    Bundle bundle = new Bundle();
                    bundle.putString(DublinCoreProperties.f65371d, StringUtils.splitByWholeSeparator(next, "$$$")[1]);
                    bundle.putInt("expired", i);
                    this.f76189N3.putBundle(str, bundle);
                } else {
                    arrayList.add(next);
                }
            }
            this.f76188M3 = arrayList;
            if (split.length > 9) {
                this.f76196U3 = split[9];
            } else {
                this.f76196U3 = "";
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            this.f76190O3 = "0";
            this.f76188M3 = new ArrayList<>();
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: M0 */
    public void mo3640M0(Activity activity) {
        super.mo3640M0(activity);
        this.f76193R3 = activity;
    }

    /* renamed from: N2 */
    public void m3947N2() {
        m3946O2();
    }

    /* renamed from: O2 */
    public void m3946O2() {
        String str = this.f76191P3.m5004E1() + "/DBs.db";
        if (new File(str).exists()) {
            try {
                this.f76187L3 = this.f76191P3.m4946Y(str, "select id,Title,name,max(Version) as Version, IconName,folderSize, url, fileSize, md5,price from Dbs where name in ('" + TextUtils.join("','", this.f76188M3) + "') group by name order by title asc");
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("LoadDBS", "Error in querying db. let's delete that");
                new File(str).delete();
            }
            iMDLogger.m3294f("LoadDBs", "Load DBS Completed");
            this.f76182G3.getAdapter().m42860G();
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: Q0 */
    public void mo3503Q0(Bundle bundle) {
        super.mo3503Q0(bundle);
        LocalBroadcastManager.m43863b(m44716w()).m43862c(this.f76199X3, new IntentFilter("referesh.account"));
        LocalBroadcastManager.m43863b(m44716w()).m43862c(this.f76200Y3, new IntentFilter("referesh.account.visible"));
        LocalBroadcastManager.m43863b(m44716w()).m43862c(this.f76201Z3, new IntentFilter("reloadaccountdownloads"));
    }

    /* renamed from: R2 */
    public void m3943R2(String str, final boolean z) {
        if (str.length() == 0) {
            this.f76191P3.m4925d2("You must enter a backup identifier");
            return;
        }
        final ProgressDialog show = ProgressDialog.show(m44716w(), "Restoring", "Please wait...", true);
        this.f76191P3.m4890o0("LoadFromFile|||||" + str).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.8
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str2) throws Throwable {
                if (str2.length() == 0) {
                    show.dismiss();
                    accountFragment.this.f76191P3.m4925d2("Identifier not found");
                    return;
                }
                String[] splitByWholeSeparatorPreserveAllTokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(str2, "###");
                if (z) {
                    CompressHelper compressHelper = accountFragment.this.f76191P3;
                    compressHelper.m4885q(compressHelper.m4972P0(), "Delete from favorites");
                    accountFragment accountfragment = accountFragment.this;
                    accountfragment.f76191P3.m4885q(accountfragment.m3921n3(), "Delete from highlight");
                }
                CompressHelper compressHelper2 = accountFragment.this.f76191P3;
                compressHelper2.m4896m0(splitByWholeSeparatorPreserveAllTokens[0], compressHelper2.m4972P0(), "favorites", "dbName,dbTitle,dbAddress,dbDate,dbDocName", null);
                accountFragment accountfragment2 = accountFragment.this;
                accountfragment2.f76191P3.m4896m0(splitByWholeSeparatorPreserveAllTokens[1], accountfragment2.m3921n3(), "highlight", "dbName,dbTitle,dbAddress,dbDate,dbDocName,type,text,note,save", null);
                show.dismiss();
                accountFragment.this.f76191P3.m4925d2("Restore successful");
                accountFragment.this.m3920o3();
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.9
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                th.printStackTrace();
                FirebaseCrashlytics.m18030d().m18027g(th);
                show.dismiss();
                accountFragment.this.f76191P3.m4925d2("Error in contacting server");
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: T0 */
    public void mo3545T0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(C4804R.C4811menu.f87327menu_account, menu);
        try {
            m44716w().setTitle("Account & Settings");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        super.mo3545T0(menu, menuInflater);
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.f76181F3;
        if (view != null) {
            return view;
        }
        this.f76185J3 = Boolean.TRUE;
        this.f76186K3 = "";
        this.f76198W3 = Typeface.createFromAsset(m44716w().getAssets(), "fonts/IRANSans(FaNum).ttf");
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87133fragment_account, viewGroup, false);
        this.f76191P3 = new CompressHelper(m44716w());
        this.f76192Q3 = new VBHelper(m44716w());
        this.f76181F3 = inflate;
        m3922m3();
        RecyclerView recyclerView = (RecyclerView) this.f76181F3.findViewById(C4804R.C4808id.f87001recycler_view);
        this.f76182G3 = recyclerView;
        recyclerView.m42923n(new DividerItemDecoration(m44716w(), 1));
        this.f76182G3.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
        this.f76182G3.setAdapter(new AccountAdapter());
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.f76181F3.findViewById(C4804R.C4808id.f87039swipeRefreshLayout);
        this.f76183H3 = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: net.imedicaldoctor.imd.Fragments.accountFragment.1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            /* renamed from: a */
            public void mo3918a() {
                accountFragment.this.m3945P2();
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        this.f76184I3 = arrayList;
        arrayList.add("Account Information");
        this.f76184I3.add("Your Databases");
        this.f76184I3.add("Settings");
        this.f76184I3.add("About Us");
        ButtonFloatHelp buttonFloatHelp = (ButtonFloatHelp) this.f76181F3.findViewById(C4804R.C4808id.f86914help);
        this.f76194S3 = buttonFloatHelp;
        buttonFloatHelp.setVisibility(8);
        ButtonFloatHelpBadge buttonFloatHelpBadge = (ButtonFloatHelpBadge) this.f76181F3.findViewById(C4804R.C4808id.f86915helpBadge);
        this.f76195T3 = buttonFloatHelpBadge;
        buttonFloatHelpBadge.setVisibility(8);
        this.f76187L3 = new ArrayList<>();
        m3944Q2();
        m3947N2();
        m44735q2(true);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: V0 */
    public void mo3638V0() {
        LocalBroadcastManager.m43863b(m44716w()).m43859f(this.f76199X3);
        LocalBroadcastManager.m43863b(m44716w()).m43859f(this.f76200Y3);
        LocalBroadcastManager.m43863b(m44716w()).m43859f(this.f76201Z3);
        super.mo3638V0();
    }

    /* renamed from: k3 */
    public Bundle m3924k3(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("Title", str);
        return bundle;
    }

    /* renamed from: l3 */
    public String m3923l3() {
        Iterator<Bundle> it2 = CompressHelper.f73778s.iterator();
        int i = 0;
        String str = "";
        while (it2.hasNext()) {
            Bundle next = it2.next();
            i++;
            str = str + i + ". " + ((((("Title : " + next.getString("Title") + " | ") + "Type : " + next.getString("Type") + " | ") + "Name : " + next.getString("Name") + " | ") + "Version : " + next.getString("Version") + " | ") + "Path : " + next.getString("Path")) + "\n\n";
        }
        return str.trim();
    }

    /* renamed from: m3 */
    public void m3922m3() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) m44716w().getSystemService("input_method");
            if (m44716w().getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(m44716w().getCurrentFocus().getWindowToken(), 0);
            }
            if (m44716w().getCurrentFocus() != null) {
                m44716w().getCurrentFocus().clearFocus();
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: n3 */
    public String m3921n3() {
        String str = this.f76191P3.m4856z1() + "/highlights.db";
        if (!new File(str).exists()) {
            SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null).execSQL("create virtual table highlight using fts4 (dbName, dbTitle, dbAddress, dbDate, dbDocName, type, text, note, save)");
        }
        return str;
    }

    /* renamed from: o3 */
    public void m3920o3() {
        iMDLogger.m3296d("sendFavorite", "Sending FavoriteChanged message");
        Intent intent = new Intent("net.imedicaldoctor.imd.favorite");
        intent.putExtra("Test", "Random data for test");
        LocalBroadcastManager.m43863b(m44716w()).m43861d(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0149, code lost:
        if (android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).contains("SearchResult") != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x014b, code lost:
        android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).edit().remove(r0).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x015e, code lost:
        android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).edit().putString(r0, r4.getString("Title")).commit();
        r3.f76182G3.getAdapter().m42860G();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0193, code lost:
        if (android.preference.PreferenceManager.getDefaultSharedPreferences(m44716w()).contains("ContentSearchResult") != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* renamed from: p3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m3919p3(android.os.Bundle r4, java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.accountFragment.m3919p3(android.os.Bundle, java.lang.String):void");
    }
}
