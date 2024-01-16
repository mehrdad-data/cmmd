package net.imedicaldoctor.imd.Fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.itextpdf.text.pdf.PdfBoolean;
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
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.VBHelper;
import net.imedicaldoctor.imd.ViewHolders.StatusAdapter;
import net.imedicaldoctor.imd.iMD;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.FileUtils;

/* loaded from: classes2.dex */
public class databasesFragment extends Fragment {

    /* renamed from: e4 */
    public static ArrayList<Bundle> f76432e4;

    /* renamed from: f4 */
    public static ArrayList<Bundle> f76433f4;

    /* renamed from: F3 */
    private String f76434F3;

    /* renamed from: G3 */
    private View f76435G3;

    /* renamed from: H3 */
    private RecyclerView f76436H3;

    /* renamed from: I3 */
    private DatabasesAdapter f76437I3;

    /* renamed from: J3 */
    private long f76438J3;

    /* renamed from: K3 */
    private boolean f76439K3;

    /* renamed from: L3 */
    public VBHelper f76440L3;

    /* renamed from: M3 */
    private boolean f76441M3;

    /* renamed from: N3 */
    private String f76442N3;

    /* renamed from: O3 */
    private MenuItem f76443O3;

    /* renamed from: P3 */
    private MenuItem f76444P3;

    /* renamed from: Q3 */
    private SearchView f76445Q3;

    /* renamed from: R3 */
    private CollectionAdapter f76446R3;

    /* renamed from: S3 */
    private RecyclerViewDragDropManager f76447S3;

    /* renamed from: T3 */
    private long f76448T3;

    /* renamed from: U3 */
    private DividerItemDecoration f76449U3;

    /* renamed from: V3 */
    private GridLayoutManager f76450V3;

    /* renamed from: W3 */
    private FloatingActionButton f76451W3;

    /* renamed from: X3 */
    private boolean f76452X3;

    /* renamed from: Y3 */
    Typeface f76453Y3;

    /* renamed from: Z3 */
    private CompressHelper f76454Z3;

    /* renamed from: a4 */
    private Bundle f76455a4;

    /* renamed from: b4 */
    HashMap<String, Bundle> f76456b4 = new HashMap<>();

