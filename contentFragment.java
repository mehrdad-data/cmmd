package net.imedicaldoctor.imd.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
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
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.itextpdf.tool.xml.html.HTML;
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
import net.imedicaldoctor.imd.Fragments.Skyscape.SSSearchActivity;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.ViewHolders.StatusAdapter;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class contentFragment extends SearchHelperFragment {

    /* renamed from: b4 */
    private ArrayList<Bundle> f76382b4;

    /* renamed from: c4 */
    private SearchAdapter f76383c4;

    /* renamed from: d4 */
    private ProgressBar f76384d4;

    /* renamed from: e4 */
    private MenuItem f76385e4;

    /* renamed from: f4 */
    private AsyncTask f76386f4;

    /* renamed from: g4 */
    private boolean f76387g4;

    /* renamed from: h4 */
    private String f76388h4;

    /* renamed from: i4 */
    private Observable<Bundle> f76389i4;

    /* renamed from: j4 */
    private DisposableObserver<Bundle> f76390j4;

    /* renamed from: k4 */
    private StickyRecyclerHeadersDecoration f76391k4;

    /* renamed from: l4 */
    private ArrayList<String> f76392l4;

    /* renamed from: m4 */
    StickyRecyclerHeadersTouchListener f76393m4;

    /* renamed from: n4 */
    private LinearLayoutManager f76394n4;

    /* renamed from: o4 */
    private RecyclerView f76395o4;

    /* renamed from: p4 */
    private boolean f76396p4;

    /* renamed from: q4 */
    public CompressHelper f76397q4;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: net.imedicaldoctor.imd.Fragments.contentFragment$5 */
    /* loaded from: classes2.dex */
    public class C45685 implements ObservableOnSubscribe<String> {

        /* renamed from: a */
        final /* synthetic */ SearchView f76405a;

        C45685(SearchView searchView) {
            this.f76405a = searchView;
        }

        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
        /* renamed from: a */
        public void mo3518a(@NonNull final ObservableEmitter<String> observableEmitter) throws Throwable {
            this.f76405a.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.5.1

                /* renamed from: a */
                private DisposableObserver<Bundle> f76407a;

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
                    contentFragment.this.f76388h4 = str;
                    contentFragment.this.f75210G3 = str;
                    DisposableObserver<Bundle> disposableObserver = this.f76407a;
                    if (disposableObserver != null) {
                        disposableObserver.onComplete();
                    }
                    observableEmitter.onNext("SoheilvbSoheilvbSoheilvb");
                    contentFragment.this.f76397q4.m4994I(str, "SearchContentAll");
                    iMDLogger.m3290j("OnQueryTextSubmit", "Building search observable");
                    Observable m7605B4 = Observable.m7156x1(new ObservableOnSubscribe<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.5.1.1
                        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                        /* renamed from: a */
                        public void mo3518a(@NonNull ObservableEmitter<Bundle> observableEmitter2) throws Throwable {
                            ArrayList<Bundle> arrayList;
                            ArrayList arrayList2 = new ArrayList();
                            Iterator<Bundle> it2 = CompressHelper.f73778s.iterator();
                            while (it2.hasNext()) {
                                arrayList2.add(it2.next());
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("Path", contentFragment.this.f76397q4.m4856z1());
                            bundle.putString("Name", "highlights.db");
                            bundle.putString("Title", "Highlights");
                            bundle.putString("Type", "highlight");
                            bundle.putString("IconName", "");
                            arrayList2.add(0, bundle);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("Path", contentFragment.this.f76397q4.m4856z1());
                            bundle2.putString("Name", "highlights.db");
                            bundle2.putString("Title", "Notes");
                            bundle2.putString("Type", "note");
                            bundle2.putString("IconName", "");
                            arrayList2.add(0, bundle2);
                            Iterator it3 = arrayList2.iterator();
                            while (it3.hasNext()) {
                                Bundle bundle3 = (Bundle) it3.next();
                                try {
                                    arrayList = contentFragment.this.m3853n3(bundle3, str);
                                } catch (Exception e) {
                                    FirebaseCrashlytics.m18030d().m18027g(e);
                                    iMDLogger.m3294f("ContentSearch", "Error in querying " + bundle3.getString("Name"));
                                    arrayList = null;
                                }
                                if (arrayList != null && arrayList.size() != 0) {
                                    iMDLogger.m3290j("Search", "Result from " + bundle3.getString("Title") + " : " + arrayList.size());
                                    Bundle bundle4 = new Bundle();
                                    bundle4.putBundle("database", bundle3);
                                    bundle4.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList);
                                    observableEmitter2.onNext(bundle4);
                                }
                            }
                            observableEmitter2.onComplete();
                        }
                    }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7605B4(new Function<Throwable, Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.5.1.2
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: a */
                        public Bundle apply(Throwable th) throws Throwable {
                            return null;
                        }
                    });
                    contentFragment.this.f76389i4 = m7605B4;
                    DisposableObserver<Bundle> disposableObserver2 = new DisposableObserver<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.5.1.3
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // io.reactivex.rxjava3.observers.DisposableObserver
                        /* renamed from: a */
                        public void mo3516a() {
                            super.mo3516a();
                            contentFragment.this.f76385e4.setVisible(true);
                            contentFragment.this.f76384d4.setIndeterminate(true);
                            iMDLogger.m3290j("SearchSubscriber", "On Start");
                            contentFragment.this.f76382b4 = new ArrayList();
                            contentFragment.this.m3857F3();
                            contentFragment.this.m4330Y2();
                            C45685.this.f76405a.getSuggestionsAdapter().mo45336l(null);
                            contentFragment.this.mo3542i3("Searching");
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        /* renamed from: b */
                        public void onNext(@NonNull Bundle bundle) {
                            contentFragment.this.mo3543h3();
                            contentFragment.this.f76382b4.add(bundle);
                            contentFragment.this.f76395o4.getAdapter().m42860G();
                            StringBuilder sb = new StringBuilder();
                            sb.append("On Next - ");
                            sb.append(bundle.getBundle("database").getString("Title"));
                            sb.append(" - ");
                            contentFragment contentfragment = contentFragment.this;
                            sb.append(contentfragment.m3856G3(contentfragment.f76382b4));
                            sb.append(" - IN Thread :");
                            sb.append(Thread.currentThread().toString());
                            iMDLogger.m3290j("SearchSubscriber", sb.toString());
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        public void onComplete() {
                            contentFragment.this.f76385e4.setVisible(false);
                            iMDLogger.m3290j("SearchSubscriber", "On Complete");
                            if (contentFragment.this.f76382b4 == null || contentFragment.this.f76382b4.size() == 0) {
                                contentFragment.this.mo3542i3("Nothing Found");
                            }
                            if (PreferenceManager.getDefaultSharedPreferences(contentFragment.this.m44716w()).getBoolean("ContentCollapsed", false)) {
                                contentFragment.this.m3861B3();
                            }
                        }

                        @Override // io.reactivex.rxjava3.core.Observer
                        public void onError(@NonNull Throwable th) {
                            contentFragment.this.f76385e4.setVisible(false);
                            iMDLogger.m3290j("SearchSubscriber", "On Error");
                            iMDLogger.m3294f("onQueryTextSubmit", "Some error on SearchObservable");
                        }
                    };
                    this.f76407a = disposableObserver2;
                    contentFragment.this.f76390j4 = disposableObserver2;
                    m7605B4.mo6065a(this.f76407a);
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
        private Context f76420d;

        public SearchAdapter(Context context) {
            this.f76420d = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            contentFragment contentfragment = contentFragment.this;
            return contentfragment.m3854m3(i, contentfragment.f76382b4).containsKey("Item") ? 0 : 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            if (viewHolder.m42556F() == 1) {
                return;
            }
            SearchItemViewHolder searchItemViewHolder = (SearchItemViewHolder) viewHolder;
            contentFragment contentfragment = contentFragment.this;
            Bundle bundle = contentfragment.m3854m3(i, contentfragment.f76382b4).getBundle("Item");
            searchItemViewHolder.f76428I.setText(bundle.getString("text"));
            if (bundle.containsKey("subText") && bundle.getString("subText").length() == 0) {
                searchItemViewHolder.f76429J.setText((CharSequence) null);
                searchItemViewHolder.f76429J.setVisibility(8);
            } else {
                searchItemViewHolder.f76429J.setVisibility(0);
                searchItemViewHolder.f76429J.setText(Html.fromHtml(bundle.getString("subText")));
            }
            searchItemViewHolder.f76430K.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.SearchAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String string;
                    String str;
                    Bundle m4926d1;
                    String string2;
                    String str2;
                    String str3;
                    String string3;
                    CompressHelper compressHelper;
                    Bundle bundle2;
                    StringBuilder sb;
                    String str4;
                    CompressHelper compressHelper2;
                    Bundle bundle3;
                    String string4;
                    CompressHelper compressHelper3;
                    Bundle bundle4;
                    StringBuilder sb2;
                    String string5;
                    StringBuilder sb3;
                    String string6;
                    contentFragment contentfragment2 = contentFragment.this;
                    Bundle m3854m3 = contentfragment2.m3854m3(i, contentfragment2.f76382b4);
                    if (m3854m3 == null) {
                        return;
                    }
                    Bundle bundle5 = m3854m3.getBundle("Database");
                    Bundle bundle6 = m3854m3.getBundle("Item");
                    String string7 = bundle5.getString("Type");
                    String string8 = bundle6.getString("id");
                    contentFragment contentfragment3 = contentFragment.this;
                    contentfragment3.f75212I3 = bundle5;
                    String[] m4332W2 = contentfragment3.m4332W2(bundle6.getString("subText"));
                    if (string7.equals("lexi")) {
                        contentFragment.this.f76397q4.m4883q1(bundle5, string8, m4332W2, null);
                    } else if (string7.equals("skyscape")) {
                        if (!string8.contains("|")) {
                            contentFragment.this.f76397q4.m4883q1(bundle5, string8, m4332W2, bundle6.getString(HTML.Tag.f65890V));
                            return;
                        }
                        Bundle bundle7 = new Bundle();
                        Bundle bundle8 = new Bundle();
                        bundle8.putString("docId", string8);
                        bundle8.putString("name", bundle6.getString("text"));
                        bundle8.putString(HTML.Tag.f65890V, bundle6.getString(HTML.Tag.f65890V));
                        bundle7.putBundle("SelectedItem", bundle8);
                        bundle7.putBundle("DB", bundle5);
                        bundle7.putInt("Mode", 2);
                        bundle7.putBundle("GotoSections", contentFragment.this.m3860C3(bundle8));
                        contentFragment.this.f76397q4.m4979N(SSSearchActivity.class, SSSearchActivity.SSSearchFragment.class, bundle7);
                    } else {
                        if (!string7.equals("medhand")) {
                            if (string7.equals("visualdx")) {
                                String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(string8, "-");
                                Bundle bundle9 = new Bundle();
                                bundle9.putInt("defaultModule", Integer.valueOf(splitByWholeSeparator[1]).intValue());
                                contentFragment.this.f76397q4.m4880r1(bundle5, splitByWholeSeparator[0], m4332W2, null, bundle9);
                                return;
                            } else if (!string7.equals("uptodate")) {
                                if (string7.equals("accessmedicine")) {
                                    String string9 = bundle6.getString("type");
                                    string = bundle6.getString("contentId");
                                    if (string9.equals(ExifInterface.f14403S4)) {
                                        CompressHelper compressHelper4 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper4.m4926d1(compressHelper4.m4955V(bundle5, "select * from videos where id=" + string));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                    } else if (string9.equals(ExifInterface.f14411T4)) {
                                        CompressHelper compressHelper5 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper5.m4926d1(compressHelper5.m4955V(bundle5, "select * from images where id=" + string));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                    } else if (!string9.equals("4")) {
                                        str = null;
                                        if (!string9.equals("5")) {
                                            return;
                                        }
                                        contentFragment.this.f76397q4.m4883q1(bundle5, string, m4332W2, str);
                                        return;
                                    } else {
                                        CompressHelper compressHelper6 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper6.m4926d1(compressHelper6.m4955V(bundle5, "select * from tables where id=" + string));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                    }
                                    string2 = m4926d1.getString("sectionId");
                                    contentFragment.this.f76397q4.m4883q1(bundle5, string2, null, m4926d1.getString("goto"));
                                    return;
                                }
                                if (string7.equals("lww")) {
                                    String string10 = bundle6.getString("type");
                                    string6 = bundle6.getString("contentId");
                                    if (string10.equals(ExifInterface.f14403S4) || string10.equals(ExifInterface.f14411T4) || string10.equals("4")) {
                                        contentFragment.this.f76397q4.m4883q1(bundle5, string6, null, null);
                                        return;
                                    } else if (!string10.equals("5")) {
                                        return;
                                    }
                                } else if (string7.equals("elsevier") || string7.equals("elseviernew")) {
                                    String string11 = bundle6.getString("type");
                                    string = bundle6.getString("contentId");
                                    if (string11.equals(ExifInterface.f14411T4)) {
                                        CompressHelper compressHelper7 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper7.m4907i1(compressHelper7.m4955V(bundle5, "select * from images where id='" + string + "'"));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                        string2 = m4926d1.getString("docId");
                                    } else if (!string11.equals("4")) {
                                        str = null;
                                        if (!string11.equals("5")) {
                                            return;
                                        }
                                        contentFragment.this.f76397q4.m4883q1(bundle5, string, m4332W2, str);
                                        return;
                                    } else {
                                        CompressHelper compressHelper8 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper8.m4926d1(compressHelper8.m4955V(bundle5, "select * from tables where id=" + string));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                        string2 = m4926d1.getString("sectionId");
                                    }
                                    contentFragment.this.f76397q4.m4883q1(bundle5, string2, null, m4926d1.getString("goto"));
                                    return;
                                } else if (string7.equals("ovid")) {
                                    String string12 = bundle6.getString("type");
                                    string = bundle6.getString("contentId");
                                    if (string12.equals(ExifInterface.f14403S4)) {
                                        return;
                                    }
                                    if (string12.equals(ExifInterface.f14411T4)) {
                                        CompressHelper compressHelper9 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper9.m4926d1(compressHelper9.m4955V(bundle5, "select * from images where imagename='" + string + "'"));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                    } else if (!string12.equals("4")) {
                                        str = null;
                                        if (!string12.equals("5")) {
                                            return;
                                        }
                                        contentFragment.this.f76397q4.m4883q1(bundle5, string, m4332W2, str);
                                        return;
                                    } else {
                                        CompressHelper compressHelper10 = contentFragment.this.f76397q4;
                                        m4926d1 = compressHelper10.m4926d1(compressHelper10.m4955V(bundle5, "select * from tables where id=" + string));
                                        if (m4926d1 == null) {
                                            return;
                                        }
                                    }
                                    string2 = m4926d1.getString("bookId");
                                    contentFragment.this.f76397q4.m4883q1(bundle5, string2, null, m4926d1.getString("goto"));
                                    return;
                                } else if (string7.equals("epub")) {
                                    String string13 = bundle6.getString("type");
                                    string6 = bundle6.getString("contentId");
                                    if (!string13.equals("5")) {
                                        return;
                                    }
                                } else if (!string7.equals("nejm")) {
                                    if (string7.equals("epocrate")) {
                                        String string14 = bundle6.getString("typeText");
                                        string5 = bundle6.getString("contentId");
                                        if (string14.equals("Dx")) {
                                            contentFragment contentfragment4 = contentFragment.this;
                                            compressHelper2 = contentfragment4.f76397q4;
                                            bundle3 = contentfragment4.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "dx-";
                                        } else if (string14.equals("Rx")) {
                                            contentFragment contentfragment5 = contentFragment.this;
                                            compressHelper2 = contentfragment5.f76397q4;
                                            bundle3 = contentfragment5.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "rx-";
                                        } else if (string14.equals("ID")) {
                                            contentFragment contentfragment6 = contentFragment.this;
                                            compressHelper2 = contentfragment6.f76397q4;
                                            bundle3 = contentfragment6.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "id-";
                                        } else if (string14.equals("Lab")) {
                                            contentFragment contentfragment7 = contentFragment.this;
                                            compressHelper2 = contentfragment7.f76397q4;
                                            bundle3 = contentfragment7.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "lab-";
                                        } else if (string14.equals("Guideline")) {
                                            contentFragment contentfragment8 = contentFragment.this;
                                            compressHelper2 = contentfragment8.f76397q4;
                                            bundle3 = contentfragment8.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "guideline-";
                                        } else if (!string14.equals("Table")) {
                                            return;
                                        } else {
                                            contentFragment contentfragment9 = contentFragment.this;
                                            compressHelper2 = contentfragment9.f76397q4;
                                            bundle3 = contentfragment9.f75212I3;
                                            sb3 = new StringBuilder();
                                            str2 = "table-";
                                        }
                                    } else {
                                        str2 = "doc,,,";
                                        if (!string7.equals("amirsys")) {
                                            if (string7.equals("statdx")) {
                                                String string15 = bundle6.getString("contentId");
                                                String string16 = bundle6.getString("type");
                                                if (string16.equals(ExifInterface.f14411T4) || string16.equals("6")) {
                                                    sb2 = new StringBuilder();
                                                    sb2.append("case,,,");
                                                } else {
                                                    sb2 = new StringBuilder();
                                                    sb2.append("doc,,,");
                                                }
                                                sb2.append(string15);
                                                string4 = sb2.toString();
                                                contentFragment contentfragment10 = contentFragment.this;
                                                compressHelper3 = contentfragment10.f76397q4;
                                                bundle4 = contentfragment10.f75212I3;
                                                str3 = null;
                                            } else {
                                                str3 = null;
                                                if (!string7.equals("martindale")) {
                                                    if (string7.equals("sanford")) {
                                                        string3 = contentFragment.this.f76397q4.m4904j1(StringUtils.splitByWholeSeparator(bundle6.getString("contentId"), "/")) + ".html";
                                                    } else if (string7.equals("micromedex-neofax")) {
                                                        String string17 = bundle6.getString("contentId");
                                                        if (bundle6.getString("typeText").equals("Drug")) {
                                                            contentFragment contentfragment11 = contentFragment.this;
                                                            compressHelper = contentfragment11.f76397q4;
                                                            bundle2 = contentfragment11.f75212I3;
                                                            sb = new StringBuilder();
                                                            str4 = "drug-";
                                                        } else {
                                                            contentFragment contentfragment12 = contentFragment.this;
                                                            compressHelper = contentfragment12.f76397q4;
                                                            bundle2 = contentfragment12.f75212I3;
                                                            sb = new StringBuilder();
                                                            str4 = "formula-";
                                                        }
                                                        sb.append(str4);
                                                        sb.append(string17);
                                                        compressHelper.m4883q1(bundle2, sb.toString(), m4332W2, null);
                                                        return;
                                                    } else if (string7.equals("mksap")) {
                                                        String string18 = bundle6.getString("contentId");
                                                        String[] splitByWholeSeparator2 = StringUtils.splitByWholeSeparator(string18, "_");
                                                        string3 = "groups/" + splitByWholeSeparator2[2] + "/" + bundle6.getString("typeText").toLowerCase() + "s/" + string18;
                                                    } else if (string7.equals("highlight") || string7.equals("note")) {
                                                        contentFragment.this.m3859D3(bundle6);
                                                        return;
                                                    } else {
                                                        string3 = bundle6.getString("contentId");
                                                    }
                                                    contentFragment contentfragment13 = contentFragment.this;
                                                    compressHelper2 = contentfragment13.f76397q4;
                                                    bundle3 = contentfragment13.f75212I3;
                                                    compressHelper2.m4883q1(bundle3, string3, m4332W2, null);
                                                    return;
                                                }
                                                string4 = bundle6.getString("contentId");
                                                contentFragment contentfragment14 = contentFragment.this;
                                                compressHelper3 = contentfragment14.f76397q4;
                                                bundle4 = contentfragment14.f75212I3;
                                            }
                                            compressHelper3.m4883q1(bundle4, string4, m4332W2, str3);
                                            return;
                                        }
                                        string5 = bundle6.getString("contentId");
                                        contentFragment contentfragment15 = contentFragment.this;
                                        compressHelper2 = contentfragment15.f76397q4;
                                        bundle3 = contentfragment15.f75212I3;
                                        sb3 = new StringBuilder();
                                    }
                                    sb3.append(str2);
                                    sb3.append(string5);
                                    string3 = sb3.toString();
                                    compressHelper2.m4883q1(bundle3, string3, m4332W2, null);
                                    return;
                                } else {
                                    String string19 = bundle6.getString("type");
                                    string6 = bundle6.getString("contentId");
                                    if (!string19.equals("5")) {
                                        return;
                                    }
                                }
                                contentFragment.this.f76397q4.m4883q1(bundle5, string6, m4332W2, null);
                                return;
                            }
                        }
                        contentFragment.this.f76397q4.m4883q1(bundle5, bundle6.getString("URL"), m4332W2, null);
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new EmptyViewHolder(LayoutInflater.from(this.f76420d).inflate(C4804R.C4810layout.f87245list_view_item_header_keeper, viewGroup, false));
            }
            View inflate = LayoutInflater.from(this.f76420d).inflate(C4804R.C4810layout.f87277list_view_item_search_ripple, viewGroup, false);
            contentFragment contentfragment = contentFragment.this;
            return new SearchItemViewHolder(contentfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: n */
        public RecyclerView.ViewHolder mo3366n(ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.f76420d).inflate(C4804R.C4810layout.f87276list_view_item_search_header, viewGroup, false);
            contentFragment contentfragment = contentFragment.this;
            return new SearchHeaderViewHolder(contentfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: o */
        public void mo3365o(RecyclerView.ViewHolder viewHolder, int i) {
            SearchHeaderViewHolder searchHeaderViewHolder = (SearchHeaderViewHolder) viewHolder;
            if (contentFragment.this.f76382b4 == null) {
                return;
            }
            contentFragment contentfragment = contentFragment.this;
            Bundle bundle = contentfragment.m3854m3(i, contentfragment.f76382b4).getBundle("Database");
            searchHeaderViewHolder.f76425J.setText(bundle.getString("Title"));
            String m5012C = CompressHelper.m5012C(bundle);
            if (!m5012C.contains("file:///android_asset/")) {
                searchHeaderViewHolder.f76424I.setImageURI(Uri.parse(m5012C));
                return;
            }
            try {
                InputStream open = contentFragment.this.m44716w().getAssets().open(m5012C.replace("file:///android_asset/", ""));
                searchHeaderViewHolder.f76424I.setImageBitmap(BitmapFactory.decodeStream(open));
                open.close();
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                e.printStackTrace();
            }
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: q */
        public long mo3364q(int i) {
            contentFragment contentfragment = contentFragment.this;
            return Long.valueOf(contentfragment.m3855l3(i, contentfragment.f76382b4)).longValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            contentFragment contentfragment = contentFragment.this;
            return contentfragment.m3856G3(contentfragment.f76382b4);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchHeaderViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public ImageView f76424I;

        /* renamed from: J */
        public TextView f76425J;

        /* renamed from: K */
        public ImageView f76426K;

        public SearchHeaderViewHolder(Context context, View view) {
            super(view);
            this.f76425J = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76424I = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76426K = (ImageView) view.findViewById(C4804R.C4808id.icon);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76428I;

        /* renamed from: J */
        public TextView f76429J;

        /* renamed from: K */
        public MaterialRippleLayout f76430K;

        public SearchItemViewHolder(Context context, View view) {
            super(view);
            this.f76428I = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f76429J = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
            this.f76430K = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C3 */
    public Bundle m3860C3(Bundle bundle) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D3 */
    public void m3859D3(Bundle bundle) {
        Bundle m4969Q0 = this.f76397q4.m4969Q0("Name", bundle.getString("dbName"));
        if (m4969Q0 == null) {
            CompressHelper.m4921e2(m44716w(), "Database not found", 1);
            return;
        }
        String string = m4969Q0.getString("Type");
        String string2 = bundle.getString("dbAddress");
        String string3 = bundle.getString("save");
        Bundle bundle2 = new Bundle();
        bundle2.putString("gotoHighlight", string3);
        if (!string.equals("lexi") && !string.equals("nejm") && !string.equals("skyscape") && !string.equals("medhand") && !string.equals("irandarou") && !string.equals("uptodateddx") && !string.equals("visualdx") && !string.equals("uptodate") && !string.equals("accessmedicine") && !string.equals("lww") && !string.equals("elsevier") && !string.equals("elseviernew") && !string.equals("ovid") && !string.equals("epub")) {
            string.equals("epocrate");
        }
        this.f76397q4.m4880r1(m4969Q0, string2, null, null, bundle2);
        m4330Y2();
    }

    /* renamed from: A3 */
    public void m3862A3() {
        if (this.f76396p4) {
            return;
        }
        this.f76395o4.m42923n(this.f76391k4);
        this.f76396p4 = true;
    }

    /* renamed from: B3 */
    public void m3861B3() {
        Bundle bundle;
        this.f76392l4 = new ArrayList<>();
        Iterator<Bundle> it2 = this.f76382b4.iterator();
        while (it2.hasNext()) {
            Bundle next = it2.next();
            if (next != null && (bundle = next.getBundle("database")) != null) {
                this.f76392l4.add(bundle.getString("Name"));
            }
        }
        this.f76395o4.getAdapter().m42860G();
    }

    /* renamed from: E3 */
    public void m3858E3() {
        if (this.f76396p4) {
            this.f76395o4.m42906s1(this.f76391k4);
            this.f76396p4 = false;
        }
    }

    /* renamed from: F3 */
    public void m3857F3() {
        StickyRecyclerHeadersTouchListener stickyRecyclerHeadersTouchListener = new StickyRecyclerHeadersTouchListener(this.f76395o4, this.f76391k4);
        this.f76393m4 = stickyRecyclerHeadersTouchListener;
        stickyRecyclerHeadersTouchListener.m8624h(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.7
            @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener.OnHeaderClickListener
            /* renamed from: a */
            public void mo3512a(View view, int i, long j) {
                String string = ((Bundle) contentFragment.this.f76382b4.get((int) j)).getBundle("database").getString("Name");
                if (contentFragment.this.f76392l4.contains(string)) {
                    contentFragment.this.f76392l4.remove(string);
                } else {
                    contentFragment.this.f76392l4.add(string);
                }
                contentFragment.this.f76395o4.getAdapter().m42860G();
            }
        });
        this.f76395o4.m42914q(this.f76393m4);
        m3862A3();
        this.f76395o4.setAdapter(this.f76383c4);
        this.f76383c4.m42849Z(new RecyclerView.AdapterDataObserver() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.8
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            /* renamed from: a */
            public void mo3511a() {
                contentFragment.this.f76391k4.m8633n();
            }
        });
    }

    /* renamed from: G3 */
    public int m3856G3(ArrayList<Bundle> arrayList) {
        int i = 0;
        if (arrayList == null) {
            return 0;
        }
        Iterator<Bundle> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Bundle next = it2.next();
            i += this.f76392l4.contains(next.getBundle("database").getString("Name")) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
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
        final SearchView searchView = (SearchView) menu.findItem(C4804R.C4808id.f86789action_search).getActionView();
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        MenuItem findItem = menu.findItem(C4804R.C4808id.f86994progress_menu);
        this.f76385e4 = findItem;
        this.f76384d4 = (ProgressBar) findItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Anything");
        final String str = this.f76388h4;
        this.f75223T3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.1
            @Override // java.lang.Runnable
            public void run() {
                contentFragment.this.f75223T3.m51655i0(str, false);
                String str2 = contentFragment.this.f75210G3;
                if (str2 == null || str2.length() <= 0) {
                    return;
                }
                if (contentFragment.this.f76382b4 == null || contentFragment.this.f76382b4.size() == 0) {
                    contentFragment contentfragment = contentFragment.this;
                    contentfragment.f75223T3.m51655i0(contentfragment.f75210G3, true);
                } else {
                    contentFragment contentfragment2 = contentFragment.this;
                    contentfragment2.f75223T3.m51655i0(contentfragment2.f75210G3, false);
                    contentFragment.this.mo3543h3();
                }
                contentFragment.this.m4330Y2();
            }
        }, 10L);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.2
            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: a */
            public boolean mo3524a(int i) {
                Cursor mo45341b = searchView.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (searchView.getTag(1) != null && ((String) searchView.getTag(1)).length() > 0) {
                        string = ((String) searchView.getTag()) + StringUtils.SPACE + string;
                    }
                    searchView.m51655i0(string, true);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnSuggestionListener
            /* renamed from: b */
            public boolean mo3523b(int i) {
                Cursor mo45341b = searchView.getSuggestionsAdapter().mo45341b();
                if (mo45341b.moveToPosition(i)) {
                    String string = mo45341b.getString(mo45341b.getColumnIndex("word"));
                    if (searchView.getTag() != null && ((String) searchView.getTag()).length() > 0) {
                        string = ((String) searchView.getTag()) + StringUtils.SPACE + string;
                    }
                    searchView.m51655i0(string, true);
                    return false;
                }
                return false;
            }
        });
        ((ImageView) searchView.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                searchView.m51655i0("", false);
                searchView.clearFocus();
                contentFragment.this.mo3542i3("Search Anything");
                contentFragment.this.m4330Y2();
            }
        });
        searchView.setSuggestionsAdapter(new CursorAdapter(m44716w(), null, 0) { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.4
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
        Observable.m7156x1(new C45685(searchView)).m7146y1(500L, TimeUnit.MILLISECONDS).mo6065a(new DisposableObserver<String>() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.6
            @Override // io.reactivex.rxjava3.core.Observer
            /* renamed from: b */
            public void onNext(@NonNull String str2) {
                if (str2.equals("SoheilvbSoheilvbSoheilvb")) {
                    searchView.getSuggestionsAdapter().mo45336l(null);
                } else if (str2.length() > 1) {
                    String[] split = str2.trim().split(StringUtils.SPACE);
                    String str3 = split[split.length - 1];
                    String str4 = "";
                    for (int i = 0; i < split.length - 1; i++) {
                        str4 = str4 + StringUtils.SPACE + split[i];
                    }
                    searchView.setTag(str4.trim());
                    CompressHelper compressHelper = contentFragment.this.f76397q4;
                    compressHelper.m4931c0(compressHelper.m5018A(), "Select rowid as _id,word from contentspell where word match '" + str3 + "*'").m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7339e6(new Consumer<ArrayList<Bundle>>() { // from class: net.imedicaldoctor.imd.Fragments.contentFragment.6.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        /* renamed from: a */
                        public void accept(ArrayList<Bundle> arrayList) throws Throwable {
                            searchView.getSuggestionsAdapter().mo45336l(contentFragment.this.f76397q4.m4912h(arrayList));
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
        this.f76392l4 = new ArrayList<>();
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87186fragment_search, viewGroup, false);
        this.f75221R3 = inflate;
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75209F3 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75210G3 = bundle.getString("Query");
        }
        if (bundle != null && bundle.containsKey("mIsSubmitted")) {
            this.f76387g4 = bundle.getBoolean("mIsSubmitted");
        }
        this.f76395o4 = (RecyclerView) this.f75221R3.findViewById(C4804R.C4808id.f87001recycler_view);
        this.f76382b4 = new ArrayList<>();
        if (bundle != null && bundle.containsKey("mResults")) {
            this.f76382b4 = bundle.getParcelableArrayList("mResults");
        }
        this.f76383c4 = new SearchAdapter(m44716w());
        this.f76397q4 = new CompressHelper(m44716w());
        this.f76391k4 = new StickyRecyclerHeadersDecoration(this.f76383c4);
        this.f76395o4.setLayoutManager(new LinearLayoutManager(m44716w()));
        this.f76395o4.setItemAnimator(new DefaultItemAnimator());
        this.f76395o4.m42923n(new DividerItemDecoration(m44716w(), 1));
        m44735q2(true);
        mo3542i3("Search Contents");
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
        RecyclerView.Adapter adapter = this.f76395o4.getAdapter();
        SearchAdapter searchAdapter = this.f76383c4;
        if (adapter == searchAdapter) {
            searchAdapter.m42860G();
            return;
        }
        this.f76391k4.m8633n();
        this.f76395o4.setAdapter(this.f76383c4);
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment
    /* renamed from: i3 */
    public void mo3542i3(String str) {
        try {
            if (!str.equals("Searching")) {
                this.f76391k4.m8633n();
                m3858E3();
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        this.f76395o4.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
        this.f76395o4.setAdapter(new StatusAdapter(m44716w(), str));
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: l1 */
    public void mo3541l1() {
        super.mo3541l1();
        m4330Y2();
    }

    /* renamed from: l3 */
    public int m3855l3(int i, ArrayList<Bundle> arrayList) {
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Bundle bundle = arrayList.get(i3);
            i2 += this.f76392l4.contains(bundle.getBundle("database").getString("Name")) ? 1 : bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
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
    public Bundle m3854m3(int i, ArrayList<Bundle> arrayList) {
        Iterator<Bundle> it2 = arrayList.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            Bundle next = it2.next();
            String string = next.getBundle("database").getString("Name");
            int size = this.f76392l4.contains(string) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            i2 += size;
            if (i < i2) {
                int i3 = i - (i2 - size);
                Bundle bundle = new Bundle();
                bundle.putBundle("Database", next.getBundle("database"));
                if (this.f76392l4.contains(string)) {
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
    public ArrayList<Bundle> m3853n3(Bundle bundle, String str) {
        CompressHelper compressHelper;
        String str2;
        CompressHelper compressHelper2;
        String str3;
        String str4;
        CompressHelper compressHelper3;
        String str5;
        CompressHelper compressHelper4;
        String str6;
        String replace = str.replace("'", "''");
        String string = bundle.getString("Type");
        try {
            if (!string.equals("lexi")) {
                if (string.equals("skyscape")) {
                    compressHelper2 = this.f76397q4;
                    str3 = "Select URL as id, Text as text,snippet(search) as subText  from search where search match '" + replace + "' ORDER BY rank(matchinfo(search)) DESC limit 15";
                    str4 = "fcontentSearch.db";
                } else if (string.equals("medhand")) {
                    compressHelper4 = this.f76397q4;
                    str6 = "Select URL, Text as text,snippet(search) as subText  from search where search match '" + replace + "' ORDER BY rank(matchinfo(search)) DESC limit 15";
                } else if (string.equals("visualdx")) {
                    compressHelper4 = this.f76397q4;
                    str6 = "Select URL as id, Text || ' - ' || SubText as text, snippet(search) as subText  from search where search match '" + replace + "' ORDER BY rank(matchinfo(search)) DESC limit 15";
                } else if (!string.equals("uptodate")) {
                    if (string.equals("accessmedicine")) {
                        compressHelper = this.f76397q4;
                        str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:2 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                    } else if (string.equals("lww")) {
                        compressHelper = this.f76397q4;
                        str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:2 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                    } else if (string.equals("nejm")) {
                        compressHelper = this.f76397q4;
                        str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " AND (type:5)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                    } else {
                        if (!string.equals("elsevier") && !string.equals("elseviernew")) {
                            if (string.equals("ovid")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:2 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                            } else if (string.equals("highlight")) {
                                compressHelper = this.f76397q4;
                                str2 = "select dbTitle || ' - ' || dbDocName as text, snippet(highlight) as subText, dbAddress,dbName, type,note,save from highlight where text match '" + replace + "' ORDER BY rank(matchinfo(highlight)) DESC limit 100";
                            } else if (string.equals("note")) {
                                compressHelper = this.f76397q4;
                                str2 = "select dbTitle || ' - ' || dbDocName as text, snippet(highlight) as subText, dbAddress,dbName, type,note,save from highlight where note match '" + replace + "' ORDER BY rank(matchinfo(highlight)) DESC limit 100";
                            } else if (string.equals("epub")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:0 OR type:1)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                            } else if (string.equals("epocrate")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  contentId,type,text || ' - ' || typeText as text,typeText, snippet(search) as subText  from search where search match '" + replace + " NOT (type:0 OR type:1 OR type:2 OR type:3 OR type:4 OR type:6 OR type:7)' ORDER BY rank(matchinfo(search)) DESC limit 100";
                            } else if (string.equals("amirsys")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  contentId,type, text || ' - ' || typeText as text,typeText, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:2)' ORDER BY rank(matchinfo(search)) DESC limit 100";
                            } else if (string.equals("statdx")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  contentId,type, text,typeText, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:3)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                            } else if (string.equals("martindale")) {
                                compressHelper = this.f76397q4;
                                str2 = "Select  text, contentId,type,typeText, snippet(search) as subText  from search where search match '" + replace + " AND (type:5)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                            } else if (!string.equals("facts")) {
                                if (string.equals("micromedex-drug")) {
                                    compressHelper3 = this.f76397q4;
                                    str5 = "Select  contentId,type, text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                                } else if (string.equals("micromedex-neofax")) {
                                    compressHelper3 = this.f76397q4;
                                    str5 = "Select  contentId,type, text || ' - ' || typeText as text,typeText, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                                } else if (string.equals("sanford")) {
                                    compressHelper2 = this.f76397q4;
                                    str3 = "Select path as contentId, title as text, snippet(search_base) as subText  from search_base where search_base match '" + replace + "' ORDER BY rank(matchinfo(search_base)) DESC limit 15";
                                    str4 = "fts.db";
                                } else if (string.equals("noskhe")) {
                                    compressHelper = this.f76397q4;
                                    str2 = "Select  Text as text, contentId,type,typeText, snippet(search) as subText  from search where search match '" + replace + " AND (type:5)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                                } else if (string.equals("stockley")) {
                                    compressHelper = this.f76397q4;
                                    str2 = "Select  text, contentId,type,typeText, snippet(search) as subText  from search where search match '" + replace + " AND (type:5)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                                } else if (!string.equals("mksap")) {
                                    return null;
                                } else {
                                    compressHelper = this.f76397q4;
                                    str2 = "Select  Text as text, contentId,type,typeText, snippet(search) as subText  from search where search match '" + replace + " AND (type:5) AND  (typeText:Topic OR typeText:Question)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                                }
                                return compressHelper3.m4952W(bundle, str5, "fsearch.sqlite");
                            } else {
                                compressHelper = this.f76397q4;
                                str2 = "Select  text, contentId,type,typeText, snippet(search) as subText  from search where search match '" + replace + " AND (type:5)' ORDER BY rank(matchinfo(search)) DESC,type asc limit 15";
                            }
                        }
                        compressHelper = this.f76397q4;
                        str2 = "Select  contentId,type,text || ' - ' || typeText as text, snippet(search) as subText  from search where search match '" + replace + " NOT (type:1 OR type:2 OR type:0)' ORDER BY rank(matchinfo(search)) DESC limit 15";
                    }
                    return compressHelper.m4955V(bundle, str2);
                } else {
                    compressHelper4 = this.f76397q4;
                    str6 = "Select URL, Text || ' - ' || \"table\" as text, snippet(search) as subText, related_topic  from search where search match '" + replace + "' ORDER BY rank(matchinfo(search)) DESC limit 15";
                }
                return compressHelper2.m4952W(bundle, str3, str4);
            } else if (!new File(CompressHelper.m4945Y0(bundle, "fcontentsearch.db")).exists()) {
                return null;
            } else {
                compressHelper4 = this.f76397q4;
                str6 = "Select URL as id, Text as text,snippet(search) as subText  from search where search match '" + replace + "' ORDER BY rank(matchinfo(search)) DESC limit 15";
            }
            return compressHelper4.m4952W(bundle, str6, "fcontentsearch.db");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            e.printStackTrace();
            iMDLogger.m3294f("SearchInDB", "Error in searching " + bundle.getString("Title") + " : " + e.toString());
            return null;
        }
    }
}
