package net.imedicaldoctor.imd.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Utils.Devices;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.Views.ProgressBarCircularIndeterminate;
import net.imedicaldoctor.imd.iMDActivity;

/* loaded from: classes2.dex */
public class activationActivity extends iMDActivity {

    /* loaded from: classes2.dex */
    public static class activationFragment extends Fragment {

        /* renamed from: F3 */
        public View f76346F3;

        /* renamed from: G3 */
        public VBHelper f76347G3;

        /* renamed from: H3 */
        private int f76348H3 = 0;

        /* renamed from: I3 */
        private String f76349I3;

        /* renamed from: J3 */
        private TextView f76350J3;

        /* renamed from: K3 */
        private EditText f76351K3;

        /* renamed from: L3 */
        private EditText f76352L3;

        /* renamed from: net.imedicaldoctor.imd.Fragments.activationActivity$activationFragment$4 */
        /* loaded from: classes2.dex */
        class View$OnClickListenerC45514 implements View.OnClickListener {

            /* renamed from: net.imedicaldoctor.imd.Fragments.activationActivity$activationFragment$4$1 */
            /* loaded from: classes2.dex */
            class AsyncTaskC45521 extends AsyncTask {

                /* renamed from: a */
                private String f76364a;

                /* renamed from: b */
                final /* synthetic */ String f76365b;

                /* renamed from: net.imedicaldoctor.imd.Fragments.activationActivity$activationFragment$4$1$1 */
                /* loaded from: classes2.dex */
                class AsyncTaskC45531 extends AsyncTask {

                    /* renamed from: a */
                    private String f76367a;

                    /* renamed from: b */
                    final /* synthetic */ String[] f76368b;

                    AsyncTaskC45531(String[] strArr) {
                        this.f76368b = strArr;
                    }

                    @Override // android.os.AsyncTask
                    protected Object doInBackground(Object[] objArr) {
                        String m3477b = Devices.m3477b();
                        int i = 0;
                        try {
                            i = activationFragment.this.m44716w().getPackageManager().getPackageInfo(activationFragment.this.m44716w().getPackageName(), 0).versionCode;
                        } catch (Exception unused) {
                        }
                        String str = "addDevice|||||" + this.f76368b[1] + "|||||" + activationFragment.this.f76347G3.m3421l() + "|||||" + Build.USER + "|||||" + m3477b + "|||||android|||||" + activationFragment.this.m3876g3() + "|||||android-" + i;
                        activationFragment activationfragment = activationFragment.this;
                        this.f76367a = activationfragment.f76347G3.m3424i(activationfragment.m3895N2(str), "127");
                        return null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPostExecute(Object obj) {
                        super.onPostExecute(obj);
                        String str = this.f76367a;
                        if (str == null) {
                            activationFragment.this.m3865q3("Error in contacting server. please check your internet connection and tap here to try again");
                            activationFragment.this.m3866p3();
                            return;
                        }
                        String replace = str.replace("|||||", ":::::");
                        this.f76367a = replace;
                        String[] split = TextUtils.split(replace, ":::::");
                        if (split[0].equals(IcyHeaders.f35463C2) && split[0].length() == 1) {
                            activationFragment.this.m3874i3();
                            activationFragment.this.f76349I3 = null;
                            activationFragment.this.f76350J3.setText("Your Device Activated Sucxcessfully.Enjoy!");
                            PreferenceManager.getDefaultSharedPreferences(activationFragment.this.m44716w()).edit().putString("ActivationCode", split[1]).commit();
                            new Timer().schedule(new TimerTask() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.4.1.1.1
                                @Override // java.util.TimerTask, java.lang.Runnable
                                public void run() {
                                    activationFragment.this.f76350J3.post(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.4.1.1.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            Intent intent = new Intent(activationFragment.this.m44716w(), mainActivity.class);
                                            activationFragment.this.m44716w().finish();
                                            activationFragment.this.mo4139H2(intent);
                                            activationFragment.this.m44716w().overridePendingTransition(C4804R.anim.f85970from_fade_in, C4804R.anim.f85971from_fade_out);
                                        }
                                    });
                                }
                            }, SimpleExoPlayer.f32068s1);
                        } else if (split[0].equals("0")) {
                            activationFragment.this.m3866p3();
                            activationFragment.this.m3865q3(split[1]);
                        } else {
                            activationFragment.this.m3866p3();
                            activationFragment.this.m3865q3("Error in adding device");
                        }
                    }
                }

                AsyncTaskC45521(String str) {
                    this.f76365b = str;
                }

