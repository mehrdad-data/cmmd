package net.imedicaldoctor.imd.Data;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.ViewHolders.HeaderCellViewHolder;
import net.imedicaldoctor.imd.ViewHolders.MessageViewHolder;
import net.imedicaldoctor.imd.ViewHolders.RippleTextFullViewHolder;

/* loaded from: classes2.dex */
public class HistoryAdapter extends RecyclerView.Adapter {

    /* renamed from: l */
    private static final int f73859l = 0;

    /* renamed from: m */
    private static final int f73860m = 1;

    /* renamed from: n */
    private static final int f73861n = 2;

    /* renamed from: o */
    private static final int f73862o = 3;

    /* renamed from: d */
    public Context f73863d;

    /* renamed from: e */
    public String f73864e;

    /* renamed from: f */
    public int f73865f;

    /* renamed from: g */
    public String f73866g;

    /* renamed from: h */
    public ArrayList<Bundle> f73867h;

    /* renamed from: i */
    public ArrayList<Bundle> f73868i;

    /* renamed from: j */
    public DrawerLayout f73869j;

    /* renamed from: k */
    public CompressHelper f73870k;

    /* loaded from: classes2.dex */
    public static class DatabaseCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f73880I;

        /* renamed from: J */
        public TextView f73881J;

        /* renamed from: K */
        public ImageView f73882K;

        /* renamed from: L */
        public MaterialRippleLayout f73883L;

        /* renamed from: M */
        public AppCompatButton f73884M;

