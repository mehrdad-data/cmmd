package net.imedicaldoctor.imd.Fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.audio.AacUtil;
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
import com.itextpdf.tool.xml.css.CSS;
import com.timehop.stickyheadersrecyclerview.ItemVisibilityAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Utils.MaterialRippleLayout;
import net.imedicaldoctor.imd.ViewHolders.HeaderCellViewHolder;
import net.imedicaldoctor.imd.ViewHolders.StatusAdapter;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p024io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class favoritesFragment extends SearchHelperFragment {

    /* renamed from: b4 */
    private FavoriteAdapter f76793b4;

    /* renamed from: c4 */
    private ArrayList<Bundle> f76794c4;

    /* renamed from: d4 */
    private ArrayList<Bundle> f76795d4;

    /* renamed from: e4 */
    private boolean f76796e4;

    /* renamed from: f4 */
    private RecyclerView f76797f4;

    /* renamed from: g4 */
    private String f76798g4;

    /* renamed from: h4 */
    private boolean f76799h4;

    /* renamed from: i4 */
    private Activity f76800i4;

    /* renamed from: j4 */
    private boolean f76801j4;

    /* renamed from: k4 */
    private MenuItem f76802k4;

    /* renamed from: l4 */
    private MenuItem f76803l4;

    /* renamed from: m4 */
    private ArrayList<String> f76804m4;

    /* renamed from: n4 */
    private StickyRecyclerHeadersDecoration f76805n4;

    /* renamed from: o4 */
    StickyRecyclerHeadersTouchListener f76806o4;

    /* renamed from: p4 */
    private RecyclerViewDragDropManager f76807p4;

    /* renamed from: q4 */
    private boolean f76808q4;

    /* renamed from: r4 */
    public CompressHelper f76809r4;

    /* renamed from: s4 */
    private BroadcastReceiver f76810s4 = new BroadcastReceiver() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            iMDLogger.m3296d("favorite", "received favorite changed message");
            favoritesFragment.this.m3633p3();
        }
    };

    /* loaded from: classes2.dex */
    public static class AddCellViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76833I;

        public AddCellViewHolder(View view) {
            super(view);
            this.f76833I = (TextView) view.findViewById(C4804R.C4808id.text);
        }
    }

    /* loaded from: classes2.dex */
    public static class EditFavoriteCellViewHolder extends AbstractDraggableItemViewHolder implements DraggableItemViewHolder {

        /* renamed from: J */
        public TextView f76834J;

        /* renamed from: K */
        public ImageView f76835K;

        /* renamed from: L */
        public LinearLayout f76836L;

        /* renamed from: M */
        public ImageView f76837M;

        public EditFavoriteCellViewHolder(View view) {
            super(view);
            this.f76834J = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76835K = (ImageView) view.findViewById(C4804R.C4808id.f86879drag_indicator);
            this.f76836L = (LinearLayout) view.findViewById(C4804R.C4808id.f86850container_view);
            this.f76837M = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public class EditFavoritesAdapter extends RecyclerView.Adapter implements DraggableItemAdapter {

        /* renamed from: d */
        HashMap<String, Integer> f76838d;

        /* renamed from: e */
        int f76839e;

        public EditFavoritesAdapter() {
            HashMap<String, Integer> hashMap = new HashMap<>();
            this.f76838d = hashMap;
            this.f76839e = 0;
            hashMap.put("AddSection", Integer.valueOf((int) AacUtil.f32624f));
            this.f76838d.put("EditSectionas", Integer.valueOf(this.f76839e));
            this.f76839e++;
            if (favoritesFragment.this.f76795d4 == null) {
                return;
            }
            Iterator it2 = favoritesFragment.this.f76795d4.iterator();
            while (it2.hasNext()) {
                this.f76838d.put("EditSection" + ((Bundle) it2.next()).getString("title"), Integer.valueOf(this.f76839e));
                this.f76839e = this.f76839e + 1;
            }
            Iterator it3 = favoritesFragment.this.f76795d4.iterator();
            while (it3.hasNext()) {
                Bundle bundle = (Bundle) it3.next();
                this.f76838d.put("Section" + bundle.getString("title"), Integer.valueOf(this.f76839e));
                this.f76839e = this.f76839e + 1;
                for (int i = 0; i < bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size(); i++) {
                    Bundle bundle2 = (Bundle) bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i);
                    this.f76838d.put("Database" + bundle2.getString("dbName") + bundle2.getString("dbAddress"), Integer.valueOf(this.f76839e));
                    this.f76839e = this.f76839e + 1;
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
            if (i < favoritesFragment.this.f76795d4.size() + 1) {
                if (i == 0) {
                    num = this.f76838d.get("EditSectionas");
                    return num.intValue();
                }
                sb2 = "EditSection" + ((Bundle) favoritesFragment.this.f76795d4.get(i - 1)).getString("title");
            } else if (i == favoritesFragment.this.f76795d4.size() + 1) {
                return 100000L;
            } else {
                favoritesFragment favoritesfragment = favoritesFragment.this;
                Bundle m3634o3 = favoritesfragment.m3634o3((i - favoritesFragment.this.f76795d4.size()) - 2, favoritesfragment.f76795d4);
                if (m3634o3.containsKey("Item")) {
                    m3634o3 = m3634o3.getBundle("Item");
                    sb = new StringBuilder();
                    sb.append("Database");
                    sb.append(m3634o3.getString("dbName"));
                    str = "dbAddress";
                } else {
                    sb = new StringBuilder();
                    sb.append("Section");
                    str = "Title";
                }
                sb.append(m3634o3.getString(str));
                sb2 = sb.toString();
                if (!this.f76838d.containsKey(sb2)) {
                    return -1L;
                }
            }
            num = this.f76838d.get(sb2);
            return num.intValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            if (i < favoritesFragment.this.f76795d4.size() + 1) {
                return i == 0 ? 1 : 2;
            } else if (i == favoritesFragment.this.f76795d4.size() + 1) {
                return 3;
            } else {
                favoritesFragment favoritesfragment = favoritesFragment.this;
                return favoritesfragment.m3634o3((i - favoritesFragment.this.f76795d4.size()) - 2, favoritesfragment.f76795d4).containsKey("Title") ? 1 : 0;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            if (viewHolder.m42556F() == 3) {
                AddCellViewHolder addCellViewHolder = (AddCellViewHolder) viewHolder;
                addCellViewHolder.f76833I.setText("Add Section");
                addCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        final EditText editText = new EditText(favoritesFragment.this.m44716w());
                        editText.setTextColor(favoritesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        new AlertDialog.Builder(favoritesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Enter Section Name").setView(editText).mo26266y("Add", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.1.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                String obj = editText.getText().toString();
                                if (obj.length() == 0) {
                                    CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "You must enter a name for the section", 1);
                                    return;
                                }
                                if (EditFavoritesAdapter.this.f76838d.containsKey("EditSection" + obj)) {
                                    CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "You already have a section with this name", 1);
                                    return;
                                }
                                Bundle bundle = new Bundle();
                                bundle.putString("title", obj);
                                bundle.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, new ArrayList<>());
                                favoritesFragment.this.f76795d4.add(bundle);
                                favoritesFragment.this.f76797f4.getAdapter().m42860G();
                                EditFavoritesAdapter editFavoritesAdapter = EditFavoritesAdapter.this;
                                editFavoritesAdapter.f76839e++;
                                editFavoritesAdapter.f76838d.put("EditSection" + obj, Integer.valueOf(EditFavoritesAdapter.this.f76839e));
                            }
                        }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                            }
                        }).m52864I();
                    }
                });
            } else if (i < favoritesFragment.this.f76795d4.size() + 1) {
                if (i == 0) {
                    ((HeaderCellViewHolder) viewHolder).f83245I.setText("Sections");
                    return;
                }
                final String string = ((Bundle) favoritesFragment.this.f76795d4.get(i - 1)).getString("title");
                EditHeaderCellViewHolder editHeaderCellViewHolder = (EditHeaderCellViewHolder) viewHolder;
                editHeaderCellViewHolder.f76862J.setText(string);
                editHeaderCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        final EditText editText = new EditText(favoritesFragment.this.m44716w());
                        editText.setText(string);
                        editText.setTextColor(favoritesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        new AlertDialog.Builder(favoritesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Edit Section Name").setView(editText).mo26266y("Edit", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.2.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                String obj = editText.getText().toString();
                                if (obj.length() == 0) {
                                    CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "You must enter a name for the section", 1);
                                } else if (obj.equals(string)) {
                                } else {
                                    if (EditFavoritesAdapter.this.f76838d.containsKey("EditSection" + obj)) {
                                        CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "You already have a section with this name", 1);
                                        return;
                                    }
                                    Bundle bundle = (Bundle) favoritesFragment.this.f76795d4.get(i - 1);
                                    bundle.remove("title");
                                    bundle.putString("title", obj);
                                    favoritesFragment.this.f76797f4.getAdapter().m42860G();
                                    EditFavoritesAdapter editFavoritesAdapter = EditFavoritesAdapter.this;
                                    editFavoritesAdapter.f76839e++;
                                    editFavoritesAdapter.f76838d.put("EditSection" + obj, Integer.valueOf(EditFavoritesAdapter.this.f76839e));
                                }
                            }
                        }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.2.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                            }
                        }).m52864I();
                    }
                });
                editHeaderCellViewHolder.f76865M.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ArrayList parcelableArrayList = ((Bundle) favoritesFragment.this.f76795d4.get(i - 1)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
                        if (parcelableArrayList != null && parcelableArrayList.size() > 0) {
                            CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "First delete favorite items inside this section", 1);
                            return;
                        }
                        favoritesFragment.this.f76795d4.remove(i - 1);
                        favoritesFragment.this.f76797f4.getAdapter().m42860G();
                    }
                });
            } else {
                favoritesFragment favoritesfragment = favoritesFragment.this;
                final Bundle m3634o3 = favoritesfragment.m3634o3((i - favoritesFragment.this.f76795d4.size()) - 2, favoritesfragment.f76795d4);
                if (!m3634o3.containsKey("Item")) {
                    ((HeaderCellViewHolder) viewHolder).f83245I.setText(m3634o3.getString("Title"));
                    return;
                }
                Bundle bundle = m3634o3.getBundle("Item");
                final EditFavoriteCellViewHolder editFavoriteCellViewHolder = (EditFavoriteCellViewHolder) viewHolder;
                editFavoriteCellViewHolder.f76834J.setText(bundle.getString("dbDocName"));
                editFavoriteCellViewHolder.f76837M.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        int i2 = m3634o3.getInt("Section");
                        int i3 = m3634o3.getInt("Row");
                        Bundle bundle2 = m3634o3.getBundle("Item");
                        ((Bundle) favoritesFragment.this.f76795d4.get(i2)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).remove(i3);
                        CompressHelper compressHelper = favoritesFragment.this.f76809r4;
                        String m4972P0 = compressHelper.m4972P0();
                        compressHelper.m4885q(m4972P0, "Delete from favorites where _id=" + bundle2.getString("_id"));
                        favoritesFragment.this.f76797f4.getAdapter().m42860G();
                    }
                });
                final String string2 = bundle.getString("dbDocName");
                editFavoriteCellViewHolder.f18491a.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        final EditText editText = new EditText(favoritesFragment.this.m44716w());
                        editText.setText(string2);
                        editText.setTextColor(favoritesFragment.this.m44782a0().getColor(C4804R.C4806color.f86093black));
                        new AlertDialog.Builder(favoritesFragment.this.m44716w(), C4804R.style.f88094alertDialogTheme).mo26292l("Edit Document Name").setView(editText).mo26266y("Edit", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.5.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                String obj = editText.getText().toString();
                                if (obj.length() == 0) {
                                    CompressHelper.m4921e2(favoritesFragment.this.m44716w(), "You must enter a name for the document", 1);
                                } else if (obj.equals(string2)) {
                                } else {
                                    Bundle bundle2 = (Bundle) ((Bundle) favoritesFragment.this.f76795d4.get(m3634o3.getInt("Section"))).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(m3634o3.getInt("Row"));
                                    bundle2.remove("dbDocName");
                                    bundle2.putString("dbDocName", obj);
                                    editFavoriteCellViewHolder.f76834J.setText(obj);
                                    EditFavoritesAdapter editFavoritesAdapter = EditFavoritesAdapter.this;
                                    editFavoritesAdapter.f76839e++;
                                    editFavoritesAdapter.f76838d.put("Database" + bundle2.getString("dbName") + bundle2.getString("dbAddress"), Integer.valueOf(EditFavoritesAdapter.this.f76839e));
                                }
                            }
                        }).mo26284p("Cancel", new DialogInterface.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.EditFavoritesAdapter.5.1
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
                return new EditFavoriteCellViewHolder(LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87240list_view_item_favorite_edit, viewGroup, false));
            }
            if (i == 1) {
                return new HeaderCellViewHolder(LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87230list_view_item_database_header, viewGroup, false));
            }
            if (i == 2) {
                return new EditHeaderCellViewHolder(LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87231list_view_item_database_header_edit, viewGroup, false));
            }
            if (i == 3) {
                return new AddCellViewHolder(LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87219list_view_item_add, viewGroup, false));
            }
            return null;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
        /* renamed from: g */
        public void mo3619g(int i, int i2) {
            if (i > 0 && i < favoritesFragment.this.f76795d4.size() + 1) {
                int i3 = i - 1;
                favoritesFragment.this.f76795d4.remove(i3);
                favoritesFragment.this.f76795d4.add(i2 - 1, (Bundle) favoritesFragment.this.f76795d4.get(i3));
                return;
            }
            favoritesFragment favoritesfragment = favoritesFragment.this;
            Bundle m3634o3 = favoritesfragment.m3634o3((i - favoritesFragment.this.f76795d4.size()) - 2, favoritesfragment.f76795d4);
            int i4 = m3634o3.getInt("Row");
            int i5 = m3634o3.getInt("Section");
            Bundle bundle = (Bundle) ((Bundle) favoritesFragment.this.f76795d4.get(i5)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i4);
            favoritesFragment favoritesfragment2 = favoritesFragment.this;
            Bundle m3634o32 = favoritesfragment2.m3634o3((i2 - favoritesFragment.this.f76795d4.size()) - 2, favoritesfragment2.f76795d4);
            int i6 = m3634o32.getInt("Row");
            int i7 = m3634o32.getInt("Section");
            iMDLogger.m3294f("Staring Drag", "Section " + i5 + " , Row : " + i4 + ", Section2:" + i7 + ", row2:" + i6);
            ((Bundle) favoritesFragment.this.f76795d4.get(i5)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).remove(i4);
            if (m3634o32.containsKey("Title")) {
                int i8 = m3634o32.getInt("Row2");
                int i9 = m3634o32.getInt("Section2");
                iMDLogger.m3294f("Staring Drag", "Section22:" + i9 + ", row22:" + i8);
                if (i5 >= i7) {
                    if (((Bundle) favoritesFragment.this.f76795d4.get(i9)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size() == 0) {
                        i8 = 0;
                    }
                    ((Bundle) favoritesFragment.this.f76795d4.get(i9)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).add(i8, bundle);
                    return;
                }
            }
            ((Bundle) favoritesFragment.this.f76795d4.get(i7)).getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).add(i6, bundle);
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
                imageView = editHeaderCellViewHolder.f76863K;
                linearLayout = editHeaderCellViewHolder.f76864L;
            } else {
                EditFavoriteCellViewHolder editFavoriteCellViewHolder = (EditFavoriteCellViewHolder) viewHolder;
                imageView = editFavoriteCellViewHolder.f76835K;
                linearLayout = editFavoriteCellViewHolder.f76836L;
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
            if (favoritesFragment.this.f76795d4 == null || favoritesFragment.this.f76795d4.size() == 0) {
                return 0;
            }
            favoritesFragment favoritesfragment = favoritesFragment.this;
            return favoritesfragment.m3639M3(favoritesfragment.f76795d4) + favoritesFragment.this.f76795d4.size() + 2;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
        /* renamed from: w */
        public ItemDraggableRange mo3617w(RecyclerView.ViewHolder viewHolder, int i) {
            if (i <= 0 || i >= favoritesFragment.this.f76795d4.size() + 1) {
                return new ItemDraggableRange(favoritesFragment.this.f76795d4.size() + 3, mo3359s() - 1);
            }
            iMDLogger.m3290j("RequestingRange", "Range requested");
            return new ItemDraggableRange(1, favoritesFragment.this.f76795d4.size());
        }
    }

    /* loaded from: classes2.dex */
    public static class EditHeaderCellViewHolder extends AbstractDraggableItemViewHolder implements DraggableItemViewHolder {

        /* renamed from: J */
        public TextView f76862J;

        /* renamed from: K */
        public ImageView f76863K;

        /* renamed from: L */
        public LinearLayout f76864L;

        /* renamed from: M */
        public ImageView f76865M;

        public EditHeaderCellViewHolder(View view) {
            super(view);
            this.f76862J = (TextView) view.findViewById(C4804R.C4808id.f86913header_text);
            this.f76863K = (ImageView) view.findViewById(C4804R.C4808id.f86879drag_indicator);
            this.f76864L = (LinearLayout) view.findViewById(C4804R.C4808id.f86850container_view);
            this.f76865M = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes2.dex */
    public class FavoriteAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter {
        public FavoriteAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: C */
        public int mo3384C(int i) {
            favoritesFragment favoritesfragment;
            ArrayList<Bundle> arrayList;
            if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76795d4;
            } else {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76794c4;
            }
            return favoritesfragment.m3635n3(i, arrayList).containsKey("Item") ? 0 : 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R */
        public void mo3363R(RecyclerView.ViewHolder viewHolder, final int i) {
            favoritesFragment favoritesfragment;
            ArrayList<Bundle> arrayList;
            if (viewHolder.m42556F() == 1) {
                return;
            }
            FavoriteItemViewHolder favoriteItemViewHolder = (FavoriteItemViewHolder) viewHolder;
            if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76795d4;
            } else {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76794c4;
            }
            Bundle bundle = favoritesfragment.m3635n3(i, arrayList).getBundle("Item");
            favoriteItemViewHolder.f76872I.setText(bundle.getString("dbDocName"));
            final String string = bundle.getString("_id");
            if (favoritesFragment.this.f76801j4) {
                favoriteItemViewHolder.f76873J.setVisibility(0);
                favoriteItemViewHolder.f76873J.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.FavoriteAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        favoritesFragment.this.m3647F3(string);
                        favoritesFragment.this.m3633p3();
                    }
                });
                favoriteItemViewHolder.f76874K.setRippleOverlay(false);
            } else {
                favoriteItemViewHolder.f76874K.setRippleOverlay(true);
                favoriteItemViewHolder.f76873J.setVisibility(8);
            }
            favoriteItemViewHolder.f76874K.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.FavoriteAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    favoritesFragment favoritesfragment2;
                    int i2;
                    ArrayList<Bundle> arrayList2;
                    CompressHelper compressHelper;
                    if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                        favoritesfragment2 = favoritesFragment.this;
                        i2 = i;
                        arrayList2 = favoritesfragment2.f76795d4;
                    } else {
                        favoritesfragment2 = favoritesFragment.this;
                        i2 = i;
                        arrayList2 = favoritesfragment2.f76794c4;
                    }
                    Bundle bundle2 = favoritesfragment2.m3635n3(i2, arrayList2).getBundle("Item");
                    Bundle m4938a1 = favoritesFragment.this.f76809r4.m4938a1(bundle2.getString("dbName"));
                    if (m4938a1 == null) {
                        CompressHelper.m4921e2(favoritesFragment.this.m3646G3(), "You don't have this database installed", 1);
                        return;
                    }
                    String string2 = m4938a1.getString("Type");
                    String string3 = bundle2.getString("dbAddress");
                    favoritesFragment.this.f75212I3 = m4938a1;
                    if (string2.equals("uworld")) {
                        compressHelper = favoritesFragment.this.f76809r4;
                        string3 = "question-" + string3;
                    } else {
                        compressHelper = favoritesFragment.this.f76809r4;
                    }
                    compressHelper.m4883q1(m4938a1, string3, null, null);
                    favoritesFragment.this.m4330Y2();
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T */
        public RecyclerView.ViewHolder mo3362T(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new EmptyViewHolder(LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87245list_view_item_header_keeper, viewGroup, false));
            }
            View inflate = LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87239list_view_item_favorite, viewGroup, false);
            favoritesFragment favoritesfragment = favoritesFragment.this;
            return new FavoriteItemViewHolder(favoritesfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: n */
        public RecyclerView.ViewHolder mo3366n(ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(favoritesFragment.this.m44716w()).inflate(C4804R.C4810layout.f87276list_view_item_search_header, viewGroup, false);
            favoritesFragment favoritesfragment = favoritesFragment.this;
            return new SearchHeaderViewHolder(favoritesfragment.m44716w(), inflate);
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: o */
        public void mo3365o(RecyclerView.ViewHolder viewHolder, int i) {
            favoritesFragment favoritesfragment;
            ArrayList<Bundle> arrayList;
            SearchHeaderViewHolder searchHeaderViewHolder = (SearchHeaderViewHolder) viewHolder;
            if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76795d4;
            } else {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76794c4;
            }
            Bundle m3635n3 = favoritesfragment.m3635n3(i, arrayList);
            Bundle m4934b1 = favoritesFragment.this.f76809r4.m4934b1(m3635n3.getString("Database"));
            if (m4934b1 == null) {
                searchHeaderViewHolder.f76886I.setVisibility(8);
                searchHeaderViewHolder.f76887J.setText(m3635n3.getString("Database"));
                return;
            }
            searchHeaderViewHolder.f76886I.setVisibility(0);
            searchHeaderViewHolder.f76887J.setText(m4934b1.getString("Title"));
            String m5012C = CompressHelper.m5012C(m4934b1);
            if (!m5012C.contains("file:///android_asset/")) {
                searchHeaderViewHolder.f76886I.setImageURI(Uri.parse(m5012C));
                return;
            }
            try {
                InputStream open = favoritesFragment.this.m44716w().getAssets().open(m5012C.replace("file:///android_asset/", ""));
                searchHeaderViewHolder.f76886I.setImageBitmap(BitmapFactory.decodeStream(open));
                open.close();
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                e.printStackTrace();
            }
        }

        @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
        /* renamed from: q */
        public long mo3364q(int i) {
            favoritesFragment favoritesfragment;
            ArrayList<Bundle> arrayList;
            if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76795d4;
            } else {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76794c4;
            }
            return Long.valueOf(favoritesfragment.m3636m3(i, arrayList)).longValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: s */
        public int mo3359s() {
            favoritesFragment favoritesfragment;
            ArrayList<Bundle> arrayList;
            if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76795d4;
            } else {
                favoritesfragment = favoritesFragment.this;
                arrayList = favoritesfragment.f76794c4;
            }
            return favoritesfragment.m3641L3(arrayList);
        }
    }

    /* loaded from: classes2.dex */
    public class FavoriteItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public TextView f76872I;

        /* renamed from: J */
        public ImageView f76873J;

        /* renamed from: K */
        public MaterialRippleLayout f76874K;

        public FavoriteItemViewHolder(Context context, View view) {
            super(view);
            this.f76872I = (TextView) view.findViewById(C4804R.C4808id.text);
            this.f76874K = (MaterialRippleLayout) view.findViewById(C4804R.C4808id.f87007ripple_layout);
            this.f76873J = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public class HeaderViewHolder {

        /* renamed from: a */
        public final TextView f76876a;

        /* renamed from: b */
        public final ImageView f76877b;

        /* renamed from: c */
        public final ImageView f76878c;

        public HeaderViewHolder(View view) {
            this.f76876a = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76877b = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76878c = (ImageView) view.findViewById(C4804R.C4808id.icon);
        }
    }

    /* loaded from: classes2.dex */
    public class ListViewItemContentSearchPlaceHolder {

        /* renamed from: a */
        public TextView f76880a;

        /* renamed from: b */
        public TextView f76881b;

        public ListViewItemContentSearchPlaceHolder(View view) {
            this.f76880a = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f76881b = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
        }
    }

    /* loaded from: classes2.dex */
    public class ListViewItemFavoritePlaceHolder {

        /* renamed from: a */
        public TextView f76883a;

        /* renamed from: b */
        public ImageView f76884b;

        public ListViewItemFavoritePlaceHolder(View view) {
            this.f76883a = (TextView) view.findViewById(C4804R.C4808id.text);
            this.f76884b = (ImageView) view.findViewById(C4804R.C4808id.f87005remove_icon);
        }
    }

    /* loaded from: classes2.dex */
    public class SearchHeaderViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: I */
        public ImageView f76886I;

        /* renamed from: J */
        public TextView f76887J;

        /* renamed from: K */
        public ImageView f76888K;

        public SearchHeaderViewHolder(Context context, View view) {
            super(view);
            this.f76887J = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f76886I = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
            this.f76888K = (ImageView) view.findViewById(C4804R.C4808id.icon);
        }
    }

    /* renamed from: E3 */
    public void m3648E3() {
        if (this.f76808q4) {
            return;
        }
        this.f76797f4.m42923n(this.f76805n4);
        this.f76808q4 = true;
    }

    /* renamed from: F3 */
    public void m3647F3(String str) {
        CompressHelper compressHelper = this.f76809r4;
        String m4972P0 = compressHelper.m4972P0();
        compressHelper.m4885q(m4972P0, "Delete from favorites where _id=" + str);
    }

    /* renamed from: G3 */
    public Activity m3646G3() {
        return this.f76800i4;
    }

    /* renamed from: H3 */
    public ArrayList<Bundle> m3645H3() {
        String str = this.f76809r4.m4856z1() + "/favorites.json";
        ArrayList<Bundle> m3637l3 = m3637l3();
        if (m3637l3 == null) {
            return null;
        }
        if (new File(str).exists()) {
            try {
                JSONArray jSONArray = new JSONArray(new String(this.f76809r4.m4980M1(str)));
                ArrayList<Bundle> arrayList = new ArrayList<>();
                int i = 0;
                while (i < jSONArray.length()) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    JSONArray jSONArray2 = jSONObject.getJSONArray(FirebaseAnalytics.Param.f55203f0);
                    ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        final JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                        ArrayList arrayList3 = new ArrayList(Collections2.m23110e(m3637l3, new Predicate<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.4
                            @Override // com.google.common.base.Predicate
                            /* renamed from: a */
                            public boolean apply(Bundle bundle) {
                                try {
                                    if (bundle.getString("dbName").equals(jSONObject2.getString("Database"))) {
                                        return bundle.getString("dbAddress").equals(jSONObject2.getString("Address"));
                                    }
                                    return false;
                                } catch (Exception e) {
                                    FirebaseCrashlytics.m18030d().m18027g(e);
                                    iMDLogger.m3294f("Error in filtering", e.getLocalizedMessage());
                                    return false;
                                }
                            }
                        }));
                        JSONArray jSONArray3 = jSONArray;
                        if (arrayList3.size() == 1) {
                            Bundle bundle = (Bundle) arrayList3.get(0);
                            m3637l3.remove(bundle);
                            bundle.remove("dbDocName");
                            bundle.putString("dbDocName", jSONObject2.getString("Title"));
                            arrayList2.add(bundle);
                        }
                        i2++;
                        jSONArray = jSONArray3;
                    }
                    JSONArray jSONArray4 = jSONArray;
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("title", jSONObject.getString("title"));
                    bundle2.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList2);
                    arrayList.add(bundle2);
                    i++;
                    jSONArray = jSONArray4;
                }
                if (m3637l3.size() > 0) {
                    ArrayList<Bundle> m4941Z1 = this.f76809r4.m4941Z1(m3637l3, "dbTitle");
                    for (int i3 = 0; i3 < m4941Z1.size(); i3++) {
                        final Bundle bundle3 = m4941Z1.get(i3);
                        ArrayList arrayList4 = new ArrayList(Collections2.m23110e(arrayList, new Predicate<Bundle>() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.5
                            @Override // com.google.common.base.Predicate
                            /* renamed from: a */
                            public boolean apply(Bundle bundle4) {
                                return bundle4.getString("title").equals(bundle3.getString("title"));
                            }
                        }));
                        if (arrayList4.size() == 0) {
                            Bundle bundle4 = new Bundle();
                            bundle4.putString("title", bundle3.getString("title"));
                            bundle4.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, bundle3.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0));
                            arrayList.add(bundle4);
                        } else {
                            Bundle bundle5 = (Bundle) arrayList4.get(0);
                            ArrayList<? extends Parcelable> parcelableArrayList = bundle5.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
                            parcelableArrayList.addAll(bundle3.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0));
                            int indexOf = arrayList.indexOf(bundle5);
                            Bundle bundle6 = new Bundle();
                            bundle6.putString("title", bundle5.getString("title"));
                            bundle6.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, parcelableArrayList);
                            arrayList.remove(indexOf);
                            arrayList.add(indexOf, bundle6);
                        }
                    }
                }
                return arrayList;
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("Error in Reading Json", e.getLocalizedMessage());
            }
        }
        return this.f76809r4.m4941Z1(m3637l3, "dbTitle");
    }

    /* renamed from: I3 */
    public void m3644I3() {
        if (this.f76808q4) {
            this.f76797f4.m42906s1(this.f76805n4);
            this.f76808q4 = false;
        }
    }

    /* renamed from: J3 */
    public void m3643J3(ArrayList<Bundle> arrayList) {
        ArrayList<Bundle> arrayList2 = arrayList;
        if (arrayList2 == null) {
            return;
        }
        String str = this.f76809r4.m4856z1() + "/favorites.json";
        ArrayList arrayList3 = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < arrayList.size()) {
            Bundle bundle = arrayList2.get(i);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
            ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
            JSONArray jSONArray2 = new JSONArray();
            int i2 = 0;
            while (i2 < parcelableArrayList.size()) {
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i2);
                Bundle bundle3 = new Bundle();
                bundle3.putString("Database", bundle2.getString("dbName"));
                ArrayList arrayList5 = parcelableArrayList;
                bundle3.putString("Address", bundle2.getString("dbAddress"));
                String str2 = str;
                int i3 = i;
                bundle3.putString("Title", bundle2.getString("dbDocName"));
                arrayList4.add(bundle3);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("Database", bundle2.getString("dbName"));
                    jSONObject.put("Address", bundle2.getString("dbAddress"));
                    jSONObject.put("Title", bundle2.getString("dbDocName"));
                    jSONArray2.put(jSONObject);
                } catch (Exception unused) {
                }
                i2++;
                parcelableArrayList = arrayList5;
                str = str2;
                i = i3;
            }
            String str3 = str;
            int i4 = i;
            Bundle bundle4 = new Bundle();
            bundle4.putString("title", bundle.getString("title"));
            bundle4.putParcelableArrayList(FirebaseAnalytics.Param.f55203f0, arrayList4);
            arrayList3.add(bundle4);
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("title", bundle.getString("title"));
                jSONObject2.put(FirebaseAnalytics.Param.f55203f0, jSONArray2);
                jSONArray.put(jSONObject2);
            } catch (Exception unused2) {
            }
            i = i4 + 1;
            arrayList2 = arrayList;
            str = str3;
        }
        String str4 = str;
        String jSONArray3 = jSONArray.toString();
        if (new File(str4).exists()) {
            new File(str4).delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str4));
            IOUtils.write(jSONArray3, (OutputStream) fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in writing json", e.getLocalizedMessage());
        }
    }

    /* renamed from: K3 */
    public void m3642K3() {
        ArrayList<Bundle> arrayList = this.f76795d4;
        if (arrayList == null || arrayList.size() == 0) {
            mo3542i3("No Favorites");
        } else {
            mo3543h3();
        }
    }

    /* renamed from: L3 */
    public int m3641L3(ArrayList<Bundle> arrayList) {
        int i = 0;
        if (arrayList == null) {
            return 0;
        }
        Iterator<Bundle> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Bundle next = it2.next();
            if (next != null && next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0) != null) {
                i += this.f76804m4.contains(next.getString("title")) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            }
        }
        return i;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: M0 */
    public void mo3640M0(Activity activity) {
        iMDLogger.m3290j("favoritesFragment", "OnAttach");
        super.mo3640M0(activity);
        this.f76800i4 = activity;
    }

    /* renamed from: M3 */
    public int m3639M3(ArrayList<Bundle> arrayList) {
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
        LocalBroadcastManager.m43863b(m3646G3()).m43862c(this.f76810s4, new IntentFilter("net.imedicaldoctor.imd.favorite"));
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: T0 */
    public void mo3545T0(Menu menu, MenuInflater menuInflater) {
        try {
            m44716w().setTitle("");
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        menuInflater.inflate(C4804R.C4811menu.f87358menu_favorites, menu);
        final MenuItem findItem = menu.findItem(C4804R.C4808id.f86770action_edit);
        final MenuItem findItem2 = menu.findItem(C4804R.C4808id.f86769action_done);
        this.f76802k4 = findItem;
        this.f76803l4 = findItem2;
        findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.7
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                favoritesFragment.this.f75223T3.clearFocus();
                if (favoritesFragment.this.f76794c4 != null && favoritesFragment.this.f76794c4.size() != 0) {
                    favoritesFragment.this.f75223T3.m51655i0("", false);
                    favoritesFragment.this.f75223T3.clearFocus();
                    favoritesFragment.this.f76794c4 = null;
                    favoritesFragment.this.m3642K3();
                    favoritesFragment.this.m4330Y2();
                }
                findItem.setVisible(false);
                findItem2.setVisible(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(favoritesFragment.this.m44716w());
                favoritesFragment.this.f76807p4 = new RecyclerViewDragDropManager();
                favoritesFragment.this.f76807p4.m16096R((NinePatchDrawable) favoritesFragment.this.m44782a0().getDrawable(C4804R.C4807drawable.f86653material_shadow_z3_9));
                RecyclerView.Adapter m16065o = favoritesFragment.this.f76807p4.m16065o(new EditFavoritesAdapter());
                favoritesFragment.this.m3644I3();
                favoritesFragment.this.f76797f4.setLayoutManager(linearLayoutManager);
                favoritesFragment.this.f76797f4.setAdapter(m16065o);
                favoritesFragment.this.f76807p4.m16073h(favoritesFragment.this.f76797f4);
                return false;
            }
        });
        findItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.8
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (favoritesFragment.this.f76794c4 != null && favoritesFragment.this.f76794c4.size() != 0) {
                    favoritesFragment.this.f75223T3.m51655i0("", false);
                    favoritesFragment.this.f75223T3.clearFocus();
                    favoritesFragment.this.f76794c4 = null;
                    favoritesFragment.this.m3642K3();
                    favoritesFragment.this.m4330Y2();
                }
                findItem.setVisible(true);
                findItem2.setVisible(false);
                favoritesFragment.this.f76807p4.m16100N();
                favoritesFragment.this.m3648E3();
                favoritesFragment.this.f76805n4.m8633n();
                favoritesFragment.this.f76793b4.m42860G();
                favoritesFragment.this.f76801j4 = false;
                favoritesFragment favoritesfragment = favoritesFragment.this;
                favoritesfragment.m3643J3(favoritesfragment.f76795d4);
                favoritesFragment.this.m3642K3();
                return false;
            }
        });
        menu.findItem(C4804R.C4808id.f86777action_navigation).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.9
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ((mainActivity) favoritesFragment.this.m44716w()).m3604I0();
                return true;
            }
        });
        final SearchView searchView = (SearchView) menu.findItem(C4804R.C4808id.f86789action_search).getActionView();
        this.f75223T3 = searchView;
        if (Build.VERSION.SDK_INT >= 26) {
            searchView.setImportantForAutofill(8);
        }
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Favorites");
        final String str = this.f76798g4;
        this.f75223T3.postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.10
            @Override // java.lang.Runnable
            public void run() {
                favoritesFragment.this.f76799h4 = true;
                favoritesFragment.this.f75223T3.m51655i0(str, false);
                String str2 = favoritesFragment.this.f75210G3;
                if (str2 == null || str2.length() <= 0) {
                    return;
                }
                if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                    favoritesFragment favoritesfragment = favoritesFragment.this;
                    favoritesfragment.f75223T3.m51655i0(favoritesfragment.f75210G3, true);
                } else {
                    favoritesFragment favoritesfragment2 = favoritesFragment.this;
                    favoritesfragment2.f75223T3.m51655i0(favoritesfragment2.f75210G3, false);
                    favoritesFragment.this.mo3543h3();
                }
                favoritesFragment.this.m4330Y2();
            }
        }, 10L);
        this.f76799h4 = false;
        ((ImageView) searchView.findViewById(C4804R.C4808id.search_close_btn)).setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                searchView.m51655i0("", false);
                searchView.clearFocus();
                favoritesFragment.this.f76794c4 = null;
                favoritesFragment.this.m3642K3();
                favoritesFragment.this.m4330Y2();
                favoritesFragment.this.f76793b4.m42860G();
                favoritesFragment.this.f76805n4.m8633n();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.12
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            /* renamed from: a */
            public boolean mo3520a(final String str2) {
                if (favoritesFragment.this.f76799h4) {
                    if (str2.length() != 0) {
                        favoritesFragment.this.f76798g4 = str2;
                        favoritesFragment.this.f75210G3 = str2;
                        new AsyncTask() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.12.1
                            @Override // android.os.AsyncTask
                            protected Object doInBackground(Object[] objArr) {
                                ArrayList<Bundle> arrayList = CompressHelper.f73778s;
                                ArrayList arrayList2 = new ArrayList();
                                for (int i = 0; i < arrayList.size(); i++) {
                                    arrayList2.add("WHEN '" + arrayList.get(i).getString("Name").replace("'", "''") + "' THEN " + i);
                                }
                                String str3 = "case dbName " + StringUtils.join(arrayList2, StringUtils.SPACE) + " end";
                                CompressHelper compressHelper = favoritesFragment.this.f76809r4;
                                ArrayList<Bundle> m4946Y = compressHelper.m4946Y(compressHelper.m4972P0(), "Select * from favorites where dbDocName like '%" + str2 + "%' order by " + str3);
                                favoritesFragment favoritesfragment = favoritesFragment.this;
                                favoritesfragment.f76794c4 = favoritesfragment.f76809r4.m4941Z1(m4946Y, "dbTitle");
                                return null;
                            }

                            @Override // android.os.AsyncTask
                            protected void onPostExecute(Object obj) {
                                if (favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) {
                                    favoritesFragment.this.mo3542i3("Nothing Found");
                                    return;
                                }
                                favoritesFragment.this.mo3543h3();
                                favoritesFragment.this.f76793b4.m42860G();
                                favoritesFragment.this.f76805n4.m8633n();
                            }

                            @Override // android.os.AsyncTask
                            protected void onPreExecute() {
                                favoritesFragment.this.mo3542i3("Searching ...");
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                        return false;
                    }
                    favoritesFragment.this.m3642K3();
                    favoritesFragment.this.f76794c4 = null;
                    favoritesFragment.this.f76793b4.m42860G();
                    favoritesFragment.this.f76805n4.m8633n();
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
        m44716w().setTitle("");
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment, androidx.fragment.app.Fragment
    /* renamed from: U0 */
    public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.f75221R3;
        if (view != null) {
            return view;
        }
        this.f76804m4 = new ArrayList<>();
        this.f76809r4 = new CompressHelper(m44716w());
        View inflate = layoutInflater.inflate(C4804R.C4810layout.f87152fragment_favorites, viewGroup, false);
        this.f75221R3 = inflate;
        if (bundle != null && bundle.containsKey("Position")) {
            this.f75209F3 = bundle.getInt("Position");
        }
        if (bundle != null && bundle.containsKey("Query")) {
            this.f75210G3 = bundle.getString("Query");
        }
        if (bundle != null && bundle.containsKey("mIsSubmitted")) {
            this.f76796e4 = bundle.getBoolean("mIsSubmitted");
        }
        this.f76797f4 = (RecyclerView) this.f75221R3.findViewById(C4804R.C4808id.f87001recycler_view);
        this.f76794c4 = new ArrayList<>();
        if (bundle != null && bundle.containsKey("mResults")) {
            this.f76794c4 = bundle.getParcelableArrayList("mResults");
        }
        this.f76795d4 = new ArrayList<>();
        if (bundle != null && bundle.containsKey("mFavorites")) {
            this.f76795d4 = bundle.getParcelableArrayList("mFavorites");
        }
        this.f76793b4 = new FavoriteAdapter();
        ArrayList<Bundle> arrayList = this.f76795d4;
        if (arrayList == null || arrayList.size() == 0) {
            ArrayList<Bundle> m3645H3 = m3645H3();
            this.f76795d4 = m3645H3;
            m3643J3(m3645H3);
        }
        StickyRecyclerHeadersDecoration stickyRecyclerHeadersDecoration = new StickyRecyclerHeadersDecoration(this.f76793b4, new ItemVisibilityAdapter() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.1
            @Override // com.timehop.stickyheadersrecyclerview.ItemVisibilityAdapter
            /* renamed from: a */
            public boolean mo3525a(int i) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) favoritesFragment.this.f76797f4.getLayoutManager();
                linearLayoutManager.m43136x2();
                linearLayoutManager.m43194A2();
                Boolean valueOf = Boolean.valueOf(linearLayoutManager.m43136x2() <= i && linearLayoutManager.m43194A2() >= i);
                iMDLogger.m3294f(CSS.Property.f65564m0, i + " visible + " + valueOf);
                return valueOf.booleanValue();
            }
        });
        this.f76805n4 = stickyRecyclerHeadersDecoration;
        StickyRecyclerHeadersTouchListener stickyRecyclerHeadersTouchListener = new StickyRecyclerHeadersTouchListener(this.f76797f4, stickyRecyclerHeadersDecoration);
        this.f76806o4 = stickyRecyclerHeadersTouchListener;
        stickyRecyclerHeadersTouchListener.m8624h(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.2
            @Override // com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener.OnHeaderClickListener
            /* renamed from: a */
            public void mo3512a(View view2, int i, long j) {
                String m4960T0 = favoritesFragment.this.f76809r4.m4960T0(((Bundle) ((favoritesFragment.this.f76794c4 == null || favoritesFragment.this.f76794c4.size() == 0) ? favoritesFragment.this.f76795d4 : favoritesFragment.this.f76794c4).get((int) j)).getString("title"));
                if (favoritesFragment.this.f76804m4.contains(m4960T0)) {
                    favoritesFragment.this.f76804m4.remove(m4960T0);
                } else {
                    favoritesFragment.this.f76804m4.add(m4960T0);
                }
                favoritesFragment.this.f76797f4.getAdapter().m42860G();
            }
        });
        this.f76797f4.m42914q(this.f76806o4);
        m3648E3();
        this.f76797f4.setLayoutManager(new LinearLayoutManager(m44716w()));
        this.f76797f4.setItemAnimator(new DefaultItemAnimator());
        this.f76797f4.m42923n(new DividerItemDecoration(m44716w(), 1));
        this.f76797f4.setAdapter(this.f76793b4);
        this.f76793b4.m42849Z(new RecyclerView.AdapterDataObserver() { // from class: net.imedicaldoctor.imd.Fragments.favoritesFragment.3
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            /* renamed from: a */
            public void mo3511a() {
                favoritesFragment.this.f76805n4.m8633n();
            }
        });
        m44735q2(true);
        m3642K3();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: V0 */
    public void mo3638V0() {
        super.mo3638V0();
        LocalBroadcastManager.m43863b(m3646G3()).m43859f(this.f76810s4);
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment
    /* renamed from: h3 */
    public void mo3543h3() {
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter = this.f76797f4.getAdapter();
        FavoriteAdapter favoriteAdapter = this.f76793b4;
        if (adapter != favoriteAdapter) {
            m3648E3();
            recyclerView = this.f76797f4;
            favoriteAdapter = this.f76793b4;
        } else {
            recyclerView = this.f76797f4;
        }
        recyclerView.setAdapter(favoriteAdapter);
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment
    /* renamed from: i3 */
    public void mo3542i3(String str) {
        try {
            if (!str.equals("Searching")) {
                this.f76805n4.m8633n();
                m3644I3();
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
        }
        this.f76797f4.setLayoutManager(new LinearLayoutManager(m44716w(), 1, false));
        this.f76797f4.setAdapter(new StatusAdapter(m44716w(), str));
    }

    @Override // androidx.fragment.app.Fragment
    /* renamed from: l1 */
    public void mo3541l1() {
        super.mo3541l1();
        m4330Y2();
    }

    /* renamed from: l3 */
    public ArrayList<Bundle> m3637l3() {
        ArrayList<Bundle> arrayList = CompressHelper.f73778s;
        Log.e("Speed", "Favorites sortedDatabases");
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add("WHEN '" + arrayList.get(i).getString("Name").replace("'", "''") + "' THEN " + i);
            }
        }
        if (arrayList2.size() > 0) {
            String str = "case dbName " + StringUtils.join(arrayList2, StringUtils.SPACE) + " end";
            CompressHelper compressHelper = this.f76809r4;
            return compressHelper.m4946Y(compressHelper.m4972P0(), "select * from favorites order by " + str);
        }
        return null;
    }

    @Override // net.imedicaldoctor.imd.Fragments.SearchHelperFragment, androidx.fragment.app.Fragment
    /* renamed from: m1 */
    public void mo3501m1(Bundle bundle) {
        super.mo3501m1(bundle);
    }

    /* renamed from: m3 */
    public int m3636m3(int i, ArrayList<Bundle> arrayList) {
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Bundle bundle = arrayList.get(i3);
            i2 += this.f76804m4.contains(bundle.getString("title")) ? 1 : bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            if (i < i2) {
                return i3;
            }
        }
        return 0;
    }

    /* renamed from: n3 */
    public Bundle m3635n3(int i, ArrayList<Bundle> arrayList) {
        Iterator<Bundle> it2 = arrayList.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            Bundle next = it2.next();
            String m4960T0 = this.f76809r4.m4960T0(next.getString("title"));
            int size = this.f76804m4.contains(m4960T0) ? 1 : next.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size();
            i2 += size;
            if (i < i2) {
                int i3 = i - (i2 - size);
                Bundle bundle = new Bundle();
                bundle.putString("Database", this.f76809r4.m4960T0(next.getString("title")));
                if (this.f76804m4.contains(m4960T0)) {
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

    /* renamed from: o3 */
    public Bundle m3634o3(int i, ArrayList<Bundle> arrayList) {
        Iterator<Bundle> it2 = arrayList.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it2.hasNext()) {
            Bundle next = it2.next();
            if (i == i2) {
                Bundle bundle = new Bundle();
                bundle.putString("Title", this.f76809r4.m4960T0(next.getString("title")));
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

    /* renamed from: p3 */
    public void m3633p3() {
        this.f76795d4 = m3645H3();
        this.f76805n4.m8633n();
        m3648E3();
        this.f76793b4.m42860G();
        m3642K3();
    }
}
