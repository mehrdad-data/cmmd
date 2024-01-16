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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.util.ArrayList;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.ViewHolders.ContentSearchAdapter;
import net.imedicaldoctor.imd.Views.ButtonSmall;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SearchDialogHelperFragment extends DialogFragment {

    /* renamed from: g4 */
    public int f75165g4;

    /* renamed from: h4 */
    public String f75166h4;

    /* renamed from: i4 */
    public ListView f75167i4;

    /* renamed from: j4 */
    public Bundle f75168j4;

    /* renamed from: k4 */
    public boolean f75169k4;

    /* renamed from: l4 */
    public CompressHelper f75170l4;

    /* renamed from: m4 */
    public RecyclerView.Adapter f75171m4;

    /* renamed from: n4 */
    public ContentSearchAdapter f75172n4;

    /* renamed from: o4 */
    public ArrayList<Bundle> f75173o4;

    /* renamed from: p4 */
    public ArrayList<Bundle> f75174p4;

    /* renamed from: q4 */
    public ArrayList<Bundle> f75175q4;

    /* renamed from: r4 */
    public ImageButton f75176r4;

    /* renamed from: s4 */
    public View f75177s4;

    /* renamed from: t4 */
    public Toolbar f75178t4;

    /* renamed from: u4 */
    public SearchView f75179u4;

    /* renamed from: v4 */
    public ImageView f75180v4;

    /* renamed from: w4 */
    public TextView f75181w4;

    /* renamed from: x4 */
    public ButtonSmall f75182x4;

    /* renamed from: y4 */
    public RecyclerView f75183y4;

    /* loaded from: classes2.dex */
    public class ContentSearchViewHolder {

        /* renamed from: a */
        public final TextView f75206a;

        /* renamed from: b */
        public final TextView f75207b;

        public ContentSearchViewHolder(View view) {
            this.f75206a = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f75207b = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
        }
    }

    /* renamed from: A3 */
    public void mo4362A3(String str) {
        TextView textView = (TextView) this.f75177s4.findViewById(C4804R.C4808id.f87029status_label);
        ((ListView) this.f75177s4.findViewById(C4804R.C4808id.f86950list_view)).setVisibility(8);
        textView.setVisibility(0);
        ((LinearLayout) this.f75177s4.findViewById(C4804R.C4808id.f87030status_layout)).setVisibility(0);
        textView.setText(str);
    }

    /* renamed from: B3 */
    public ArrayList<Bundle> m4361B3(String str) {
        return null;
    }

    /* renamed from: C3 */
    public String m4360C3() {
        return this.f75168j4.getString("Title");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f75170l4 = new CompressHelper(m44716w());
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75165g4 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75166h4 = bundle.getString("Query");
        }
        this.f75167i4 = (ListView) this.f75177s4.findViewById(C4804R.C4808id.f86950list_view);
        this.f75168j4 = (m44859B() == null || !m44859B().containsKey("DB")) ? null : m44859B().getBundle("DB");
        try {
            m4346w3("");
        } catch (Exception unused) {
        }
        LinearLayout linearLayout = (LinearLayout) this.f75177s4.findViewById(C4804R.C4808id.f87030status_layout);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchDialogHelperFragment.this.m4352q3();
                }
            });
        }
        return super.mo3277U0(layoutInflater, viewGroup, bundle);
    }

    /* renamed from: j3 */
    public void mo4359j3() {
        this.f75183y4.setItemAnimator(new DefaultItemAnimator());
        this.f75183y4.m42923n(new DividerItemDecoration(m44716w(), 1));
        this.f75183y4.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
    }

    /* renamed from: k3 */
    public void m4358k3() {
        SearchView searchView = (SearchView) this.f75177s4.findViewById(C4804R.C4808id.f87012search_view);
        this.f75179u4 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75179u4.setIconifiedByDefault(false);
        this.f75179u4.setQueryHint("Search All");
        m4349t3();
        this.f75179u4.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.9
            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: a */
            public boolean mo3524a(int i) {
                Cursor mo45341b = SearchDialogHelperFragment.this.f75179u4.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchDialogHelperFragment.this.f75179u4.getTag(1) != null && ((String) SearchDialogHelperFragment.this.f75179u4.getTag(1)).length() > 0) {
                        string = ((String) SearchDialogHelperFragment.this.f75179u4.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchDialogHelperFragment.this.f75179u4.m51655i0(string, true);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: b */
            public boolean mo3523b(int i) {
                Cursor mo45341b = SearchDialogHelperFragment.this.f75179u4.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (SearchDialogHelperFragment.this.f75179u4.getTag() != null && ((String) SearchDialogHelperFragment.this.f75179u4.getTag()).length() > 0) {
                        string = ((String) SearchDialogHelperFragment.this.f75179u4.getTag()) + StringUtils.SPACE + string;
                    }
                    SearchDialogHelperFragment.this.f75179u4.m51655i0(string, true);
                    return false;
                }
                return false;
            }
        });
        ((ImageView) this.f75179u4.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchDialogHelperFragment.this.f75179u4.m51655i0("", false);
                SearchDialogHelperFragment.this.f75179u4.clearFocus();
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                searchDialogHelperFragment.f75183y4.setAdapter(searchDialogHelperFragment.f75171m4);
                SearchDialogHelperFragment.this.m4352q3();
                SearchDialogHelperFragment.this.m4348u3();
            }
        });
        this.f75179u4.setSuggestionsAdapter(new CursorAdapter(m44716w(), null, 0) { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.11
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
        this.f75179u4.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.12
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                if (searchDialogHelperFragment.f75169k4) {
                    searchDialogHelperFragment.f75166h4 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.12.2
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                String[] split;
                                String str2 = str.trim().split(StringUtils.SPACE)[split.length - 1];
                                String str3 = "";
                                for (int i = 0; i < split.length - 1; i++) {
                                    str3 = str3 + StringUtils.SPACE + split[i];
                                }
                                str3.trim();
                                return SearchDialogHelperFragment.this.m4361B3(str2);
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchDialogHelperFragment.this.f75179u4.getSuggestionsAdapter().mo45336l(SearchDialogHelperFragment.this.f75170l4.m4912h((ArrayList) obj));
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
                new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.12.1
                    @Override // android.os.AsyncTask
                    protected Object doInBackground(Object[] objArr) {
                        SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                        searchDialogHelperFragment.f75174p4 = searchDialogHelperFragment.m4347v3(str);
                        return null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPostExecute(Object obj) {
                        SearchDialogHelperFragment.this.m4350s3();
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                return false;
            }
        });
    }

    /* renamed from: l3 */
    public void m4357l3() {
        SearchView searchView = (SearchView) this.f75177s4.findViewById(C4804R.C4808id.f87012search_view);
        this.f75179u4 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75179u4.setIconifiedByDefault(false);
        this.f75179u4.setQueryHint("Search");
        m4349t3();
        ((ImageView) this.f75179u4.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchDialogHelperFragment.this.f75179u4.m51655i0("", false);
                SearchDialogHelperFragment.this.f75179u4.clearFocus();
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                searchDialogHelperFragment.f75183y4.setAdapter(searchDialogHelperFragment.f75171m4);
                SearchDialogHelperFragment.this.m4352q3();
                SearchDialogHelperFragment.this.m4348u3();
            }
        });
        this.f75179u4.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.8
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                if (searchDialogHelperFragment.f75169k4) {
                    searchDialogHelperFragment.f75166h4 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.8.1
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                SearchDialogHelperFragment searchDialogHelperFragment2 = SearchDialogHelperFragment.this;
                                searchDialogHelperFragment2.f75174p4 = searchDialogHelperFragment2.m4347v3(str);
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchDialogHelperFragment.this.m4350s3();
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }

    /* renamed from: m3 */
    public void m4356m3() {
        SearchView searchView = (SearchView) this.f75177s4.findViewById(C4804R.C4808id.f87012search_view);
        this.f75179u4 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        this.f75179u4.setIconifiedByDefault(false);
        this.f75179u4.setQueryHint("Search");
        m4349t3();
        ((ImageView) this.f75179u4.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchDialogHelperFragment.this.f75179u4.m51655i0("", false);
                SearchDialogHelperFragment.this.f75179u4.clearFocus();
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                searchDialogHelperFragment.f75183y4.setAdapter(searchDialogHelperFragment.f75171m4);
                SearchDialogHelperFragment.this.m4352q3();
                SearchDialogHelperFragment.this.m4348u3();
            }
        });
        this.f75179u4.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.6
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str) {
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                if (searchDialogHelperFragment.f75169k4) {
                    searchDialogHelperFragment.f75166h4 = str;
                    if (str.length() > 1) {
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.6.1
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                SearchDialogHelperFragment searchDialogHelperFragment2 = SearchDialogHelperFragment.this;
                                searchDialogHelperFragment2.f75174p4 = searchDialogHelperFragment2.m4347v3(str);
                                SearchDialogHelperFragment searchDialogHelperFragment3 = SearchDialogHelperFragment.this;
                                searchDialogHelperFragment3.f75175q4 = searchDialogHelperFragment3.m4361B3(str);
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                SearchDialogHelperFragment.this.m4350s3();
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

    /* renamed from: n3 */
    public void m4355n3() {
        Toolbar toolbar = (Toolbar) this.f75177s4.findViewById(C4804R.C4808id.f87063toolbar);
        this.f75178t4 = toolbar;
        if (toolbar == null) {
            return;
        }
        this.f75182x4 = (ButtonSmall) this.f75177s4.findViewById(C4804R.C4808id.f86802back_button);
        this.f75178t4.setNavigationOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchDialogHelperFragment.this.f75170l4.m4998G1(true);
            }
        });
        ButtonSmall buttonSmall = this.f75182x4;
        if (buttonSmall != null) {
            buttonSmall.setDrawableIcon(m44716w().getResources().getDrawable(C4804R.C4807drawable.f86482back_icon));
            this.f75182x4.setRippleColor(m44716w().getResources().getColor(C4804R.C4806color.f86409toolbar_item_ripple_color));
            this.f75182x4.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchDialogHelperFragment.this.f75170l4.m4998G1(true);
                }
            });
            this.f75182x4.setOnLongClickListener(new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.3
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    SearchDialogHelperFragment.this.f75170l4.m4989J1(true);
                    return true;
                }
            });
        }
        ImageButton imageButton = (ImageButton) this.f75177s4.findViewById(C4804R.C4808id.f86775action_home);
        this.f75176r4 = imageButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SearchDialogHelperFragment.this.f75170l4.m4989J1(true);
                }
            });
        }
        this.f75180v4 = (ImageView) this.f75177s4.findViewById(C4804R.C4808id.f87064toolbar_image_view);
        TextView textView = (TextView) this.f75177s4.findViewById(C4804R.C4808id.f87066toolbar_text_view);
        this.f75181w4 = textView;
        if (textView != null) {
            this.f75181w4.setTypeface(Typeface.createFromAsset(m44716w().getAssets(), "fonts/HelveticaNeue-Light.otf"));
            this.f75181w4.setText(m4360C3());
        }
        if (this.f75180v4 != null) {
            m4345x3();
        }
        m4344y3();
    }

    /* renamed from: o3 */
    public String[] m4354o3(String str) {
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

    /* renamed from: p3 */
    public int m4353p3() {
        int identifier = m44782a0().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return m44782a0().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: q3 */
    public void m4352q3() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) m44716w().getSystemService("input_method");
            if (m44716w().getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(m44716w().getCurrentFocus().getWindowToken(), 0);
            }
            SearchView searchView = this.f75179u4;
            if (searchView != null) {
                searchView.clearFocus();
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: r3 */
    public void m4351r3(Bundle bundle) {
        this.f75170l4 = new CompressHelper(m44716w());
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75165g4 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75166h4 = bundle.getString("Query");
        }
        this.f75168j4 = (m44859B() == null || !m44859B().containsKey("DB")) ? null : m44859B().getBundle("DB");
    }

    /* renamed from: s3 */
    public void m4350s3() {
        this.f75172n4.m3396f0(this.f75174p4);
        this.f75183y4.setAdapter(this.f75172n4);
    }

    /* renamed from: t3 */
    public void m4349t3() {
        this.f75179u4.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.SearchDialogHelperFragment.14
            @Override // java.lang.Runnable
            public void run() {
                SearchDialogHelperFragment searchDialogHelperFragment = SearchDialogHelperFragment.this;
                searchDialogHelperFragment.f75169k4 = true;
                searchDialogHelperFragment.f75179u4.m51655i0(searchDialogHelperFragment.f75166h4, false);
                SearchDialogHelperFragment.this.m4352q3();
            }
        }, 10L);
        this.f75169k4 = false;
    }

    /* renamed from: u3 */
    public void m4348u3() {
    }

    /* renamed from: v3 */
    public ArrayList<Bundle> m4347v3(String str) {
        return null;
    }

    /* renamed from: w3 */
    public void m4346w3(String str) {
        try {
            ((AppCompatActivity) m44716w()).m52860W().mo52658A0(str);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: x3 */
    public void m4345x3() {
        Glide.m40316F(this).mo40145t(CompressHelper.m5012C(this.f75168j4)).mo39038a(new RequestOptions().m39082B()).m40191t2(this.f75180v4);
    }

    /* renamed from: y3 */
    public void m4344y3() {
        if (PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("HideStatusBar", false)) {
            float dimension = m44782a0().getDimension(C4804R.dimen.f86462toolbar_padding);
            Toolbar toolbar = this.f75178t4;
            if (toolbar != null) {
                toolbar.setPadding(0, (int) dimension, 0, 0);
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.f75177s4.findViewById(C4804R.C4808id.f86847collapsing_toolbar);
            }
        }
    }

    /* renamed from: z3 */
    public void mo4343z3() {
        ((ListView) this.f75177s4.findViewById(C4804R.C4808id.f86950list_view)).setVisibility(0);
        ((TextView) this.f75177s4.findViewById(C4804R.C4808id.f87029status_label)).setVisibility(8);
        ((LinearLayout) this.f75177s4.findViewById(C4804R.C4808id.f87030status_layout)).setVisibility(8);
    }
}