        public DatabaseCellViewHolder(View view) {
            super(view);
            this.f73880I = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f73882K = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f73883L = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
            this.f73884M = (AppCompatButton) view.findViewById(C4804R.C4808id.f86830buy_button);
            this.f73881J = (TextView) view.findViewById(C4804R.C4808id.f86856database_subtitle);
        }
    }

    public HistoryAdapter(Context context, DrawerLayout drawerLayout) {
        this.f73870k = new CompressHelper(context);
        this.f73863d = context;
        m4840d0();
        CompressHelper compressHelper = this.f73870k;
        this.f73868i = compressHelper.m4946Y(compressHelper.m4971P1(), "SELECT m1.* FROM recent m1 LEFT JOIN recent m2 ON (m1.dbAddress = m2.dbAddress AND m1.dbName = m2.dbName AND m1.id < m2.id) WHERE m2.id IS NULL order by id desc limit 30");
        if (this.f73867h == null) {
            this.f73867h = new ArrayList<>();
        }
        if (this.f73868i == null) {
            this.f73868i = new ArrayList<>();
        }
        this.f73869j = drawerLayout;
        this.f73866g = "No History";
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: C */
    public int mo3384C(int i) {
        if (this.f73867h.size() == 0 && this.f73868i.size() == 0) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        if (i < this.f73867h.size() + 1) {
            return 2;
        }
        if (i == this.f73867h.size() + 1) {
            return 1;
        }
        return i > this.f73867h.size() + 1 ? 3 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: R */
    public void mo3363R(RecyclerView.ViewHolder viewHolder, int i) {
        View view;
        View.OnLongClickListener onLongClickListener;
        int m42556F = viewHolder.m42556F();
        if (m42556F == 0) {
            ((MessageViewHolder) viewHolder).f83248I.setText(this.f73866g);
        } else if (m42556F == 1) {
            ((HeaderCellViewHolder) viewHolder).f83245I.setText(i == 0 ? "Recent Databases" : "Recent Documents");
        } else {
            if (m42556F == 2) {
                final Bundle bundle = this.f73867h.get(i - 1);
                DatabaseCellViewHolder databaseCellViewHolder = (DatabaseCellViewHolder) viewHolder;
                databaseCellViewHolder.f73884M.setVisibility(8);
                databaseCellViewHolder.f73881J.setVisibility(8);
                if (bundle.containsKey("home")) {
                    databaseCellViewHolder.f73882K.setImageDrawable(this.f73863d.getResources().getDrawable(C4804R.C4807drawable.f86607imd_home));
                    databaseCellViewHolder.f73880I.setText("Home");
                    databaseCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Data.HistoryAdapter.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            HistoryAdapter.this.m4839e0();
                            HistoryAdapter.this.f73869j.m45138h();
                            HistoryAdapter.this.f73870k.m4989J1(true);
                            HistoryAdapter.this.f73870k.m4989J1(false);
                        }
                    });
                    return;
                }
                databaseCellViewHolder.f73880I.setText(bundle.getString("dbTitle"));
                String m5009D = CompressHelper.m5009D(bundle);
                if (m5009D.contains("file:///android_asset/") || new File(m5009D).exists()) {
                    Glide.m40318D(this.f73863d).mo40145t(m5009D).m40191t2(databaseCellViewHolder.f73882K);
                } else {
                    databaseCellViewHolder.f73882K.setImageDrawable(this.f73863d.getResources().getDrawable(C4804R.C4807drawable.f86674placeholder));
                }
                viewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Data.HistoryAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        HistoryAdapter.this.m4839e0();
                        HistoryAdapter.this.f73869j.m45138h();
                        Bundle m4969Q0 = HistoryAdapter.this.f73870k.m4969Q0("Name", bundle.getString("dbName"));
                        if (m4969Q0 == null) {
                            CompressHelper.m4921e2(HistoryAdapter.this.f73863d, "This database doesn't exist anymore", 0);
                        } else {
                            HistoryAdapter.this.f73870k.m4886p1(m4969Q0);
                        }
                    }
                });
                view = viewHolder.f18491a;
                onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Data.HistoryAdapter.3
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view2) {
                        CompressHelper compressHelper = HistoryAdapter.this.f73870k;
                        String m4971P1 = compressHelper.m4971P1();
                        compressHelper.m4885q(m4971P1, "delete from dbrecent where dbName='" + bundle.getString("dbName") + "'");
                        HistoryAdapter.this.m4840d0();
                        HistoryAdapter.this.m42860G();
                        return true;
                    }
                };
            } else if (m42556F != 3) {
                return;
            } else {
                final Bundle bundle2 = this.f73868i.get((i - this.f73867h.size()) - 2);
                RippleTextFullViewHolder rippleTextFullViewHolder = (RippleTextFullViewHolder) viewHolder;
                rippleTextFullViewHolder.f83284I.setText(bundle2.getString("dbDocName").trim());
                rippleTextFullViewHolder.f83285J.setText(bundle2.getString("dbTitle").trim());
                rippleTextFullViewHolder.f83288M.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Data.HistoryAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        HistoryAdapter.this.m4839e0();
                        HistoryAdapter.this.f73869j.m45138h();
                        Bundle m4969Q0 = HistoryAdapter.this.f73870k.m4969Q0("Name", bundle2.getString("dbName"));
                        if (m4969Q0 == null) {
                            CompressHelper.m4921e2(HistoryAdapter.this.f73863d, "This database doesn't exist anymore", 0);
                        } else {
                            HistoryAdapter.this.f73870k.m4883q1(m4969Q0, bundle2.getString("dbAddress"), null, null);
                        }
                    }
                });
                view = rippleTextFullViewHolder.f83288M;
                onLongClickListener = new View.OnLongClickListener() { // from class: net.imedicaldoctor.imd.Data.HistoryAdapter.5
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view2) {
                        CompressHelper compressHelper = HistoryAdapter.this.f73870k;
                        String m4971P1 = compressHelper.m4971P1();
                        compressHelper.m4885q(m4971P1, "delete from recent where dbName='" + bundle2.getString("dbName") + "' AND dbAddress='" + bundle2.getString("dbAddress") + "'");
                        HistoryAdapter historyAdapter = HistoryAdapter.this;
                        CompressHelper compressHelper2 = historyAdapter.f73870k;
                        historyAdapter.f73868i = compressHelper2.m4946Y(compressHelper2.m4971P1(), "SELECT m1.* FROM recent m1 LEFT JOIN recent m2 ON (m1.dbAddress = m2.dbAddress AND m1.dbName = m2.dbName AND m1.id < m2.id) WHERE m2.id IS NULL order by id desc limit 30");
                        HistoryAdapter.this.m42860G();
                        return true;
                    }
                };
            }
            view.setOnLongClickListener(onLongClickListener);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: T */
    public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new MessageViewHolder(this.f73863d, LayoutInflater.from(this.f73863d).inflate(C4804R.C4810layout.f87220list_view_item_card_notfound, viewGroup, false));
        } else if (i == 1) {
            return new HeaderCellViewHolder(LayoutInflater.from(this.f73863d).inflate(C4804R.C4810layout.f87230list_view_item_database_header, viewGroup, false));
        } else {
            if (i == 2) {
                return new DatabaseCellViewHolder(LayoutInflater.from(this.f73863d).inflate(C4804R.C4810layout.f87232list_view_item_database_ripple, viewGroup, false));
            }
            if (i == 3) {
                RippleTextFullViewHolder rippleTextFullViewHolder = new RippleTextFullViewHolder(LayoutInflater.from(this.f73863d).inflate(C4804R.C4810layout.f87260list_view_item_ripple_recent, viewGroup, false));
                rippleTextFullViewHolder.f83286K.setVisibility(8);
                return rippleTextFullViewHolder;
            }
            return null;
        }
    }

    /* renamed from: d0 */
    public void m4840d0() {
        CompressHelper compressHelper = this.f73870k;
        ArrayList<Bundle> m4946Y = compressHelper.m4946Y(compressHelper.m4971P1(), "SELECT distinct(dbName), dbTitle, dbIcon FROM dbrecent order by id desc limit 2");
        this.f73867h = m4946Y;
        if (m4946Y == null) {
            this.f73867h = new ArrayList<>();
        }
        Bundle bundle = new Bundle();
        bundle.putString("home", "");
        this.f73867h.add(bundle);
    }

    /* renamed from: e0 */
    public void m4839e0() {
        try {
            ((InputMethodManager) ((Activity) this.f73863d).getSystemService("input_method")).hideSoftInputFromWindow(((Activity) this.f73863d).getCurrentFocus().getWindowToken(), 0);
            if (((Activity) this.f73863d).getCurrentFocus() != null) {
                ((Activity) this.f73863d).getCurrentFocus().clearFocus();
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: s */
    public int mo3359s() {
        try {
            if (this.f73867h.size() == 0 && this.f73868i.size() == 0) {
                return 1;
            }
            return this.f73867h.size() + this.f73868i.size() + 2;
        } catch (Exception unused) {
            return 0;
        }
    }
}
