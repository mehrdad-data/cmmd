package net.imedicaldoctor.imd.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.tool.xml.css.CSS;
import com.itextpdf.tool.xml.html.HTML;
import com.timehop.stickyheadersrecyclerview.ItemVisibilityAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.ViewHolders.StatusAdapter;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class searchFragment extends SearchHelperFragment {

    /* renamed from: b4 */
    private SearchAdapter f76974b4;

    /* renamed from: c4 */
    private boolean f76975c4;

    /* renamed from: d4 */
    private ArrayList<Bundle> f76976d4;

    /* renamed from: e4 */
    private RecyclerView f76977e4;

    /* renamed from: f4 */
    private AsyncTask f76978f4;

    /* renamed from: g4 */
    private boolean f76979g4;

    /* renamed from: h4 */
    private Observable<Bundle> f76980h4;

    /* renamed from: i4 */
    private DisposableObserver<Bundle> f76981i4;

    /* renamed from: j4 */
    private ProgressBar f76982j4;

    /* renamed from: k4 */
    private MenuItem f76983k4;

    /* renamed from: l4 */
    private String f76984l4;

    /* renamed from: m4 */
    private StickyRecyclerHeadersDecoration f76985m4;

    /* renamed from: n4 */
    private LinearLayoutManager f76986n4;

    /* renamed from: o4 */
    private ArrayList<String> f76987o4;

    /* renamed from: p4 */
    StickyRecyclerHeadersTouchListener f76988p4;

    /* renamed from: q4 */
    public CompressHelper f76989q4;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: net.imedicaldoctor.imd.Fragments.searchFragment$6 */
    /* loaded from: classes2.dex */
    public class C47746 implements ObservableOnSubscribe<String> {
        C47746() {
        }

        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
        /* renamed from: a */
        public void mo3518a(@NonNull final ObservableEmitter<String> observableEmitter) throws Throwable {
            searchFragment.this.f75223T3.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.6.1

                /* renamed from: a */
                private DisposableObserver<Bundle> f76997a;

                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                /* renamed from: a */
                public boolean mo3520a(String str) {
                    observableEmitter.onNext(str);
                    return true;
                }

                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                /* renamed from: b */
                public boolean mo3519b(final String str) {
                    iMDLogger.m3290j("OnQueryTextSubmit", "OnQueryTextSubmit");
                    searchFragment.this.f76984l4 = str;
                    searchFragment.this.f75210G3 = str;
                    DisposableObserver<Bundle> disposableObserver = this.f76997a;
                    if (disposableObserver != null) {
                        disposableObserver.onComplete();
                    }
                    observableEmitter.onNext("SoheilvbSoheilvbSoheilvb");
                    searchFragment.this.f76989q4.m4994I(str, "SearchAll");
                    iMDLogger.m3290j("OnQueryTextSubmit", "Building search observable");
                    Observable m7605B4 = Observable.m7156x1(new ObservableOnSubscribe<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.6.1.1
                        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                        /* renamed from: a */
                        public void mo3518a(@NonNull ObservableEmitter<Bundle> observableEmitter2) throws Throwable {
                            Iterator<Bundle> it2 = CompressHelper.f73778s.iterator();
                            while (it2.hasNext()) {
                                Bundle next = it2.next();
                                ArrayList<Bundle> m3538n3 = searchFragment.this.m3538n3(next, str);
                                if (m3538n3 != null && m3538n3.size() != 0) {
                                    iMDLogger.m3290j("Search", "Result from " + next.getString("Title") + " : " + m3538n3.size());
                                    Bundle bundle = new Bundle();
                                    bundle.putBundle("database", next);
                                    bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, m3538n3);
                                    observableEmitter2.onNext(bundle);
                                }
                            }
                            observableEmitter2.onComplete();
                        }
                    }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7605B4(new Function<Throwable, Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.6.1.2
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: a */
                        public Bundle apply(Throwable th) throws Throwable {
                            return null;
                        }
                    });
                    searchFragment.this.f76980h4 = m7605B4;
                    DisposableObserver<Bundle> disposableObserver2 = new DisposableObserver<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.6.1.3
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // io.reactivex.rxjava3.observers.DisposableObserver
                        /* renamed from: a */
                        public void mo3516a() {
                            super.mo3516a();
                            searchFragment.this.f76983k4.setVisible(true);
                            searchFragment.this.f76982j4.setIndeterminate(true);
                            iMDLogger.m3290j("SearchSubscriber", "On Start");
                            searchFragment.this.f76976d4 = new ArrayList();
                            searchFragment.this.m3547F3();
                            searchFragment.this.m4330Y2();
                            searchFragment.this.f75223T3.getSuggestionsAdapter().mo45336l(null);
                            searchFragment.this.mo3542i3("Searching");
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        /* renamed from: b */
                        public void onNext(@NonNull Bundle bundle) {
                            searchFragment.this.f76976d4.add(bundle);
                            searchFragment.this.mo3543h3();
                            StringBuilder sb = new StringBuilder();
                            sb.append("On Next - ");
                            sb.append(bundle.getBundle("database").getString("Title"));
                            sb.append(" - ");
                            searchFragment searchfragment = searchFragment.this;
                            sb.append(searchfragment.m3546G3(searchfragment.f76976d4));
                            sb.append(" - IN Thread :");
                            sb.append(Thread.currentThread().toString());
                            iMDLogger.m3290j("SearchSubscriber", sb.toString());
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        public void onComplete() {
                            searchFragment.this.f76983k4.setVisible(false);
                            iMDLogger.m3290j("SearchSubscriber", "On Complete");
                            if (searchFragment.this.f76976d4 == null || searchFragment.this.f76976d4.size() == 0) {
                                searchFragment.this.mo3542i3("Nothing Found");
                            }
                            if (PreferenceManager.getDefaultSharedPreferences(searchFragment.this.m44716w()).getBoolean("SearchCollapsed", false)) {
                                searchFragment.this.m3550C3();
                            }
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        public void onError(@NonNull Throwable th) {
                            searchFragment.this.f76983k4.setVisible(false);
                            iMDLogger.m3290j("SearchSubscriber", "On Error");
                            iMDLogger.m3294f("onQueryTextSubmit", "Some error on SearchObservable");
                        }
                    };
                    this.f76997a = disposableObserver2;
                    searchFragment.this.f76981i4 = disposableObserver2;
                    m7605B4.mo6065a(this.f76997a);
                    return true;
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter {

        /* renamed from: d */
        private Context f77009d;

        public SearchAdapter(Context context) {
            this.f77009d = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            searchFragment searchfragment = searchFragment.this;
            return searchfragment.m3539m3(i, searchfragment.f76976d4).containsKey("Item") ? 0 : 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            if (viewHolder.m42556F() == 1) {
                return;
            }
            SearchItemViewHolder searchItemViewHolder = (SearchItemViewHolder) viewHolder;
            searchFragment searchfragment = searchFragment.this;
            Bundle bundle = searchfragment.m3539m3(i, searchfragment.f76976d4).getBundle("Item");
            searchItemViewHolder.f77017I.setText(bundle.getString("text"));
            if (bundle.containsKey("subText") && bundle.getString("subText").length() == 0) {
                searchItemViewHolder.f77018J.setText((CharSequence) null);
                searchItemViewHolder.f77018J.setVisibility(8);
            } else {
                searchItemViewHolder.f77018J.setVisibility(0);
                searchItemViewHolder.f77018J.setText(bundle.getString("subText"));
            }
            searchItemViewHolder.f77019K.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.SearchAdapter.1
                /* JADX WARN: Code restructure failed: missing block: B:114:0x03d8, code lost:
                    if (r3.equals("0") != false) goto L122;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:119:0x03f5, code lost:
                    if (r3.equals(com.google.android.exoplayer2.metadata.icy.IcyHeaders.f35463C2) != false) goto L122;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:120:0x03f7, code lost:
                    r21.f77011X.f77010e.f76989q4.m4883q1(r4, r2, null, r1);
                 */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r4v17, types: [java.lang.String[], java.lang.String] */
                /* JADX WARN: Type inference failed for: r4v18 */
                /* JADX WARN: Type inference failed for: r4v7 */
                /* JADX WARN: Type inference failed for: r5v29 */
                /* JADX WARN: Type inference failed for: r5v30, types: [java.lang.String[], java.lang.String] */
                /* JADX WARN: Type inference failed for: r5v31 */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onClick(android.view.View r22) {
                    /*
                        Method dump skipped, instructions count: 1654
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.searchFragment.SearchAdapter.View$OnClickListenerC47831.onClick(android.view.View):void");
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new EmptyViewHolder(LayoutInflater.from(this.f77009d).inflate(C4804R.C4810layout.f87245list_view_item_header_keeper, viewGroup, false));
            }
            View inflate = LayoutInflater.from(this.f77009d).inflate(C4804R.C4810layout.f87277list_view_item_search_ripple, viewGroup, false);
            searchFragment searchfragment = searchFragment.this;
            return new SearchItemViewHolder(searchfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: n */
        public RecyclerView.ViewHolder mo3366n(ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.f77009d).inflate(C4804R.C4810layout.f87276list_view_item_search_header, viewGroup, false);
            searchFragment searchfragment = searchFragment.this;
            return new SearchHeaderViewHolder(searchfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: o */
        public void mo3365o(RecyclerView.ViewHolder viewHolder, int i) {
            SearchHeaderViewHolder searchHeaderViewHolder = (SearchHeaderViewHolder) viewHolder;
            if (searchFragment.this.f76976d4 == null) {
                return;
            }
            searchFragment searchfragment = searchFragment.this;
            Bundle bundle = searchfragment.m3539m3(i, searchfragment.f76976d4).getBundle("Database");
            searchHeaderViewHolder.f77014J.setText(bundle.getString("Title"));
            String m5012C = CompressHelper.m5012C(bundle);
            if (!m5012C.contains("file:///android_asset/")) {
                searchHeaderViewHolder.f77013I.setImageURI(Uri.parse(m5012C));
                return;
            }
            try {
                InputStream open = searchFragment.this.m44716w().getAssets().open(m5012C.replace("file:///android_asset/", ""));
                searchHeaderViewHolder.f77013I.setImageBitmap(BitmapFactory.decodeStream(open));
                open.close();
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                e.printStackTrace();
            }
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: q */
        public long mo3364q(int i) {
            searchFragment searchfragment = searchFragment.this;
            return Long.valueOf(searchfragment.m3540l3(i, searchfragment.f76976d4)).longValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            searchFragment searchfragment = searchFragment.this;
            return searchfragment.m3546G3(searchfragment.f76976d4);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchHeaderViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public ImageView f77013I;

        /* renamed from: J */
        public TextView f77014J;

        /* renamed from: K */
        public ImageView f77015K;

        public SearchHeaderViewHolder(Context context, View view) {
            super(view);
            this.f77014J = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f77013I = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f77015K = (ImageView) view.findViewById(C4804R.C4808id.icon);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f77017I;

        /* renamed from: J */
        public TextView f77018J;

        /* renamed from: K */
        public MaterialRippleLayout f77019K;

        public SearchItemViewHolder(Context context, View view) {
            super(view);
            this.f77017I = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f77018J = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
            this.f77019K = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D3 */
    public Bundle m3549D3(Bundle bundle) {
        String str;
        String str2;
        Bundle bundle2 = new Bundle();
        String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(bundle.getString("docId"), "|");
        String[] splitByWholeSeparator2 = StringUtils.splitByWholeSeparator(bundle.getString(HTML.Tag.f65890V), "|");
        for (int i = 0; i < splitByWholeSeparator.length; i++) {
            if (splitByWholeSeparator2.length > i) {
                str = splitByWholeSeparator[i];
                str2 = splitByWholeSeparator2[i];
            } else {
                str = splitByWholeSeparator[i];
                str2 = "";
            }
            bundle2.putString(str, str2);
        }
        return bundle2;
    }

    /* renamed from: B3 */
    public void m3551B3() {
        if (this.f76975c4) {
            return;
        }
        this.f76977e4.m42923n(this.f76985m4);
        this.f76975c4 = true;
    }

    /* renamed from: C3 */
    public void m3550C3() {
        Bundle bundle;
        this.f76987o4 = new ArrayList<>();
        Iterator<Bundle> it2 = this.f76976d4.iterator();
        while (it2.hasNext()) {
            Bundle next = it2.next();
            if (next != null && (bundle = next.getBundle("database")) != null) {
                this.f76987o4.add(bundle.getString("Name"));
            }
        }
        this.f76977e4.getAdapter().m42860G();
    }

    /* renamed from: E3 */
    public void m3548E3() {
        if (this.f76975c4) {
            this.f76977e4.m42906s1(this.f76985m4);
            this.f76975c4 = false;
        }
    }

    /* renamed from: F3 */
    public void m3547F3() {
        StickyRecyclerHeadersTouchListener stickyRecyclerHeadersTouchListener = new StickyRecyclerHeadersTouchListener(this.f76977e4, this.f76985m4);
        this.f76988p4 = stickyRecyclerHeadersTouchListener;
        stickyRecyclerHeadersTouchListener.m8624h(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.8
            @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener.OnHeaderClickListener
            /* renamed from: a */
            public void mo3512a(View view, int i, long j) {
                String string = ((Bundle) searchFragment.this.f76976d4.get((int) j)).getBundle("database").getString("Name");
                if (searchFragment.this.f76987o4.contains(string)) {
                    searchFragment.this.f76987o4.remove(string);
                } else {
                    searchFragment.this.f76987o4.add(string);
                }
                searchFragment.this.f76977e4.getAdapter().m42860G();
            }
        });
        this.f76977e4.m42914q(this.f76988p4);
        m3551B3();
        this.f76977e4.setAdapter(this.f76974b4);
        this.f76974b4.m42849Z(new RecyclerView.AdapterDataObserver() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.9
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            /* renamed from: a */
            public void mo3511a() {
                searchFragment.this.f76985m4.m8633n();
            }
        });
    }

    /* renamed from: G3 */
    public int m3546G3(ArrayList<Bundle> arrayList) {
        int i = 0;
        if (arrayList == null) {
            return 0;
        }
        Iterator<Bundle> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Bundle next = it2.next();
            i += this.f76987o4.contains(next.getBundle("database").getString("Name")) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
        }
        return i;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: T0 */
    public void mo3545T0(Menu menu, MenuInflater menuInflater) {
        try {
            m44716w().setTitle("");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        menuInflater.inflate(C4804R.C4811menu.f87405search, menu);
        SearchView searchView = (SearchView) menu.findItem(C4804R.C4808id.f86789action_search).getActionView();
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        MenuItem findItem = menu.findItem(C4804R.C4808id.f86994progress_menu);
        this.f76983k4 = findItem;
        this.f76982j4 = (ProgressBar) findItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Anything");
        final String str = this.f76984l4;
        this.f75223T3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.2
            @Override // java.lang.Runnable
            public void run() {
                searchFragment.this.f75223T3.m51655i0(str, false);
                String str2 = searchFragment.this.f75210G3;
                if (str2 == null || str2.length() <= 0) {
                    return;
                }
                if (searchFragment.this.f76976d4 == null || searchFragment.this.f76976d4.size() == 0) {
                    searchFragment searchfragment = searchFragment.this;
                    searchfragment.f75223T3.m51655i0(searchfragment.f75210G3, true);
                } else {
                    searchFragment searchfragment2 = searchFragment.this;
                    searchfragment2.f75223T3.m51655i0(searchfragment2.f75210G3, false);
                    searchFragment.this.mo3543h3();
                }
                searchFragment.this.m4330Y2();
            }
        }, 10L);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.3
            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: a */
            public boolean mo3524a(int i) {
                Cursor mo45341b = searchFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (searchFragment.this.f75223T3.getTag(1) != null && ((String) searchFragment.this.f75223T3.getTag(1)).length() > 0) {
                        string = ((String) searchFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    searchFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: b */
            public boolean mo3523b(int i) {
                Cursor mo45341b = searchFragment.this.f75223T3.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (searchFragment.this.f75223T3.getTag() != null && ((String) searchFragment.this.f75223T3.getTag()).length() > 0) {
                        string = ((String) searchFragment.this.f75223T3.getTag()) + StringUtils.SPACE + string;
                    }
                    searchFragment.this.f75223T3.m51655i0(string, true);
                    return false;
                }
                return false;
            }
        });
        ((ImageView) searchView.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                searchFragment.this.f75223T3.m51655i0("", false);
                searchFragment.this.f75223T3.clearFocus();
                searchFragment.this.mo3542i3("Search Anything");
                searchFragment.this.m4330Y2();
            }
        });
        searchView.setSuggestionsAdapter(new CursorAdapter(m44716w(), null, 0) { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.5
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
        Observable.m7156x1(new C47746()).m7146y1(500L, TimeUnit.MILLISECONDS).mo6065a(new DisposableObserver<String>() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.7
            @Override // io.reactivex.rxjava3.core.Observer
            /* renamed from: b */
            public void onNext(@NonNull String str2) {
                if (str2.equals("SoheilvbSoheilvbSoheilvb")) {
                    searchFragment.this.f75223T3.getSuggestionsAdapter().mo45336l(null);
                } else if (str2.length() > 1) {
                    String[] split = str2.trim().split(StringUtils.SPACE);
                    String str3 = split[split.length - 1];
                    String str4 = "";
                    for (int i = 0; i < split.length - 1; i++) {
                        str4 = str4 + StringUtils.SPACE + split[i];
                    }
                    searchFragment.this.f75223T3.setTag(str4.trim());
                    CompressHelper compressHelper = searchFragment.this.f76989q4;
                    compressHelper.m4931c0(compressHelper.m5018A(), "Select rowid as _id,word from spell where word match '" + str3 + "*'").m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7339e6(new Consumer<ArrayList<Bundle>>() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.7.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(ArrayList<Bundle> arrayList) throws Throwable {
                            searchFragment.this.f75223T3.getSuggestionsAdapter().mo45336l(searchFragment.this.f76989q4.m4912h(arrayList));
                        }
                    });
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(@NonNull Throwable th) {
            }
        });
        m44716w().setTitle("");
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment, androidx.fragment.app.Fragment
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.f75221R3;
        if (view != null) {
            return view;
        }
        this.f76989q4 = new CompressHelper(m44716w());
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87186fragment_search, viewGroup, false);
        this.f75221R3 = inflate;
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75209F3 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75210G3 = bundle.getString("Query");
        }
        if (bundle != null && bundle.containsKey("mIsSubmitted")) {
            this.f76979g4 = bundle.getBoolean("mIsSubmitted");
        }
        this.f76987o4 = new ArrayList<>();
        this.f76977e4 = (RecyclerView) this.f75221R3.findViewById(C4804R.C4808id.f87001recycler_view);
        this.f76976d4 = new ArrayList<>();
        if (bundle != null && bundle.containsKey("mResults")) {
            this.f76976d4 = bundle.getParcelableArrayList("mResults");
        }
        SearchAdapter searchAdapter = new SearchAdapter(m44716w());
        this.f76974b4 = searchAdapter;
        this.f76985m4 = new StickyRecyclerHeadersDecoration(searchAdapter, new ItemVisibilityAdapter() { // from class: net.imedicaldoctor.imd.Fragments.searchFragment.1
            @Override // com.timehop.stickyheadersrecyclerview.ItemVisibilityAdapter
            /* renamed from: a */
            public boolean mo3525a(int i) {
                searchFragment searchfragment = searchFragment.this;
                searchfragment.f76986n4 = (LinearLayoutManager) searchfragment.f76977e4.getLayoutManager();
                searchFragment.this.f76986n4.m43136x2();
                searchFragment.this.f76986n4.m43194A2();
                Boolean valueOf = Boolean.valueOf(searchFragment.this.f76986n4.m43136x2() <= i && searchFragment.this.f76986n4.m43194A2() >= i);
                iMDLogger.m3294f(CSS.Property.f65564m0, i + " visible + " + valueOf);
                return valueOf.booleanValue();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m44716w());
        this.f76986n4 = linearLayoutManager;
        this.f76977e4.setLayoutManager(linearLayoutManager);
        this.f76977e4.setItemAnimator(new DefaultItemAnimator());
        this.f76977e4.m42923n(new DividerItemDecoration(m44716w(), 1));
        m44735q2(true);
        mo3542i3("Search Titles");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: g1 */
    public void mo3544g1() {
        super.mo3544g1();
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment
    /* renamed from: h3 */
    public void mo3543h3() {
        RecyclerView.Adapter adapter = this.f76977e4.getAdapter();
        SearchAdapter searchAdapter = this.f76974b4;
        if (adapter == searchAdapter) {
            searchAdapter.m42860G();
            return;
        }
        this.f76985m4.m8633n();
        this.f76977e4.setAdapter(this.f76974b4);
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment
    /* renamed from: i3 */
    public void mo3542i3(String str) {
        try {
            if (!str.equals("Searching")) {
                this.f76985m4.m8633n();
                m3548E3();
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        this.f76977e4.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
        this.f76977e4.setAdapter(new StatusAdapter(m44716w(), str));
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: l1 */
    public void mo3541l1() {
        super.mo3541l1();
        m4330Y2();
    }

    /* renamed from: l3 */
    public int m3540l3(int i, ArrayList<Bundle> arrayList) {
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Bundle bundle = arrayList.get(i3);
            i2 += this.f76987o4.contains(bundle.getBundle("database").getString("Name")) ? 1 : bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            if (i < i2) {
                return i3;
            }
        }
        return 0;
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment, androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }

    /* renamed from: m3 */
    public Bundle m3539m3(int i, ArrayList<Bundle> arrayList) {
        Iterator<Bundle> it2 = arrayList.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            Bundle next = it2.next();
            String string = next.getBundle("database").getString("Name");
            int size = this.f76987o4.contains(string) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            i2 += size;
            if (i < i2) {
                int i3 = i - (i2 - size);
                Bundle bundle = new Bundle();
                bundle.putBundle("Database", next.getBundle("database"));
                if (this.f76987o4.contains(string)) {
                    if (i3 == 0) {
                        return bundle;
                    }
                    i3--;
                }
                bundle.putBundle("Item", (Bundle) next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i3));
                return bundle;
            }
        }
        return null;
    }

    /* renamed from: n3 */
    public ArrayList<Bundle> m3538n3(Bundle bundle, String str) {
        CompressHelper compressHelper;
        String str2;
        CompressHelper compressHelper2;
        String str3;
        CompressHelper compressHelper3;
        String str4;
        String replace = str.replace("'", "''");
        String string = bundle.getString("Type");
        try {
            if (!string.equals("lexi")) {
                if (string.equals("skyscape")) {
                    compressHelper = this.f76989q4;
                    str2 = "Select id as id, indexName as text,indexType as subText,section  from search where indexName match '" + replace + "'";
                } else if (string.equals("medhand")) {
                    compressHelper3 = this.f76989q4;
                    str4 = "select Text as text, \"table\" as subText, URL from search where text match '" + replace + "'";
                } else if (string.equals("infopoems")) {
                    return null;
                } else {
                    if (string.equals("irandarou")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select drugId, drug as text, '' as subText from Search where drug match '" + replace + "' order by drug asc";
                    } else if (string.equals("uptodateddx")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select id, diagnosis as text, isMain from search where diagnosis match '" + replace + "' order by isMain desc";
                    } else if (string.equals("labvalues")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select id,shortName, longName, subtitle, value, shortName as text, subtitle as subText from search where search match 'shortName:" + replace + " OR longName:" + replace + "'";
                    } else if (string.equals("visualdx")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select id,dName as text,'' as subText from DiagnosesSearch where dNameSearch match '" + replace + "' order by dName asc";
                    } else if (string.equals("uptodate")) {
                        compressHelper3 = this.f76989q4;
                        str4 = "select Text as text, \"table\" as subText,URL, related_topic from search where search match 'text:" + replace + " OR subText:" + replace + "'";
                    } else if (string.equals("accessmedicine")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select text, typeText as subText, type, contentId from Search where search match 'text:" + replace + " NOT type:5'";
                    } else if (string.equals("lww")) {
                        compressHelper = this.f76989q4;
                        str2 = "Select text, typeText as subText, type, contentId from Search where search match 'text:" + replace + " NOT (type:5 OR type:0)'";
                    } else {
                        if (!string.equals("elsevier") && !string.equals("elseviernew")) {
                            if (string.equals("ovid")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text, typeText as subText, type, contentId from Search where search match 'text:" + replace + " NOT type:5 NOT type:0'";
                            } else if (string.equals("epub")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text, typeText as subText, type, contentId, section from Search where search match 'text:" + replace + " NOT type:5'";
                            } else if (string.equals("nejm")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text, typeText as subText, type, contentId, section from Search where search match 'text:" + replace + " AND type:1'";
                            } else if (string.equals("epocrate")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text, typeText as subText,typeText, type, contentId from Search where search match 'text:" + replace + " NOT type:5'";
                            } else if (string.equals("amirsys")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text  || ' - ' || content as text, typeText as subText, type, contentId from Search where search match 'text:" + replace + " AND type:1' order by type asc";
                            } else if (string.equals("statdx")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select text  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1 OR type:3)' order by type asc";
                            } else if (string.equals("martindale")) {
                                compressHelper = this.f76989q4;
                                str2 = "Select distinct(text)  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1)' ";
                            } else if (!string.equals("facts")) {
                                if (string.equals("micromedex-drug")) {
                                    compressHelper2 = this.f76989q4;
                                    str3 = "Select text, content as subText, type, contentId from Search where search match 'text:" + replace + " NOT type:5'";
                                } else if (string.equals("micromedex-neofax")) {
                                    compressHelper2 = this.f76989q4;
                                    str3 = "Select text, typeText as subText, typeText, type, contentId from Search where search match 'text:" + replace + " NOT type:5'";
                                } else if (string.equals("sanford")) {
                                    return this.f76989q4.m4952W(bundle, "Select title as text, '' as subText,path as contentId from Search_base where Search_base match 'title:" + replace + " OR subject:" + replace + "'", "fts.db");
                                } else if (string.equals("noskhe")) {
                                    compressHelper = this.f76989q4;
                                    str2 = "Select text  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1)'";
                                } else if (string.equals("stockley")) {
                                    compressHelper = this.f76989q4;
                                    str2 = "Select text  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1)'";
                                } else if (!string.equals("mksap")) {
                                    return null;
                                } else {
                                    compressHelper = this.f76989q4;
                                    str2 = "Select text  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1) AND (typeText:Topic OR typeText:Question)'";
                                }
                                return compressHelper2.m4952W(bundle, str3, "fsearch.sqlite");
                            } else {
                                compressHelper = this.f76989q4;
                                str2 = "Select text  as text,content as subText, typeText, type, contentId from Search where search match '(text:" + replace + "*) AND (type:1)'";
                            }
                        }
                        compressHelper = this.f76989q4;
                        str2 = "Select text, typeText as subText, type, contentId from Search where search match 'text:" + replace + " NOT type:5'";
                    }
                }
                return compressHelper.m4955V(bundle, str2);
            } else if (!new File(CompressHelper.m4945Y0(bundle, "fsearch.db")).exists()) {
                return null;
            } else {
                compressHelper3 = this.f76989q4;
                str4 = "Select id, name as text, type as subText from search where name match '" + replace + "'";
            }
            return compressHelper3.m4952W(bundle, str4, "fsearch.db");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            iMDLogger.m3294f("SearchInDB", "Error in searching " + bundle.getString("Title") + " : " + e.toString());
            return null;
        }
    }
}