    /* renamed from: c4 */
    private final ActivityResultLauncher<String> f76457c4 = mo44731s(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: net.imedicaldoctor.imd.Fragments.d
        @Override // androidx.activity.result.ActivityResultCallback
        /* renamed from: a */
        public final void mo3836a(Object obj) {
            databasesFragment.m3822N2((Boolean) obj);
        }
    });

    /* renamed from: d4 */
    public BroadcastReceiver f76458d4 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.15
        {
            databasesFragment.this = this;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            databasesFragment.this.f76454Z3.m4993I0(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.15.1
                {
                    C458815.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    databasesFragment.this.f76454Z3.m4902k0();
                    databasesFragment.f76432e4 = databasesFragment.this.f76454Z3.m4947X1();
                }
            }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.15.2
                {
                    C458815.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RecyclerView recyclerView;
                    RecyclerView.Adapter adapter;
                    databasesFragment.this.m3823M3();
                    databasesFragment.f76432e4.size();
                    if (databasesFragment.this.f76437I3 != null) {
                        databasesFragment.this.f76437I3.m42860G();
                    }
                    if (databasesFragment.this.f76446R3 != null) {
                        databasesFragment.this.f76446R3.m42860G();
                    }
                    databasesFragment.this.m3812U2();
                    if (databasesFragment.this.f76445Q3 == null) {
                        return;
                    }
                    databasesFragment.this.f76445Q3.m51655i0("", false);
                    databasesFragment.this.f76445Q3.clearFocus();
                    if (databasesFragment.this.f76439K3) {
                        recyclerView = databasesFragment.this.f76436H3;
                        adapter = databasesFragment.this.f76446R3;
                    } else {
                        recyclerView = databasesFragment.this.f76436H3;
                        adapter = databasesFragment.this.f76437I3;
                    }
                    recyclerView.setAdapter(adapter);
                    LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
                    LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("referesh.account"));
                    LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reloadaccountdownloads"));
                }
            });
        }
    };

    /* renamed from: net.imedicaldoctor.imd.Fragments.databasesFragment$22 */
    /* loaded from: classes2.dex */
    public class C460122 extends DisposableObserver<String> {

        /* renamed from: X */
        final /* synthetic */ Bundle f76497X;

        /* renamed from: net.imedicaldoctor.imd.Fragments.databasesFragment$22$2 */
        /* loaded from: classes2.dex */
        public class DialogInterface$OnClickListenerC46032 implements DialogInterface.OnClickListener {
            DialogInterface$OnClickListenerC46032() {
                C460122.this = r1;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DisposableObserver<String> disposableObserver = new DisposableObserver<String>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.22.2.1
                    {
                        DialogInterface$OnClickListenerC46032.this = this;
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    /* renamed from: b */
                    public void onNext(@NonNull String str) {
                        String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
                        if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                            if (splitByWholeSeparator.length > 1) {
                                if (splitByWholeSeparator[1].contains("Not Enough Money")) {
                                    new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("You don't have enough credit in your account. what do you want to do ?").mo26266y("Buy Database", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.22.2.1.2
                                        {
                                            C46041.this = this;
                                        }

                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface2, int i2) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("http://imedicaldoctor.net/buydb.php?user=");
                                            databasesFragment databasesfragment = databasesFragment.this;
                                            sb.append(databasesfragment.f76440L3.m3420m(databasesfragment.f76454Z3.m4889o1(), "127"));
                                            sb.append("&db=");
                                            C460122 c460122 = C460122.this;
                                            sb.append(databasesFragment.this.f76440L3.m3420m(c460122.f76497X.getString("Name"), "127"));
                                            databasesFragment.this.f76454Z3.m4973P(sb.toString());
                                        }
                                    }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.22.2.1.1
                                        {
                                            C46041.this = this;
                                        }

                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface2, int i2) {
                                        }
                                    }).m52864I();
                                    return;
                                } else {
                                    CompressHelper.m4921e2(databasesFragment.this.m44716w(), splitByWholeSeparator[1], 1);
                                    return;
                                }
                            }
                            return;
                        }
                        databasesFragment.this.f76454Z3.m4933b2(splitByWholeSeparator[1]);
                        databasesFragment.this.f76454Z3.m4902k0();
                        LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("referesh.account"));
                        LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
                        C460122.this.f76497X.remove("Inactive");
                        C460122.this.f76497X.remove("Damu");
                        databasesFragment.this.f76436H3.getAdapter().m42860G();
                        databasesFragment.this.f76454Z3.m4859y1();
                        Toast.makeText(databasesFragment.this.m44716w(), "Purchase was successful", 1).show();
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onError(@NonNull Throwable th) {
                        CompressHelper.m4921e2(databasesFragment.this.m44716w(), "Error in contacting server, try again later", 1);
                    }
                };
                CompressHelper compressHelper = databasesFragment.this.f76454Z3;
                databasesFragment databasesfragment = databasesFragment.this;
                CompressHelper compressHelper2 = databasesfragment.f76454Z3;
                compressHelper.m5014B0(databasesfragment, compressHelper2.m4890o0("BuyItem|||||" + new VBHelper(databasesFragment.this.m44716w()).m3421l() + "|||||" + C460122.this.f76497X.getString("Name"))).mo6065a(disposableObserver);
            }
        }

        C460122(Bundle bundle) {
            databasesFragment.this = r1;
            this.f76497X = bundle;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        /* renamed from: b */
        public void onNext(@NonNull String str) {
            String str2;
            String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(str, "|||||");
            if (!splitByWholeSeparator[0].equals(IcyHeaders.f35463C2)) {
                CompressHelper.m4921e2(databasesFragment.this.m44716w(), splitByWholeSeparator[1], 1);
                return;
            }
            String str3 = splitByWholeSeparator[1];
            if (str3.equals("0")) {
                str2 = "Are You Sure You Want To Buy " + this.f76497X.getString("Title") + " For Free ?";
            } else {
                str2 = "Are You Sure You Want To Buy " + this.f76497X.getString("Title") + " For " + str3 + " Toman ?";
            }
            new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l(str2).mo26266y("Yes", new DialogInterface$OnClickListenerC46032()).mo26284p("No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.22.1
                {
                    C460122.this = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).m52864I();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(@NonNull Throwable th) {
            CompressHelper.m4921e2(databasesFragment.this.m44716w(), "Error in contacting server, try again later", 1);
        }
    }

    /* loaded from: classes2.dex */
    public static class AddCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76540I;

        public AddCellViewHolder(View view) {
            super(view);
            this.f76540I = (TextView) view.findViewById(C4804R.C4808id.text);
        }
    }

    /* loaded from: classes2.dex */
    public static class CardViewPlaceHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        private TextView f76541I;

        /* renamed from: J */
        private TextView f76542J;

        /* renamed from: K */
        private ImageView f76543K;

        /* renamed from: L */
        private AppCompatButton f76544L;

        /* renamed from: M */
        private MaterialRippleLayout f76545M;

        public CardViewPlaceHolder(View view) {
            super(view);
            this.f76541I = (TextView) view.findViewById(C4804R.C4808id.f87058text_view);
            this.f76542J = (TextView) view.findViewById(C4804R.C4808id.f87034subtext_view);
            this.f76543K = (ImageView) view.findViewById(C4804R.C4808id.f86937image_view);
            this.f76544L = (AppCompatButton) view.findViewById(C4804R.C4808id.f86830buy_button);
            this.f76545M = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
        }
    }

    /* loaded from: classes2.dex */
    public class CollectionAdapter extends RecyclerView.Adapter {

        /* renamed from: d */
        HashMap<String, Integer> f76546d = new HashMap<>();

        public CollectionAdapter() {
            databasesFragment.this = r9;
            Iterator<Bundle> it2 = databasesFragment.f76432e4.iterator();
            int i = 0;
            while (it2.hasNext()) {
                Bundle next = it2.next();
                HashMap<String, Integer> hashMap = this.f76546d;
                hashMap.put("Section" + next.getString("title"), Integer.valueOf(i));
                i++;
                for (int i2 = 0; i2 < next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size(); i2++) {
                    HashMap<String, Integer> hashMap2 = this.f76546d;
                    hashMap2.put("Database" + ((Bundle) next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i2)).getString("Name"), Integer.valueOf(i));
                    i++;
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return databasesFragment.this.m3814S2(i, databasesFragment.f76432e4).containsKey("Title") ? 1 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            Bundle m3814S2 = databasesFragment.this.m3814S2(i, databasesFragment.f76432e4);
            if (!m3814S2.containsKey("Item")) {
                ((HeaderCellViewHolder) viewHolder).f76583I.setText(m3814S2.getString("Title"));
                return;
            }
            CardViewPlaceHolder cardViewPlaceHolder = (CardViewPlaceHolder) viewHolder;
            databasesFragment.this.m3818P2(m3814S2.getBundle("Item"), cardViewPlaceHolder.f76544L, cardViewPlaceHolder.f76541I, cardViewPlaceHolder.f76542J, cardViewPlaceHolder.f76543K, cardViewPlaceHolder.f76545M, cardViewPlaceHolder.f18491a);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 0) {
                CardViewPlaceHolder cardViewPlaceHolder = new CardViewPlaceHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87223list_view_item_card_view_database, viewGroup, false));
                cardViewPlaceHolder.f76541I.setTypeface(databasesFragment.this.f76453Y3);
                return cardViewPlaceHolder;
            } else if (i == 1) {
                return new HeaderCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87228list_view_item_database_card_header, viewGroup, false));
            } else {
                return null;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return databasesFragment.this.m3817P3(databasesFragment.f76432e4);
        }
    }

    /* loaded from: classes2.dex */
    public static class DatabaseCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76548I;

        /* renamed from: J */
        public TextView f76549J;

        /* renamed from: K */
        public ImageView f76550K;

        /* renamed from: L */
        public MaterialRippleLayout f76551L;

        /* renamed from: M */
        public AppCompatButton f76552M;

        public DatabaseCellViewHolder(View view) {
            super(view);
            this.f76548I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76550K = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76551L = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
            this.f76552M = (AppCompatButton) view.findViewById(C4804R.C4808id.f86830buy_button);
            this.f76549J = (TextView) view.findViewById(C4804R.C4808id.f86856database_subtitle);
        }
    }

    /* loaded from: classes2.dex */
    public class DatabasesAdapter extends RecyclerView.Adapter {

        /* renamed from: d */
        HashMap<String, Integer> f76553d = new HashMap<>();

        public DatabasesAdapter() {
            databasesFragment.this = r9;
            Iterator<Bundle> it2 = databasesFragment.f76432e4.iterator();
            int i = 0;
            while (it2.hasNext()) {
                Bundle next = it2.next();
                HashMap<String, Integer> hashMap = this.f76553d;
                hashMap.put("Section" + next.getString("title"), Integer.valueOf(i));
                i++;
                for (int i2 = 0; i2 < next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size(); i2++) {
                    HashMap<String, Integer> hashMap2 = this.f76553d;
                    hashMap2.put("Database" + ((Bundle) next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i2)).getString("Name"), Integer.valueOf(i));
                    i++;
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return databasesFragment.this.m3814S2(i, databasesFragment.f76432e4).containsKey("Title") ? 1 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            Bundle m3814S2 = databasesFragment.this.m3814S2(i, databasesFragment.f76432e4);
            if (!m3814S2.containsKey("Item")) {
                ((HeaderCellViewHolder) viewHolder).f76583I.setText(m3814S2.getString("Title"));
                return;
            }
            DatabaseCellViewHolder databaseCellViewHolder = (DatabaseCellViewHolder) viewHolder;
            databasesFragment.this.m3818P2(m3814S2.getBundle("Item"), databaseCellViewHolder.f76552M, databaseCellViewHolder.f76548I, databaseCellViewHolder.f76549J, databaseCellViewHolder.f76550K, databaseCellViewHolder.f76551L, databaseCellViewHolder.f18491a);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 0) {
                return new DatabaseCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87232list_view_item_database_ripple, viewGroup, false));
            }
            if (i == 1) {
                return new HeaderCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87230list_view_item_database_header, viewGroup, false));
            }
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return databasesFragment.this.m3817P3(databasesFragment.f76432e4);
        }
    }

    /* loaded from: classes2.dex */
    public static class EditDatabaseCellViewHolder extends AbstractDraggableItemViewHolder implements DraggableItemViewHolder {

        /* renamed from: J */
        public TextView f76555J;

        /* renamed from: K */
        public ImageView f76556K;

        /* renamed from: L */
        public ImageView f76557L;

        /* renamed from: M */
        public LinearLayout f76558M;

        /* renamed from: N */
        public ImageView f76559N;

        public EditDatabaseCellViewHolder(View view) {
            super(view);
            this.f76555J = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76556K = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76557L = (ImageView) view.findViewById(C4804R.C4808id.f86879drag_indicator);
            this.f76558M = (LinearLayout) view.findViewById(C4804R.C4808id.f86850container_view);
            this.f76559N = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public class EditDatabasesAdapter extends RecyclerView.Adapter implements DraggableItemAdapter {

        /* renamed from: d */
        HashMap<String, Integer> f76560d;

        /* renamed from: e */
        int f76561e;

        public EditDatabasesAdapter() {
            databasesFragment.this = r10;
            HashMap<String, Integer> hashMap = new HashMap<>();
            this.f76560d = hashMap;
            this.f76561e = 0;
            hashMap.put("AddSEction", Integer.valueOf((int) AacUtil.f32624f));
            this.f76560d.put("EditSectionas", Integer.valueOf(this.f76561e));
            this.f76561e++;
            Iterator<Bundle> it2 = databasesFragment.f76432e4.iterator();
            while (it2.hasNext()) {
                this.f76560d.put("EditSection" + it2.next().getString("title"), Integer.valueOf(this.f76561e));
                this.f76561e = this.f76561e + 1;
            }
            Iterator<Bundle> it3 = databasesFragment.f76432e4.iterator();
            while (it3.hasNext()) {
                Bundle next = it3.next();
                this.f76560d.put("Section" + next.getString("title"), Integer.valueOf(this.f76561e));
                this.f76561e = this.f76561e + 1;
                for (int i = 0; i < next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size(); i++) {
                    this.f76560d.put("Database" + ((Bundle) next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i)).getString("Name"), Integer.valueOf(this.f76561e));
                    this.f76561e = this.f76561e + 1;
                }
            }
            mo15774a0(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            StringBuilder sb;
            String str;
            String sb2;
            Integer num;
            if (i < databasesFragment.f76432e4.size() + 1) {
                if (i == 0) {
                    num = this.f76560d.get("EditSectionas");
                    return num.intValue();
                }
                sb2 = "EditSection" + databasesFragment.f76432e4.get(i - 1).getString("title");
            } else if (i == databasesFragment.f76432e4.size() + 1) {
                return 100000L;
            } else {
                Bundle m3814S2 = databasesFragment.this.m3814S2((i - databasesFragment.f76432e4.size()) - 2, databasesFragment.f76432e4);
                if (m3814S2.containsKey("Item")) {
                    m3814S2 = m3814S2.getBundle("Item");
                    sb = new StringBuilder();
                    sb.append("Database");
                    str = "Name";
                } else {
                    sb = new StringBuilder();
                    sb.append("Section");
                    str = "Title";
                }
                sb.append(m3814S2.getString(str));
                sb2 = sb.toString();
                if (!this.f76560d.containsKey(sb2)) {
                    return -1L;
                }
            }
            num = this.f76560d.get(sb2);
            return num.intValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            if (i < databasesFragment.f76432e4.size() + 1) {
                return i == 0 ? 1 : 2;
            } else if (i == databasesFragment.f76432e4.size() + 1) {
                return 3;
            } else {
                return databasesFragment.this.m3814S2((i - databasesFragment.f76432e4.size()) - 2, databasesFragment.f76432e4).containsKey("Title") ? 1 : 0;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            if (viewHolder.m42556F() == 3) {
                AddCellViewHolder addCellViewHolder = (AddCellViewHolder) viewHolder;
                addCellViewHolder.f76540I.setText("Add Section");
                addCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.1
                    {
                        EditDatabasesAdapter.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        final EditText editText = new EditText(databasesFragment.this.m44716w());
                        editText.setTextColor(databasesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Enter Section Name").setView(editText).mo26266y("Add", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.1.2
                            {
                                View$OnClickListenerC46281.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                String obj = editText.getText().toString();
                                if (obj.length() == 0) {
                                    CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You must enter a name for the section", 1);
                                    return;
                                }
                                if (EditDatabasesAdapter.this.f76560d.containsKey("EditSection" + obj)) {
                                    CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You already have a section with this name", 1);
                                    return;
                                }
                                Bundle bundle = new Bundle();
                                bundle.putString("title", obj);
                                bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, new ArrayList<>());
                                databasesFragment.f76432e4.add(bundle);
                                EditDatabasesAdapter editDatabasesAdapter = EditDatabasesAdapter.this;
                                editDatabasesAdapter.f76561e++;
                                editDatabasesAdapter.f76560d.put("EditSection" + obj, Integer.valueOf(EditDatabasesAdapter.this.f76561e));
                            }
                        }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.1.1
                            {
                                View$OnClickListenerC46281.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                            }
                        }).m52864I();
                    }
                });
            } else if (i < databasesFragment.f76432e4.size() + 1) {
                if (i == 0) {
                    ((HeaderCellViewHolder) viewHolder).f76583I.setText("Sections");
                    return;
                }
                final String string = databasesFragment.f76432e4.get(i - 1).getString("title");
                EditHeaderCellViewHolder editHeaderCellViewHolder = (EditHeaderCellViewHolder) viewHolder;
                editHeaderCellViewHolder.f76579J.setText(string);
                editHeaderCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.2
                    {
                        EditDatabasesAdapter.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        final EditText editText = new EditText(databasesFragment.this.m44716w());
                        editText.setText(string);
                        editText.setTextColor(databasesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Edit Section Name").setView(editText).mo26266y("Edit", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.2.2
                            {
                                View$OnClickListenerC46312.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                String obj = editText.getText().toString();
                                if (obj.length() == 0) {
                                    CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You must enter a name for the section", 1);
                                } else if (obj.equals(string)) {
                                } else {
                                    if (EditDatabasesAdapter.this.f76560d.containsKey("EditSection" + obj)) {
                                        CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You already have a section with this name", 1);
                                        return;
                                    }
                                    Bundle bundle = databasesFragment.f76432e4.get(i - 1);
                                    bundle.remove("title");
                                    bundle.putString("title", obj);
                                    EditDatabasesAdapter editDatabasesAdapter = EditDatabasesAdapter.this;
                                    editDatabasesAdapter.f76561e++;
                                    editDatabasesAdapter.f76560d.put("EditSection" + obj, Integer.valueOf(EditDatabasesAdapter.this.f76561e));
                                }
                            }
                        }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.2.1
                            {
                                View$OnClickListenerC46312.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                            }
                        }).m52864I();
                    }
                });
                editHeaderCellViewHolder.f76582M.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.3
                    {
                        EditDatabasesAdapter.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ArrayList parcelableArrayList = databasesFragment.f76432e4.get(i - 1).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
                        if (parcelableArrayList != null && parcelableArrayList.size() > 0) {
                            CompressHelper.m4921e2(databasesFragment.this.m44716w(), "First delete databases inside this section", 1);
                            return;
                        }
                        databasesFragment.f76432e4.remove(i - 1);
                        databasesFragment.this.f76436H3.getAdapter().m42860G();
                    }
                });
            } else {
                final Bundle m3814S2 = databasesFragment.this.m3814S2((i - databasesFragment.f76432e4.size()) - 2, databasesFragment.f76432e4);
                if (!m3814S2.containsKey("Item")) {
                    ((HeaderCellViewHolder) viewHolder).f76583I.setText(m3814S2.getString("Title"));
                    return;
                }
                Bundle bundle = m3814S2.getBundle("Item");
                EditDatabaseCellViewHolder editDatabaseCellViewHolder = (EditDatabaseCellViewHolder) viewHolder;
                editDatabaseCellViewHolder.f76555J.setText(databasesFragment.this.f76454Z3.m4960T0(bundle.getString("Title")));
                Glide.m40316F(databasesFragment.this).mo40145t(CompressHelper.m5012C(bundle)).m40191t2(editDatabaseCellViewHolder.f76556K);
                editDatabaseCellViewHolder.f76559N.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.4
                    {
                        EditDatabasesAdapter.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Are you really want to delete this database ?").mo26266y("Yes", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.4.2
                            {
                                View$OnClickListenerC46354.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                int i3 = m3814S2.getInt("Section");
                                int i4 = m3814S2.getInt("Row");
                                String string2 = m3814S2.getBundle("Item").getString("Path");
                                databasesFragment.this.m3830F3();
                                iMDLogger.m3294f("HideKeyboard", "5");
                                databasesFragment.this.m3815R2(new File(string2));
                                databasesFragment.f76432e4.get(i3).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).remove(i4);
                                databasesFragment.this.f76436H3.getAdapter().m42860G();
                                databasesFragment.this.f76454Z3.m4950W1(databasesFragment.f76432e4);
                            }
                        }).mo26284p("No", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.EditDatabasesAdapter.4.1
                            {
                                View$OnClickListenerC46354.this = this;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                            }
                        }).m52864I();
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 0) {
                return new EditDatabaseCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87229list_view_item_database_edit, viewGroup, false));
            }
            if (i == 1) {
                return new HeaderCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87230list_view_item_database_header, viewGroup, false));
            }
            if (i == 2) {
                return new EditHeaderCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87231list_view_item_database_header_edit, viewGroup, false));
            }
            if (i == 3) {
                return new AddCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87219list_view_item_add, viewGroup, false));
            }
            return null;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
        /* renamed from: g */
        public void mo3619g(int i, int i2) {
            ArrayList parcelableArrayList;
            if (i > 0) {
                try {
                    if (i < databasesFragment.f76432e4.size() + 1) {
                        int i3 = i - 1;
                        databasesFragment.f76432e4.remove(i3);
                        databasesFragment.f76432e4.add(i2 - 1, databasesFragment.f76432e4.get(i3));
                        return;
                    }
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    return;
                }
            }
            Bundle m3814S2 = databasesFragment.this.m3814S2((i - databasesFragment.f76432e4.size()) - 2, databasesFragment.f76432e4);
            int i4 = m3814S2.getInt("Row");
            int i5 = m3814S2.getInt("Section");
            Bundle bundle = (Bundle) databasesFragment.f76432e4.get(i5).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i4);
            Bundle m3814S22 = databasesFragment.this.m3814S2((i2 - databasesFragment.f76432e4.size()) - 2, databasesFragment.f76432e4);
            int i6 = m3814S22.getInt("Row");
            int i7 = m3814S22.getInt("Section");
            iMDLogger.m3294f("Staring Drag", "Section " + i5 + " , Row : " + i4 + ", Section2:" + i7 + ", row2:" + i6);
            databasesFragment.f76432e4.get(i5).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).remove(i4);
            if (m3814S22.containsKey("Title")) {
                int i8 = m3814S22.getInt("Row2");
                int i9 = m3814S22.getInt("Section2");
                iMDLogger.m3294f("Staring Drag", "Section22:" + i9 + ", row22:" + i8);
                if (i5 >= i7) {
                    if (databasesFragment.f76432e4.get(i9).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size() == 0) {
                        i8 = 0;
                    }
                    databasesFragment.f76432e4.get(i9).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).add(i8, bundle);
                    return;
                }
                parcelableArrayList = databasesFragment.f76432e4.get(i7).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
            } else {
                parcelableArrayList = databasesFragment.f76432e4.get(i7).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
            }
            parcelableArrayList.add(i6, bundle);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
        /* renamed from: j */
        public boolean mo3618j(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
            ImageView imageView;
            LinearLayout linearLayout;
            if (viewHolder.m42556F() == 1 || viewHolder.m42556F() == 3) {
                return false;
            }
            if (viewHolder.m42556F() == 2) {
                EditHeaderCellViewHolder editHeaderCellViewHolder = (EditHeaderCellViewHolder) viewHolder;
                imageView = editHeaderCellViewHolder.f76580K;
                linearLayout = editHeaderCellViewHolder.f76581L;
            } else {
                EditDatabaseCellViewHolder editDatabaseCellViewHolder = (EditDatabaseCellViewHolder) viewHolder;
                imageView = editDatabaseCellViewHolder.f76557L;
                linearLayout = editDatabaseCellViewHolder.f76558M;
            }
            linearLayout.getTop();
            ViewCompat.m46388y0(linearLayout);
            boolean m4148a = ViewUtils.m4148a(imageView, i2 - (linearLayout.getLeft() + ((int) (ViewCompat.m46392x0(linearLayout) + 0.5f))), 50);
            iMDLogger.m3294f("CanDrag", m4148a ? PdfBoolean.f61411N2 : "false");
            return m4148a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return databasesFragment.this.m3817P3(databasesFragment.f76432e4) + databasesFragment.f76432e4.size() + 2;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
        /* renamed from: w */
        public ItemDraggableRange mo3617w(RecyclerView.ViewHolder viewHolder, int i) {
            if (i <= 0 || i >= databasesFragment.f76432e4.size() + 1) {
                return new ItemDraggableRange(databasesFragment.f76432e4.size() + 3, mo3359s() - 1);
            }
            iMDLogger.m3290j("RequestingRange", "Range requested");
            return new ItemDraggableRange(1, databasesFragment.f76432e4.size());
        }
    }

    /* loaded from: classes2.dex */
    public static class EditHeaderCellViewHolder extends AbstractDraggableItemViewHolder implements DraggableItemViewHolder {

        /* renamed from: J */
        public TextView f76579J;

        /* renamed from: K */
        public ImageView f76580K;

        /* renamed from: L */
        public LinearLayout f76581L;

        /* renamed from: M */
        public ImageView f76582M;

        public EditHeaderCellViewHolder(View view) {
            super(view);
            this.f76579J = (TextView) view.findViewById(C4804R.C4808id.f86913header_text);
            this.f76580K = (ImageView) view.findViewById(C4804R.C4808id.f86879drag_indicator);
            this.f76581L = (LinearLayout) view.findViewById(C4804R.C4808id.f86850container_view);
            this.f76582M = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public static class HeaderCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76583I;

        public HeaderCellViewHolder(View view) {
            super(view);
            this.f76583I = (TextView) view.findViewById(C4804R.C4808id.f86913header_text);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchCollectionDatabasesAdapter extends RecyclerView.Adapter {
        public SearchCollectionDatabasesAdapter() {
            databasesFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            CardViewPlaceHolder cardViewPlaceHolder = (CardViewPlaceHolder) viewHolder;
            databasesFragment.this.m3818P2(databasesFragment.f76433f4.get(i), cardViewPlaceHolder.f76544L, cardViewPlaceHolder.f76541I, cardViewPlaceHolder.f76542J, cardViewPlaceHolder.f76543K, cardViewPlaceHolder.f76545M, cardViewPlaceHolder.f18491a);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            return new CardViewPlaceHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87223list_view_item_card_view_database, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return databasesFragment.f76433f4.size();
        }
    }

    /* loaded from: classes2.dex */
    public class SearchDatabasesAdapter extends RecyclerView.Adapter {
        public SearchDatabasesAdapter() {
            databasesFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
            DatabaseCellViewHolder databaseCellViewHolder = (DatabaseCellViewHolder) viewHolder;
            databasesFragment.this.m3818P2(databasesFragment.f76433f4.get(i), databaseCellViewHolder.f76552M, databaseCellViewHolder.f76548I, databaseCellViewHolder.f76549J, databaseCellViewHolder.f76550K, databaseCellViewHolder.f76551L, databaseCellViewHolder.f18491a);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            return new DatabaseCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87232list_view_item_database_ripple, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return databasesFragment.f76433f4.size();
        }
    }

    /* loaded from: classes2.dex */
    public class TestCollectionAdapter extends RecyclerView.Adapter {

        /* renamed from: d */
        HashMap<String, Integer> f76586d = new HashMap<>();

        public TestCollectionAdapter() {
            databasesFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: B */
        public long mo3620B(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            return databasesFragment.this.m3814S2(1, databasesFragment.f76432e4).containsKey("Title") ? 1 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            Bundle m3814S2 = databasesFragment.this.m3814S2(1, databasesFragment.f76432e4);
            if (!m3814S2.containsKey("Item")) {
                ((HeaderCellViewHolder) viewHolder).f76583I.setText(m3814S2.getString("Title"));
                return;
            }
            Bundle bundle = m3814S2.getBundle("Item");
            final CardViewPlaceHolder cardViewPlaceHolder = (CardViewPlaceHolder) viewHolder;
            cardViewPlaceHolder.f76541I.setText(databasesFragment.this.f76454Z3.m4960T0(bundle.getString("Title")));
            final String m5012C = CompressHelper.m5012C(bundle);
            if (databasesFragment.this.f76455a4.containsKey(m5012C)) {
                cardViewPlaceHolder.f76545M.setRippleColor(databasesFragment.this.f76455a4.getInt(m5012C));
            } else {
                databasesFragment.this.m3833C3(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.TestCollectionAdapter.1
                    {
                        TestCollectionAdapter.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        Bitmap decodeStream;
                        if (m5012C.contains("file:///android_asset/")) {
                            try {
                                decodeStream = BitmapFactory.decodeStream(databasesFragment.this.m44716w().getAssets().open(m5012C.replace("file:///android_asset/", "")));
                            } catch (Exception e) {
                                FirebaseCrashlytics.m18030d().m18027g(e);
                                e.printStackTrace();
                                return;
                            }
                        } else {
                            decodeStream = BitmapFactory.decodeFile(m5012C);
                        }
                        Palette.Swatch m43631C = Palette.m43628b(decodeStream).m43594g().m43631C();
                        if (m43631C == null) {
                            return;
                        }
                        int m43579e = m43631C.m43579e();
                        if (databasesFragment.this.f76455a4.containsKey(m5012C)) {
                            return;
                        }
                        databasesFragment.this.f76455a4.putInt(m5012C, m43579e);
                    }
                }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.TestCollectionAdapter.2
                    {
                        TestCollectionAdapter.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        cardViewPlaceHolder.f76545M.setRippleColor(databasesFragment.this.f76455a4.getInt(m5012C));
                    }
                });
            }
            Glide.m40316F(databasesFragment.this).mo40145t(m5012C).mo39038a(new RequestOptions().m39082B()).m40191t2(cardViewPlaceHolder.f76543K);
            cardViewPlaceHolder.f76545M.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.TestCollectionAdapter.3
                {
                    TestCollectionAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Bundle m3814S22 = databasesFragment.this.m3814S2(i, databasesFragment.f76432e4);
                    if (m3814S22.containsKey("Title")) {
                        return;
                    }
                    databasesFragment.this.f76454Z3.m4886p1(m3814S22.getBundle("Item"));
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 0) {
                CardViewPlaceHolder cardViewPlaceHolder = new CardViewPlaceHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87223list_view_item_card_view_database, viewGroup, false));
                cardViewPlaceHolder.f76541I.setTypeface(databasesFragment.this.f76453Y3);
                return cardViewPlaceHolder;
            } else if (i == 1) {
                return new HeaderCellViewHolder(LayoutInflater.from(databasesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87228list_view_item_database_card_header, viewGroup, false));
            } else {
                return null;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            return 300;
        }
    }

    /* loaded from: classes2.dex */
    public abstract class UIActionClass extends ItemTouchHelper.Callback {

        /* renamed from: i */
        Context f76595i;

        /* renamed from: j */
        private Paint f76596j;

        /* renamed from: k */
        private ColorDrawable f76597k = new ColorDrawable();

        /* renamed from: l */
        private int f76598l = Color.parseColor("#b80f0a");

        /* renamed from: m */
        private Drawable f76599m;

        /* renamed from: n */
        private int f76600n;

        /* renamed from: o */
        private int f76601o;

        UIActionClass(Context context) {
            databasesFragment.this = r2;
            this.f76595i = context;
            Paint paint = new Paint();
            this.f76596j = paint;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            Drawable m47744h = ContextCompat.m47744h(this.f76595i, 17301564);
            this.f76599m = m47744h;
            this.f76600n = m47744h.getIntrinsicWidth();
            this.f76601o = this.f76599m.getIntrinsicHeight();
        }

        /* renamed from: E */
        private void m3762E(Canvas canvas, Float f, Float f2, Float f3, Float f4) {
            canvas.drawRect(f.floatValue(), f2.floatValue(), f3.floatValue(), f4.floatValue(), this.f76596j);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: A */
        public boolean mo3761A(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder2) {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: l */
        public int mo3759l(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder) {
            if (databasesFragment.this.m3829G3()) {
                return ItemTouchHelper.Callback.m43215v(0, 0);
            }
            viewHolder.m42556F();
            return ItemTouchHelper.Callback.m43215v(0, 4);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: n */
        public float mo3758n(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.7f;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: w */
        public void mo3757w(@androidx.annotation.NonNull Canvas canvas, @androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            super.mo3757w(canvas, recyclerView, viewHolder, f, f2, i, z);
            View view = viewHolder.f18491a;
            int height = view.getHeight();
            if (f == 0.0f && !z) {
                m3762E(canvas, Float.valueOf(view.getRight() + f), Float.valueOf(view.getTop()), Float.valueOf(view.getRight()), Float.valueOf(view.getBottom()));
            } else {
                this.f76597k.setColor(this.f76598l);
                this.f76597k.setBounds(view.getRight() + ((int) f), view.getTop(), view.getRight(), view.getBottom());
                this.f76597k.draw(canvas);
                int top = view.getTop();
                int i2 = this.f76601o;
                int i3 = top + ((height - i2) / 2);
                int i4 = (height - i2) / 2;
                this.f76599m.setBounds((view.getRight() - i4) - this.f76600n, i3, view.getRight() - i4, this.f76601o + i3);
                this.f76599m.draw(canvas);
            }
            super.mo3757w(canvas, recyclerView, viewHolder, f, f2, i, z);
        }
    }

    /* loaded from: classes2.dex */
    public abstract class UIActionClassRight extends ItemTouchHelper.Callback {

        /* renamed from: i */
        Context f76603i;

        /* renamed from: j */
        private Paint f76604j;

        /* renamed from: k */
        private ColorDrawable f76605k = new ColorDrawable();

        /* renamed from: l */
        private int f76606l = Color.parseColor("#0ab867");

        /* renamed from: m */
        private Drawable f76607m;

        /* renamed from: n */
        private int f76608n;

        /* renamed from: o */
        private int f76609o;

        UIActionClassRight(Context context) {
            databasesFragment.this = r2;
            this.f76603i = context;
            Paint paint = new Paint();
            this.f76604j = paint;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            Drawable m47744h = ContextCompat.m47744h(this.f76603i, 17301566);
            this.f76607m = m47744h;
            this.f76608n = m47744h.getIntrinsicWidth();
            this.f76609o = this.f76607m.getIntrinsicHeight();
        }

        /* renamed from: E */
        private void m3760E(Canvas canvas, Float f, Float f2, Float f3, Float f4) {
            canvas.drawRect(f.floatValue(), f2.floatValue(), f3.floatValue(), f4.floatValue(), this.f76604j);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: A */
        public boolean mo3761A(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder2) {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: l */
        public int mo3759l(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder) {
            if (!databasesFragment.this.m3829G3() && viewHolder.m42556F() == 1) {
                return ItemTouchHelper.Callback.m43215v(0, 8);
            }
            return ItemTouchHelper.Callback.m43215v(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: n */
        public float mo3758n(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.7f;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        /* renamed from: w */
        public void mo3757w(@androidx.annotation.NonNull Canvas canvas, @androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            super.mo3757w(canvas, recyclerView, viewHolder, f, f2, i, z);
            View view = viewHolder.f18491a;
            int height = view.getHeight();
            if (f == 0.0f && !z) {
                m3760E(canvas, Float.valueOf(view.getRight() + f), Float.valueOf(view.getTop()), Float.valueOf(view.getRight()), Float.valueOf(view.getBottom()));
            } else {
                this.f76605k.setColor(this.f76606l);
                this.f76605k.setBounds(view.getLeft(), view.getTop(), view.getLeft() + ((int) f), view.getBottom());
                this.f76605k.draw(canvas);
                int top = view.getTop();
                int i2 = this.f76609o;
                int i3 = top + ((height - i2) / 2);
                int i4 = (height - i2) / 2;
                this.f76607m.setBounds(i4, i3, this.f76608n + i4, i2 + i3);
                this.f76607m.draw(canvas);
            }
            super.mo3757w(canvas, recyclerView, viewHolder, f, f2, i, z);
        }
    }

    /* renamed from: A3 */
    public void m3835A3() {
        m3834B3();
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.m47751a(m44716w(), "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        m44836G2("android.permission.POST_NOTIFICATIONS");
        this.f76457c4.m53002b("android.permission.POST_NOTIFICATIONS");
    }

    /* renamed from: B3 */
    private void m3834B3() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("12121", "iMDChannel", 4);
            notificationChannel.setDescription("iMDChannel");
            ((NotificationManager) m44716w().getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    /* renamed from: D3 */
    public void m3832D3() {
        UIActionClass uIActionClass = new UIActionClass(m44716w()) { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.1
            {
                databasesFragment.this = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:37:0x0040 A[Catch: Exception -> 0x00c2, TryCatch #0 {Exception -> 0x00c2, blocks: (B:28:0x0000, B:30:0x0016, B:33:0x0029, B:35:0x003a, B:37:0x0040, B:38:0x006e, B:40:0x0084, B:42:0x008a, B:44:0x00a4, B:34:0x0032), top: B:49:0x0000 }] */
            /* JADX WARN: Removed duplicated region for block: B:38:0x006e A[Catch: Exception -> 0x00c2, TryCatch #0 {Exception -> 0x00c2, blocks: (B:28:0x0000, B:30:0x0016, B:33:0x0029, B:35:0x003a, B:37:0x0040, B:38:0x006e, B:40:0x0084, B:42:0x008a, B:44:0x00a4, B:34:0x0032), top: B:49:0x0000 }] */
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            /* renamed from: D */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void mo3777D(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder r4, int r5) {
                /*
                    r3 = this;
                    int r5 = r4.m42560B()     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Fragments.databasesFragment r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.m3811V2(r0)     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()     // Catch: java.lang.Exception -> Lc2
                    java.lang.Class r0 = r0.getClass()     // Catch: java.lang.Exception -> Lc2
                    java.lang.Class<net.imedicaldoctor.imd.Fragments.databasesFragment$SearchDatabasesAdapter> r1 = net.imedicaldoctor.imd.Fragments.databasesFragment.SearchDatabasesAdapter.class
                    if (r0 == r1) goto L32
                    net.imedicaldoctor.imd.Fragments.databasesFragment r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.m3811V2(r0)     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()     // Catch: java.lang.Exception -> Lc2
                    java.lang.Class r0 = r0.getClass()     // Catch: java.lang.Exception -> Lc2
                    java.lang.Class<net.imedicaldoctor.imd.Fragments.databasesFragment$SearchCollectionDatabasesAdapter> r1 = net.imedicaldoctor.imd.Fragments.databasesFragment.SearchCollectionDatabasesAdapter.class
                    if (r0 != r1) goto L29
                    goto L32
                L29:
                    net.imedicaldoctor.imd.Fragments.databasesFragment r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    java.util.ArrayList<android.os.Bundle> r1 = net.imedicaldoctor.imd.Fragments.databasesFragment.f76432e4     // Catch: java.lang.Exception -> Lc2
                    android.os.Bundle r0 = r0.m3814S2(r5, r1)     // Catch: java.lang.Exception -> Lc2
                    goto L3a
                L32:
                    java.util.ArrayList<android.os.Bundle> r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.f76433f4     // Catch: java.lang.Exception -> Lc2
                    java.lang.Object r0 = r0.get(r5)     // Catch: java.lang.Exception -> Lc2
                    android.os.Bundle r0 = (android.os.Bundle) r0     // Catch: java.lang.Exception -> Lc2
                L3a:
                    int r4 = r4.m42556F()     // Catch: java.lang.Exception -> Lc2
                    if (r4 != 0) goto L6e
                    androidx.appcompat.app.AlertDialog$Builder r4 = new androidx.appcompat.app.AlertDialog$Builder     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Fragments.databasesFragment r1 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.fragment.app.FragmentActivity r1 = r1.m44716w()     // Catch: java.lang.Exception -> Lc2
                    r2 = 2131952405(0x7f130315, float:1.9541252E38)
                    r4.<init>(r1, r2)     // Catch: java.lang.Exception -> Lc2
                    java.lang.String r1 = "Are you really want to delete this database ?"
                    androidx.appcompat.app.AlertDialog$Builder r4 = r4.mo26292l(r1)     // Catch: java.lang.Exception -> Lc2
                    java.lang.String r1 = "Yes"
                    net.imedicaldoctor.imd.Fragments.databasesFragment$1$2 r2 = new net.imedicaldoctor.imd.Fragments.databasesFragment$1$2     // Catch: java.lang.Exception -> Lc2
                    r2.<init>()     // Catch: java.lang.Exception -> Lc2
                    androidx.appcompat.app.AlertDialog$Builder r4 = r4.mo26266y(r1, r2)     // Catch: java.lang.Exception -> Lc2
                    java.lang.String r0 = "No"
                    net.imedicaldoctor.imd.Fragments.databasesFragment$1$1 r1 = new net.imedicaldoctor.imd.Fragments.databasesFragment$1$1     // Catch: java.lang.Exception -> Lc2
                    r1.<init>()     // Catch: java.lang.Exception -> Lc2
                    androidx.appcompat.app.AlertDialog$Builder r4 = r4.mo26284p(r0, r1)     // Catch: java.lang.Exception -> Lc2
                    r4.m52864I()     // Catch: java.lang.Exception -> Lc2
                    goto Lca
                L6e:
                    java.lang.String r4 = "Section"
                    int r4 = r0.getInt(r4)     // Catch: java.lang.Exception -> Lc2
                    java.util.ArrayList<android.os.Bundle> r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.f76432e4     // Catch: java.lang.Exception -> Lc2
                    java.lang.Object r0 = r0.get(r4)     // Catch: java.lang.Exception -> Lc2
                    android.os.Bundle r0 = (android.os.Bundle) r0     // Catch: java.lang.Exception -> Lc2
                    java.lang.String r1 = "items"
                    java.util.ArrayList r0 = r0.getParcelableArrayList(r1)     // Catch: java.lang.Exception -> Lc2
                    if (r0 == 0) goto La4
                    int r0 = r0.size()     // Catch: java.lang.Exception -> Lc2
                    if (r0 <= 0) goto La4
                    net.imedicaldoctor.imd.Fragments.databasesFragment r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.fragment.app.FragmentActivity r4 = r4.m44716w()     // Catch: java.lang.Exception -> Lc2
                    java.lang.String r0 = "First delete databases inside this section"
                    r1 = 1
                    net.imedicaldoctor.imd.Data.CompressHelper.m4921e2(r4, r0, r1)     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Fragments.databasesFragment r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.m3811V2(r4)     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView$Adapter r4 = r4.getAdapter()     // Catch: java.lang.Exception -> Lc2
                    r4.m42859H(r5)     // Catch: java.lang.Exception -> Lc2
                    return
                La4:
                    java.util.ArrayList<android.os.Bundle> r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.f76432e4     // Catch: java.lang.Exception -> Lc2
                    r0.remove(r4)     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Fragments.databasesFragment r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Data.CompressHelper r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.m3809X2(r4)     // Catch: java.lang.Exception -> Lc2
                    java.util.ArrayList<android.os.Bundle> r0 = net.imedicaldoctor.imd.Fragments.databasesFragment.f76432e4     // Catch: java.lang.Exception -> Lc2
                    r4.m4950W1(r0)     // Catch: java.lang.Exception -> Lc2
                    net.imedicaldoctor.imd.Fragments.databasesFragment r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.m3811V2(r4)     // Catch: java.lang.Exception -> Lc2
                    androidx.recyclerview.widget.RecyclerView$Adapter r4 = r4.getAdapter()     // Catch: java.lang.Exception -> Lc2
                    r4.m42851P(r5)     // Catch: java.lang.Exception -> Lc2
                    goto Lca
                Lc2:
                    r4 = move-exception
                    com.google.firebase.crashlytics.FirebaseCrashlytics r5 = com.google.firebase.crashlytics.FirebaseCrashlytics.m18030d()
                    r5.m18027g(r4)
                Lca:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.databasesFragment.C45791.mo3777D(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
            }
        };
        UIActionClassRight uIActionClassRight = new UIActionClassRight(m44716w()) { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.2
            {
                databasesFragment.this = this;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            /* renamed from: D */
            public void mo3777D(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final ArrayList arrayList = new ArrayList();
                final int m42560B = viewHolder.m42560B();
                final int i2 = databasesFragment.this.m3814S2(m42560B, databasesFragment.f76432e4).getInt("Section");
                final String string = databasesFragment.f76432e4.get(i2).getString("title");
                for (int i3 = 0; i3 < databasesFragment.f76432e4.size(); i3++) {
                    if (i3 != i2) {
                        arrayList.add(databasesFragment.f76432e4.get(i3).getString("title"));
                    }
                }
                final EditText editText = new EditText(databasesFragment.this.m44716w());
                editText.setText(string);
                editText.setTextColor(databasesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Edit Section Name").setView(editText).mo26266y("Edit", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.2.2
                    {
                        C45962.this = this;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        String obj = editText.getText().toString();
                        if (obj.length() == 0) {
                            CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You must enter a name for the section", 1);
                            databasesFragment.this.f76436H3.getAdapter().m42859H(m42560B);
                        } else if (obj.equals(string)) {
                            databasesFragment.this.f76436H3.getAdapter().m42859H(m42560B);
                        } else if (arrayList.contains(obj)) {
                            CompressHelper.m4921e2(databasesFragment.this.m44716w(), "You already have a section with this name", 1);
                            databasesFragment.this.f76436H3.getAdapter().m42859H(m42560B);
                        } else {
                            Bundle bundle = databasesFragment.f76432e4.get(i2);
                            bundle.remove("title");
                            bundle.putString("title", obj);
                            databasesFragment.this.f76436H3.getAdapter().m42859H(m42560B);
                            databasesFragment.this.f76454Z3.m4950W1(databasesFragment.f76432e4);
                        }
                    }
                }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.2.1
                    {
                        C45962.this = this;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        databasesFragment.this.f76436H3.getAdapter().m42859H(m42560B);
                    }
                }).m52864I();
            }
        };
        new ItemTouchHelper(uIActionClass).m43250m(this.f76436H3);
        new ItemTouchHelper(uIActionClassRight).m43250m(this.f76436H3);
    }

    /* renamed from: J3 */
    public void m3826J3(@androidx.annotation.NonNull File file, @androidx.annotation.NonNull File file2, Bundle bundle, ObservableEmitter<Bundle> observableEmitter) throws IOException {
        Path path;
        Path path2;
        if (file.isDirectory()) {
            m3825K3(file, file2, bundle, observableEmitter);
            return;
        }
        this.f76438J3++;
        if (System.currentTimeMillis() - this.f76448T3 > 1000) {
            if (bundle.containsKey("counter")) {
                bundle.remove("counter");
            }
            bundle.putLong("counter", this.f76438J3);
            this.f76448T3 = System.currentTimeMillis();
            observableEmitter.onNext(bundle);
        }
        if (Build.VERSION.SDK_INT < 26) {
            if (file2.exists() && !((file2.getName().contains("favorites.db") || file2.getName().contains("favorites.json") || file2.getName().contains("databases.json") || file2.getName().contains("highlights.db") || file2.getName().contains("recent.db")) && Boolean.valueOf(file2.delete()).booleanValue())) {
                return;
            }
            FileUtils.moveFile(file, file2);
            return;
        }
        path = Paths.get(file.toURI());
        path2 = Paths.get(file2.toURI());
        if (!file2.exists()) {
            Files.move(path, path2, new CopyOption[0]);
        } else if ((file2.getName().contains("favorites.db") || file2.getName().contains("favorites.json") || file2.getName().contains("databases.json") || file2.getName().contains("highlights.db") || file2.getName().contains("recent.db")) && Boolean.valueOf(file2.delete()).booleanValue()) {
            Files.move(path, path2, new CopyOption[0]);
        }
    }

    /* renamed from: K3 */
    private void m3825K3(@androidx.annotation.NonNull File file, @androidx.annotation.NonNull File file2, Bundle bundle, ObservableEmitter<Bundle> observableEmitter) throws IOException {
        String[] list;
        if (file2.exists() || file2.mkdir()) {
            for (String str : file.list()) {
                m3826J3(new File(file, str), new File(file2, str), bundle, observableEmitter);
            }
        }
    }

    /* renamed from: N2 */
    public static /* synthetic */ void m3822N2(Boolean bool) {
        bool.booleanValue();
    }

    /* renamed from: C3 */
    public void m3833C3(final Runnable runnable, final Runnable runnable2) {
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.23
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                try {
                    runnable.run();
                    observableEmitter.onNext("asdfadf");
                } catch (Exception unused) {
                }
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7329f6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.24
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str) throws Throwable {
                try {
                    runnable2.run();
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                }
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.25
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    iMDLogger.m3294f("Error occured", th.getMessage());
                } catch (Exception unused) {
                }
            }
        });
    }

    /* renamed from: E3 */
    public long m3831E3(File file) {
        File[] listFiles;
        if (file.isDirectory()) {
            int i = 0;
            for (File file2 : file.listFiles()) {
                i = file2.isDirectory() ? (int) (i + m3831E3(file2)) : i + 1;
            }
            return i;
        }
        return 1L;
    }

    /* renamed from: F3 */
    public void m3830F3() {
        iMDLogger.m3294f("HideKeyboard", "6");
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

    /* renamed from: G3 */
    public boolean m3829G3() {
        ArrayList<Bundle> arrayList;
        RecyclerView recyclerView = this.f76436H3;
        return (recyclerView == null || recyclerView.getAdapter().getClass() == SearchDatabasesAdapter.class || this.f76436H3.getAdapter().getClass() == SearchCollectionDatabasesAdapter.class || this.f76436H3.getAdapter().getClass() == DatabasesAdapter.class || this.f76436H3.getAdapter().getClass() == CollectionAdapter.class || (arrayList = f76432e4) == null || arrayList.size() == 0) ? false : true;
    }

    /* renamed from: I3 */
    public void m3827I3() {
        if ((Build.VERSION.SDK_INT < 23 || ContextCompat.m47751a(m44716w(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) && new File(this.f76454Z3.m4985L()).exists()) {
            m3813T2(new File(this.f76454Z3.m4985L()), new File(this.f76454Z3.m4856z1()));
        }
    }

    /* renamed from: L3 */
    public void m3824L3(final String str) {
        Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.11
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                databasesFragment.f76433f4 = new ArrayList<>(Collections2.m23110e(((iMD) databasesFragment.this.m44716w().getApplicationContext()).f83461s, new Predicate<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.11.1
                    {
                        C458311.this = this;
                    }

                    @Override // com.google.common.base.Predicate
                    /* renamed from: a */
                    public boolean apply(Bundle bundle) {
                        try {
                            return bundle.getString("Title").toLowerCase().contains(str.toLowerCase());
                        } catch (Exception e) {
                            FirebaseCrashlytics.m18030d().m18027g(e);
                            iMDLogger.m3294f("Error in filtering", e.getLocalizedMessage());
                            e.printStackTrace();
                            return false;
                        }
                    }
                }));
                observableEmitter.onComplete();
            }
        }).m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<String>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.12
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(String str2) throws Throwable {
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.13
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                try {
                    iMDLogger.m3294f("DatabasesFragmentSearch", "Error occured in filtering databases");
                    th.printStackTrace();
                } catch (Exception unused) {
                }
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.14
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
                RecyclerView recyclerView;
                RecyclerView.Adapter searchCollectionDatabasesAdapter;
                ArrayList<Bundle> arrayList = databasesFragment.f76433f4;
                if (arrayList == null || arrayList.size() == 0) {
                    databasesFragment.this.m3819O3("Nothing Found");
                    return;
                }
                databasesFragment.this.m3821N3();
                if (databasesFragment.this.f76439K3) {
                    recyclerView = databasesFragment.this.f76436H3;
                    searchCollectionDatabasesAdapter = new SearchCollectionDatabasesAdapter();
                } else {
                    recyclerView = databasesFragment.this.f76436H3;
                    searchCollectionDatabasesAdapter = new SearchDatabasesAdapter();
                }
                recyclerView.setAdapter(searchCollectionDatabasesAdapter);
            }
        });
    }

    /* renamed from: M3 */
    public final void m3823M3() {
        iMDLogger.m3296d("sendFavorite", "Sending FavoriteChanged message");
        Intent intent = new Intent("net.imedicaldoctor.imd.favorite");
        intent.putExtra("Test", "Random data for test");
        LocalBroadcastManager.m43863b(m44716w()).m43861d(intent);
    }

    /* renamed from: N3 */
    public void m3821N3() {
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        if (this.f76439K3) {
            recyclerView = this.f76436H3;
            adapter = this.f76446R3;
        } else {
            recyclerView = this.f76436H3;
            adapter = this.f76437I3;
        }
        recyclerView.setAdapter(adapter);
    }

    /* renamed from: O2 */
    public void m3820O2(Bundle bundle) {
        C460122 c460122 = new C460122(bundle);
        CompressHelper compressHelper = this.f76454Z3;
        compressHelper.m5014B0(this, compressHelper.m4890o0("QueryPrice|||||" + new VBHelper(m44716w()).m3421l() + "|||||" + bundle.getString("Name"))).mo6065a(c460122);
    }

    /* renamed from: O3 */
    public void m3819O3(String str) {
        RecyclerView recyclerView;
        StatusAdapter statusAdapter;
        if (str.toLowerCase().contains("go to".toLowerCase())) {
            recyclerView = this.f76436H3;
            statusAdapter = new StatusAdapter(m44716w(), str) { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.26
                {
                    databasesFragment.this = this;
                }

                @Override // net.imedicaldoctor.imd.ViewHolders.StatusAdapter
                /* renamed from: d0 */
                public void mo3376d0() {
                    ((mainActivity) databasesFragment.this.m44716w()).f76898Q2.setCurrentItem(4);
                }
            };
        } else {
            recyclerView = this.f76436H3;
            statusAdapter = new StatusAdapter(m44716w(), str);
        }
        recyclerView.setAdapter(statusAdapter);
    }

    /* renamed from: P2 */
    public void m3818P2(final Bundle bundle, AppCompatButton appCompatButton, TextView textView, TextView textView2, ImageView imageView, final MaterialRippleLayout materialRippleLayout, View view) {
        String str;
        View.OnClickListener onClickListener;
        textView.setText(this.f76454Z3.m4960T0(bundle.getString("Title")));
        final String m5012C = CompressHelper.m5012C(bundle);
        if (m5012C.contains("file:///android_asset/") || new File(m5012C).exists()) {
            if (m5012C.contains("irandarou")) {
                iMDLogger.m3294f("debug", "de");
            }
            Glide.m40316F(this).mo40145t(m5012C).m40191t2(imageView);
            if (this.f76452X3) {
                if (this.f76455a4.containsKey(m5012C)) {
                    materialRippleLayout.setRippleColor(this.f76455a4.getInt(m5012C));
                } else {
                    m3833C3(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.16
                        {
                            databasesFragment.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            Bitmap decodeStream;
                            if (m5012C.contains("file:///android_asset/")) {
                                try {
                                    decodeStream = BitmapFactory.decodeStream(databasesFragment.this.m44716w().getAssets().open(m5012C.replace("file:///android_asset/", "")));
                                } catch (Exception e) {
                                    FirebaseCrashlytics.m18030d().m18027g(e);
                                    e.printStackTrace();
                                    return;
                                }
                            } else {
                                decodeStream = BitmapFactory.decodeFile(m5012C);
                            }
                            Palette.Swatch m43631C = Palette.m43628b(decodeStream).m43594g().m43631C();
                            if (m43631C == null) {
                                return;
                            }
                            int m43579e = m43631C.m43579e();
                            if (databasesFragment.this.f76455a4.containsKey(m5012C)) {
                                return;
                            }
                            databasesFragment.this.f76455a4.putInt(m5012C, m43579e);
                        }
                    }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.17
                        {
                            databasesFragment.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            materialRippleLayout.setRippleColor(databasesFragment.this.f76455a4.getInt(m5012C));
                        }
                    });
                }
            }
        } else {
            imageView.setImageDrawable(m44782a0().getDrawable(C4804R.C4807drawable.f86674placeholder));
        }
        if (!bundle.containsKey("Dadu")) {
            appCompatButton.setVisibility(8);
            if (bundle.containsKey("Demu")) {
                textView2.setVisibility(0);
                textView2.setText(CompressHelper.m4957U0(bundle.getString("Version")));
            } else {
                textView2.setVisibility(8);
            }
            materialRippleLayout.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.18
                {
                    databasesFragment.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    databasesFragment.this.f76454Z3.m4886p1(bundle);
                }
            });
            return;
        }
        appCompatButton.setVisibility(0);
        textView2.setVisibility(0);
        if (bundle.containsKey("Demu")) {
            str = "Expired at " + CompressHelper.m4957U0(bundle.getString("Demu"));
        } else {
            str = "Click Buy for activation";
        }
        textView2.setText(str);
        if (bundle.containsKey("Damu")) {
            textView2.setText(((Object) textView2.getText()) + " - Damu Available");
            textView2.setVisibility(8);
            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.19
                {
                    databasesFragment.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    new AlertDialog.Builder(databasesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("You are using Damu version of this database, in this mode you can see three random chapters and can't search the database").mo26266y("OK", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.19.1
                        {
                            View$OnClickListenerC459419.this = this;
                        }

                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            databasesFragment.this.f76454Z3.m4886p1(bundle);
                        }
                    }).m52864I();
                }
            };
        } else {
            onClickListener = new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.20
                {
                    databasesFragment.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Toast.makeText(databasesFragment.this.m44716w(), "You don't own this database, please click Buy for purchase.", 1).show();
                }
            };
        }
        materialRippleLayout.setOnClickListener(onClickListener);
        appCompatButton.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.21
            {
                databasesFragment.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                databasesFragment.this.m3820O2(bundle);
            }
        });
    }

    /* renamed from: P3 */
    public int m3817P3(ArrayList<Bundle> arrayList) {
        int i = 0;
        if (arrayList == null) {
            return 0;
        }
        Iterator<Bundle> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            i = i + it2.next().getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size() + 1;
        }
        return i;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: Q0 */
    public void mo3503Q0(Bundle bundle) {
        super.mo3503Q0(bundle);
        LocalBroadcastManager.m43863b(m44716w()).m43862c(this.f76458d4, new IntentFilter("reload"));
    }

    /* renamed from: Q2 */
    public void m3816Q2(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m3816Q2(file2);
            }
        }
        file.delete();
    }

    /* renamed from: R2 */
    public void m3815R2(final File file) {
        Observable m7156x1 = Observable.m7156x1(new ObservableOnSubscribe<Long>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.27
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<Long> observableEmitter) throws Throwable {
                databasesFragment.this.m3816Q2(file);
                observableEmitter.onComplete();
            }
        });
        final ProgressDialog show = ProgressDialog.show(m44716w(), "Deleting", "Please wait...", true);
        m7156x1.m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<Long>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.28
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Long l) throws Throwable {
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.29
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                show.dismiss();
                databasesFragment.this.f76454Z3.m4902k0();
                LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.30
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
                show.dismiss();
                databasesFragment.this.f76454Z3.m4902k0();
                LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reloadDownloads"));
            }
        });
    }

    /* renamed from: S2 */
    public Bundle m3814S2(int i, ArrayList<Bundle> arrayList) {
        Iterator<Bundle> it2 = arrayList.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it2.hasNext()) {
            Bundle next = it2.next();
            if (i == i2) {
                Bundle bundle = new Bundle();
                bundle.putString("Title", next.getString("title"));
                bundle.putInt("Row", 0);
                bundle.putInt("Section", i3);
                bundle.putInt("Row2", 1);
                bundle.putInt("Section2", i3 - 1);
                return bundle;
            }
            int size = i2 + next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            if (i <= size) {
                int size2 = (i - (size - next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size())) - 1;
                Bundle bundle2 = new Bundle();
                bundle2.putBundle("Item", (Bundle) next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(size2));
                bundle2.putInt("Row", size2);
                bundle2.putInt("Section", i3);
                return bundle2;
            }
            i2 = size + 1;
            i3++;
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: T0 */
    public void mo3545T0(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(C4804R.C4811menu.f87334menu_databases, menu);
        final MenuItem findItem = menu.findItem(C4804R.C4808id.f86770action_edit);
        final MenuItem findItem2 = menu.findItem(C4804R.C4808id.f86769action_done);
        this.f76443O3 = findItem;
        this.f76444P3 = findItem2;
        findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.5
            {
                databasesFragment.this = this;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                RecyclerView recyclerView;
                RecyclerView.Adapter adapter;
                ArrayList<Bundle> arrayList = databasesFragment.f76432e4;
                if (arrayList == null || arrayList.size() == 0) {
                    return true;
                }
                databasesFragment.this.f76445Q3.clearFocus();
                if (databasesFragment.this.f76436H3.getAdapter().getClass() == SearchDatabasesAdapter.class || databasesFragment.this.f76436H3.getAdapter().getClass() == SearchCollectionDatabasesAdapter.class) {
                    databasesFragment.this.f76445Q3.m51655i0("", false);
                    databasesFragment.this.f76445Q3.clearFocus();
                    if (databasesFragment.this.f76439K3) {
                        recyclerView = databasesFragment.this.f76436H3;
                        adapter = databasesFragment.this.f76446R3;
                    } else {
                        recyclerView = databasesFragment.this.f76436H3;
                        adapter = databasesFragment.this.f76437I3;
                    }
                    recyclerView.setAdapter(adapter);
                    databasesFragment.this.m3830F3();
                    iMDLogger.m3294f("HideKeyboard", "4");
                }
                findItem.setVisible(false);
                findItem2.setVisible(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(databasesFragment.this.m44716w());
                databasesFragment.this.f76447S3 = new RecyclerViewDragDropManager();
                databasesFragment.this.f76447S3.m16096R((NinePatchDrawable) databasesFragment.this.m44782a0().getDrawable(C4804R.C4807drawable.f86653material_shadow_z3_9));
                RecyclerView.Adapter m16065o = databasesFragment.this.f76447S3.m16065o(new EditDatabasesAdapter());
                databasesFragment.this.f76436H3.setLayoutManager(linearLayoutManager);
                databasesFragment.this.f76436H3.setAdapter(m16065o);
                databasesFragment.this.f76447S3.m16073h(databasesFragment.this.f76436H3);
                return false;
            }
        });
        findItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.6
            {
                databasesFragment.this = this;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                findItem.setVisible(true);
                findItem2.setVisible(false);
                databasesFragment.this.f76447S3.m16100N();
                if (databasesFragment.this.f76439K3) {
                    databasesFragment databasesfragment = databasesFragment.this;
                    databasesfragment.f76446R3 = new CollectionAdapter();
                    databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76446R3);
                    databasesFragment.this.f76436H3.setLayoutManager(databasesFragment.this.f76450V3);
                } else {
                    databasesFragment databasesfragment2 = databasesFragment.this;
                    databasesfragment2.f76437I3 = new DatabasesAdapter();
                    databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76437I3);
                }
                databasesFragment.this.f76454Z3.m4950W1(databasesFragment.f76432e4);
                databasesFragment.this.f76454Z3.m4947X1();
                databasesFragment.this.m3823M3();
                return false;
            }
        });
        if (m3829G3()) {
            this.f76443O3.setVisible(false);
            this.f76444P3.setVisible(true);
        } else {
            this.f76443O3.setVisible(true);
            this.f76444P3.setVisible(false);
        }
        menu.findItem(C4804R.C4808id.f86777action_navigation).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.7
            {
                databasesFragment.this = this;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ((mainActivity) databasesFragment.this.m44716w()).m3604I0();
                return true;
            }
        });
        SearchView searchView = (SearchView) menu.findItem(C4804R.C4808id.f86789action_search).getActionView();
        searchView.setIconified(true);
        searchView.onActionViewCollapsed();
        this.f76445Q3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Databases");
        final String str = this.f76442N3;
        this.f76445Q3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.8
            {
                databasesFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                databasesFragment.this.f76441M3 = true;
                databasesFragment.this.f76445Q3.m51655i0(str, false);
                if (databasesFragment.this.f76434F3 == null || databasesFragment.this.f76434F3.length() <= 0) {
                    return;
                }
                ArrayList<Bundle> arrayList = databasesFragment.f76433f4;
                if (arrayList == null || arrayList.size() == 0) {
                    databasesFragment.this.f76445Q3.m51655i0(databasesFragment.this.f76434F3, true);
                } else {
                    databasesFragment.this.f76445Q3.m51655i0(databasesFragment.this.f76434F3, false);
                    databasesFragment.this.f76436H3.getAdapter().m42860G();
                    databasesFragment.this.m3821N3();
                }
                databasesFragment.this.m3830F3();
                iMDLogger.m3294f("HideKeyboard", IcyHeaders.f35463C2);
            }
        }, 10L);
        this.f76441M3 = false;
        ((TextView) searchView.findViewById(C4804R.C4808id.search_src_text)).setText("");
        ((ImageView) searchView.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.9
            {
                databasesFragment.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecyclerView recyclerView;
                RecyclerView.Adapter adapter;
                databasesFragment.this.f76445Q3.m51655i0("", false);
                databasesFragment.this.f76445Q3.clearFocus();
                databasesFragment.this.m3812U2();
                if (databasesFragment.this.f76439K3) {
                    recyclerView = databasesFragment.this.f76436H3;
                    adapter = databasesFragment.this.f76446R3;
                } else {
                    recyclerView = databasesFragment.this.f76436H3;
                    adapter = databasesFragment.this.f76437I3;
                }
                recyclerView.setAdapter(adapter);
                databasesFragment.this.m3830F3();
                iMDLogger.m3294f("HideKeyboard", ExifInterface.f14403S4);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.10
            {
                databasesFragment.this = this;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(String str2) {
                RecyclerView recyclerView;
                RecyclerView.Adapter adapter;
                if (databasesFragment.this.f76441M3) {
                    databasesFragment.this.f76442N3 = str2;
                    databasesFragment.this.f76434F3 = str2;
                    if (str2.length() == 0) {
                        databasesFragment.this.m3812U2();
                        if (databasesFragment.this.f76439K3) {
                            recyclerView = databasesFragment.this.f76436H3;
                            adapter = databasesFragment.this.f76446R3;
                        } else {
                            recyclerView = databasesFragment.this.f76436H3;
                            adapter = databasesFragment.this.f76437I3;
                        }
                        recyclerView.setAdapter(adapter);
                    } else {
                        databasesFragment.this.m3824L3(str2);
                    }
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
        try {
            m44716w().setTitle("");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
    }

    /* renamed from: T2 */
    public void m3813T2(final File file, final File file2) {
        Observable m7156x1 = Observable.m7156x1(new ObservableOnSubscribe<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.31
            {
                databasesFragment.this = this;
            }

            /* JADX WARN: Code restructure failed: missing block: B:45:0x004b, code lost:
                if (r9 != null) goto L11;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x0071, code lost:
                if (r9 != null) goto L11;
             */
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void mo3518a(@io.reactivex.rxjava3.annotations.NonNull io.reactivex.rxjava3.core.ObservableEmitter<android.os.Bundle> r15) throws java.lang.Throwable {
                /*
                    r14 = this;
                    java.io.File r0 = r2
                    java.lang.String[] r0 = r0.list()
                    int r1 = r0.length
                    r2 = 0
                    r3 = 0
                L9:
                    if (r2 >= r1) goto Lcf
                    r4 = r0[r2]
                    int r3 = r3 + 1
                    net.imedicaldoctor.imd.Fragments.databasesFragment r5 = net.imedicaldoctor.imd.Fragments.databasesFragment.this
                    r6 = 0
                    net.imedicaldoctor.imd.Fragments.databasesFragment.m3795l3(r5, r6)
                    java.io.File r5 = new java.io.File
                    java.io.File r8 = r2
                    r5.<init>(r8, r4)
                    java.io.File r8 = new java.io.File
                    java.io.File r9 = r3
                    r8.<init>(r9, r4)
                    boolean r9 = r5.isDirectory()
                    if (r9 == 0) goto L74
                    java.io.File r9 = new java.io.File
                    java.lang.String r10 = "info.vbe"
                    r9.<init>(r5, r10)
                    boolean r11 = r9.exists()
                    java.lang.String r12 = "Title"
                    java.lang.String r13 = "Error"
                    if (r11 == 0) goto L56
                    net.imedicaldoctor.imd.Fragments.databasesFragment r10 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> L52
                    net.imedicaldoctor.imd.VBHelper r10 = r10.f76440L3     // Catch: java.lang.Exception -> L52
                    java.net.URI r11 = r9.toURI()     // Catch: java.lang.Exception -> L52
                    byte[] r11 = org.apache.commons.p024io.IOUtils.toByteArray(r11)     // Catch: java.lang.Exception -> L52
                    android.os.Bundle r9 = r10.m3429d(r11, r9)     // Catch: java.lang.Exception -> L52
                    if (r9 == 0) goto L74
                L4d:
                    java.lang.String r4 = r9.getString(r12)     // Catch: java.lang.Exception -> L52
                    goto L74
                L52:
                    android.util.Log.e(r13, r13)
                    goto L74
                L56:
                    java.io.File r9 = new java.io.File
                    r9.<init>(r8, r10)
                    boolean r10 = r9.exists()
                    if (r10 == 0) goto L74
                    net.imedicaldoctor.imd.Fragments.databasesFragment r10 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> L52
                    net.imedicaldoctor.imd.VBHelper r10 = r10.f76440L3     // Catch: java.lang.Exception -> L52
                    java.net.URI r11 = r9.toURI()     // Catch: java.lang.Exception -> L52
                    byte[] r11 = org.apache.commons.p024io.IOUtils.toByteArray(r11)     // Catch: java.lang.Exception -> L52
                    android.os.Bundle r9 = r10.m3429d(r11, r9)     // Catch: java.lang.Exception -> L52
                    if (r9 == 0) goto L74
                    goto L4d
                L74:
                    android.os.Bundle r9 = new android.os.Bundle
                    r9.<init>()
                    java.lang.String r10 = "counter"
                    r9.putLong(r10, r6)
                    net.imedicaldoctor.imd.Fragments.databasesFragment r6 = net.imedicaldoctor.imd.Fragments.databasesFragment.this
                    long r6 = r6.m3831E3(r5)
                    java.lang.Long r6 = java.lang.Long.valueOf(r6)
                    java.lang.String r7 = "total"
                    long r10 = r6.longValue()
                    r9.putLong(r7, r10)
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r7 = "Moving "
                    r6.append(r7)
                    r6.append(r3)
                    java.lang.String r7 = " of "
                    r6.append(r7)
                    int r7 = r0.length
                    r6.append(r7)
                    java.lang.String r7 = " : "
                    r6.append(r7)
                    r6.append(r4)
                    java.lang.String r4 = r6.toString()
                    java.lang.String r6 = "title"
                    r9.putString(r6, r4)
                    r15.onNext(r9)
                    net.imedicaldoctor.imd.Fragments.databasesFragment r4 = net.imedicaldoctor.imd.Fragments.databasesFragment.this     // Catch: java.lang.Exception -> Lc1
                    net.imedicaldoctor.imd.Fragments.databasesFragment.m3794m3(r4, r5, r8, r9, r15)     // Catch: java.lang.Exception -> Lc1
                    goto Lcb
                Lc1:
                    r4 = move-exception
                    java.lang.String r5 = "IOError"
                    java.lang.String r4 = r4.toString()
                    android.util.Log.e(r5, r4)
                Lcb:
                    int r2 = r2 + 1
                    goto L9
                Lcf:
                    java.io.File r0 = r2     // Catch: java.lang.Exception -> Ld4
                    org.apache.commons.p024io.FileUtils.deleteDirectory(r0)     // Catch: java.lang.Exception -> Ld4
                Ld4:
                    r15.onComplete()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.databasesFragment.C461631.mo3518a(io.reactivex.rxjava3.core.ObservableEmitter):void");
            }
        });
        final ProgressDialog show = ProgressDialog.show(m44716w(), "Migrating Databases", "Please wait...", true);
        m7156x1.m7300i6(Schedulers.m5370e()).m7193t4(AndroidSchedulers.m8490e()).m7319g6(new Consumer<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.32
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Bundle bundle) throws Throwable {
                show.setMessage(bundle.getString("title") + " (" + bundle.getLong("counter") + " of " + bundle.getLong("total") + " files)");
            }
        }, new Consumer<Throwable>() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.33
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Throwable th) throws Throwable {
                th.printStackTrace();
                iMDLogger.m3294f("Moving", th.getMessage());
            }
        }, new Action() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.34
            {
                databasesFragment.this = this;
            }

            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Throwable {
                show.dismiss();
                LocalBroadcastManager.m43863b(databasesFragment.this.m44716w()).m43861d(new Intent("reload"));
                databasesFragment.this.m3823M3();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, final Bundle bundle) {
        View view = this.f76435G3;
        if (view != null) {
            return view;
        }
        this.f76453Y3 = Typeface.createFromAsset(m44716w().getAssets(), "fonts/HelveticaNeue-Light.otf");
        this.f76452X3 = PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("ripple", true);
        this.f76454Z3 = new CompressHelper(m44716w());
        this.f76455a4 = new Bundle();
        this.f76439K3 = PreferenceManager.getDefaultSharedPreferences(m44716w()).getBoolean("GridView", true);
        this.f76440L3 = new VBHelper(m44716w());
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87143fragment_databases, viewGroup, false);
        this.f76435G3 = inflate;
        m3830F3();
        iMDLogger.m3294f("HideKeyboard", ExifInterface.f14411T4);
        this.f76442N3 = "";
        this.f76454Z3.m4993I0(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.3
            {
                databasesFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                databasesFragment.f76432e4 = databasesFragment.this.f76454Z3.m4947X1();
            }
        }, new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.4
            {
                databasesFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                FloatingActionButton floatingActionButton;
                Resources resources;
                int i;
                databasesFragment.this.m3823M3();
                databasesFragment.this.m44735q2(true);
                Bundle bundle2 = bundle;
                if (bundle2 != null && bundle2.containsKey("Query")) {
                    databasesFragment.this.f76434F3 = bundle.getString("Query");
                }
                databasesFragment databasesfragment = databasesFragment.this;
                databasesfragment.f76436H3 = (RecyclerView) databasesfragment.f76435G3.findViewById(C4804R.C4808id.f87001recycler_view);
                databasesFragment.this.f76436H3.setVisibility(0);
                ((TextView) databasesFragment.this.f76435G3.findViewById(C4804R.C4808id.f86955loading_text)).setVisibility(8);
                databasesFragment.this.f76449U3 = new DividerItemDecoration(databasesFragment.this.m44716w(), 1);
                databasesFragment databasesfragment2 = databasesFragment.this;
                databasesfragment2.f76450V3 = new GridLayoutManager(databasesfragment2.m44716w(), 2);
                databasesFragment.this.f76450V3.m43287N3(new GridLayoutManager.SpanSizeLookup() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.4.1
                    {
                        RunnableC46204.this = this;
                    }

                    @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                    /* renamed from: f */
                    public int mo3768f(int i2) {
                        Bundle m3814S2;
                        return (databasesFragment.this.f76436H3.getAdapter().getClass() == SearchCollectionDatabasesAdapter.class || (m3814S2 = databasesFragment.this.m3814S2(i2, databasesFragment.f76432e4)) == null || m3814S2.containsKey("Title")) ? 2 : 1;
                    }
                });
                if (databasesFragment.this.f76439K3) {
                    databasesFragment.this.f76436H3.m42906s1(databasesFragment.this.f76449U3);
                    databasesFragment databasesfragment3 = databasesFragment.this;
                    databasesfragment3.f76446R3 = new CollectionAdapter();
                    databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76446R3);
                    databasesFragment.this.f76436H3.setLayoutManager(databasesFragment.this.f76450V3);
                } else {
                    databasesFragment.this.f76436H3.m42923n(databasesFragment.this.f76449U3);
                    databasesFragment.this.f76436H3.setLayoutManager(new LinearLayoutManager(databasesFragment.this.m44716w(), 1, false));
                    databasesFragment.this.f76436H3.setItemAnimator(new DefaultItemAnimator());
                    databasesFragment databasesfragment4 = databasesFragment.this;
                    databasesfragment4.f76437I3 = new DatabasesAdapter();
                    databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76437I3);
                }
                databasesFragment.this.m3812U2();
                databasesFragment databasesfragment5 = databasesFragment.this;
                databasesfragment5.f76451W3 = (FloatingActionButton) databasesfragment5.f76435G3.findViewById(C4804R.C4808id.f86891fab);
                if (databasesFragment.this.f76439K3) {
                    floatingActionButton = databasesFragment.this.f76451W3;
                    resources = databasesFragment.this.m44716w().getResources();
                    i = C4804R.C4807drawable.f86638listview_icon;
                } else {
                    floatingActionButton = databasesFragment.this.f76451W3;
                    resources = databasesFragment.this.m44716w().getResources();
                    i = C4804R.C4807drawable.f86577gridview_icon;
                }
                floatingActionButton.setImageDrawable(resources.getDrawable(i));
                databasesFragment.this.f76451W3.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.databasesFragment.4.2
                    {
                        RunnableC46204.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        SharedPreferences.Editor putBoolean;
                        databasesFragment.this.f76443O3.setVisible(true);
                        databasesFragment.this.f76444P3.setVisible(false);
                        if (databasesFragment.this.f76439K3) {
                            databasesFragment.this.f76436H3.setLayoutManager(new LinearLayoutManager(databasesFragment.this.m44716w()));
                            databasesFragment.this.f76436H3.m42923n(databasesFragment.this.f76449U3);
                            databasesFragment.this.f76439K3 = false;
                            databasesFragment databasesfragment6 = databasesFragment.this;
                            databasesfragment6.f76437I3 = new DatabasesAdapter();
                            databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76437I3);
                            databasesFragment.this.f76451W3.setImageDrawable(databasesFragment.this.m44716w().getResources().getDrawable(C4804R.C4807drawable.f86577gridview_icon));
                            putBoolean = PreferenceManager.getDefaultSharedPreferences(databasesFragment.this.m44716w()).edit().putBoolean("GridView", false);
                        } else {
                            databasesFragment.this.f76436H3.setLayoutManager(databasesFragment.this.f76450V3);
                            databasesFragment.this.f76436H3.m42906s1(databasesFragment.this.f76449U3);
                            databasesFragment.this.f76439K3 = true;
                            databasesFragment databasesfragment7 = databasesFragment.this;
                            databasesfragment7.f76446R3 = new CollectionAdapter();
                            databasesFragment.this.f76436H3.setAdapter(databasesFragment.this.f76446R3);
                            databasesFragment.this.f76451W3.setImageDrawable(databasesFragment.this.m44716w().getResources().getDrawable(C4804R.C4807drawable.f86638listview_icon));
                            putBoolean = PreferenceManager.getDefaultSharedPreferences(databasesFragment.this.m44716w()).edit().putBoolean("GridView", true);
                        }
                        putBoolean.commit();
                        databasesFragment.this.m3812U2();
                    }
                });
                if (PreferenceManager.getDefaultSharedPreferences(databasesFragment.this.m44716w()).getBoolean("swipedelete", true)) {
                    databasesFragment.this.m3832D3();
                }
                databasesFragment.this.m3835A3();
            }
        });
        return inflate;
    }

    /* renamed from: U2 */
    public void m3812U2() {
        ArrayList<Bundle> arrayList = f76432e4;
        if (arrayList == null || arrayList.size() == 0) {
            m3819O3("No databases is installed. Tap to go to store tab to buy and download databases");
        } else {
            m3821N3();
        }
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: V0 */
    public void mo3638V0() {
        super.mo3638V0();
        LocalBroadcastManager.m43863b(m44716w()).m43859f(this.f76458d4);
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }
}