                @Override // android.os.AsyncTask
                protected Object doInBackground(Object[] objArr) {
                    activationFragment activationfragment = activationFragment.this;
                    this.f76364a = activationfragment.f76347G3.m3424i(activationfragment.m3895N2(this.f76365b), "127");
                    return null;
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                    String str = this.f76364a;
                    if (str == null) {
                        activationFragment.this.m3865q3("Error in contacting server. please check your internet connection and tap here to try again");
                        activationFragment.this.m3866p3();
                        return;
                    }
                    String replace = str.replace("|||||", ":::::");
                    this.f76364a = replace;
                    String[] split = TextUtils.split(replace, ":::::");
                    if (!split[0].equals(IcyHeaders.f35463C2) || split[0].length() != 1) {
                        activationFragment.this.m3866p3();
                        activationFragment.this.m3865q3("Wrong Username or Password");
                        return;
                    }
                    activationFragment.this.m3874i3();
                    activationFragment.this.f76349I3 = null;
                    activationFragment.this.f76350J3.setText("Login Successful");
                    PreferenceManager.getDefaultSharedPreferences(activationFragment.this.m44716w()).edit().remove("DS").commit();
                    new AsyncTaskC45531(split).execute(new Object[0]);
                }
            }

            View$OnClickListenerC45514() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activationFragment.this.f76351K3.getText().toString().length() < 1) {
                    activationFragment.this.m3877f3();
                    activationFragment.this.m3869n3("Please enter your Username");
                    return;
                }
                activationFragment.this.m3871l3();
                if (activationFragment.this.f76352L3.getText().toString().length() < 1) {
                    activationFragment.this.m3877f3();
                    activationFragment.this.m3869n3("Please enter your Password");
                    return;
                }
                activationFragment.this.m3873j3();
                activationFragment.this.m3868o3();
                activationFragment.this.m3874i3();
                new AsyncTaskC45521("checkUser|||||" + activationFragment.this.f76351K3.getText().toString() + "|||||" + activationFragment.this.f76352L3.getText().toString()).execute(new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:34:0x013a  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0144  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0149 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r2v0, types: [net.imedicaldoctor.imd.Data.CompressHelper] */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v11, types: [java.net.HttpURLConnection, java.net.URLConnection] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4, types: [java.net.HttpURLConnection] */
        /* renamed from: N2 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String m3895N2(java.lang.String r10) {
            /*
                Method dump skipped, instructions count: 372
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.m3895N2(java.lang.String):java.lang.String");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f3 */
        public void m3877f3() {
            if (m44716w() == null) {
                return;
            }
            try {
                TextView textView = (TextView) m44716w().findViewById(C4804R.C4808id.f87029status_label);
                textView.setVisibility(0);
                textView.setTextColor(SupportMenu.f12537c);
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }

        /* renamed from: h3 */
        private void m3875h3() {
            try {
                ((ProgressBarCircularIndeterminate) m44716w().findViewById(C4804R.C4808id.f86992progress_bar)).setVisibility(8);
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i3 */
        public void m3874i3() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j3 */
        public void m3873j3() {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) m44716w().getSystemService("input_method");
                if (m44716w().getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(m44716w().getCurrentFocus().getWindowToken(), 0);
                }
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }

        /* renamed from: k3 */
        private void m3872k3(final View view, long j) {
            view.animate().alpha(0.0f).setDuration(j).setListener(new Animator.AnimatorListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.12
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(8);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l3 */
        public void m3871l3() {
            TextView textView = (TextView) m44716w().findViewById(C4804R.C4808id.f87029status_label);
            textView.setVisibility(0);
            textView.setTextColor(-16711936);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m3 */
        public void m3870m3(String str) {
            mo4139H2(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n3 */
        public void m3869n3(String str) {
            try {
                m3875h3();
                if (str != null) {
                    this.f76350J3.setText(str);
                    this.f76350J3.setVisibility(0);
                } else {
                    this.f76350J3.setText("");
                    this.f76350J3.setVisibility(8);
                    this.f76349I3 = str;
                }
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o3 */
        public void m3868o3() {
            ProgressBarCircularIndeterminate progressBarCircularIndeterminate = (ProgressBarCircularIndeterminate) m44716w().findViewById(C4804R.C4808id.f86992progress_bar);
            progressBarCircularIndeterminate.setBackgroundColor(Color.parseColor("#1e88e5"));
            progressBarCircularIndeterminate.setVisibility(0);
            this.f76350J3.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p3 */
        public void m3866p3() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: q3 */
        public void m3865q3(String str) {
            m3875h3();
            this.f76349I3 = null;
            this.f76350J3.setText(str);
            m3877f3();
        }

        @Override // androidx.fragment.app.Fragment
        /* renamed from: U0 */
        public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View view = this.f76346F3;
            if (view != null) {
                return view;
            }
            this.f76347G3 = new VBHelper(m44716w());
            this.f76346F3 = layoutInflater.inflate(C4804R.C4810layout.f87172fragment_new_login, viewGroup, false);
            new Timer().schedule(new TimerTask() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (activationFragment.this.f76349I3 == null) {
                        activationFragment.this.f76348H3 = 0;
                        return;
                    }
                    activationFragment.this.f76348H3++;
                    final String str = "";
                    for (int i = 0; i < activationFragment.this.f76348H3; i++) {
                        str = str + ".";
                    }
                    activationFragment.this.f76350J3.post(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TextView textView = (TextView) activationFragment.this.f76346F3.findViewById(C4804R.C4808id.f87029status_label);
                            if (activationFragment.this.f76349I3 != null) {
                                textView.setText(activationFragment.this.f76349I3 + str);
                            }
                        }
                    });
                    if (activationFragment.this.f76348H3 >= 3) {
                        activationFragment.this.f76348H3 = 0;
                    }
                }
            }, 500L, 500L);
            ((TextView) this.f76346F3.findViewById(C4804R.C4808id.f86939imd_title)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    String m4959T1 = new CompressHelper(activationFragment.this.m44716w()).m4959T1();
                    if (m4959T1.length() <= 0) {
                        m4959T1 = "No Message Available";
                    }
                    new AlertDialog.Builder(activationFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l(m4959T1).mo26284p("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.2.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).m52864I();
                }
            });
            this.f76350J3 = (TextView) this.f76346F3.findViewById(C4804R.C4808id.f87029status_label);
            this.f76351K3 = (EditText) this.f76346F3.findViewById(C4804R.C4808id.f87078user_text);
            this.f76352L3 = (EditText) this.f76346F3.findViewById(C4804R.C4808id.f86987password_text);
            final CompressHelper compressHelper = new CompressHelper(m44716w());
            ((LinearLayout) this.f76346F3.findViewById(C4804R.C4808id.f87076upper_layout)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    activationFragment.this.m3873j3();
                }
            });
            ((Button) this.f76346F3.findViewById(C4804R.C4808id.f86956login_button)).setOnClickListener(new View$OnClickListenerC45514());
            ((TextView) this.f76346F3.findViewById(C4804R.C4808id.f87003register_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    compressHelper.m4973P("http://imedicaldoctor.net/buyaccount.php");
                }
            });
            final TextView textView = (TextView) this.f76346F3.findViewById(C4804R.C4808id.f86839change_server_button);
            textView.setText(PreferenceManager.getDefaultSharedPreferences(m44716w()).getString("MainServer", "Iran") + " Server (Tap to change)");
            textView.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    PreferenceManager.getDefaultSharedPreferences(activationFragment.this.m44716w()).edit().putString("MainServer", PreferenceManager.getDefaultSharedPreferences(activationFragment.this.m44716w()).getString("MainServer", "Iran").equals("Iran") ? "Germany" : "Iran").commit();
                    TextView textView2 = textView;
                    textView2.setText(PreferenceManager.getDefaultSharedPreferences(activationFragment.this.m44716w()).getString("MainServer", "Iran") + " Server (Tap to change)");
                }
            });
            TextView textView2 = (TextView) this.f76346F3.findViewById(C4804R.C4808id.f86901forgot_label);
            textView2.setOnLongClickListener(new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.7
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    String m4959T1 = compressHelper.m4959T1();
                    if (m4959T1.length() > 0) {
                        new AlertDialog.Builder(activationFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l(m4959T1).mo26284p("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.7.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).m52864I();
                        return false;
                    }
                    return false;
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    compressHelper.m4973P("http://imedicaldoctor.net/forgot.php");
                }
            });
            ((ImageView) this.f76346F3.findViewById(C4804R.C4808id.f87043telegram_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    activationFragment.this.m3870m3("http://imedicaldoctor.net/telegramandroid.php");
                }
            });
            ((ImageView) this.f76346F3.findViewById(C4804R.C4808id.f86941instagram_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    activationFragment.this.m3870m3("http://instagram.com/imedicaldoctor");
                }
            });
            ((ImageView) this.f76346F3.findViewById(C4804R.C4808id.f86957mail_button)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.activationActivity.activationFragment.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    activationFragment.this.m3870m3("mailto:support@imedicaldoctor.net");
                }
            });
            return this.f76346F3;
        }

        /* renamed from: g3 */
        public String m3876g3() {
            String str = Build.VERSION.RELEASE;
            int i = Build.VERSION.SDK_INT;
            return "Android SDK: " + i + " (" + str + ")";
        }

        @Override // androidx.fragment.app.Fragment
        /* renamed from: p1 */
        public void mo3867p1(View view, Bundle bundle) {
            super.mo3867p1(view, bundle);
            this.f76350J3.setText("");
        }
    }

    /* renamed from: q0 */
    public static Bitmap m3896q0(Context context, String str) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(str));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4804R.C4810layout.f87089activity_activation);
        if (bundle == null) {
            m44690E().m44464r().m44301b(C4804R.C4808id.container, new activationFragment()).mo44289n();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        menuItem.getItemId();
        return super.onOptionsItemSelected(menuItem);
    }
}
