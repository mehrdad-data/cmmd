package net.imedicaldoctor.imd.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.basusingh.beautifulprogressdialog.BeautifulProgressDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Data.HistoryAdapter;
import net.imedicaldoctor.imd.ViewHolders.ContentSearchAdapter;
import net.imedicaldoctor.imd.Views.ButtonSmall;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SearchHelperFragment extends Fragment {

    /* renamed from: F3 */
    public int f75209F3;

    /* renamed from: G3 */
    public String f75210G3;

    /* renamed from: H3 */
    public ListView f75211H3;

    /* renamed from: I3 */
    public Bundle f75212I3;

    /* renamed from: J3 */
    public boolean f75213J3;

    /* renamed from: K3 */
    public boolean f75214K3;

    /* renamed from: L3 */
    public CompressHelper f75215L3;

    /* renamed from: M3 */
    public RecyclerView.Adapter f75216M3;

    /* renamed from: N3 */
    public ContentSearchAdapter f75217N3;

    /* renamed from: O3 */
    public ArrayList<Bundle> f75218O3;

    /* renamed from: P3 */
    public ArrayList<Bundle> f75219P3;

    /* renamed from: Q3 */
    public ArrayList<Bundle> f75220Q3;

    /* renamed from: R3 */
    public View f75221R3;

    /* renamed from: S3 */
    public Toolbar f75222S3;

    /* renamed from: T3 */
    public SearchView f75223T3;

    /* renamed from: U3 */
    public ImageView f75224U3;

    /* renamed from: V3 */
    public TextView f75225V3;

    /* renamed from: W3 */
    public ButtonSmall f75226W3;

    /* renamed from: X3 */
    public RecyclerView f75227X3;

    /* renamed from: Y3 */
    public ImageButton f75228Y3;

    /* renamed from: Z3 */
    private DrawerLayout f75229Z3;

    /* renamed from: a4 */
    public RecyclerView f75230a4;

    /* loaded from: classes2.dex */
    public class ContentSearchViewHolder {

        /* renamed from: a */
        public final TextView f75275a;

        /* renamed from: b */
        public final TextView f75276b;

        public ContentSearchViewHolder(View view) {
            this.f75275a = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f75276b = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: N0 */
    public void mo4342N0(Context context) {
        super.mo4342N0(context);
    }

    /* renamed from: N2 */
    public void m4341N2() {
        SearchView searchView = (SearchView) this.f75221R3.findViewById(C4804R.C4808id.f87012search_view);
        this.f75223T3 = searchView;
        searchView.setIconifiedByDefault(false);
        this.f75223T3.setQueryHint("Can't Search in Damu");
        this.f75223T3.setEnabled(false);
    }

    /* renamed from: O2 */
    public void m4340O2(final Runnable runnable, final Runnable runnable2) {
        final BeautifulProgressDialog beautifulProgressDialog = new BeautifulProgressDialog(m44716w(), BeautifulProgressDialog.f23703p, null);
        beautifulProgressDialog.m40335o("loading-1.json");
        beautifulProgressDialog.m40334p(true);
        new Thread(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(500L);
                    SearchHelperFragment.this.f75221R3.post(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RunnableC41267 runnableC41267 = RunnableC41267.this;
                            if (SearchHelperFragment.this.f75214K3) {
                                return;
                            }
                            beautifulProgressDialog.m40329u();
                        }
                    });
                } catch (InterruptedException unused) {
                }
            }
        }).start();
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.8
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    runnable.run();
                    observableEmitter.onNext("asdfadf");
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    e.printStackTrace();
                    SearchHelperFragment.this.f75214K3 = true;
                    beautifulProgressDialog.m40349a();
                }
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.9
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                SearchHelperFragment.this.f75214K3 = true;
                beautifulProgressDialog.m40349a();
                try {
                    runnable2.run();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.10
            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                SearchHelperFragment.this.f75214K3 = true;
                beautifulProgressDialog.m40349a();
                th.printStackTrace();
                FirebaseCrashlytics.m18030d().m18027g(th);
                runnable2.run();
            }
        });
    }

    /* renamed from: Q2 */
    public void m4338Q2() {
        this.f75227X3.setItemAnimator(new DefaultItemAnimator());
        this.f75227X3.m42923n(new DividerItemDecoration(m44716w(), 1));
        this.f75227X3.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
    }

    /* renamed from: R2 */
    public void m4337R2() {
        if (this.f75212I3.containsKey("Damu")) {
            m4341N2();
            return;
        }
        SearchView searchView = (SearchView) this.f75221R3.findViewById(C4804R.C4808id.f87012search_view);
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75223T3.setIconifiedByDefault(false);
        this.f75223T3.setQueryHint("Search All");
        m4328b3();
        this.f75223T3.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.19
            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: a */
            public boolean mo3524a(int i) {
                Cursor mo45341b = SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchHelperFragment.this.f75223T3.getTag(1) != null && ((String) SearchHelperFragment.this.f75223T3.getTag(1)).length() > 0) {
                        string = ((String) SearchHelperFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchHelperFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: b */
            public boolean mo3523b(int i) {
                Cursor mo45341b = SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchHelperFragment.this.f75223T3.getTag() != null && ((String) SearchHelperFragment.this.f75223T3.getTag()).length() > 0) {
                        string = ((String) SearchHelperFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchHelperFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }
        });
        ((ImageView) this.f75223T3.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchHelperFragment.this.f75223T3.m51655i0("", false);
                SearchHelperFragment.this.f75223T3.clearFocus();
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                searchHelperFragment.f75227X3.setAdapter(searchHelperFragment.f75216M3);
                SearchHelperFragment.this.m4330Y2();
                SearchHelperFragment.this.mo4182c3();
            }
        });
        ((SearchView.SearchAutoComplete) this.f75223T3.findViewById(C4804R.C4808id.search_src_text)).setDropDownAnchor(C4804R.C4808id.f87012search_view);
        this.f75223T3.setSuggestionsAdapter(new CursorAdapter(m44716w(), null, 0) { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.21
            @Override // androidx.cursoradapter.widget.CursorAdapter
            /* renamed from: d */
            public void mo3522d(View view, Context context, Cursor cursor) {
                ((TextView) view.getTag()).setText(cursor.getString(cursor.getColumnIndex("word")));
            }

            @Override // androidx.cursoradapter.widget.CursorAdapter
            /* renamed from: i */
            public View mo3521i(Context context, Cursor cursor, ViewGroup viewGroup) {
                View inflate = LayoutInflater.from(context).inflate(C4804R.C4810layout.f87293list_view_item_spell, viewGroup, false);
                inflate.setTag(inflate.findViewById(C4804R.C4808id.text));
                return inflate;
            }
        });
        this.f75223T3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.22
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                if (searchHelperFragment.f75213J3) {
                    searchHelperFragment.f75210G3 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.22.2
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                String[] split;
                                String str2 = str.trim().split(StringUtils.SPACE)[split.length - 1];
                                String str3 = "";
                                for (int i = 0; i < split.length - 1; i++) {
                                    str3 = str3 + StringUtils.SPACE + split[i];
                                }
                                SearchHelperFragment.this.f75223T3.setTag(str3.trim());
                                return SearchHelperFragment.this.mo3980j3(str2);
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45336l(SearchHelperFragment.this.f75215L3.m4912h((ArrayList) obj));
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                        return true;
                    }
                    return false;
                }
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(final String str) {
                new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.22.1
                    @Override // android.os.AsyncTask
                    protected Object doInBackground(Object[] objArr) {
                        SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                        searchHelperFragment.f75219P3 = searchHelperFragment.mo3981d3(str);
                        return null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPostExecute(Object obj) {
                        SearchHelperFragment.this.mo3982a3();
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        try {
                            SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                            CompressHelper compressHelper = searchHelperFragment.f75215L3;
                            String str2 = searchHelperFragment.f75210G3;
                            compressHelper.m4994I(str2, SearchHelperFragment.this.f75212I3.getString("Name") + " --- " + SearchHelperFragment.this.f75212I3.getString("Title"));
                        } catch (Exception e) {
                            FirebaseCrashlytics.m18030d().m18027g(e);
                        }
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                return false;
            }
        });
    }

    /* renamed from: S2 */
    public void m4336S2() {
        if (this.f75212I3.containsKey("Damu")) {
            m4341N2();
            return;
        }
        SearchView searchView = (SearchView) this.f75221R3.findViewById(C4804R.C4808id.f87012search_view);
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75223T3.setIconifiedByDefault(false);
        this.f75223T3.setQueryHint("Search");
        m4328b3();
        ((ImageView) this.f75223T3.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchHelperFragment.this.f75223T3.m51655i0("", false);
                SearchHelperFragment.this.f75223T3.clearFocus();
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                searchHelperFragment.f75227X3.setAdapter(searchHelperFragment.f75216M3);
                SearchHelperFragment.this.m4330Y2();
                SearchHelperFragment.this.mo4182c3();
            }
        });
        this.f75223T3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.14
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                if (searchHelperFragment.f75213J3) {
                    searchHelperFragment.f75210G3 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.14.1
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                SearchHelperFragment searchHelperFragment2 = SearchHelperFragment.this;
                                searchHelperFragment2.f75219P3 = searchHelperFragment2.mo3981d3(str);
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchHelperFragment.this.mo3982a3();
                            }

                            @Override // android.os.AsyncTask
                            protected void onPreExecute() {
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                        return true;
                    }
                    return false;
                }
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(String str) {
                return false;
            }
        });
    }

    /* renamed from: T2 */
    public void mo4335T2() {
        if (this.f75212I3.containsKey("Damu")) {
            m4341N2();
            return;
        }
        SearchView searchView = (SearchView) this.f75221R3.findViewById(C4804R.C4808id.f87012search_view);
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75223T3.setIconifiedByDefault(false);
        this.f75223T3.setQueryHint("Search");
        this.f75213J3 = true;
        ((ImageView) this.f75223T3.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchHelperFragment.this.f75223T3.m51655i0("", false);
                SearchHelperFragment.this.f75223T3.clearFocus();
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                searchHelperFragment.f75227X3.setAdapter(searchHelperFragment.f75216M3);
                SearchHelperFragment.this.m4330Y2();
                SearchHelperFragment.this.mo4182c3();
            }
        });
        this.f75223T3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.12
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                if (searchHelperFragment.f75213J3) {
                    searchHelperFragment.f75210G3 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.12.1
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                SearchHelperFragment searchHelperFragment2 = SearchHelperFragment.this;
                                searchHelperFragment2.f75219P3 = searchHelperFragment2.mo3981d3(str);
                                SearchHelperFragment searchHelperFragment3 = SearchHelperFragment.this;
                                searchHelperFragment3.f75220Q3 = searchHelperFragment3.mo3980j3(str);
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchHelperFragment.this.mo3982a3();
                            }

                            @Override // android.os.AsyncTask
                            protected void onPreExecute() {
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                        return true;
                    }
                    SearchHelperFragment searchHelperFragment2 = SearchHelperFragment.this;
                    searchHelperFragment2.f75227X3.setAdapter(searchHelperFragment2.f75216M3);
                    return false;
                }
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(String str) {
                return false;
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f75215L3 = new CompressHelper(m44716w());
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75209F3 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75210G3 = bundle.getString("Query");
        }
        this.f75211H3 = (ListView) this.f75221R3.findViewById(C4804R.C4808id.f86950list_view);
        this.f75212I3 = (m44859B() == null || !m44859B().containsKey("DB")) ? null : m44859B().getBundle("DB");
        try {
            m4327e3("");
        } catch (Exception unused) {
        }
        LinearLayout linearLayout = (LinearLayout) this.f75221R3.findViewById(C4804R.C4808id.f87030status_layout);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.23
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchHelperFragment.this.m4330Y2();
                }
            });
        }
        return super.mo3277U0(layoutInflater, viewGroup, bundle);
    }

    /* renamed from: U2 */
    public void m4334U2() {
        if (this.f75212I3.containsKey("Damu")) {
            m4341N2();
            return;
        }
        SearchView searchView = (SearchView) this.f75221R3.findViewById(C4804R.C4808id.f87012search_view);
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75223T3.setIconifiedByDefault(false);
        this.f75223T3.setQueryHint("Search All");
        m4328b3();
        this.f75223T3.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.15
            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: a */
            public boolean mo3524a(int i) {
                Cursor mo45341b = SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchHelperFragment.this.f75223T3.getTag(1) != null && ((String) SearchHelperFragment.this.f75223T3.getTag(1)).length() > 0) {
                        string = ((String) SearchHelperFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchHelperFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: b */
            public boolean mo3523b(int i) {
                Cursor mo45341b = SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchHelperFragment.this.f75223T3.getTag() != null && ((String) SearchHelperFragment.this.f75223T3.getTag()).length() > 0) {
                        string = ((String) SearchHelperFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchHelperFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }
        });
        ((ImageView) this.f75223T3.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchHelperFragment.this.f75223T3.m51655i0("", false);
                SearchHelperFragment.this.f75223T3.clearFocus();
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                searchHelperFragment.f75227X3.setAdapter(searchHelperFragment.f75216M3);
                SearchHelperFragment.this.m4330Y2();
                SearchHelperFragment.this.mo4182c3();
            }
        });
        ((SearchView.SearchAutoComplete) this.f75223T3.findViewById(C4804R.C4808id.search_src_text)).setDropDownAnchor(C4804R.C4808id.f87012search_view);
        this.f75223T3.setSuggestionsAdapter(new CursorAdapter(m44716w(), null, 0) { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.17
            @Override // androidx.cursoradapter.widget.CursorAdapter
            /* renamed from: d */
            public void mo3522d(View view, Context context, Cursor cursor) {
                ((TextView) view.getTag()).setText(cursor.getString(cursor.getColumnIndex("word")));
            }

            @Override // androidx.cursoradapter.widget.CursorAdapter
            /* renamed from: i */
            public View mo3521i(Context context, Cursor cursor, ViewGroup viewGroup) {
                View inflate = LayoutInflater.from(context).inflate(C4804R.C4810layout.f87293list_view_item_spell, viewGroup, false);
                inflate.setTag(inflate.findViewById(C4804R.C4808id.text));
                return inflate;
            }
        });
        this.f75223T3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.18
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                if (searchHelperFragment.f75213J3) {
                    searchHelperFragment.f75210G3 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.18.2
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                return SearchHelperFragment.this.mo3980j3(str);
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchHelperFragment.this.f75223T3.getSuggestionsAdapter().mo45336l(SearchHelperFragment.this.f75215L3.m4912h((ArrayList) obj));
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                        return true;
                    }
                    return false;
                }
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: b */
            public boolean mo3519b(final String str) {
                new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.18.1
                    @Override // android.os.AsyncTask
                    protected Object doInBackground(Object[] objArr) {
                        SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                        searchHelperFragment.f75219P3 = searchHelperFragment.mo3981d3(str);
                        return null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPostExecute(Object obj) {
                        SearchHelperFragment.this.mo3982a3();
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        try {
                            SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                            CompressHelper compressHelper = searchHelperFragment.f75215L3;
                            String str2 = searchHelperFragment.f75210G3;
                            compressHelper.m4994I(str2, SearchHelperFragment.this.f75212I3.getString("Name") + " --- " + SearchHelperFragment.this.f75212I3.getString("Title"));
                        } catch (Exception e) {
                            FirebaseCrashlytics.m18030d().m18027g(e);
                        }
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                return false;
            }
        });
    }

    /* renamed from: V2 */
    public void m4333V2() {
        Toolbar toolbar = (Toolbar) this.f75221R3.findViewById(C4804R.C4808id.f87063toolbar);
        this.f75222S3 = toolbar;
        if (toolbar == null) {
            return;
        }
        this.f75226W3 = (ButtonSmall) this.f75221R3.findViewById(C4804R.C4808id.f86802back_button);
        this.f75222S3.setNavigationOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchHelperFragment.this.f75215L3.m4998G1(true);
            }
        });
        if (this.f75226W3 != null) {
            this.f75226W3.setDrawableIcon(m44716w().getResources().getDrawable(C4804R.C4807drawable.f86485back_icon_white));
            this.f75226W3.setRippleColor(m44716w().getResources().getColor(C4804R.C4806color.f86409toolbar_item_ripple_color));
            this.f75226W3.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchHelperFragment.this.f75215L3.m4998G1(true);
                }
            });
            this.f75226W3.setOnLongClickListener(new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.3
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    SearchHelperFragment.this.f75215L3.m4989J1(true);
                    return true;
                }
            });
        }
        ImageButton imageButton = (ImageButton) this.f75221R3.findViewById(C4804R.C4808id.f86775action_home);
        this.f75228Y3 = imageButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchHelperFragment.this.f75215L3.m4989J1(true);
                }
            });
        }
        this.f75224U3 = (ImageView) this.f75221R3.findViewById(C4804R.C4808id.f87064toolbar_image_view);
        TextView textView = (TextView) this.f75221R3.findViewById(C4804R.C4808id.f87066toolbar_text_view);
        this.f75225V3 = textView;
        if (textView != null) {
            this.f75225V3.setTypeface(Typeface.createFromAsset(m44716w().getAssets(), "fonts/HelveticaNeue-Light.otf"));
            this.f75225V3.setText(mo4205k3());
        }
        if (this.f75224U3 != null) {
            mo4326f3();
        }
        mo4325g3();
        DrawerLayout drawerLayout = (DrawerLayout) this.f75221R3.findViewById(C4804R.C4808id.f86880drawer_layout);
        this.f75229Z3 = drawerLayout;
        if (drawerLayout != null) {
            drawerLayout.m45146a(new DrawerLayout.DrawerListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.5
                @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                /* renamed from: a */
                public void mo3583a(View view) {
                    SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                    searchHelperFragment.f75230a4.setAdapter(new HistoryAdapter(searchHelperFragment.m44716w(), SearchHelperFragment.this.f75229Z3));
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
            RecyclerView recyclerView = (RecyclerView) this.f75221R3.findViewById(C4804R.C4808id.f86881drawer_view);
            this.f75230a4 = recyclerView;
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
                this.f75230a4.m42923n(new DividerItemDecoration(m44716w(), 1));
            }
        }
        this.f75221R3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.6
            @Override // java.lang.Runnable
            public void run() {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                if (searchHelperFragment.f75224U3 != null) {
                    searchHelperFragment.mo4326f3();
                }
            }
        }, 1000L);
    }

    /* renamed from: W2 */
    public String[] m4332W2(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split("</b>")) {
            String[] split = str2.split("<b>");
            if (split.length == 2 && !arrayList.contains(split[1].toLowerCase()) && !split[1].equals("...")) {
                arrayList.add(split[1].toLowerCase());
            }
        }
        if (str.contains("</b> <b>")) {
            for (String str3 : str.replace("</b> <b>", StringUtils.SPACE).split("</b>")) {
                String[] split2 = str3.split("<b>");
                if (split2.length == 2 && !arrayList.contains(split2[1].toLowerCase()) && !split2[1].equals("...") && !arrayList.contains(split2[1].toLowerCase())) {
                    arrayList.add(0, split2[1]);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: X0 */
    public void mo4116X0() {
        super.mo4116X0();
    }

    /* renamed from: X2 */
    public int m4331X2() {
        int identifier = m44782a0().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return m44782a0().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: Y2 */
    public void m4330Y2() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) m44716w().getSystemService("input_method");
            if (m44716w().getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(m44716w().getCurrentFocus().getWindowToken(), 0);
            }
            SearchView searchView = this.f75223T3;
            if (searchView != null) {
                searchView.clearFocus();
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: Z2 */
    public void m4329Z2(Bundle bundle) {
        this.f75215L3 = new CompressHelper(m44716w());
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75209F3 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75210G3 = bundle.getString("Query");
        }
        this.f75212I3 = (m44859B() == null || !m44859B().containsKey("DB")) ? null : m44859B().getBundle("DB");
    }

    /* renamed from: a3 */
    public void mo3982a3() {
        this.f75217N3.m3396f0(this.f75219P3);
        this.f75227X3.setAdapter(this.f75217N3);
    }

    /* renamed from: b3 */
    public void m4328b3() {
        this.f75223T3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.SearchHelperFragment.24
            @Override // java.lang.Runnable
            public void run() {
                SearchHelperFragment searchHelperFragment = SearchHelperFragment.this;
                searchHelperFragment.f75213J3 = true;
                searchHelperFragment.f75223T3.m51655i0(searchHelperFragment.f75210G3, false);
                SearchHelperFragment.this.m4330Y2();
            }
        }, 10L);
        this.f75213J3 = false;
    }

    /* renamed from: c3 */
    public void mo4182c3() {
    }

    /* renamed from: d3 */
    public ArrayList<Bundle> mo3981d3(String str) {
        return null;
    }

    /* renamed from: e3 */
    public void m4327e3(String str) {
        try {
            ((AppCompatActivity) m44716w()).m52860W().mo52658A0(str);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: f3 */
    public void mo4326f3() {
        try {
            Glide.m40316F(this).mo40145t(CompressHelper.m5012C(this.f75212I3)).mo39038a(new RequestOptions().m39082B()).m40191t2(this.f75224U3);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: g3 */
    public void mo4325g3() {
        if (PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("HideStatusBar", false)) {
            float dimension = m44782a0().getDimension(C4804R.dimen.f86462toolbar_padding);
            Toolbar toolbar = this.f75222S3;
            if (toolbar != null) {
                toolbar.setPadding(0, (int) dimension, 0, 0);
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.f75221R3.findViewById(C4804R.C4808id.f86847collapsing_toolbar);
            }
        }
    }

    /* renamed from: h3 */
    public void mo3543h3() {
        ((ListView) this.f75221R3.findViewById(C4804R.C4808id.f86950list_view)).setVisibility(0);
        ((TextView) this.f75221R3.findViewById(C4804R.C4808id.f87029status_label)).setVisibility(8);
        ((LinearLayout) this.f75221R3.findViewById(C4804R.C4808id.f87030status_layout)).setVisibility(8);
    }

    /* renamed from: i3 */
    public void mo3542i3(String str) {
        TextView textView = (TextView) this.f75221R3.findViewById(C4804R.C4808id.f87029status_label);
        ((ListView) this.f75221R3.findViewById(C4804R.C4808id.f86950list_view)).setVisibility(8);
        textView.setVisibility(0);
        ((LinearLayout) this.f75221R3.findViewById(C4804R.C4808id.f87030status_layout)).setVisibility(0);
        textView.setText(str);
    }

    /* renamed from: j3 */
    public ArrayList<Bundle> mo3980j3(String str) {
        return null;
    }

    /* renamed from: k3 */
    public String mo4205k3() {
        return this.f75215L3.m4960T0(this.f75212I3.getString("Title"));
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }
}
